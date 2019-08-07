package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolTest {
    
    private static ForkJoinPool forkJoinPool = new ForkJoinPool();
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(100));
    private static String path = "C:\\ICF_AutoCapsule_disabled\\save\\Test";

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        // TODO 自動生成されたメソッド・スタブ
        /*forkJoinPool.submit(()->{
            threadPrint();
        }).join();
        forkJoinPool.shutdown();*/
        /*for(int i = 0; i < 50;i++) {
            Random ran = new Random(System.currentTimeMillis());
            int ranInt = ran.nextInt();
            //System.out.println(ranInt);
            Task mytask = new Task(i);
            threadPoolExecutor.execute(mytask);
        }*/
        threadPrint();
    }
    
    private static void threadPrint() throws InterruptedException, ExecutionException {
        Future f = null;
        for(int i = 0; i < 10;i++) {
            int j = i;
                f=threadPoolExecutor.submit(()->{
                    try {
                        readProcessor();
                        writeProcessor();
                        System.out.println("--"+j+"---");
                    } catch (IOException e) {
                        // TODO 自動生成された catch ブロック
                        e.printStackTrace();
                    }
                });
        }
        f.get();
    }
    
    private static void readProcessor() throws IOException {
        try {
            Thread.sleep(1000);
            System.out.println("read : "+Thread.currentThread().getName());
            File file = new File(path+Thread.currentThread().getName()+".txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(Thread.currentThread().getName());
            fw.flush();
            fw.close();
        } catch (InterruptedException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
    
    private static void writeProcessor() throws IOException {
        try {
            Thread.sleep(1000);
            System.out.println("write : "+Thread.currentThread().getName());
            File file = new File(path+Thread.currentThread().getName()+".txt");
            FileWriter fw = new FileWriter(file,true);
            fw.write(Thread.currentThread().getName());
            fw.flush();
            fw.close();
        } catch (InterruptedException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
}
final class Task implements Runnable{
    private int i;
    
    public Task(int i) {
        this.i=i;
    }
    
    @Override
    public void run() {
        try {
            //Thread.sleep((50-i)*10);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("This is "+Thread.currentThread().getName());
    }
}
