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

		for (int i = 0; i < temp.board.size; i ++) {
			for (int j = 0; j < temp.board.size; j ++) {
				if (temp.board.getChip(i,j) != null) {
					System.out.println(temp.board.getChip(i,j));
					System.out.println(temp.board.getChip(i,j).edges);
				}
			}
		}
		System.out.println("\n///////////////////////////////////////////\n");



		Board test2 = new Board();
		test2.placeChip(0,3,1);
		test2.placeChip(0,3,6);
		test2.placeChip(0,3,4);
		System.out.println(test2.getChip(3,1).edges);
		test2.placeChip(0,4,0);
		System.out.println(test2.getChip(4,0).isInStart());
		System.out.println(test2.getChip(4,0).isInGoal());
		test2.placeChip(0,5,7);
		System.out.println(test2.getChip(5,7).isInStart());
		System.out.println(test2.getChip(5,7).isInGoal());
		System.out.println("\n///////////////////////////////////////////\n");
		Board test1 = new Board();
		boolean temp1 = test1.placeChip(1,6,2);
		test1.placeChip(1,0,6);
		test1.placeChip(1,1,6);
		test1.placeChip(1,3,4);
		test1.placeChip(1,4,4);
		test1.placeChip(1,7,2);
		test1.placeChip(0,2,5);




		boolean check = test1.hasNetwork(1);

		System.out.println("-");
		System.out.println(check);














	}

}