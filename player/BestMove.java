    // this is simply a data storage clas used to hold the fields for our chooseMove() method. 
// It has a return type of BestMove.
package player;
public class BestMove {

    int score;
    Move move;

    protected int getScore() {
        return score;
    }

    // no new fields to declare. Inherits from Move.
    public String toString() {
        return move.toString();
    }

}