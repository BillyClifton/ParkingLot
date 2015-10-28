public class Motorcycle extends Vehicle {
	public Motorcycle(String employee) {
		spotsNeeded = 1;
		size = VehicleSize.Mini;
		employeeNumber = employee;
	}

	public boolean canFitInSpot(ParkingSpot spot) {
		return true;
	}

	public void print() {
		System.out.print("M");
	}
}