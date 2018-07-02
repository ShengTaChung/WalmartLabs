import java.util.UUID;

public class Server implements TicketService{
	private Venue venue;
	
	public Server(Venue venue) {
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
		
		SeatHold seatsReserved = organizer.reserveSeatsFromSeatHold(seatHoldId, customerEmail);
		if(seatsReserved == null) return null;
		
		return generateConfirmationCode();
	}
	
	public String generateConfirmationCode() {
		return UUID.randomUUID().toString();
	}
}