import java.util.ArrayList;

public class ParkingLot {
	public Level[] levels;

	public ParkingLot(int levels, int rowsPerLevel, int miniSpotsPerRow, int compactSpotsPerRow, int largeSpotsPerRow) {
		this.levels = new Level[levels];
		for (int floor = 0; floor < levels; floor++) {
			this.levels[floor] = new Level(floor, rowsPerLevel, miniSpotsPerRow, compactSpotsPerRow, largeSpotsPerRow);
		}

	}

	public boolean parkVehicle(Vehicle vehicle) {
		for (int i = 0; i < levels.length; i++) {
			if (levels[i].parkVehicle(vehicle)) {
				return true;
			}
		}
		return false;
	}

	public ParkingSpot getSpot(int level, int row, int spot) {
		return this.levels[level].getSpot(row, spot);
	}

	public void printReport() {
		Report fullReport = new Report(0, 0, 0, new ArrayList<ParkingSpot>());
		Report[] reports = new Report[this.levels.length];
		int totalSpots = 0;
		int totalLargeSpots = 0;
		int totalCompactSpots = 0;
		int totalMiniSpots = 0;
		for (int level = 0; level < this.levels.length; level++) {
			reports[level] = this.levels[level].levelReport();
			fullReport.takenLargeSpots += reports[level].takenLargeSpots;
			fullReport.takenCompactSpots += reports[level].takenCompactSpots;
			fullReport.takenMiniSpots += reports[level].takenMiniSpots;
			fullReport.takenSpots.addAll(reports[level].takenSpots);
			totalSpots += this.levels[level].getTotalSpots();
			totalLargeSpots += this.levels[level].getTotalLargeSpots();
			totalCompactSpots += this.levels[level].getTotalCompactSpots();
			totalMiniSpots += this.levels[level].getTotalCompactSpots();
		}

		int totalSpotsTaken = fullReport.takenSpots.size();
		float occupancyPercentage = ((float) totalSpotsTaken / totalSpots) * 100;
		float largeSpotsTakenPercentage = ((float) fullReport.takenLargeSpots / totalLargeSpots) * 100;
		float compactSpotsTakenPercentage = ((float) fullReport.takenCompactSpots / totalCompactSpots) * 100;
		float miniSpotsTakenPercentage = ((float) fullReport.takenMiniSpots / totalMiniSpots) * 100;
		System.out.println("percentage of occupation (parking): " + occupancyPercentage + "% ("
				+ largeSpotsTakenPercentage + "% large spots," + compactSpotsTakenPercentage + "% compact spots, "
				+ miniSpotsTakenPercentage + "% mini spots)");
		for (int x = 0; x < fullReport.takenSpots.size(); x++) {
			fullReport.takenSpots.get(x).report();
		}
	}

	public void print() {
		for (int i = 0; i < levels.length; i++) {
			System.out.print("Level " + i + ": ");
			levels[i].print();
			System.out.println("");
		}
		System.out.println("");
	}
}
