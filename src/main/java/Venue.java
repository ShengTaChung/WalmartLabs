//A template for venue
public abstract class Venue {
	private int numRows;
	private int numCols;
	private VenueOrganizer organizer;
	
	public Venue(int nRows, int nCols, VenueOrganizer organizer){
		this.numRows = nRows;
		this.numCols = nCols;
		this.organizer = organizer;
	}
	
	/* get number of rows in venue */
	public int getNumberOfVenueRows() { return this.numRows; } 
	
	/* get number of columns in venue */
	public int getNumberOfVenueCols() { return this.numCols; } 
	
	/* get the organizer */
	public VenueOrganizer getMyOrganizer() { return this.organizer; }
	
	/* add one row to venue */
	public int addOneRow() {
		this.numRows = this.numRows + 1;
		return this.numRows;
	}
	
	/* add one column to venue */
	public int addOneCol() {
		this.numCols = this.numCols + 1;
		return this.numCols;
	}
	
	/* fill the venue with seats */
	public abstract void createSeatsForVenue();
}
