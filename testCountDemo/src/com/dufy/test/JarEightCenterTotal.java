package com.dufy.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarEightCenterTotal {

	//存放所有的jar的包路径和名称
	public static Set<String> jarList = new HashSet<String>();
	public static List<String> filePathList = new ArrayList<String>();

	//统计代码行数
	public static Integer countCode = 0;
/*
		D:/EightCenter/financeCenter_pom.xml
		D:/EightCenter/marketingCenter_pom.xml
		D:/EightCenter/orderCenter_pom.xml
		D:/EightCenter/priceCenter_pom.xml
		D:/EightCenter/productCenter_pom.xml
		D:/EightCenter/serviceCenter_pom.xml
		D:/EightCenter/stockCenter_pom.xml
		D:/EightCenter/userCenter_pom.xml
*/	 
	public static String path = "D:/EightCenter/stockCenter_pom.xml";
	 
	
	public static void main(String[] args) throws IOException {
		List<String> findFolderFile = findFolderFile(path);
		for (String string : findFolderFile) {
			System.out.println(string);
		}
		for (String string : findFolderFile) {
			File file = new File(string);
			findAllJarFolder(file);
			File filetxtPath = new File(string.substring(0, string.lastIndexOf(".")) + "_Total.txt");//输出要统计的文件信息
			PrintWriter pw = new PrintWriter(new FileWriter(filetxtPath));
			for (String jarName : jarList) {
				pw.println(jarName);//将jar包文件和对应的代码行数写入 txt文件
				Set<String> findAllJarClassfiles = findAllJarClassfiles(jarName);
				for (String jarClassFileName : findAllJarClassfiles) {
						int countJarLine = countJarLine(jarName,jarClassFileName);
						pw.println( jarClassFileName + "\t" + countJarLine);
				}
			}
			pw.close();
			
		}
		System.out.println("Total line : " + countCode);
	}
	/**
	 * 根据pom文件获得com.aebiz 下的jar包路径
	 * @throws IOException
	 */
	public static void findAllJarFolder(File dirFile){
		try{
			List<String> jarListFolder = new ArrayList<String>();
			
				BufferedReader bf = new BufferedReader(new FileReader(dirFile)) ;
				System.out.println("===========" + dirFile);
				String line = "";
				while((line = bf.readLine()) != null ){
					if(line.contains("<groupId>com.aebiz</groupId>")){
						String line1 = bf.readLine().trim();
						int startIndex = line1.indexOf(">");
						int endIndex = line1.lastIndexOf("<");
						line1 = line1.substring(startIndex + 1, endIndex);
						jarListFolder.add(line1);
						//System.out.println(line1);
					}
						
				}
				bf.close();
			for (String jarFolderName : jarListFolder) {
				System.err.println("---" + jarFolderName);
				File dir = new File("D:/KuYuPrograms/repos/com/aebiz/" + jarFolderName);
				findAllJarFiles(dir);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("访问出错了！");
		}
		
		
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
				 // System.out.println(files[i].getAbsolutePath());
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
	
	public static List<String> findFolderFile(String filepath) {
		File dirFile = new File(filepath);
		try {
			if(dirFile.exists()){
				if(dirFile.isDirectory()){
					File[] files = dirFile.listFiles();
					for (File file : files) {
						findFolderFile(file.getAbsolutePath());//递归
					}
				}else {
					String list = dirFile.getAbsolutePath().replace("\\", "/");
					filePathList.add(list);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return filePathList;
	}
}
