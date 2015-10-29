
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

	public boolean canFitVehicle(Vehicle vehicle) {
		return isAvailable() && vehicle.canFitInSpot(this);
	}

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

	public int getLevel() {
		return level.getFloor();
	}

	public int getSpotNumber() {
		return spotNumber;
	}

	public VehicleSize getSize() {
		return spotSize;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void removeVehicle() {
		level.spotFreed();
		vehicle = null;
	}

	public void report() {
		if (this.vehicle != null) {
			System.out.println("level " + level.getFloor() + ", row " + row + ", spot " + spotNumber + ":"
					+ this.vehicle.getClass().getSimpleName() + " for employee " + this.vehicle.employeeNumber);
		} else {
			System.out.println("level " + level.getFloor() + ", row " + row + ", spot " + spotNumber + ": empty");
		}
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