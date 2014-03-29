// this class is primarily used for testing our various board methods. It is also for teting win
// rates. 
package player;
import java.util.*;
public class RandomMove extends Move {
    
    /**
    * Returns a random move if it is valid
    **/
    public static Move randomForTesting(Board board, int color) {
      int x;
      int y;
      Random rand = new Random();
      x = rand.nextInt(8);
      y = rand.nextInt(8);
      while (!board.isValidMove(color,x,y)) {
        x = rand.nextInt(8);
        y = rand.nextInt(8);
      }
      return new Move(x,y);
    }
}