package com.wipro.rmgyantra.genericlib;

import java.util.Random;

public class JavaUtility {
	
	public static int getRandomNum() {
		Random r=new Random();
		int num = r.nextInt(200);
		return num;
		
	}

}
