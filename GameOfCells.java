package Coditation_Assignment;

public class GameOfCells {
	
	private static char Live;
	private static char Dead;
	
	GameOfCells(char L, char D){
		GameOfCells.Live = L;
		GameOfCells.Dead = D;
	}
	
	public void printBoard(char[][] board) {

        for (int i = 0; i < board.length ; i++) {
            for (int j = 0; j < board[i].length ; j++) {
                System.out.print((board[i][j]) + " ");
            } 
            System.out.println();
        }
        System.out.println();
    }
	
	public char[][] getNextGenerationBoard(char[][] board) {

        // The board does not have any values so return the newly created
        // playing field.
        if (board.length <= 0 || board[0].length <= 0) {
        	System.out.print("Invalid Board");
            throw new IllegalArgumentException("Board must have a positive amount of rows and/or columns");
        }

        int nrRows = board.length;
        int nrCols = board[0].length;

        // temporary board to store new values
        char[][] nextGen = new char[nrRows][nrCols];

        for (int row = 0 ; row < nrRows ; row++) {
            for (int col = 0 ; col < nrCols ; col++) {
            	nextGen[row][col] = getNewCellState(board[row][col], getCountLiveNeighbours(row, col, board));
            }
        }   
        return nextGen;
    }
	
	public String getCellState(char[][] board, String name) {
		
		try {
		String[] arr = name.split("_");
		int row = Integer.parseInt(arr[0]);
		int col = Integer.parseInt(arr[1]);
		
		if(row >= board.length || row < 0 || col >= board[0].length || col < 0)
			return ("Invalid Cell Name");
		
		if(board[row][col] == Live) 
			return "Live";
		
		else
			return "Dead";
		
		}
		catch(Exception e) {
			return "Invalid Cell Name";
		}
	}
	
	static int getCountLiveNeighbours(int cellRow, int cellCol, char[][] board) {

        int CountliveNeighbours = 0;
        int rowEnd = Math.min(board.length , cellRow + 2);
        int colEnd = Math.min(board[0].length, cellCol + 2);

        for (int row = Math.max(0, cellRow - 1) ; row < rowEnd ; row++) {
            for (int col = Math.max(0, cellCol - 1) ; col < colEnd ; col++) {
                if ((row != cellRow || col != cellCol) && board[row][col] == GameOfCells.Live) {
                	CountliveNeighbours++;
                }
            }
        }
        return CountliveNeighbours;
    }
	
	static char getNewCellState(char curState, int CountliveNeighbours) {

        char newState = curState;
        
        if (curState == GameOfCells.Live) {
        	// Any live cell with fewer than two 
            // live neighbours dies
            if (CountliveNeighbours < 2) 
                newState = GameOfCells.Dead;
            
            // Any live cell with two or three live   
            // neighbours lives on to the next generation.
            if (CountliveNeighbours == 2 || CountliveNeighbours == 3) 
                newState = GameOfCells.Live;
            
            // Any live cell with more than three live neighbours
            // dies, as if by overcrowding.
            if (CountliveNeighbours > 3) 
                newState = GameOfCells.Dead;
        }
        else if(curState == GameOfCells.Dead) {
        	if (CountliveNeighbours == 3) 
                newState = GameOfCells.Live;
        }
        else {
        	System.out.print("Invalid State of cell : "+newState);
            throw new IllegalArgumentException("Invalid State of Cell");
        }
			
        return newState;
    }
	
}
