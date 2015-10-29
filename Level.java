import java.util.ArrayList;

public class Level {
	private int floor;
	private ParkingSpot[] spots;
	private int availableSpots = 0;
	private int spotsPerRow;
	private int largeSpotsPerRow;
	private int compactSpotsPerRow;
	private int miniSpotsPerRow;
	private int rows;

	public Level(int floor, int rows, int miniSpotsPerRow, int compactSpotsPerRow, int largeSpotsPerRow) {
		this.floor = floor;
		this.largeSpotsPerRow = largeSpotsPerRow;
		this.compactSpotsPerRow = compactSpotsPerRow;
		this.miniSpotsPerRow = miniSpotsPerRow;
		this.rows = rows;
		this.spotsPerRow = miniSpotsPerRow + compactSpotsPerRow + largeSpotsPerRow;
		int numberOfSpots = rows * spotsPerRow;
		spots = new ParkingSpot[numberOfSpots];
		VehicleSize size;
		int spotCount = 0;
		for (int row = 0; row < rows; row++) {
			for (int spot = 0; spot < spotsPerRow; spot++) {

				if (spot < largeSpotsPerRow) {
					size = VehicleSize.Large;
				} else if (spot < largeSpotsPerRow + compactSpotsPerRow) {
					size = VehicleSize.Compact;
				} else {
					size = VehicleSize.Mini;
				}
				spots[spotCount] = new ParkingSpot(this, row, spot, size);
				spotCount++;
			}
		}
		availableSpots = numberOfSpots;
	}

	public int getFloor() {
		return floor;
	}

	public int availableSpots() {
		return availableSpots;
	}

	public boolean parkVehicle(Vehicle vehicle) {
		if (availableSpots() < vehicle.getSpotsNeeded()) {
			return false;
		}
		int spotNumber = findAvailableSpots(vehicle);
		if (spotNumber < 0) {
			return false;
		}
		return parkStartingAtSpot(spotNumber, vehicle);
	}

	private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle) {
		vehicle.clearSpots();
		boolean success = true;
		for (int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i++) {
			success &= spots[i].park(vehicle);
		}
		availableSpots -= vehicle.spotsNeeded;
		return success;
	}

	private int findAvailableSpots(Vehicle vehicle) {
		int spotsNeeded = vehicle.getSpotsNeeded();
		int lastRow = -1;
		int spotsFound = 0;
		for (int i = 0; i < spots.length; i++) {
			ParkingSpot spot = spots[i];
			if (lastRow != spot.getRow()) {
				spotsFound = 0;
				lastRow = spot.getRow();
			}
			if (spot.canFitVehicle(vehicle)) {
				spotsFound++;
			} else {
				spotsFound = 0;
			}
			if (spotsFound == spotsNeeded) {
				return i - (spotsNeeded - 1);
			}
		}
		return -1;
	}

	public int getTotalSpots() {
		return this.spots.length;
	}

	public int getTotalLargeSpots() {
		return rows * largeSpotsPerRow;
	}

	public int getTotalCompactSpots() {
		return rows * compactSpotsPerRow;
	}

	public int getTotalMiniSpots() {
		return rows * miniSpotsPerRow;
	}

	public ParkingSpot getSpot(int row, int spot) {
		int spotNumber = ((row + 1) * spotsPerRow) + spot;
		return spots[spotNumber - 1];
	}

	public void print() {
		int lastRow = -1;
		for (int i = 0; i < spots.length; i++) {
			ParkingSpot spot = spots[i];
			if (spot.getRow() != lastRow) {
				System.out.print("  ");
				lastRow = spot.getRow();
			}
			spot.print();
		}
	}

	public Report levelReport() {
		Report fullReport = new Report(0, 0, 0, new ArrayList<ParkingSpot>());
		Report[] reports = new Report[this.rows];
		for (int row = 0; row < this.rows; row++) {
			reports[row] = rowReport(row);
			fullReport.takenLargeSpots += reports[row].takenLargeSpots;
			fullReport.takenCompactSpots += reports[row].takenCompactSpots;
			fullReport.takenMiniSpots += reports[row].takenMiniSpots;
			fullReport.takenSpots.addAll(reports[row].takenSpots);
		}
		return fullReport;
	}

	public void printLevelReport() {
		Report fullReport = levelReport();
		int totalSpots = spots.length;
		int totalSpotsTaken = fullReport.takenLargeSpots + fullReport.takenCompactSpots + fullReport.takenMiniSpots;
		float occupancyPercentage = ((float) totalSpotsTaken / totalSpots) * 100;
		float largeSpotsTakenPercentage = ((float) fullReport.takenLargeSpots / (this.largeSpotsPerRow * this.rows))
				* 100;
		float compactSpotsTakenPercentage = ((float) fullReport.takenCompactSpots
				/ (this.compactSpotsPerRow * this.rows)) * 100;
		float miniSpotsTakenPercentage = ((float) fullReport.takenMiniSpots / (this.miniSpotsPerRow * this.rows)) * 100;
		System.out.println("percentage of occupancy (parking level " + this.getFloor() + ": " + occupancyPercentage
				+ "% (" + largeSpotsTakenPercentage + "% large spots," + compactSpotsTakenPercentage
				+ "% compact spots, " + miniSpotsTakenPercentage + "% mini spots)");
		for (int x = 0; x < fullReport.takenSpots.size(); x++) {
			fullReport.takenSpots.get(x).report();
		}
	}

	public Report rowReport(int row) {
		int firstSpotOfRow = row * spotsPerRow;
		int takenLargeSpots = 0;
		int takenCompactSpots = 0;
		int takenMiniSpots = 0;
		ArrayList<ParkingSpot> takenSpots = new ArrayList<ParkingSpot>();
		for (int x = 0; x < spotsPerRow; x++) {
			if (spots[firstSpotOfRow + x].getVehicle() != null) {
				if (spots[firstSpotOfRow + x].getSize() == VehicleSize.Large) {
					takenLargeSpots++;
				} else if (spots[firstSpotOfRow + x].getSize() == VehicleSize.Compact) {
					takenCompactSpots++;
				} else if (spots[firstSpotOfRow + x].getSize() == VehicleSize.Mini) {
					takenMiniSpots++;
				}
				takenSpots.add(spots[firstSpotOfRow + x]);

			}
		}

		return new Report(takenLargeSpots, takenCompactSpots, takenMiniSpots, takenSpots);
	}

	public void printRowReport(Report r) {
		int totalSpotsTaken = r.takenLargeSpots + r.takenCompactSpots + r.takenMiniSpots;
		System.out.println(totalSpotsTaken);
		System.out.println("out of " + this.spotsPerRow);
		float occupancyPercentage = ((float) totalSpotsTaken / this.spotsPerRow) * 100;
		float largeSpotsTakenPercentage = ((float) r.takenLargeSpots / this.largeSpotsPerRow) * 100;
		float compactSpotsTakenPercentage = ((float) r.takenCompactSpots / this.compactSpotsPerRow) * 100;
		float miniSpotsTakenPercentage = ((float) r.takenMiniSpots / this.miniSpotsPerRow) * 100;
		System.out.println("percenttage of occupancy (parking level " + this.getFloor() + ": " + occupancyPercentage
				+ "% (" + largeSpotsTakenPercentage + "% large spots," + compactSpotsTakenPercentage
				+ "% compact spots, " + miniSpotsTakenPercentage + "% mini spots)");
		for (int x = 0; x < r.takenSpots.size(); x++) {
			r.takenSpots.get(x).report();
		}
	}

	public void spotFreed() {
		availableSpots++;
	}

}