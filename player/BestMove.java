    // this is simply a data storage clas used to hold the fields for our chooseMove() method. 
// It has a return type of BestMove.
package player;
public class BestMove extends Move {

    int score;
    Move move;

    public int getScore() {
        return score;
    }

    // no new fields to declare. Inherits from Move.

}