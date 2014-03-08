

public class Board {
	/** 
	 *  declare fields here
	 *
	 *
	 *
	 **/


	protected Chip[][] board;
	protected int size;
	/**
	* zero parameter constructor that initializes an 8x8 2d array to hold chips
	* The board is initially empty
	**/
	public Board(int size) {
		this.size = 8;
		board = new Chip[size][size];
	}
	public Board() {
		this(8);
	}

	private Chip newChip(int side, int x, int y) {
		return new Chip(side, x, y);
	}

	private static boolean isValidMove(int color, int x, int y) {
		try {
			if (color == Chip.BLACK && x > 0 && x < this.size) {
				return true;
			}
			if (color == Chip.WHTIE && y > 0 && y < this.size) {
				return true;
			}
		}
		catch (ArrayIndexOutOfBoundsException e1) {
			return false;
		}
	}

	/**
	* getChip() returns the chip at an index x,y if a chip exists. 
	* if the chip does not exist and if the array is out of bounds, it returns null
	**/
	public Chip getChip(int x, int y) throws ArrayIndexOutOfBoundsException {
		return board[x][y];
	}


	public boolean placeChip(int color, int x, int y) throws ArrayIndexOutOfBoundsException {
		if (this.getChip(x,y) == null && isValidMove(color, x, y)) {
			board[x][y] = newChip(color, x, y);
			return true;
		}
		return false;
	}

	public boolean removeChip(int x, int y) throws ArrayIndexOutOfBoundsException {
		if (this.getChip(x,y) != null && isValidMove(color, x, y)) {
			board[x][y] = null;
			return true;
		}
		return false;
	}

	public boolean moveChip(int x1, int y1, int x2, int y2) throws ArrayIndexOutOfBoundsException {
		if (this.getChip(x1, y1) != null && this.getChip(x2, y2) == null && isValidMove(color, x, y)) {
			int color = this.getChip(x1, y1).getColor();
			this.removeChip(x1, y1);
			this.placeChip(color, x2, y2);
			return true;
		}
		return false;
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
		Chip[] neighbors = new Chip[9];
		int index = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				try {
					neighbors[index] = this.getChip(x+i, y+j);
				}
				catch (ArrayIndexOutOfBoundsException e2) {
					neighbors[index] = null;
				}
				index ++;
			}
		}
	}

}