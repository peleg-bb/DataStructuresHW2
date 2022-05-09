public class Tests {
    public static void main(String[] args) {
//            check4l1();
//            check4l2();
        check4l3();
        check4l32();

    }

    public static void check4l1() {
        System.out.println("check part 4 Q1:");
        Stack s1 = new Stack();
        BacktrackingArray array = new BacktrackingArray(s1, 15);
        array.backtrack();
        array.insert(5);
        array.insert(16);
        array.insert(45);
        array.insert(147);
        array.insert(2);
        System.out.println("2|" + array.search(45));
        array.delete(2);
        System.out.println("-1|" + array.search(45));
        array.backtrack();
        System.out.println("2|" + array.search(45));
        array.backtrack();
        System.out.print("5 16 45 147|");
        array.print();
        array.insert(78);
        array.backtrack();
        System.out.println("-1|" + array.search(78));
        System.out.println("2|" + array.predecessor(3));
        array.insert(14);
        array.insert(9);
        System.out.println("5|" + array.predecessor(4));
        array.backtrack();
        System.out.println("0|" + array.predecessor(4));
        array.insert(1);
        System.out.println("5|" + array.minimum());
        System.out.println("3|" + array.maximum());
        System.out.println("1|" + array.successor(4));
        System.out.println("*************************************");

        //eroor checks:
//            System.out.println(array.predecessor(5));
//            System.out.println(array.successor(3));
//                array.delete(13);
//
//                for (int i = 0; i <= 15; i++)
//                    array.insert(i);
    }

    public static void check4l2() {
        System.out.println("check part 4 Q2:");
        Stack s1 = new Stack();
        BacktrackingSortedArray array = new BacktrackingSortedArray(s1, 15);
        array.backtrack();
        array.insert(5);
        array.insert(16);
        array.insert(45);
        array.insert(147);
        array.insert(2);
        System.out.print("2 5 16 45 147|");
        array.print();

        System.out.println("3|" + array.search(45));
        array.delete(3);
        System.out.println("-1|" + array.search(45));
        array.backtrack();
        System.out.println("3|" + array.search(45));

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
        System.out.println("-1|" + array.search(38));

        System.out.println("1|" + array.predecessor(2));
        array.insert(14);
        array.insert(9);
        System.out.print("5 9 14 16 45|");
        array.print();

        System.out.println("3|" + array.successor(2));
        array.backtrack();
        System.out.println("0|" + array.predecessor(1));
        array.insert(1);
        System.out.println("0|" + array.minimum());
        System.out.println("4|" + array.maximum());
        array.backtrack();
        array.backtrack();
        array.backtrack();
        array.backtrack();
        System.out.print("5|");
        array.print();
        System.out.println("-1|" + array.search(45));
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
        Stack s1 = new Stack();
        Stack s2 = new Stack();
        BacktrackingBST tree = new BacktrackingBST(s1, s2);
        tree.backtrack();
        BacktrackingBST.Node n120 = new BacktrackingBST.Node(120, null);
        BacktrackingBST.Node n100 = new BacktrackingBST.Node(100, null);
        BacktrackingBST.Node n13 = new BacktrackingBST.Node(13, null);
        BacktrackingBST.Node n56 = new BacktrackingBST.Node(56, null);
        BacktrackingBST.Node n87 = new BacktrackingBST.Node(87, null);
        BacktrackingBST.Node n230 = new BacktrackingBST.Node(230, null);
        BacktrackingBST.Node n40 = new BacktrackingBST.Node(40, null);
        BacktrackingBST.Node n22 = new BacktrackingBST.Node(22, null);
        BacktrackingBST.Node n80 = new BacktrackingBST.Node(80, null);

        BacktrackingBST.Node n240 = new BacktrackingBST.Node(240, null);

        tree.insert(n120);
        tree.insert(n100);
        tree.insert(n13);
        tree.insert(n56);
        tree.insert(n87);
        tree.insert(n230);
        tree.insert(n40);
        tree.insert(n22);
        tree.insert(n80);


        System.out.println("13|" + tree.minimum());
        System.out.println("230|" + tree.maximum());
        System.out.println("null|" + tree.search(47));
        System.out.println("87|" + tree.search(87));
        System.out.println("87|" + tree.successor(n80));
        System.out.println("230|" + tree.successor(n120));
        System.out.println("22|" + tree.successor(n13));
        System.out.println("13|" + tree.predecessor(n22));
        System.out.println("100|" + tree.predecessor(n120));

        //error check
        //tree.insert(null);
        //System.out.println("error|"+tree.successor(n230));
        //System.out.println("error|"+tree.predecessor(n13));


        System.out.println("the tree at beggining");
        tree.print();
        System.out.println(" ");
        System.out.println("***************************");
        tree.backtrack();
        System.out.println("the tree after backtracking addition 80");
        tree.print();
        System.out.println("");
        tree.retrack();
        System.out.println("the tree after retracking the backtrack of 80 (should insert 80)");
        tree.print();
        System.out.println("");
        tree.delete(n13);
        tree.delete(n22);
        tree.delete(n56);
        System.out.println("the tree after deleting 13, 22, 56:");
        tree.print();
        System.out.println("");
        System.out.println("the tree after backtracking the deletion of 56 (56 should be included):");
        tree.backtrack();
        tree.print();
        System.out.println("");
        System.out.println("the tree after after backtracking the deletion of 22 (22 should be included):");
        tree.backtrack();
        tree.print();
        System.out.println("");
        System.out.println("the tree after after backtracking the deletion of 13 (should look like the original tree):");
        tree.backtrack();
        tree.print();
        System.out.println("");
        System.out.println("the tree after after retracking the backtrack of 13 (should delete 13):");
        tree.retrack();
        tree.print();
        System.out.println("");
        System.out.println("the tree after after retracking the backtrack of 22 (should delete 22):");
        tree.retrack();
        tree.print();
        System.out.println("");
        System.out.println("the tree after after retracking the backtrack of 56 (should delete 56):");
        tree.retrack();
        tree.print();
        System.out.println("");
        System.out.println("the tree after deleting the root (120)");
        tree.delete(n120);
        tree.print();
        System.out.println("");
        tree.delete(n230);
        System.out.println("the tree after deleting the new root (230)");
        tree.print();
        System.out.println("");
        System.out.println("the tree after backtracking the deletion of the root (230)");
        tree.backtrack();
        tree.print();
        System.out.println("");
        tree.backtrack();
        System.out.println("the tree after backtracking the deletion of the root (120)");
        tree.print();
        System.out.println("");

        System.out.println("*************************************");
    }

    public static void check4l32() {
        System.out.println("check part 4 Q3:");
        Stack s1 = new Stack();
        Stack s2 = new Stack();
        BacktrackingBST tree = new BacktrackingBST(s1, s2);
        tree.backtrack();
        BacktrackingBST.Node n120 = new BacktrackingBST.Node(120, null);
        BacktrackingBST.Node n100 = new BacktrackingBST.Node(100, null);
        BacktrackingBST.Node n13 = new BacktrackingBST.Node(13, null);
        BacktrackingBST.Node n56 = new BacktrackingBST.Node(56, null);
        BacktrackingBST.Node n87 = new BacktrackingBST.Node(87, null);
        BacktrackingBST.Node n230 = new BacktrackingBST.Node(230, null);
        BacktrackingBST.Node n40 = new BacktrackingBST.Node(40, null);
        BacktrackingBST.Node n22 = new BacktrackingBST.Node(22, null);
        BacktrackingBST.Node n80 = new BacktrackingBST.Node(80, null);
        BacktrackingBST.Node n250 = new BacktrackingBST.Node(250, null);

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


        System.out.println("13|" + tree.minimum());
        System.out.println("230|" + tree.maximum());
        System.out.println("null|" + tree.search(47));
        System.out.println("87|" + tree.search(87));
        System.out.println("87|" + tree.successor(n80));
        System.out.println("230|" + tree.successor(n120));
        System.out.println("22|" + tree.successor(n13));
        System.out.println("13|" + tree.predecessor(n22));
        System.out.println("100|" + tree.predecessor(n120));

        //error check
        //tree.insert(null);
        //System.out.println("error|"+tree.successor(n230));
        //System.out.println("error|"+tree.predecessor(n13));


        System.out.println("the tree at beginning  ");

        tree.print();
        System.out.println(" ");
        System.out.println("***************************");
        System.out.println("the tree after backtracking the addition of 80");
        tree.backtrack();

        tree.print();
        System.out.println(" ");
        System.out.println("the tree after inserting 80");
        tree.insert(n80);

        tree.print();
        System.out.println(" ");
        System.out.println("the tree after deleting 80");
        tree.delete(n80);

        tree.print();
        System.out.println(" ");
        System.out.println("the tree after backtracking the deletion 80");
        tree.backtrack();

        tree.print();
        System.out.println(" ");


        tree.delete(n13);
        tree.delete(n22);
        tree.delete(n120);
        System.out.println("the tree after deleting 13, 22, 120:");

        tree.print();
        System.out.println(" ");
        System.out.println("the tree after backtracking the deletion of 120 (with 120):");
        tree.backtrack();

        tree.print();
        System.out.println(" ");
        System.out.println("the tree after after backtracking the deletion of 22 (with 22):");
        tree.backtrack();

        tree.print();
        System.out.println("");
        System.out.println("the tree after after backtracking the deletion of 13 (should look like the original tree):");
        tree.backtrack();

        tree.print();
        System.out.println(" ");
        tree.delete(n120);
        System.out.println("the tree after deleting 120:");

        tree.print();
        System.out.println(" *******");
        System.out.println("the tree after backtracking the deletion of 120 (with 120):");
        tree.backtrack();

        tree.print();
        System.out.println("******* ");
        System.out.println("the tree after deleting the root (40)");
        tree.delete(n40);

        tree.print();
        System.out.println(" ");
        System.out.println("the tree after deleting the root (56)");
        tree.delete(n56);

        tree.print();
        System.out.println(" ");
        System.out.println("the tree after backtracking the deletion of the root (56)");
        tree.backtrack();
        System.out.println(" ");
        tree.print();
        System.out.println(" ");
        System.out.println("the tree after backtracking the deletion of the root (40)");
        tree.backtrack();

        tree.print();
        System.out.println(" ");
        System.out.println("13|" + n13);
        System.out.println("22|" + n13.right);
        System.out.println("40|" + n40);
        System.out.println("56|" + n56);
        System.out.println("the tree after deleting 13");
        tree.delete(n13);

        tree.print();
        System.out.println(" ");
        System.out.println("*************************************");

    }


}
