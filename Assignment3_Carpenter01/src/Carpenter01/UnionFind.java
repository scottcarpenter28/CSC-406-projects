package Carpenter01;

public class UnionFind {
	
	int [] a;
	
	public UnionFind(int s) {
		a=new int [s+1];
		makeSet(s);
	}
	
	public void makeSet(int x) {
		for(int i=1; i<a.length;i++)
			a[i]=-1;
	}
	
	public int find(int x) {
		int n = x, m;

		while (a[n] > 0) //Finds the parent node
			n = a[n];

		while (x != n) {	//While you are not at the parent node
			m = a[x];		//Give m the next node up
			a[x] = n;		//Set the parent node of x to the one found
			x = m;			//Change given node to next one up
		}
		return x;
	}
	
	public void union(int x, int y) {
		int p1=find(x);
		int p2=find(y);
		
		if(a[p1]<a[p2]) {	//Ensures that the smallest tree is added to p1
			a[p1]+=a[p2];
			a[p2]=p1;
		}
		
		else {
			a[p2]+=a[p1];
			a[p1]=p2;
		}
	}
	
	public int getSize() {
		return a.length-1;
	}
}
