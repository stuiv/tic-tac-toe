package com.example.tic_tac_toe;

import java.io.Serializable;

public class Game implements Serializable {

    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    public Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private GameState gameState;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        movesPlayed = 0;
        gameState = GameState.IN_PROGRESS;
    }

    public TileState choose(int row, int column) {
        TileState current_state = board[row][column];

        if (current_state == TileState.BLANK ) {
            movesPlayed++;
            if (playerOneTurn) {
                board[row][column] = TileState.CROSS;
                updateGameState(row, column);
                playerOneTurn = !playerOneTurn;
                return TileState.CROSS;

            } else {
                board[row][column] = TileState.CIRCLE;
                updateGameState(row, column);
                playerOneTurn = !playerOneTurn;
                return TileState.CIRCLE;
            }
        } else {
            return TileState.INVALID;
        }
    }

    public TileState getTileState(int row, int column) {
        return board[row][column];
    }
    public GameState won() {
        return gameState;
}

    private void updateGameState(int row, int column) {
        TileState turn;
        if (playerOneTurn) {
            turn = TileState.CROSS;
        } else {
            turn = TileState.CIRCLE;
        }

        //If there vertical/horizontal/diagonal row of 3 exists. player whos turn it is wins.
        if ((board[row][0] == turn && board[row][1] == turn && board[row][2] == turn) ||
            (board[0][column] == turn && board[1][column] == turn && board[2][column] == turn) ||
            (board[0][0] == turn && board[1][1] == turn && board[2][2] == turn) ||
            (board[0][2] == turn && board[2][0] == turn && board[1][1] == turn)) {

            if (playerOneTurn) {
                gameState = GameState.PLAYER_TWO;

            } else {
                gameState = GameState.PLAYER_ONE;
            }
            return;
        }
        if (movesPlayed >= 9) {
            gameState = GameState.DRAW;
        }
    }
}
