package com.shopping.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;

public class MD5Util {

	public static String getMD5(String str){
	        byte [] buf = str.getBytes();
	        MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        md5.update(buf);
	        byte [] tmp = md5.digest();
	        StringBuilder sb = new StringBuilder();
	        for (byte b:tmp) {
	            sb.append(Integer.toHexString(b&0xff));
	        }
	        return sb.toString() ;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		 /*for (int i = 0; i < 10; i++) {
			 System.out.println(getMD5("123456"));
		}*/
		 
		 //×¢²á  ÃÜÂëµÄ¼ÓÃÜ
		 //µÇÂ¼ ÃÜÂëµÄ¼ÓÃÜ
		/*String email_test = "(^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$)";
		String email = "real";
		System.out.println(Pattern.matches(email_test, email));*/
		/*String str = JSON.toJSONString(null);
		System.out.println(str == null);
		System.out.println(str.equals("null"));*/
		String test_num = "(^[0-9]*$)";
		Scanner s = new Scanner(System.in);
		
		while(true) {
			String str = s.next();
			System.out.println(Pattern.matches(test_num, str));
		}
	}
}
