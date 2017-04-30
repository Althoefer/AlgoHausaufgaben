/**
 * Class MyDeque implements a double ended queue with basic insertion and
 * deletion functionality
 * 
 * @author C.Wassermann
 *
 */
public class MyDeque {
	// pointer to the first and last element in the deque
	private Node first = null;
	private Node last = null;

	/**
	 * Insert a new element at the front of the deque
	 * 
	 * @param node
	 *            which is inserted
	 */
	public void addFirst(Node node) {
		node.prev = null;
		node.next = this.first;
		this.first = node;
		if (this.first.next != null) {
			this.first.next.prev = this.first;
		}
	}

	/**
	 * Insert a new element at the end of the deque
	 * 
	 * @param node
	 *            which is inserted
	 */
	public void addLast(Node node) {
		node.prev = this.last;
		node.next = null;
		this.last = node;
		if (this.last.prev != null) {
			this.last.prev.next = this.last;
		}
	}

	/**
	 * Removes the first element from the deque and returns its content
	 * 
	 * @return the content of the element which is removed
	 */
	public String removeFirst() {
		if (first != last && first != null) { // the list has at least 2 nodes
			String ret = first.content;
			first = first.next;
			if (first != null) {
				first.prev = null;
			}
			return ret;
		}
		if (first != null) { // list has only 1 element
			String ret = first.content;
			first = null;
			last = null;
			return ret;
		}
		return null;
	}

	/**
	 * Removes the last element from the deque and returns its content
	 * 
	 * @return the content of the element which is removed
	 */
	public String removeLast() {
		if (first != last && last != null) { // the list has at least 2 nodes
			String ret = last.content;
			last = last.prev;
			if (last != null) {
				last.next = null;
			}
			return ret;
		}
		if (first != null) { // list has only 1 element
			String ret = first.content;
			first = null;
			last = null;
			return ret;
		}
		// list has no elements in it
		return null;
	}

	/**
	 * Checks if the deque is empty
	 * 
	 * @return whether the deque is empty or not
	 */
	public boolean isEmpty() {
		if (first == null && last == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes all element from the deque
	 */
	public void clear() {
		first = null;
		last = null;
	}
}