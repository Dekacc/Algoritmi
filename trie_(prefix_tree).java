import java.util.HashMap;

public class Main {
	  public static void main(String[] args){
	    
		
	  }
}

class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean terminal;
 
    public TrieNode() {
    	  children = new HashMap<Character, TrieNode>();
    	  terminal = false;
    }
}

class Trie {
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode();
    }
 
    // Adds a word into the trie.
    public void add(String word) {
        HashMap<Character, TrieNode> children = root.children;
 
        for(int i = 0, len = word.length(); i < len; i++){
            char c = word.charAt(i);
 
            TrieNode t;
            if(children.containsKey(c)){
            	  t = children.get(c);
            }
            else{
                t = new TrieNode();
                children.put(c, t);
            }
 
            children = t.children;
            if(i == len - 1) t.terminal = true;		//set terminal node
        }
    }
 
    // Returns if the word is in the trie.
    public boolean contains(String word) {
        TrieNode t = searchNode(word);
        return (t != null && t.terminal);
    }
 
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean containsPrefix(String prefix) {
        return (searchNode(prefix) != null);
    }
 
    public TrieNode searchNode(String str){
        HashMap<Character, TrieNode> children = root.children; 
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }
 
        return t;
    }
}
