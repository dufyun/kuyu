package com.dufy.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 统计指定项目的目录下的*.java代码行数
 * @author aflyun
 * @date 2016.02.16
 * @version 1.0
 */
public class JavaTotal {

	//项目java文件所在目录
	public static String javaPath = "D:/KuYuPrograms/tclshop/src/";
	//统计java文件和对应的代码行数
	public static Map<String, Integer> javaMap = new HashMap<String, Integer>();
	//统计代码行数
	public static Integer countCode = 0;
	
	public static int runJavaTotal(){
		try {
			File filetxtPath = new File("D:/javaFileCount.txt");//输出要统计的文件信息
			PrintWriter pw = new PrintWriter(new FileWriter(filetxtPath));
			
			List<File> list = total(javaPath);
			countJavaLine(list);
			//将java文件和对应的代码行数写入 txt文件
			for(Map.Entry<String, Integer> entry : javaMap.entrySet()){
				pw.println(entry.getKey()+"\t" + entry.getValue()); //将java文件写入txt中
				//System.out.println(entry.getKey()+"\t"+entry.getValue());    
			}
			pw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return countCode;
	}
	/**
	 * 获取所有的文件
	 * @param path 获取文件的路径
	 * @return
	 */
	public static List<File> total(String path){
		List<File> fileList = null;
		try {
			fileList = new ArrayList<File>();
			File filePath = new File(path);
			File[] files = filePath.listFiles();//listFiles能够获取当前文件夹下的所有文件和文件夹
			for (File file : files) {
				if(file.isFile() && file.getName().endsWith(".java")){
					fileList.add(file);
				}else {
					fileList.addAll(fileList.size(), total(file.getPath()));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return fileList;
	}
	
	/**
	 * 统计项目中java代码的行数
	 * @param listFile 文件的集合
	 */
	public static void countJavaLine(List<File> listFile){
		try {
			for (File file : listFile) {
				int count = 0;
				if(file.getName().endsWith(".java")){
					
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String line = "";
					while((line = br.readLine()) != null){
						count ++;
					}
				}
				javaMap.put(file.getAbsolutePath().replace("\\", "/"), count);
				countCode += count;
			}
		} catch (Exception e) {
			System.err.println("统计java代码行数出错!");
		}
		
	}
	
//==========================================================================================//
	public static void main(String[] args) throws IOException {

		long start = System.nanoTime();
		int runJavaTotal = runJavaTotal();
		System.err.println("Total java code : " + runJavaTotal);
		long end = System.nanoTime();
		System.out.print("cost: " + (end - start)/1e9 + " seconds");
	}
}
