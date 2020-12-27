package com.example.tictactoe;

import android.util.Log;

/**
 * The class <b>TicTacToeGame</b> is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Deniz Sevinc
 */
public class TicTacToeGame {
    private static final String TAG = "TicTacToeGame";

     /**
     * The board of the game, stored as a one dimension array.
     */
    private CellValue[] board;


    /**
     * level records the number of rounds that have been
     * played so far.
     */
    private int level;

    /**
     * gameState records the current state of the game.
     */
    private GameState gameState;


    /**
     * lines is the number of lines in the grid
     */
    private int lines;

    /**
     * columns is the number of columns in the grid
     */
    private int columns;


    /**
     * sizeWin is the number of cell of the same type
     * that must be aligned to win the game
     */
    private int sizeWin;


    /**
     * default constructor, for a game of 3x3, which must
     * align 3 cells
     */
    public TicTacToeGame(){

        board = new CellValue[9];
        for (int i=0; i<9; i++) {
            board[i] = CellValue.EMPTY;
        }
        level = 0;
        gameState = GameState.PLAYING;
        lines = 3;
        columns = 3;
        sizeWin = 3;
    }

    /**
     * constructor allowing to specify the number of lines
     * and the number of columns for the game. 3 cells must
     * be aligned.
     * @param lines
     *  the number of lines in the game
     * @param columns
     *  the number of columns in the game
     */
    public TicTacToeGame(int lines, int columns){

        int numCells = lines*columns;
        board = new CellValue[numCells];
        for (int i=0; i<numCells; i++) {
            board[i] = CellValue.EMPTY;
        }
        level = 0;
        this.lines = lines;
        this.columns = columns;
        gameState = GameState.PLAYING;
        sizeWin = 3;
    }

    /**
     * constructor allowing to specify the number of lines
     * and the number of columns for the game, as well as
     * the number of cells that must be aligned to win.
     * @param lines
     *  the number of lines in the game
     * @param columns
     *  the number of columns in the game
     * @param sizeWin
     *  the number of cells that must be aligned to win.
     */
    public TicTacToeGame(int lines, int columns, int sizeWin){

        int numCells = lines*columns;
        board = new CellValue[numCells];
        for (int i=0; i<numCells; i++) {
            board[i] = CellValue.EMPTY;
        }
        level = 0;
        this.lines = lines;
        this.columns = columns;
        gameState = GameState.PLAYING;
        this.sizeWin = sizeWin;
    }

    /**
     * getter for the variable lines
     * @return
     * 	the value of lines
     */
    public int getLines(){

        return lines;

    }

    /**
     * getter for the variable columns
     * @return
     * 	the value of columns
     */
    public int getColumns(){

        return columns;

    }

    /**
     * getter for the variable level
     * @return
     * 	the value of level
     */
    public int getLevel(){

        return level;

    }

    /**
     * getter for the variable sizeWin
     * @return
     * 	the value of sizeWin
     */
    public int getSizeWin(){

        return sizeWin;

    }

    /**
     * getter for the variable gameState
     * @return
     * 	the value of gameState
     */
    public GameState getGameState(){

        return gameState;

    }

    /**
     * returns the cellValue that is expected next,
     * in other word, which played (X or O) should
     * play next.
     * This method does not modify the state of the
     * game.
     * @return
     *  the value of the enum CellValue corresponding
     * to the next expected value.
     */
    public CellValue nextCellValue(){

        if (level == 0) {
            return CellValue.X;
        }
        if (level % 2 == 0) {
            return CellValue.X;
        } else {
            return CellValue.O;
        }
    }

    /**
     * returns the value  of the cell at
     * index i.
     * If the index is invalid, an error message is
     * printed out. The behaviour is then unspecified
     * @param i
     *  the index of the cell in the array board
     * @return
     *  the value at index i in the variable board.
     */
    public CellValue valueAt(int i) {

        if ((i >= 0) || i < this.board.length) {
            return this.board[i];
        } else {
            Log.e(TAG, "Error: array index out of bounds or invalid");
            return this.board[0];
        }

    }

    /**
     * This method is called when the next move has been
     * decided by the next player. It receives the index
     * of the cell to play as parameter.
     * If the index is invalid, an error message is
     * printed out. The behaviour is then unspecified
     * If the chosen cell is not empty, an error message is
     * printed out. The behaviour is then unspecified
     * If the move is valide, the board is updated, as well
     * as the state of the game.
     * To faciliate testing, is is acceptable to keep playing
     * after a game is already won. If that is the case, the
     * a message should be printed out and the move recorded.
     * the  winner of the game is the player who won first
     * @param i
     *  the index of the cell in the array board that has been
     * selected by the next player
     */
    public void play(int i) {

        if ((i >= 0) || i < this.board.length) {
            if (this.board[i] != CellValue.EMPTY) {
                Log.w(TAG,"This cell has already been played");
            } else {
                this.board[i] = nextCellValue();
                this.level++;
                setGameState(i);
            }
        }


    }

