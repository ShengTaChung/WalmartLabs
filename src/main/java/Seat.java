
public class Seat {
	private static int numSeats = 0;
	private int seatID;
	private int seatLevel;
	private String ownerEmail;
	
	
	public Seat(int seatLevel) {
		numSeats++;
		this.seatLevel = seatLevel;
		this.seatID = numSeats;
		this.ownerEmail = "noOwner";
	}
	
	public static int getNumberOfSeats() { return numSeats; }
	
	public int getSeatID() { return this.seatID; }
	
	public int getSeatLevel() { return this.seatLevel; }
	
	public static void resetNumSeats() { numSeats = 0; }
	
	public void setOwner(String ownerEmail) { this.ownerEmail = ownerEmail; }
	
	public String getOwnerEmail() { return this.ownerEmail; }
}
