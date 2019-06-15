import java.util.Random;

public class PandC {
    CircularQueue buffer = new CircularQueue<Integer>(20);
    Producer P = new Producer();
    Consumer C = new Consumer();

    class Producer implements Runnable {
        void produce() {
            int randomNum;
            while (true) {
                randomNum = new Random().nextInt(100) + 1;
                try {
                    buffer.offer(randomNum);
                } catch (QueueFullException e) {
                    e.printStackTrace();
                    continue;
                }
                System.out.println("Producer:" + randomNum);
                if (randomNum == 100) break;
            }
        }

        @Override
        public void run() {
            produce();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        void consume() {
            while (true) {
                try {
                    int num = (int) buffer.poll();
                    System.out.println("cunsumer:" + num);
                    if (num == 100) break;
                } catch (QueueEmptyException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            consume();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PandC pc = new PandC();
        Thread P = new Thread(pc.P);
        Thread C = new Thread(pc.C);
        P.start();
        C.start();
    }

}
