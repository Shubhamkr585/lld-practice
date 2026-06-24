//package TicTacToe;

import jdk.jshell.spi.SPIResolutionException;
import java.util.*;

enum PieceType{
    X,
    O
}

enum GameStatus{
    WIN,
    LOSE,
    DRAW,
    IN_PROGRESS,
}

class PlayingPiece{
     PieceType type;

    PlayingPiece(PieceType type){
        this.type = type;
    }

    PieceType getPieceType(){
        return type;
    }
}

class Player{
    private final String name;
    private  final PlayingPiece playingPiece;

    Player(String name, PieceType pieceType){
        this.name = name;
        this.playingPiece = new PlayingPiece(pieceType);
    }

    String getName(){
        return name;
    }

    PlayingPiece getPlayingPiece(){
        return playingPiece;
    }
}

class Cell{
    private final int x;
    private final int y;
    private PlayingPiece playingPiece;

    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean isEmpty(){
        return playingPiece == null;
    }

    public PlayingPiece getPlayingPiece() {
        return playingPiece;
    }

    public void setPlayingPiece(PlayingPiece playingPiece) {
        this.playingPiece = playingPiece;
    }
}

// Board

class Board{
    private final int size;
    private final Cell[][] cells;

    Board(int size){
        this.size = size;
        cells = new Cell[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cells[i][j] = new Cell(i,j);
            }
        }
    }

    public boolean placePiece(int row, int col,PlayingPiece playingPiece){
        if(row<0 || row>=size || col<0 || col>=size){
            return false;
        }

        Cell cell = cells[row][col];
        if(!cell.isEmpty()){
            return false;
        }
        cell.setPlayingPiece(playingPiece);
        return true;
    }

    public int getSize(){
        return size;
    }

    public Cell[][] getCell(int x, int y){
        return cells;
    }

    public void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                Cell cell = cells[i][j];
                if(cell.getPlayingPiece()==null){
                    System.out.print(" ");
                }
                else{
                    System.out.print(cell.getPlayingPiece().getPieceType());
                }
                if(j<size-1){
                    System.out.print("|");
                }
            }
            System.out.println();

            if(i<size-1){
                for(int j=0;j<size;j++){
                    System.out.print("--");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}

//strategy pattern

interface WinningStrategy{
    boolean recordMoveAndCheckWinner(
            int row,int col,PieceType pieceType
    );
}

class OptimizedWinningStrategy
        implements WinningStrategy {

    private final int size;

    private final int[] rowCounts;
    private final int[] colCounts;

    private int diagonalCount;
    private int antiDiagonalCount;

    public OptimizedWinningStrategy(int size) {

        this.size = size;

        rowCounts = new int[size];
        colCounts = new int[size];
    }

    @Override
    public boolean recordMoveAndCheckWinner(
            int row,
            int col,
            PieceType pieceType) {

        int value =
                pieceType == PieceType.X
                        ? 1
                        : -1;

        rowCounts[row] += value;
        colCounts[col] += value;

        if (row == col) {
            diagonalCount += value;
        }

        if (row + col == size - 1) {
            antiDiagonalCount += value;
        }

        int target =
                pieceType == PieceType.X
                        ? size
                        : -size;

        return rowCounts[row] == target
                || colCounts[col] == target
                || diagonalCount == target
                || antiDiagonalCount == target;
    }
}

// Game

public class TicTacToeGame {
    private final Board board;
    private final WinningStrategy winningStrategy;
    private final Deque<Player> players;
    private int freeCells;
    private GameStatus gameStatus;

    public TicTacToeGame(int size) {
        board = new Board(size);
        players = new LinkedList<>();
        winningStrategy = new OptimizedWinningStrategy(size);

        Player p1 = new Player(
                "Player 1",
                PieceType.X
        );

        Player p2 = new Player(
                "Player 2",
                PieceType.O
        );

        players.addLast(p1);
        players.addLast(p2);

        freeCells = size * size;

        gameStatus = GameStatus.IN_PROGRESS;
    }

    public String startGame() {
        Scanner in = new Scanner(System.in);

        while (gameStatus == GameStatus.IN_PROGRESS) {
            Player currentPlayer = players.pollFirst();
            board.printBoard();

            System.out.println(
                    currentPlayer.getName()
                            + "'s turn ("
                            + currentPlayer
                            .getPlayingPiece()
                            .getPieceType()
                            + ")"
            );


            System.out.println("Enter row and col: ");
            int row = in.nextInt();
            int col = in.nextInt();

            boolean placed=board.placePiece(row,col,currentPlayer.getPlayingPiece());

            if(!placed){
                System.out.println("Invalid Move!");
                players.addFirst(currentPlayer);
                continue;
            }
            freeCells--;
            boolean winner=winningStrategy.recordMoveAndCheckWinner(row,col,
                    currentPlayer.getPlayingPiece().getPieceType());

            if(winner){
                gameStatus=GameStatus.WIN;
                board.printBoard();
                System.out.println("Winner!");
                return currentPlayer.getName()+" Wins!";
            }

            if(freeCells==0){
                gameStatus=GameStatus.DRAW;
                board.printBoard();
                return "Draw!";
            }

            players.addLast(currentPlayer);
        }
        return "Game Ended";
    }

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame(3);
        String result = game.startGame();
        System.out.println(result);
    }
}