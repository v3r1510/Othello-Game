package A2Othello;
import java.util.Scanner;
public class Game {

    Scanner sc=new Scanner(System.in);
    private Board board;
    public Game(){
        board=new Board(new Player("first", Position.BLACK), new Player("second", Position.WHITE));
    }
    public Game(Player first, Player second){
        board=new Board(first, second);
    }


    //method to start the game by displaying the menu first:
    public void start(){

        System.out.println("Please choose the starting positions for this game: " +
                "\n1. Offset Starting Positions \n2. Standard Starting Positions");
        String initial=sc.nextLine();
        String offset="";
        if(initial.equals("1")){
            System.out.println("\n1. Offset top left \n2. Offset top right \n3. Offset bottom left \n4. Offset bottom right");
            offset = sc.nextLine();
            board.initializeBoard(initial+offset);

        }
        if (initial.equals("2")){
            board.initializeBoard(initial+offset);
        }
        board.play();
    }
}
