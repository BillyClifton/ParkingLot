
public class ParkingLot {
	private Level[] levels;

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

	public void print() {
		for (int i = 0; i < levels.length; i++) {
			System.out.print("Level " + i + ": ");
			levels[i].print();
			System.out.println("");
		}
		System.out.println("");
	}
}
