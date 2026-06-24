class MinHeap {

    static int[] heap;
    static int size;

    MinHeap(int cap) {
        heap = new int[cap];
    }

    static void insert(int val) {

        heap[size] = val;

        heapifyUp(size);

        size++;
    }

    static int removeMin() {

        int min = heap[0];

        heap[0] = heap[size - 1];

        size--;

        heapifyDown(0);

        return min;
    }

    static void heapifyUp(int i) {

        while (i > 0) {

            int p = (i - 1) / 2;

            if (heap[p] <= heap[i]) break;

            swap(i, p);

            i = p;
        }
    }

    static void heapifyDown(int i) {

        while (true) {

            int l = 2 * i + 1;
            int r = 2 * i + 2;

            int smallest = i;

            if (l < size && heap[l] < heap[smallest]) {
                smallest = l;
            }

            if (r < size && heap[r] < heap[smallest]) {
                smallest = r;
            }

            if (smallest == i) break;

            swap(i, smallest);

            i = smallest;
        }
    }

    static void swap(int i, int j) {

        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    static int getTop() {
        return heap[0];
    }
    static int pop() {
        return removeMin();
    }

    static void add(int val) {
        insert(val);
    }

    public static void main(String[] args) {
        MinHeap heap=new MinHeap(10);
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        System.out.println(getTop());
        pop();
        System.out.println(getTop());
    }
}