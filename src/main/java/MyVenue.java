import java.util.ArrayList;

public class MyVenue extends Venue{
	
	private int numOfSeatingLevels;
	private int seatsPerLevel;
	private ArrayList<ArrayList<Seat>> levelList;
	
	
	public MyVenue(int nRows, int nCols, int numSeatingLevels, VenueOrganizer organizer, int seatsPerLevel){
		super(nRows, nCols, organizer);

		this.numOfSeatingLevels = numSeatingLevels;
		this.levelList = new ArrayList<ArrayList<Seat>>(numSeatingLevels);
		this.setSeatsPerLevel(seatsPerLevel);
	}
	
	/* Set max number of seats per level.
	 * return true if success else false
	*/
	public boolean setSeatsPerLevel(int seatsPerLevel) {
		int totalSeats = this.getNumberOfVenueRows() * this.getNumberOfVenueCols();
		if(totalSeats < seatsPerLevel * this.numOfSeatingLevels) { return false; }
		
		this.seatsPerLevel = seatsPerLevel;
		return true;
	}
	
	/* Get max number of seats per level */
	public int getSeatsPerLevel() { return this.seatsPerLevel; }
	
	/* Add a seat to a level
	 * return true if success else false
	 */
	public boolean addSeatToLevel(int level, Seat seat) {
		if(level > this.numOfSeatingLevels) { return false; }
		
		for(int i = levelList.size(); i < level; i++) {
			this.levelList.add(i, new ArrayList<Seat>());
		}
		
		if(this.levelList.get(level-1).size() >= this.seatsPerLevel) { return false; }
		
		this.levelList.get(level-1).add(seat);
		this.getMyOrganizer().addAvailableSeat(level, seat);
		
		return true;
	}
	
	/* get number of seats created in a level */
	public int getNumSeatsInLevel(int level) {
		if(this.levelList.size() < level) return 0;
		
		return this.levelList.get(level-1).size();
	}
	
	/* fill the venue with seats */
	public void createSeatsForVenue() {
		int totalSeats = this.getNumberOfVenueCols() * this.getNumberOfVenueRows();
		for(int i = 0; i < this.numOfSeatingLevels; i++) {
			for(int j = 0; j < this.seatsPerLevel; j++) {
				this.addSeatToLevel(i+1, new Seat(i+1));
			}
		}
	}
	
}
