package org.zxg.utils;

import org.springframework.util.DigestUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MD5 {

//	public static void main(String[] args) {
//		//对密码进行 md5 加密
//		String md5Password = DigestUtils.md5DigestAsHex("user.getPassword()".getBytes());
//		System.out.println(md5Password);
//	}
//	
	public static String getMd5(String str){
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}
	public static String getMd5(byte[] bytes){
		return DigestUtils.md5DigestAsHex(bytes);
	}
	public static String getMd5(File file) throws IOException {
		return getMd5(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
	}
}
