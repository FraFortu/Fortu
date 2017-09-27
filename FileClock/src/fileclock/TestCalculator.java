/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileclock;

/**
 *
 * @author FortuF100
 */
import java.lang.Thread.State;
import java.util.concurrent.TimeUnit;

/************* THREAD STATE ***********************************************************************************

A thread can be in one of the following states:
-> NEW
A thread that has not yet started is in this state.

-> RUNNABLE
A thread executing in the Java virtual machine is in this state.

-> BLOCKED
A thread that is blocked waiting for a monitor lock is in this state.

-> WAITING
A thread that is waiting indefinitely for another thread to perform a particular action is in this state.

-> TIMED_WAITING
A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.

-> TERMINATED
A thread that has exited is in this state.

*******************************************************************************************************************/

public class TestCalculator {
    public static void main(String[] args) {
        // a pool of thread
        Thread threads[] = new Thread[10];
        // state array of previous thred
        Thread.State status[] = new State[10]; 
        // settings
        System.out.println("threads initialized");
        for (int i=0; i<10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if ((i%2)==0){ 
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread "+ i);
            status[i] = threads[i].getState();
            System.out.println("... status of " + threads[i].getName() + ": " + status[i]);
        }
        // start threads
        System.out.println("threads starting");
        for (int i=0; i<10; i++){
            threads[i].start();
            System.out.println("... status of " + threads[i].getName() + ": " + threads[i].getState());
        }
        boolean finish = false;
        while (!finish) {
            for (int i=0; i<10; i++){
                // if new state != old state
                if (threads[i].getState() != status[i]) {
                    // write
                    System.out.println("... status of " + threads[i].getName() + 
                                       " modified in " + threads[i].getState());
                    // update state
                    status[i] = threads[i].getState();
                }
            }
            finish = true;
            // when a thread stops ... (only one!)
            for (int i=0; i<10; i++){
                finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
            }
        }
    }
}



class Calculator implements Runnable {
    private int number;
    public Calculator(int number) {
        this.number=number;
    }
    
    @Override
    public void run() {
        for (int i=1; i<=10; i++){
            System.out.printf("%s: %d * %d = %d\n",
            Thread.currentThread().getName(), number, i, i*number);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
