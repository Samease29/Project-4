
public class AVLTreeNode {

	Restaurant value;
	AVLTreeNode left;
	AVLTreeNode right;
	int level;
	
	public AVLTreeNode() {
		// TODO Auto-generated constructor stub
	}
	
	public AVLTreeNode(Restaurant value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	
	public AVLTreeNode(Restaurant value, int level) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.level = level;
	}
	
	public AVLTreeNode getleft() {
		return left;
	}

	public void setLeft(AVLTreeNode left) {
		this.left = left;
	}

	public AVLTreeNode getRight() {
		return right;
	}

	public void setRight(AVLTreeNode right) {
		this.right = right;
	}

	public Restaurant getValue() {
		return value;
	}

	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setValue(Restaurant value) {
		this.value = value;
	}
}
