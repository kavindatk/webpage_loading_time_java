package Automation.Mobitel.All.Operator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationReader {
	public static String []Lines = new String[3];
	public static String CHROME_DRIVER=null;
	public static String OPERATOR=null;
	public static String OUTPUT=null;

	
	public void ConfigSave(){
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Automation\\config.ini")))
		{

			String sCurrentLine;
			int i = 0;
			int j=0;
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				Lines[i] = sCurrentLine;
				i++;
			}
			CHROME_DRIVER = Lines[0].split("=")[1].trim();
			OPERATOR = Lines[1].split("=")[1].trim();
			OUTPUT = Lines[2].split("=")[1].trim();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}
