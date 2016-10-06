package com.kuyu.study;

/**
 * 锁学习
 * @author aflyun
 *
 */
public class LockStudy {

	//修饰方法和代码块 对象锁
	public void testObjectLock1(String name){
		
		synchronized (this) {
			int i = 5;
			while(i-- > 0){
				System.out.println(name + "--lock1 : "+ this.getClass().getName() + " " + i);
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
	public void testObjectLock2(String name){
		int i = 5;
		while(i-- > 0){
			System.out.println(name + "--lock2 : "+ this.getClass().getName() + " " + i);
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
		final LockStudy ls1 = new LockStudy();
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				ls.testObjectLock1("ls-lock1");
				
			}
		}, "lockObject1");
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
				ls.testObjectLock2("ls-lock2");
			}
		}, "lockObject1");
		
		t1.start();
		t2.start();
		
		Thread t3 = new Thread(new Runnable() {
			
			public void run() {
				ls1.testObjectLock1("ls1-lock1");
			}
		}, "lockObject1");
		//t3.start();
		
	}
}
