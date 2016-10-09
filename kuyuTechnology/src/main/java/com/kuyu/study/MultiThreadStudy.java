package com.kuyu.study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程学习
 * @author aflyun
 *
 */
public class MultiThreadStudy {

		public static void main(String[] args) throws InterruptedException, ExecutionException {
			/*1*/
			TestExThread t1 = new TestExThread("A");
			TestExThread t2 = new TestExThread("B");
			//t1.start();
			//t2.start();
			t1.setPriority(Thread.MAX_PRIORITY);
			/*2*/
			TestImRunnable tir1 = new TestImRunnable("C");
			TestImRunnable tir2 = new TestImRunnable("D");
			Thread t3 = new Thread(tir1);
			Thread t4 = new Thread(tir2);
			//t3.start();
			//t4.start();
			
			/*3*/
			TestImCallable tic = new TestImCallable();
			FutureTask<Integer> ft1 = new FutureTask<Integer>(tic); 
			FutureTask<Integer> ft2 = new FutureTask<Integer>(tic);
			
			Thread t5 = new Thread(ft1);
			Thread t6 = new Thread(ft2);
			//t5.start();
			//t6.start();
			
			//Integer integer1 = ft1.get();
			//System.out.println(integer1);
			
			for (int i = 0; i < 1; i++) {
				/*4.测试线程优先级*/
				TestThreadPriority thp1 = new TestThreadPriority("A");
				TestThreadPriority thp5= new TestThreadPriority("B");
				TestThreadPriority thp10 = new TestThreadPriority("C");
				 Thread t7 = new Thread(thp1);
				// t7.setPriority(Thread.MIN_PRIORITY);
				 Thread t8 = new Thread(thp5);
				// t8.setPriority(Thread.NORM_PRIORITY);
				 Thread t9 = new Thread(thp10);
				// t9.setPriority(Thread.MAX_PRIORITY);
				 t7.start();
				 t8.start();
				 t9.start();
			}
			
			 
			 
		}
}

//1.继承Thread类，重写该类的run()方法。
class TestExThread extends Thread{
	
	private String name;

	public TestExThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(i + "--"+ name  +" : "+ Thread.currentThread().getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
//2.实现Runnable接口，并重写该接口的run()方法，该run()方法同样是线程执行体，创建Runnable实现类的实例，
//并以此实例作为Thread类的target来创建Thread对象，该Thread对象才是真正的线程对象。
class TestImRunnable implements Runnable{
	private String name;

	public TestImRunnable(String name) {
		this.name = name;
	}
	
	
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(i + "--"+ name  +" : "+ Thread.currentThread().getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
}

//3.使用Callable和Future接口创建线程。具体是创建Callable接口的实现类，并实现clall()方法。
//并使用FutureTask类来包装Callable实现类的对象，且以此FutureTask对象作为Thread对象的target来创建线程。
class TestImCallable implements Callable<Integer>{
	
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i < 50; i++) {
			System.out.println(i + " --- " + Thread.currentThread().getName() );
			sum += i;
		}
	
		return sum;
	}
	
}

//4.线程的优先级
class TestThreadPriority implements Runnable{
	
	public String name;
	
	public TestThreadPriority(String name) {
		this.name = name;
	}

	public void sayHello(String name){
		
		System.out.println("Hello everyOne ,　My name is " + name);
	}

	public void run() {
		sayHello(name);
	}
}
