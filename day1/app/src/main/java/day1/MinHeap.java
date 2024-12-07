package day1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface Heap<T extends Comparable<T>> extends Iterator<T> {
    void insert(T item);

    T pop();
}

public class MinHeap<T extends Comparable<T>> implements Heap<T> {
    private List<T> heapified;

    public MinHeap(List<T> items) {
        this.heapified = this.buildHeap(items);
    }

    private List<T> buildHeap(List<T> items) {
        List<T> data = new ArrayList<>();
        Iterator<T> itr = items.iterator();

        while (itr.hasNext()) {
            T item = itr.next();
            data.add(item);
        }

        // start from non-leaf node
        for (int i = (data.size() - 1) / 2; i >= 0; i--) {
            this.heapify(data, i);
        }

        return data;
    }

    private void heapify(List<T> items, int i) {
        int left = this.left(i);
        int right = this.right(i);
        int min = i;

        if (left >= items.size() || left < 0) {
            left = -1;
        }
        if (right >= items.size() || right < 0) {
            right = -1;
        }

        // is left smaller than min (i)
        if (left != -1 && items.get(left).compareTo(items.get(min)) < 0) {
            min = left;
        }

        // is right smaller than min
        if (right != -1 && items.get(right).compareTo(items.get(min)) < 0) {
            min = right;
        }

        if (min != i) {
            this.swap(items, i, min);

            this.heapify(items, min);
        }
    }

    private void swap(List<T> items, int i, int j) {
        T tmp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, tmp);
    }

    /**
     * Insert inserts an item into the list re-heapify it.
     * 
     * @implSpec {@code O(log(n))}
     * @param item
     */
    public void insert(T item) {
        this.heapified.add(item);

        int i = this.heapified.size() - 1;
        
        while (i > 0) {
            int parent = this.parent(i);

            T parentItem = this.heapified.get(parent);
            T currentItem = this.heapified.get(i);

            if (parentItem.compareTo(currentItem) > 0) {
                this.swap(heapified, i, parent);

                i = parent;
            }
        }
    }

    /**
     * Pop returns the root element of the tree and re-heapify it.
     * 
     * @implSpec {@code O(log(n))}
     * @return The smallest element in the list. Returns {@code null} if the heap is
     *         empty.
     */
    public T pop() {
        if (this.heapified.isEmpty()) {
            return null;
        }

        if (this.heapified.size() == 1) {
            return this.heapified.removeLast();
        }

        T root = this.heapified.get(0);
        T last = this.heapified.removeLast();
        this.heapified.set(0, last);

        this.heapify(this.heapified, 0);

        return root;
    }

    private int left(int i) {
        return (2 * i) + 1;
    }

    private int right(int i) {
        return (2 * i + 2);
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Returns all of the items raw.
     * 
     * @return List of items
     */
    public List<T> getItems() {
        return this.heapified;
    }

    @Override
    public boolean hasNext() {
        if (this.heapified.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public T next() {
        return this.pop();
    }
}
