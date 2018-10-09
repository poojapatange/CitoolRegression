package CitoolAutomationTesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;

public class CCPaymentCorrectionClass extends SAPSalesOrderClass {

	  public void ccpayment(String salesOrder){

		try {
			

			System.setProperty("webdriver.chrome.driver", "C:\\Users\\PoojaPatange\\Downloads\\workfolder\\chromedrive\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://citool.ctv-it.net");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[1]/input")).sendKeys("pooja.patange@citruss.com");
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/div[2]/input")).sendKeys("Test123@");
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/form/button")).click();
			String Products = ProductsClass.productsmethod(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			if (Products.equals("fail")) {
				Assert.assertEquals(Products, "verifying Products");
				logger.log(LogStatus.FAIL, "Test Case (failTest) Status is failed");
			}
			
			
			
			System.out.println("i came to CC Payment Correction");
			driver.findElement(By.xpath("html/body/header/div[1]/div/div[2]")).click();//menu
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(8000);
			driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/ul[3]/li[3]/a/span")).click();//select icone 
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(5000);
	
			/*driver.findElement(By.xpath("//*[@id='txtOrderID']")).sendKeys("");//send credit card sap order id 
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/div[2]/div/div/div[1]/div[2]/button[2]")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);	driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);	driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.findElement(By.xpath("")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Thread.sleep(10000);*/
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
