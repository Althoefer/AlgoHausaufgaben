import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class Heap implements a field embedded heap
 * 
 * @author C.Wassermann
 *
 */
public class Heap {

	// field embedded heap
	// from child to parent: childIndex / 2
	// from parent to left child: parentIndex * 2
	// from parent to right child: parentIndex * 2 + 1
	private List<Integer> heap;

	/**
	 * Default constructor which creates an empty heap
	 */
	public Heap() {
		// new field with dummy element
		this.heap = new ArrayList<>();
		this.heap.add(null);
	}

	/**
	 * Check if the heap is empty
	 * 
	 * @return whether the heap is empty or not
	 */
	public boolean isEmpty() {
		// only dummy element in heap
		return this.heap.size() == 1;
	}

	/**
	 * Add a new element to the heap
	 * 
	 * @param i
	 *            value which is added to the heap
	 */
	public void add(int i) {
		// simply adding to the field so the heap
		// remains left complete (heap condition)
		this.heap.add(i);
		// reestablish heap condition (sift up)
		upheap();
	}

	/**
	 * Remove the largest value from the heap
	 * 
	 * @return the largest value
	 * @throws IndexOutOfBoundsException
	 *             if heap is already empty
	 */
	public int getMax() throws IndexOutOfBoundsException {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("Heap is already empty!");
		}
		// remove first element and replace with last
		// so the heap remains left complete (heap condition)
		int result = this.heap.get(1);
		int lastIndex = this.heap.size() - 1;
		swap(1, lastIndex);
		this.heap.remove(lastIndex);
		// reestablish heap condition (sift down)
		downheap();
		return result;
	}

	/**
	 * ToString method: Get a textual representation of the heap
	 */
	public String toString() {
		Iterator<Integer> iter = this.heap.iterator();
		iter.next(); // skip dummy value
		if (!iter.hasNext()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			Integer value = iter.next();
			sb.append(value);
			if (!iter.hasNext()) {
				return sb.append(']').toString();
			}
			sb.append(", ");
		}
	}

	// sift up to reestablish heap condition
	private void upheap() {
		if (isEmpty()) {
			return;
		}
		// initialize variables with valid values
		int currentIndex = this.heap.size() - 1;
		int current = this.heap.get(currentIndex);
		int parentIndex = (currentIndex) / 2; // integer division intended
		if (parentIndex < 1) {
			return;
		}
		int parent = this.heap.get(parentIndex);
		// swap parent of current & current as long as
		// parent is smaller than current (heap condition)
		while (parent < current) {
			swap(parentIndex, currentIndex);
			currentIndex = parentIndex;
			parentIndex = (currentIndex) / 2;
			if (parentIndex < 1) {
				return;
			}
			parent = this.heap.get(parentIndex);
		}
	}

	// sift down to reestablish heap condition
	private void downheap() {
		if (isEmpty()) {
			return;
		}
		// initialize variables with valid values
		// middle
		int currentIndex = 1;
		int current = this.heap.get(currentIndex);
		// left
		int leftIndex = currentIndex * 2;
		int left = Integer.MIN_VALUE;
		if (this.heap.size() > leftIndex) {
			left = this.heap.get(leftIndex);
		}
		// right
		int rightIndex = currentIndex * 2 + 1;
		int right = Integer.MIN_VALUE;
		if (this.heap.size() > rightIndex) {
			right = this.heap.get(rightIndex);
		}
		// swap with larger child value as long as
		// parent is not yet larger than both child value
		while (current < left || current < right) {
			if (left > right) { // left bigger
				swap(currentIndex, leftIndex);
				currentIndex = leftIndex;
			} else { // right bigger
				swap(currentIndex, rightIndex);
				currentIndex = rightIndex;
			}
			// left
			leftIndex = currentIndex * 2;
			left = Integer.MIN_VALUE;
			if (this.heap.size() > leftIndex) {
				left = this.heap.get(currentIndex * 2);
			}
			// right
			rightIndex = currentIndex * 2 + 1;
			right = Integer.MIN_VALUE;
			if (this.heap.size() > rightIndex) {
				right = this.heap.get(currentIndex * 2 + 1);
			}
		}
	}

	// swap element at index i and j
	private void swap(int i, int j) {
		int temp = this.heap.get(i);
		this.heap.set(i, this.heap.get(j));
		this.heap.set(j, temp);
	}
}
