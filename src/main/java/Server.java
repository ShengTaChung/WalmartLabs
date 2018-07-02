import java.util.UUID;

public class Server implements TicketService{
	private Venue venue;
	
	public Server(Venue venue, int holdExpireTime) {
        this.venue = venue;
        venue.createSeatsForVenue();
    }
	
	public int numSeatsAvailable() {
		VenueOrganizer organizer = this.venue.getMyOrganizer();
		return organizer.getNumberOfAvailableSeats();
	}

	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		VenueOrganizer organizer = this.venue.getMyOrganizer();
		SeatHold newSeatHold = organizer.createSeatHold(numSeats, customerEmail);
		
		return newSeatHold;
	}

	public String reserveSeats(int seatHoldId, String customerEmail) {
		VenueOrganizer organizer = this.venue.getMyOrganizer();
		SeatHold seatHold = organizer.getSeatHold(seatHoldId);
		
		if(seatHold == null || !seatHold.getSeatHoldCustomerEmail().equals(customerEmail)) {
			return null; 
		}
		SeatHold seatsReserved = organizer.removeSeatHold(seatHoldId);
		if(seatsReserved == null) return null;
		
		return generateConfirmationCode();
	}
	
	public String generateConfirmationCode() {
		return UUID.randomUUID().toString();
	}
}