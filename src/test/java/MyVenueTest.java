import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyVenueTest {
	
    @Test public void shouldReturnCorrectRowsAndCols(){
    	MyVenue venue = new MyVenue(3, 2, 3, new VenueOrganizer(300, 3), 2);
    	
    	Assert.assertEquals(3, venue.getNumberOfVenueRows());
    	Assert.assertEquals(2, venue.getNumberOfVenueCols());
    }
    
    @Test public void shouldAddRowsAndColsToVenue(){
    	MyVenue venue = new MyVenue(3, 2, 3, new VenueOrganizer(300, 3), 2);
    	
    	venue.addOneRow();
    	venue.addOneCol();
    	
    	Assert.assertEquals(4, venue.getNumberOfVenueRows());
    	Assert.assertEquals(3, venue.getNumberOfVenueCols());
    }
    
    @Test public void shouldSetSeatsPerLevel(){
    	int seatsPerLevel = 3;
    	MyVenue venue = new MyVenue(3, 2, 2, new VenueOrganizer(300, 2), seatsPerLevel);
    	Assert.assertEquals(3, venue.getSeatsPerLevel());
    }
    
    @Test public void shouldNotSetSeatsPerLevelIfGreaterThanVenueSize(){
    	int seatsPerLevel = 3;
    	MyVenue venue = new MyVenue(3, 2, 3, new VenueOrganizer(300, 3), seatsPerLevel);
    	Assert.assertEquals(0, venue.getSeatsPerLevel());
    }
    
    @Test public void shouldNotAddSeatToLevelIfNoSpace() {
    	int seatsPerLevel = 1;
    	MyVenue venue = new MyVenue(3, 1, 3, new VenueOrganizer(300, 3), seatsPerLevel);

    	venue.addSeatToLevel(1, new Seat(1));
    	venue.addSeatToLevel(1, new Seat(1));
    	Assert.assertEquals(1, venue.getNumSeatsInLevel(1));
    	
    }
    
    @Test public void shouldAddSeatsToLevels(){
    	int seatsPerLevel = 2;
    	MyVenue venue = new MyVenue(3, 2, 3, new VenueOrganizer(300, 3), seatsPerLevel);
    	Seat seat1 = new Seat(1);
    	Seat seat2 = new Seat(2);
    	Seat seat3 = new Seat(2);
    	Seat seat4 = new Seat(3);
    	
    	
    	venue.addSeatToLevel(2, seat2);
    	Assert.assertEquals(0, venue.getNumSeatsInLevel(1));
    	Assert.assertEquals(1, venue.getNumSeatsInLevel(2));
    	Assert.assertEquals(0, venue.getNumSeatsInLevel(3));
   
    	venue.addSeatToLevel(1, seat1);
    	
       	Assert.assertEquals(1, venue.getNumSeatsInLevel(1));
    	Assert.assertEquals(1, venue.getNumSeatsInLevel(2));
    	Assert.assertEquals(0, venue.getNumSeatsInLevel(3));
    	
    	venue.addSeatToLevel(2, seat3);
    	venue.addSeatToLevel(3, seat4);
    	
       	Assert.assertEquals(1, venue.getNumSeatsInLevel(1));
    	Assert.assertEquals(2, venue.getNumSeatsInLevel(2));
    	Assert.assertEquals(1, venue.getNumSeatsInLevel(3));
    }
    
    @Test public void shouldReturnFalseIfAddToInvalidLevel(){
    	int seatsPerLevel = 6;
    	MyVenue venue = new MyVenue(3, 2, 1, new VenueOrganizer(300, 1), seatsPerLevel);
    	Seat seat1 = new Seat(1);
    	Seat seat2 = new Seat(1);
    	Seat seat3 = new Seat(2);
    	
    	Assert.assertTrue(venue.addSeatToLevel(1, seat1));
    	Assert.assertTrue(venue.addSeatToLevel(1, seat2));
    	Assert.assertFalse(venue.addSeatToLevel(2, seat3));

    }
    
    @Test public void shouldReturnCorrectOrganizer(){
    	VenueOrganizer organizer1 = new VenueOrganizer(20, 1);
    	VenueOrganizer organizer2 = new VenueOrganizer(20, 1);
    	MyVenue venue = new MyVenue(3, 2, 1, organizer1, 2);
    	
    	Assert.assertSame(organizer1, venue.getMyOrganizer());
    	Assert.assertNotSame(organizer2, venue.getMyOrganizer());
    }
    
    @Test public void shouldCreateSeatsForVenue1() {
    	int seatsPerLevel = 3;
    	MyVenue venue = new MyVenue(3, 2, 2, new VenueOrganizer(300, 2), seatsPerLevel);
    	
    	venue.createSeatsForVenue();
    	Assert.assertEquals(3, venue.getNumSeatsInLevel(1));
    	Assert.assertEquals(3, venue.getNumSeatsInLevel(2));
    }
    
    @Test public void shouldNotCreateSeatsForVenue() {
    	int seatsPerLevel = 4;
    	MyVenue venue = new MyVenue(3, 2, 2, new VenueOrganizer(300, 2), seatsPerLevel);
    	
    	venue.createSeatsForVenue();
    	Assert.assertEquals(0, venue.getNumSeatsInLevel(1));
    	Assert.assertEquals(0, venue.getNumSeatsInLevel(2));
    }
}
