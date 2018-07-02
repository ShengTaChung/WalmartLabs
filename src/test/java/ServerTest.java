import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class ServerTest {
	private VenueOrganizer organizer;
	MyVenue venue;
	int expireHoldTime;
	int seatsPerLevel;
	
    @Before  
    public void before() {  
    	expireHoldTime = 300;
    	seatsPerLevel = 2;
    	organizer = new VenueOrganizer(expireHoldTime, 4);
    	venue = new MyVenue(2, 4, 4, organizer, seatsPerLevel);
    }  
	
    @Test public void shouldReturnCorrectNumberOfSeatsAvailable(){
    	Server server = new Server(venue, expireHoldTime);
    	
    	Assert.assertEquals(8, server.numSeatsAvailable());
    }
    
    
    @Test public void shouldReturnCorrectSeatHold(){
    	Server server = new Server(venue, expireHoldTime);
    	
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
		Server server = new Server(venue, expireHoldTime);
		SeatHold sh1 = server.findAndHoldSeats(8, "e1@email.com");
		SeatHold sh2 = server.findAndHoldSeats(1, "e2@email.com");
		
		Assert.assertEquals(null, sh2);
	}
	
	@Test public void shouldReserveCorrectSeats() {
		Server server = new Server(venue, expireHoldTime);
		SeatHold sh1 = server.findAndHoldSeats(2, "e1@email.com");
		int sh1ID = sh1.getSeatHoldId();
		server.reserveSeats(sh1ID, sh1.getSeatHoldCustomerEmail());
		
		Assert.assertEquals(null, organizer.getSeatHold(sh1ID));
		Assert.assertEquals(6, organizer.getNumberOfAvailableSeats());
		
	}
}
