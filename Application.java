import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Application {
	public static HashMap<String, Vehicle> catalog = new HashMap<String, Vehicle>();
	int levels = 5;
	int rows = 15;
	int miniSpots = 15;
	int compactSpots = 40;
	int largeSpots = 20;

	public static void main(String[] args) {
		int levels = 5;
		int rows = 15;
		int miniSpots = 15;
		int compactSpots = 40;
		int largeSpots = 20;
		ParkingLot lot = new ParkingLot(levels, rows, miniSpots, compactSpots, largeSpots);
		System.out.println("Type help for a list of commands");
		while (takeCommand(lot))
			;

	}

	public static boolean takeCommand(ParkingLot lot) {
		String employeeNumber;
		Vehicle vehicle;
		Scanner user_input = new Scanner(System.in);
		System.out.println("Enter Commmand:");
		switch (user_input.next()) {
		case "park":
			if (!user_input.hasNext()) {
				System.out.println("Park command expects an employee number. eg. park 12345678 car");
				return true;
			}
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
				System.out.println("Lot can only park bike, motocycle, car or bus.");
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
			// reporting
			lot.printReport();
			break;
		case "parkinglevel":
			if (user_input.next().equals("level")) {
				if (user_input.hasNextInt()) {
					int level = user_input.nextInt();
					System.out.println(level);
					// TODO: add row reporting and spot reporting
					System.out.println("printing level report");
					lot.levels[level].printLevelReport();
				} else {
					System.out.println("No level specified. Type 'help' to see a list of commands.");
				}
			} else {
				System.out.println("Unrecognized command.Type 'help' to see a list of commands.");
			}
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
			System.out.println(
					vehicle.getClass().getSimpleName() + " for employee " + vehicle.employeeNumber + " left parking");
			break;
		case "help":
			System.out.println("1. To park a car enter:");
			System.out.println("park {employee id} {vehicle type}");
			System.out.println("eg. \"park 12345678 bike\"");
			System.out.println("");

			System.out.println("2. To find an employee's car enter:");
			System.out.println("employee {employee id}");
			System.out.println("eg. \"employee 12345678\"");
			System.out.println("");

			System.out.println("3. To remove a vehicle from a spot enter:");
			System.out.println("leave");
			System.out.println("Then enter the row and spot at the prompt");
			System.out.println("");

			System.out.println("4. To see parking lot occupancy enter:");
			System.out.println("parking");
			System.out.println("");

			System.out.println("5. To see parking lot level occupancy enter:");
			System.out.println("parkinglevel {level}");
			System.out.println("eg. \"parkinglevel 1\" (Note: parking level starts with 0)");
			System.out.println("");

			System.out.println("6. To populate parkinglot with random vehicles enter:");
			System.out.println("populate");
			System.out.println("");

			System.out.println("7. To see visual representation of parking lot enter:");
			System.out.println("print");
			System.out.println("");

			break;
		case "print":
			System.out.println("Legend:");
			System.out.println("Open Spots \t Parked Vehicles");
			System.out.println("l: Large \t B: Bus");
			System.out.println("l: Compact \t C: Car");
			System.out.println("l: Mini \t M: Motorcycle");
			System.out.println("\t \t B: Bike");
			lot.print();
			break;
		case "populate":
			System.out.println("How many vehicles would you like to park?");
			if (user_input.hasNextInt()) {
				int numberOfSamples = user_input.nextInt();
				Random randomGenerator = new Random();
				int randomInt = 0;
				int randomEmployeeNumber = 0;
				Vehicle v;
				for (int x = numberOfSamples; x > 0; x--) {
					randomInt = randomGenerator.nextInt(10) + 1;
					randomEmployeeNumber = randomGenerator.nextInt(99999999);
					employeeNumber = String.valueOf(randomEmployeeNumber);
					if (randomInt < 2) {
						System.out.println("parking bus for " + employeeNumber);
						v = new Bus(employeeNumber);
					} else if (randomInt < 4) {
						System.out.println("parking Bike for " + employeeNumber);
						v = new Bike(employeeNumber);
					} else if (randomInt < 6) {
						System.out.println("parking Motorcycle for " + employeeNumber);
						v = new Motorcycle(employeeNumber);
					} else {
						System.out.println("parking car for " + employeeNumber);
						v = new Car(employeeNumber);
					}
					if (lot.parkVehicle(v)) {
						catalog.put(employeeNumber, v);
						System.out.println(v.status());

					} else {
						System.out.println("No space to park vehicle.");
						break;
					}
				}
			} else {
				System.out.println("Invalid number of vehicles. You must enter an integer.");
			}
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
