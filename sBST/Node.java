
public class Node <T extends Comparable> {
	
	public T data;
	public Node left;
	public Node right;
	
	public Node(T data) {
		this(data, null, null);
	}
	
	public Node(T data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

}
