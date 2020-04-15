import org.w3c.dom.Node;


// A red black tree must satisfy these 5 rules:
//  1. EVERY NODE IS EITHER RED OR BLACK
//  2. THE ROOT IS BLACK.
//  3. EVERY LEAF IS BLACK.
//  4. IF A NODE IS RED, THEN BOTH ITS CHILDREN WILL BE BLACK.
//  5. PER NODE, ALL 'SIMPLE PATHS' FROM THE NODE TO THE DESCENDANT LEAVES CONTAIN THE SAME AMOUNT OF BLACK NODES


public class RedBlackTree <Key extends Comparable<Key>, Value> {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;
    // to do: create root of the tree
    private Node root;

    private class Node {
        /**
         * Class that represents a node of the tree. This node has a link to left and right subtrees,
         * a key with the value, color (where red is true and black is false, instead of using numbers 0 or 1),
         * and the subtreeCount which is the amount of subtrees.
         */
        private Node left, right;
        private Key key;
        private Value data;
        private boolean color;
        private int subtreeCount;

        public Node (Key key, int count, boolean color, Value value){
            this.key = key;
            this.subtreeCount = count;
            this.color = color;
            this.data = value;
        }
    }

    public RedBlackTree(){
        /**
         * Initializes an empty RBT example.
         */
    }


        // specific for the example
    public int size() {
        /**
         * returns the number of key-value pairs in the example.
         */
        return size(root);
    }

    // specific for example
    public boolean isEmpty() {
        /**
         * returns true if the example is empty.
         */
        return root == null;
    }

    private boolean isRed (Node node){
        /**
         * returns false if the node is null or not red, returns true if the node is red.
         */
        if (node == null){
            return false;
        }
        else{
            if(node.color == RED){
                return true;
            }
            else{
                return false;
            }
        }
    }

    private int size(Node node) {
        /**
         * number of nodes in the subtree with the root node (parameter node).
         * returns as "0" if the node is null.
         */
        if (node == null){
            return 0;
        }
        return node.subtreeCount;
    }

    // example specific
    public Key min() {
        /**
         * returns the smallest key in the example
         *
         * returns: KEY
         */
        return min(root).key;
    }

    // EXAMPLE SPECIFIC
    public Key max() {
        /**
         * RETURNS THE BIGGEST KEY IN THE EXAMPLE
         *
         * RETURNS: KEY
         */
        return max(root).key;
    }

    private Node max(Node node) {
        /**
         * the biggest key in the subtree rooted at the node parameter
         * if there is no key, returns as null
         *
         * returns: NODE
         */
        if (node.right == null){
            return node;
        }
        else{
            return max(node.right);
        }
    }

    private Node min(Node node) {
        /**
         * the smallest key in the subtree rooted at the node parameter
         * if there is no key, returns as null
         *
         * returns: NODE
         */
        if (node.left == null){
            return node;
        }
        else{
            return min(node.left);
        }
    }

// EXAMPLE SPECIFIC
    public boolean containsKey(Key key) throws Exception {
        /**
         * checks if the example has the key or not
         *
         * returns: boolean true if it has the key, false if it doesn't
         */
        return getKeyValue(key, root) != null;
    }

    public Value getKeyValue(Key key, Node node) throws Exception {
        /**
         * gets the value at the key parameter, in the subtree rooted at the node parameter
         *
         * returns: VALUE
         */
        if (key == null){
            throw new Exception();
        }
        while (node != null) {
            int compared = key.compareTo(node.key);
            if (compared < 0){
                node = node.left;
            }
            else if (compared > 0){
                node = node.right;
            }
            else {
                return node.data;
            }
        }
        return null;
    }


    private Node rotateR(Node node) {
        /**
         * Helper function that makes a left-leaning part lean to the right
         *
         * returns: NODE
         */
        Node n = node.left;
        node.left = n.right;
        n.right = node;
        n.color = n.right.color;
        n.right.color = RED;
        n.subtreeCount = node.subtreeCount;
        node.subtreeCount = size(node.left) + size(node.right) + 1;
        return n;
    }

