
import java.util.Iterator;
import java.util.Stack;

public class BSTIterator<T extends Comparable> implements Iterator<T>, Iterable<T>{

	private Node<T> root, next;
	public Stack<Node<T>> stack;
	
	public BSTIterator(Node root) {
		this.root = root;
		next = root;
		stack = new Stack();
	}
	
	public boolean hasNext() {
		if (!stack.empty() || next != null) {
			return true;
		}
		return false;
	}
	
	public T next() {
		T nextData;
		while (next != null) {
			stack.push(next);
			next = next.left;
		}
		next = stack.pop();
		nextData = (T)next.data;
		next = next.right;
		return nextData;
	}
	
	public void remove() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Operation is not supported.");
	}
	
	public Iterator<T> iterator() {
		return this;
	}
}
