// this class is primarily used for testing our various board methods. It is also for teting win
// rates. 
package player;
import java.util.*;
public class RandomMove extends Move {
    
  /**
  * Returns a random move if it is valid
  * @param board is the board being used by the machine player
  * @param color is the color indicating the side of the machine player
  **/
  public static Move randomForTesting(Board board, int color) {
    Random rand = new Random();
    if (board.moveType() == Board.ADD) {

    int x;
    int y;
    
    x = rand.nextInt(8);
    y = rand.nextInt(8);
    while (!board.isValidMove(color,x,y)) {
      x = rand.nextInt(8);
      y = rand.nextInt(8);
    }
    return new Move(x,y);}
    else {
      int x1, x2, y1, y2;
      x1 = rand.nextInt(8);
    y1 = rand.nextInt(8);
    x2 = rand.nextInt(8);
    y2 = rand.nextInt(8);
    while (!board.isValidMove(color,x1,y1,x2,y2)) {

        x1 = rand.nextInt(8);
        y1 = rand.nextInt(8);
        x2 = rand.nextInt(8);
        y2 = rand.nextInt(8);

      }
      return new Move(x1,y1,x2,y2);
    }
  }
}