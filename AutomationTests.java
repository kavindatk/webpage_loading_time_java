/*
 * 
 * Author : Kavinda Thennakoon
 *  
 */
package Automation.Mobitel.All.Operator;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AutomationTests {
    public static String[] stringArr,stringOut;
	public static String CHROME_DRIVERk=null;
	public static String OPERATORk=null;
	public static String OUTPUTk=null;
    public static void main(String[] args) throws InterruptedException, IOException {
    	ConfigurationReader configreader = new ConfigurationReader();
    	configreader.ConfigSave();
    	CHROME_DRIVERk = configreader.CHROME_DRIVER;
    	OPERATORk = configreader.OPERATOR;
    	OUTPUTk = configreader.OUTPUT;
    	
        DNSFlus();
        FileReaders();
        stringOut = new String[stringArr.length];
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVERk);
        ChromeOptions options = new ChromeOptions (); 

        DesiredCapabilities capabilities = new DesiredCapabilities ();
       
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);      
        
        for(int i=0;i<stringArr.length;i++){
            WebDriver driver = new ChromeDriver(capabilities);
            driver.get("http://"+stringArr[i]);
            //driver.navigate().refresh();
            final JavascriptExecutor js = (JavascriptExecutor) driver;
            long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd");
            long redirectStart = (Long) js.executeScript("return window.performance.timing.fetchStart");
            long time = loadEventEnd-redirectStart;
            stringOut[i] = stringArr[i] + " , " + time ;
            System.out.println(stringArr[i] + " : " + time + " milliseconds");  // <- i need this 
            driver.close();
            driver.quit();
            OutputWriter(stringOut[i]);
        }        
        
  }
        public static void OutputWriter(String finalcommand){

            try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OPERATORk,true)));
                    out.println(finalcommand);
                    out.close();
            } catch (IOException e) {                    
                    e.printStackTrace();
            }

    }
    public static void FileReaders() throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(OUTPUTk));
        String str;

        List<String> list = new ArrayList<String>();
        while((str = in.readLine()) != null){
        list.add(str);
        }

        stringArr = list.toArray(new String[0]);
    }
    public static void DNSFlus() throws IOException{
                ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "ipconfig /flushdns");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }
}