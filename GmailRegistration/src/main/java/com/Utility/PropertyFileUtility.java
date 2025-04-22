package com.Utility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtility
{
	public String getData(String key)
	{
		try
		{
			FileInputStream fis = new FileInputStream("./src/test/resources/com/Commondata/GMailProperty.properties");
			Properties property = new Properties();
			property.load(fis);
			
			return property.getProperty(key);
		
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		return null ;
	}
}
