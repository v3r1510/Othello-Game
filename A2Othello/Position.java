package A2Othello;

public class Position {
    private char piece;
    public static final char UNPLAYABLE='*';
    public static final char EMPTY=' ';
    public static final char BLACK='B';
    public static final char WHITE='W';
    protected int x, y;
    public Position(){
        piece=EMPTY;
    }
    public Position(char piece){
        this.piece=piece;
    }
    public char getPiece(){
        return piece;
    }
    public void setPiece(char piece){
        this.piece=piece;
    }

    public static int[] coordinates(String coor) {
        int[] xy = new int[2];
        xy[0] = Character.getNumericValue(coor.charAt(1)) - 1;
        xy[1] = (int) Character.toLowerCase(coor.charAt(0)) - 97;
        return xy;
    }

    public boolean canPlay(){
        return false;
    }

}
