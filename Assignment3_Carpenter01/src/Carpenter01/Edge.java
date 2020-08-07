package Carpenter01;

public class Edge implements Comparable<Edge>{

	int node1;
	int node2;
	int weight;
	
	public Edge(int n1, int n2, int w) {
		this.node1=n1;
		this.node2=n2;
		this.weight=w;
	}
	
	public int getNode1() {
		return node1;
	}
	
	public int getNode2() {
		return node2;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String toString() {
		return node1+" "+node2+" "+weight+"\n";
	}

	public int compareTo(Edge e) {
		//If this weight is greater than another nodes weight, return 1, else if weight is less than another nodes weight return -1, else return 0
		return weight>e.weight ? 1: (weight<e.getWeight() ? -1: 0);
	}

}
