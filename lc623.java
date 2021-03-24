//https://leetcode.com/problems/add-one-row-to-tree/

class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        Queue<TreeNode> q = new LinkedList<>();
        if(d == 1) {
           // System.out.println("here");
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        q.offer(root);
        int count = 0;
        while(!q.isEmpty() && count <= d){
            count++;
            int size = q.size();
            for(int i = 0; i < size; i++){
                
                TreeNode node = q.peek();
                if(count == d - 1){
                    if(node.left != null){
                        
                        TreeNode leftNode = node.left;
                        node.left = new TreeNode(v);
                        node = node.left;
                        node.left = leftNode;
                    
                    }
                    node = q.peek();
                    if(node.right != null){
                        TreeNode rightNode = node.right;
                        node.right = new TreeNode(v);
                        node = node.right;
                        node.right = rightNode;
                        
                    }
                    node = q.peek();
                    if(node.left == null){
                        node.left = new TreeNode(v);
                    }
                    if(node.right == null){
                        node.right = new TreeNode(v);
                    }
                }else{
                    if(node.left != null){
                        q.offer(node.left);
                    
                    }
                    if(node.right != null){
                         q.offer(node.right);
                        
                    }
                }
                q.poll();
            }   
        }
        return root;
    }
}
