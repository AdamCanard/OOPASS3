package implementations;

import java.util.NoSuchElementException;

import utilities.BSTreeADT;
import utilities.Iterator;

public class BSTree<E extends Comparable<E>> implements BSTreeADT<E>, Iterator<E> {

	// Iterator functions
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

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
