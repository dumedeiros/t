/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author
 */
public class ExecThread extends Thread {

    boolean executing;
    long sleepTime;

    public ExecThread() {
        super();
    }

    public ExecThread(boolean b) {
        executing = b;
    }
    
    public ExecThread(boolean b, long t) {
        executing = b;
        sleepTime = t;
    }
    

    public boolean isExecuting() {
        return executing;
    }

    public void doSuspend() {
        executing = false;
    }

    public synchronized void doResume() {
        executing = true;
        notifyAll();
    }

    public void setExecuting(boolean executing) {
        this.executing = executing;
    }

    public void syncronizeAndDoTheRest() {
        try {
            sleep(this.sleepTime);
        } catch (InterruptedException ex) {
        }
        synchronized (this) {
            while (!isExecuting()) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
