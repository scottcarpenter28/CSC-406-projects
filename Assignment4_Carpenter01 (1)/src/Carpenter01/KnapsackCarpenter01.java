package Carpenter01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.regex.Pattern;

public class KnapsackCarpenter01 {
	
	private Scanner sc;
	private int setSize, sackSize;
	private int [] weightArray;
	private int [] valueArray;
	
	private Writer w;
	private String outputFile="knapOutput.txt";
	int [][]M;

	public static class Main {

		public static void main(String[] args) throws FileNotFoundException {
			if (args.length == 0) {
				System.out.println("No file given");
				System.exit(1);
			} else
				new KnapsackCarpenter01(args[0]);
		}
	}
	
	public KnapsackCarpenter01(String f) throws FileNotFoundException {
		knapSackSolution(new File(f));
	}

	private void knapSackSolution(File dataFile) throws FileNotFoundException {
	   /*	The cardinality of the set of elements is setSize
		*	Weight and value of each element i-weightArray[i],valueArray[i]
		*	Maximum sack size is sackSize
		*/
		createKnapsackArray(dataFile);
		
		M= new int[setSize+1][sackSize+1];	//Step 1
		for(int k=0; k<sackSize;k++) M[0][k]=0;		//Step 2
		
		for(int i=1; i<setSize+1;i++) {				//Step 3
			for(int j=0; j<=sackSize;j++) {			//Step 3.1
				if(weightArray[i-1]<=j) {
					M[i][j]=Math.max(M[i-1][j], weightArray[i-1]+ weightArray[i-1]* (j-weightArray[i-1]));
				}
				else
					M[i][j]=M[i-1][j];
			}//End of j
		}//End of i
		
		this.w=new PrintWriter(outputFile);
		write(toString());
	}

	private void createKnapsackArray(File dataFile) throws FileNotFoundException {
		sc=new Scanner(dataFile);
		Pattern p= Pattern.compile("c ");
		while(sc.findInLine(p) != null)	sc.nextLine();	//Skips any lines that starts with c
		
		setSize=sc.nextInt();
		sackSize=sc.nextInt();
		
		weightArray= new int [setSize+1];
		valueArray= new int [setSize+1];
		
		for(int i=1;i<weightArray.length;i++)//Reading in the weights
			weightArray[i]=sc.nextInt();
		
		for(int i=1;i<valueArray.length;i++)//Reading in the keys
			valueArray[i]=sc.nextInt();
	}
	
	public void write(String s) {
		try{
            w.write(s);
            w.flush();
        } 
        catch(IOException e) {}
	}
	
	public String toString() {
		String s="";
		s+= weightArray.length+"\t"+(M[0].length-1)+"\n";
		
		
		for(int i=1; i<weightArray.length;i++) 
			s+=weightArray[i]+"\t";
		s+="\n";
		
		for(int i=1; i<valueArray.length;i++) 
			s+=valueArray[i]+"\t";
		s+="\n";
		
		s+="Max: "+M[M.length-1][M[0].length-1]+"\n";
		
		s+="Optimal set: {"+findSet()+"}";
		
		return s;
	}
	
	public String findSet() {
		String s="";
		int temp=M[M.length-1][M.length-1], col=M.length-1, row=M.length-1;
		while(col!=0 && row!=0){
			System.out.println(M[row][col]);
			if(M[row][col]!=temp)
				s+=weightArray[col]+" ";
			else {
				row--;
				temp=M[row][col];
			}
			col--;
		}
		return s;
	}

}
