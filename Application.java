import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Application {
	public static HashMap<String, Vehicle> catalog = new HashMap<String, Vehicle>();

	public static void main(String[] args) {
		int levels = 5;
		int rows = 15;
		int miniSpots = 15;
		int compactSpots = 40;
		int largeSpots = 20;
		ParkingLot lot = new ParkingLot(levels, rows, miniSpots, compactSpots, largeSpots);
		while (takeCommand(lot)) {
			lot.print();
		}

	}

	public static boolean takeCommand(ParkingLot lot) {
		String employeeNumber;
		Vehicle vehicle;
		Scanner user_input = new Scanner(System.in);
		System.out.println("Enter Commmand:");
		switch (user_input.next()) {
		case "park":
			employeeNumber = user_input.next();

			System.out.println(employeeNumber);
			switch (user_input.next()) {
			case "bike":
				vehicle = new Bike(employeeNumber);
				lot.parkVehicle(vehicle);
				catalog.put(employeeNumber, vehicle);
				System.out.println(vehicle.status());
				break;
			case "motorcycle":
				vehicle = new Motorcycle(employeeNumber);
				lot.parkVehicle(vehicle);
				catalog.put(employeeNumber, vehicle);
				System.out.println(vehicle.status());
				break;
			case "car":
				vehicle = new Car(employeeNumber);
				lot.parkVehicle(vehicle);
				catalog.put(employeeNumber, vehicle);
				System.out.println(vehicle.status());
				break;
			case "bus":
				vehicle = new Bus(employeeNumber);
				lot.parkVehicle(vehicle);
				catalog.put(employeeNumber, vehicle);
				System.out.println(vehicle.status());
				break;
			default:
				System.out.println("Lot can only park bike, motocycle, car or Bus");
				break;
			}
			break;
		case "employee":
			employeeNumber = user_input.next();
			vehicle = catalog.get(employeeNumber);
			if (vehicle != null) {
				System.out.println(vehicle.status());
			} else {
				System.out.println("There is no vehicle registered for employee " + employeeNumber);
			}
			break;

		case "parking":

			break;
		case "leave":
			System.out.print("level:");
			int levelNumber = user_input.nextInt();
			System.out.print("row:");
			int rowNumber = user_input.nextInt();
			System.out.print("spot:");
			int spotNumber = user_input.nextInt();
			ParkingSpot spot = lot.getSpot(levelNumber, rowNumber, spotNumber);
			vehicle = spot.getVehicle();
			vehicle.clearSpots();
			catalog.remove(vehicle.employeeNumber, vehicle);
			break;
		case "help":
			System.out.println("To park a car enter:");
			System.out.println("park {employee id} {vehicle type}");
			System.out.println("eg. \"park 12345678 bike\"");
			System.out.println("");

			System.out.println("To find an employees car enter:");
			System.out.println("employee {employee id}");
			System.out.println("eg. \"employee 12345678\"");
			System.out.println("");

			System.out.println("To see parking occupation enter:");
			System.out.println("parking");
			System.out.println("eg. \"employee 12345678\"");
			System.out.println("");
			break;
		case "exit":
			System.out.println("Exiting the program");
			return false;
		default:
			System.out.println("Invalid command. Type help to see a list of commands");
			break;

		}
		return true;
	}

}
