package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.TicTacToeGame;
/*
 * This class will take the given tik tac toe board
 * and find the spot on the board that will give the player
 * the win. If that spot doesn't exist, it will find a move
 * that will block the opponent from winning. If neither of 
 * these are an option it will simply choose a random spot
 * to place.
 * Author: Alex Rendler
 */
public class IntermediateAI implements TicTacToeStrategy {

    @Override
    public OurPoint desiredMove(TicTacToeGame theGame) {
        // Check for a winning move
        OurPoint winningMove = getWinningMove(theGame, 'O');
        if (winningMove != null) {
            return winningMove;
        }

        // Check for a move that blocks the opponent's winning move
        OurPoint blockingMove = getWinningMove(theGame, 'X');
        if (blockingMove != null) {
            return blockingMove;
        }

        // Pick a random move from the available moves
        List<OurPoint> availableMoves = getAvailableMoves(theGame);
        Random random = new Random();
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }
    
    // Returns a List of any available spots on the board.
    private List<OurPoint> getAvailableMoves(TicTacToeGame theGame) {
    	char[][] board = theGame.getTicTacToeBoard();
        List<OurPoint> availableMoves = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '_') {
                    availableMoves.add(new OurPoint(row, col));
                }
            }
        }
        return availableMoves;
    }
    
    // Helper method to get a winning move for the given player, if one exists
    private OurPoint getWinningMove(TicTacToeGame theGame, int player) {
        // Check rows
    	char[][] board = theGame.getTicTacToeBoard();
        for (int row = 0; row < 3; row++) {
            int playerCount = 0;
            int emptyCount = 0;
            OurPoint emptyPoint = null;
            for (int col = 0; col < 3; col++) {
                int cellValue = board[row][col];
                if (cellValue == player) {
                    playerCount++;
                } else if (cellValue == '_') {
                    emptyCount++;
                    emptyPoint = new OurPoint(row, col);
                }
            }
            if (playerCount == 2 && emptyCount == 1) {
                return emptyPoint;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            int playerCount = 0;
            int emptyCount = 0;
            OurPoint emptyPoint = null;
            for (int row = 0; row < 3; row++) {
                int cellValue = board[row][col];
                if (cellValue == player) {
                    playerCount++;
                } else if (cellValue == '_') {
                    emptyCount++;
                    emptyPoint = new OurPoint(row, col);
                }
            }
            if (playerCount == 2 && emptyCount == 1) {
                return emptyPoint;
            }
        }

        // Check diagonals
        int playerCount = 0;
        int emptyCount = 0;
        OurPoint emptyPoint = null;
        for (int i = 0; i < 3; i++) {
            int cellValue = board[i][i];
            if (cellValue == player) {
                playerCount++;
            } else if (cellValue == '_') {
                emptyCount++;
                emptyPoint = new OurPoint(i, i);
            }
        }
        if (playerCount == 2 && emptyCount == 1) {
            return emptyPoint;
        }

        playerCount = 0;
        emptyCount = 0;
        emptyPoint = null;
        for (int i = 0; i < 3; i++) {
            int cellValue = board[i][2-i];
            if (cellValue == player) {
                playerCount++;
            } else if (cellValue == '_') {
                emptyCount++;
                emptyPoint = new OurPoint(i, 2 - i);
            }
        
        }
		return null;
    }
}
