/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileclock;

import java.util.concurrent.TimeUnit;
import  java.util.Random;
/**
 *
 * @author FortuF100
 */
public class FileClockInterrupt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread[] threads=new Thread[10];
        Thread.State[] status=new Thread.State[10];
        Random ciao=new Random();
        for(int i=0; i<10; i++) {
            threads[i]=new Thread(new Interrupt());
            threads[i].setName("Thread :"+i);
            threads[i].start();
            System.out.println("Thread "+i+" starting");
        }
        for(int i=0; i<10; i++) {
            System.out.println("Status of thread "+i+" :"+threads[i].getState());
        }
        for(int i=0; i<10; i++) {
            int time=ciao.nextInt(11);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int ehi=ciao.nextInt(10);
            threads[ehi].interrupt();
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread "+i+" has interrupted thread "+ehi+" after "+time+" seconds");
        }
        
    }
    
    
    
}
class Interrupt implements Runnable {
        private int i=0;
        @Override
        public void run() {
            System.out.println("Hello from "+Thread.currentThread().getName());
            for(int j=0; j<10; j++) {
                System.out.println("Il valore di "+Thread.currentThread().getName()+" è "+i++);
            }
        }
    }