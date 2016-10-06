package com.kuyu.study;
/**
 * 死锁例子
 * @author aflyun
 *
 */
public class ThreadDeadlock {
	
	public static void main(String[] args) throws InterruptedException {
		//1:死锁例子
		/*
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
 
        Thread t1 = new Thread(new SyncThread(obj1, obj2), "t1");
        Thread t2 = new Thread(new SyncThread(obj2, obj3), "t2");
        Thread t3 = new Thread(new SyncThread(obj3, obj1), "t3");
 
        t1.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        t3.start();
 		*/
		//2:死锁例子
		/*
		SyncThread2 st1 = new SyncThread2();
		st1.flag  = 1;
		SyncThread2 st2 = new SyncThread2();
		st2.flag  = 0;
		
		Thread t1 = new Thread(st1);
		Thread t2 = new Thread(st2);
		t1.start();
		t2.start();
		*/
    }
 
}
//1:死锁例子
/* 
class SyncThread implements Runnable{
    private Object obj1;
    private Object obj2;
 
    public SyncThread(Object o1, Object o2){
        this.obj1=o1;
        this.obj2=o2;
    }
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " acquiring lock on "+obj1);
        
        synchronized (obj1) {
	         System.out.println(name + " acquired lock on "+obj1);
	         work();
	         System.out.println(name + " acquiring lock on "+obj2);
	         
		         synchronized (obj2) {
		            System.out.println(name + " acquired lock on "+obj2);
		            work();
		         }
		         System.out.println(name + " released lock on "+obj2);
		         
        }
        
        System.out.println(name + " released lock on "+obj1);
        System.out.println(name + " finished execution.");
    }
    
    private void work() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
*/
//2:死锁例子
/**
 * thread1线程占有了o1对象并等待o2对象，而thread2线程占有了o2对象并等待o1对象，
 * 而o1和o2又被这俩个线程所共享，所以就出现了死锁的问题了。
 */
/*
class SyncThread2 implements Runnable{
	public int flag;  
	//静态的对象，被DeadLockTest的所有实例对象所公用
	static Object o1 = new Object();   
	static Object o2 = new Object();
	public void run() {
		System.out.println("flag : " + flag);
		if(flag == 0){  
			synchronized (o1) {
				System.out.println(Thread.currentThread().getName());
				try{  
				     Thread.sleep(500);  
				} catch(Exception e){  
				     e.printStackTrace();  
				}  
				System.out.println("---test1-----");
			    synchronized(o2){  
			    	System.out.println("o2--------");
			    }  
			}
		}
		if(flag == 1){ 
			synchronized (o2) {
				System.out.println(Thread.currentThread().getName());
				try{  
				     Thread.sleep(500);  
				} catch(Exception e){  
				     e.printStackTrace();  
				}  
				System.out.println("---test2-----");
			    synchronized(o1){  
			    	System.out.println("o1--------");
			    }  
			}
		}
	}
	
}
*/
