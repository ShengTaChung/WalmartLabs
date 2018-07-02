import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class VenueOrganizer {
	private int holdExpireTime;
	private ArrayList<Stack<Seat>> availableSeats;
	private int numAvailableSeats;
	private HashMap<Integer, SeatHold> seatHoldMap;
	
	
	public VenueOrganizer(int holdExpireTime, int numSeatingLevels) {
		this.holdExpireTime = holdExpireTime;
		this.availableSeats = new ArrayList<Stack<Seat>>(numSeatingLevels);
		seatHoldMap = new HashMap<Integer, SeatHold>();
	}
	
	public void addAvailableSeat(int level, Seat seat) {
		for(int i = availableSeats.size(); i < level; i++) {
			this.availableSeats.add(i, new Stack<Seat>());
		}
		
		this.availableSeats.get(level-1).push(seat);
		this.numAvailableSeats++;
	}
	
	public int getNumberOfAvailableSeats() { return this.numAvailableSeats; }
	
	public synchronized Seat findAndHoldSeat() {
		for(int i = 0; i < availableSeats.size(); i++) {
			if(!availableSeats.get(i).isEmpty()) {
				this.numAvailableSeats--;
				return availableSeats.get(i).pop();
			}
		}
		return null;
	}
	
	public SeatHold createSeatHold(int numSeats, String customerEmail) {
		if(this.numAvailableSeats < numSeats) { return null; }
		
		SeatHold newSeatHold = new SeatHold(numSeats, customerEmail);
		
		for(int i = 0; i < numSeats; i++) {
			Seat newSeat = findAndHoldSeat();
			newSeatHold.addSeat(newSeat);
		}
		
		seatHoldMap.put(newSeatHold.getSeatHoldId(), newSeatHold);
		
		return newSeatHold;
	}
	
	//return null if not fund
	public SeatHold getSeatHold(int seatHoldId) {
		return this.seatHoldMap.get(seatHoldId);
	}
	
	public synchronized SeatHold removeSeatHold(int seatHoldId) {
		SeatHold seatHold = this.seatHoldMap.remove(seatHoldId);
		
		return seatHold;
	}

	public SeatHold reserveSeatsFromSeatHold(int seatHoldId, String customerEmail) {
		if(!this.getSeatHold(seatHoldId).getSeatHoldCustomerEmail().equals(customerEmail)) return null;
		
		SeatHold seatHold = removeSeatHold(seatHoldId);
		if(seatHold == null) return null;
		for(Seat seat: seatHold.getSeats()) {
			seat.setOwner(customerEmail);
		}
		
		return seatHold;
	}
}
