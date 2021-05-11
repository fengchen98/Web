/**
 * @author FCCC
 * @version 1.0
 * @date 2021/5/7 10:10
 */
public class ThreadDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads=new Thread[20];
        Object lock=new Object();
        for (int i = 0; i < 20; i++) {
            final int n=i;
            threads[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (lock){
                        System.out.println(n);
                    }
                }
            });
            threads[i].start();
            threads[i].join();
        }
        System.out.println("ok");
    }
}
