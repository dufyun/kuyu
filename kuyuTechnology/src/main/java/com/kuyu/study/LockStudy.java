package com.kuyu.study;


/**
 * 锁学习
 * 1:对象锁和类锁举例
 * 2:synchronized同时修饰静态和非静态方法
 *
 * @author aflyun
 */
public class LockStudy {

	/*------------------------1:对象锁和类锁举例-----------------------------------------*/
	//修饰方法和代码块 对象锁
	public void testObjectLock1(){
		
		synchronized (this) {
			int i = 5;
			while(i-- > 0){
				System.out.println("--lock1 : "+ Thread.currentThread().getName() + " " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
		}
	}
	//去掉 synchronized
	public synchronized void testObjectLock2(){
		int i = 5;
		while(i-- > 0){
			System.out.println("--lock2 : "+ Thread.currentThread().getName() + " " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	/**
	 * 讲解如下：
	 * （1）：都加synchronized
	 * 第一个方法时用了同步代码块的方式进行同步，传入的对象实例是this，表明是当前对象，
	 * 当然，如果需要同步其他对象实例，也不可传入其他对象的实例；第二个方法是修饰方法的方式进行同步。
	 * 因为第一个同步代码块传入的this，所以两个同步代码所需要获得的对象锁都是同一个对象锁，
	 * 下面main方法时分别开启两个线程，分别调用test1和test2方法，那么两个线程都需要获得该对象锁，另一个线程必须等待。
	 * 
	 * （2）：有一个不加synchronized
	 * 结果输出是交替着进行输出的，这是因为，某个线程得到了对象锁，但是另一个线程还是可以访问没有进行同步的方法或者代码。
	 * 进行了同步的方法（加锁方法）和没有进行同步的方法（普通方法）是互不影响的，
	 * 一个线程进入了同步方法，得到了对象锁，其他线程还是可以访问那些没有同步的方法（普通方法）。
	 * 
	 * 注：类锁的修饰方法和代码块：
	 * 写法如 testObjectLock1()
	 * synchronized (LockStudy.class) {}
	 * 其他不变，输出结果如 （1）的结果。
	 * 类锁修饰方法和代码块的效果和对象锁是一样的，因为类锁只是一个抽象出来的概念，
	 * 只是为了区别静态方法的特点，因为静态方法是所有对象实例共用的，
	 * 所以对应着synchronized修饰的静态方法的锁也是唯一的，所以抽象出来个类锁。
	 */
	
	/*-----------------------------------------------------------------------------------------------*/
	/*------------------------2:synchronized同时修饰静态和非静态方法-----------------------------------------*/
	//非静态方法（实例方法）  对象所有的
	public synchronized void testNoStaticSyn(){
	
		int i = 5;
		while(i-- > 0){
			System.out.println("--lock3 : "+ Thread.currentThread().getName() + " " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
			
	}
	//静态方法 类所有的
	public synchronized static void  testStaticSyn(){
		int i = 5;
		while(i-- > 0){
			System.out.println("--lock4 : "+ Thread.currentThread().getName() + " " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/*
	public static void main(String[] args) {
		final LockStudy ls = new LockStudy();
		Thread t3 = new Thread(new Runnable() {
			
			public void run() {
				ls.testNoStaticSyn();
				
			}
		}, "testNoStaticSyn");
		Thread t4 = new Thread(new Runnable() {
			
			public void run() {
				testStaticSyn();
			}
		}, "testStaticSyn");
		
		t3.start();
		t4.start();
	}
	*/
	/**
	 * 解释：
	 * 上面代码synchronized同时修饰实例方法和静态方法，但是运行结果是交替进行的。
	 * 这证明了对象锁和类锁是两个不一样的锁，控制着不同的区域，它们是互不干扰的。
	 * 同样，线程获得对象锁的同时，也可以获得该类锁，即同时获得两个锁，这是允许的。
	 */
	
	 //当某个线程进入一个需要执行时间很长的同步方法之后，这个对象的其他同步方法就都不能给其他线程访问了，造成线程阻塞，影响系统性能。
	//这就是同步代码块在某种情况下要优于同步方法的方面.
	
	/*-----------------------------------------------------------------------------------------------*/
	
}
