
package player;
import java.util.Arrays;
import list.*;
// import java.Math;
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
	protected static final int ADD = 1;
	protected static final int STEP = 2;



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
	* returns the set of chips on the board. 
	**/
	public Set getChips() {
		return chips;
	}

	/**
	 * private method that constructs and returns a new Chip
	 * @param color is either one or zero, depending on white or black
	 * @param x is the x coordinate of the chip being created
	 * @param y is the y coordinate of the chip being created
	 **/
	private Chip newChip(int color, int x, int y) {
		return new Chip(color, x, y, this);
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
	 * isValidMove() returns a boolean that indicates the legality of placing a chip of int color in x,y
	 * will raise an ArrayIndexOutOfBoundsException if the coordinates are out of the bounds of the array
	 * @param color is either one or zero, depending on white or black
	 * @param x is the x coordinate of the chip being created
	 * @param y is the y coordinate of the chip being created
	 **/
	protected boolean isValidMove(int color, int x, int y) {
		try {
			if (this.getChip(x,y) != null) {
				return false;
			}
			if (!(color == Chip.BLACK && x > 0 && x < this.size-1) && !(color == Chip.WHITE && y > 0 && y < this.size-1)) {
				return false;
			}
			if (this.numNeighbors(color,x,y) > 1) {
				return false;
			}

			if (this.numNeighbors(color,x,y) == 0) {
				return true;
			}
			Chip[] neighbors = this.getNeighbors(x,y);

			for (int i = 0; i < neighbors.length; i++) {
				if (neighbors[i] != null && neighbors[i].getColor() == color) {
					return this.numNeighbors(neighbors[i].getColor(), neighbors[i].getX(), neighbors[i].getY()) == 0;
				}
			}


			return false;
		}
		catch (ArrayIndexOutOfBoundsException e1) {
			return false;
		}
	}

	protected boolean isValidMove(int color, int x1, int y1, int x2, int y2) {
		if (x1 == x2 && y1 == y2) {
			return false;
		}
		if (this.getChip(x2,y2) == null || this.getChip(x2,y2).getColor() != color) {
			return false;
		}
		if (this.getChip(x1,y1) != null) {
			return false;
		}
		Chip temp = this.getChip(x2,y2);
		this.removeChip(x2,y2);
		if (this.isValidMove(color, x1, y1)) {
			this.placeChip(color, x2,y2);
			return true;
		}
		else {
			this.placeChip(color, x2,y2);
			// System.out.println("yo you cant step this shit");
			return false;
		}

	}


	/**
	 * isValidMove() returns a boolean that indicates the legality of placing a chip of int color in x,y
	 * will raise an ArrayIndexOutOfBoundsException if the coordinates are out of the bounds of the array
	 * @param color is either one or zero, depending on white or black
	 * @param move is the move of the chip being created
	 **/
	protected boolean isValidMove(int color, Move m) {
		// System.out.println(m);
		if (m.moveKind == Move.ADD) {
			return isValidMove(color, m.x1, m.y1);}
			if (m.moveKind == Move.STEP) {
				return isValidMove(color, m.x1, m.y1, m.x2, m.y2);
			}
			return false;
		}

	/**
	 * validMoves() returns an array of all the possible moves a given color could make
	 * will iterate through the board and check for a valid move before appending that move 
	 * @param color is the color of moves to be checked 
	 * @return a Move array containing valid moves
	 **/
	protected Move[] validMoves(int color) {
		Move[] moves;
		int index = 0;

		if (moveType() == STEP) {
		    moves = new Move[400];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (this.getChip(i, j) != null && this.getChip(i, j).getColor() == color) {
						for (int k = 0;k < size; k++) {
							for (int l = 0; l < size; l++) {
								Move m = new Move(k, l, i, j);
								if (isValidMove(color, m)) {
									moves[index] = m;
									index++;
								}
							}
						}
					}
				}
			}
			return moves;	
		}
		else {
			moves = new Move[60];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (isValidMove(color, i, j)) {
						Move m = new Move(i, j);
						moves[index] = m;
						index++;
					}
				}
			}
		}		return moves; 
	}

	/**
	* placeChip() constructs a new chip of int color and then assigns it to the board at that coord
	* it will return true or false depending on if a Chip was placed
	* will throw an ArrayIndexOutOfBoundsException if the coords are out of the bounds
	 * @param color is either one or zero, depending on white or black chips are being placed 
	 * @param x is the x coordinate of the chip being created
	 * @param y is the y coordinate of the chip being created
	**/
	public boolean placeChip(int color, int x, int y){
		try {				
			if (this.getChip(x,y) == null && isValidMove(color, x, y)) {
				board[x][y] = newChip(color, x, y);
				chips.insert(this.getChip(x,y));
				this.getChip(x,y).updateEdges();
				for (int k = -1; k <2; k++) {
					for (int m = 0; m < 2; m++) {
						if (k != -1 || m != 0) {
							Chip temp1 = this.getChip(x,y).findAnyChipInDirection(m,k);
							Chip temp2 = this.getChip(x,y).findAnyChipInDirection(-m,-k);
							if (temp1 != null && temp1.getColor() != color) {
								if (temp2 != null && temp2.getColor() != color) {
									temp1.edges.remove(temp2);
									temp2.edges.remove(temp1);
								}
							}
						}
					}
				}
				return true;
			}
			return false;
		} catch (ArrayIndexOutOfBoundsException e1) {
			return false;
		}
	}

	/**
	* removeChip() removes the Chip located at x,y
	* will return true if a chip is removed, else returns false
	* will throw an ArrayIndexOutOfBoundsException if the coords are out of the bounds
	 * @param x is the x coordinate of the chip being removed
	 * @param y is the y coordinate of the chip being removed
	**/
	public boolean removeChip(int x, int y) {
		try {
			Chip removed = this.getChip(x,y);
			if (removed != null) {
				this.chips.remove(removed);
				board[x][y] = null;
				Chip temp;
				for (int x_dir = -1; x_dir<2; x_dir++) {
					for (int y_dir = -1; y_dir<2; y_dir++) {
						if (x_dir != 0 || y_dir != 0) { 
							temp = removed.findChipInDirection(x_dir,y_dir);
							if (temp != null && temp.getColor() == removed.getColor()) {
								temp.getEdges().remove(removed);
								temp.updateEdges();
							}
						}
					}
				}
				return true;
			}
			return false;
		} catch (ArrayIndexOutOfBoundsException e1) {
			return false;
		}
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
	public boolean moveChip(int x1, int y1, int x2, int y2) {
		try {
			if (this.getChip(x1, y1) != null && this.getChip(x2, y2) == null && isValidMove(this.getChip(x1,y1).getColor(), x2, y2)) {
				int color = this.getChip(x1, y1).getColor();
				this.removeChip(x1, y1);
				this.placeChip(color, x2, y2);
				return true;
			}
			return false;
		} catch (ArrayIndexOutOfBoundsException e1) {
			return false;
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
					if (i != 0 || j != 0) {	
						neighbors[index] = this.getChip(x+i, y+j);
					}
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
	* returns either an ADD move or a STEP move, based upon the number of pieces on the board 
	**/
	public int moveType() {
		if (chips.cardinality() < 20) {
			return ADD;
		} 
		return STEP;
	}

	/**
	* execMove() takes in a move and executes it as a step or add move
	* @param m is the move being executed
	* @param color is an integer representing whose turn it is
	**/
	public void execMove(Move m, int color) {
		if (m.moveKind == 1) {
			this.placeChip(color, m.x1, m.y1);
		}
		if (m.moveKind == 2) {
			this.removeChip(m.x2,m.y2);
			this.placeChip(color, m.x1, m.y1);
		}
	}
	 /**
	 * undoMove() takes in a move and undoes it.
	 * used for testing possible moves 
	 * @param m is the move being undone
	 * @param color is an integer representing whose turn it is
	 **/
	 public void undoMove(Move m, int color) {

	 	if (m.moveKind == Move.ADD) {
	 		this.removeChip(m.x1, m.y1);
	 	}
	 	else {
	 		this.removeChip(m.x1, m.y1);
	 		this.placeChip(color, m.x2, m.y2);
	 	}
	 }

  /**
  * hasNetwork() is a method called by a board that returns true if a network exists
  * @param color is an integer that states which player is going
  * @param startval
  **/
  public boolean hasNetwork(int color) {
  	if (color == 1) {
  		for (int i = 1; i < this.size-1; i ++) {
  			Chip start = this.getChip(0,i);
  			Chip[] temp = new Chip[10];
  			temp[0] = start;
  			if (start != null && hasNetworkHelper(temp)) {
  				return true;
  			}
  		}
  	}
  	if (color == 0) {
  		for (int i = 1; i < this.size-1; i ++) {
  			Chip start = this.getChip(i,0);
  			Chip[] temp = new Chip[10];
  			temp[0] = start;
  			if (start != null && hasNetworkHelper(temp)) {
  				return true;
  			}
  		}
  	}
  	return false;
  }


  public boolean isSameDirection(Chip chip1, Chip chip2, Chip chip3, Chip chip4) {
  	int x1, x2, y1, y2;
  	x1 = chip1.getX()-chip2.getX();
  	x2 = chip3.getX()-chip4.getX();
  	y1 = chip1.getY()-chip2.getY();
  	y2 = chip3.getY()-chip4.getY();
  	int counter1 = 0;
  	int counter2 = 0;
  	if (x1 == 0 || y1 == 0) {
  		if (x1 == 0) {
  			counter1 = Math.abs(y1);
  		}
  		else if (y1 == 0) {
  			counter1 = Math.abs(x1);
  		}
  	}
  	if (x2 == 0 || y2 == 0) {
  		if (x2 == 0) {
  			counter2 = Math.abs(y2);
  		}
  		else if (y2 == 0) {
  			counter2 = Math.abs(x2);
  		}
  	}
  	int min1 = Math.min(Math.abs(x1), Math.abs(y1));
  	int min2 = Math.min(Math.abs(x2), Math.abs(y2));
  	if (min1 != 0) {
  		counter1 = min1;
  	}
  	if (min2 != 0) {
  		counter2 = min2;
  	}
  	x1 = x1/counter1;
  	y1 = y1/counter1;
  	x2 = x2/counter2;
  	y2 = y2/counter2;

  	return x1 == x2 && y1 == y2;
  }


  public boolean hasNetworkHelper(Chip[] sofar) {
  	try {  	int size = 0;
  		while (size < sofar.length && sofar[size] != null) {
  			size ++;
  		}
  		size --;
  		if (sofar[size].isInGoal() && size >= 5) {
  			return true;
  		}
  		if (sofar[size].isInGoal() && size < 5) {
  			return false;
  		}
  		else if (size >= 9) {
  			return false;
  		}
  		else {
  			List connections = sofar[size].edges.set;
  			ListNode curr = connections.front();
  			while (curr != null) {
  				boolean temp = false;
  				for (int i = 0; i < sofar.length; i ++) {
  					if (sofar[i] == (Chip) curr.item()) {
  						temp = true;
  					}
  				}
  				if (!temp && !((Chip) (curr.item())).isInStart()) {	
  					if (!(size > 0) || !this.isSameDirection(sofar[size-1], sofar[size], sofar[size], (Chip) curr.item())){
  						sofar[size+1] = (Chip) (curr.item());
  						if (hasNetworkHelper(sofar)) {
  							return true;
  						}
  						sofar[size+1] = null;}
  					}
  					curr = curr.next();
  				}
  			}
  			return false;}
  			catch (InvalidNodeException e) {
  				return false;
  			}
  		}

  		public int evaluate(int color, int depth) {
  			if (hasNetwork(color)) {
  				return Integer.MAX_VALUE - depth +1;
  			} else if (hasNetwork(1 - color)) {
  				return Integer.MIN_VALUE + depth -1; 
  			}

  			int[] pairs = sumConnections(color);
  		//sums connections of machineplayer, and subtracts other players.
  			int score = pairs[0] - pairs[1];

  		//score += 
  			return score; 
  		}

  		public int[] sumConnections(int color) {
  			int[] connections = new int[2];
  			int machineEdges = 0;
  			int otherEdges = 0;
  			ListNode curr = chips.set.front();

  			try {
  				while (curr.isValidNode()) {
  					if (((Chip)curr.item()).getColor() == color) {
  						Set edges = ((Chip)curr.item()).getEdges();
  						machineEdges += edges.cardinality();
  					} else if (((Chip)curr.item()).getColor() == 1 - color) {
  						Set edges = ((Chip)curr.item()).getEdges();
  						otherEdges += edges.cardinality();
  					}
  					curr = curr.next();
  				}
  			} catch (InvalidNodeException e5) {
	  		//end of set, catch exception 
  			}
  			connections[0] = machineEdges;
  			connections[1] = otherEdges;
  			return connections;
  		}
  	
	/**
	* returns a String representation of the board
	**/
	// public String toString() {
	// 	String temp = "";
	// 	for (int y = 0; y < 8; y++) {
	// 		for (int x = 0; x < 8; x++) {
	// 			temp += "|"+this.getChip(x,y);
	// 		}
	// 		temp += "|\n";
	// 	}
	// 	return temp;
	// }

	// public String toTestString() {
	// 	String temp = "";
	// 	for (int x = 0; x < 8; x++) {
	// 		for (int y = 0; y < 8; y++) {
	// 			temp += "|"+this.getChip(x,y);
	// 		}
	// 		temp += "|\\n";
	// 	}
	// 	return temp;
	// }
	public String toString(){
		return simpleToString();
		// String s = "=========================================\n";
	 //    s+= "Stringified version:\n";
	 //    s+= serializeToString();
	 //    s+= "\n";
	 //    s+= "Code: <color ([W]hite,[B]lack)>:<blackNetworks>:<blackPotential>:<whiteNetworks>:<whitePotential>\n";
	 //    s += "-----------------------------------------";
		// for (int y = 0; y < DIMENSION; y++){
		// 	s+= "\n|";
		// 	for (int x = 0; x < DIMENSION; x++){
	 //       		s += " "+get(x, y).toString()+" |";
		// 	}
		// 	s+="\n";
		// }
		// s += "-----------------------------------------";
		// return s;
	}

	public String simpleToString(){

		String s = "==================================\n";
		s+="   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |";
		for (int y = 0; y < size; y++){
			s+= "\n----------------------------------\n";
			s+= y+"_ |";
			for (int x = 0; x < size; x++){
				if (this.getChip(x,y)==null){
					s+="   |";
				}
				else if (this.getChip(x,y).getColor()==Chip.BLACK){
					s+=" B |";
				}
				else if (this.getChip(x,y).getColor()==Chip.WHITE){
					s+=" W |";
				}
				
				//s += " "+get(x,y).getPiece()+" |";
			}
		}
		s += "\n==================================";
		// s += "\n BLACK SQUARES: ";
		// for (Square a: blackSquares){
		// 	s += "\n"+a;
		// }
		// s += "\n WHITE SQUARES: ";
		// for (Square b: whiteSquares){
		// 	s += "\n"+b;
		// }

		return s;
	}
	

	public static String printString(Object[] obj) {
		String temp = "[";
		for (int i = 0; i < obj.length; i ++) {
			if (obj[i] != null) 
			{
				temp += obj[i].toString();}
				temp += ", ";
			}
			return temp+"]";
		}


		// public static void main(String[] args) {




		// //initiates a board with several pieces placed and then prints it
		// 	Board test1 = new Board();
		// 	int x = 1;
		// 	while (test1.placeChip(Chip.BLACK, x, 0)) {
		// 		x += 2;
		// 	}

		// 	System.out.println("TEST1: \n"+test1);
		// 	if (test1.toString().equals(data.BOARD_TEST1)) {
		// 		System.out.println("TEST1 PASSED HOORAY");
		// 	}
		// 	else {
		// 		System.out.println("TEST1 did not pass :(");
		// 	}


		// //TEST2
		// 	boolean bool = test1.placeChip(Chip.WHITE, 1, 0);
		// 	System.out.println("TEST2: bool should be false: ");
		// 	if (bool == false) {
		// 		System.out.println("TEST2 PASSED HOORAY");
		// 	}
		// 	else {
		// 		System.out.println("TEST2 did not pass :(");
		// 	}

		// 	System.out.println(""+test1.chips);
		// 	System.out.println("" + test1.getChip(3,0).getEdges());
		// 	test1.placeChip(Chip.BLACK, 3,2);
		// 	System.out.println("" + test1.getChip(3,2).getEdges());

		// 	test1.placeChip(Chip.BLACK, 3,6);
		// 	System.out.println("" + test1.getChip(3,6).getEdges());
		// 	test1.placeChip(Chip.BLACK, 2,0);
		// 	System.out.println(""+test1);



		// 	test1.removeChip(3,6);
		// 	System.out.println(""+test1);
		// 	System.out.println(""+test1.chips);
		// 	System.out.println("" + test1.getChip(3,2).getEdges());
		// 	test1.placeChip(Chip.BLACK, 3,3);
		// 	System.out.println("helllllooooooooo");
		// 	System.out.println(""+test1.getNeighbors(3,2));

		// 	Chip[] hello = {test1.newChip(Chip.BLACK, 4,5)};
		// 	System.out.println(printString(hello));

		// 	int[] hellll = {1,2,3,4};
		// 	System.out.println(hellll);

		// 	System.out.println("////////////////////////////////////////////////\n");

		// 	Board test2 = new Board();
		// 	int y = 1;
		// 	while (test2.placeChip(Chip.BLACK, y, 0)) {
		// 		y += 1;
		// 		System.out.println(y);
		// 	}
		// 	System.out.println(test2);
		// 	System.out.println(printString(test2.getNeighbors(1,1)));


		// }
	}