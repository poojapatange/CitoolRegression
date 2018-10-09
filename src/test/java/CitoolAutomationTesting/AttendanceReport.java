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

public class AttendanceReport { // records from ashwath
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
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[1]/input")).sendKeys("pooja.patange@citruss.com");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[2]/input")).sendKeys("Welcome1!");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/button")).click();
		String AttendanceReportMethod = AttendanceReport.AgentMethod(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		String QueueMethod = AttendanceReport.QueueMethod(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);		
		String GroupReportMethod = AttendanceReport.GroupMethod(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		if (AttendanceReportMethod.equals("fail") || QueueMethod.equals("fail") || GroupReportMethod.equals("fail")) {
			Assert.assertEquals(AttendanceReportMethod, "verifying Attendance Report");
			logger.log(LogStatus.FAIL, "Test Case (failTest) Status is failed");
		}

	}

	@Test
	public static String AgentMethod(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 20);
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
			System.out.println("Checking for Agent");
			driver.findElement(By.xpath("/html/body/header/div[1]/div/div[2]")).click();// menu
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("/html/body/div/div[1]/div/div/ul[2]/li[1]/a/span[1]")).click();// reports
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("/html/body/div/div[1]/div/div/ul[2]/li[1]/ul/li[1]/a/span")).click();// attendance
																												// report
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			WebElement Attendance_dataList;
			Attendance_dataList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[1]/label")));
			Attendance_dataList.click();
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[1]/div/input")).click();// change
																											// date from
																											// current
																											// date to
																											// august
																											// 13th
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[1]/div/input")).clear();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[1]/div/input")).sendKeys("14/09/2018");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[2]/div/input")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[2]/div/input")).clear();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[2]/div/input")).sendKeys("14/09/2018");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/button[1]/span[1]")).click();// click on
																											// validate
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			WebElement Attendance_dataList1;
			Attendance_dataList1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/p")));// data
																													// table
																					 								// of
																													// attendence
																													// report
			Attendance_dataList1.click();
			String AgentFirstName = driver.findElement(By.xpath("//*[@id='table_wrapper']/tbody/tr[1]/td[1]")).getText();// get first name of agent column
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='demo1']/div/div[2]/div/div/div[6]/div/div/button")).click();// click on select agent dropdown
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='demo1']/div/div[2]/div/div/div[6]/div/div/ul/li[4]/div/input")).sendKeys(AgentFirstName);// sending keys of first name of agent column
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='demo1']/div/div[2]/div/div/div[6]/div/div/ul/li[6]/a")).click();// click
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='demo1']/div/div[3]/div/label[2]")).click();// apply filter
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(15000);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		return "sucess";

	}

	@Test
	public static String QueueMethod(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 20);
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
			System.out.println("checking for queue");
			driver.findElement(By.xpath("//*[@id='demo1']/div/div[2]/div/div/button[2]")).click();// menu
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='demo1']/div/div[1]/div/div/label[2]/div/ins")).click();// reports
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[1]/div/input")).clear();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[1]/div/input")).sendKeys("14/09/2018");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[2]/div/input")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[2]/div/input")).clear();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[2]/div/input")).sendKeys("14/09/2018");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/button[1]/span[1]")).click();// click on
																											// validate
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/button[1]/span[1]")).click();//validate
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			WebElement Quenue_dataList1;
			Quenue_dataList1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='table_attendance_queues_length']/label")));// data
																												// table
																												// of
																												// attendence
																												// report
			Quenue_dataList1.click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(15000);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		return "sucess";

	}

	@Test
	public static String GroupMethod(WebDriver driver) throws InterruptedException {
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
			System.out.println("checking for Group");
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/button[2]")).click();// menu
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[1]/div/div/label[3]/div/ins")).click();// reports
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[1]/div/input")).clear();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[1]/div/input")).sendKeys("14/09/2018");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[2]/div/input")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[2]/div/input")).clear();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/div[2]/div/input")).sendKeys("14/09/2018");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/button[1]/span[1]")).click();// click on
																											// validate
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id=\"demo1\"]/div/div[2]/div/div/button[1]/span[1]")).click();//validate
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			/*WebElement Quenue_dataList1;
			Quenue_dataList1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='table_attendance_queues_length']/label")));// data table of attendence report																						
			Quenue_dataList1.click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(15000);*/

		} catch (Exception e) {
			// TODO: handle exception
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
	/*
	 * @Test public void skipTest(){ logger = extent.startTest("skipTest"); throw
	 * new SkipException("Skipping - This is not ready for testing "); }
	 */

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println(" Attendence Report Test Cases have been failed");
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = AttendanceReport.getScreenhot(driver, result.getName());
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
