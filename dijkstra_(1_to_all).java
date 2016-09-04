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
			for(int i = 0; i < V; i++) ver[i] = new Vertex();
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
            		PriorityQueue<Pair> pq = new PriorityQueue<>();
            		pq.add(new Pair(start, 0));
            		while(!pq.isEmpty()){
                		Pair current = pq.remove();
                		if(current.distance <= ver[current.destination].shortest){
                    			ver[current.destination].shortest = current.distance;
                    			for(Edge e : ver[current.destination].edges){
                        			int newDist = current.distance + e.length;
                        			if(newDist < ver[e.dest].shortest){
                            				pq.add(new Pair(e.dest, newDist));
                            				ver[e.dest].shortest = newDist;
                        			}
                    			}
                		}
            		}

			//PRINT
			StringBuilder sb = new StringBuilder();
            		for(int i = 0; i < V; i++){
                		if(i == start) continue;
                		int length = ver[i].shortest;
                		if(length == Integer.MAX_VALUE) sb.append("-1 "); //no path
                		else sb.append(length + " ");
            		}
            		System.out.println(sb);
		}
	}
}

class Pair implements Comparable<Pair> {
    	int destination;
    	int distance;
    	Pair(int dest, int dist){
        	destination = dest;
        	distance = dist;
    	}

    	@Override
    	public int compareTo(Pair p) {
        	return distance - p.distance;
	}
}

class Vertex {
	int shortest;
	ArrayList<Edge> edges;

	Vertex() {
		shortest = Integer.MAX_VALUE;
		edges = new ArrayList<>();
	}
}

class Edge {
	int dest;
	int length;

	Edge(int d, int l) {
		dest = d;
		length = l;
	}
}
