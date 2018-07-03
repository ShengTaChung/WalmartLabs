import java.util.Date;
import java.util.ArrayList;

public class SeatHold {
	private static int numSeatHolds = 0;
	private int seatHoldId;
	private String customerEmail;
	private ArrayList<Seat> seats;
	private long dateCreatedInSecond;
	
	public SeatHold(int numSeats, String customerEmail) {
		this.seatHoldId = numSeatHolds + 1;
		numSeatHolds++;
		this.customerEmail = customerEmail;
		
		dateCreatedInSecond = (new Date().getTime())/1000;
		seats = new ArrayList<Seat>(numSeats);
	}
	
	/* Add a seat to this seathold */
	public void addSeat(Seat seat) {
		this.seats.add(seat);
	}
	
	/* Get seatHold's id*/
	public int getSeatHoldId() { return this.seatHoldId; }
	
	/* Get holder's email*/
	public String getSeatHoldCustomerEmail() { return this.customerEmail; }
	
	/* Get all seats in the seatHold */
	public ArrayList<Seat> getSeats() { return this.seats; }
	
	/* Set the seathold creation date */
	public long getDateCreated() { return this.dateCreatedInSecond; }
	
	/* Get the seathold creation date */
	public void setDateCreated(long dateCreatedInSec) { this.dateCreatedInSecond = dateCreatedInSec; }
}
