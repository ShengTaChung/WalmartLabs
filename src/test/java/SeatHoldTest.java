import org.junit.Assert;
import org.junit.Test;

public class SeatHoldTest {
    @Test public void shouldHaveUniqueSeatHoldID(){
    	SeatHold sh1 = new SeatHold(1, "e1@email.com");
    	SeatHold sh2 = new SeatHold(1, "e2@email.com");
    	SeatHold sh3 = new SeatHold(1, "e1@email.com");
    	
    	Assert.assertNotEquals(sh1.getSeatHoldId(), sh2.getSeatHoldId());
    	Assert.assertNotEquals(sh1.getSeatHoldId(), sh3.getSeatHoldId());
    	Assert.assertNotEquals(sh2.getSeatHoldId(), sh3.getSeatHoldId());
    }
    
    @Test public void shouldReturnCorrectCustomerEmail(){
    	SeatHold sh1 = new SeatHold(1, "e1@email.com");
    	SeatHold sh2 = new SeatHold(1, "e2@email.com");
    	SeatHold sh3 = new SeatHold(1, "e1@email.com");
    	
    	Assert.assertEquals("e1@email.com", sh1.getSeatHoldCustomerEmail());
    	Assert.assertEquals("e2@email.com", sh2.getSeatHoldCustomerEmail());
    	Assert.assertEquals("e1@email.com", sh3.getSeatHoldCustomerEmail());
    }
    
    @Test public void shouldAddSeatToSeatHold(){
    	SeatHold sh1 = new SeatHold(1, "e1@email.com");
    	SeatHold sh2 = new SeatHold(1, "e2@email.com");

    	sh1.addSeat(new Seat(1));
    	sh1.addSeat(new Seat(1));
    	sh2.addSeat(new Seat(3));
    	sh2.addSeat(new Seat(2));
    	sh2.addSeat(new Seat(2));
    	
    	Assert.assertEquals(2, sh1.getSeats().size());
    	Assert.assertEquals(3, sh2.getSeats().size());
    }
}
