import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
	
	/* Add a seat to available list*/
	public void addAvailableSeat(int level, Seat seat) {
		for(int i = availableSeats.size(); i < level; i++) {
			this.availableSeats.add(i, new Stack<Seat>());
		}
		
		this.availableSeats.get(level-1).push(seat);
		this.numAvailableSeats++;
	}
	
	/* Get number of available seats*/
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
	
	/* Create a seatHold and return it back*/
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
	
	/* Search for a seathold. Return null if not found*/
	public SeatHold getSeatHold(int seatHoldId) {
		return this.seatHoldMap.get(seatHoldId);
	}
	
	/* Remove seathold from map. Return null if not found*/
	public synchronized SeatHold removeSeatHold(int seatHoldId) {
		SeatHold seatHold = this.seatHoldMap.remove(seatHoldId);
		
		return seatHold;
	}

	/*Reserve seats using the seatHold id*/
	public SeatHold reserveSeatsFromSeatHold(int seatHoldId, String customerEmail) {
		if(!this.getSeatHold(seatHoldId).getSeatHoldCustomerEmail().equals(customerEmail)) return null;
		
		SeatHold seatHold = removeSeatHold(seatHoldId);
		if(seatHold == null) return null;
		for(Seat seat: seatHold.getSeats()) {
			seat.setOwner(customerEmail);
		}
		
		return seatHold;
	}
	
	/* Remove expire seathold from map*/
	public void removeExpiredSeatHold() {
		ArrayList<Integer> expiredSeatHoldID = new ArrayList<Integer>();
		Iterator seatHoldIterator = this.seatHoldMap.entrySet().iterator();
		
		while(seatHoldIterator.hasNext()) {
			long dateNowInSecond = (new Date().getTime())/1000;
			SeatHold sh = (SeatHold) ((Map.Entry)seatHoldIterator.next()).getValue();
			if(dateNowInSecond - sh.getDateCreated() >= this.holdExpireTime) {
				expiredSeatHoldID.add(sh.getSeatHoldId());
			}
		}
		
		for(int i = 0; i < expiredSeatHoldID.size(); i++) {
			ArrayList<Seat> removedSeats = this.removeSeatHold(expiredSeatHoldID.get(i)).getSeats();
			
			for(int j = 0; j < removedSeats.size(); j++) {
				Seat seat = removedSeats.get(j);
				this.addAvailableSeat(seat.getSeatLevel(), seat);
			}
		}
	}
}
