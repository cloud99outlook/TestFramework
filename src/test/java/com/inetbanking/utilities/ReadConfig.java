package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro = new Properties();

	public ReadConfig() {

		File src = new File("./Configuration/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("ErrorMessage" + e.getMessage());
		}
	}

	public String getApplicationURL() {

		String Url = pro.getProperty("baseURL");
		return Url;
	}


	public String getUsername() {

		String username = pro.getProperty("username");
		return username;
	}
	public String getPassword() {

		String password = pro.getProperty("password");
		return password;
	}

	public String getChromeDriverPath() {

		String chromePath = pro.getProperty("chromepath");
		return chromePath;
	}

	public String getIEDriverPath() {

		String iePath = pro.getProperty("iepath");
		return iePath;
	}
	public String getFirefoxDriverPath() {

		String firefoxPath = pro.getProperty("firefoxpath");
		return firefoxPath;
	}
	

	

}
