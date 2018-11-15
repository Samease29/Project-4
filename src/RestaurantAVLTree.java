import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class RestaurantAVLTree {

	AVLTreeNode root;
	int size;
	int lowestLevel;
	int shortestSubTree;
	int filledInLowest;
	int filledInSecondLowest;
	
	public RestaurantAVLTree() {
		this.size = 0;
	}
	
	public void sizeUp() {
		this.size += 1;
	}
	
	public void sizeDown() {
		this.size -= 1;
	}

	public AVLTreeNode getRoot() {
		return root;
	}

	public void setRoot(AVLTreeNode root) {
		this.root = root;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setLowestLevel(int lowestLevel) {
		this.lowestLevel = lowestLevel;
	}
	
	public int getLowestLevel() {
		return lowestLevel;
	}
	
	public void setShortestSubTree(int height) {
		this.shortestSubTree = height;
	}
	
	public int getShortestSubTree() {
		return shortestSubTree;
	}
	
	public int getFilledInLowest() {
		return filledInLowest;
	}

	public void setFilledInLowest(int filledInLowest) {
		this.filledInLowest = filledInLowest;
	}

	public int getFilledInSecondLowest() {
		return filledInSecondLowest;
	}

	public void setFilledInSecondLowest(int filledInSecondLowest) {
		this.filledInSecondLowest = filledInSecondLowest;
	}
	
	public void add(Restaurant restaurant, String wayToCompare) {
		boolean contains = false;
		if(!restaurant.isHasID()) {
			Restaurant.setCOMPLETE();
		}
		if(!Restaurant.isCOMPLETE()) {
			if(restaurant == null) {
				return;
			}else if(size == 0) {
				AVLTreeNode node = new AVLTreeNode(restaurant, 0);
				this.root = node;
				this.lowestLevel = 0;
				this.shortestSubTree = 0;
			}else if(size == 1) {
				if(wayToCompare.equalsIgnoreCase("id")) {
					
				}else if(wayToCompare.equalsIgnoreCase("name") && !restaurant.isHasName()) {
					
				}else if(wayToCompare.equalsIgnoreCase("average grade score") && restaurant.isHasGradeAverage()) {
					
				}
				if(root.getValue().compareRestaurants(restaurant, wayToCompare) < 0) {
					AVLTreeNode node = new AVLTreeNode(restaurant, 1);
					this.lowestLevel = 1;
					this.filledInLowest = 1;
					this.filledInSecondLowest = 1;
				}else if(root.getValue().compareRestaurants(restaurant, wayToCompare) >= 0) {//this is wrong but it may be unnecessary
					/*
					 Look over all of this
					 Might be completely unnecessary
					 */
					AVLTreeNode node = new AVLTreeNode(restaurant, 1);
					root.setRight(node);
					setLowestLevel(1);
					setFilledInLowest(1);
					setFilledInSecondLowest(1);
				}
			}else if(size == 2) {
				Queue<AVLTreeNode> queue = (Queue<AVLTreeNode>) new ArrayList<AVLTreeNode>();
				queue.add(root);
				AVLTreeNode current = null;
				boolean done = false;
				while(!contains && !queue.isEmpty() && !done) {
					current = queue.poll();
					if(current.getValue().compareRestaurants(restaurant, wayToCompare) < 0) {
						if(current.getleft() == null) 
							done = true;
						queue.add(current.getleft());
					}else if(current.getValue().compareRestaurants(restaurant, wayToCompare) == 0) {
						contains = true;
					}else if(current.getValue().compareRestaurants(restaurant, wayToCompare) > 0) {
						if(current.getRight() == null)
							done = true;
						queue.add(current.getRight());
					}
				}
				if(!contains) {
					AVLTreeNode node = new AVLTreeNode(restaurant, current.getLevel() + 1);
					if(shortestSubTree > node.getLevel()) {
						shortestSubTree = node.getLevel();
					}
					if(this.lowestLevel < node.getLevel()) {
						this.filledInSecondLowest = this.filledInLowest;
						this.filledInLowest = 1;
						this.lowestLevel = node.getLevel();
					}else if(node.getLevel() == this.lowestLevel + 1)
						this.filledInSecondLowest += 1;
					else if(node.getLevel() == this.lowestLevel)
						this.filledInLowest += 1;
				}
				if(shortestSubTree - lowestLevel <= -2) {
					
				}
			}

			
			if(!Restaurant.isCOMPLETE() && !contains)
				this.size += 1;
		}
	}


	public Restaurant purge(Restaurant restaurant, String wayToCompare) {
		if(this.root == null || this.size == 0)
			return null;
		
		if(matchingRestaurant(this.root.getValue(), restaurant, wayToCompare)) {
			Restaurant toReturn = this.root.getValue();
			//Some method of reorginizing this bitch
			if(this.head != null)
				this.head.setPrevious(null);
			this.size = this.size - 1;
			return toReturn;
		}else if(matchingRestaurant(this.tail.getValue(), restaurant, wayToCompare)) {
			Restaurant toReturn = this.tail.getValue();
			this.tail = this.tail.getPrevious();
			this.tail.setNext(null);
			this.size = this.size - 1;
			return toReturn;
		}else if(this.size > 1) {
			LinkedNode previous = null;
			LinkedNode current = this.head;
			while(current.getNext() != null && !matchingRestaurant(current.getValue(), restaurant, wayToCompare)) {
				current = current.getNext();
			}if(current != null) {
				previous = current.getPrevious();
				previous.setNext(current.getNext());
				if(current.getNext() != null)
					previous.getNext().setPrevious(previous);
				this.size = this.size - 1;
				return current.getValue();
			}
		}
		return null;
	}
	
	public boolean matchingRestaurant(Restaurant one, Restaurant two, String wayToCompare) {
		if(wayToCompare.equals("id") && one.compareRestaurants(two, "id") == 0) 
			return true;
		else if(wayToCompare.equals("name") && one.compareRestaurants(two, "name") == 0)
			return false;
		return false;
	}
	
	public boolean matchesComparison(Restaurant restaurant, String wayToCompare, String compareTo) {
		if(wayToCompare.equals("id") && restaurant.getID() == Integer.parseInt(compareTo))
			return true;
		if(wayToCompare.equals("name") && restaurant.getName().equalsIgnoreCase(compareTo))
			return true;
		if(wayToCompare.equals("cuisine") && restaurant.getCuisine().equalsIgnoreCase(compareTo))
			return true;
		return false;
	}
}
