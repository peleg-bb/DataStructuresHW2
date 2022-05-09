

import java.util.NoSuchElementException;

public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node> {
    private Stack stack;
    private Stack redoStack;
    private BacktrackingBST.Node root = null;
    private int backtracks = 0;


    /*
Left to resolve
 Issue 3 -
    - Backtracking the deletion of a second consecutive root deletes one of the roots -
    - See line 176 for a further explanation suggestion.
 Issue 4 -
    - backtracking the deletion of a root can insert the old root as a leaf (and not where it was) - line 140


 Left to implement
    - Make sure we return nulls and handle all special cases
    - It appears that retrack functions as expected.
*/


    // Do not change the constructor's signature
    public BacktrackingBST(Stack stack, Stack redoStack) {
        this.stack = stack;
        this.redoStack = redoStack;
    }

    public Node getRoot() {
        if (root == null) {
            throw new NoSuchElementException("empty tree has no root");
        }
        return root;
    }

    public Node search(int k) {
        if (root != null) {
            return root.search(k);
        }
        return null;
    }

    public void insert(Node node) {
        backtracks=0;
        stack.push(node);
        if (root != null) {
            root.insert(node);
        }
        else {
            root = node;
        }
    }

    public void delete(Node node) {
        backtracks=0;
        if(node.left!= null && node.right != null){ // if the removed node has 2 children - add its successor.
            stack.push(node.right.minimum());
            // We need a second pop - the minimum node will be inserted
            // in place of the deleted item
        }
        stack.push(node);
        if(root != null){
            root = root.delete(node);
        }
    }

    public Node minimum() {
        if (root!= null) {
            return root.minimum();
        }
        return null;
    }

    public Node maximum() {
        if (root!= null) {
            return root.maximum();
        }
        return null;
    }

    public Node successor(Node node) {
        // Does it return null if node has no successor??
        // I believe it does - needs to be confirmed
        if (node.right != null) {
            return node.right.minimum();
        }
        Node y = node.parent;
        while (y != null && node == y.right) {
            node = y;
            y = y.parent;
        }
        return y;

    }

    public Node predecessor(Node node) {
        // Does it return null if node has no predecessor??
        // I believe it does - needs to be confirmed
        if (node.left != null) {
            return node.left.maximum();
        } else {
            Node y = node.parent;
            while (y != null && node == y.left) {
                node = y;
                y = y.parent;
            }
            return y;
        }
    }

    @Override
    public void backtrack() {
        backtracks++;
        if(!stack.isEmpty()) {
            Node popped = (Node) this.stack.pop();
            this.redoStack.push(popped);
            if ( this.search(popped.getKey()) != null ) { // If an item was inserted - delete it through its parent


                    popped.parent.delete(popped);
            }
            else { // If an item was deleted - insert the item
                if (popped.left != null && popped.right != null) { // Node has 2 children - Case 3 of delete
                    Node poppedSecond = (Node) stack.pop();
                    // We need a second pop - the minimum node will be inserted
                    // in place of the deleted item
                    // poppedSecond.parent.left = poppedSecond; -Probably redundant and therefore commented out
                    if (popped.wasLeftChild()) { //checking if is left child
                        popped.parent.left = popped;
                    }
                    else if (popped.wasRightChild()){ //is right child
                        popped.parent.right = popped;
                    }
                    else { // root was deleted
                        root = popped;
                        root.left.parent = popped;
                        root.right.parent = popped;
                    }
                    poppedSecond.right = null;
                    poppedSecond.left = null;
                    popped.right.insert(poppedSecond); // Issue 4 - insert results in insertion as a leaf
                }

                else if (popped.right != null) { // Case 2 of delete - node has right child
                    if (popped.wasLeftChild()) { //checking if it is a left child
                        popped.parent.left = popped; // I am now the left child of my parent
                        popped.right.parent = popped; // I am now the parent of my right child
                    }

                    else if (popped.wasRightChild()) { // checking if node is rightChild
                        popped.parent.right = popped; // I am now the right child of my parent
                        popped.right.parent = popped; // I am now the parent of my right child
                    }

                    else { // checking if popped was root
                        root = popped;
                        root.right.parent = root;
                    }
                }

                else if (popped.left != null) { // Case 2 of delete - node has left child
                    if(popped.wasLeftChild()){ //checking if it is a left child
                        popped.parent.left = popped;// I am now the right child of my parent
                        popped.left.parent = popped;// I am now the parent of my right child
                    }
                    else if (popped.wasRightChild()){ // is rightChild
                        popped.parent.right = popped; // I am now the right child of my parent
                        popped.left.parent = popped; // I am now the parent of my right child
                    }
                    else { // reinserting as root
                        // Probably no longer relevant
                        //popped.right = null;
                        //popped.left = null;
                        //this.insert(popped);


                        // This is the root (no pun intended) of issue 3 - we need to move the current root
                        // to the left or to the right
                        // Do we need to write a special case - check where to enter?
                        // Seems dumb...
                        root = popped;
                        root.left.parent = root;
                    }
                }

                else { // Case 1 - node has no children
                    if(popped.wasLeftChild()){ //checking if it is a left child
                        popped.parent.left = popped;// I am now the right child of my parent
                    }
                    else if (popped.wasRightChild()){ // is rightChild
                        popped.parent.right = popped; // I am now the right child of my parent
                    }
                    else {
                        root = popped;
                    }
                }
            }


        }
    }

    @Override
    public void retrack () {
        int b = this.backtracks-1;
        if (!redoStack.isEmpty() && backtracks>0) {
            Node popped = (Node) redoStack.pop();
            this.stack.push(popped);
            if (this.search(popped.getKey()) != null) {
                this.delete(popped);
            } else {
                this.insert(popped);
            }
            this.backtracks = b;
        }
    }


    public void printPreOrder () {
        if (root != null) {
            root.printPreOrder();
        }
        else{System.out.println("null");}
    }

    @Override
    public void print () {
        printPreOrder();
    }

    public static class Node {


        // These fields are public for grading purposes. By coding conventions and best practice they should be private.
        public BacktrackingBST.Node left;
        public BacktrackingBST.Node right;
        private BacktrackingBST.Node parent;


        private int key;
        private Object value;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public Node search(int k) {
            if (this.key == k || this == null) {
                return this;
            }
            //else if ( (this.getKey()<k && k<this.right.getKey()) || (this.getKey()>k && k>this.left.getKey()) ){
            //  return null;
            //}
            else if (this.key > k) {
                if(left!=null) {
                    return this.left.search(k);
                }
                else{
                    return null;
                }
            }
            else {
                if(right!=null) {
                    return this.right.search(k);
                }
                return null;
            }

        }

        public void insert(Node node) {
            if (key > node.getKey()) {
                if (left != null) {
                    this.left.insert(node);
                }
                else {
                    node.parent = this;
                    this.left = node;
                }
            }
            else {
                if (right != null) {
                    right.insert(node);
                }
                else {
                    node.parent = this;
                    this.right = node;
                }
            }
        }

        //
        public Node delete(Node node) {
            if (node.left == null && node.right == null) { // Case 1 - node has no children
                if (node.isLeftChild()) { // Parent is bigger than me -> I am a left child
                    node.parent.left = null;
                }
                else if (node.isRightChild()){ // is right child
                    node.parent.right = null;
                }
                else {
                    return null;
                }
            }
            else if (node.left == null) { // Case 2 - node has one child
                if(node.isLeftChild()){
                    node.parent.left = node.right;
                    node.right.parent = node.parent;
                }
                else if (node.isRightChild()){
                    node.parent.left = node.right;
                    node.right.parent = node.parent; // added late - set my parent as the parent of mt right child
                }

                else { //we want to delete the root
                    if (node.left != null){ //node has one left child
                    return node.left;
                    }
                    else if (node.right != null) { // node has 1 right child
                        return node.right;
                    }
                }
            }

            else if (node.right == null) { // Case 2 - node has one child
                if(node.isLeftChild()){
                    node.parent.left = node.right;
                    node.left.parent = node.parent;
                }
                else if (node.isRightChild()){
                    node.parent.left = node.right;
                    node.left.parent = node.parent; // added late - set my parent as the parent of my right child
                }
                else { // is root
                    if (node.left != null){ //node has a left child
                        return node.left;
                    }
                    else if (node.right != null) { // node has 1 right child
                        return node.right;
                    }
                }
            }

            else { // Case 3 - node has 2 children
                Node min = node.right.minimum();
                node.delete(min);
                min.right = node.right;
                min.parent = node.parent; // here lies the problem of deleting 120 -
                // The parent of 230 is defined to be null (as 120 is the root)
                min.left = node.left;
                if (min.hasParent()){ // hence
                    if (min.parent.getKey() > min.getKey()){
                        node.parent.left = min;
                    }
                    else if (min.parent.getKey() < min.getKey()){
                        node.parent.right = min;
                    }
                }
                else {
                    return min;
                }
            }
            return this;
        } // when backtracking the insertion of an item the wrong item is deleted - FIXED

        public Node minimum() {
            if (this.left == null) {
                return this;
            } else {
                return this.left.minimum();
            }
        }

        @Override
        public String toString() {
            return "" + getKey();
        }

        public Node maximum() {
            if (this.right == null) {
                return this;
            } else {
                return this.right.maximum();
            }
        }

        public void printPreOrder() {
            System.out.print(key+" ");
            if (left != null) {
                left.printPreOrder();
            }
            if (right != null) {
                right.printPreOrder();
            }
        }

        public boolean hasParent(){
            return this.parent != null;
        }

        public boolean isRightChild(){
            if (this.parent != null) {
                if (this.parent.right != null) {
                    return this.getKey() == this.parent.right.getKey();
                }
            }
            return false;
        }

        public boolean wasLeftChild() {
            if (this.parent != null) {
                return this.getKey() < this.parent.getKey();
            }
            return false;
        }

        public boolean wasRightChild() {
            if (this.parent != null) {
                    return this.getKey() > this.parent.getKey();
                }
            return false;
        }

        public boolean isLeftChild(){
            if (this.parent != null) {
                if (this.parent.left != null) {
                    return this.getKey() == this.parent.left.getKey();
                }
            }
            return false;
        }




    }

}

