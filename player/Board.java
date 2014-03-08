

public class Board {
	/** 
	 *  declare fields here
	 *
	 *
	 *
	 **/

	protected Set chips;
	protected Chip[][] board;
	protected int size;


	/**
	 * this one parameter constructor initializes a board taking in one parameter for size
	 * also initiates a set that holds all the chips present on the board
	 * @param size is the dimensions of the Board. Assume that the Board is square
	 **/
	public Board(int size) {
		this.size = 8;
		board = new Chip[size][size];
		chips = new Set();
	}


	/**
	* zero parameter constructor that initializes an 8x8 2d array to hold chips
	* The board is initially empty
	**/
	public Board() {
		this(8);
	}



	/**
	 * private method that constructs and returns a new Chip
	 * @param color is either one or zero, depending on white or black
	 * @param x is the x coordinate of the chip being created
	 * @param y is the y coordinate of the chip being created
	 **/
	private Chip newChip(int color, int x, int y) {
		return new Chip(color, x, y);
	}


	/**
	 * isValidMove() returns a boolean that indicates the legality of placing a chip of int color in x,y
	 * will raise an ArrayIndexOutOfBoundsException if the coordinates are out of the bounds of the array
	 * @param color is either one or zero, depending on white or black
	 * @param x is the x coordinate of the chip being created
	 * @param y is the y coordinate of the chip being created
	 **/
	private boolean isValidMove(int color, int x, int y) {
		try {
			if (color == Chip.BLACK && x > 0 && x < this.size) {
				return true;
			}
			if (color == Chip.WHITE && y > 0 && y < this.size) {
				return true;
			}
			return false;
		}
		catch (ArrayIndexOutOfBoundsException e1) {
			return false;
		}
	}

	/**
	* getChip() returns the chip at an index x,y if a chip exists. 
	* if the chip does not exist it returns null
	* will raise an ArrayIndexOutOfBoundsException if the coordinates are out of the bounds of the array
	 * @param x is the x coordinate of the chip being retrieved
	 * @param y is the y coordinate of the chip being retrieved
	**/
	public Chip getChip(int x, int y) throws ArrayIndexOutOfBoundsException {
		return board[x][y];
	}



	/**
	* placeChip() constructs a new chip of int color and then assigns it to the board at that coord
	* it will return true or false depending on if a Chip was placed
	* will throw an ArrayIndexOutOfBoundsException if the coords are out of the bounds
	 * @param color is either one or zero, depending on white or black chips are being placed 
	 * @param x is the x coordinate of the chip being created
	 * @param y is the y coordinate of the chip being created
	**/
	public boolean placeChip(int color, int x, int y) throws ArrayIndexOutOfBoundsException {
		if (this.getChip(x,y) == null && isValidMove(this.getChip(x,y).getColor(), x, y)) {
			board[x][y] = newChip(color, x, y);
			return true;
		}
		return false;
	}



	/**
	* removeChip() removes the Chip located at x,y
	* will return true if a chip is removed, else returns false
	* will throw an ArrayIndexOutOfBoundsException if the coords are out of the bounds
	 * @param x is the x coordinate of the chip being removed
	 * @param y is the y coordinate of the chip being removed
	**/
	public boolean removeChip(int x, int y) throws ArrayIndexOutOfBoundsException {
		if (this.getChip(x,y) != null) {
			board[x][y] = null;
			return true;
		}
		return false;
	}


	/**
	* moveChip() removes the Chip located at x1,y1 and places the chip at x2,y2
	* will return true if a chip is moved, else returns false
	* will throw an ArrayIndexOutOfBoundsException if the coords are out of the bounds
	 * @param x1 is the x coordinate of the chip being removed
	 * @param y1 is the y coordinate of the chip being removed
	 * @param x2 is the x coordinate of the chip being placed
	 * @param y2 is the y coordinate of the chip being placed
	**/
	public boolean moveChip(int x1, int y1, int x2, int y2) throws ArrayIndexOutOfBoundsException {
		if (this.getChip(x1, y1) != null && this.getChip(x2, y2) == null && isValidMove(this.getChip(x1,y1).getColor(), x2, y2)) {
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



	/**
	* getNeighbors() returns a Chip array of length nine containg nulls and neighbors
	 *  @param x is the x coordinate of the spot we are checking
	 *  @param y is the y coordinate of the spot we are checking
	**/
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
		return neighbors;
	}


	/**
	* returns a String representation of the board
	**/
	public String toString() {
		String temp = "";
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				temp += "|"+this.getChip(x,y);
			}
			temp += "|\n";
		}
		return temp;
	}

	public static void main(String[] args) {
		//
	}

}