    private Node rotateL(Node node) {
        /**
         * Helper function that makes a right-leaning part lean to the left
         *
         * returns: NODE
         */
        Node n = node.right;
        node.right = n.left;
        n.left = node;
        n.color = n.left.color;
        n.left.color = RED;
        n.subtreeCount = node.subtreeCount;
        node.subtreeCount = size(node.left) + size(node.right) + 1;
        return n;
    }


    private void switchColors(Node node) {
        /**
         * switches the color from the current color to the opposite color
         *
         * returns: none
         */
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }




    private Node insert(Node node, Key key, Value data) {
        /**
         * inserts key/value in the subtree with the root at the node parameter
         *
         * returns: NODE
         */
        if (node == null){
            return new Node(key, 1, RED, data);
        }
        if (key.compareTo(node.key) < 0) {
            node.left  = insert(node.left,  key, data);
        }
        else if (key.compareTo(node.key) > 0){
            node.right = insert(node.right, key, data);
        }
        else{
            node.data = data;
        }
        // FIX RL-LINKS:
        if (isRed(node.left)  &&  isRed(node.right)){
            switchColors(node);
        }
        if (isRed(node.right) && !isRed(node.left)){
            node = rotateL(node);
        }
        if (isRed(node.left)  &&  isRed(node.left.left)){
            node = rotateR(node);
        }
        node.subtreeCount = size(node.left) + size(node.right) + 1;
        return node;
    }



    public void insert(Key key, Value value) throws Exception {
        /**
         * inserts the key/value pair parameters into the example.
         * replaces old data as needed or deletes as needed.
         *
         * returns: none
         */
        if (key == null){
            throw new Exception();
        }
        if (value == null) {
            delete(key);
            return;
        }
        root = insert(root, key, value);
        root.color = BLACK;
    }

    private Node balance(Node node) {
        /**
         * rotates colors as needed in-order to make the red black tree again.
         *
         * returns: NODE
         */
        if (isRed(node.right)){
            node = rotateL(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateR(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            switchColors(node);
        }
        node.subtreeCount = size(node.left) + size(node.right) + 1;
        return node;
    }


    //EXAMPLE SPECIFIC
    public void removeSmallest() throws Exception {
        /**
         * REMOVES THE SMALLEST KEY/VALUE PAIR FROM THE EXAMPLE
         * (if both children of the root are black, it sets the root to red.)
         */
        if (isEmpty()){
            throw new Exception();
        }
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = removeSmallestPair(root);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }

    //EXAMPLE SPECIFIC
    public void removeBiggest() throws Exception {
        /**
         * removes the key/value pair that is the biggest from the example.
         *
         * RETURNS: NONE
         */
        if (isEmpty()){
            throw new Exception();
        }
        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = removeBiggestPair(root);
        if (!isEmpty()){
            root.color = BLACK;
        }

    }

    private Node removeSmallestPair (Node node) {
        /**
         * removes the key/value pair with the smallest key rooted at the node parameter
         *
         * returns: NODE
         */
        if (node.left == null)
            return null;

        if (!isRed(node.left) && !isRed(node.left.left))
            node = RedToLeft(node);

        node.left = removeSmallestPair(node.left);
        return balance(node);
    }

    private Node removeBiggestPair(Node node) {
        /**
         * Removes the the key/value pair with the biggest key rooted at the node parameter
         *
         * returns: NODE
         */
        if (isRed(node.left)){
            node = rotateR(node);
        }
        if (node.right == null){
            return null;
        }
        if (!isRed(node.right) && !isRed(node.right.left))
            node = RedToRight(node);
        node.right = removeBiggestPair(node.right);

        return balance(node);
    }

    // EXAMPLE SPECIFIC
    public void delete(Key key) throws Exception {
        /**
         * removes the key parameter (with value) from the example
         *
         * RETURNS: NONE
         */
        if (key == null){
            throw new Exception();
        }
        if (!containsKey(key)){
            return;
        }
        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delete(key, root);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }

    private Node delete(Key key, Node node) {
        /**
         * deletes a key (and value) with the key parameter rooted at the node parameter.
         * This adjusts the entire red black tree; It has to adjust to the removal
         * by moving red to left or to the right. It balances the tree.
         *
         * returns: NODE
         */
        if (key.compareTo(node.key) < 0)  {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = RedToLeft(node);
            }
            node.left = delete(key, node.left);
        }
        else {
            if (isRed(node.left)){
                node = rotateR(node);
            }
            if (key.compareTo(node.key) == 0 && (node.right == null)) {
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = RedToRight(node);
            }
            if (key.compareTo(node.key) == 0) {
                Node n = min(node.right);
                node.key = n.key;
                node.data = n.data;
                node.right = removeSmallestPair(node.right);
            }
            else {
                node.right = delete(key, node.right);
            }
        }
        return balance(node);
    }


    private Node RedToLeft(Node node) {
        /**
         * makes the node parameter's left node or one of its children red.
         *
         * returns: NODE
         */
        switchColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateR(node.right);
            node = rotateL(node);
            switchColors(node);
        }
        return node;
    }

