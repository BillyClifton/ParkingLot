import java.util.ArrayList;

public abstract class Vehicle {
	protected ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
	protected String employeeNumber;
	protected int spotsNeeded;
	protected VehicleSize size;

	public int getSpotsNeeded() {
		return spotsNeeded;
	}

	public VehicleSize getSize() {
		return size;
	}

	public void parkInSpot(ParkingSpot spot) {
		parkingSpots.add(spot);
	}

	public void clearSpots() {
		for (int i = 0; i < parkingSpots.size(); i++) {
			parkingSpots.get(i).removeVehicle();
		}
		parkingSpots.clear();
		System.out.println(this.getClass().getSimpleName() + " for employee " + employeeNumber + " left parking");
	}

	public String status() {
		ParkingSpot spot = parkingSpots.get(0);
		int row = spot.getRow();
		int level = spot.getLevel();
		int number = spot.getSpotNumber();
		return this.getClass().getSimpleName() + " for employee " + employeeNumber + " parked in level " + level
				+ ", row " + row + " spot " + number;
	}

	public abstract boolean canFitInSpot(ParkingSpot spot);

	public abstract void print();

}