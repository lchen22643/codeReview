/*
https://leetcode.com/problems/design-add-and-search-words-data-structure/
211. Design Add and Search Words Data Structure
Medium

2836

121

Add to List

Share
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


Constraints:

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.


*/

class WordDictionary {

    class Node{  // you can call it TrieNode
        String isEnd;  // is* should be a boolean type, and you don't have to save the word string here, you only need a boolean value.
        Node[] children;
        Node(){
        children = new Node[128];  // indentation. hashmap is better because when size is small, only part of 128 chars are used.
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

// List a few unit test cases after you finish the implementation.