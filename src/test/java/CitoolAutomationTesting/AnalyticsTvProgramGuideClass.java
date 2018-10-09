package CitoolAutomationTesting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AnalyticsTvProgramGuideClass {
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	SendEMailcitrussClass objSendEMail = new SendEMailcitrussClass();
	private static int invalidImageCount;

	@BeforeTest
	public void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/STMExtentReport.html", true);
		extent.addSystemInfo("Host Name", "citruss tv").addSystemInfo("Environment", "Automation Testing").addSystemInfo("User Name", "Pooja PS");
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}

	// This method is to capture the screenshot and return the path of the
	// screenshot.

	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@Test
	public void passTest() {
		logger = extent.startTest("passTest");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
	}

	@Test
	public void failTest() throws InterruptedException {
		logger = extent.startTest("failTest");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\PoojaPatange\\Downloads\\workfolder\\chromedrive\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://citool.ctv-it.net");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[1]/input")).sendKeys("pooja.patange@citruss.com");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[2]/input")).sendKeys("Welcome1!");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/button")).click();
		String TVGuide = AnalyticsTvProgramGuideClass.TvProgramGuideMethod(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		String mbctv = AnalyticsTvProgramGuideClass.MBCTvProgramGuideMethod(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);		
		String zeealfam = AnalyticsTvProgramGuideClass.ZeeAflamTvProgramGuideMethod(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		String zeeAlwan = AnalyticsTvProgramGuideClass.ZeeAlwanTvProgramGuideMethod(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		String zeeliving = AnalyticsTvProgramGuideClass.ZeeLivingTvProgramGuideMethod(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		if (TVGuide.equals("fail") || mbctv.equals("fail") || zeeAlwan.equals("fail") || zeealfam.equals("fail") || zeeliving.equals("fail")){
			Assert.assertEquals(TVGuide, "verifying TVGuide");
			logger.log(LogStatus.FAIL, "Test Case (failTest) Status is TVGuide");
		}
	}

	public static String TvProgramGuideMethod(WebDriver driver) throws InterruptedException {
		
		try {
			invalidImageCount = 0;
			List<WebElement> imagesList = driver.findElements(By.tagName("img"));
			// System.out.println("Total no. of images are " + imagesList.size());
			for (WebElement imgElement : imagesList) {
				if (imgElement != null) {
					verifyimageActive(imgElement);

				}
			}
			System.out.println("Total no. of invalid images are " + invalidImageCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("Tv Program Guide");
			driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]")).click();//main menu
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/ul[5]/li[2]/a/span")).click();//tv program guide
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[1]/div[1]/input")).click();//change date from current date to august 13th 
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[1]/div[1]/input")).clear();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[1]/div[1]/input")).sendKeys("14/09/2018");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[2]/div/div[1]/input")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[2]/div/div[1]/input")).clear();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[2]/div/div[1]/input")).sendKeys("14/09/2018");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			//select channel
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/filter-by-channel/div/button")).click();//
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000); 
			driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div/div[2]/div/form/div/filter-by-channel/div/ul/li[2]/a")).click();//select citruss channel
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/button")).click();//select citruss tv 
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			
			try {
				
				Screen checkfordata = new Screen();
				Pattern pay = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodata.PNG");//path of image
				checkfordata.exists(pay);
				
				if(checkfordata.exists(pay)!= null) {
					System.out.println("Citruss data does not exists");
					Pattern nodata = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodataclickOk.PNG");//path of image
					checkfordata.click(nodata);
				}
				else {
					driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")).clear();
					driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					Thread.sleep(10000);
					driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")).sendKeys("2");
					driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					Thread.sleep(10000);
					driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/button[2]")).click();
					driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					Thread.sleep(10000);
				}
					
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return "fail";
			}
			}
		 catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return "fail";
			}
		return "sucess";
	}

	public static String MBCTvProgramGuideMethod(WebDriver driver) throws InterruptedException {
		
		
		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/filter-by-channel/div/button")).click();//
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/div/div[2]/div/form/div/filter-by-channel/div/ul/li[3]/a")).click();//select MBC channel
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/button")).click();//select mbc tv 
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(10000);
		try {
			Screen checkfordata = new Screen();
			Pattern pay = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodata.PNG");//path of image
			checkfordata.exists(pay);
			
			if(checkfordata.exists(pay)!= null) {
				System.out.println("MBC tv data does not exists");
				Pattern nodata = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodataclickOk.PNG");//path of image
				checkfordata.click(nodata);
			}
			else {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")).clear();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(10000);
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")).sendKeys("2");
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(10000);
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/button[2]")).click();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(10000);
			}
			
							
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "fail";
		}
		return "sucess";
	}

	
	
	
