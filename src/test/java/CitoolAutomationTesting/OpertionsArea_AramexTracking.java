package CitoolAutomationTesting;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class OpertionsArea_AramexTracking {
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	SendEMailcitrussClass objSendEMail = new SendEMailcitrussClass();
	private static int invalidImageCount;
	private static XSSFCell cell;

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
		String Aramex = OpertionsArea_AramexTracking.aramextracking(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		String AramexFileUpload = OpertionsArea_AramexTracking.FileUpload(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);

		// validation
		if (Aramex.equals("fail") || AramexFileUpload.equals("fail")) {
			Assert.assertEquals(Aramex, "verifying Attendance Tracking");
			logger.log(LogStatus.FAIL, "Test Case (failTest) Status is failed");
		}

	}

	// to check whether image have any invalid/broken images
	@Test
	public static String aramextracking(WebDriver driver) throws InterruptedException {
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
			System.out.println("Aramex Tracking");
			driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]")).click(); // mainmenu
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);

			WebElement scrolldown1 = driver.findElement(By.xpath("/html/body/div/div[1]/div/div/ul[8]/li/a/span"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scrolldown1);// scroll down
																										// page
			Thread.sleep(10000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.xpath("/html/body/div/div[1]/div/div/ul[8]/li/a/span")).click(); // click on aramex
																									// Tracking module
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);

			WebElement srollup1 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[1]/div/h2"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", srollup1);
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // scroll up

			File scr = new File("C:\\Users\\PoojaPatange\\Downloads\\AWB Numbers.xlsx"); // Data driven method for
			FileInputStream inputStream = new FileInputStream(scr);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet1 = wb.getSheetAt(0);

			double data0 = sheet1.getRow(2).getCell(0).getNumericCellValue();
			String value = NumberToTextConverter.toText(data0);
			System.out.println(value);
			WebElement DatadeivenData = driver.findElement(By.xpath("//*[@id=\"txtWayBillNumber\"]"));
			DatadeivenData.click();
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			DatadeivenData.sendKeys(value);
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div/div[2]/div/button[2]")).click();
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div/div[2]/div/button[1]")).click();
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement scrollup = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[1]/div/h2"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scrollup);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "fail";
		}
		return "sucess";
	}

	@Test
	public static String FileUpload(WebDriver driver) {
		try {
			System.out.println("uploading AWB excel file");

			WebElement srollup2 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", srollup2);
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			String filepath = "C:\\Users\\PoojaPatange\\Downloads\\AWB Numbers.xlsx";
			WebElement chooseFile = driver.findElement(By.xpath("//*[@id=\"fileShipmentTracking\"]"));
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			chooseFile.sendKeys(filepath);
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div/div[2]/div/button[2]")).click();
			;
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			/*
			 * Screen screen = new Screen(); Pattern fileUpload = new Pattern(
			 * "C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomationRegression\\lib\\Armx-tracking-fileUpload(1).PNG"
			 * ); Pattern fileSelect = new Pattern(
			 * "C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomationRegression\\lib\\Armx-tracking-select-desktop.PNG"
			 * ); Pattern filepath = new Pattern(
			 * "C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomationRegression\\lib\\Armx-tracking-EnterFilePath.PNG"
			 * ); Pattern UploadFile = new Pattern(
			 * "C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomationRegression\\lib\\Armx-tracking-Open.PNG"
			 * ); Pattern Search = new Pattern(
			 * "C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomationRegression\\lib\\Armx-tracking-Clicksearch.PNG"
			 * ); Pattern SearchClick = new Pattern(
			 * "C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomationRegression\\lib\\Armx-tracking-Clicksearch(1).PNG"
			 * ); screen.click(fileUpload); screen.wait(fileSelect, 5);
			 * screen.click(fileSelect); screen.wait(filepath, 5); screen.type(filepath,
			 * "AWB.xlsx"); screen.wait(UploadFile, 5); screen.click(UploadFile);
			 * screen.find(Search).exists(Search); System.out.println(Search);
			 * 
			 * if(Search!=null) { screen.click(SearchClick);
			 * 
			 * }
			 */

		} catch (Exception e) {
			// TODO: handle exception
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
			String screenshotPath = OpertionsArea_AramexTracking.getScreenhot(driver, result.getName());
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
