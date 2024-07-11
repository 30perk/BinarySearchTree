import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class BinSearch {

    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;

        }

    }

    private Node root;
//    private int size = 0;
//	private Integer height;

    public BinSearch() {
        this.root = null;
    }

    public void add(int value) {
        root = addMethod(root, value);

    }

    private Node addMethod(Node node, int value) {

        // empty tree
        if (node == null) {

//             System.out.println(addNode.height);
//            System.out.println(addNode);

            return new Node(value);
        }

        if (value > node.value) {

            node.right = addMethod(node.right, value);
        } else if (value < node.value) {

            node.left = addMethod(node.left, value);
        }

        return node;
    }


    public int max() {


        Node max = root;
        if (max == null) {
            throw new NullPointerException("Empty Tree");
        }

        while (max.right != null) {

            max = max.right;
        }

        return max.value;

    }


    public int min() {

        Node min = root;
        if (min == null) {
            throw new NullPointerException("Empty Tree");
        }

        while (min.left != null) {
            min = min.left;
        }
        return min.value;
    }

    public boolean contains(int value) {
        return containsMethod(root, value);
    }


    private boolean containsMethod(Node node, int value) {
        // null

        if (node == null) {
            return false;
        }
        // one
        if (node.value == value) {
            return true;
        }
        // left
        else if (node.value > value) {
            return containsMethod(node.left, value);
        } else {
            return containsMethod(node.right, value);
        }
    }

    public int height() {
        if (root == null) { return 0; }
        if (root.right == null && root.left == null) { return 1; }

        int depth = 1;
        int depthOfLeft = 0;
        int depthOfRight = 0;

        if (root.left != null) { depthOfLeft = (heightHelper(root, depth)); }
        if (root.right != null) { depthOfRight = (heightHelper(root, depth)); }

        return Math.max(depthOfLeft, depthOfRight);

    }

    private int heightHelper(Node node, int depth) {

        int leftDepthCounter = depth;
        int rightDepthCounter = depth;

        if (node.right != null & node.left != null) {
            depth += 1;
            rightDepthCounter = heightHelper(node.right, depth);
            leftDepthCounter = heightHelper(node.left, depth);
        }
        else if (node.right != null & node.left == null) {
            depth += 1;
            rightDepthCounter = heightHelper(node.right, depth);
        } else if (node.left != null) {
            depth += 1;
            leftDepthCounter = heightHelper(node.left, depth);
        }

        return Math.max(leftDepthCounter, rightDepthCounter);
    }


    private Node goLeft(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    public void remove(int value) {
        // empty
        if (root == null) {return;}

        Stack<Node> mainStack = new Stack<>();
//        Stack<Node> ifNeeded = new Stack<>();
        Node curr = root;
        Node parent = null;

        // find node
        while (curr != null && curr.value != value) {
            parent = curr;
            // find node
            if (curr.value > value) {
                mainStack.push(curr);
                curr = curr.left;
            } else {
                mainStack.push(curr);
                curr = curr.right;
            }
        }
        if (curr == null) { return; } // didn't find node

        // leaf
        if (curr.left == null && curr.right == null) {
            // check if there's more than one node
            if (curr != root) {
                // delete curr node
                if (parent.left == curr) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            else { root=null; }  // there was only the root node
        }

        // two kids
        else if (curr.left != null && curr.right != null) {
            // down right and farthest to the left
            Node farLeft = goLeft(curr.right);
            int val = farLeft.value;
            remove(farLeft.value);
            curr.value = val;
        }
        // one child
        else {
            // is it right child or left child
            Node child = (curr.left != null) ? curr.left : curr.right;

            if (curr != root) {
                // delete curr node
                if (curr == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else { root = child; }

        }

    }


    public static void main(String[] args) {
        BinSearch hi = new BinSearch();

        hi.add(56); //1
        hi.add(30); //2
        hi.add(70); //2
        hi.add(22); //3
        hi.add(40); //3
        hi.add(60);
//        hi.add(95);
//        hi.add(11);
//        hi.add(16);
//        hi.add(65);
//        hi.add(63);
//        hi.add(67);
//        hi.remove(9);
//        hi.remove(12444);

//        hi.remove(6);
//        System.out.println(hi.contains(4 ));
        System.out.println(hi.height());


//    	System.out.println(hi.max());

    }


}