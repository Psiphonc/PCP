import java.util.Random;
import java.util.concurrent.Semaphore;

public class PandC {
    CircularQueue buffer = new CircularQueue<Integer>(20);
    Producer P = new Producer();
    Consumer C = new Consumer();
    Semaphore semaphore=new Semaphore(1);

    class Producer implements Runnable {
        void produce() throws InterruptedException {
            int randomNum;
            while (true) {
                randomNum = new Random().nextInt(100) + 1;
                semaphore.acquire();
                try {
                    buffer.offer(randomNum);
                    System.out.println("Producer:" + randomNum);
                } catch (QueueFullException e) {
                    e.printStackTrace();
                    continue;
                }
                finally {
                    semaphore.release();
                }
                if (randomNum == 100) break;
            }
        }

        @Override
        public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        void consume() throws InterruptedException {
            while (true) {
                semaphore.acquire();
                try {
                    int num = (int) buffer.poll();
                    System.out.println("cunsumer:" + num);
                    if (num == 100) break;
                } catch (QueueEmptyException e) {
                    e.printStackTrace();
                }
                finally {
                    semaphore.release();
                }
            }
        }

        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PandC pc = new PandC();
        Thread P = new Thread(pc.P);
        Thread C = new Thread(pc.C);
        P.start();
        C.start();
    }

}