    private Node RedToRight (Node node) {
        /**
         * makes the node parameter's right node or one of its children red.
         *
         * returns: NODE
         */
        switchColors(node);
        if (isRed(node.left.left)) {
            node = rotateR(node);
            switchColors(node);
        }
        return node;
    }

    // EXAMPLE SPECIFIC
    public Key biggestKey(Key key) {
        /**
         * returns the largest key in the example less than or equal to the parameter key
         *
         * returns: KEY
         */
        Node node = biggestKey(key, root);
        return node.key;
    }

    private Node biggestKey(Key key, Node node) {
        /**
         * returns the largest key in the subtree rooted at the parameter nde less than or equal to the key parameter
         *
         * returns: NODE
         */
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        }
        if (key.compareTo(node.key) < 0){
            return biggestKey(key, node.left);
        }
        if (biggestKey(key, node.right) != null){
            return biggestKey(key, node.right);
        }
        else{
            return node;
        }
    }

    // EXAMPLE SPECIFIC
    public Key smallestKey(Key key) {
        /**
         * returns the smallest key in the example greater than or equal to the key parameter.
         *
         * returns: Key
         */
        Node node = smallestKey(key, root);
        return node.key;
    }

    private Node smallestKey(Key key, Node node) {
        /**
         * returns the smallest key in the subtree rooted at the node parameter, that is also
         * greater than or equal to the key parameter
         *
         * returns: Node
         */
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        }
        if (key.compareTo(node.key) > 0){
            return smallestKey(key, node.right);
        }
        if (smallestKey(key, node.left) != null){
            return biggestKey(key, node.left);
        }
        else{
            return node;
        }
    }

    //EXAMPLE SPECIFIC
    public Key keyAtRank(int rank) {
        /**
         * Gets the key in the example which has keys that are smaller.
         *
         * Returns: Key
         */
        return keyAtRank(rank, root);
    }

    private Key keyAtRank(int rank, Node node) {
        /**
         * returns the key in the red black tree that is rooted at the node parameter at the rank number parameter.
         */
        if (node == null){
            return null;
        }
        if (size(node.left) > rank){
            return keyAtRank(rank, node.left);
        }
        else if (size(node.left) < rank){
            return keyAtRank(rank - size(node.left) - 1, node.right);
        }
        else{
            return node.key;
        }
    }

    // EXAMPLE SPECIFIC
    public int numOfKeysLessThanKey(Key key) {
        /**
         * Returns the number of keys in the example less than the parameter key.
         */
        return numOfKeysLessThanKey(root, key);
    }

    private int numOfKeysLessThanKey(Node node, Key key) {
        /**
         * returns the number of keys less than the key in the subtree with the root of 'node'
         */
        if (node == null){
            return 0;
        }
        if (key.compareTo(node.key) < 0){
            return numOfKeysLessThanKey(node.left, key);
        }
        else if (key.compareTo(node.key) > 0){
            return size(node.left) + numOfKeysLessThanKey(node.right, key) + 1;
        }
        else{
            return size(node.left);
        }
    }

}
