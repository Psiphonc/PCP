public class QueueEmptyException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("consume failed");

    }
}
