package A2Othello;

import java.io.*;
import java.util.Scanner;
public class Board {
    static Scanner sc=new Scanner(System.in);
    Scanner sc2=new Scanner(System.in);

    private String name;
    private Player first;
    private Player second;
    private Player current;
    private String save_file;
    private Position[][] boardPieces;

    //Board constructors:
    public Board(){
        first = new Player("first", Position.BLACK);
        second = new Player("second", Position.WHITE);
        current = first;
        boardPieces=new Position[8][8];
        for (int row=0; row<8; row++){
            for (int col=0; col<8; col++){
                boardPieces[row][col]= new PlayablePosition();
            }
        }

        initializeBoard("2");
    }
    public Board(Board board){
        this.setBoardPieces(board.boardPieces);
    }
    public Board(Player first, Player second){
        this.first= first;
        this.second=second;
        this.current=this.first;
        boardPieces=new Position[8][8];
        for (int row=0; row<8; row++){
            for (int col=0; col<8; col++){
                boardPieces[row][col]= new PlayablePosition();
            }
        }

    }
    //Board getters&setters:
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public Position[][] getBoardPieces(){

        Position[][] copy= new Position[8][8];

        for (int row=0; row<8; row++){
            for (int col=0; col<8; col++){
                copy[row][col]= new Position();
            }
        }

        for (int row=0; row<8; row++){
            for (int col=0; col<8; col++){
                copy[row][col]= boardPieces[row][col];
            }
        }
        return copy;
    }
    public void setBoardPieces(Position[][] boardPieces){
        for (int row=0; row<8; row++){
            for (int col=0; col<8; col++){
                this.boardPieces[row][col]=boardPieces[row][col];
            }
        }
    }
    public void setFirst(String first) {
        this.first = new Player(first, Position.BLACK);
    }
    public void setSecond(String second) {
        this.second = new Player(second, Position.BLACK);
    }
    //initializing the board:
    public void initializeBoard(String initial){
        for (int row=0; row<8; row++){
            for (int col=0; col<8; col++){
                boardPieces[row][col]= new PlayablePosition();
            }
        }
        //setting the unplayable positions:
        for (int row=2; row<6; row++){
            boardPieces[row][7]= new Position(Position.UNPLAYABLE);
        }
        //switch statement with options for initial starting positions
        switch(initial){
            case "11": {
                boardPieces[2][2].setPiece(Position.WHITE);
                boardPieces[2][3].setPiece(Position.BLACK);
                boardPieces[3][2].setPiece(Position.BLACK);
                boardPieces[3][3].setPiece(Position.WHITE);
                break;
            }
            case "12": {
                boardPieces[2][4].setPiece(Position.WHITE);
                boardPieces[2][5].setPiece(Position.BLACK);
                boardPieces[3][4].setPiece(Position.BLACK);
                boardPieces[3][5].setPiece(Position.WHITE);
                break;
            }
            case "13": {
                boardPieces[4][2].setPiece(Position.WHITE);
                boardPieces[4][3].setPiece(Position.BLACK);
                boardPieces[5][2].setPiece(Position.BLACK);
                boardPieces[5][3].setPiece(Position.WHITE);
                break;
            }
            case "14": {
                boardPieces[4][4].setPiece(Position.WHITE);
                boardPieces[4][5].setPiece(Position.BLACK);
                boardPieces[5][4].setPiece(Position.BLACK);
                boardPieces[5][5].setPiece(Position.WHITE);
                break;
            }
            case "2": {
                boardPieces[3][3].setPiece(Position.WHITE);
                boardPieces[3][4].setPiece(Position.BLACK);
                boardPieces[4][3].setPiece(Position.BLACK);
                boardPieces[4][4].setPiece(Position.WHITE);
                break;
            }
            default:{
                System.out.println("Please choose starting positions from the indicated options.");
                break;
            }
        }
    }

    //method that displays the board:
    public void drawBoard(){
        System.out.println("   ");
        for (char c='A'; c<'I'; c++){
            System.out.print(" "+c);
        }
        System.out.println();
        for(int row=0; row<8; row++){
            System.out.print(row+1);
            for(int col=0; col<8; col++){
                System.out.print(" "+boardPieces[row][col].getPiece());
            }
            System.out.println();
        }
    }

