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
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class IAAS {
	
	//PARAMETERS
    public static String login_username = "ALBAG009";
    public static String login_password = "Pass@word1";
    public static String rel_usr_prprty;
    public static String usr_email = "george.x.albantov.-nd@disney.com";
    public static String tenant = "PORTAL";
    public static String servername = "PORTAL_JENKINS";

    public static String servname = "~,!,@,#,$,%,^,&,*,-,+,=,{,},[,],|,?,>,.,<,`,:,;"; //except letters, numb, underscores
    public static String costcent = "~,!,@,#,$,%,^,&,*,-,+,=,{,},[,],|,?,>,.,<,`,:,;,q,W,e";
    public static String test = "~,!,@,#,$,%,^,&,*,-,+,=,{,},[,],|,?,>,<,`,:,;,q,W,e"; //except . 
    

    
    
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
	//Verify that user with valid permissions is able to access USERS module
	public void PORTAL_IAAS_001() {
	        driver.get("http://qa.service.corp.disney.com/#IaaS");
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
			Assert.assertEquals("URL", "https://qa.service.corp.disney.com/#IaaS", driver.getCurrentUrl());
	        //wait for element to load
	        sleep(8);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header")));
			driver.findElement(By.className("header")).click();	
	}
    
    @Test
	//Verify that Servers widget is loaded with default columns
	public void PORTAL_IAAS_002() {
    	Assert.assertEquals("Status", driver.findElement(By.xpath("//tr[@role='row']/th[2]")).getText());
    	Assert.assertEquals("Monitors", driver.findElement(By.xpath("//tr[@role='row']/th[3]")).getText());
    	Assert.assertEquals("Server", driver.findElement(By.xpath("//tr[@role='row']/th[4]")).getText());
    	Assert.assertEquals("Public VIP", driver.findElement(By.xpath("//tr[@role='row']/th[5]")).getText());
    	Assert.assertEquals("Size", driver.findElement(By.xpath("//tr[@role='row']/th[6]")).getText());
    	Assert.assertEquals("Service", driver.findElement(By.xpath("//tr[@role='row']/th[8]")).getText());

	}
    
    @Test
	//Verify that row configuration icon has valid options
	public void PORTAL_IAAS_003() {
    	driver.findElement(By.xpath("//span[@class='ss_sprite ss_cog icon']")).click();	
    	Assert.assertEquals("Status", driver.findElement(By.xpath("//div/label[contains(.,'Status')]")).getText());
    	Assert.assertEquals("Monitors", driver.findElement(By.xpath("//div/label[contains(.,'Monitors')]")).getText());
    	Assert.assertEquals("Server", driver.findElement(By.xpath("//div/label[contains(.,'Server')]")).getText());
    	Assert.assertEquals("Public VIP", driver.findElement(By.xpath("//div/label[contains(.,'Public VIP')]")).getText());
    	Assert.assertEquals("Size", driver.findElement(By.xpath("//div/label[contains(.,'Size')]")).getText());
    	Assert.assertEquals("Tenant", driver.findElement(By.xpath("//div/label[contains(.,'Tenant')]")).getText());
    	Assert.assertEquals("Server Group", driver.findElement(By.xpath("//div/label[contains(.,'Server Group')]")).getText());
    	Assert.assertEquals("Service", driver.findElement(By.xpath("//div/label[contains(.,'Service')]")).getText());
    	Assert.assertEquals("Image", driver.findElement(By.xpath("//div/label[contains(.,'Image')]")).getText());
    	Assert.assertEquals("Updated", driver.findElement(By.xpath("//div/label[contains(.,'Updated')]")).getText());
    	Assert.assertEquals("Created", driver.findElement(By.xpath("//div/label[contains(.,'Created')]")).getText());
    	
	}
    
    @Test
	//Verify that user is able to activate remaining columns in configuration menu
	public void PORTAL_IAAS_004() {
    	driver.findElement(By.xpath("//div/label[contains(.,'Server Group')]")).click();	
    	driver.findElement(By.xpath("//div/label[contains(.,'Image')]")).click();
    	driver.findElement(By.xpath("//div/label[contains(.,'Updated')]")).click();
    	driver.findElement(By.xpath("//div/label[contains(.,'Created')]")).click(); 
    	driver.findElement(By.xpath("//span[@class='ss_sprite ss_cog icon']")).click();	

    }
    
    @Test
	//Verify that user is able to close row configuration menu by clicking icon
	public void PORTAL_IAAS_005() {
    	Assert.assertEquals("Status", driver.findElement(By.xpath("//tr[@role='row']/th[2]")).getText());
    	Assert.assertEquals("Monitors", driver.findElement(By.xpath("//tr[@role='row']/th[3]")).getText());
    	Assert.assertEquals("Server", driver.findElement(By.xpath("//tr[@role='row']/th[4]")).getText());
    	Assert.assertEquals("Public VIP", driver.findElement(By.xpath("//tr[@role='row']/th[5]")).getText());
    	Assert.assertEquals("Size", driver.findElement(By.xpath("//tr[@role='row']/th[6]")).getText());
    	Assert.assertEquals("Server Group", driver.findElement(By.xpath("//tr[@role='row']/th[8]")).getText());
    	Assert.assertEquals("Service", driver.findElement(By.xpath("//tr[@role='row']/th[9]")).getText());
    	Assert.assertEquals("Image", driver.findElement(By.xpath("//tr[@role='row']/th[10]")).getText());
    	Assert.assertEquals("Updated", driver.findElement(By.xpath("//tr[@role='row']/th[11]")).getText());
    	Assert.assertEquals("Created", driver.findElement(By.xpath("//tr[@role='row']/th[12]")).getText());

    }
    
    @Test
	//Verify that New Server VM button opens IaaS provisioning wizard on Platform configuration menu
	public void PORTAL_IAAS_006() {
    	driver.findElement(By.xpath("//button[@class='new primary']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='step step1 inprogress' and contains(.,'Platform')]")));
    	Assert.assertEquals("Platform", driver.findElement(By.xpath("//div[@class='step step1 inprogress']")).getText());
    }
    
    @Test
	//Verify OpenStack based provisioning
	public void PORTAL_IAAS_007() {
    	driver.findElement(By.xpath("//input[@id='servers_w_platform_option_OpenStack']")).click();
    	driver.findElement(By.className("next")).click();
    }
    
    @Test
	//Verify that Properties configuration menu is displayed and all required form fields are rendered
	public void PORTAL_IAAS_008() {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='step step2 inprogress' and contains(.,'Properties')]")));
    	Assert.assertEquals("Properties", driver.findElement(By.xpath("//div[@class='step step2 inprogress']")).getText());
    	Assert.assertEquals(tenant, driver.findElement(By.xpath("//span[@class='tenantWidget_span tenantWidget']")).getText());
    }
    
    @Test
	//Verify that Server Name field accepts Letters, numbers or underscores only
	public void PORTAL_IAAS_009() {
		for (String pp : servname.split("\\,|\\,")){
			driver.findElement(By.xpath("//input[@id='servers_w_name']")).sendKeys(pp);
			driver.findElement(By.xpath("//button[@class='next']")).click();
			String error = driver.findElement(By.xpath("//input[@id='servers_w_name']")).getAttribute("class");
			driver.findElement(By.xpath("//input[@id='servers_w_name']")).clear();
			if(error.equals("required server_w_checkName good ui-state-highlight")){
				assert true;
			}
			
			else {
				assert false;
					
				}
			}
		
    }
    
    @Test
	//Verify that Server Name input field accepts only 128 characters
	public void PORTAL_IAAS_010() {
    	driver.findElement(By.xpath("//input[@id='servers_w_name']")).clear();
    	Assert.assertEquals("128", driver.findElement(By.xpath("//input[@id='servers_w_name']")).getAttribute("maxlength"));
    	   	
    }
    
    @Test
	//Verify that CloudInit User Data input field accepts only 20480 characters
	public void PORTAL_IAAS_011() {
    	Assert.assertEquals("20480", driver.findElement(By.xpath("//textarea[@id='servers_w_userData']")).getAttribute("maxlength"));
    }
    
    @Test
	//Verify that Server Name input field checks for name availability(negative)
	public void PORTAL_IAAS_012() {
    	driver.findElement(By.xpath("//input[@id='servers_w_name']")).sendKeys(servername);
    	driver.findElement(By.xpath("//div[@class='step step2 inprogress']")).click();
    	sleep(5);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ui-tooltip-content']")));
    	Assert.assertEquals("This name is not available", driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText());
    }
    
    @Test
	//Verify that Server Name input field checks for name availability(positive)
	public void PORTAL_IAAS_013() {
    	driver.findElement(By.xpath("//input[@id='servers_w_name']")).clear();
    	driver.findElement(By.xpath("//input[@id='servers_w_name']")).sendKeys("TEST101");
    	sleep(2);
    	driver.findElement(By.xpath("//div[@class='step step2 inprogress']")).click();
    	sleep(5);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ui-tooltip-content']")));
    	Assert.assertEquals("This name is available", driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText());
    }
    
    @Test
	//Verify that user is able to enter 20480 characters into User Data
	public void PORTAL_IAAS_014() throws FileNotFoundException {
        String text = new Scanner(new File("media/cloud_user_data.txt")).useDelimiter("\\Z").next();
        driver.findElement(By.xpath("//textarea[@id='servers_w_userData']")).sendKeys(text);
    	//WebElement form = driver.findElement(By.xpath("//textarea[@id='servers_w_userData']"));
		//jsLib.callEmbeddedSelenium(driver, functionName, element, values)
        
    }
    
    @Test
	//Verify that Image Options configuration menu is displayed and all required form fields are rendered
	public void PORTAL_IAAS_015() {
    	driver.findElement(By.className("next")).click();
    	Assert.assertEquals("Image Options", driver.findElement(By.xpath("//div[@class='step step3 inprogress']")).getText());

    }
    
    @Test
	//Verify that Server Images table is rendered
	public void PORTAL_IAAS_016() {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']")));
    	Assert.assertEquals("e64f33ad-1bf2-41b8-b4b9-86e401d3e44c", driver.findElement(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']/tbody/tr[contains(.,'CentOS release 6.3')]")).getAttribute("rel"));
    	Assert.assertEquals("830a711e-017f-4862-a2e5-78964313cc49", driver.findElement(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']/tbody/tr[contains(.,'Metacloud Virtual Loadbalancer release 2.2')]")).getAttribute("rel"));
    	Assert.assertEquals("fd4adc86-6510-48d2-a02e-20f0e327065d", driver.findElement(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']/tbody/tr[contains(.,'Red Hat Enterprise Linux Server release 5.8')]")).getAttribute("rel"));
    	Assert.assertEquals("63479efd-4348-43e8-9adf-4fcca85bae7f", driver.findElement(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']/tbody/tr[contains(.,'Red Hat Enterprise Linux Server release 6.3')]")).getAttribute("rel"));
    	Assert.assertEquals("ca9c8f2c-4104-415c-bf3f-1e67df193da0", driver.findElement(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']/tbody/tr[contains(.,'Windows Server 2008 R2 Standard SP1')]")).getAttribute("rel"));
    }
    
    @Test
	//Verify that user is able to modify Server Size
	public void PORTAL_IAAS_017() {
    	Actions builder = new Actions(driver);
    	WebElement handle = driver.findElement(By.xpath("//a[@id='handle_servers_w_select_name']"));
    	WebElement smaller = driver.findElement(By.xpath("//li[@style='left:0.00%']"));
    	WebElement small = driver.findElement(By.xpath("//li[@style='left:33.33%']"));
    	WebElement medium = driver.findElement(By.xpath("//li[@style='left:66.67%']"));
    	WebElement large = driver.findElement(By.xpath("//li[@style='left:100.00%']"));
    	   Action dragsmall = builder.clickAndHold(handle)
    	       .moveToElement(small)
    	       .release(small)
    	       .build();
    	   dragsmall.perform();
    	   Action dragmedium = builder.clickAndHold(handle)
        	       .moveToElement(medium)
        	       .release(medium)
        	       .build();
        	   dragmedium.perform();
        	   Action draglarge = builder.clickAndHold(handle)
            	       .moveToElement(large)
            	       .release(large)
            	       .build();
            	   draglarge.perform();
            	   Action dragsmaller = builder.clickAndHold(handle)
                	       .moveToElement(smaller)
                	       .release(smaller)
                	       .build();
                	   dragsmaller.perform();
                	   
        //Allocate additional disk capacity 
        driver.findElement(By.xpath("//input[@id='servers_size_extra_disk']")).click();
        Assert.assertEquals("1 VCPU, 150GB Disk, and 4096MB RAM", driver.findElement(By.xpath("//div[@id='servers_size_data']")).getText());
        driver.findElement(By.xpath("//input[@id='servers_size_extra_disk']")).click();
        Assert.assertEquals("1 VCPU, 50GB Disk, and 4096MB RAM", driver.findElement(By.xpath("//div[@id='servers_size_data']")).getText());
    	   
    }
    
    @Test
	//Verify that Back button functionality
	public void PORTAL_IAAS_018() {
    	driver.findElement(By.className("prev")).click();
    	Assert.assertEquals("Properties", driver.findElement(By.xpath("//div[@class='step step2 inprogress']")).getText());
    	driver.findElement(By.className("prev")).click();
    	Assert.assertEquals("Platform", driver.findElement(By.xpath("//div[@class='step step1 inprogress']")).getText());
    }
    
    @Test
	//Verify that user is able to select VMware based Hosting
	public void PORTAL_IAAS_019() {
    	driver.findElement(By.xpath("//input[@id='servers_w_platform_option_VMware']")).click();
    	driver.findElement(By.className("next")).click();

    }
    
    
    @Test
	//Verify that VMware Server Name maxlength is 15 characters
	public void PORTAL_IAAS_020() {
    	Assert.assertEquals("15", driver.findElement(By.xpath("//input[@id='servers_w_name_VMware']")).getAttribute("maxlength"));

    }
    
    @Test
	//Verify that only alphanumeric (A-Z, 0-9) characters are used in a server name
	public void PORTAL_IAAS_021() {
    	for (String pp : servname.split("\\,|\\,")){
			driver.findElement(By.xpath("//input[@id='servers_w_name_VMware']")).sendKeys(pp);
			driver.findElement(By.xpath("//button[@class='next']")).click();
			String error = driver.findElement(By.xpath("//input[@id='servers_w_name_VMware']")).getAttribute("class");
			driver.findElement(By.xpath("//input[@id='servers_w_name_VMware']")).clear();
			if(error.equals("required server_w_checkName_VMware good ui-state-highlight")){
				assert true;
			}
			
			else {
				assert false;
					
				}
			}
    	
    	driver.findElement(By.xpath("//input[@id='servers_w_name_VMware']")).clear();
    	driver.findElement(By.xpath("//input[@id='servers_w_name_VMware']")).sendKeys("TEST101");

    }
    
    @Test
	//Verify Admin Account domain groups
	public void PORTAL_IAAS_022() {
    	Assert.assertEquals("SWNA", driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='SWNA']")).getText());
    	Assert.assertEquals("NENA", driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='NENA']")).getText());
    	Assert.assertEquals("WDW", driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='WDW']")).getText());
    	Assert.assertEquals("EMEA", driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='EMEA']")).getText());
    	Assert.assertEquals("LATAM", driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='LATAM']")).getText());
    	Assert.assertEquals("APAC", driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='APAC']")).getText());
    	Assert.assertEquals("ESPNCORP", driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='ESPNCORP']")).getText());
    	Assert.assertEquals("WOC", driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='WOC']")).getText());
    	Assert.assertEquals("MGMTPROD", driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='MGMTPROD']")).getText());
    	Assert.assertEquals("DISID", driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='DISID']")).getText());
    	driver.findElement(By.xpath("//select[@id='servers_adminGroups']/option[@value='SWNA']")).click();
    	

    }
    
    @Test
	//Verify that only alphanumeric (A-Z, 0-9) characters are used for Admin Account
	public void PORTAL_IAAS_023() {
    	for (String pp : servname.split("\\,|\\,")){
			driver.findElement(By.xpath("//input[@id='servers_w_admin_user_VMware']")).sendKeys(pp);
			driver.findElement(By.xpath("//button[@class='next']")).click();
			String error = driver.findElement(By.xpath("//input[@id='servers_w_admin_user_VMware']")).getAttribute("class");
			driver.findElement(By.xpath("//input[@id='servers_w_admin_user_VMware']")).clear();
			if(error.equals("required ui-state-highlight")){
				assert true;
			}
			
			else {
				assert false;
					
				}
			}
    	
    	driver.findElement(By.xpath("//input[@id='servers_w_admin_user_VMware']")).clear();
    	driver.findElement(By.xpath("//input[@id='servers_w_admin_user_VMware']")).sendKeys("TEST101");
    	

    }
    
    @Test
	//Verify that Image Options configuration menu is displayed and all required form fields are rendered
	public void PORTAL_IAAS_024() {
    	driver.findElement(By.className("next")).click();
    	Assert.assertEquals("Image Options", driver.findElement(By.xpath("//div[@class='step step3 inprogress']")).getText());

    }
    
    @Test
	//Verify that Server Images table is rendered
	public void PORTAL_IAAS_025() {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']")));
    	Assert.assertEquals("rhel5-64", driver.findElement(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']/tbody/tr[contains(.,'RHEL 5 Linux 64Bit')]")).getAttribute("rel"));
    	Assert.assertEquals("rhel6-64", driver.findElement(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']/tbody/tr[contains(.,'RHEL 6 Linux 64Bit')]")).getAttribute("rel"));
    	Assert.assertEquals("win2k8 ent r2 gold v1", driver.findElement(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']/tbody/tr[contains(.,'Windows 2008 R2 Ent 64 Bit')]")).getAttribute("rel"));
    	Assert.assertEquals("win2k8 std r2 gold v1", driver.findElement(By.xpath("//div[@id='servers_ServerImagesWidget']/div[@id='servers_serverImages_wrapper']/table[@id='servers_serverImages']/tbody/tr[contains(.,'Windows 2008 R2 Std 64 Bit')]")).getAttribute("rel"));

    }
    
    @Test
	//Verify that user is able to modify Server Size - VCPUs
	public void PORTAL_IAAS_026() {
    	Actions builder = new Actions(driver);
    	WebElement handle = driver.findElement(By.xpath("//a[@id='handle_servers_w_select_vcpus']"));
    	WebElement vcpu1 = driver.findElement(By.xpath("//li[@style='left:0.00%']"));
    	WebElement vcpu2 = driver.findElement(By.xpath("//li[@style='left:50.00%']"));
    	WebElement vcpu4 = driver.findElement(By.xpath("//li[@style='left:100.00%']"));
    	   Action dragsmall = builder.clickAndHold(handle)
    	       .moveToElement(vcpu2)
    	       .release(vcpu2)
    	       .build();
    	   dragsmall.perform();
    	   Action dragmedium = builder.clickAndHold(handle)
        	       .moveToElement(vcpu4)
        	       .release(vcpu4)
        	       .build();
        	   dragmedium.perform();
        	   Action draglarge = builder.clickAndHold(handle)
            	       .moveToElement(vcpu1)
            	       .release(vcpu1)
            	       .build();
            	   draglarge.perform();
  	   
    }
    
    @Test
	//Verify that user is able to modify Server Size - Memory
	public void PORTAL_IAAS_027() {
    	Actions builder = new Actions(driver);
    	WebElement handle = driver.findElement(By.xpath("//a[@id='handle_servers_w_select_ram']"));
    	WebElement n1 = driver.findElement(By.xpath("//li[@style='left:0.00%']"));
    	WebElement n2 = driver.findElement(By.xpath("//li[@style='left:6.67%']"));
    	WebElement n3 = driver.findElement(By.xpath("//li[@style='left:13.33%']"));
    	WebElement n4 = driver.findElement(By.xpath("//li[@style='left:20.00%']"));
    	WebElement n5 = driver.findElement(By.xpath("//li[@style='left:26.67%']"));
    	WebElement n6 = driver.findElement(By.xpath("//li[@style='left:33.33%']"));
    	WebElement n7 = driver.findElement(By.xpath("//li[@style='left:40.00%']"));
    	WebElement n8 = driver.findElement(By.xpath("//li[@style='left:46.67%']"));
    	WebElement n9 = driver.findElement(By.xpath("//li[@style='left:53.33%']"));
    	WebElement n10 = driver.findElement(By.xpath("//li[@style='left:60.00%']"));
    	WebElement n11 = driver.findElement(By.xpath("//li[@style='left:66.67%']"));
    	WebElement n12 = driver.findElement(By.xpath("//li[@style='left:73.33%']"));
    	WebElement n13 = driver.findElement(By.xpath("//li[@style='left:80.00%']"));
    	WebElement n14 = driver.findElement(By.xpath("//li[@style='left:86.67%']"));
    	WebElement n15 = driver.findElement(By.xpath("//li[@style='left:93.33%']"));
    	WebElement n16 = driver.findElement(By.xpath("//li[@style='left:100.00%']"));
    	
    	   Action drag2= builder.clickAndHold(handle)
    	       .moveToElement(n2)
    	       .release(n2)
    	       .build();
    	   drag2.perform();
    	   Action drag3 = builder.clickAndHold(handle)
        	       .moveToElement(n3)
        	       .release(n3)
        	       .build();
        	   drag3.perform();
        	   Action drag4 = builder.clickAndHold(handle)
            	       .moveToElement(n4)
            	       .release(n4)
            	       .build();
            	   drag4.perform();
            	   Action drag5 = builder.clickAndHold(handle)
                	       .moveToElement(n5)
                	       .release(n5)
                	       .build();
                	   drag5.perform();
                	   Action drag6 = builder.clickAndHold(handle)
                    	       .moveToElement(n6)
                    	       .release(n6)
                    	       .build();
                    	   drag6.perform();
                    	   Action drag7 = builder.clickAndHold(handle)
                        	       .moveToElement(n7)
                        	       .release(n7)
                        	       .build();
                        	   drag7.perform();
                        	   Action drag8 = builder.clickAndHold(handle)
                            	       .moveToElement(n8)
                            	       .release(n8)
                            	       .build();
                            	   drag8.perform();
                            	   Action drag9 = builder.clickAndHold(handle)
                                	       .moveToElement(n9)
                                	       .release(n9)
                                	       .build();
                                	   drag9.perform();
                                	   Action drag10 = builder.clickAndHold(handle)
                                    	       .moveToElement(n10)
                                    	       .release(n10)
                                    	       .build();
                                    	   drag10.perform();
                                    	   Action drag11 = builder.clickAndHold(handle)
                                        	       .moveToElement(n11)
                                        	       .release(n11)
                                        	       .build();
                                        	   drag11.perform();
                                        	   Action drag12 = builder.clickAndHold(handle)
                                            	       .moveToElement(n12)
                                            	       .release(n12)
                                            	       .build();
                                            	   drag12.perform();
                                            	   Action drag13 = builder.clickAndHold(handle)
                                                	       .moveToElement(n13)
                                                	       .release(n13)
                                                	       .build();
                                                	   drag13.perform();
                                                	   Action drag14 = builder.clickAndHold(handle)
                                                    	       .moveToElement(n14)
                                                    	       .release(n2)
                                                    	       .build();
                                                    	   drag14.perform();
                                                    	   Action drag15 = builder.clickAndHold(handle)
                                                        	       .moveToElement(n15)
                                                        	       .release(n3)
                                                        	       .build();
                                                        	   drag15.perform();
                                                        	   Action drag16 = builder.clickAndHold(handle)
                                                            	       .moveToElement(n16)
                                                            	       .release(n4)
                                                            	       .build();
                                                            	   drag16.perform();
                                                            	   Action drag1 = builder.clickAndHold(handle)
                                                                	       .moveToElement(n1)
                                                                	       .release(n1)
                                                                	       .build();
                                                                	   drag1.perform();
    	   
    }
	
}	
	