package Carpenter01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.util.Scanner;

public class ObstCarpenter40601 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		if(args.length==0) {
			System.out.print("No file given");
			System.exit(1);
		}
		initialize(args[0]);
	}

	
	static private Scanner sc;
	static int n;
	static int []k;
	static double [] p;
	static double [][] s;
	static String output="";

	private static void initialize(String file) throws FileNotFoundException {
		sc=new Scanner(new File(file));
		while(sc.hasNextLine()) {
			n=sc.nextInt();
			
			k=new int[n+1];
			for(int i=1;i<k.length;i++) 
				k[i]=sc.nextInt();
			
			p=new double[k.length];
			for(int i=1;i<p.length;i++) 
				p[i]=sc.nextInt();
			
			s=new double[k.length][k.length];
		}
		
		optimize();
		print();
	}
	
	public static void optimize() {
		//n is the number of keys
		//p is the probabilities
		
		//step 1
		for(int i=1; i<n;i++)
			s[i][i]=p[i];
		
		//step 2
		for(int d=1;d<n-1;d++) {
			//step 3
			for(int i=1;i<(n-d);i++) {
				int j=i+d;
				int minK=0;
				double tempMin=Integer.MAX_VALUE;
				
				//step 4
				for(int k=i;k<=j;k++) {
					double c=s[i][k-1]+s[k+1][j];
					if(tempMin>c) {
						tempMin=c;
						minK=k;
					}
				}//End of k loop
				
				//Step 5
				s[i][j]=tempMin+probSums(i,j);
				s[j][i]=j;
			}//End of loop i
		}//End of loop d
		
	}

	private static int probSums(int start, int end) {
		int sum=0;
		for(int i=start;i<=end;i++) 
			sum+=p[i];
		return sum;
	}
	
	private static void print() {
		for(int r=0;r<s.length; r++) {
			for(int c=0;c<s[0].length;c++) {
				System.out.print(s[r][c]+"\t");
			}
			System.out.println();
		}
	}
}
