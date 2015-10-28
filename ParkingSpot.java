
public class ParkingSpot {
	private Vehicle vehicle;
	private VehicleSize spotSize;
	private int row;
	private int spotNumber;
	private Level level;
	
	public ParkingSpot(Level level, int row, int number, VehicleSize size) {
		this.level = level;
		this.row = row;
		this.spotNumber = number;
		this.spotSize = size;
	}
	
	public boolean isAvailable() {
		return vehicle == null;
	}
	
	/* Checks if the spot is big enough for the vehicle (and is available). This compares
	 * the SIZE only. It does not check if it has enough spots. */
	public boolean canFitVehicle(Vehicle vehicle) {
		return isAvailable() && vehicle.canFitInSpot(this);
	}
	
	/* Park vehicle in this spot. */
	public boolean park(Vehicle v) {
		if (!canFitVehicle(v)) {
			return false;
		}
		vehicle = v;
		vehicle.parkInSpot(this);
		return true;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getLevel(){
		return level.getFloor();
	}
	
	public int getSpotNumber() {
		return spotNumber;
	}
	
	public VehicleSize getSize() {
		return spotSize;
	}
	
	public Vehicle getVehicle(){
		return this.vehicle;
	}
	/* Remove vehicle from spot, and notify level that a new spot is available */
	public void removeVehicle() {
		level.spotFreed();
		vehicle = null;
	}
	
	public void print() {
		if (vehicle == null) {
			if (spotSize == VehicleSize.Compact) {
				System.out.print("c");
			} else if (spotSize == VehicleSize.Large) {
				System.out.print("l");
			} else if (spotSize == VehicleSize.Mini) {
				System.out.print("m");
			}
		} else {
			vehicle.print();
		}
	}
}