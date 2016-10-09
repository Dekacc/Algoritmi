public class Main {	
	static long[] extendedEuclid(long a, long b) {
		//returns {x, y, d}, where ax + by = d, and d = gcd(a, b)
		if (b == 0) {
			long[] r = {1, 0, a};
			return r; 
		}
		long[] tmp = extendedEuclid(b, a % b);
		long[] r = {tmp[1], tmp[0] - (a / b) * tmp[1], tmp[2]};
		return r;
	}
	
	static long[] diophantine(long a, long b, long c){
		//solves ax + by = c, returns {x, y} where x and y are the smallest non-negative solutions
		long[] ee = extendedEuclid(a, b);
		if(c % ee[2] != 0){
			//c is not divisible by gcd(a, b), the equation has no solution!
			return null;
		}
		
		//multiply the array ee to get a real solution
		long mult = c/ee[2];
		ee[0] *= mult;
		ee[1] *= mult;
		ee[2] = Math.abs(ee[2]);
		
		//At this point, the general solutions are:
		//x = ee[0] + n * (b/ee[2])
		//y = ee[1] - n * (a/ee[2])
		
		//since we need non-negative x and y:
		//x >= 0    ==>    ee[0] + n * (b/ee[2]) >= 0    ==>    n >= (-ee[0]) / (b/ee[2])
		//y >= 0    ==>    ee[1] - n * (a/ee[2]) >= 0    ==>    n >= ee[1] / (a/ee[2])
		
		double lowBound = Long.MIN_VALUE;
		double upBound = Long.MAX_VALUE;
		//for x:
		if(b < 0){
			//equation is: n < (-ee[0]) / (b/ee[2])
			upBound = Math.min(upBound, ((double)-ee[0]) / (b/ee[2]));
		}
		else{
			//equation is: n >= (-ee[0]) / (b/ee[2])
			lowBound = Math.max(lowBound, ((double)-ee[0]) / (b/ee[2]));
		}
		
		//for y:
		if(a < 0){
			//equation is: n >= ee[1] / (a/ee[2])
			lowBound = Math.max(lowBound, ((double)ee[1]) / (a/ee[2]));
		}
		else{
			//equation is: n < ee[1] / (a/ee[2])
			upBound = Math.min(upBound, ((double)ee[1]) / (a/ee[2]));
		}
		
		int n = (int)Math.ceil(lowBound);
		if(lowBound >= upBound) return null;
		
		//x = ee[0] + n * (b/ee[2])
		//y = ee[1] - n * (a/ee[2])
		
		long[] r = {ee[0] + n * (b/ee[2]), ee[1] - n * (a/ee[2])};
		return r;
	}
	
	public static void main(String[] args) {
		long[] a = diophantine(2, 65, 2);
		System.out.println(a[0]);
		System.out.println(a[1]);
		
		//TODO: test
	}
}
