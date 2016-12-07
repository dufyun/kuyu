package com.dufy.count.url;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 * 统计指定项目的目录下的*Controller.java代码行数
 * @author aflyun
 * @date 20160.3.01
 * @version 1.0
 */
public class FrontlUrlTota {
	
	//项目java文件所在目录
	//public static String javaPath = "D:/KuYuPrograms/tclshop/src/main/java/com/aebiz/b2b2c";
	public static String javaPath = "D:/KuYuPrograms/tclshop/src/main/java/com/aebiz/b2b2c/dealer";
//	public static String javaPath = "D:/KuYuPrograms/TCLcenter/userCenter/branches/15721_20151119/usercenter/src/main/java/com/aebiz/b2b2c";
	public static File filetxtPath = new File("E:"+File.separator+"Url.txt");//输出要统计的文件信息
	public static PrintWriter pw = null;
	public static int countTotal = 0;
	/**
	 * 获取所有的文件Controller.java
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
				if(file.isFile() && file.getName().endsWith("Controller.java")){
					//System.out.println("============" + file.getAbsolutePath());
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
	 * 统计项目中java中Controller中url数
	 * @param listFile 文件的集合
	 */
	public static void countJavaLine(File file){
		try {
			String controllerUrl = "";//临时变量
			String url = "";//请求地址
			String reqMethod = "";//请求方式
			String methodNameAndreturn = ""; //方法名称和返回值
			String method = ""; //方法名称
			String returnvalue = "";//返回值
				pw.print(file.getAbsolutePath().replace("\\", "/").substring(42));
				//pw.print(file.getName());//写文件名称
				//pw.println("url" +"\t"+ "request" +"\t"+ "method"+"\t"+"return");
				int count = 0;
				if(file.getName().endsWith("Controller.java")){
					
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String line = "";
					while((line = br.readLine()) != null){
						//获取ControllerUrl
						if(line.contains("@Controller")){
							line = br.readLine(); //@RequestMapping("/single")
							if(line.contains("@RequestMapping")){//有@RequestMapping
								int start = line.indexOf("\"");
								int end = getCharacterPosition(line);
								controllerUrl = line.substring(start + 1, end);
								//System.out.println("controllerUrl = " + controllerUrl);
								line = br.readLine();//读取 "/single" 的下一行
							}
							
						}
						
						//获取访问的url和method
						if(line.contains("@RequestMapping")){
							url = line;
							if(url.contains("method")){//有请求方法进入
								int start = url.indexOf("RequestMethod.");//截取method开始
								reqMethod = url.substring(start + 14, start + 18);
								//System.out.println("reqMethod = " + reqMethod);
							}
							int startUrl = url.indexOf("\"");//url开始 第一个分号  " 
							int endUrl = getCharacterPosition(url);//url结束 为第二个 分号   "
							url = url.substring(startUrl + 1,endUrl); //截取url
							if(!url.contains("/")){
								url = "/"+url;
							}
							url = controllerUrl + url; //拼接url 为/xxx/yyy
							//System.out.println("url = " + url);
							pw.print(""+"\t"+ url + "\t");//输出url
							
							if(reqMethod != ""){//请求方法不为空
								if(reqMethod.contains("}")){
									reqMethod = reqMethod.replace("}","");
								}
								if(reqMethod.contains(")")){
									reqMethod = reqMethod.replace(")", "");
								}
								pw.print(reqMethod+"\t");
							}else {
								//method 默认为GET请求
								pw.print(""+"\t");
							}
							
							//获取方法名称和返回值
							line = br.readLine();
							methodNameAndreturn = line;
							if( (methodNameAndreturn.contains("//") && methodNameAndreturn.contains("add") )|| methodNameAndreturn.contains("@ResponseBody")){
								line = br.readLine();//有 @ResponseBody 注解在读取下一行
								methodNameAndreturn = line;
							}
							if(methodNameAndreturn != null && methodNameAndreturn.contains("(")){
								int mArEnd = methodNameAndreturn.indexOf("(");//第一个 ( 前面的内容
								methodNameAndreturn = methodNameAndreturn.substring(0,mArEnd);
								method = methodNameAndreturn.substring(methodNameAndreturn.lastIndexOf(" "));
								returnvalue = methodNameAndreturn.substring(methodNameAndreturn.indexOf(" "), methodNameAndreturn.lastIndexOf(" "));
								if(returnvalue.contains("public")){
									returnvalue = returnvalue.replace("public", "");
								}else if(returnvalue.contains("static")){
									returnvalue = returnvalue.replace("static", "");
								}else if(returnvalue.contains("private")){
									returnvalue = returnvalue.replace("private", "");
								}else if(returnvalue.contains("protected")){
									returnvalue = returnvalue.replace("protected", "");
								}else if(returnvalue.contains("/")){
									returnvalue = returnvalue.replace("*/", "");
								}
								//System.out.println("method == " + method + "returnvalue == " + returnvalue);
								pw.print(method+"\t"+returnvalue +"\n");
							}
							
							count ++;
						}
						
					}//while
					//pw.print(""+"\t"+""+"\t"+ "" +"\t"+ ""+"\t"+""+"\t" + count);
					if(url  == ""){
						pw.println();
					}
				}
				countTotal += count;
		} catch (Exception e) {
			System.err.println("统计java代码行数出错!" + file );
		}
		
	}
	/**
	 * 判断一个字符出现的位置
	 * @param string
	 * @return
	 */
	public static int  getCharacterPosition(String string){
	    //这里是获取"#"符号的位置
	    Matcher slashMatcher = Pattern.compile("\"").matcher(string);
	    int mIdx = 0;
	    while(slashMatcher.find()) {
	       mIdx++;
	       //当"#"符号第二次出现的位置
	       if(mIdx == 2){
	          break;
	       }
	    }
	    return slashMatcher.start();
	}
	
//==========================================================================================//
	public static void main(String[] args) throws IOException {
		long start = System.nanoTime();
		
		pw = new PrintWriter(new FileWriter(filetxtPath));
		List<File> files = total(javaPath);
		for (File file : files) {
			countJavaLine(file);
		}
		System.out.println("total = " + countTotal);
		pw.close();//关闭输出流
		long end = System.nanoTime();
		System.out.print("cost: " + (end - start)/1e9 + " seconds");
	}
}
