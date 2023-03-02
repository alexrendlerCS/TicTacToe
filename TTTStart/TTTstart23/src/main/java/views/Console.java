package views;

import java.util.Scanner;

import model.*;

public class Console {

    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = '_';

    private char[][] board;
    private TicTacToeGame game;
    private RandomAI ai;
    private Scanner scanner;

    public Console() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
        game = new TicTacToeGame();
        ai = new RandomAI();
        scanner = new Scanner(System.in);
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isGameOver() {
        return game.didWin('X') || game.didWin('O') || game.tied();
    }

    public void playGame() {
        char currentPlayer = 'O';

        while (!isGameOver()) {
            if (currentPlayer == 'X') {
                System.out.print("Enter row and column for X: ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
                    System.out.println("Invalid row or column, try again.");
                    continue;
                }
                if (board[row][col] != EMPTY_CELL) {
                    System.out.println("Cell already taken, try again.");
                    continue;
                }
                game.humanMove(row, col, true);
                board[row][col] = currentPlayer;
                printBoard();
            } else {
            	OurPoint point = ai.desiredMove(game);
            	while(board[point.getRow()][point.getCol()] != EMPTY_CELL) {
            		point = ai.desiredMove(game);}
            	game.computerMove(point.getRow(), point.getCol());
                board[point.getRow()][point.getCol()] = currentPlayer;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        if (game.tied()) {
            System.out.println("Tie");
        } else if (game.didWin('O')) {
            System.out.println("O wins");
        } else {
            System.out.println("X wins");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Console game = new Console();
        game.playGame();
    }
}
