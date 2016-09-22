import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		//long startTime = System.currentTimeMillis();
		FastScanner sc = new FastScanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		UnionFind uf = new UnionFind(V);
		for(int i = 0; i<V; i++) uf.makeSet(i);
		
		Edge[] edges = new Edge[E];
		for(int i = 0; i<E; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			edges[i] = new Edge(a-1, b-1, c);
		}
		
		Arrays.sort(edges);
		
		int MSTlength = 0;
		int counter = 1;
		for(int i = 0; counter < V; i++){
			Edge e = edges[i];
			if(uf.findSet(e.x) != uf.findSet(e.y)){
				//if needed, add this edge to some set for the spanning tree
				uf.union(e.x, e.y);
				counter++;
				MSTlength += e.l;
			}
		}
		System.out.println(MSTlength);
        //long endTime   = System.currentTimeMillis();
        //long totalTime = endTime - startTime;
        //System.out.println(totalTime + " ms");
	}
}

class Edge implements Comparable<Edge>{
	int x;
	int y;
	int l;
	Edge(int xx, int yy, int ll){
		x = xx;
		y = yy;
		l = ll;
	}
	
	@Override
	public int compareTo(Edge e) {
		return l - e.l;
	}
}

class UnionFind{
	int[] parent;
	int[] rank;
	
	public UnionFind(int N){
		parent = new int[N];
		Arrays.fill(parent, -1);
		rank = new int[N];
	}
	
	public void makeSet(int i){
		parent[i] = i;
		rank[i] = 0;
	}
	
	public void union(int i, int j){
		link(findSet(i), findSet(j));
	}
	
	private void link(int i, int j){
		if(rank[i] > rank[j]){
			parent[j] = i;
		}
		else{
			parent[i] = j;
			if(rank[i] == rank[j]) rank[j]++;
		}
	}
	
	public int findSet(int i){
		if(parent[i] != i) parent[i] = findSet(parent[i]);
		return parent[i];
	}
}