    //helper method that checks the validity of a move in the corresponding direction:
    private boolean checkDirection(int row, int col, int rowDir, int colDir, char color, boolean flip) {
        int i = row + rowDir;
        int j = col + colDir;
        char opposite = color == Position.BLACK ? Position.WHITE : Position.BLACK;
        boolean foundOpposite = false;

        while (i >= 0 && i < 8 && j >= 0 && j < 8 && boardPieces[i][j].getPiece() == opposite) {
            i += rowDir;
            j += colDir;
            foundOpposite = true;
        }

        if (foundOpposite && i >= 0 && i < 8 && j >= 0 && j < 8 && boardPieces[i][j].getPiece() == color) {
            if (flip) {
                i = row + rowDir;
                j = col + colDir;
                while (boardPieces[i][j].getPiece() == opposite) {
                    boardPieces[i][j].setPiece(color);
                    i += rowDir;
                    j += colDir;
                }
            }
            return true;
        }
        return false;
    }

    //method that uses the helper method checkDirection() to verify the validity of moves without flipping the pieces
    public boolean isValid(String coor, char color) {
        int row = Position.coordinates(coor)[0];
        int col = Position.coordinates(coor)[1];
        boolean valid = false;

        // checking in all 8 directions
        valid |= checkDirection(row, col, 0, -1, color, false); // left
        valid |= checkDirection(row, col, 0, 1, color, false);  // right
        valid |= checkDirection(row, col, -1, 0, color, false); // up
        valid |= checkDirection(row, col, 1, 0, color, false);  // down
        valid |= checkDirection(row, col, -1, -1, color, false); // top-left
        valid |= checkDirection(row, col, -1, 1, color, false);  // top-right
        valid |= checkDirection(row, col, 1, -1, color, false);  // bottom-left
        valid |= checkDirection(row, col, 1, 1, color, false);   // bottom-right

        return valid;
    }

    //method that uses the helper method isValid() to flip the pieces if move is valid
    public boolean flipPiece(String coor, char color) {
        int row = Position.coordinates(coor)[0];
        int col = Position.coordinates(coor)[1];
        boolean valid = false;

        valid |= checkDirection(row, col, 0, -1, color, true); // left
        valid |= checkDirection(row, col, 0, 1, color, true);  // right
        valid |= checkDirection(row, col, -1, 0, color, true); // up
        valid |= checkDirection(row, col, 1, 0, color, true);  // down
        valid |= checkDirection(row, col, -1, -1, color, true); // top-left
        valid |= checkDirection(row, col, -1, 1, color, true);  // top-right
        valid |= checkDirection(row, col, 1, -1, color, true);  // bottom-left
        valid |= checkDirection(row, col, 1, 1, color, true);   // bottom-right

        return valid;
    }

