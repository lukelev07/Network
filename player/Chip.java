

public class Chip {
	// declarations for the side. Declared final for security. 
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	protected int side;
	protected int x;
	protected int y;
	// when checking networks, cant use same chip twice. This maintains rule
	public boolean isChecked;
	protected Set edges;

	public Chip(int side, int x, int y) {
		this.side = side;
		this.x = x;
		this.y = y;
		isChecked = false;
		this.edges = new Set();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getColor() {
		return side;
	}
	
}
