import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

public class ServerTest {
	private VenueOrganizer organizer;
	MyVenue venue;
	int expireHoldTime;
	int seatsPerLevel;
	Server server;
	
    @Before  
    public void before() {  
    	expireHoldTime = 300;
    	seatsPerLevel = 2;
    	organizer = new VenueOrganizer(expireHoldTime, 4);
    	venue = new MyVenue(2, 4, 4, organizer, seatsPerLevel);
    	server = new Server(venue);
    }  
	
    @Test public void shouldReturnCorrectNumberOfSeatsAvailable(){
    	Assert.assertEquals(8, server.numSeatsAvailable());
    }
    
    
    @Test public void shouldReturnCorrectSeatHold(){
    	SeatHold sh1 = server.findAndHoldSeats(2, "e1@email.com");
		Assert.assertEquals(6, organizer.getNumberOfAvailableSeats());
		Assert.assertEquals(2, sh1.getSeats().size());
		Assert.assertEquals(1, sh1.getSeats().get(0).getSeatLevel());
		Assert.assertEquals(1, sh1.getSeats().get(1).getSeatLevel());
		
		SeatHold sh2 = server.findAndHoldSeats(3, "e2@email.com");
		Assert.assertEquals(3, organizer.getNumberOfAvailableSeats());
		Assert.assertEquals(3, sh2.getSeats().size());
		Assert.assertEquals(2, sh2.getSeats().get(0).getSeatLevel());
		Assert.assertEquals(2, sh2.getSeats().get(1).getSeatLevel());
		Assert.assertEquals(3, sh2.getSeats().get(2).getSeatLevel());
    }
    
	@Test public void shouldReturnNullWhenNotEnoughSeatsAvailable(){
		SeatHold sh1 = server.findAndHoldSeats(8, "e1@email.com");
		SeatHold sh2 = server.findAndHoldSeats(1, "e2@email.com");
		
		Assert.assertEquals(null, sh2);
	}
	
	@Test public void shouldReserveCorrectSeats() {
		SeatHold sh1 = server.findAndHoldSeats(2, "e1@email.com");
		int sh1ID = sh1.getSeatHoldId();
		ArrayList<Seat> sh1Seats = sh1.getSeats();
		
		Assert.assertEquals("noOwner", sh1Seats.get(0).getOwnerEmail());
		Assert.assertEquals("noOwner", sh1Seats.get(1).getOwnerEmail());
		
		server.reserveSeats(sh1ID, sh1.getSeatHoldCustomerEmail());
		Assert.assertEquals("e1@email.com", sh1Seats.get(0).getOwnerEmail());
		Assert.assertEquals("e1@email.com", sh1Seats.get(1).getOwnerEmail());
		
		Assert.assertEquals(null, organizer.getSeatHold(sh1ID));
		Assert.assertEquals(6, organizer.getNumberOfAvailableSeats());
	}
	
	@Test public void shouldReturnNullIfInvalidEmail() {
		SeatHold sh1 = server.findAndHoldSeats(2, "e1@email.com");
		int sh1ID = sh1.getSeatHoldId();
		
		String reservedSeats = server.reserveSeats(sh1ID, "e2@email.com");
		
		Assert.assertEquals(null, reservedSeats);
	}
	
	@Test public void shouldGenerateUniqueConfirmationCode() {
		String code1 = server.generateConfirmationCode();
		String code2 = server.generateConfirmationCode();
		String code3 = server.generateConfirmationCode();
		
		Assert.assertTrue(!code1.equals(code2));
		Assert.assertTrue(!code1.equals(code3));
		Assert.assertTrue(!code2.equals(code3));
	}
}
