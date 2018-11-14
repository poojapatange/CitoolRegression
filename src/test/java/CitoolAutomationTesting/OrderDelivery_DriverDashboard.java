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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class OrderDelivery_DriverDashboard {
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
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	// This method is to Decide whether Test is pass or fail
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
				"C:\\Users\\PoojaPatange\\Downloads\\workfolder\\chromedrive\\chromedriver.exe"); // Initialize chrome
		// browser
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // Maximize screen
		driver.get("https://citool.ctv-it.net"); // Website URL
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Login page with valid Credentials
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[1]/input"))
				.sendKeys("pooja.patange@citruss.com");
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[2]/input")).sendKeys("Welcome1!");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/button")).click();

		// Methods
		String OrderDelivery = OrderDelivery_DriverDashboard.OrderTrack(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);

		// validation
		if (OrderDelivery.equals("fail")) {
			Assert.assertEquals(OrderDelivery, "verifying Deliver Dashboard");
			logger.log(LogStatus.FAIL, "Test Case (failTest) Status is failed");
		}

	}

	// to check whether image have any invalid/broken images
	@Test
	public static String OrderTrack(WebDriver driver) throws InterruptedException {
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

			// Automation Check for Module
			System.out.println("Driver Dashboard");
			driver.findElement(By.xpath("/html/body/header/div[1]/div/div[2]")).click(); // mainmenu
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);

			WebElement scrolldown = driver.findElement(By.xpath("/html/body/footer/div[3]/div[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scrolldown); // scroll down
																										// till it
																										// fiends Driver
																										// dashbord icon
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.xpath("/html/body/div/div[1]/div/div/ul[7]/li/a/span")).click(); // select module
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"carga_{{order.idField.valueField}}\"]/td[2]/h4")));

			WebElement scrollup = driver
					.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[1]/div/h2")); // scroll up
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scrollup);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"carga_{{order.idField.valueField}}\"]/td[2]/h4")).click(); // click
																												// on
																												// Order
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String TakeOrderID = driver
					.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/div[1]/div[2]/b"))
					.getText(); // sales order copy
			System.out.println(TakeOrderID);
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.xpath("//*[@id=\"txtSalesOrderIdVerify\"]")).sendKeys(TakeOrderID);
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(
					By.xpath("html/body/div[1]/div[2]/div/div/div/div/div/div[5]/table/tbody/tr[1]/td/div/span/button"))
					.click();
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement signature = driver
					.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/canvas"));

			//mouse action to draw signature will put three dots in parallel
			Actions builder = new Actions(driver);
			Action drawAction = builder.moveToElement(signature, 135, 15).click().moveByOffset(200, 60).click()
					.moveByOffset(100, 70).doubleClick().build();
			drawAction.perform();
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div[3]/div[2]/button")).click();
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("/html/body/div[3]/h2"));
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("/html/body/div[3]/div[7]/div/button")).click();
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
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
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = OrderDelivery_DriverDashboard.getScreenhot(driver, result.getName());
			System.out.println("Taken screenshot");
			objSendEMail.emailsend(screenshotPath);// send email
			System.out.println("Sent To Mail ID");
			// To add it in the extent report
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