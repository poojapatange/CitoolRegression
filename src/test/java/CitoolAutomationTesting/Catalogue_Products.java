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
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Catalogue_Products {
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	SendEMailcitrussClass objSendEMail = new SendEMailcitrussClass();
	private static int invalidImageCount;

	@BeforeTest
	public void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/STMExtentReport.html", true);
		extent.addSystemInfo("Host Name", "citruss tv").addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "Pooja PS");
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
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\PoojaPatange\\Downloads\\workfolder\\chromedrive\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://citool.ctv-it.net");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[1]/input"))
				.sendKeys("pooja.patange@citruss.com");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[2]/input")).sendKeys("Welcome1!");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/button")).click();
		String Products = Catalogue_Products.productsmethod(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		if (Products.equals("fail")) {
			Assert.assertEquals(Products, "verifying Products");
			logger.log(LogStatus.FAIL, "Test Case (failTest) Status is failed");
		}
	}

	@Test
	public static String productsmethod(WebDriver driver) throws InterruptedException {
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
			System.out.println("i came to product");
			driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/ul[4]/li/a/span")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			WebElement element = driver.findElement(By.xpath("//*[@id='searchTextProduct']"));// search products
			element.sendKeys("steel");
			element.sendKeys(Keys.ENTER);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='onoffswitch-pilot']/div/div[1]/div/div/label/span[2]")).click();// hide sales kit
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='onoffswitch-pilot']/div/div[3]/div/div/label/span[2]")).click();//visible products
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='onoffswitch-pilot']/div/button[4]")).click();//stock
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='onoffswitch-pilot']/div/button[2]")).click();// export
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(12000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[2]/product-search-comp/div[1]/div/div[2]/div/div[3]/input")).sendKeys("2");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[2]/product-search-comp/div[1]/div/div[2]/div/div[3]/button")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='priceSelect']")).click();//tvprice
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/product-search-comp/div[1]/div/div[2]/div/div[4]/div/select/option[1]")).click();//tvprice
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/product-search-comp/div[1]/div/div[2]/div/div[4]/div/select/option[3]")).click();//tvprice
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/product-search-comp/div[1]/div/div[2]/div/div[4]/div/select/option[2]")).click();//tvprice
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[2]/product-search-comp/div[1]/div/div[2]/div/div[5]/div/div/div[1]/div[1]/div[1]/div/div/i")).click();//view
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='myModalDetails']/div/div/div[1]/button")).click();//arabic/english
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[2]/product-search-comp/div[1]/div/div[1]/div/div[1]/i")).click();
			
	
		} catch (Exception e) {
			e.printStackTrace();
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
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println(" Main Menu Test Cases have been failed");
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			
			  String screenshotPath = CallCenterTools_Pilot.getScreenhot(driver, result.getName());
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
