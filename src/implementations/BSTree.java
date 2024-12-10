package implementations;

import java.util.NoSuchElementException;

import utilities.BSTreeADT;
import utilities.Iterator;

import javax.swing.tree.TreeNode;

public class BSTree<E extends Comparable<E>> implements BSTreeADT<E>, Iterator<E> {

	private BSTreeNode<E> root;

	public BSTree() {

	}
	// Iterator functions
	@Override
	public boolean hasNext(){
//		return root.isEmpty();
		return false;
	}

	@Override
	public E next() throws NoSuchElementException {
		// TODO Auto-generated method stub

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
		if (root == null){
			return 0;
		}
		return root.getHeight();
	}

	@Override
	public int size() {
		if (root == null){
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BSTreeNode<E> search(E entry) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E newEntry) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BSTreeNode<E> removeMin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BSTreeNode<E> removeMax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> inorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> preorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> postorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
