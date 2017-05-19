import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Heap {

	private List<Integer> heap;

	public Heap() {
		this.heap = new ArrayList<>();
		this.heap.add(null);
	}

	public boolean isEmpty() {
		return this.heap.size() == 1;
	}

	public void add(int i) {
		this.heap.add(i);
		upheap();
	}

	public int getMax() {
		int result = this.heap.get(1);
		int lastIndex = this.heap.size() - 1;
		swap(this.heap.get(1), this.heap.get(lastIndex));
		this.heap.remove(lastIndex);
		downheap();
		return result;
	}

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

	private void upheap() {
		if (isEmpty()) {
			return;
		}
		int currentIndex = this.heap.size() - 1;
		int current = this.heap.get(currentIndex);
		int parentIndex = (currentIndex) / 2; // int division intended
		if (parentIndex < 1) {
			return;
		}
		int parent = this.heap.get(parentIndex);
		while (parent < current) {
			swap(parent, current);
			currentIndex = parentIndex;
			parentIndex = (currentIndex) / 2;
			if (parentIndex < 1) {
				return;
			}
			parent = this.heap.get(parentIndex);
		}
	}

	private void downheap() {
		// middle
		if (isEmpty()) {
			return;
		}
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
		while (current < left || current < right) {
			if (left > right) {
				swap(current, left);
				currentIndex = leftIndex;
			} else {
				swap(current, right);
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

	private void swap(int i, int j) {
		int posj = this.heap.indexOf(j);
		int posi = this.heap.indexOf(i);
		this.heap.set(posj, i);
		this.heap.set(posi, j);
	}
}
