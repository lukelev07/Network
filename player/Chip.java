

public class Chip {
	// declarations for the side. Declared final for security. 
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	protected int color;
	protected int x;
	protected int y;
	// when checking networks, cant use same chip twice. This maintains rule
	public boolean isChecked;
	protected Set edges;


	/**
	* Chip constructor makes a Chip object
	* @param color represents white or black
	* @param x represents x coord of the chip being placed
	* @param y represents y coord of the chip being placed
	**/
	public Chip(int color, int x, int y) {
		this.color = color;
		this.x = x;
		this.y = y;
		isChecked = false;
		this.edges = new Set();
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
	* toString() returns the string representation of the Chip
	**/
	public String toString() {
		return "{" + color +" at ("+x+", "+y+")}";
	}

	
}
