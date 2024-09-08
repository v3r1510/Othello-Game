package A2Othello;

public class PlayablePosition extends Position{
    public PlayablePosition(char piece){
        super(piece);
    }
    public PlayablePosition(){
        super();
    }
    @Override
    public boolean canPlay(){
        return getPiece()==EMPTY;
    }
     /*The initial implementation in the Position class
     should return false since it represents an unplayable position.
     The return value should be true for empty playable positions in
     the PlayablePosition subclass.*/
}
