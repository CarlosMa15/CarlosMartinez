/*************************************************************
 * Author: Carlos Martinez
 * Date: September 1, 2018
 * Assignment: Ceiling Function
 ************************************************************/

package ceilingFunction;

/**
 * This is a class created to represent a binary search tree. The first element becomes
 * the root, then if new element is greater than the root it got added to the right of
 * the root otherwise it gets added to the left. The code takes great inspiration from
 * multiple jave binary search trees found online. Other functions like contains, size,
 * and remove are not needed for the implementation of this program so they were left out.
 * @author Carlos Martinez
 */
public class binarySearchTree {

	//The root is the very top of the the binary tree, first element added
    private Node root;

    /**
     * This is a helper private class used to help the binary search tree. The node
     * keeps track of the nodes data(integer value) and a pointer to to the node to
     * the left and right below it.
     * @author Carlos Martinez
     */
    private static class Node {
        private int data;
        private Node left;
        private Node right;

        private Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * This creates an empty binary search tree
     */
    public binarySearchTree() {
        this.root = null;
    }

    /**
     * This creates a new binary search tree with the given integer as the root
     * @param startingRoot
     */
    public void add(int startingRoot) {
        this.root = addHelper(root, startingRoot);
    }

    /**
     * This is a helper method that does the work of adding the element to the binary search tree
     * @param node - the node that can above it 
     * @param newElement -  the new element that you are trying to add
     * @return 
     */
    private Node addHelper(Node node, int newElement) {
        if (node == null) return new Node(newElement); //creates a new node
        if (node.data < newElement) node.right = addHelper(node.right, newElement); //adds node to the right
        else node.left = addHelper(node.left, newElement); // adds node to the left
        return node;
    }

    /**
     * returns a string representation of the binary search tree
     */
    @Override
    public String toString() {
        if (root == null) return "";
        StringBuilder str = new StringBuilder();
        toStringHelper(root, str);
        return str.toString();
    }

    /**
     * This creates a string representation of the binary search tree starting from the left to the right
     * @param node the starting node or node that came before
     * @param str the string builder with all the information of the binary search tree
     */
    private void toStringHelper(Node node, StringBuilder str) {
        if (node.left != null) {// searches the left side
            str.append("L");
            toStringHelper(node.left, str);
        }
        str.append('X');
        if (node.right != null) {// searches the right side
            str.append("R");
            toStringHelper(node.right, str);
        }
    }
}