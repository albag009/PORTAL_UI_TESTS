package portal;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KMTC {
	
	//PARAMETERS
    public static String login_username = "ALBAG009";
    public static String login_password = "Pass@word1";
    public static String rel_usr_prprty;
    public static String usr_email = "george.x.albantov.-nd@disney.com";
    public static String usr_frst_name = "Test";
    public static String usr_lst_name = "300";
    public static String usr_mgmtname = "test300.user";
    public static String usr_full_name = "George Albantov";

    public static String prn = "~,!,@,#,$,%,^,&,*,-,+,=,{,},[,],|,?,>,.,<,`,:,;";
    public static String costcent = "~,!,@,#,$,%,^,&,*,-,+,=,{,},[,],|,?,>,.,<,`,:,;,q,W,e";
    public static String datavolume = "~,!,@,#,$,%,^,&,*,-,+,=,{,},[,],|,?,>,<,`,:,;,q,W,e"; //except . 

    
    
	private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
		//driver.manage().window().maximize();
	    driver.manage().window().setPosition(new Point(0,0));
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension dim = new Dimension((int) screenSize.getWidth(), (int)screenSize.getHeight());
	    driver.manage().window().setSize(dim);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }
    
    private static void sleep(int seconds) {

        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
    JavascriptLibrary jsLib = new JavascriptLibrary();
    
    WebDriverWait wait = new WebDriverWait(driver, 60);
    
    //TEST BEGINS
    
    
    @Test
	//Verify that user with valid permissions is able to access Access module
	public void PORTAL_KMTC_001() {
	        driver.get("http://qa.service.corp.disney.com/#kmtc");
	        //wait for element to load
	        //sleep(5);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login_username")));
	        //Enter Login credentials
	        WebElement login_username = driver.findElement(By.name("login_username"));
	        WebElement login_password = driver.findElement(By.name("login_password"));
	        login_username.sendKeys(portal.USERS.login_username);
	        login_password.sendKeys(portal.USERS.login_password);
	        //Submit login
	        WebElement login_submit = driver.findElement(By.name("login_submit"));
	        login_submit.submit();
	        sleep(15);
			Assert.assertEquals("URL", "https://qa.service.corp.disney.com/#kmtc", driver.getCurrentUrl());
	        //wait for element to load
	        sleep(8);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header")));
			driver.findElement(By.className("header")).click();	
	}
    
    @Test
    //Verify that Virtual Server Request Tickets widget is loaded and contains proper columns
    public void PORTAL_KMTC_002(){
    	//sleep(5);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='kmtc_VMTable' and contains(.,'Status')]")));
    	driver.findElement(By.xpath("//div[@class='kmtc_VMTable' and contains(.,'Status')]"));
    	driver.findElement(By.xpath("//div[@class='kmtc_VMTable' and contains(.,'Project Name')]"));
    	driver.findElement(By.xpath("//div[@class='kmtc_VMTable' and contains(.,'Ticket')]"));
    	driver.findElement(By.xpath("//div[@class='kmtc_VMTable' and contains(.,'Updated')]"));
    	driver.findElement(By.xpath("//div[@class='kmtc_VMTable' and contains(.,'Requested')]"));
    	driver.findElement(By.xpath("//div[@class='kmtc_VMTable' and contains(.,'Creator')]"));
    	
    }
    
	@Test
	//Verify that clicking NEW USER button will open wizard menu at Request step
	public void PORTAL_KMTC_003(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("new")));
		driver.findElement(By.className("new")).click();
		//wait for element to load
		//sleep(5);
		driver.findElement(By.xpath("//div[@class='step step1 inprogress' and contains(.,'Request')]"));
		
	}
    
	@Test
	//Verify that Requester fields are pre-loaded
	public void PORTAL_KMTC_004(){
		WebElement kmtc_w_sapid = driver.findElement(By.xpath("//input[@id='kmtc_w_sapid']"));
		Assert.assertEquals(login_username, kmtc_w_sapid.getAttribute("value"));
		WebElement kmtc_w_name = driver.findElement(By.xpath("//input[@id='kmtc_w_name']"));
		Assert.assertEquals(usr_full_name, kmtc_w_name.getAttribute("value"));
		WebElement kmtc_w_email = driver.findElement(By.xpath("//input[@id='kmtc_w_email']"));
		Assert.assertEquals(usr_email, kmtc_w_email.getAttribute("value"));
		
	}
	
	@Test
	//Verify that Project Name input field accepts only Letters, numbers, spaces and parentheses
	public void PORTAL_KMTC_005(){
		
		for (String pp : prn.split("\\,|\\,")){
			driver.findElement(By.xpath("//input[@id='kmtc_w_project_name']")).sendKeys(pp);
			driver.findElement(By.xpath("//button[@class='next']")).click();
			String error = driver.findElement(By.xpath("//div[contains(.,'Letters, numbers, spaces and perenthesis only please')]")).getText();
			driver.findElement(By.xpath("//input[@id='kmtc_w_project_name']")).clear();
			if(error.equals("Letters, numbers, spaces and perenthesis only please")){
				assert true;
			}
			
			else {
				assert false;
					
				}
				
			}
	}
	
	@Test
	//Verify that Business Unit drop-down menu contains valid options
	public void PORTAL_KMTC_006(){
		Assert.assertEquals("Media", driver.findElement(By.xpath("//select[@id='kmtc_w_segment']/option[@value='A']")).getText());
		Assert.assertEquals("Corporate", driver.findElement(By.xpath("//select[@id='kmtc_w_segment']/option[@value='C']")).getText());
		Assert.assertEquals("Disneyland", driver.findElement(By.xpath("//select[@id='kmtc_w_segment']/option[@value='D']")).getText());
		Assert.assertEquals("WDSHE (Studio)", driver.findElement(By.xpath("//select[@id='kmtc_w_segment']/option[@value='B']")).getText());
		Assert.assertEquals("WDI", driver.findElement(By.xpath("//select[@id='kmtc_w_segment']/option[@value='I']")).getText());
		Assert.assertEquals("Consumer Products", driver.findElement(By.xpath("//select[@id='kmtc_w_segment']/option[@value='P']")).getText());
		Assert.assertEquals("Shared (DTSS)", driver.findElement(By.xpath("//select[@id='kmtc_w_segment']/option[@value='S']")).getText());
		Assert.assertEquals("WDW", driver.findElement(By.xpath("//select[@id='kmtc_w_segment']/option[@value='W']")).getText());
	}
	
	@Test
	//Verify that Cost Center input field accepts only numbers
	public void PORTAL_KMTC_007(){
		driver.findElement(By.xpath("//input[@id='kmtc_w_project_name']")).sendKeys("TEST_PROJECT");
		driver.findElement(By.xpath("//select[@id='kmtc_w_segment']")).click();
		driver.findElement(By.xpath("//select[@id='kmtc_w_segment']/option[@value='C']"));
		
		for (String pp : costcent.split("\\,|\\,")){
			driver.findElement(By.xpath("//input[@id='kmtc_w_cost']")).sendKeys(pp);
			driver.findElement(By.xpath("//button[@class='next']")).click();
			String error = driver.findElement(By.xpath("//input[@id='kmtc_w_cost']")).getAttribute("class");
			driver.findElement(By.xpath("//input[@id='kmtc_w_cost']")).clear();
			if(error.equals("required ui-autocomplete-input ui-state-highlight")){
				assert true;
			}
			
			else {
				assert false;
					
				}
				
			}
	}
		
	@Test
	//Verify Cost Center UI auto-complete function
	public void PORTAL_KMTC_008(){
		driver.findElement(By.xpath("//input[@id='kmtc_w_cost']")).sendKeys("0");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-menu-item")));
		//sleep(5);
		driver.findElement(By.xpath("//a[contains(.,'0005000018')]")).click();
		
	}
	
	@Test
	//Verify Server Owner UI auto-complete function
	public void PORTAL_KMTC_009(){
		driver.findElement(By.xpath("//input[@id='kmtc_w_primary_contact_display']")).sendKeys("albantov");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-menu-item")));
		sleep(2);
		driver.findElement(By.xpath("//a[contains(.,'ALBAG009')]")).click();
		
	}
	
	@Test
	//Verify that Domain User Account input field accepts only "domain\\username" format
	public void PORTAL_KMTC_010(){
		driver.findElement(By.xpath("//input[@id='kmtc_w_domain_user_account']")).sendKeys("swnagalbantov");
		driver.findElement(By.xpath("//button[@class='next']")).click();
		//sleep(5);
		Assert.assertEquals("required ui-state-highlight", driver.findElement(By.xpath("//input[@id='kmtc_w_domain_user_account']")).getAttribute("class"));
		driver.findElement(By.xpath("//input[@id='kmtc_w_domain_user_account']")).clear();
		driver.findElement(By.xpath("//input[@id='kmtc_w_domain_user_account']")).sendKeys("swna\\galbantov");
		
	}
	
	
	@Test
	//Verify that Next button will open Network configuration menu
	public void PORTAL_KMTC_0011(){
		driver.findElement(By.xpath("//button[@class='next']")).click();
		//wait for element to load
		//sleep(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='step step2 inprogress' and contains(.,'Network')]")));
		//Assert.assertEquals("Network", driver.findElement(By.xpath("//div[@class='step step2 inprogress']")).getText());		
	}
	
	@Test
	//Verify default Network configuration settings
	public void PORTAL_KMTC_0012(){
		Assert.assertEquals("No", driver.findElement(By.xpath("//input[@id='kmtc_w_pci_no']")).getAttribute("value"));
		Assert.assertEquals("Yes", driver.findElement(By.xpath("//input[@id='kmtc_w_pci_yes']")).getAttribute("value"));
		Assert.assertEquals("No", driver.findElement(By.xpath("//input[@id='kmtc_w_public_no']")).getAttribute("value"));
		Assert.assertEquals("Yes", driver.findElement(By.xpath("//input[@id='kmtc_w_public_yes']")).getAttribute("value"));
		Assert.assertEquals("No", driver.findElement(By.xpath("//input[@id='kmtc_w_end_user_accessible_no']")).getAttribute("value"));
		Assert.assertEquals("Yes", driver.findElement(By.xpath("//input[@id='kmtc_w_end_user_accessible_yes']")).getAttribute("value"));
		Assert.assertEquals("Low", driver.findElement(By.xpath("//input[@id='kmtc_w_impactlow']")).getAttribute("value"));
		Assert.assertEquals("High", driver.findElement(By.xpath("//input[@id='kmtc_w_impacthigh']")).getAttribute("value"));
		Assert.assertEquals("Pre-Prod", driver.findElement(By.xpath("//input[@id='kmtc_w_prod_no']")).getAttribute("value"));
		Assert.assertEquals("Prod", driver.findElement(By.xpath("//input[@id='kmtc_w_prod_yes']")).getAttribute("value"));
				
	}
	
