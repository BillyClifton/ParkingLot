public class Bike extends Vehicle {
	public Bike(String employee) {
		spotsNeeded = 1;
		size = VehicleSize.Mini;
		employeeNumber = employee;
	}

	public boolean canFitInSpot(ParkingSpot spot) {
		return true;
	}

	public void print() {
		System.out.print("B");
	}

}