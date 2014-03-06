

public class Chip {
	// declarations for the side. Declared final for security. 
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	// when checking networks, cant use same chip twice. This maintains rule
	public boolean isChecked;

	public Chip(int side, int x, int y) {
		this.side = side;
		this.x = x;
		this.y = y;
		isChecked = false;
	}

	public int getx() {
		return this.x;
	}

	public int gety() {
		return this.y;
	}

	public int getside() {
		return this.side;
	}
	
}
