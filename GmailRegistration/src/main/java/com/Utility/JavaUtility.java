package com.Utility;

import java.util.Random;

public class JavaUtility 
{
	public long createRandomNumber()
	{
		Random random = new Random();
		long randomInt = random.nextInt(1000);
		
		return randomInt;
	}
}
