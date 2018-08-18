package com.deloitte.integ;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	public Properties load(String propertiesFilePath) {
		Properties pros = new Properties();
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(propertiesFilePath);
			pros.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { fis.close(); }catch(Exception e) {}
		}
		return pros;
	}
	public void save(String propertiesFilePath, Properties properties) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(propertiesFilePath);
			properties.store(fos, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{ fos.close(); } catch(Exception e) {}
		}
		
	}
	
}
