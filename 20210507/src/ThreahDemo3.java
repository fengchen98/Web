/**
 * @author FCCC
 * @version 1.0
 * @date 2021/5/10 8:52
 */
public class ThreahDemo3 {
    public static void main(String[] args) {
        Object lockA=new Object();
        Object lockB=new Object();

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockA){
                    System.out.println("线程1获得锁A");
                    System.out.println("线程1尝试获得锁B");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockB){
                        System.out.println("线程1获得锁B");
                    }
                }
            }
        },"t1");
        t1.start();

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockB){
                    System.out.println("线程2获得锁B");
                    System.out.println("线程2尝试获得锁A");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockA){
                        System.out.println("线程2获得锁A");
                    }
                }

            }
        },"t2");
        t2.start();
    }
}
