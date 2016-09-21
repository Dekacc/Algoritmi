public class Costumes {
    static Par[] ver;
    static int[][] memoizacija;
    static int[][] distances;

    private static int distance(Par p1, Par p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private static int solve(int bit, int pos){
        if(memoizacija[bit][pos] != -1){
            //System.out.println("SKRAT");
            return memoizacija[bit][pos];
        }
        else{
            if(bit == (1 << ver.length) - 1){
                int dist = distances[0][pos];//distance(ver[0], ver[current]);
                memoizacija[bit][pos] = dist;
                return dist;
            }
            else{
                int min = Integer.MAX_VALUE;
                for(int i = 0; i < ver.length; i++){
                    if((bit & (1 << i)) == 0){
                        int newbit = bit | (1 << i);
                        int newpos = ver.length - 1 - i;
                        min = Math.min(min,  distances[newpos][pos] + solve(newbit, newpos));
                    }
                }
                memoizacija[bit][pos] = min;
                return min;
            }
        }
    }

    public static int minimumPath(int[] x, int[] y) {
        ver = new Par[x.length];
        int S = 1 << x.length;
        memoizacija = new int[S][x.length];

        for(int i = 0; i < x.length; i++){
            ver[i] = new Par(x[i], y[i]);
        }

        distances = new int[x.length][y.length];
        for(int i = 0; i<x.length; i++){
            for(int j = 0; j<x.length; j++){
                distances[i][j] = distance(ver[i], ver[j]);
            }
        }

        for(int i = 0; i < S; i++){
            for(int j = 0; j<x.length; j++){
                memoizacija[i][j] = -1;
            }
        }

        return solve(0, 0);
    }

    public static void main(String[] args){
        //int x[] = {166,866,171,523,614,533,728,262,77,29,730,828,273,704,959,149,630,928};
        //int y[] = {19,981,324,868,493,564,834,559,23,168,674,894,538,436,251,240,412,994};
        //System.out.println(minimumPath(x, y));
    }
}

class Par{
    int x;
    int y;
    Par(int xx, int yy){
        x = xx;
        y = yy;
    }
}
