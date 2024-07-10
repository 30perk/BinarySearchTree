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
        // null
        if (root == null) {
            return 0;
        }
        // set helper
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        int max_height = 0;
        int temp_height = 1;


        while (!stack.isEmpty()) {
            Node heightCheck = stack.peek();

            // depth track
            if (!visited.contains(heightCheck)) {
                visited.add(heightCheck);
                temp_height = stack.size();
                max_height = Math.max(max_height, temp_height);
            }

            // node track
            if (heightCheck.left != null && !visited.contains(heightCheck.left)) {
                stack.push(heightCheck.left);
            } else if (heightCheck.right != null && !visited.contains(heightCheck.right)) {
                stack.push(heightCheck.right);
            } else {
                stack.pop();
            }

        }

        return max_height;
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

        hi.add(12); //1
        hi.add(12444); //2
        hi.add(3235350); //2
        hi.add(54528); //3
        hi.add(54512); //3
        hi.add(2614);
        hi.add(33);
        hi.add(7);
        hi.add(9);

//        hi.remove(9);
        hi.remove(12444);

        System.out.println(hi.contains(12444));


//    	System.out.println(hi.max());

    }


}