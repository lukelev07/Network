/* MachinePlayer.java */

package player;
import java.util.*;


/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {

  Board board;
  int color;
  int searchDepth;

  // Creates a machine player with the given color.  Color is either 0 (black)
  // or 1 (white).  (White has the first move.)
  public MachinePlayer(int color) {
    this(color, 2);
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
    Move temp = RandomMove.randomForTesting(board, color);
    board.execMove(temp, color);
    return temp;
  } 

  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
    return false;
  }

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
    return false;
  }

  // public BestMove abPrune(int side, int alpha, int beta) {
  //     Best myBest = new BestMove();
  //     Best reply;

  //     int[] valmoves = new int[] 


  //     if (current grid full or has a win) {
  //       return a BestMove with the grids score, no move;
  //     }
  //     if (side == color) {
  //       myBest.score = alpha; 
  //     } else {
  //       myBest.score = beta; 
  //     }


  //     for (each legal move m) {
  //       Move move = new Move(newx, newy);
  //       Board.execMove(move, color);

  //       reply = abPrune(oppColor(color), alpha, beta);
  //       Board.undoMove(move, color);
  //       if side((side == color) && (reply.getScore() >= myBest.getScore())) {
  //         myBest.move = m;
  //         myBest.score = reply.getScore();
  //         alpha = reply.getScore();
  //       } else if ((color == oppColor(color)) && (reply.getScore() <= myBest.getScore())) {
  //         myBest.move = m; 
  //         myBest.score = reply.getScore; 
  //         beta = reply.getScore;
  //       }
  //       if(alpha >= beta) { 
  //         return myBest;
  //       }
  //     }
  //     return myBest;
  //   }

  //   public int oppColor(color) {
  //     if (color == 0) {
  //       return 1;
  //     }
  //     else {
  //       return 0;
  //     }
  //   }


}
