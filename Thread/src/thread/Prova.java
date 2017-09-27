/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author FortuF100
 */
public class Prova {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Thread[] ehi=new Thread[10];
        for(int i=0; i<10; i++) {
            ehi[i]=new Thread(new FileClock());
            ehi[i].setName("Thread "+i);
            ehi[i].start();
        }
        Random gen=new Random();
        for(int i=0; i<10; i++) {
        try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //System.out.println(i+"  "+ehi[i].getState());
            if(!ehi[i].isInterrupted()) {
                int num=gen.nextInt(10);
                ehi[num].interrupt();
                System.out.println(ehi[i].getName()+" has interrupted "+ehi[num].getName());
            }
        System.out.println(i+" Stato "+ehi[i].getState());
        }
    }
    
}

