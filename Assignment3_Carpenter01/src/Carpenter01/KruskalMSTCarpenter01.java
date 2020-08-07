package Carpenter01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KruskalMSTCarpenter01 {
	
	private PriorityQueue <Edge> pq;
	private String output="kruskaloutput.txt";
	private Writer writer;
	private Scanner sc;
	private LinkedList <Edge> mstEdges;
	private UnionFind uf;
	int weight=0;
	
	public KruskalMSTCarpenter01(String f) throws FileNotFoundException {
		findMST(new File(f));
	}
	
	public void findMST(File dataFile) throws FileNotFoundException {
		initializeHeap(dataFile);	//Reads in the file
		
		mstEdges= new LinkedList<Edge>();
		this.writer = new PrintWriter(output);
		Edge edge;
		while(mstEdges.size()<(uf.getSize()-1)){
			edge=pq.remove();
			if(uf.find(edge.getNode1())!=uf.find(edge.getNode2())) {	//If the two nodes do not have the same parent
				mstEdges.add(edge);
				weight+=edge.getWeight();
				uf.union(edge.getNode1(), edge.getNode2());
			}
		}
		write(toString());
	}
	
	public void initializeHeap(File dataFile) throws FileNotFoundException {
		pq=new PriorityQueue <Edge>();
		sc=new Scanner(dataFile);
		
		uf=new UnionFind(sc.nextInt());
		
		while(sc.hasNext()){
			pq.offer(new Edge(sc.nextInt(),sc.nextInt(),sc.nextInt()));
		}
	}
	
	public void write(String s) {
        try{
            writer.write(s);
            writer.flush();
        } 
        catch(IOException e) {}
	}
	
	public String toString() {
		return mstEdges.toString()+" Weight: "+weight;
	}
	
}
