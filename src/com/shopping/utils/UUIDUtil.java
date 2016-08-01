package com.shopping.utils;

import java.util.Scanner;
import java.util.UUID;


public class UUIDUtil {
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		String str = "";
		String str2 = "";
		
		/*for (int i = 1; i < 7; i++) {
			for (int g = 1; g < 5; g++) {
				for (int j = 1; j < 11; j++) {
					str = "insert into t_goodsinfo values("
							+ "'"+getUUID()+"',"
							+ "'【"+i+""+g+"衣舍】韩国时尚前卫设计师最新力作潮的你哭不买就计师最新力作潮的你哭不买就后悔限量版',"
							+ "'韩国时尚前卫设计师最新力作潮的你哭不买就计师最新力作潮的你哭不买就后悔限量版韩国时尚前卫设计师最新力作潮的你哭不买就计师最新力作潮的你哭不买就后悔限量版',"
							+ "'"+i+":"+g+"',"
							+ ""+i+""+i+""+g+"."+""+j+","
							+ ""+i+""+g+"."+""+j+","
							+ ""+i+""+j+","
							+""+i+""+i+""+g+""+j+","
							+""+i+""+i+""+g+""+j+","
							+ "0,"
							+ "'goods.jpg,goods.jpg,goods.jpg,goods.jpg,goods.jpg'"
							+ ");";
					System.out.println(str);
				}
			}
			
		}*/
		//System.out.println(getUUID());
		
		Scanner s = new Scanner(System.in);
		 
	}
}
