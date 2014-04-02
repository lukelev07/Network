/* MachinePlayer.java */

package player;
import java.util.*;


/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {

  Board board = new Board();
  int color;
  int searchDepth;
  static int sdepth = 2;
  public static final int BLACK = 0;
  public static final int WHITE = 1;

  // Creates a machine player with the given color.  Color is either 0 (black)
  // or 1 (white).  (White has the first move.)
  public MachinePlayer(int color) {
    this(color, sdepth);
  }

  // Creates a machine player with the given color and search depth.  Color is
  // either 0 (black) or 1 (white).  (White has the first move.)
  public MachinePlayer(int color, int searchDepth) {
    this.color = color;
    this.searchDepth = searchDepth;
  }

  // Returns a new move by "this" player.  Internally records the move (updates
  // the internal game board) as a move by "this" player.
  public Move chooseMove() {
    // Move temp = RandomMove.randomForTesting(board, color);
    // board.execMove(temp, color);

    // System.out.println(temp);
    // return temp;
    Move temp = abPrune(color, Integer.MIN_VALUE, Integer.MAX_VALUE, 0).move;
    board.execMove(temp, color);
    return temp;
  } 

  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
    if (board.isValidMove(1-color, m)) {
      // System.out.println("Step 1");
      board.execMove(m, 1-color);
      return true;
    }
    return false;
  }

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
    if (board.isValidMove(color, m)) {
      board.execMove(m, color);
      return true;
    }
    return false;
  }

  /**
  * abPrune implements game tree search from the Lecture 17 notes from CS61B.
  * See more description in GRADER.
  * @param side is the current side being searched. 
  * @param alpha is a trait of alpha-beta pruning. See lecture notes for details 
  * @param beta is a trait of alpha-beta pruning. See lecture notes for details 
  * @param depth maintains search depth parameter. Once zero, evaluate() is called 
  **/
  protected BestMove abPrune(int side, int alpha, int beta, int depth) {
    BestMove myBest = new BestMove();
    BestMove reply;
    Move[] valMoves = board.validMoves(side);
    //automatically place in first goal first     
    if (board.getChips().cardinality() < 2) {
      if (color == BLACK) {
        myBest.move = new Move(3, 0);
        return myBest;
      } else if (color == WHITE) {
        myBest.move = new Move(0, 3);
        return myBest;
      }
    }
    //automatically place in last goal second 
    if (board.getChips().cardinality() < 4) {
      if (color == BLACK) {
        myBest.move = new Move(3, 7);
        return myBest;
      } else if (color == WHITE) {
        myBest.move = new Move(7, 3);
        return myBest;
      } 
    }
    if (board.getChips().cardinality() < 6) {
      for (int i = 3; i < 5; i++) {
        for (int j = 3; j < 5; j++) {
          Move temp2 = new Move(i, j);
          if (board.isValidMove(side, i, j)) {
            myBest.move = temp2;
            return myBest;
          }
        }
      }
    }
    if (depth >= searchDepth || board.hasNetwork(color) || board.hasNetwork(1-color)) { //current grid full "or" case 
      myBest.score = board.evaluate(color, depth);
      return myBest;
    }

    if (side == color) {
      myBest.score = alpha; 
    } 
    else {
      myBest.score = beta; 
    }
    while (myBest.move == null) {
      myBest.move = valMoves[((int) (Math.random()*valMoves.length))];
    }
    for (int i = 0; i < valMoves.length; i++) {
      Move move = valMoves[i];
      if (move == null) {
        break;
      }
      board.execMove(move, side); //make exec move work for step
      reply = abPrune(oppColor(side), alpha, beta, depth + 1);
      board.undoMove(move, side);
      if ((side == color) && (reply.getScore() > myBest.getScore())) {
        myBest.move = move;
        myBest.score = reply.getScore();
        alpha = reply.getScore();
      } 
      else if ((side == 1-color) && (reply.getScore() < myBest.getScore())) {
        myBest.move = move; 
        myBest.score = reply.getScore(); 
        beta = reply.getScore();
      }
      if(alpha >= beta) { 
        return myBest;
      }
    }
    return myBest;
  } 
  /**
  * method that changes the color given a color 
  **/
  public int oppColor(int color) {
    if (color == 0) {
      return 1;
    }
    else {
      return 0;
    }
  }


}
