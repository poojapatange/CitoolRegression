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
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Analytics_TvProgramGuide {
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
		String TVGuide = Analytics_TvProgramGuide.TvProgramGuideMethod(driver);
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String TVmbc = Analytics_TvProgramGuide.MBCTvProgramGuideMethod(driver);
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String zeealfam = Analytics_TvProgramGuide.ZeeAflamTvProgramGuideMethod(driver);
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String zeeAlwan = Analytics_TvProgramGuide.ZeeAlwanTvProgramGuideMethod(driver);
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String zeeliving = Analytics_TvProgramGuide.ZeeLivingTvProgramGuideMethod(driver);
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// validation
		if (TVGuide.equals("fail") || TVmbc.equals("fail") || zeeAlwan.equals("fail") || zeealfam.equals("fail")
				|| zeeliving.equals("fail")) {
			Assert.assertEquals(TVGuide, "verifying TVGuide");
			logger.log(LogStatus.FAIL, "Test Case (failTest) Status is TVGuide");
		}
	}

	// to check whether image have any invalid/broken images
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
			// Automation Check for Module
			System.out.println("Tv Program Guide");
			driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]")).click();// main menu
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/ul[5]/li[2]/a/span")).click();// tv program
																										// guide
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[1]/div[1]/input")).click();// change date
																										// from current
																										// date to
																										// august 13th
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[1]/div[1]/input")).clear();
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[1]/div[1]/input")).sendKeys("01/11/2018");
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[2]/div/div[1]/input")).click();
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[2]/div/div[1]/input")).clear();
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/div[2]/div/div[1]/input"))
					.sendKeys("01/11/2018");
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// select channel
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/filter-by-channel/div/button")).click();//
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(
					"/html/body/div/div[2]/div/div/div[2]/div/div/div[2]/div/form/div/filter-by-channel/div/ul/li[2]/a"))
					.click();// select citruss channel
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='form-dashboard']/div/button")).click();// select citruss tv
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			try {
				String getstatus = driver
						.findElement(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/div[1]/label"))
						.getText();
				System.out.println(getstatus);
				if (getstatus.equals("Product Availability Determination Horizon Duration")) {

					driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/div[1]/label"))
							.getText();
					Thread.sleep(5000);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

					System.out.println("TV Program Guide Data Exists");

				} else {
					String shippingText = driver.findElement(By.xpath("/html/body/div[3]/p/span")).getText();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					Thread.sleep(5000);
					if (shippingText.equals("There is no data related to TV Program Guide.")) {
						driver.findElement(By.xpath("/html/body/div[3]/div[7]/div/button")).click();
						Thread.sleep(5000);
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						System.out.println("No Data");
					}

					/*
					 * Screen checkfordata = new Screen(); Pattern pay = new Pattern(
					 * "C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodata.PNG"
					 * );// path // of // image checkfordata.exists(pay);
					 * 
					 * if (checkfordata.exists(pay) != null) {
					 * System.out.println("Citruss data does not exists"); Pattern nodata = new
					 * Pattern(
					 * "C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\tvnodataclickOk.PNG"
					 * );// path // of // image checkfordata.click(nodata); } else {
					 * driver.findElement(By.xpath(
					 * "html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input")) .clear();
					 * driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					 * Thread.sleep(10000); driver.findElement(By.xpath(
					 * "html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/input"))
					 * .sendKeys("2"); driver.manage().timeouts().implicitlyWait(50,
					 * TimeUnit.SECONDS); Thread.sleep(10000); driver.findElement(By.xpath(
					 * "html/body/div[1]/div[2]/div/div/div[3]/div/div/div/div[1]/button[2]"))
					 * .click(); driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					 * Thread.sleep(10000); }
					 */

				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return "fail";
			}
		} catch (Exception e) {
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
		driver.findElement(By.xpath(
				"/html/body/div[1]/div[2]/div/div/div[2]/div/div/div[2]/div/form/div/filter-by-channel/div/ul/li[3]/a"))
				.click();// select MBC channel
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/button")).click();// select mbc tv
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		try {
			String getstatus = driver
					.findElement(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/div[1]/label")).getText();
			System.out.println(getstatus);
			if (getstatus.equals("Product Availability Determination Horizon Duration")) {

				driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/div[1]/label")).getText();
				Thread.sleep(5000);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				System.out.println("TV Program Guide Data Exists");

			} else {
				String shippingText = driver.findElement(By.xpath("/html/body/div[3]/p/span")).getText();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);
				if (shippingText.equals("There is no data related to TV Program Guide.")) {
					driver.findElement(By.xpath("/html/body/div[3]/div[7]/div/button")).click();
					Thread.sleep(5000);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					System.out.println("No Data");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "fail";
		}
		return "sucess";
	}

	public static String ZeeAflamTvProgramGuideMethod(WebDriver driver) throws InterruptedException {

		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/filter-by-channel/div/button")).click();// select
																											// channel
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.findElement(By.xpath(
				"/html/body/div[1]/div[2]/div/div/div[2]/div/div/div[2]/div/form/div/filter-by-channel/div/ul/li[4]/a"))
				.click();// select Zee-Aflam
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/button")).click();// select zeeaflam tv
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		try {
			// Logic is to check data available for channel or not
			String getstatus = driver
					.findElement(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/div[1]/label")).getText();
			System.out.println(getstatus);
			if (getstatus.equals("Product Availability Determination Horizon Duration")) {

				driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/div[1]/label")).getText();
				Thread.sleep(5000);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				System.out.println("TV Program Guide Data Exists");

			} else {
				String shippingText = driver.findElement(By.xpath("/html/body/div[3]/p/span")).getText();
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				if (shippingText.equals("There is no data related to TV Program Guide.")) {
					driver.findElement(By.xpath("/html/body/div[3]/div[7]/div/button")).click();
					Thread.sleep(5000);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					System.out.println("No Data");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "fail";
		}
		return "sucess";
	}

	public static String ZeeAlwanTvProgramGuideMethod(WebDriver driver) throws InterruptedException {

		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/filter-by-channel/div/button")).click();
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(
				"/html/body/div[1]/div[2]/div/div/div[2]/div/div/div[2]/div/form/div/filter-by-channel/div/ul/li[5]/a"))
				.click();// select ZeeAlwan channel
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/button")).click();// select citruss tv
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		try {
			String getstatus = driver
					.findElement(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/div[1]/label")).getText();
			Thread.sleep(10000);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			System.out.println(getstatus);
			if (getstatus.equals("Product Availability Determination Horizon Duration")) {
				driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/div[1]/label")).getText();
				Thread.sleep(5000);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				System.out.println("TV Program Guide Data Exists");

			} else {
				String shippingText = driver.findElement(By.xpath("/html/body/div[3]/p/span")).getText();
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				if (shippingText.equals("There is no data related to TV Program Guide.")) {
					driver.findElement(By.xpath("/html/body/div[3]/div[7]/div/button")).click();
					Thread.sleep(5000);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					System.out.println("No Data");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "fail";
		}
		return "sucess";
	}

	public static String ZeeLivingTvProgramGuideMethod(WebDriver driver) throws InterruptedException {

		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/filter-by-channel/div/button")).click();// select
																											// channel
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	
		driver.findElement(By.xpath("//*[@id=\"form-dashboard\"]/div/filter-by-channel/div/ul/li[6]/a")).click();// select
																													// Zee-living
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='form-dashboard']/div/button")).click();// select ZeeLivingTv
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	
		try {
			String getstatus = driver
					.findElement(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/div[1]/label")).getText();
			System.out.println(getstatus);
			if (getstatus.equals("Product Availability Determination Horizon Duration")) {

				driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[3]/div/div/div/div[1]/label")).getText();
				Thread.sleep(5000);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				System.out.println("TV Program Guide Data Exists");

			} else {

				String shippingText = driver.findElement(By.xpath("/html/body/div[3]/p/span")).getText();
				Thread.sleep(10000);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
				if (shippingText.equals("There is no data related to TV Program Guide.")) {
					driver.findElement(By.xpath("/html/body/div[3]/div[7]/div/button")).click();
					Thread.sleep(5000);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					System.out.println("No Data");
				}
			}
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
			System.out.println(" Main Menu Test Cases have been failed");
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());

			String screenshotPath = Analytics_TvProgramGuide.getScreenhot(driver, result.getName());
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
