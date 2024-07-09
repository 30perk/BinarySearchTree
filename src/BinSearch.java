
public class BinSearch {

    public static class Node {
        int value;
        Node left;
        Node right;
        Node prev;
        int height;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;

        }

    }

    private Node root;
    public int max_height = 0;
//	private Integer height;

    public BinSearch() {
        this.root = null;
    }

    public void add(int value) {
        root = addMethod(root, value, null);

    }

    private Node addMethod(Node node, int value, Node prev) {

        // empty tree
        if (node == null) {

             Node addNode = new Node(value);
             if (prev != null) {
                 addNode.height = prev.height +1;
                 addNode.prev = prev;
             }

             if (addNode.height > max_height) {
                 max_height = addNode.height;
             }

//             System.out.println(addNode.height);
             return addNode; }

        if (value > node.value) {

            node.right = addMethod(node.right, value, node);
        } else if (value < node.value) {

            node.left = addMethod(node.left, value, node);
        }

        return node; }



    public int max() {


        if (root == null) {
            throw new NullPointerException("Empty Tree");
        }

        while (root.right != null) {
            root = root.right;
        }
        return root.value;

    }


    public int min() {


        if (root == null) {
            throw new NullPointerException("Empty Tree");
        }

        while (root.left != null) {
            root = root.left;
        }
        return root.value;
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

    public void remove(int value) {
        removeMethod(root, value);
    }

    private Node goLeft(Node node) {
        while (node.left != null) {
            goLeft(node.left);
        }

        node.prev.left = null;
        return node;
    }

    private void removeMethod(Node node, int value) {
        // null
        if (node == null) {
            return;
        }


        if (node.value == value) {
            // leaf
            if (node.left == null && node.right == null) {
                if (node.value > node.prev.value) {
                    node.prev.right = null;
                } else {node.prev.left = null;}
            }

            // two kids
            if (node.left != null & node.right != null) {

                // replacement node
                Node replace = node.right;
                replace = goLeft(replace);

                // set replace == main node
                replace.left = node.left;
                replace.right = node.right;
                replace.prev = node.prev;

                // replace main node
                if (replace.prev.value > replace.value) {
                    replace.prev.left = replace;
                } else { replace.prev.right = replace;}

            }

            // one child
            if (node.left != null || node.right != null) {
                // left child
                if (node.left != null) {
                    node.left.prev = node.prev;
                    // check if current node is the left or right of current node's parent
                    if (node.value > node.prev.value) {
                        node.prev.right = node.left;
                    } else {node.prev.left = node.left;}

                }
                else { // right child
                    node.right.prev = node.prev;
                    // checking parent of current node
                    if (node.value > node.prev.value) {
                        node.prev.right = node.right;
                    } else { node.prev.left = node.right; }
                }

            }

        } else {
            if ( node.value > value ) {
                removeMethod(node.left, value);
            } else { removeMethod(node.right, value);}
        }
    }

    public int height() {
        return max_height;
    }

    public static void main(String[] args) {
        BinSearch hi = new BinSearch();

        hi.add(20); //1
        hi.add(10); //2
        hi.add(30); //2
        hi.add(8); //3
        hi.add(12); //3
        hi.add(26);
        hi.add(33);
        hi.add(7);
        hi.add(9);

//        hi.remove(9);
        System.out.println(hi.max_height);


        System.out.println(hi.contains(9));


//    	System.out.println(hi.max());

    }

}
