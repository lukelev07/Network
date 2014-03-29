// this class is primarily used for testing our various board methods. It is also for teting win
// rates. 

public RandomMove extends Move {
    
    /**
    * Returns a random move if it is valid
    **/
    public Move randomForTesting() {
      int x;
      int y;
      RandomMove rand = new RandomMove();
      x = rand.nextInt(8);
      y = rand.nextInt(8);
      while (!this.board.isValidMove(this.color,x,y)) {
        x = rand.nextInt(8);
        y = rand.nextInt(8);
      }
      return new Move(x,y);
    }
}