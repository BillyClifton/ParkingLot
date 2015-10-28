
public class Level {
	private int floor;
	private ParkingSpot[] spots;
	private int availableSpots = 0; 
	private int spotsPerRow;
	
	public Level(int floor, int rows, int miniSpotsPerRow, int compactSpotsPerRow, int largeSpotsPerRow){
		this.floor = floor;
		this.spotsPerRow = miniSpotsPerRow + compactSpotsPerRow + largeSpotsPerRow;
		int numberOfSpots = rows * spotsPerRow;
		spots = new ParkingSpot[numberOfSpots];
		VehicleSize size; 
		int spotCount = 0;
		System.out.println(spots.length);
		for(int row = 0; row < rows; row++){
			for(int spot = 0; spot < spotsPerRow; spot++){
				
				if (spot < largeSpotsPerRow) {
					size = VehicleSize.Large;
				} else if (spot < largeSpotsPerRow + compactSpotsPerRow) {
					size = VehicleSize.Compact;
				}else{
					size = VehicleSize.Mini;
				}
				spots[spotCount] = new ParkingSpot(this, row, spot, size);
				spotCount++;
			}
		}
		availableSpots = numberOfSpots;
	}
	
	public int getFloor(){
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
	public ParkingSpot getSpot(int row, int spot){
		int spotNumber = ((row+1) * spotsPerRow) + spot; 
		return spots[spotNumber-1];
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
	
	public void spotFreed() {
		availableSpots++;
	}
}