import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args){
    	FastScanner sc = new FastScanner(System.in); 
        int T = sc.nextInt();
        for(int itt = 1; itt <= T; itt++){
            //INPUT STARTS
            int V = sc.nextInt();
            int E = sc.nextInt();
            Vertex[] ver = new Vertex[V];
            for(int i = 0; i < V; i++) ver[i] = new Vertex();
            for(int i = 0; i < E; i++){
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1;
                int c = sc.nextInt();
                ver[a].edges.add(new Edge(b, c));
                ver[b].edges.add(new Edge(a, c));
            }
            int start = sc.nextInt() - 1;
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
                            ver[e.dest].predecessor = current.destination; //if the shortest path is required
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

class Pair implements Comparable<Pair>{
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

class Vertex{
    int shortest;
    int predecessor;
    ArrayList<Edge> edges;
    Vertex(){
        shortest = Integer.MAX_VALUE;
        predecessor = -1;
        edges = new ArrayList<>();
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
