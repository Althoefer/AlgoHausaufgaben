
public class TestHeap {

	public static void main(String[] args) {
		int[] itemsToAdd = new int[] { 1, 6, 8, 18, 23, 5, 17, 20, 26, 21, 9 };
		Heap heap = new Heap();
		System.out.println(heap);
		for (int i = 0; i < itemsToAdd.length; ++i) {
			heap.add(itemsToAdd[i]);
			System.out.println(heap);
		}
		while (!heap.isEmpty()) {
			System.out.println(heap.getMax());
			System.out.println(heap);
		}
	}

}
