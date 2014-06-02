import java.util.Iterator;

public class BinarySearchTree<T extends Comparable> {

	private Node root;
	
	public BinarySearchTree() {
		root = null;
	}
	
	public BinarySearchTree(Node root) {
		this.root = root;
	}
	
	public Node getMax() {
		return getMax(root);
	}
	
	private Node getMax(Node root) {
		if (root == null) {
			return null;
		} else if (root.right == null) {
			return root;
		} else {
			return getMax(root.right);
		}
	}
	
	public Node getMin() {
		return getMin(root);
	}
	
	private Node getMin(Node root) {
		if (root == null) {
			return null;
		} else if (root.right == null) {
			return root;
		} else {
			return getMin(root.left);
		}
	}
	
	public boolean search(T data) {
		return search(root, data);
	}
	
	private boolean search(Node root, T data) {
		if (root == null) {
			return false;
		} else if (root.data == data) {
			return true;
		} else if (data.compareTo(root.data) < 0) {
			return search(root.left, data);
		} else {
			return search(root.right, data);
		}
	}
	
	public void add(T data) {
		root = add(root, data);
	}
	
	private Node add(Node root, T data) {
		if (!search(data)) {
			if (root == null) {
				root = new Node(data);
			} else if (data.compareTo(root.data) < 0) {
				root.left = add(root.left, data);
			} else {
				root.right = add(root.right, data);
			}
			return root;
		} else {
			return null;
		}
	}
	
	public boolean delete(T data) {
		if (search(data)) {
			root = delete(root, data);
			return true;
		} else {
			return false;
		}
	}
	
	private Node delete(Node root, T data) {
		if (root == null) {
			return null;
		} else if (root.data == data) {
			if (root.left != null && root.right != null) {
				Node minFromRight = getMin(root.right);
				root.data = minFromRight.data;
				root.right = delete(root.right, (T)minFromRight.data);
				return root;
			} else if (root.right != null) {
				return root.right;
			} else {
				return root.left;
			}
		} else if (data.compareTo(root.data) < 0) {
			return delete(root.left, data);
		} else {
			return delete(root.right, data);
		}
	}
	
	public void addTree(BinarySearchTree bST) {
		BSTIterator<T> iter = new BSTIterator<T>(bST.root);
		for (T item : iter) {
			if (!search(item)) {
				add(item);
			}
		}
	}
	
	public boolean searchIter(T data) {
		BSTIterator<T> iter = new BSTIterator<T>(root);
		for(T next : iter) {
			if (data.equals(next)) {
				return true;
			}
		}
		return false;
	}
}
