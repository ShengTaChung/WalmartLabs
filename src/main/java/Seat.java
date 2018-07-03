
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
	
	/* Get number of seats created */
	public static int getNumberOfSeats() { return numSeats; }
	
	/* Get the seat id*/
	public int getSeatID() { return this.seatID; }
	
	/* Get this seat's level */
	public int getSeatLevel() { return this.seatLevel; }
	
	/* Get seat owner's email */
	public String getOwnerEmail() { return this.ownerEmail; }
	
	/* Reset number of seats created */
	public static void resetNumSeats() { numSeats = 0; }
	
	/*Set the owner of the seat*/
	public void setOwner(String ownerEmail) { this.ownerEmail = ownerEmail; }
}
