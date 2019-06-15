public class QueueFullException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("Queue Full");
    }
}
