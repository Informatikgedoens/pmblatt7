import java.util.Iterator;

public class BinarySearchTree<T extends Comparable> {

	private Node<T> root;
	
	private String[][] output;
	
	private int depth;
	
	public BinarySearchTree() {
		root = null;
	}
	
	public BinarySearchTree(Node<T> root) {
		this.root = root;
	}
	
	public Node<T> getMax() {
		return getMax(root);
	}
	
	private Node<T> getMax(Node<T> root) {
		if (root == null) {
			return null;
		} else if (root.right == null) {
			return root;
		} else {
			return getMax(root.right);
		}
	}
	
	public Node<T> getMin() {
		return getMin(root);
	}
	
	private Node<T> getMin(Node<T> root) {
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
	
	private boolean search(Node<T> root, T data) {
		if (root == null) {
			return false;
		} else if (data.compareTo(root.data) == 0) {
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
	
	private Node<T> add(Node<T> root, T data) {
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
	
	private Node<T> delete(Node<T> root, T data) {
		if (root == null) {
			return null;
		} else if (data.compareTo(root.data) < 0) {
			root.left = delete(root.left, data);
			return root;
		} else if (data.compareTo(root.data) > 0) {
			root.right = delete(root.right, data);
			return root;
		} else if (data.equals(root.data)) {
			if (root.left != null && root.right != null) {
				Node<T> min = getMin(root.right);
				root.data = min.data;
				root.right = delete(root.right, min.data);
				return root;
			} else if (root.right != null) {
				root = root.right;
				return root;
			} else if (root.left != null) {
				root = root.left;
				return root;
			} else {
				root = null;
				return root;
			}
		}
		return null;
	}
	
	public void addTree(BinarySearchTree<T> bST) {
		BSTIterator<T> iter = new BSTIterator<T>(bST.root);
		for (T item : iter) {
			if (!search(item)) {
				add(item);
			}
		}
	}
	
	public void displayTree() {
		if (root != null) {
			depth = getDepth();
			int width = 2 * depth + 1;
			int lines = 4 * (int) Math.pow(2, depth) - 3;
			output = new String[lines][width];
			for (int i = 0; i < lines; i++) {
				for (int j = 0; j < width; j++) {
					output[i][j] = " ";
				}
			}
			drawTree();
			displayTree(lines, width);
		} else {
			System.out.println("BST has no root.");
		}
	}
	
	private void displayTree(int lines, int width) {
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(output[i][j]);
			}
			System.out.println();
		}
	}
	
	private void drawTree() {
		int y = 0;
		int x = output.length / 2;
		String data = (String) root.data;
		output[x][y] = data;
		drawLeft(root.left, y, x);
		drawRight(root.right, y, x);
	}
	
	private void drawLeft(Node<T> root, int y, int x) {
		if (root != null) {
			int[] xyRoot = drawLeftH(root, y, x);
			drawLeft(root.left, xyRoot[0], xyRoot[1]);
			drawRight(root.right, xyRoot[0], xyRoot[1]);
		}
	}
	
	private void drawRight(Node<T> root, int y, int x) {
		if (root != null) {
			int[] xyRoot = drawRightH(root, y, x);
			drawLeft(root.left, xyRoot[0], xyRoot[1]);
			drawRight(root.right, xyRoot[0], xyRoot[1]);
		}
	}
	
	private int[] drawLeftH(Node<T> root, int y, int x) {
		int length = calcLength(y);
		int yRoot = y + 2;
		int xRoot = x + length;
		for (int i = x + 1; i < xRoot + 1; i++) {
			output[i][y] = "|";
		}
		output[xRoot][yRoot - 1] = "-";
		String data = (String) root.data;
		output[xRoot][yRoot] = data;
		int[] xyRoot = new int[2];
		xyRoot[0] = yRoot;
		xyRoot[1] = xRoot;
		return xyRoot;
	}
	
	private int[] drawRightH(Node<T> root, int y, int x) {
		int length = calcLength(y);
		int yRoot = y + 2;
		int xRoot = x - length;
		for (int i = xRoot; i < x; i++) {
			output[i][y] = "|";
		}
		output[xRoot][yRoot - 1] = "-";
		String data = (String) root.data;
		output[xRoot][yRoot] = data;
		int[] xyRoot = new int[2];
		xyRoot[0] = yRoot;
		xyRoot[1] = xRoot;
		return xyRoot;
	}
	
	private int calcLength(int y) {
		return (int) Math.pow(2, depth - y / 2);
	}
	
	private int getDepth() {
		return getDepth(root) - 1;
	}
	
	private int getDepth(Node<T> root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(getDepth(root.left), getDepth(root.right));
	}
}
