

import java.util.NoSuchElementException;

public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node> {
    private Stack stack;
    private Stack redoStack;
    private BacktrackingBST.Node root = null;

    // Done - min, max, pred, succ, search, insert, delete, print;
    // To do - backtrack, retrack;




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
        if (root.getKey()==k){
            return root;
        }
        else if ( (root.getKey()<k && k<root.right.getKey()) || (root.getKey()>k && k>root.left.getKey()) ){
            return null;
        }
        else if (root.getKey()>k){
            return root.left.search(k);
        }
        else {
            return root.right.search(k);
        }
    }

    public void insert(Node node) {
        stack.push(node);
       if(root != null){
           root.insert(node);
       }
    }

    public void delete(Node node) {
        stack.push(node);
        if(node.left==null && node.right==null){
            if(node.parent.getKey() > node.getKey()){
                node.parent.left = null;
            }
            else {
                node.parent.right = null;
            }
        }
        else if(node.left==null){
            node = node.right;
            node.right = null;
        }
        else if(node.right==null) {
            node = node.left;
            node.left = null;
        }
        else {
            node = node.right.minimum();
            node.right.minimum().parent.left = null; /////////////////can be done in a smarter way?
        }
    }

    public Node minimum() {
        if (root.left == null){
            return root;
        }
        else {
            return root.left.minimum();
        }
    }

    public Node maximum() {
        if (root.right == null){
            return root;
        }
        else {
            return root.right.maximum();
        }
    }

    public Node successor(Node node) {
        // Does it return null if node has no successor??
        // I believe it does - needs to be confirmed
        if (node.right != null){
            return node.right.minimum();
        }
        else {
            Node y = node.parent;
            while (y != null && node==y.right){
                node = y;
                y = y.parent;
            }
            return y;
        }
    }

    public Node predecessor(Node node) {
        // Does it return null if node has no predecessor??
        // I believe it does - needs to be confirmed
        if (node.left != null){
            return node.left.maximum();
        }
        else {
            Node y = node.parent;
            while (y != null && node==y.left){
                node = y;
                y = y.parent;
            }
            return y;
        }
    }

    @Override
    public void backtrack() {
        // TODO: implement your code here
    }

    @Override
    public void retrack() {
        // TODO: implement your code here
    }

    public void printPreOrder(){
       if(root != null){
           root.printPreOrder();
       }
    }

    @Override
    public void print() {
        printPreOrder();
    }

    public static class Node {

//        public String toString() {
//            if (root!=null) {
//                System.out.println("***************************");
//                return root.toString2();
//            }
//            else
//                return "Empty Tree";
//        }
//	**************************

//        and add the function to the Node class:

//        public String toString() {
//            return ""+this.getKey()+"";
//        }
//        public String toString2() {
//            String d="";
//            return toString2(d);
//        }
//
//        private String toString2(String d) {
//            String s="";
//            if(right!=null)
//                s=s+right.toString2(d+"  ");
//            s=s+d+getKey()+"\n";
//            if(left!=null)
//                s=s+left.toString2(d+"  ");
//            return s;
//        }
//
//
//	 */

        public static void main(String[] args) {
            check4l1();
            //check4l2();
            //check4l3();
            //check4l32();

        }
        public static void check4l1() {
            System.out.println("check part 4 Q1:");
            Stack s1= new Stack();
            BacktrackingArray array=new BacktrackingArray(s1, 15);
            array.backtrack();
            array.insert(5);
            array.insert(16);
            array.insert(45);
            array.insert(147);
            array.insert(2);
            System.out.println("2|"+array.search(45));
            array.delete(2);
            System.out.println("-1|"+array.search(45));
            array.backtrack();
            System.out.println("2|"+array.search(45));
            array.backtrack();
            System.out.print("5 16 45 147|");
            array.print();
            array.insert(78);
            array.backtrack();
            System.out.println("-1|"+array.search(78));
            System.out.println("2|"+array.predecessor(3));
            array.insert(14);
            array.insert(9);
            System.out.println("5|"+array.predecessor(4));
            array.backtrack();
            System.out.println("0|"+array.predecessor(4));
            array.insert(1);
            System.out.println("5|"+array.minimum());
            System.out.println("3|"+array.maximum());
            System.out.println("1|"+array.successor(4));
            System.out.println("*************************************");

            //eroor checks:
//            System.out.println(array.predecessor(5));
//            System.out.println(array.successor(3));
            array.delete(13);

            for (int i=0; i<=15; i++)
                array.insert(i);
        }

        public static void check4l2() {
            System.out.println("check part 4 Q2:");
            Stack s1= new Stack();
            BacktrackingSortedArray array=new BacktrackingSortedArray(s1, 15);
            array.backtrack();
            array.insert(5);
            array.insert(16);
            array.insert(45);
            array.insert(147);
            array.insert(2);
            System.out.print("2 5 16 45 147|");
            array.print();

            System.out.println("3|"+array.search(45));
            array.delete(3);
            System.out.println("-1|"+array.search(45));
            array.backtrack();
            System.out.println("3|"+array.search(45));

            array.backtrack();
            System.out.print("5 16 45 147|");
            array.print();
            array.backtrack();
            System.out.print("5 16 45 |");
            array.print();

            array.insert(38);
            System.out.print("5 16 38 45|");
            array.print();

            array.backtrack();
            System.out.println("-1|"+array.search(38));

            System.out.println("1|"+array.predecessor(2));
            array.insert(14);
            array.insert(9);
            System.out.print("5 9 14 16 45|");
            array.print();

            System.out.println("3|"+array.successor(2));
            array.backtrack();
            System.out.println("0|"+array.predecessor(1));
            array.insert(1);
            System.out.println("0|"+array.minimum());
            System.out.println("4|"+array.maximum());
            array.backtrack();
            array.backtrack();
            array.backtrack();
            array.backtrack();
            System.out.print("5|");
            array.print();
            System.out.println("-1|"+array.search(45));
            System.out.println("*************************************");

            //eroor checks:
            //System.out.println(array.predecessor(0));
            //System.out.println(array.successor(4));
            //array.delete(13);

            //for (int i=0; i<=15; i++)
            //	array.insert(i);



        }
        public static void check4l3() {
            System.out.println("check part 4 Q3:");
            Stack s1= new Stack();
            Stack s2= new Stack();
            BacktrackingBST tree=new BacktrackingBST(s1,s2);
            tree.backtrack();
            BacktrackingBST.Node n120=new BacktrackingBST.Node(120,null);
            BacktrackingBST.Node n100=new BacktrackingBST.Node(100,null);
            BacktrackingBST.Node n13=new BacktrackingBST.Node(13,null);
            BacktrackingBST.Node n56=new BacktrackingBST.Node(56,null);
            BacktrackingBST.Node n87=new BacktrackingBST.Node(87,null);
            BacktrackingBST.Node n230=new BacktrackingBST.Node(230,null);
            BacktrackingBST.Node n40=new BacktrackingBST.Node(40,null);
            BacktrackingBST.Node n22=new BacktrackingBST.Node(22,null);
            BacktrackingBST.Node n80=new BacktrackingBST.Node(80,null);

            BacktrackingBST.Node n240=new BacktrackingBST.Node(240,null);

            tree.insert(n120);
            tree.insert(n100);
            tree.insert(n13);
            tree.insert(n56);
            tree.insert(n87);
            tree.insert(n230);
            tree.insert(n40);
            tree.insert(n22);
            tree.insert(n80);


            System.out.println("13|"+tree.minimum());
            System.out.println("230|"+tree.maximum());
            System.out.println("null|"+tree.search(47));
            System.out.println("87|"+tree.search(87));
            System.out.println("87|"+tree.successor(n80));
            System.out.println("230|"+tree.successor(n120));
            System.out.println("22|"+tree.successor(n13));
            System.out.println("13|"+tree.predecessor(n22));
            System.out.println("100|"+tree.predecessor(n120));

            //error check
            //tree.insert(null);
            //System.out.println("error|"+tree.successor(n230));
            //System.out.println("error|"+tree.predecessor(n13));


            System.out.println("the tree at beggining");
            System.out.println(tree.toString());
            System.out.println("***************************");
            tree.backtrack();
            System.out.println("the tree after the backtracking adding 80");
            System.out.println(tree.toString());
            tree.insert(n80);
            System.out.println("the tree after inserting 80 back");
            System.out.println(tree.toString());
            tree.delete(n13);
            tree.delete(n22);
            tree.delete(n56);
            System.out.println("the tree after deliting 13, 22, 56:");
            System.out.println(tree.toString());
            System.out.println("the tree after backtracking the delete of 56 (with 56):");
            tree.backtrack();
            System.out.println(tree.toString());
            System.out.println("the tree after after backtracking the delete of 22 (with 22):");
            tree.backtrack();
            System.out.println(tree.toString());
            System.out.println("the tree after after backtracking the delete of 13 (supposed to look like the original tree):");
            tree.backtrack();
            System.out.println(tree.toString());
            tree.delete(n120);
            System.out.println("the tree after deleting the root (120)");
            System.out.println(tree.toString());
            tree.delete(n230);
            System.out.println("the tree after deleting the root (230)");
            System.out.println(tree.toString());
            tree.backtrack();
            System.out.println("the tree after bringing back the root (230)");
            System.out.println(tree.toString());
            tree.backtrack();
            System.out.println("the tree after bringing back the root (120)");
            System.out.println(tree.toString());

            System.out.println("*************************************");
        }

        public static void check4l32() {
            System.out.println("check part 4 Q3:");
            Stack s1= new Stack();
            Stack s2= new Stack();
            BacktrackingBST tree=new BacktrackingBST(s1,s2);
            tree.backtrack();
            BacktrackingBST.Node n120=new BacktrackingBST.Node(120,null);
            BacktrackingBST.Node n100=new BacktrackingBST.Node(100,null);
            BacktrackingBST.Node n13=new BacktrackingBST.Node(13,null);
            BacktrackingBST.Node n56=new BacktrackingBST.Node(56,null);
            BacktrackingBST.Node n87=new BacktrackingBST.Node(87,null);
            BacktrackingBST.Node n230=new BacktrackingBST.Node(230,null);
            BacktrackingBST.Node n40=new BacktrackingBST.Node(40,null);
            BacktrackingBST.Node n22=new BacktrackingBST.Node(22,null);
            BacktrackingBST.Node n80=new BacktrackingBST.Node(80,null);
            BacktrackingBST.Node n250=new BacktrackingBST.Node(250,null);

            tree.insert(n40);
            tree.insert(n120);
            tree.insert(n56);
            tree.insert(n13);
            tree.insert(n22);
            tree.insert(n87);
            tree.insert(n250);
            tree.insert(n100);
            tree.insert(n230);

            tree.insert(n80);


            System.out.println("13|"+tree.minimum());
            System.out.println("230|"+tree.maximum());
            System.out.println("null|"+tree.search(47));
            System.out.println("87|"+tree.search(87));
            System.out.println("87|"+tree.successor(n80));
            System.out.println("230|"+tree.successor(n120));
            System.out.println("22|"+tree.successor(n13));
            System.out.println("13|"+tree.predecessor(n22));
            System.out.println("100|"+tree.predecessor(n120));

            //error check
            //tree.insert(null);
            //System.out.println("error|"+tree.successor(n230));
            //System.out.println("error|"+tree.predecessor(n13));


            System.out.println("the tree at beggining");
            System.out.println(tree.toString());
            System.out.println("***************************");
            System.out.println("the tree after the backtracking adding 80");
            tree.backtrack();
            System.out.println(tree.toString());
            System.out.println("the tree after inserting 80 back");
            tree.insert(n80);
            System.out.println(tree.toString());
            System.out.println("the tree after deletenig 80 back");
            tree.delete(n80);
            System.out.println(tree.toString());
            System.out.println("the tree after the backtracking deliting 80");
            tree.backtrack();
            System.out.println(tree.toString());



            tree.delete(n13);
            tree.delete(n22);
            tree.delete(n120);
            System.out.println("the tree after deliting 13, 22, 120:");
            System.out.println(tree.toString());
            System.out.println("the tree after backtracking the delete of 120 (with 120):");
            tree.backtrack();
            System.out.println(tree.toString());
            System.out.println("the tree after after backtracking the delete of 22 (with 22):");
            tree.backtrack();
            System.out.println(tree.toString());
            System.out.println("the tree after after backtracking the delete of 13 (supposed to look like the original tree):");
            tree.backtrack();
            System.out.println(tree.toString());

            tree.delete(n120);
            System.out.println("the tree after deliting 120:");
            System.out.println(tree.toString());
            System.out.println("the tree after backtracking the delete of 120 (with 120):");
            tree.backtrack();
            System.out.println(tree.toString());
            System.out.println("the tree after deleting the root (40)");
            tree.delete(n40);
            System.out.println(tree.toString());
            System.out.println("the tree after deleting the root (56)");
            tree.delete(n56);
            System.out.println(tree.toString());
            System.out.println("the tree after bringing back the root (56)");
            tree.backtrack();
            System.out.println(tree.toString());
            System.out.println("the tree after bringing back the root (40)");
            tree.backtrack();
            System.out.println(tree.toString());
            System.out.println("13|"+n13);
            System.out.println("40|"+n13.parent);
            System.out.println("40|"+n40);
            System.out.println("56|"+n56);
            System.out.println("the tree after deliting 13");
            tree.delete(n13);
            System.out.println(tree.toString());
            System.out.println("*************************************");

        }


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

        public Node search(int k){
            if (this.key == k || this == null){
                return this;
            }
            else if ( (this.getKey()<k && k<this.right.getKey()) || (this.getKey()>k && k>this.left.getKey()) ){
                return null;
            }
            else if (this.key>k){
                return this.left.search(k);
            }
            else {
                return this.right.search(k);
            }

        }

        public void insert(Node node) {

            if(key < node.getKey()){
                if(left!=null) {
                    left.insert(node);
                }
                else {
                    left = node;
                }
            }
            else {
                if(right!=null) {
                    right.insert(node);
                }
                else {
                    right = node;
                }
            }
        }
//
        public void delete(Node node) {
            if(left==null && right==null){
                if(parent.getKey() > getKey()){
                    parent.left = null;
                }
                else {
                    parent.right = null;
                }
            }
            else if(left==null){
                parent.right = this.right;


            }
            else if(node.right==null) {
                parent.left = this.left;
            }
            else {
                this.key = right.minimum().getKey();
                this.value = right.minimum().getValue();
                right.delete(right.minimum());
            }
        }

        public Node minimum() {
            if (this.left == null){
                return this;
            }
            else {
                return this.left.minimum();
            }
        }

        public Node maximum() {
            if (this.right == null){
                return this;
            }
            else {
                return this.left.maximum();
            }
        }

        public void printPreOrder(){
            System.out.println(key);
            if(left != null){
                left.printPreOrder();
            }
            if(right != null){
                right.printPreOrder();
            }
        }



    }

}
