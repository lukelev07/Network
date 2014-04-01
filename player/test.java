package player;

public class test {
	public static void main (String[] args) {
		MachinePlayer temp = new MachinePlayer(0);
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		temp.chooseMove();
		for (int i = 0; i < temp.board.size; i ++) {
			for (int j = 0; j < temp.board.size; j ++) {
				if (temp.board.getChip(i,j) != null) {
					System.out.println(temp.board.getChip(i,j));
					System.out.println(temp.board.getChip(i,j).edges);
				}
			}
		}
		System.out.println("\n///////////////////////////////////////////\n");


		Board test1 = new Board();
		boolean temp1 = test1.placeChip(1,0,3);
		test1.placeChip(1,2,3);
		test1.placeChip(1,4,3);
		test1.placeChip(1,2,5);
		test1.placeChip(1,4,1);
		test1.placeChip(1,7,4);
		System.out.println(test1.hasNetwork(1));














	}

}