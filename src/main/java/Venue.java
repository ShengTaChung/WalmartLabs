
public abstract class Venue {
	private int numRows;
	private int numCols;
	private VenueOrganizer organizer;
	
	public Venue(int nRows, int nCols, VenueOrganizer organizer){
		this.numRows = nRows;
		this.numCols = nCols;
		this.organizer = organizer;
	}
	
	public int getNumberOfVenueRows() { return this.numRows; } 
	public int getNumberOfVenueCols() { return this.numCols; } 
	public VenueOrganizer getMyOrganizer() { return this.organizer; }
	
	public int addOneRow() {
		this.numRows = this.numRows + 1;
		return this.numRows;
	}
	
	public int addOneCol() {
		this.numCols = this.numCols + 1;
		return this.numCols;
	}
	
	public abstract void createSeatsForVenue();
}
