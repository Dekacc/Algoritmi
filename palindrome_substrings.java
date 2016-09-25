public class Main {
	
	//Manacher's algorithm for finding all palindromes in a string
	//
	//Time complexity: O(N)
	//
	//Returns: int array of length 2N-1 where even-index elements represent the length of all odd-length
	//palindromes centered at character with index i/2, and odd-index elements represent the length of all
	//even-length palindromes with center between characters with index floor(i/2) and ceil(i/2).
	public static int[] manacher(String s){
		//Filling a char array on which we will make the comparissons
		int length = s.length();
		char[] chars = new char[2 * length + 3];
		chars[0] = '$'; //sentinel character representing the start of the array (useful for avoiding 'out of bounds' checks)
		chars[1] = '#'; //hashes represent the centers of even-length palindromes
		int ind = 2;
		for(char c : s.toCharArray()){
			chars[ind] = c;
			chars[ind+1] = '#';
			ind += 2;
		}
		chars[ind] = '@'; //sentinel character representing the end of the array
		
		//Manacher's algorithm starts here:
		int p_len = 2 * length - 1;
		int[] p = new int[p_len]; //the return array
		int c = 0; //the index of the center of the 'main palindrome'
		int r = 0; //the right bound of the 'main palindrome'
		
		for(int i = 0; i < p_len; i++){
			int mirror = 2 * c - i; //the mirror index of the current character
			
			if(i < r) p[i] = Math.min(r - i, p[mirror]);
			
			while(chars[i + (1 + p[i]) + 2] == chars[i - (1 + p[i]) + 2]) p[i]++;
			
			if(i + p[i] > r){
				c = i;
				r = i + p[i];
			}
		}
		
		return p;
	}
	
	public static void main(String[] args) {
		String s = "ABBABA";
		int[] arr = manacher(s);
		
		//printing all palindromes
		for(int i = 0; i<arr.length; i++){
			if(i%2 == 0){
				//odd length palindromes
				int center = i/2;
				int lim = arr[i] / 2 + 1;
				for(int j = 1; j <= lim; j++) System.out.println(s.substring(center - j + 1, center + j));
			}
			else{
				//even length palindromes
				int leftcenter = i/2;
				int rightcenter = leftcenter + 1;
				int lim = arr[i] / 2;
				for(int j = 1; j<= lim; j++) System.out.println(s.substring(leftcenter - j + 1, rightcenter + j));
			}
		}
	}
}
