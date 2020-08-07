package Carpenter01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument.Iterator;

public class TopoSortCarpenter01 {

	private Scanner sc;
	private Queue <Edge> q;
	private String output="TopoSortOutput.txt";
	private Writer writer;
	private Stack <Integer> s;
	private int inDegree[];	
	
	public static class main{
		
		public static void main(String [] args) throws FileNotFoundException {
			if(args.length==0) {
				System.out.println("No file given");
				System.exit(1);
			}
			else
				new TopoSortCarpenter01(args[0]);
		}
	}
	
	public TopoSortCarpenter01(String file) throws FileNotFoundException {
		createDiGraph(new File(file));
		topoSort();
	}

	private void createDiGraph(File dataFile) throws FileNotFoundException {
		sc= new Scanner(dataFile);
		Pattern p= Pattern.compile("c ");
		while(sc.findInLine(p) != null)	sc.nextLine();	//Skips any lines that starts with c
		
		inDegree=new int[sc.nextInt()+1];
		q= new LinkedList<Edge>();
		writer=new PrintWriter(output);
		while(sc.hasNext()) {
			int n1=sc.nextInt(), n2=sc.nextInt();
			inDegree[n2]++;
			if(!q.contains(new Edge(n1,n2)))
				q.add(new Edge(n1, n2));
		}
	}

	public void topoSort() {
		//Pre: Digraph G, with n verticies, indegree of nodes
		//Post: Sorted in topological order, or that G is cyclical
		s=new Stack<Integer>();	//Step 1
		for(int u=1;u<inDegree.length;u++) { 	//Step 2
			if(inDegree[u]==0) {
				s.push(u);
				inDegree[u]=-1;
			}
		}
		
		int i=1;	//Step 3
		while(!s.isEmpty()) {	//Step 4
			int u=s.pop();
			write(u+", ");
			i++;
			for(Edge v:q) {
				if(v.node1==u)
					inDegree[v.getNode2()]--;
				if(inDegree[v.getNode2()]==0) {
					s.push(v.getNode2());
					inDegree[v.getNode2()]=-1;
				}
			}
		}
		if(i>inDegree.length)
			write("Cycllical");
		
	}
	
	private void write(String s) {
        try{
            writer.write(s);
            writer.flush();
        } 
        catch(IOException e) {}
	}
	
}
