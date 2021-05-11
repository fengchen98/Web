/**
 * @author FCCC
 * @version 1.0
 * @date 2021/5/7 10:31
 */
//有三个线程，线程名称分别为：a，b，c。
//每个线程打印自己的名称。
//需要让他们同时启动，并按 c，b，a的顺序打印
public class ThreadDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        Thread t1=new Thread(runnable,"a");
        Thread t2=new Thread(runnable,"b");
        Thread t3=new Thread(runnable,"c");
        t3.start();
        t3.join();

        t2.start();
        t2.join();

        t1.start();
        t1.join();

    }
}
