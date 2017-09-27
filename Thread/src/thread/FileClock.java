/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

/**
 *
 * @author FortuF100
 */
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FileClock implements Runnable {
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Random r=new Random();
            try {
                TimeUnit.SECONDS.sleep(r.nextInt(10));
            } catch (InterruptedException e) {
                System.out.printf("%s\n: %s\n", new Date(), Thread.currentThread().getName());
                System.out.println("The FileClock "+Thread.currentThread().getName()+" has been interrupted");
            }
        }
    }
}
