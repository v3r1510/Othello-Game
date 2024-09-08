package A2Othello;

public class Player {
    private String name;
    private char color;
    public Player(String name, char color){
        this.name=name;
        this.color=color;
    }
    public Player() {
        color = Position.EMPTY;
        name = "Player: ";
    }
    public String getName(){
        return name;
    }
    public char getColor() {
        return color;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setColor(char color) {
        this.color = color;
    }
}
