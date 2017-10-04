class Fenwick{
	int size;
	int[] table;
	
	Fenwick(int size){
		table = new int[size + 1];	//1-indexed
		this.size = size;
	}
	
	//update position i by delta
	void update(int i, int delta){
		while(i <= size){
			table[i] += delta;
			i += i&-i;    //(i&-i) returns the least-significant 1 bit of i
		}
	}
	
	int query(int i){
		int sum = 0;
		while(i > 0){
			sum += table[i];
			i -= i&-i;  //an alternative for (i&-i) is Integer.lowestOneBit(i)
		}
		return sum;
	}
}
