
public class MyDeque {
	private Node first = null;
	private Node last = null;

	public void addFirst(Node node) {
		node.prev = null;
		node.next = this.first;
		this.first = node;
		if (this.first.next != null) {
			this.first.next.prev = this.first;
		}
	}

	public void addLast(Node node) {
		node.prev = this.last;
		node.next = null;
		this.last = node;
		if (this.last.prev != null) {
			this.last.prev.next = this.last;
		}
	}

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

	public boolean isEmpty() {
		if (first == null && last == null) {
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		first = null;
		last = null;
	}
}