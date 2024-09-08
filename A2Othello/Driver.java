package A2Othello;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("●○●○ OTHELLO GAME MENU ○●○●");
        System.out.println("1. Load a Game");
        System.out.println("2. Start a New Game");
        System.out.println("3. Quit");
        int menu = sc.nextInt();
        sc.nextLine();
        do {
            switch (menu) {
                case 1: {
                    Board.load();
                    break;
                }
                case 2: {
                    System.out.println("Starting a New Game!");
                    System.out.println("Player 1: ");
                    String first = sc.nextLine();
                    System.out.println("Player 2: ");
                    String second = sc.nextLine();
                    Game game = new Game(new Player(first, Position.BLACK), new Player(second, Position.WHITE));

                    game.start();
                    break;
                }
                case 3: {
                    System.out.println("Quitting Game :(");
                    System.exit(0);
                }
                break;
                default: {
                    System.out.println("Invalid input. Please select an option between 1, 2 and 3.");
                    break;
                }
            }
            menu = sc.nextInt();
            sc.nextLine();
        } while (!(menu == 1) && !(menu == 2) && !(menu == 3));
    }
}