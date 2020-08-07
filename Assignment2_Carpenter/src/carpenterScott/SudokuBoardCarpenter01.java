package carpenterScott;

public class SudokuBoardCarpenter01 {

	private int [] boardCells;
	private int boxWidth, boxHeight;
	
	public SudokuBoardCarpenter01(int bw, int bh) {	
		boxWidth=bw;
		boxHeight=bh;
		
		boardCells=new int [(int) (Math.pow(boxHeight,2)*Math.pow(boxWidth,2))];
	}
	
	public int findRow(int slot) {
		//Takes a slot from the board, finds its row
		int loc,found=0;
		for(int r=0;r<Math.pow(boxHeight, 2);r++) {
			for(int c=0;c<Math.pow(boxWidth,2); c++) {
				loc=getSlot(r,c);//Attempts to find the row by comparison
				if(loc==slot)
					found= r;
			}
		}
		return found;
	}
	
	public int findCol(int slot) {
		//Takes a slot from the board, finds its row
			int loc,found=0;
			for(int r=0;r<Math.pow(boxHeight, 2);r++) {
				for(int c=0;c<Math.pow(boxWidth,2); c++) {
					loc=getSlot(r,c);//Attempts to find the col by comparison
					if(loc==slot)
						found= c;
				}
			}
			return found;
	}
	
	public int getValue(int slot) {
		return boardCells[slot];	//Returns the value of the slot
	}
	
	public int  box(int slot) {
		int row=findRow(slot),col=findCol(slot);
		row/=boxHeight;
		col/=boxWidth;
		if((row>=0 && row<3) && (col>=0 && col<3))
			return 0;
		else if((row>=0 && row<3) && (col>=3 && col<6))
			return 1;
		else if((row>=0 && row<3) && (col>=6 && col<9))
			return 2;
		else if((row>=3 && row<6) && (col>=0 && col<3))
			return 3;
		else if((row>=3 && row<6) && (col>=3 && col<6))
			return 4;
		else if((row>=3 && row<6) && (col>=6 && col<9))
			return 5;
		else if((row>=6 && row<9) && (col>=0 && col<3))
			return 6;
		else if((row>=6 && row<9) && (col>=3 && col<6))
			return 7;
		else
			return 8;
			
	}
	
	public int getSlot(int row, int col) {
		return (row*(boxWidth*boxWidth))+col;
	}
	
	public int getBoxWidth() {
		return boxWidth;	//Returns the width of the box
	}
	
	public int getBoxHeight() {
		return boxHeight;	//Returns the height of the box
	}
	
	public int boardSize() {
		return boxWidth*boxHeight;	//Returns the size of the board
	}
	
	public int numberOfCells() {
		return (boxWidth*boxWidth)+(boxHeight*boxHeight);	//Returns the total amounts of cells in the whole board.
	}
	
	public String toString() {
		String r="";
		int loc;
		for(int row=0;row<Math.pow(boxHeight, 2);row++) {
			for(int c=0;c<Math.pow(boxWidth,2); c++) {
				loc=getSlot(row,c);
				r+=boardCells[loc]+"\t";
			}
			r+="\n";
		}
		return r;
	}
	
	public void set(int i, int j, int k) {
		boardCells[getSlot(i,j)]=k;
	}
	
}
