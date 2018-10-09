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

public class PilotV1Class {
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
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName+ ".png";
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
		String PilotV1 = PilotV1Class.pilotv1method(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		if (PilotV1.equals("fail")) {
			Assert.assertEquals(PilotV1, "verifying PilotV1");
			logger.log(LogStatus.FAIL, "Test Case (failTest) Status is failed");
		}

	}

	@Test
	public static String pilotv1method(WebDriver driver) throws InterruptedException {
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
			System.out.println("i came to pilotv1");
			driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/ul[3]/li[4]/a/i")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/div[1]/div/ul/li[3]/a/i")).click();
			driver.findElement(By.xpath("//*[@id='phonenumber']")).sendKeys("0568974568");
			driver.findElement(By.xpath("//*[@id='title']")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/div[1]/div/div/div/div/div/form/div[2]/div[1]/select/option[2]")).click();
			driver.findElement(By.xpath("//*[@id='lang']/option[3]")).click();
			driver.findElement(By.xpath("//*[@id='firstname']")).sendKeys("Dubai");
			driver.findElement(By.xpath("//*[@id='lastname']")).sendKeys("citruss tv");
			driver.findElement(By.xpath("//*[@id='countrycode']")).click();
			driver.findElement(By.xpath("//*[@id='countrycode']/option[5]")).click();
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/div[1]/div/div/div/div/div/form/div[5]/div/select/option[5]")).click();
			driver.findElement(By.xpath("//*[@id='currency']")).click();
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/div[1]/div/div/div/div/div/form/div[6]/div/select/option[4]")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='formCust']/form/div[7]/div/button[1]")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='successNewCustomer']/div/div/div[3]/button[2]")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(10000);
			
			try {
				driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div[1]/div/div/div[1]/div/ul/li[1]/a")).click();// click on phone to add phone number in address
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);				
				driver.findElement(By.xpath("//*[@id='phone']")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);	
				driver.findElement(By.xpath("//*[@id='phone']")).clear();
				WebElement newreg = driver.findElement(By.xpath("//*[@id='phone']"));
				Thread.sleep(5000);	
				newreg.sendKeys("0589837434");
				newreg.sendKeys(Keys.ENTER);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='Accounts-Related']/div[1]/div[1]/button[1]")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/div[3]/div/ul/li[2]/a/i")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='phoneNumb2']")).sendKeys("0589837434");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id=\"countryCodeAdd\"]")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div/div/div/div/form/div[3]/div/select/option[2]")).click();//select AE country
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id=\"formAddress\"]/div[4]/div/div/button/span[1]")).click();//select AE country
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id=\"formAddress\"]/div[4]/div/div/ul/li[4]/a")).click();//select AE country
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id=\"streetName\"]")).sendKeys("Citruss TV, UAE");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				
			} catch (Exception e) {				
				e.printStackTrace();
				return "fail";
			}

			try {

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				WebElement ele = driver.findElement(By.xpath("//*[@id='searchTextProduct']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
				WebElement element = driver.findElement(By.xpath("//*[@id='searchTextProduct']"));// search products
				element.sendKeys("Tria");
				element.sendKeys(Keys.ENTER);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='onoffswitch-pilot']/div/div[1]/div/div/label/span[2]")).click();// hide sales kit
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='onoffswitch-pilot']/div/div[3]/div/div/label/span[2]")).click();//visible products
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='onoffswitch-pilot']/div/button[3]")).click();//stock
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='onoffswitch-pilot']/div/button[1]")).click();// export
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='productSearch']/div/product-search-comp/div[1]/div/div[2]/div/div[3]/input")).sendKeys("2");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id='productSearch']/div/product-search-comp/div[1]/div/div[2]/div/div[3]/button")).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='priceSelect']")).click();//tvprice
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div/product-search-comp/div[1]/div/div[2]/div/div[4]/div/select/option[3]")).click();//tvprice
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div/product-search-comp/div[1]/div/div[2]/div/div[4]/div/select/option[1]")).click();//tvprice
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div[2]/div/div/div/div/div[1]/div/product-search-comp/div[1]/div/div[2]/div/div[4]/div/select/option[2]")).click();//tvprice
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='productSearch']/div/product-search-comp/div[1]/div/div[2]/div/div[5]/div/div/div[1]/div[1]/div[1]/div/div/i")).click();//view
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='myModalDetails']/div/div/div[1]/div/div/div/label/span[2]")).click();//arabic/english
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='myModalDetails']/div/div/div[1]/button[2]")).click();//minimize
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(12000);
				
				driver.findElement(By.xpath("//*[@id='product-block']/div/ul/li[2]/a")).click();//click on popup mini-mization
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='productDetails']/div/div/div/div/div[1]/div/div/div/label/span[2]")).click();//tvprice
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='product-block']/div/ul/li[2]/i")).click();//close
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//*[@id='productSearch']/div/product-search-comp/div[1]/div/div[1]/div/div[1]/i")).click();//product recently aired 
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(12000);
				//no data in preprod
				

			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		return "sucess";
	}
	/*
	 * @Test public void skipTest(){ logger = extent.startTest("skipTest"); throw
	 * new SkipException("Skipping - This is not ready for testing "); }
	 */

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
			/*String screenshotPath = PilotV1Class.getScreenhot(driver, result.getName());
			System.out.println("Taken screenshot");
			objSendEMail.emailsend(screenshotPath);// send email
			System.out.println("Sent To Mail ID");
			// To add it in the extent report
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));*/
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
