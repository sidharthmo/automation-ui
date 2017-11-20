package com.dxc.vpc.automation.util;


import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebConnector {

	
	Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	WebDriver driver =null;
	WebDriverWait wait =null;
	WebDriver mozilla=null;
	WebDriver chrome=null;
	WebDriver ie=null;
	static WebConnector w;
	private Properties localProperties=null;
	private XlsReader datatable =null;

	
	private WebConnector(){
		getConfigration();
		getXlsReader();
	}
	

	// Rading Configration
	public void getConfigration(){
		String str = "config.properties";
		InputStream localInputStream = ClassLoader.getSystemResourceAsStream(str);
		if (localInputStream == null) {
			localInputStream = WebConnector.class.getResourceAsStream(str);
			
		}
		if (localInputStream != null) {
			localProperties = new Properties();
			try {
				localProperties.load(localInputStream);
			} catch (Exception e) {
			}
		}
	}
	
	public Properties getLocalProperties() {
		return localProperties;
	}
	

	
	// Xls Object
	public void getXlsReader(){
		String xlspath = System.getProperty("user.dir")+"//src//main//resources//"+ localProperties.getProperty("xls");
		if (xlspath.contains("\\ "))
				{
			System.out.println("file contains space in it");
			
				}
		
		datatable = new XlsReader(xlspath);


	}
	public XlsReader getDatatable() {
		return datatable;
	}
	
	

	
	/// ****************Application Independent functions************************ ///

	// opening the browser
	public void openBrowser(String browserType){
		log("Opening browser in "+browserType);
		if(browserType.equals("Mozilla") && mozilla==null){
			driver = new FirefoxDriver();
			mozilla=driver;
		}else if(browserType.equals("Mozilla") && mozilla!=null){
			driver=mozilla;
		}else if(browserType.equals("Chrome") && chrome==null){
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//chromedriver.exe");
			driver=new ChromeDriver();
			chrome=driver;
		}else if(browserType.equals("Chrome") && chrome==null){
			driver=chrome;
		}
		
		else if(browserType.equals("IE")){
			// set the IE server exe path and initialize
		} else
			System.out.println("Browser not defined");
		// max
		driver.manage().window().maximize();
		


		
	}
	// navigates to a URL
	public void navigateToURL(String URL) {
		//driver.navigate()
		log("navigate to URL "+URL);
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.SECONDS);
		
		// Need to remove below sleep
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		

		
	
	}
	// clicking on any object
	public void click(String text, String objectName){
		log("Clicking on " + objectName);
		driver.findElement(By.id("username")).sendKeys("migration");
		driver.findElement(By.id("password")).sendKeys("propel@cpe");
		driver.findElement(By.id("submit")).click();
		isElementPresent("//*[@id='dashboard-tile_services']/div/div/div/div/div/div");
		driver.findElement(By.xpath("//*[@id='dashboard-tile_services']/div/div/div/div/div/div")).click();
		isElementPresent("//*[@id='default_dc_link']");
		driver.findElement(By.xpath("//*[@id='default_dc_link']")).click();
		isElementPresent("//*[@id='center-list']/li[3]/a");
		driver.findElement(By.xpath("//*[@id='center-list']/li[3]/a")).click();
		//isElementPresent("//html/body/div[1]/div/div/div/div/div/div/ui-view/div[2]/ui-view/div/div/div[1]/div/sort-selector[2]/a");
		//driver.findElement(By.xpath("//html/body/div[1]/div/div/div/div/div/div/ui-view/div[2]/ui-view/div/div/div[1]/div/sort-selector[2]/a")).click();
		//isElementPresent("//*[@id='dashboard-tile_services']/div/div/div/div/div/div");
		//driver.findElement(By.xpath("//*[@id='sort-types246166096']/li[3]/a")).click();
		driver.close();
		//Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='sort-types195147279']/li[2]/a")));
		//dropdown.selectByIndex(1);
		//dropdown.selectByVisibleText("Group By Catalog Item And Owner");
		//Group By Catalog Item And Owner
		driver.findElement(By.id(text)).sendKeys(objectName);
		
	}
	
	public void type(String text, String objectName){
		log("Typing in " + objectName);
		driver.findElement(By.id("username")).sendKeys( "migration");
		driver.findElement(By.id("submit")).click();
		
	}
	
	public void select(String text, String objectName){
		log("Selecting from "+ objectName);
	
	}
	
	public  void isElementPresent(String xpath){
		WebDriverWait wait = new WebDriverWait(driver, 100000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

	}

	/// ****************Application dependent functions************************ ///

	

	
	
	//Singleton for WebConnector/
	public static WebConnector getInstance(){
		if(w==null)
			w= new WebConnector();		
		return w;
	}
	
	//Logging Object/
	public void log(String msg){
		APPLICATION_LOGS.info(msg);
	}

}

