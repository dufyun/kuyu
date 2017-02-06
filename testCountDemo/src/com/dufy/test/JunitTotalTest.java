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
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.Test;

public class JunitTotalTest {

	/**
	 * 获取指定jar文件中的所有文件
	 * @throws IOException
	 */
	@Test 
	public void testGetFileFromURIPath() throws IOException { 
		String filename = "D:/KuYuPrograms/repository/com/aebiz/cms/0.0.1/cms-0.0.1.jar";
	    JarFile jarFile = new JarFile(filename);  
	    Enumeration<JarEntry> entrys = jarFile.entries();  
	    while (entrys.hasMoreElements()) {  
	        JarEntry jarEntry = entrys.nextElement();  
	        String nameString = jarEntry.getName();
	        if(nameString.endsWith(".class")){
	        	System.out.println(nameString);  
	       }
	    }
	} 
	/** 
	 * 构造URI/URL格式的文件路径，读取本地文件 
	 *  
	 */ 
	@Test
	public void TestDouble() throws IOException { 
		URL url = new URL("jar:file:/D:/KuYuPrograms/repository/com/aebiz/cms/0.0.1/cms-0.0.1.jar!/com/aebiz/b2b2c/cms/channel/dao/ChannelDAO.class"); 
		System.out.println(url); 
		InputStream is=url.openStream(); 
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		String s="";
		int count = 0;
		while((s=br.readLine())!=null){
			count ++;
			//System.out.println(s);
		}
		System.out.println(count);
	} 
	/**
	 * 将字符串写入txt文件
	 * @throws IOException
	 */
	@Test
	public void testFileWrite() throws IOException{
		 File filetxtPath = new File("D:/jarFileCount.txt");
		 PrintWriter pw = new PrintWriter(new FileWriter(filetxtPath));
         pw.println("abc");
         pw.println("abc");
         pw.close();
	}
	@Test
	public void showTxtContent() throws IOException {
		List<String> jarList = new ArrayList<String>();
 		File file =  new File("E:"+ File.separator +"usercenter.txt");
		BufferedReader bf = new BufferedReader(new FileReader(file)) ;
		String line = "";
		while((line = bf.readLine()) != null ){
			if(line.contains("<groupId>com.aebiz</groupId>")){
				String line1 = bf.readLine().trim();
				int startIndex = line1.indexOf(">");
				int endIndex = line1.lastIndexOf("<");
				line1 = line1.substring(startIndex + 1, endIndex);
				jarList.add(line1);
			}
				
		}
		bf.close();
		for (String jarName : jarList) {
			File dir = new File("D:/KuYuPrograms/repos/com/aebiz/" + jarName);
			findAllJarFiles(dir);
		}
	}
	
	public static void findAllJarFiles(File dir) {
		 try {
			 //获取当前文件夹下的所有文件和文件夹
			 File[] files = dir.listFiles();
			 for(int i = 0; i < files.length; i++){
				 // System.out.println(fs[i].getAbsolutePath());
				  String jspPath = files[i].getAbsolutePath().replace("\\", "/");
				  if(files[i].isFile() && jspPath.endsWith(".jar")){
					  System.out.println(jspPath);
					 // jarList.add(jspPath);
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
	
}
