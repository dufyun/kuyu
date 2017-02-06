package com.dufy.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
/**
 * 统计指定路径下面jar包文件中所有*.class 文件的代码行数
 * @author aflyun
 * @date 2016.02.16
 * @version 1.0
 */
public class JarTotal {
	//jar包存放的仓库位置 
	//public static String jarPath = "D:/KuYuPrograms/repository/com/aebiz"; 
	public static String jarPath = "D:/KuYuPrograms/repos/com/aebiz"; 
	//存放所有的jar的包路径和名称
	public static Set<String> jarList = new HashSet<String>();
	//统计代码行数
	public static Integer countCode = 0;
	
	public static int runJarTotal() {
		try {
			File filetxtPath = new File("D:/jarFileCountCenter111.txt");//输出要统计的文件信息
			PrintWriter pw = new PrintWriter(new FileWriter(filetxtPath));
			File file = new File(jarPath);
				findAllJarFiles(file);
			for (String jarName : jarList) {
				pw.println(jarName);//将jar包文件和对应的代码行数写入 txt文件
				Set<String> findAllJarClassfiles = findAllJarClassfiles(jarName);
				for (String jarClassFileName : findAllJarClassfiles) {
						int countJarLine = countJarLine(jarName,jarClassFileName);
						pw.println( jarClassFileName + "\t" + countJarLine);
				}
			}
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return countCode;
	}
	
	/**
	 * 遍历获取所有的jar包文件路径和名称
	 * @param dir 目标路径
	 */
	 public static void findAllJarFiles(File dir) {
		 try {
			 //获取当前文件夹下的所有文件和文件夹
			 File[] files = dir.listFiles();
			 for(int i = 0; i < files.length; i++){
				 // System.out.println(fs[i].getAbsolutePath());
				  String jspPath = files[i].getAbsolutePath().replace("\\", "/");
				  if(files[i].isFile() && jspPath.endsWith(".jar")){
					  //System.out.println(jspPath);
					  jarList.add(jspPath);
				  }
				  //如果是文件夹，递归
				  if(files[i].isDirectory()){
					  findAllJarFiles(files[i]);
				  }
				
			 }
		} catch (Exception e) {
			System.err.println("获取所有的jar包路径和名称出错！");
		}
		
	 }
	 
	/**
	 * 获取jar包目录下所有的class文件
	 * @param jarName jar包的路径和名称
	 * @return  返回对应jar包下所有.class 文件的集合
	 */
	 public static Set<String> findAllJarClassfiles(String jarName){
		//存放jar包下对应的文件路径和名称
		Set<String> jarFileList = new HashSet<String>();
		 try {
			JarFile jarFile = new JarFile(jarName);
			Enumeration<JarEntry> entries = jarFile.entries();
			while(entries.hasMoreElements()){
				JarEntry jarEntry = entries.nextElement();
				String fileName = jarEntry.getName();
				if(fileName.endsWith(".class")){
					//System.out.println("***" + fileName);
					jarFileList.add(fileName);
				}
			}
		} catch (IOException e) {
			System.err.println("获取jar包下的所有class出错！");
		}
		 return jarFileList;
	 }
	 
	/**
	 * 构造URI/URL格式的文件路径<br/>
	 * 统计所有jar包中所有class文件的代码行数
	 * @param jarName	jar包的路径和名称
	 * @param jarClassFileName	jar包下所有文件.class 文件的路径和名称
	 * @throws	IOException
	 */
	 public static int countJarLine(String jarName,String jarClassFileName) {
		 int count = 0;
		 try {
			URL url = new URL("jar:file:/"+jarName+"!/"+jarClassFileName+""); 
			//System.out.println(url); 
			InputStream is=url.openStream(); 
			BufferedReader br=new BufferedReader(new InputStreamReader(is)); 
			String line = "";
			
			while((line = br.readLine())!=null){
				count ++;
			}
			countCode += count;
		} catch (Exception e) {
			System.err.println("统计jar包总代码数出错!");
		}
		return count;
	}
	 
//==========================================================================================//		
	public static void main(String[] args) throws Exception {
		long start = System.nanoTime();
		int runJarTotal = runJarTotal();
		System.err.println("总文件 : " + jarList.size());
		for (String string : jarList) {
			System.out.println(string);
		}
		System.err.println("Total jar code : " + runJarTotal);
		long end = System.nanoTime();
		System.out.print("cost: " + (end - start)/1e9 + " seconds");
	}
}
