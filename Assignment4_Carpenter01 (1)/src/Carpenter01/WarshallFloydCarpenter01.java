package Carpenter01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WarshallFloydCarpenter01 {
	
	public static class Main {

		public static void main(String[] args) throws FileNotFoundException {
			if (args.length == 0) {
				System.out.println("No file given");
				System.exit(1);
			} else
				new WarshallFloydCarpenter01(args[0]);
		}
	}
	
	
	private int [][] W;
	private int [][]pred;
	private int size;
	private Writer w;
	private String out="wfout.txt";
	
	
	WarshallFloydCarpenter01(String file) throws FileNotFoundException{
		shortestPath(new File(file));
	}
	
	public void constructWDAMatrix(File dataFile) throws FileNotFoundException {
		//Reads datFile and makes the weighted matrix of the digraph
		w=new PrintWriter(out);
		Scanner sc= new Scanner(dataFile);
		Pattern p= Pattern.compile("c ");
		while(sc.findInLine(p) != null)	sc.nextLine();	//Skips anylines that starts with c
		
		size=sc.nextInt();
		W=new int[size+1][size+1];//Input weighted adjacency matrix
		pred = new int [size+1][size+1];//Relationship matrix
		
		while(sc.hasNext()) {
			int r=sc.nextInt(), c=sc.nextInt(), weight=sc.nextInt();
			W[r][c]=weight;	//Set weight at edge (r,c)
			pred[r][c]=r;	//Set relation to 1 to show edge
		}
	}

	public void shortestPath(File dataFile) throws FileNotFoundException {
		//Computes the all-source shortest paths
		constructWDAMatrix(dataFile);
		write("Before:\n"+toString()+"\n");
		
		for(int k=1;k<W.length;k++) {	//Step 2
			for(int i=1;i<W[k].length;i++) {	//Step 2.1
				for(int j=1;j<W.length;j++)	{	//Step 2.2
					if(W[i][j]==0 && i!=j) {
						W[i][j]=W[i][k]+W[k][j];
					}
					else
						W[i][j]=Math.min(W[i][j], W[i][k]+W[k][j]);
				}
			}
		}
		
		write("After:\n"+toString()+"\n"+path(5,4));
	}
	
	public String path(int i, int j) {
		int r=i,c=j, temp ,w=0;
		String s=i+" ";
		while(r!=c && pred[r][c]!=0) {
			s+=pred[r][c]+" ";
			w+=W[r][c];
			temp=r;
			r=pred[r][c];
			c=temp;
		}
		if(w!=0)
			return s+j+" weight "+w;
		else
			return "No path from "+i+" to "+j;
	}
	
	public String toString() {
		String s="";
		for(int r=1; r<W.length;r++) {
			for(int c=1;c<W[r].length;c++)
				s+=W[r][c]+" ";
			s+="\n";
		}
		return s;
	}
	
	public void write(String s) {
		try{
            w.write(s);
            w.flush();
        } 
        catch(IOException e) {}
	}

}