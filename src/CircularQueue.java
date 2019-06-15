public class CircularQueue<T> {
    public CircularQueue(int capacity) {
        arr = (T[]) new Object[this.capacity = capacity];
    }

    private T[] arr;
    private final int capacity;
    private int cnt = 0;
    private int in = 0;
    private int out = 0;

    void offer(T e) throws QueueFullException {
        if (cnt > capacity) {
            show();
            throw new QueueFullException();
        }
        arr[in] = e;
        cnt++;
        in = (in + 1) % capacity;
    }

    T poll() throws QueueEmptyException{
        if (cnt == 0) {
            show();
            throw new QueueEmptyException();
        }
        T ret = arr[out];
        out = (out + 1) % capacity;
        cnt--;
        return ret;
    }
    void show(){
        System.out.printf(String.format("length:%d;", arr.length));
        for (T i :
                arr) {
            System.out.printf("%d ",i);
        }
        System.out.println();
    }
}
