class WordDictionary {

    class Node{
        String isEnd;
        Node[] children;
        Node(){
        children = new Node[128];
        }
    }
    Node start;
    public WordDictionary() {
         start = new Node();
    }
    
    public void addWord(String word) {
        Node head = start;
        for(int i = 0; i < word.length(); i++){
            int idx = word.charAt(i) -'a';
            if(head.children[idx] == null){
               head.children[idx] = new Node(); 
            }
            //System.out.println(Arrays.toString(head.children));
            head = head.children[idx];
            
        }
        head.isEnd = word;   
    }
    
    
    public boolean search(String word) {
        
        return search(word, 0, start);
    }
    public boolean search(String word, int ind, Node cur){
        if(cur == null) return false;
        
        if(ind == word.length()){
            return cur.isEnd != null; 
        }
        
        if(word.charAt(ind) == '.'){
            for(Node child : cur.children){
                if(search(word, ind + 1, child)) return true;
            }
        }else if(cur.children[word.charAt(ind)-'a'] != null){
            return search(word, ind + 1, cur.children[word.charAt(ind)-'a']);
        }
        return false;
    }
}