/*	@Test
	//Verify that clicking Yes radio-button on PCI Compliant option, disables Internet Accessible and Impact selectors
	public void PORTAL_KMTC_0013(){
		driver.findElement(By.xpath("//input[@id='kmtc_w_pci_yes']")).click();
		driver.findElement(By.xpath("//input[@id='kmtc_w_public_yes']")).click();
				
	}*/
	
	@Test
	//Verify that clicking Next button will open Image configuration menu
	public void PORTAL_KMTC_0013(){
		driver.findElement(By.xpath("//button[@class='next']")).click();
		//wait for element to load
		//sleep(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='step step3 inprogress' and contains(.,'Image')]")));
		//Assert.assertEquals("Image", driver.findElement(By.xpath("//div[@class='step step3 inprogress']")).getText());			
	}
	
	@Test
	//Verify that Server Function drop down menu contains valid options
	public void PORTAL_KMTC_0014(){
		Assert.assertEquals("Application Server", driver.findElement(By.xpath("//select[@id='kmtc_w_server_function']/option[@value='AP']")).getText());
		Assert.assertEquals("Database General", driver.findElement(By.xpath("//select[@id='kmtc_w_server_function']/option[@value='DS']")).getText());
		Assert.assertEquals("Web Server (HTTP Services)", driver.findElement(By.xpath("//select[@id='kmtc_w_server_function']/option[@value='WW']")).getText());

	}
	
	@Test
	//Verify that Image Type drop down menu contains valid options
	public void PORTAL_KMTC_0015(){
		Assert.assertEquals("Windows 2008 R2 Enteprise 64-Bit", driver.findElement(By.xpath("//select[@id='kmtc_w_image']/option[@value='Windows 2008 R2 Enteprise 64-Bit']")).getText());
		Assert.assertEquals("Windows 2008 R2 Standard 64-Bit", driver.findElement(By.xpath("//select[@id='kmtc_w_image']/option[@value='Windows 2008 R2 Standard 64-Bit']")).getText());
		Assert.assertEquals("Windows 2008 R1 Enteprise 32-Bit", driver.findElement(By.xpath("//select[@id='kmtc_w_image']/option[@value='Windows 2008 R1 Enteprise 32-Bit']")).getText());
		//Assert.assertEquals("Windows 2008 R1 Standard 32-Bit", driver.findElement(By.xpath("//select[@id='kmtc_w_image']/option[@value='Windows 2008 R1 Standard 32-Bit']")).getText());
		Assert.assertEquals("Red Hat Linux v5 64-Bit", driver.findElement(By.xpath("//select[@id='kmtc_w_image']/option[@value='Red Hat Linux v5 64-Bit']")).getText());
		Assert.assertEquals("Red Hat Linux v5 32-Bit", driver.findElement(By.xpath("//select[@id='kmtc_w_image']/option[@value='Red Hat Linux v5 32-Bit']")).getText());
			
	}
	
	@Test
	//Verify that Image Size drop down menu contains valid options
	public void PORTAL_KMTC_0016(){
		Assert.assertEquals("Small - 2vCPU / 4GB RAM", driver.findElement(By.xpath("//select[@id='kmtc_w_imagesize']/option[@value='small']")).getText());
		Assert.assertEquals("Medium - 4vCPU / 8GB RAM", driver.findElement(By.xpath("//select[@id='kmtc_w_imagesize']/option[@value='medium']")).getText());
		//Assert.assertEquals("Large - 8vCPU / 16GB RAM", driver.findElement(By.xpath("//select[@id='kmtc_w_imagesize']/option[@value='large']")).getText());
		//Assert.assertEquals("X-Large - 12vCPU / 24GB RAM", driver.findElement(By.xpath("//select[@id='kmtc_w_imagesize']/option[@value='x-large']")).getText());
	}
	
	@Test
	//Verify that Service Level drop down menu contains valid options
	public void PORTAL_KMTC_0017(){
		Assert.assertEquals("Silver", driver.findElement(By.xpath("//select[@id='kmtc_w_service_level']/option[@value='silver']")).getText());
		Assert.assertEquals("Bronze", driver.findElement(By.xpath("//select[@id='kmtc_w_service_level']/option[@value='bronze']")).getText());
		
	}
	
	@Test
	//Verify that Data Volume Size form field accepts only numbers and '.'
	public void PORTAL_KMTC_0018(){
		driver.findElement(By.xpath("//input[@id='kmtc_w_data_volume']")).clear();
		for (String pp : datavolume.split("\\,|\\,")){
			driver.findElement(By.xpath("//input[@id='kmtc_w_data_volume']")).sendKeys(pp);
			driver.findElement(By.xpath("//button[@class='next']")).click();
			String error = driver.findElement(By.xpath("//input[@id='kmtc_w_data_volume']")).getAttribute("class");
			driver.findElement(By.xpath("//input[@id='kmtc_w_data_volume']")).clear();
			if(error.equals("required ui-state-highlight")){
				assert true;
			}
			
			else {
				assert false;
					
				}
				
			}
		driver.findElement(By.xpath("//input[@id='kmtc_w_data_volume']")).clear();
		driver.findElement(By.xpath("//input[@id='kmtc_w_data_volume']")).sendKeys("10");
	}
	
	@Test
	//Verify that Time Zone drop down menu contains valid options
	public void PORTAL_KMTC_0020(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='kmtc_w_time_zone']/option[@value='UTC']")));
		Assert.assertEquals("UTC", driver.findElement(By.xpath("//select[@id='kmtc_w_time_zone']/option[@value='UTC']")).getText());
		Assert.assertEquals("Eastern", driver.findElement(By.xpath("//select[@id='kmtc_w_time_zone']/option[@value='EST']")).getText());
		Assert.assertEquals("Central", driver.findElement(By.xpath("//select[@id='kmtc_w_time_zone']/option[@value='CST']")).getText());
		Assert.assertEquals("Mountain", driver.findElement(By.xpath("//select[@id='kmtc_w_time_zone']/option[@value='MST']")).getText());
		Assert.assertEquals("Pacific", driver.findElement(By.xpath("//select[@id='kmtc_w_time_zone']/option[@value='PST']")).getText());
		
	}

	@Test
	//Verify that Domain drop down menu contains valid options
	public void PORTAL_KMTC_0021(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='kmtc_w_domain']/option[@value='disney.pvt']")));
		Assert.assertEquals("disney.pvt", driver.findElement(By.xpath("//select[@id='kmtc_w_domain']/option[@value='disney.pvt']")).getText());
		Assert.assertEquals("idmz.pvt", driver.findElement(By.xpath("//select[@id='kmtc_w_domain']/option[@value='idmz.pvt']")).getText());
		Assert.assertEquals("nena.wdpr.disney.com", driver.findElement(By.xpath("//select[@id='kmtc_w_domain']/option[@value='nena.wdpr.disney.com']")).getText());
		//Assert.assertEquals("swna.wdpr.disney.com", driver.findElement(By.xpath("//select[@id='kmtc_w_domain']/option[@value='swna.wdpr.disney.co']")).getText());
		Assert.assertEquals("wdw.disney.com", driver.findElement(By.xpath("//select[@id='kmtc_w_domain']/option[@value='wdw.disney.com']")).getText());
		Assert.assertEquals("idmzsena.idmz.disney.com", driver.findElement(By.xpath("//select[@id='kmtc_w_domain']/option[@value='idmzsena.idmz.disney.com']")).getText());
		Assert.assertEquals("idmzswna.idmz.disney.com", driver.findElement(By.xpath("//select[@id='kmtc_w_domain']/option[@value='idmzswna.idmz.disney.com']")).getText());
	}
	
	@Test
	//Verify that clicking Next button will open Image configuration menu
	public void PORTAL_KMTC_0022(){
		driver.findElement(By.xpath("//button[@class='next']")).click();
		//wait for element to load
		//sleep(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='step step4 inprogress' and contains(.,'Operations')]")));
		//Assert.assertEquals("Operations", driver.findElement(By.xpath("//div[@class='step step4 inprogress']")).getText());		
	}
	
		
	@Test
	//Verify Owner Group configuration
	public void PORTAL_KMTC_0023(){
		driver.findElement(By.xpath("//input[@id='kmtc_w_app_owning_assignment_group']")).sendKeys("a");
		sleep(5);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-menu-item")));
		driver.findElement(By.xpath("//a[contains(.,'app-ardcp-aaatest')]")).click();
		driver.findElement(By.xpath("//input[@id='kmtc_w_approver_assignment_group']")).sendKeys("bus");
		sleep(5);
		driver.findElement(By.xpath("//a[contains(.,'bus-rm-abc-abc-webproductmgmt')]")).click();
		driver.findElement(By.xpath("//input[@id='kmtc_w_local_application_owner']")).sendKeys("app-ardcp-aaatest");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-menu-item")));
		sleep(5);
		//driver.findElement(By.xpath("//a[contains(.,'app-ardcp-aaatest')]")).click();
		driver.findElement(By.xpath("//input[@id='kmtc_w_remote_application_owner']")).sendKeys("app-ardcp-aaatest");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-menu-item")));
		sleep(5);
		//driver.findElement(By.xpath("//a[contains(.,'app-ardcp-aaatest')]")).click();
	}
	
	@Test
	//Verify that Vulnerability Patch Window Week drop down menu contains valid options
	public void PORTAL_KMTC_0024(){
		Assert.assertEquals("1st", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_week']/option[@value='1']")).getText());
		Assert.assertEquals("2nd", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_week']/option[@value='2']")).getText());
		Assert.assertEquals("3rd", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_week']/option[@value='3']")).getText());
		Assert.assertEquals("4th", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_week']/option[@value='4']")).getText());
		
	}
	
	@Test
	//Verify that Vulnerability Patch Window Day drop down menu contains valid options 
	public void PORTAL_KMTC_0025(){
		Assert.assertEquals("Sunday", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_day']/option[@value='7']")).getText());
		Assert.assertEquals("Monday", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_day']/option[@value='1']")).getText());
		Assert.assertEquals("Tuesday", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_day']/option[@value='2']")).getText());
		Assert.assertEquals("Wednesday", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_day']/option[@value='3']")).getText());
		Assert.assertEquals("Thursday", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_day']/option[@value='4']")).getText());
		Assert.assertEquals("Friday", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_day']/option[@value='5']")).getText());
		Assert.assertEquals("Saturday", driver.findElement(By.xpath("//select[@id='kmtc_w_vulnpatchwindow_day']/option[@value='6']")).getText());
		
	}
	
	@Test
	//Verify that users is able to submit request and navigated back to module page
	public void PORTAL_KMTC_0026(){
		driver.findElement(By.xpath("//button[@class='next']")).click();
		sleep(10);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='kmtc_VMTable' and contains(.,'Status')]")));
    	driver.findElement(By.xpath("//div[@class='kmtc_VMTable' and contains(.,'Status')]"));
	}
	
}	
	