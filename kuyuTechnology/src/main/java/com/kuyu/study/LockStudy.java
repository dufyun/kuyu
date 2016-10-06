package com.kuyu.study;

/**
 * 锁学习
 * @author aflyun
 *
 */
public class LockStudy {

	//修饰方法和代码块 对象锁
	public void testObjectLock1(){
		
		synchronized (this) {
			int i = 5;
			while(i-- > 0){
				System.out.println("--lock1 : "+ Thread.currentThread().getName() + " " + i);
			} 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//去掉 synchronized
	public void testObjectLock2(){
		int i = 5;
		while(i-- > 0){
			System.out.println("--lock2 : "+ Thread.currentThread().getName() + " " + i);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		final LockStudy ls = new LockStudy();
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				ls.testObjectLock1();
				
			}
		}, "lockObject1");
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
				ls.testObjectLock2();
			}
		}, "lockObject2");
		
		t1.start();
		t2.start();
		
		
	}
}