    /**
     * A helper method which updates the gameState variable
     * correctly after the cell at index i was just set in
     * the method play(int i)
     * The method assumes that prior to setting the cell
     * at index i, the gameState variable was correctly set.
     * it also assumes that it is only called if the game was
     * not already finished when the cell at index i was played
     * (i.e. the game was playing). Therefore, it only needs to
     * check if playing at index i has concluded the game, and if
     * set the oucome correctly
     *
     * @param i
     *  the index of the cell in the array board that has just
     * been set
     */
    private void setGameState(int i){

        // YOUR CODE HERE
        if (this.gameState == GameState.PLAYING) {
            CellValue[][] temp2d = new CellValue[this.lines][this.columns]; //convert 1d board array to a more easily manipulable 2d board
            int cells1d = 0;
            for (int x = 0; x<this.lines; x++) {
                for (int y = 0; y<this.columns; y++) {
                    temp2d[x][y] = board[cells1d];
                    cells1d++;
                }
            }
            // System.out.println("2d array" + Arrays.deepToString(temp2d)); //debugging
            if(checkRows(temp2d)) {
                //check if the last player won in any row
                if (board[i] == CellValue.O) {
                    //if player O wins, print a message and change the gameState variable of this class
                    this.gameState = GameState.OWIN;
                    Log.i(TAG,this.toString());
                    Log.i(TAG,"Result: OWIN");
                } else if (board[i] == CellValue.X) {
                    //if player X wins, print a message and change the gameState variable of this class
                    this.gameState = GameState.XWIN;
                    Log.i(TAG,this.toString());
                    Log.i(TAG,"Result: XWIN");
                }
            }
            if(checkCols(temp2d)) {
                //check if the last player won in any column
                if (board[i] == CellValue.O) {
                    this.gameState = GameState.OWIN;
                    Log.i(TAG,this.toString());
                    Log.i(TAG,"Result: OWIN");
                } else if (board[i] == CellValue.X) {
                    this.gameState = GameState.XWIN;
                    Log.i(TAG,this.toString());
                    Log.i(TAG,"Result: XWIN");
                }
            }
            if(checkDiags(temp2d,board[i])) {
                //check if the last player won in any diagonal
                if (board[i] == CellValue.O) {
                    this.gameState = GameState.OWIN;
                    Log.i(TAG,this.toString());
                    Log.i(TAG,"Result: OWIN");
                } else if (board[i] == CellValue.X) {
                    this.gameState = GameState.XWIN;
                    Log.i(TAG,this.toString());
                    Log.i(TAG,"Result: XWIN");
                }
            }
            if(checkDraw()) {
                //change game state if game is a draw
                Log.i(TAG,"Result: DRAWN");
                Log.i(TAG,this.toString());
                this.gameState = GameState.DRAW;
            }
        }

    }

    /**
     * A helper method called by setGameState to check if the game has reached a draw
     * (i.e. if no more cells can be filled in the board)
     *
     * @return draw
     *  true, if the game is a draw and false otherwise
     */
    private boolean checkDraw() {
        boolean draw = false;
        int numEmpty = 0;
        for (int i = 0; i < (lines*columns); i++) {
            if (board[i] == CellValue.EMPTY) {
                numEmpty++;
            }
        }
        if (numEmpty == 0) {
            draw = true;
        }
        return draw;
    }

    /**
     * A helper method called by setGameState to check if the game was won after the last move
     * If the number of consecutive cells in a row reaches this.sizeWin, the game is declared as won
     * @param board2d
     * a reference to the 1d board converted into a 2d array
     *
     * @return win
     *  true, if the game is won, false otherwise
     */
    private boolean checkRows(CellValue[][] board2d) {
        int consecutive = 0;
        boolean win = false;
        //
        for (int i = 0; i<lines; i++) {
            for (int j = 0; j < columns-1; j++) {
                if ((board2d[i][j] == board2d[i][j + 1]) && board2d[i][j] != CellValue.EMPTY) {
                    consecutive++;
                    // System.out.println("checkRows consecutive = " + consecutive);
                    if (consecutive >= sizeWin -1) {
                        win = true;
                    }
                } else {
                    //reset counter if non-consecutive tile detected
                    consecutive = 0;
                }
            }
        }
        return win;
    }

    /**
     * A helper method called by setGameState to check if the game was won after the last move
     * If the number of consecutive cells in a column reaches this.sizeWin, the game is declared as won
     * @param board2d
     * a reference to the 1d board converted into a 2d array
     *
     * @return win
     *  true, if the game is won, false otherwise
     */
    private boolean checkCols(CellValue board2d[][]) {
        int consecutive = 0;
        boolean win = false;
        //
        for (int i = 0; i<columns; i++) {
            for (int j = 0; j < lines-1; j++) {
                if ((board2d[j][i] == board2d[j+1][i] && board2d[j][i] != CellValue.EMPTY)) {
                    consecutive++;
                    // System.out.println("checkCols consecutive = " + consecutive);
                    if (consecutive >= sizeWin-1) {
                        win = true;
                    }
                } else {
                    //reset counter if non-consecutive tile detected
                    consecutive = 0;
                }
            }
        }
        return win;
    }

