

public class Chip implements Comparable {
	// declarations for the side. Declared final for security. 
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	protected int color;
	protected int x;
	protected int y;
	// when checking networks, cant use same chip twice. This maintains rule
	public boolean isChecked;
	protected Set edges;
	protected Board board;


	/**
	* Chip constructor makes a Chip object
	* @param color represents white or black
	* @param x represents x coord of the chip being placed
	* @param y represents y coord of the chip being placed
	**/
	public Chip(int color, int x, int y, Board board) {
		this.color = color;
		this.x = x;
		this.y = y;
		isChecked = false;
		this.edges = new Set();
		this.board = board;
	}



	/**
	* getX() returns the x coordinate of the Chip
	**/
	public int getX() {
		return x;
	}

	/**
	* getY() returns the y coordinate of the Chip
	**/
	public int getY() {
		return y;
	}


	/**
	* getColor() returns the color of the Chip
	**/
	public int getColor() {
		return color;
	}


	/**
	* called on a chip, and updates the edges.
	* it then recursively calls update on any edges added to this chip
	**/
	public void updateEdges() {
		Chip temp;
		for (int x = -1; x<2; x++) {
			for (int y = -1; y<2; y++) {
				temp = findChipInDirection(x,y);
				if (temp != null && temp.getColor() == this.getColor() && !(this.getEdges().contains(temp))) {
					this.addEdge(temp);
					temp.updateEdges();
				}
			}
		}
	}
	/**
	* finds the closest chip in a given direction
	* returns null if there is none
	**/
	public Chip findChipInDirection(int x, int y) {
		int x_curr = this.getX();
		int y_curr = this.getY();
		while (true) {
			try {
				x_curr += x;
				y_curr += y;
				Chip temp = this.getBoard().getChip(x_curr,y_curr);
				if (temp != null) {
					if (temp.getColor() == this.getColor()) {
						return temp;
					}
					return null;
				}
			}
			catch (ArrayIndexOutOfBoundsException e1) {
				return null;
			}
		}

	}

	/**
	* returns board
	**/
	public Board getBoard() {
		return board;
	}


	/**
	* returns edges
	**/
	public Set getEdges() {
		return edges;
	}


	/**
	* adds one edge to a chips set
	**/
	public void addEdge(Chip chip) {
		this.edges.insert(chip);
	}



	/**
	* method that implements Comparable
	* returns +1, -1 or 0
	**/
	public int compareTo(Object other) {
		Chip others = (Chip) other;
		if (this.getY() < others.getY()) {
			return -1; 
		}
		if (this.getY() > others.getY()) {
			return 1;
		}
		if (this.getX() < others.getX()) {
			return -1;
		}
		if (this.getX() > others.getX()) {
			return 1;
		}
		return 0;
	}


	/**
	* toString() returns the string representation of the Chip
	**/
	public String toString() {
		return "{" + color +" at ("+x+", "+y+")}";
	}

	
}
