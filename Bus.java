public class Bus extends Vehicle {
	public Bus(String employee) {
		spotsNeeded = 6;
		size = VehicleSize.Large;
		employeeNumber = employee;
	}

	public boolean canFitInSpot(ParkingSpot spot) {
		return spot.getSize() == VehicleSize.Large;
	}

	public void print() {
		System.out.print("B");
	}
}