    //helper method that verifies if any valid moves on the board are left for the current player
    public boolean checkAnyMoves(){
        boolean movesLeft = false;
        //iterating through all coordinates to see if there is any valid move left:
        breakOut:
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                String coor = "" + ((char) ('A' + col)) + (row + 1);
                if (getBoardPieces()[row][col].canPlay() && isValid(coor, current.getColor())) {
                    movesLeft = true;
                    break breakOut;
                }
            }
        }
        return movesLeft;
    }

    //method that performs a move:
    public void makeMove(String coor) {
        int row = Position.coordinates(coor)[0];
        int col = Position.coordinates(coor)[1];

        char color = current.equals(first) ? Position.BLACK : Position.WHITE;

        // Check if the move is playable and flip the pieces if it is
        if (getBoardPieces()[row][col].canPlay() && flipPiece(coor, color)) {
            getBoardPieces()[row][col].setPiece(color);
        } else {
            System.out.println("Unplayable position.");
        }
    }

    //helper method that determines whether the game has ended by checking if there are any moves left for each player:
    private boolean hasEnded() {
        Player temp=current;
        boolean ended = true;
        // Check if both players have moves
        Player[] players = {first, second};
        for (Player player : players) {
            current = player;
            if (checkAnyMoves()) {
                ended = false;
                break;
            }
        }
        // If the game has ended, count the pieces of both players
        if (ended) {
            int black = 0;
            int white = 0;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    char piece = getBoardPieces()[i][j].getPiece();
                    if (piece == Position.BLACK) {
                        black++;
                    } else if (piece == Position.WHITE) {
                        white++;
                    }
                }
            }
            // Determine the winner
            String winner = "";
            boolean tie = false;

            if (black > white) {
                winner = first.getName();
            } else if (white > black) {
                winner = second.getName();
            } else {
                tie = true;
            }

            // Draw the board and announce the result
            drawBoard();

            if (tie) {
                System.out.println("The game has ended in a tie.\n" + first.getName() + ": " + black + "\n" + second.getName() + ": " + white);
            } else {
                System.out.println("The game has ended.\n" + winner + " won the game\n" + first.getName() + ": " + black + "\n" + second.getName() + ": " + white);
            }
        }
        current=temp;
        return ended;
    }

    //method with implementation for playing the game:
    public void play() {
        boolean end = false;

        while (!end && !hasEnded()) {
            end = hasEnded();
            drawBoard();
            if (checkAnyMoves()) {
                System.out.println("Player " + current.getName() + "'s turn");
                System.out.println("1. Save game \t2. Concede game \t3. Make a move");
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1: {
                        System.out.println("Enter filename: ");
                        save_file = sc.nextLine();
                        save(save_file);
                        System.out.println("Saving game~~~");
                        end = true;
                        break;
                    }
                    case 2: {
                        end = true;
                        System.out.println(current.getName() + " concedes the game.");
                        break;
                    }
                    case 3: {
                        System.out.println("Enter your move following the configuration of ColumnRow (example: A1): ");
                        String move = sc2.nextLine();
                        if (isValid(move, current.getColor())) {
                            manageTurn(move, current);
                            takeTurn();
                        } else {
                            System.out.println("Invalid move");
                        }
                        break;
                    }
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } else {
                System.out.println("Player " + current.getName() + "'s turn");
                System.out.println("1. Save game \t2. Concede game \t3. Forfeit turn");
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1: {
                        System.out.println("Enter filename: ");
                        save_file = sc.nextLine();
                        save(save_file);
                        System.out.println("Saving game~~~");
                        end = true;
                        break;
                    }
                    case 2: {
                        end = true;
                        System.out.println(current.getName() + " concedes the game.");
                        break;
                    }
                    case 3: {
                        System.out.println("Forfeiting turn... next player's turn");
                        takeTurn();
                        break;
                    }
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }
        }
    }

    //constructor for the Board class, initializes a game board based on data read from a file:
    public Board(String save_file) {
        name = save_file;
        boardPieces = new Position[8][8];
        initializeBoard("2");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(save_file));
            String currentLine;
            int i = 0;
            while ((currentLine = reader.readLine()) != null) {
                if (i == 0) {
                    first = new Player(currentLine, Position.BLACK);
                } else if (i == 1) {
                    second = new Player(currentLine, Position.WHITE);
                } else if (i == 2) {
                    current = currentLine.equals(first.getName()) ? first : second;
                } else {
                    for (int j = 0; j < currentLine.length(); j++) {

                        if (currentLine.charAt(j) == Position.BLACK) {
                            boardPieces[i - 3][j].setPiece(Position.BLACK);
                        } else if (currentLine.charAt(j) == Position.WHITE) {
                            boardPieces[i - 3][j].setPiece(Position.WHITE);
                        } else if (currentLine.charAt(j) == Position.UNPLAYABLE) {
                            boardPieces[i - 3][j].setPiece(Position.UNPLAYABLE);
                        } else if (currentLine.charAt(j) == Position.EMPTY) {
                            boardPieces[i - 3][j].setPiece(Position.EMPTY);
                        }
                    }
                }
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //method to write (save) the board state in a file output:
    private void save(String save_file) {
    /* Games should be saved as a text file, with first, second and current player names on the first three lines,
    then all the characters in the board state on subsequent lines. */
        try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter(save_file));
            // Write the first, second, and current player names, each on a new line
            writer.write(first.getName());
            writer.write("\n"+second.getName());
            writer.write("\n"+current.getName()+"\n");

            // write the board state row by row
            for (int i = 0; i < boardPieces.length; i++) {
                for (int j = 0; j < boardPieces[i].length; j++) {
                    // Convert the Position object to its character representation
                    writer.write(boardPieces[i][j].getPiece());
                }
                writer.newLine();  // Move to the next line after each row
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to load a previous game board from a file input:
    public static Board load() {
        System.out.println("Enter filename: ");
        String save_file = sc.nextLine();
        Board board = new Board(save_file);

        try (BufferedReader reader = new BufferedReader(new FileReader(save_file))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                switch (i) {
                    case 0:
                        board.first = new Player(line, Position.BLACK);
                        break;
                    case 1:
                        board.second = new Player(line, Position.WHITE);
                        break;
                    case 2:
                        board.current = line.equals(board.first.getName()) ? board.first : board.second;
                        break;
                }
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found. Please enter a valid file name to load it.");
        } catch (IOException e) {
            System.out.println("Error: an I/O error occurred when loading the game.");
            e.printStackTrace();
        }
        board.play();
        return board;
    }


    /*helper method for setting the current player's piece at the specified position to their own color if the move is
    valid and flips opponent pieces:*/
    public void manageTurn(String coor, Player current){
        char color=current.getColor();
        int row=Position.coordinates(coor)[0];
        int col=Position.coordinates(coor)[1];
        if (boardPieces[row][col].canPlay() && flipPiece(coor, color)) {
            boardPieces[row][col].setPiece(color);
        }
        else {
            System.out.println("The selected position is unplayable.");
        }
    }

    //method that manages players taking turns:
    private void takeTurn(){
        current = current == first ? second : first;
    }

}
