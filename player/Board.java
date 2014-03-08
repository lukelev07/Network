

public class Board {
	/** 
	 *  declare fields here
	 *
	 *
	 *
	 **/


	protected Chip[][] board;
	
	/**
	* zero parameter constructor that initializes an 8x8 2d array to hold chips
	* The board is initially empty
	**/
	public Board() {
		board = new Chip[8][8];
	}

	private Chip newChip(int side, int x, int y) {
		return new Chip(side, x, y);
	}

	/**
	* getChip() returns the chip at an index x,y if a chip exists. 
	* if the chip does not exist and if the array is out of bounds, it returns null
	**/
	public Chip getChip(int x, int y) throws ArrayIndexOutOfBoundsException {
			return board[x][y];
	}


	public boolean placeChip(int color, int x, int y) throws ArrayIndexOutOfBoundsException {
			if (this.getChip(x,y) == null) {
				board[x][y] = newChip(color, x, y);
			}
	}


	/**
	 *  numNeighbors() determines if the given position has a safe amount of neighbors
	 *  to place a new chip in. The README states no chip may have more than one adjacent 
	 *  similarly colored chip. y
	 *
	 *  @param color is the color to be checked with
	 *  @param x is the x coordinate of the spot we are checking
	 *  @param y is the y coordinate of the spot we are checking
	 *  @return the number of same-colored chips around the given coordinate.  
	 **/
	public int numNeighbors(int color, int x, int y) {
		Chip[] temp = getNeighbors(x, y);
		int sameNeighbors = 0;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null && temp[i].getColor() == color) {
				sameNeighbors++;
			} 
		}
		return sameNeighbors;
	}

	public Chip[] getNeighbors(int x, int y) {
		//implementation here 
	}

}