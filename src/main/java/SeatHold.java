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
	
	public void addSeat(Seat seat) {
		this.seats.add(seat);
	}
	
	public int getSeatHoldId() { return this.seatHoldId; }
	
	public String getSeatHoldCustomerEmail() { return this.customerEmail; }
	
	public ArrayList<Seat> getSeats() { return this.seats; }
	
	public long getDateCreated() { return this.dateCreatedInSecond; }
	public void setDateCreated(long dateCreatedInSec) { this.dateCreatedInSecond = dateCreatedInSec; }
}
