import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public class SeatTest {	
	private Seat seat1, seat2, seat3;
    @Before  
    public void before() {  
    	seat1 = new Seat(1);
    	seat2 = new Seat(2);
    	seat3 = new Seat(3);
    }  
    @After 
    public void after() {
    	Seat.resetNumSeats();
    }
    
    
    @Test public void shouldReturnCorrectNumberOfSeats(){ 
    	Assert.assertEquals(3, Seat.getNumberOfSeats());
    	Seat seat4 = new Seat(1);
    	Seat seat5 = new Seat(1);
    	Assert.assertEquals(5, Seat.getNumberOfSeats());
    }
    
    @Test public void shouldHaveUniqueSeatID(){
    	Assert.assertNotEquals(seat1.getSeatID(), seat2.getSeatID());
    	Assert.assertNotEquals(seat1.getSeatID(), seat3.getSeatID());
    	Assert.assertNotEquals(seat2.getSeatID(), seat3.getSeatID());
    }
    
    @Test public void shouldReturnCorrectSeatLevel(){  
    	Seat seat4 = new Seat(1);
    	
    	Assert.assertEquals(1, seat1.getSeatLevel());
    	Assert.assertEquals(2, seat2.getSeatLevel());
    	Assert.assertEquals(3, seat3.getSeatLevel());
    	Assert.assertEquals(1, seat4.getSeatLevel());
    }
    
    @Test public void shouldResetNumberOfSeatsToZero(){  
    	Seat.resetNumSeats();
    	Assert.assertEquals(0, Seat.getNumberOfSeats());
    }    
}
