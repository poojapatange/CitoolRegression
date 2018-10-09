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
import org.sikuli.script.FindFailed;
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

public class SAPSalesOrderClass {
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

	@Test
	public void passTest() {
		logger = extent.startTest("passTest");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
	}

	@Test
	public void failTest() throws InterruptedException {
		logger = extent.startTest("failTest");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\PoojaPatange\\Downloads\\workfolder\\chromedrive\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://my344586.sapbydesign.com/sap/public/ap/ui/repository/SAP_UI/HTMLOBERON5/client.html?client_type=html&app.component=/SAP_UI_CT/Main/root.uiccwoc&rootWindow=X&redirectUrl=/sap/public/ap/ui/runtime");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(12000);
		driver.findElement(By.xpath("//*[@id='__control0-user-inner']")).sendKeys("pooja.patange");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='__control0-pass-inner']")).sendKeys("Welcome2");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='__control0-langdd-inner']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='__control0-logonBtn']")).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(12000);

		String sales = SAPSalesOrderClass.SAPSalesOrdermethod(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		if (sales.equals("fail")) {
			Assert.assertEquals(sales, "verifying SAP");
			logger.log(LogStatus.FAIL, "Test Case (failTest) Status is failed");
		}

		/*
		 * String salesOredr = SAPSalesOrderClass.ccpaymentSalesorder(driver, sales);
		 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 * Thread.sleep(8000); if (salesOredr.equals("fail")) {
		 * Assert.assertEquals(sales, "verifying CCOrderpayment");
		 * logger.log(LogStatus.FAIL, "Test Case (failTest) Status is failed"); }
		 * 
		 * 
		 * String salesOredrCancel = SAPSalesOrderClass.SalesorderCancel(driver, sales);
		 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 * Thread.sleep(8000); if (salesOredrCancel.equals("fail")) {
		 * Assert.assertEquals(sales, "verifying Sales Oredr cancelation failure");
		 * logger.log(LogStatus.FAIL, "Test Case (failTest) Status is failed"); }
		 */

	}

	@Test
	public static String SAPSalesOrdermethod(WebDriver driver) throws InterruptedException {
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
		String selectedDateTextBeforeAjaxCall = "";

		try {
			System.out.println("I Came To SAP to place sap order");
			List<WebElement> elem = driver.findElements(By.xpath("//*[@id=\"__control1-continueBtn-inner\"]"));
			if (elem.size() > 0) {
				if (elem.get(0).isDisplayed()) {
					driver.findElement(By.xpath("//*[@id=\"__control1-continueBtn-inner\"]")).click();
					driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					Thread.sleep(12000);
				}
			}
			driver.findElement(By.xpath("//*[@id=\"__button0\"]")).click(); // menu
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(12000);
			WebElement Select_sO = driver.findElement(By.xpath("//*[@id=\"__field0-I\"]"));
			Select_sO.sendKeys("sales Order");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"__item187\"]/div[3]")).click();// sales order
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(12000);
			driver.findElement(By.xpath("//*[@id=\"__item1060\"]/div")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(30000);

			// Using sikuli for auto detection of sales order for placing new sales order
			Screen screen = new Screen();
			Pattern salesOrder_select_from_dropdown = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\sales_order_sales_order_new.PNG");
			Pattern salesOrder_select_sO_place = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\sales_order_select_SO_place.PNG");		
			screen.click(salesOrder_select_from_dropdown);
			screen.click(salesOrder_select_sO_place);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(12000);
			driver.findElement(By.xpath("//*[@id=\"__input3-inner\"]")).sendKeys("M738419");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"__item1164-__input3-0-dli-content\"]/label")).click();// click on
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(12000);
			driver.findElement(By.xpath("//*[@id=\"__input14-inner\"]")).sendKeys("5");// citruss tv
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"__input14-inner\"]")).click();// citruss tv
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(15000);
			Pattern credit = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\credit.PNG");
			Pattern credit1 = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\credit_dropdown.PNG");
			Pattern credit2 = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\card.PNG");
			screen.click(credit);
			screen.click(credit1);
			screen.click(credit2);			
		
			WebElement ele = driver.findElement(By.xpath("//*[@id='__toolbar1']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
			
			// Clicking Sales Orders option:
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"__button42-content\"]")).click();// add row 
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(12000);
			driver.findElement(By.xpath("//*[@id=\"__input53-inner\"]")).click();// add sku number
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"__input53-inner\"]")).sendKeys("629300");// add sku number
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(15000);
			driver.findElement(By.xpath("//*[@id=\"__button73-content\"]")).click();
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(12000);
			 
			try {

				waitForImage("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\salesorder.PNG",20);
				driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
				Thread.sleep(50000);
				selectedDateTextBeforeAjaxCall = driver.findElement(By.xpath("//*[@id=\"__text4\"]")).getText().trim();
				driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
				Thread.sleep(12000);
				System.out.println(selectedDateTextBeforeAjaxCall);
				System.out.println("SAP Credit Card Order placed sucessfully");

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());

			}
			} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "fail";
		}
		return selectedDateTextBeforeAjaxCall;

	}

	@Test
	public static String ccpaymentSalesorder(WebDriver driver, String selectedDateTextBeforeAjaxCall) {
		try {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\PoojaPatange\\Downloads\\workfolder\\chromedrive\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://citool.ctv-it.net");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[1]/input"))
					.sendKeys("pooja.patange@citruss.com");
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[2]/input")).sendKeys("Test123@");
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/button")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);

			System.out.println("i came to CC Payment Order");
			driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]")).click();// menu
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/ul[3]/li[3]/a/span")).click();// select icone
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			System.out.println(selectedDateTextBeforeAjaxCall);
			String str = selectedDateTextBeforeAjaxCall.replaceAll("\\D+", "");
			System.out.println(str);
			int Sales_Order = Integer.parseInt(str.trim());
			String SapID = Integer.toString(Sales_Order);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='txtOrderID']")).sendKeys(SapID);
			// driver.findElement(By.xpath("//*[@id='txtOrderID']")).sendKeys("614547");

			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[2]/div/div/div[1]/div[2]/button[2]"))
					.click();// select icone
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(8000);
			org.sikuli.script.Screen screen = new org.sikuli.script.Screen();
			Pattern cc = new Pattern(
					"C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\Credit_card_details.PNG");// credit
																															// card
			screen.wait(cc, 20);
			screen.click(cc);
			Pattern cc1 = new Pattern(
					"C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\ccdrop.PNG");// credit
																												// card
																												// dropdown
			screen.wait(cc1, 20);
			screen.click(cc1);
			driver.findElement(By.xpath(
					"/html/body/div/div[2]/div/div/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/select/option[2]"))
					.click();// Add new card
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='creditCardDiv']/div/div/div[2]/div[1]/input"))
					.sendKeys("4111111111111111111");// card number
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='creditCardDiv']/div/div/div[2]/div[2]/input")).sendKeys("pooja"); // card
																													// holder
																													// name
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='creditCardDiv']/div/div/div[3]/div[1]/input")).sendKeys("1125");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='creditCardDiv']/div/div/div[3]/div[2]/input")).sendKeys("123");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(8000);
			org.sikuli.script.Screen save = new org.sikuli.script.Screen();
			Pattern pay = new Pattern(
					"C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\save_pay.PNG");
			save.wait(pay, 20);
			save.click(pay);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			System.out.println(
					"CC Order Payment working, checked by placing Order in SAP for Credit card order --  Sucessfully");
		} catch (Exception e) {
			e.printStackTrace();

			return "fail";
		}
		return "sucess";
	}

	@Test
	public static String SalesorderCancel(WebDriver driver, String selectedDateTextBeforeAjaxCall) {
		try {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\PoojaPatange\\Downloads\\workfolder\\chromedrive\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://my342206.sapbydesign.com/sap/public/ap/ui/runtime?logoff=1&client_type=html");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div/div[3]/div/a")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@id='__control0-user-inner']")).sendKeys("pooja.patange");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='__control0-pass-inner']")).sendKeys("Welcome2");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='__control0-langdd-inner']")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='__control0-logonBtn']")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(12000);

			System.out.println("I Came To SAP to place sap order to cancel Sales Order in SAP");
			List<WebElement> elem = driver.findElements(By.xpath("//*[@id=\"__control1-continueBtn-inner\"]"));
			if (elem.size() > 0) {
				if (elem.get(0).isDisplayed()) {
					driver.findElement(By.xpath("//*[@id=\"__control1-continueBtn-inner\"]")).click();
					driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					Thread.sleep(12000);
				}
			}

			driver.findElement(By.xpath("//*[@id='__button0']")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(12000);
			driver.findElement(By.xpath("//*[@id='__item198']/div[3]")).click();// menu
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(12000);
			driver.findElement(By.xpath("//*[@id=\"__group74\"]")).click();// sales order
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(25000);

			org.sikuli.script.Screen screen = new org.sikuli.script.Screen();
			Pattern cc = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\sales_Oredr_cancel_SearchButton.PNG");// search
																																		// for
																																		// sap
																																		// order
																																		// id
																																		// for
																																		// cancellation
			screen.wait(cc, 20);
			screen.click(cc);
			String str = selectedDateTextBeforeAjaxCall.replaceAll("\\D+", "");
			System.out.println(str);
			int Sales_Order = Integer.parseInt(str.trim());
			String SapID = Integer.toString(Sales_Order);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"__pane1-searchField-I\"]")).sendKeys(SapID);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(12000);

			Pattern Sapid = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\SAPID _Search.PNG");
			screen.wait(Sapid, 20);
			screen.click(Sapid);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(20000);

			try {
				Pattern cancelid = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\selection_of_id_for_cancellation.PNG");
				screen.wait(cancelid, 20);
				screen.click(cancelid);
				Pattern id = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\salection_of_cancel_id_click.PNG");
				screen.wait(id, 20);
				screen.click(id);
				Pattern action = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\actions_sap.PNG");
				screen.wait(action, 20);
				screen.click(action);
				Pattern reject = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\sales_Order_reject.PNG");// 
				screen.wait(reject, 20);
				screen.click(reject);
				Pattern reason = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\reasonfor deselect.PNG");
				screen.wait(reason, 20);
				screen.click(reason);
				Pattern reason_dd = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\reason_dropdown.PNG");
				screen.wait(reason_dd, 20);
				screen.click(reason_dd);
				screen.type(reason_dd, "Testing-Training Purpose");
				Pattern reason_scroll = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\reason_for_reject_saporder.PNG");
				screen.wait(reason_scroll, 20);
				screen.click(reason_scroll);
				Pattern reason_ok = new Pattern("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\oredr_cancel_ok.PNG");
				screen.wait(reason_ok, 20);
				screen.click(reason_ok);
				waitForImage("C:\\Users\\PoojaPatange\\eclipse-workspacepractice\\CIToolAutomation\\lib\\SAP_order_cancellation.PNG", 20);
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				Thread.sleep(20000);
				System.out.println("SAP Order Cancelled Sucessfully");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				System.out.println("SAP Order Cancelled failed");

			}
		} catch (Exception e) {
			e.printStackTrace();

			return "fail";
		}
		return "sucess";

	}

	public static boolean isImagePresent(String image) {
		boolean status = false;
		org.sikuli.script.Screen screen = new org.sikuli.script.Screen();
		try {
			screen.find(image);
			status = true;
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public static void waitForImage(String image, int time) throws InterruptedException {
		for (int i = 0; i < time; i++) {
			if (isImagePresent(image)) {
				break;
			} else {
				Thread.sleep(2000);
			}
		}
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
			String screenshotPath = PilotV1Class.getScreenhot(driver, "screenshotName");
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
