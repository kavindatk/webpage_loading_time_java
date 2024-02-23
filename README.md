# Webpage Loading Time Analyzer - Selenium based Java application  

This application was developed in 2016 to automatically measure webpage loading times. I have uploaded a compiled JAR file for those who want to measure loading times without compiling the code. For developers, I have also included the source code. This application utilizes Selenium WebDriver as an automation tool. To use it, you need to have Google Chrome or any Chrome-based web browser installed, along with the Chrome WebDriver. Personally, I recommend using Google Chrome as the web browser for optimal performance.


<picture>
  <img alt="Webpage Time" src="https://kadiska.com/wp-content/uploads/2020/11/timenav.png" width="1000" height="400">
</picture>

To extract loading time, I am utilizing the following logic. It returns time in long format, which is then converted to a human-readable time format once it reaches a significant duration.

  ```java
            long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd");
            long redirectStart = (Long) js.executeScript("return window.performance.timing.fetchStart");
            long time = loadEventEnd-redirectStart;
  ```

## Java Files : 

#### ConfigurationReader.java

This will read the required configurations for the webpage loading time test application. It will read the following configurations:

    1. Chrome driver path
    2. Operator Name for saving 
    3. Output file path

#### AutomationTest.java

This will be the main program. It will initially flush any saved DND configurations before getting time. Then, it will read URLs one by one, load them into the web browser, extract the time, and save it to the output file.

# Application 

As mentioned above, I have created a standalone application for obtaining time. Those who dislike coding can use this program to obtain time.

System requirement :

  1. Windows 7,8,10 or above
  2. JRE installed
  3. Google Chrome installed

Steps : 
  1. Download Google chrome web drive from this like : https://chromedriver.chromium.org/downloads
  2. Copy downloaded driver in to chrome installed dirctory 
  3. Create folder called "webDriver" in "C drive"
  4. Then copy urls.txt and webauto.jar into it
  5. Open urls.txt in any editor and place chrome driver path and required links and save it
  6. open cmd/terminal and execute
      ```java
        java -jar webauto.jar
      ```


