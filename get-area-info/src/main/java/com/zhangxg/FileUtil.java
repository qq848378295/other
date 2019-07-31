package com.zhangxg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileUtil {
	public static Charset defaultCharset = StandardCharsets.UTF_8;

	public static void main(String[] args) throws Exception {
//		String p = "d:/NewTest.java";
//		String txt = readFile2String(p);
//		System.out.println(txt);
//		txt += " // 测试追加";
//		writeString(p, txt);
//		txt = readFile2String(p);
//		System.out.println(txt);
		 String PATH="D:/2018";
		 for (File file : new File(PATH).listFiles()) {
			if(file.isDirectory()) {
				 for (File file2 : file.listFiles()) {
					 if(file2.isDirectory()) {
						 try {
							 for (File file3 : file2.listFiles()) {
								 System.out.println(file3.getName());
								 if("430223.html".contentEquals(file3.getName())) {
//									 System.exit(1);
								 }
								 if(file3.isDirectory()) {
									es.execute(new Runnable() {
										
										@Override
										public void run() {
											 del(file3.getAbsolutePath());
											 file3.delete();
										}
									});
									
								 }
							 }
						} catch (Exception e) {
							System.out.println(file2);
							e.printStackTrace();
						}
					}
				 }
			}
		}
		 es.shutdown();
	}
	static ExecutorService es=Executors.newFixedThreadPool(31);
	
	private static void del(String  path) {
		File file3=new File(path);
			for (File f : file3.listFiles()) {
				if(f.isDirectory()) {
					del(f.getAbsolutePath());
				}else {
					f.deleteOnExit();
				}
			}
	}

	public static String readFile2String(String path) throws Exception {
		return readFile2String(path, defaultCharset.name());
	}

	public static String readFile2String(String path, String charSet) throws Exception {
		byte[] bytes = readFile(path);
		return new String(bytes, charSet);
	}

	public static byte[] readFile(String path) throws Exception {
		return Files.readAllBytes(Paths.get(path));
	}

	public static void writeString(String path, String txt) {
		writeString(Paths.get(path), txt, defaultCharset);
	}

	public static void writeString(String path, String txt, Charset charset) {
		writeString(Paths.get(path), txt, charset);
	}

	private static void writeString(Path path, String txt, Charset charset) {
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset);) {
			writer.write(txt);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
