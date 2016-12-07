package com.dufy.test;

import com.dufy.count.url.JarURlTotal;

/**
 * 统计项目中所有代码的行数<br/>
 * 	1: .java文件中代码<br/>
 * 	2: jar包中的文件代码
 * @author aflyun
 *
 */
public class CountTotalMain {

	public static void main(String[] args) {
		long start = System.nanoTime();
		
		int runJavaTotal = JavaTotal.runJavaTotal();
		int runJarTotal = JarTotal.runJarTotal();
		System.out.println("java总代码： " + runJavaTotal +"----jar总代码： " + runJarTotal);
		System.out.println("项目中总代码之和为 ： " + (runJarTotal + runJavaTotal));
		
		long end = System.nanoTime();
		System.out.println("cost: " + (end - start)/1e9 + " seconds");
	}
}
