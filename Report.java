import java.util.ArrayList;

public class Report {
	public int takenLargeSpots;
	public int takenCompactSpots;
	public int takenMiniSpots;
	public ArrayList<ParkingSpot> takenSpots;

	public Report(int takenLargeSpots, int takenCompactSpots, int takenMiniSpots, ArrayList<ParkingSpot> takenSpots) {
		this.takenLargeSpots = takenLargeSpots;
		this.takenCompactSpots = takenCompactSpots;
		this.takenMiniSpots = takenMiniSpots;
		this.takenSpots = takenSpots;
	}

}