
public class BSTMain {
	
	private static boolean isProgress = true; 

	private static final String MAIN_ERROR = "Error! No such command!";
	
	public static void main(String[] args) {
		BinarySearchTree<String> bst = new BinarySearchTree();
		while(isProgress) {
			mainMenu();
			String prompt = Terminal.askString("> ");
			String[] input = prompt.split(" ");
			if (input.length == 1) {
				if (input[0].equals("4")) {
					bst.displayTree();
				} else if (input[0].equals("5")) {
					System.exit(0);
				} else {
					System.err.println(MAIN_ERROR);
				}
			} else if (input.length == 2) {
				int i = 0;
				switch (input[0]) {
				case "1":
					bst.add(input[1]);
					break;
				case "2":
					if(bst.search(input[1])) {
						System.out.println("Found data.");
					} else {
						System.out.println("No such data.");
					}
					break;
				case "3":
					bst.delete(input[1]);
					break;
				}
			}
		}
	}
	
	private static void mainMenu() {
		System.out.println("Sorted BinarySearchTree (Int)");
		System.out.println("1 \"Int\" - Add Element");
		System.out.println("2 \"Int\" - Search Element");
		System.out.println("3 \"Int\" - Delete Element");
		System.out.println("4 - Print BinarySearchTree");
		System.out.println("5 - Beenden");
	}
} 
