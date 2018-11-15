public class LinkedNode {

	Restaurant value;
	LinkedNode previous;
	LinkedNode next;
	
	public LinkedNode() {
		// TODO Auto-generated constructor stub
	}
	
	public LinkedNode(Restaurant value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	
	public LinkedNode getPrevious() {
		return previous;
	}

	public void setPrevious(LinkedNode previous) {
		this.previous = previous;
	}

	public LinkedNode getNext() {
		return next;
	}

	public void setNext(LinkedNode next) {
		this.next = next;
	}

	public Restaurant getValue() {
		return value;
	}

	public void setValue(Restaurant value) {
		this.value = value;
	}
}
