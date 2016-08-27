import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int itt = 1; itt <= T; itt++){
			//INPUT STARTS
			String line1 = br.readLine();
			String[] split1 = line1.split(" ");
			int V = Integer.parseInt(split1[0]);
			int E = Integer.parseInt(split1[1]);
			Vertex[] ver = new Vertex[V];
			for(int i = 0; i < V; i++) ver[i] = new Vertex(i);
			for(int i = 0; i < E; i++){
				String line = br.readLine();
				String[] split = line.split(" ");
				int a = Integer.parseInt(split[0]) - 1;
				int b = Integer.parseInt(split[1]) - 1;
				int c = Integer.parseInt(split[2]);
				ver[a].edges.add(new Edge(b, c));
				ver[b].edges.add(new Edge(a, c));
			}
			int start = Integer.parseInt(br.readLine()) - 1;
			//INPUT ENDS
			
			//DIJKSTRA
			Vertex current = ver[start];
			current.shortest = 0;
			current.visited = true;
			int visited = 1;
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			while(visited < V){
				for(Edge e : current.edges){
					if(ver[e.dest].visited) continue;
					int length = current.shortest + e.length;
					if(length < ver[e.dest].shortest){
						ver[e.dest].shortest = length;
						//update the placement in prioQueue
						pq.remove(ver[e.dest]);
						pq.add(ver[e.dest]);
					}
				}
				if(pq.isEmpty()) break;
				current = pq.remove();
				if(current.shortest == Integer.MAX_VALUE) break;
				current.visited = true;
				visited++;
			}
			//PRINT
			for(int i = 0; i < V; i++){
				if(i == start) continue;
				int length = ver[i].shortest;
				if(length == Integer.MAX_VALUE) System.out.print("-1 "); //no path
				else System.out.print(length + " ");
			}
			System.out.println();
		}
	}
}

class Vertex implements Comparable<Vertex>{
	int index;
	boolean visited;
	int shortest;
	ArrayList<Edge> edges;
	Vertex(int i){
		visited = false;
		index = i;
		shortest = Integer.MAX_VALUE;
		edges = new ArrayList<>();
	}
	@Override
	public int compareTo(Vertex o) {
		return shortest - o.shortest;
	}
	@Override
	public int hashCode() {
		return index;
	}
	
	@Override
	public boolean equals(Object obj) {
		return index == ((Vertex) obj).index;
	}
}

class Edge{
	int dest;
	int length;
	Edge(int d, int l){
		dest = d;
		length = l;
	}
}
