package implementations;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Stack;

import utilities.BSTreeADT;
import utilities.Iterator;

public class BSTree<E extends Comparable<E>> implements BSTreeADT<E>, Iterator<E>, Serializable {

	private BSTreeNode<E> root;
	// 0 = in, 1, pre, 2, post
	private int iterateMode = 0;
	private Stack<BSTreeNode<E>> nodeStack;
	private Stack<Boolean> expanded;

	// Iterator functions
	@Override
	public boolean hasNext() {
		switch (iterateMode) {
		case 0:
			return !nodeStack.isEmpty();
		case 1:
			break;
		case 2:
			if (nodeStack.isEmpty()) {
				return false;
			}
			while (!expanded.peek()) {
				expanded.pop();
				expanded.push(true);
				BSTreeNode<E> node = nodeStack.peek();
				if (node.getRight() != null) {
					nodeStack.push(node.getRight());
					expanded.push(false);
				}
				if (node.getLeft() != null) {
					nodeStack.push(node.getLeft());
					expanded.push(false);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public E next() throws NoSuchElementException {
		switch (iterateMode) {
		case 0:
			if (!hasNext())
				throw new NoSuchElementException();
			BSTreeNode<E> node = nodeStack.pop();
			if (node.getRight() != null) {
				moveLeft(node.getRight());
			}
			return node.getElement();
		case 1:
			if (!hasNext())
				throw new NoSuchElementException();
			BSTreeNode<E> currentNode = root;
			while (!nodeStack.empty() || currentNode != null) {
				if (currentNode != null) {
					nodeStack.push(currentNode);
					currentNode = currentNode.getLeft();
				} else {
					BSTreeNode<E> node2 = nodeStack.pop();
					return node2.getElement();
				}
			}
		case 2:
			if (!hasNext()) {
				throw new NoSuchElementException("End reached");
			}
			expanded.pop();
			return nodeStack.pop().getElement();
		}
		return null;
	}

	// BSTree Functions
	@Override
	public BSTreeNode<E> getRoot() throws NullPointerException {
		if (root == null) {
			throw new NullPointerException();
		}
		return root;
	}

	@Override
	public int getHeight() {
		if (root == null) {
			return 0;
		}
		return root.getHeight();
	}

	@Override
	public int size() {
		if (root == null) {
			return 0;
		}
		return root.getNumberNodes();
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		if (root != null) {
			root = null;
		}
	}

	@Override
	public boolean contains(E entry) throws NullPointerException {
		if (entry == null || root == null) {
			throw new NullPointerException();
		}
		return traverse(root, entry) != null;
	}

	@Override
	public BSTreeNode<E> search(E entry) throws NullPointerException {
		if (entry == null || root == null) {
			throw new NullPointerException();
		}
		return traverse(root, entry);
	}

	private BSTreeNode<E> traverse(BSTreeNode<E> node, E target) {
		if (node == null) {
			return null;
		}

		if (node.getElement().equals(target)) {
			return node;
		}

		if (node.getElement().compareTo(target) > 0) {
			return traverse(node.getLeft(), target);
		}
		return traverse(node.getRight(), target);
	}

	@Override
	public boolean add(E newEntry) throws NullPointerException {
		if (newEntry == null) {
			throw new NullPointerException();
		}
		root = insert(root, newEntry);
		return true;
	}

	public BSTreeNode<E> insert(BSTreeNode<E> node, E newVal) {
		if (node == null) {
			root = new BSTreeNode<>(newVal);
			return root;
		}

		if (node.getElement().equals(newVal)) {
			return node;
		}

		if (node.getElement().compareTo(newVal) > 0) {
			node.setLeft(insert(node.getLeft(), newVal));
		} else {
			node.setRight(insert(node.getRight(), newVal));
		}
		return node;
	}

	@Override
	public BSTreeNode<E> removeMin() {
		if (root == null)
			return null;

		// Largest is always to the right, so if there's nothing there, root is greatest
		if (root.getRight() == null) {
			return root;
		}

		BSTreeNode<E> parent = root;
		BSTreeNode<E> child = parent.getLeft();
		while (child.getLeft() != null) {
			parent = child;
			child = child.getLeft();
		}
		parent.setLeft(child.getRight());
		return child;
	}

	@Override
	public BSTreeNode<E> removeMax() {
		if (root == null)
			return null;

		// Largest is always to the right, so if there's nothing there, root is greatest
		if (root.getRight() == null) {
			return root;
		}

		BSTreeNode<E> parent = root;
		BSTreeNode<E> child = parent.getRight();
		while (child.getRight() != null) {
			parent = child;
			child = child.getRight();
		}
		parent.setRight(child.getLeft());
		return child;
	}

	private void moveLeft(BSTreeNode<E> node) {
		while (node != null) {
			nodeStack.push(node);
			node = node.getLeft();
		}
	}

	@Override
	public Iterator<E> inorderIterator() {
		iterateMode = 0;
		nodeStack = new Stack<>();
		moveLeft(root);
		return this;
	}

	@Override
	public Iterator<E> preorderIterator() {
		iterateMode = 1;
		nodeStack = new Stack<>();
		return this;
	}

	@Override
	public Iterator<E> postorderIterator() {
		iterateMode = 2;
		nodeStack = new Stack<>();
		expanded = new Stack<>();
		if (root != null) {
			nodeStack.push(root);
			expanded.push(false);
		}
		return this;
	}

}
