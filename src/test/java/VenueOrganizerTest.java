import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class VenueOrganizerTest {
	@Test public void shouldAddSeatsToAvailableList(){
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		Seat seat1 = new Seat(1);
		Seat seat2 = new Seat(2);
		Seat seat3 = new Seat(2);
				
		organizer.addAvailableSeat(1, seat1);
		Assert.assertEquals(1, organizer.getNumberOfAvailableSeats());
		organizer.addAvailableSeat(2, seat2);
		organizer.addAvailableSeat(2, seat3);
		Assert.assertEquals(3, organizer.getNumberOfAvailableSeats());
	}
	
	@Test public void shouldHoldAndReturnBestSeat1(){
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		Seat seat1 = new Seat(1);
		Seat seat2 = new Seat(2);
		Seat seat3 = new Seat(2);
		
		organizer.addAvailableSeat(1, seat1);
		organizer.addAvailableSeat(2, seat2);
		organizer.addAvailableSeat(2, seat3);
		Assert.assertEquals(3, organizer.getNumberOfAvailableSeats());
		
		
		Seat bestSeat1 = organizer.findAndHoldSeat();
		Assert.assertEquals(1, bestSeat1.getSeatLevel());
		Assert.assertEquals(2, organizer.getNumberOfAvailableSeats());
		
		Seat bestSeat2 = organizer.findAndHoldSeat();
		Assert.assertEquals(2, bestSeat2.getSeatLevel());
		Assert.assertEquals(1, organizer.getNumberOfAvailableSeats());
		
		Seat bestSeat3 = organizer.findAndHoldSeat();
		Assert.assertEquals(2, bestSeat3.getSeatLevel());
		Assert.assertEquals(0, organizer.getNumberOfAvailableSeats());
	}
	
	@Test public void shouldHoldAndReturnBestSeat2(){
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		Seat seat1 = new Seat(3);
		Seat seat2 = new Seat(2);
		Seat seat3 = new Seat(1);
		
		organizer.addAvailableSeat(3, seat1);
		Seat bestSeat1 = organizer.findAndHoldSeat();
		Assert.assertEquals(3, bestSeat1.getSeatLevel());
		Assert.assertEquals(0, organizer.getNumberOfAvailableSeats());
		
		organizer.addAvailableSeat(2, seat2);
		organizer.addAvailableSeat(1, seat3);
		Seat bestSeat2 = organizer.findAndHoldSeat();
		Assert.assertEquals(1, bestSeat2.getSeatLevel());
		Assert.assertEquals(1, organizer.getNumberOfAvailableSeats());
		
		Seat bestSeat3 = organizer.findAndHoldSeat();
		Assert.assertEquals(2, bestSeat3.getSeatLevel());
		Assert.assertEquals(0, organizer.getNumberOfAvailableSeats());
		

	}
	
	@Test public void shouldReturnNullWhenNoAvailableSeat(){
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		Seat seat1 = new Seat(1);

		Seat bestSeatNull = organizer.findAndHoldSeat();
		Assert.assertEquals(null, bestSeatNull);
		Assert.assertEquals(0, organizer.getNumberOfAvailableSeats());
		
		organizer.addAvailableSeat(1, seat1);
		Seat bestSeat1 = organizer.findAndHoldSeat();
		Seat bestSeatNull2 = organizer.findAndHoldSeat();
		Assert.assertEquals(null, bestSeatNull2);
		Assert.assertEquals(0, organizer.getNumberOfAvailableSeats());
	}
	
	@Test public void shouldCreateSeatHoldWithBestSeats(){
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(2, new Seat(2));
		organizer.addAvailableSeat(3, new Seat(3));
		organizer.addAvailableSeat(3, new Seat(3));
		
		SeatHold sh1 = organizer.createSeatHold(2, "e1@email.com");
		Assert.assertEquals(3, organizer.getNumberOfAvailableSeats());
		Assert.assertEquals(2, sh1.getSeats().size());
		Assert.assertEquals(1, sh1.getSeats().get(0).getSeatLevel());
		Assert.assertEquals(1, sh1.getSeats().get(1).getSeatLevel());
		
		
		
		SeatHold sh2 = organizer.createSeatHold(3, "e2@email.com");
		Assert.assertEquals(0, organizer.getNumberOfAvailableSeats());
		Assert.assertEquals(3, sh2.getSeats().size());
		Assert.assertEquals(2, sh2.getSeats().get(0).getSeatLevel());
		Assert.assertEquals(3, sh2.getSeats().get(1).getSeatLevel());
		Assert.assertEquals(3, sh2.getSeats().get(2).getSeatLevel());
	}
	
	@Test public void shouldReturnNullWhenNotEnoughSeatsAvailable(){
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		SeatHold shNull = organizer.createSeatHold(1, "e1@email.com");
		Assert.assertEquals(null, shNull);
		
		organizer.addAvailableSeat(2, new Seat(2));
		SeatHold shNull2 = organizer.createSeatHold(2, "e1@email.com");
		Assert.assertEquals(null, shNull2);
		
		SeatHold sh1 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold shNull3 = organizer.createSeatHold(1, "e1@email.com");
		Assert.assertEquals(null, shNull3);
	}
	
	@Test public void shouldReturnCorrectSeatHold(){
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(2, new Seat(2));
		SeatHold sh1 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold sh2 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold sh3 = organizer.createSeatHold(1, "e1@email.com");
		int shID1 = sh1.getSeatHoldId();
		int shID2 = sh2.getSeatHoldId();
		int shID3 = sh3.getSeatHoldId();
		
		
		Assert.assertEquals(sh1, organizer.getSeatHold(shID1));
		Assert.assertNotEquals(sh1, organizer.getSeatHold(shID2));
		Assert.assertNotEquals(sh1, organizer.getSeatHold(shID3));
	}
	
	@Test public void shouldRemoveCorrectSeatHold(){
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(2, new Seat(2));
		SeatHold sh1 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold sh2 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold sh3 = organizer.createSeatHold(1, "e1@email.com");
		int shID1 = sh1.getSeatHoldId();
		int shID2 = sh2.getSeatHoldId();
		int shID3 = sh3.getSeatHoldId();
		
		Assert.assertEquals(sh1, organizer.getSeatHold(shID1));
		SeatHold removedSeatHold = organizer.removeSeatHold(shID1);
		Assert.assertEquals(sh1, removedSeatHold);
		Assert.assertEquals(null, organizer.getSeatHold(shID1));
		Assert.assertEquals(sh2, organizer.getSeatHold(shID2));
		Assert.assertEquals(sh3, organizer.getSeatHold(shID3));
	}
	
	@Test public void shouldReturnNullIfSeatHoldIdNotFound() {
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(2, new Seat(2));
		SeatHold sh1 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold sh2 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold sh3 = organizer.createSeatHold(1, "e1@email.com");
		
		SeatHold removedSeatHold = organizer.removeSeatHold(4);
		Assert.assertEquals(null, removedSeatHold);
	}
	
	@Test public void shouldReserveCorrectSeats() {
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(2, new Seat(2));
		
		SeatHold sh = organizer.createSeatHold(2, "e1@email.com");
		SeatHold reservedSH = organizer.reserveSeatsFromSeatHold(sh.getSeatHoldId(), "e1@email.com");
		ArrayList<Seat> reservedSeats = reservedSH.getSeats();
		

		Assert.assertEquals("e1@email.com", reservedSeats.get(0).getOwnerEmail());
		Assert.assertEquals("e1@email.com", reservedSeats.get(1).getOwnerEmail());
		
		Assert.assertEquals(null, organizer.getSeatHold(sh.getSeatHoldId()));
		Assert.assertEquals(1, organizer.getNumberOfAvailableSeats());
	}
	
	@Test public void shouldReturnNullIfInvalidEmail() {
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		organizer.addAvailableSeat(1, new Seat(1));
		
		SeatHold sh = organizer.createSeatHold(1, "e1@email.com");
		SeatHold reservedSH = organizer.reserveSeatsFromSeatHold(sh.getSeatHoldId(), "e2@email.com");
		Assert.assertEquals(null, reservedSH);
	}
	
	@Test public void shouldRemoveExpiredSeatHolds() throws InterruptedException {
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(2, new Seat(2));
		SeatHold sh1 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold sh2 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold sh3 = organizer.createSeatHold(1, "e1@email.com");
		int shID1 = sh1.getSeatHoldId();
		int shID2 = sh2.getSeatHoldId();
		int shID3 = sh3.getSeatHoldId();
		
		organizer.removeExpiredSeatHold();
		Assert.assertEquals(sh1, organizer.getSeatHold(sh1.getSeatHoldId()));
		Assert.assertEquals(sh2, organizer.getSeatHold(sh2.getSeatHoldId()));
		Assert.assertEquals(sh3, organizer.getSeatHold(sh3.getSeatHoldId()));
		
		
		long newDateCreatedInSecond = (new Date().getTime())/1000 - 300;
		sh1.setDateCreated(newDateCreatedInSecond);
		sh2.setDateCreated(newDateCreatedInSecond);
		sh3.setDateCreated(newDateCreatedInSecond);
		
		organizer.removeExpiredSeatHold();
		Assert.assertEquals(null, organizer.getSeatHold(sh1.getSeatHoldId()));
		Assert.assertEquals(null, organizer.getSeatHold(sh2.getSeatHoldId()));
		Assert.assertEquals(null, organizer.getSeatHold(sh3.getSeatHoldId()));
		
	}
	
	@Test public void shouldRemoveExpireAndAddBackToAvailable() {
		VenueOrganizer organizer = new VenueOrganizer(300, 3);
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(1, new Seat(1));
		organizer.addAvailableSeat(2, new Seat(2));
		SeatHold sh1 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold sh2 = organizer.createSeatHold(1, "e1@email.com");
		SeatHold sh3 = organizer.createSeatHold(1, "e1@email.com");
		int shID1 = sh1.getSeatHoldId();
		int shID2 = sh2.getSeatHoldId();
		int shID3 = sh3.getSeatHoldId();
		
		organizer.removeExpiredSeatHold();
		Assert.assertEquals(sh1, organizer.getSeatHold(sh1.getSeatHoldId()));
		Assert.assertEquals(sh2, organizer.getSeatHold(sh2.getSeatHoldId()));
		Assert.assertEquals(sh3, organizer.getSeatHold(sh3.getSeatHoldId()));
		
		
		long newDateCreatedInSecond = (new Date().getTime())/1000 - 300;
		sh1.setDateCreated(newDateCreatedInSecond);
		sh2.setDateCreated(newDateCreatedInSecond);
		sh3.setDateCreated(newDateCreatedInSecond);
		
		Assert.assertEquals(0, organizer.getNumberOfAvailableSeats());
		organizer.removeExpiredSeatHold();
		Assert.assertEquals(null, organizer.getSeatHold(sh1.getSeatHoldId()));
		Assert.assertEquals(null, organizer.getSeatHold(sh2.getSeatHoldId()));
		Assert.assertEquals(null, organizer.getSeatHold(sh3.getSeatHoldId()));
		Assert.assertEquals(3, organizer.getNumberOfAvailableSeats());
		
		Seat bestSeat1 = organizer.findAndHoldSeat();
		Assert.assertEquals(1, bestSeat1.getSeatLevel());
		Assert.assertEquals(2, organizer.getNumberOfAvailableSeats());
		
		Seat bestSeat2 = organizer.findAndHoldSeat();
		Assert.assertEquals(1, bestSeat2.getSeatLevel());
		Assert.assertEquals(1, organizer.getNumberOfAvailableSeats());
		
		Seat bestSeat3 = organizer.findAndHoldSeat();
		Assert.assertEquals(2, bestSeat3.getSeatLevel());
		Assert.assertEquals(0, organizer.getNumberOfAvailableSeats());
	}
}
