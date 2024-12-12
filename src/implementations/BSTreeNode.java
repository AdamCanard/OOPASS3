package implementations;

public class BSTreeNode<E> {

	private static final long serialVersionUID = 1L; // Add serialVersionUID
	private E element; //
	private String Occurrences;
	private BSTreeNode<E> left; // left child
	private BSTreeNode<E> right; // right child

	// Constructor (just sets the value)
	public BSTreeNode(E elem) {
		this.element = elem;
		this.left = null;
		this.right = null;
	}

	// Constructor (set value and left and right children)
	public BSTreeNode(E elem, BSTreeNode<E> left, BSTreeNode<E> right) {
		this.element = elem;
		this.left = left;
		this.right = right;
	}

	// Getter for element
	public E getElement() {
		return element;
	}

	// Setter for element
	public void setElement(E element) {
		this.element = element;
	}

	// Getter for left child
	public BSTreeNode<E> getLeft() {
		return left;
	}

	// Setter for left child
	public void setLeft(BSTreeNode<E> left) {
		this.left = left;
	}

	// Getter for right child
	public BSTreeNode<E> getRight() {
		return right;
	}

	// Setter for right child
	public void setRight(BSTreeNode<E> right) {
		this.right = right;
	}

	// Check if the node has a left child
	public boolean hasLeftChild() {
		return this.left != null;
	}

	// Check if the node has a right child
	public boolean hasRightChild() {
		return this.right != null;
	}

	// Check if the node is a leaf (no children)
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}

	// Get the number of nodes in the subtree rooted at this node
	public int getNumberNodes() {
		int leftCount = (left != null) ? left.getNumberNodes() : 0;
		int rightCount = (right != null) ? right.getNumberNodes() : 0;
		return 1 + leftCount + rightCount;
	}

	// Get the height of the subtree rooted at this node
	public int getHeight() {
		int leftHeight = (left != null) ? left.getHeight() : 0;
		int rightHeight = (right != null) ? right.getHeight() : 0;
		return 1 + Math.max(leftHeight, rightHeight);
	}

	// Static method to calculate height of a given node
	public static <E> int getHeight(BSTreeNode<E> node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = getHeight(node.getLeft());
		int rightHeight = getHeight(node.getRight());
		return 1 + Math.max(leftHeight, rightHeight);
	}

	public String getOccurrences() {
		return Occurrences;
	}

	public void setOccurrences(String occurences) {
		Occurrences = occurences;
	}

	public Integer getFrequency() {
		return Occurrences.split(";").length;
	}

}
