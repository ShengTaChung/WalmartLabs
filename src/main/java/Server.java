import java.util.UUID;
//A simple ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance venue.

public class Server implements TicketService{
	private Venue venue;
	
	public Server(Venue venue) {
        this.venue = venue;
        venue.createSeatsForVenue();
    }
	
	/**
	* The number of seats in the venue that are neither held nor reserved
	*
	* @return the number of tickets available in the venue
	*/
	public int numSeatsAvailable() {
		VenueOrganizer organizer = this.venue.getMyOrganizer();
		return organizer.getNumberOfAvailableSeats();
	}
	
	/**
	* Find and hold the best available seats for a customer
	*
	* @param numSeats the number of seats to find and hold
	* @param customerEmail unique identifier for the customer
	* @return a SeatHold object identifying the specific seats and related information
	*/
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		VenueOrganizer organizer = this.venue.getMyOrganizer();
		SeatHold newSeatHold = organizer.createSeatHold(numSeats, customerEmail);
		
		return newSeatHold;
	}

	/**
	* Commit seats held for a specific customer
	*
	* @param seatHoldId the seat hold identifier
	* @param customerEmail the email address of the customer to which the seat hold is assigned
	* @return a reservation confirmation code
	*/
	public String reserveSeats(int seatHoldId, String customerEmail) {
		VenueOrganizer organizer = this.venue.getMyOrganizer();
		
		SeatHold seatsReserved = organizer.reserveSeatsFromSeatHold(seatHoldId, customerEmail);
		if(seatsReserved == null) return null;
		
		return generateConfirmationCode();
	}
	
	/**
	* Generate a confirmation code
	*
	* @param seatHoldId the seat hold identifier
	* @param customerEmail the email address of the customer to which the seat hold is assigned
	* @return a confirmation code
	*/
	public String generateConfirmationCode() {
		return UUID.randomUUID().toString();
	}
}