    /**
     * A helper method called by setGameState to check if the game was won after the last move
     * If the number of consecutive cells in a diagonal reaches this.sizeWin, the game is declared as won
     * @param board2d
     * a reference to the 1d board converted into a 2d array
     * @param player
     * the CellValue of the most recent player
     * @return win
     *  true, if the game is won, false otherwise
     */
    private boolean checkDiags(CellValue board2d[][], CellValue player) {
        int consecutive = 0;
        boolean win = false;
        int maxDiag = columns;
        if(columns > lines) {
            maxDiag = lines;
        }
        //
        int tileCtr = 0;

        //check main (central) diagonal that passes through the middle of the board
        while (tileCtr < maxDiag-1) {
            if (board2d[tileCtr][tileCtr] == board2d[tileCtr+1][tileCtr+1] && board2d[tileCtr][tileCtr] != CellValue.EMPTY) {
                consecutive++; //count the number of consecutive occurrences of tiles in the main diagonal
                // System.out.println("consecutive diags = " + consecutive);
                if (consecutive >= sizeWin-1) {
                    win = true; //the game is won if the number of consecutive diagonal tiles reaches sizeWin
                }
            } else {
                consecutive = 0; //reset counter to 0 if next tile does not match previous tile
            }
            tileCtr++;
        }

        int colCtr = this.columns-1;
        int rowCtr = 0;
        int consecutiveR = 0;
        //check main counter diagonal
        while (colCtr > 0 && rowCtr < this.lines -1) {
            if (board2d[rowCtr][colCtr] == board2d[rowCtr+1][colCtr-1] && board2d[rowCtr][colCtr] != CellValue.EMPTY) {
                consecutiveR++; //count the number of consecutive occurrences of tiles in the main counter diagonal
                // System.out.println("revDiag Count = " + consecutiveR);
                if (consecutiveR >= sizeWin-1) {
                    win = true;
                }
            } else {
                consecutiveR = 0;
            }
            colCtr--;
            rowCtr++;
        }

        //checks for non-central diagonals (i.e. if the diagonal does not intersect the center of the board)
        int gridSize = Math.min(columns,lines); //if the grid is not a square, the max diagonal size should be the smaller dimension
        for (int y = sizeWin-gridSize; y < gridSize-sizeWin; y++) { //row 0 to row lines-1
            int fCount = 0; //forward diagonal counter
            int firstIx = Math.max(0, y); //column iteration, use max since y can be negative (out of bound)
            int lastIx = Math.min(gridSize, gridSize + y);  //stop at gridSize in case (gridSize + y) is out of array bounds
            // System.out.println("first ix: " + firstIx + " last ix: " + lastIx);
            for (int yy = firstIx; yy<lastIx; yy++) {
                //loop over array
                // System.out.println("yy = " + yy + " y= " + y);
                if ((yy + y) < 0) {
                    y = Math.abs(y); //so array boundary is not exceeded (negative index not allowed in java?)
                }
                if (board2d[yy][yy+y].equals(player)) {
                    //check diagonals from top left to bottom right
                    //increment number of tiles detected in diagonal
                    fCount++;
                    // System.out.println("diagonal score:" + fCount);
                    if (fCount >= sizeWin) {
                        win = true;
                    }
                } else {
                    fCount = 0;
                }
            }
        }

        //checks for a win in reverse diagonals (including non-central)
        for (int z = sizeWin-gridSize; z < gridSize - sizeWin+1; z++) {
            int rCount = 0; //counter in reverse diagonal direction
            int firstR = Math.max(0,z); //if z is a negative integer, start at 0 to avoid going out of array boundary
            int lastR = Math.min(gridSize, gridSize + z); //if z is positive, stop at gridSize
            // System.out.println("first r: " + firstR + " last r: " + lastR);
            for(int zz = firstR; zz<lastR;zz++) {
                // System.out.println("zz = " + zz + " z= " + z);
                if (board2d[zz][gridSize+z-zz-1].equals(player)){
                    // checking from top right to bottom left of board for all possible diagonals
                    rCount++;
                    // System.out.println("diagonal rev score:" + rCount);
                    if (rCount >= sizeWin) {
                        win = true;
                    }
                }
                else {
                    rCount = 0;
                }
            }
        }
        return win;
    }

    /**
     * Returns a String representation of the game matching
     * the example provided in the assignment's description
     *
     * @return
     *  String representation of the game
     */
    public String toString(){

        String output = "\n";
        int cellIndex = 0;
        for (int j = 0; j < this.lines; j++) {
            for (int k = 0; k < this.columns; k++) {
                if (this.board[cellIndex] == CellValue.EMPTY) {
                    output+="   ";
                } else if (this.board[cellIndex] == CellValue.X) {
                    output +=" X ";
                } else {
                    output += " O ";
                }
                if (k != (this.columns -1)) {
                    output += "|";
                }
                cellIndex++;
            }
            output = output + "\n";
            if (j != (this.lines -1)) {
                for (int m = 0; m<this.columns * 4;m++) {
                    output += "-";
                }
            }
            output += "\n";
        }
        return output;
    }
}