public static String ZeeAflamTvProgramGuideMethod(WebDriver driver) throws InterruptedException {
		
	driver.findElement(By.xpath("//*[@id='form-dashboard']/div/filter-by-channel/div/button")).click();//select channel
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/div/div[2]/div/form/div/filter-by-channel/div/ul/li[4]/a")).click();//select Zee-Aflam
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/button")).click();//select zeeaflam tv 
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(10000);
		try {
			Screen checkfordata = new Screen();
			Pattern pay = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodata.PNG");//path of image
			checkfordata.exists(pay);
			
			if(pay.equals(pay)) {				
				System.out.println("Zee-Aflam tv data does not exists");
				Pattern nodata = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodataclickOk.PNG");//path of image
				checkfordata.wait(nodata, 10);
				checkfordata.click(nodata);
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(10000);
			}
			else {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")).clear();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(10000);
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")).sendKeys("2");
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(10000);
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/button[2]")).click();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(15000);
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "fail";
		}		
		return "sucess";
	}

public static String ZeeAlwanTvProgramGuideMethod(WebDriver driver) throws InterruptedException {
	
	driver.findElement(By.xpath("//*[@id='form-dashboard']/div/filter-by-channel/div/button")).click();
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	Thread.sleep(10000);
	driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/div/div[2]/div/form/div/filter-by-channel/div/ul/li[5]/a")).click();//select ZeeAlwan channel
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	Thread.sleep(10000);
	driver.findElement(By.xpath("//*[@id='form-dashboard']/div/button")).click();//select citruss tv 
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	try {
		Screen checkfordata = new Screen();
		Pattern pay = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodata.PNG");//path of image
		checkfordata.wait(pay, 10);
		checkfordata.exists(pay);
		if(pay.equals(pay)) {
			
			System.out.println("Zee-Alwan tv data does not exists");	
			Pattern nodata = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodataclickOk.PNG");//path of image
			checkfordata.click(nodata);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
		}
		else {
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")).clear();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")).sendKeys("2");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/button[2]")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
		}					
	}catch (Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		return "fail";
	}		
	return "sucess";
}

public static String ZeeLivingTvProgramGuideMethod(WebDriver driver) throws InterruptedException {
		

	driver.findElement(By.xpath("//*[@id='form-dashboard']/div/filter-by-channel/div/button")).click();//select channel
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id=\"form-dashboard\"]/div/filter-by-channel/div/ul/li[6]/a")).click();//select Zee-living
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/button")).click();//select ZeeLivingTv
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(10000);
		try {
			Screen checkfordata = new Screen();
			Pattern pay = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodata.PNG");//path of image
			checkfordata.exists(pay);
			if(pay.equals(pay)) {		
				System.out.println("Zee-Living tv data does not exists");
				Pattern nodata = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodataclickOk.PNG");//path of image
				checkfordata.wait(pay, 10);
				checkfordata.click(nodata);
			}
				
			else {
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")).clear();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(10000);
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")).sendKeys("2");
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(10000);
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/button[2]")).click();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(10000);
			}					
			

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "fail";
		}		
		return "sucess";
	}

	public static void verifyimageActive(WebElement imgElement) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			org.apache.http.HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200)
				invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * @Test public void skipTest(){ logger = extent.startTest("skipTest"); throw
	 * new SkipException("Skipping - This is not ready for testing "); }
	 */

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println(" Main Menu Test Cases have been failed");
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			
			  String screenshotPath = AnalyticsTvProgramGuideClass.getScreenhot(driver, result.getName());
			  System.out.println("Taken screenshot");
			  objSendEMail.emailsend(screenshotPath);// send email
			  System.out.println("Sent To Mail ID"); // To add it in the extent report
			  logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
			 
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(logger);

	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
		driver.quit();
	}

}
