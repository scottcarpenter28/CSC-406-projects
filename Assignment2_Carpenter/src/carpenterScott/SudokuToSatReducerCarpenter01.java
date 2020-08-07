package carpenterScott;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class SudokuToSatReducerCarpenter01 {
	static final String tempFileName = "out.cnf";
	private final Writer writer;
	int cnf=11988;
	File file;
	PrintStream out;
	TimerCarpenter01 t1;
	SudokuBoardCarpenter01 s;
	
	public SudokuToSatReducerCarpenter01(File f) throws FileNotFoundException {
		file=f;
		this.writer = new PrintWriter(tempFileName);
		t1=new TimerCarpenter01();
		
		createBoard();
		reduceBoard();
	}
	
	public void createBoard() throws FileNotFoundException {
		//Reads the sudoku board then sends it to the sudokuBoard class
		Scanner sc=new Scanner(file);
		while(!sc.hasNextInt()) sc.nextLine();
		s=new SudokuBoardCarpenter01(sc.nextInt(),sc.nextInt());
		for(int i=0;i<s.boardSize();i++) {
			for(int j=0;j<s.boardSize();j++) {
				s.set(i, j, sc.nextInt());
			}
		}
	}
	
	public void reduceBoard() {
		t1.start();
		
		for(int i=0;i<((int)Math.pow(s.boardSize(), 2));i++){
			if(s.getValue(i)!=0)
				cnf++;
		}
		write("p cnf 729 "+cnf+"\n");//File header
		
		//For rows and cols only
		for(int i=0;i<s.boardSize();i++) {				//0-8 rows
				for(int k=1; k<=s.boardSize();k++) {	//1-9 nums
					atLeastOneInRow(i,k);
					atLeastOneInCol(i,k);
				}
		}
		
		
		//For boxes only
		for(int i=0;i<s.boardSize();i+=3) {				//row 0, 3 or 6
			for(int j=0;j<s.boardSize();j+=3) {			//col 0, 3 or 6
				for(int k=1;k<=s.boardSize();k++) {		//1-9 nums
					atLeastOneInBox(i,j,k);
				}
			}
		}
		
		//For every box
		for(int i=0;i<s.boardSize();i++) {			//0-8 rows
			for(int j=0;j<s.boardSize();j++) {		//0-8 cols
				atLeastOneVal(i,j);
			}
		}
		
		
		
		for(int i=0;i<((int)Math.pow(s.boardSize(), 2));i++){
			if(s.getValue(i)!=0)
				write(encode(s.findRow(i),s.findCol(i),s.getValue(i))+" 0\n");
		}
		
		t1.stop();
		System.out.println("Run time:"+t1.getDuration()+"ms");
	}
	
	public void atLeastOneInRow(int i, int k) {
		for(int j=0;j<s.boardSize();j++) {	//0-8 col
			write(encode(i,j,k)+" ");
		}
		write("0\n");
		atMostOneInRow(i,k);
	}
	
	public void atMostOneInRow(int i,int k) {
		for(int j=0;j<s.boardSize();j++) {			//0-8 cols
			for(int c=s.boardSize()-1;c>j;c--) {	//8-0 cols
				write((encode(i,j,k)*-1)+" ");
				write((encode(i,c,k)*-1)+" 0\n");
			}
		}
	}
	
	public void atLeastOneInCol(int j, int k) {
		for(int i=0;i<s.boardSize();i++) {	//0-8 rows
			write(encode(i,j,k)+" ");
		}
		write("0\n");
		atMostOneInCol(j,k);
	}
	
	public void atMostOneInCol(int j, int k) {
		for(int i=0;i<s.boardSize();i++) {			//0-8 rows
			for(int r=s.boardSize()-1;r>i;r--) {	//0-8 rows
				write((encode(i,j,k)*-1)+" ");
				write((encode(r,j,k)*-1)+" 0\n");
			}
		}
	}
	
	public void atLeastOneInBox(int i, int j, int k) {
		for(int r=i;r<i+3;r++) {		//0-2, 3-5, or 6-8
			for(int c=j;c<j+3;c++) {	//0-2, 3-5, or 6-8
				write(encode(r,c,k)+" ");
			}
		}
		write("0\n");
		atMostOneInBox(i,j,k);
	}
	
	public void atMostOneInBox(int i, int j, int k) {
		for(int r=i;r<i+3;r++) {		//first box 0-2, 3-5, or 6-8
			for(int c=j;c<j+3;c++) {	//first box 0-2, 3-5, or 6-8
				for(int r2=i+3;r2>r;r2--) {		//second box 8-6, 5-3 or 2-0
					for(int c2=j+3;c2>c;c2--) {	//second box 8-6, 5-3 or 2-0
						write((encode(r,c,k)*-1)+" ");
						write((encode(r2,c2,k)*-1)+" 0\n");
					}
				}	
			}
		}
	}

	public void atLeastOneVal(int i, int j) {
		for(int k=1;k<=s.boardSize();k++) {	//Nums 1-9
			write(encode(i,j,k)+" ");
		}
		write("0\n");
		atMostOneVal(i,j);
	}
	
	public void atMostOneVal(int i, int j) {
		for(int x=1;x<=s.boardSize();x++) {		//Nums 1-9
			for(int y=s.boardSize();y>x;y--) {	//Nums 1-9
				write((encode(i,j,x)*-1)+" ");
				write((encode(i,j,y)*-1)+" 0\n");
			}
		}
	}
	
	public void write(String s) {
        try{
            writer.write(s);
            writer.flush();
        } 
        catch(IOException e) {}
	}
	
	public int encode(int i,int j, int k) {
		//Takes a triple and encodes it using the formula (i*(n^2))+(j*n)+k
		return i*((int)Math.pow(s.boardSize(), 2))+j*(s.boardSize())+k;
	}

}
	