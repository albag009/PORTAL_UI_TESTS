package portal;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class USERS {
	
	//PARAMETERS
    public static String login_username = "ALBAG009";
    public static String login_password = "Pass@word1";
    public static String rel_usr_prprty;
    public static String rel_usr;
    public static String usr_email = "test777.user@disney.com";
    public static String usr_frst_name = "Test";
    public static String usr_lst_name = "777";
    public static String usr_mgmtname = "test777.user";

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
    
    WebDriverWait wait = new WebDriverWait(driver, 5);
    
    //TEST BEGINS

	@Test
	//Verify that user with valid permissions is able to access USERS module
	public void PORTAL_USERS_001() {
	        driver.get("http://qa.service.corp.disney.com/#user");
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
	        sleep(10);
	        Assert.assertEquals("URL", "https://qa.service.corp.disney.com/#user", driver.getCurrentUrl());
	        //wait for element to load
	        sleep(8);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header")));
			driver.findElement(By.className("header")).click();	
				
	}
	
	@Test
	//Verify that Browser Stats widget is rendered
	public void PORTAL_USERS_002(){
		//wait for element to load
		//sleep(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_BrowserStatsWidget")));
		WebElement browser = driver.findElement(By.id("user_BrowserStatsWidget"));
		Assert.assertEquals("widget", browser.getAttribute("class"));
				
	}
	
	@Test
	//Verify that Users widget is rendered
	public void PORTAL_USERS_003(){
		//wait for element to load
		//sleep(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_UserListWidget")));
		WebElement browser = driver.findElement(By.id("user_UserListWidget"));
		Assert.assertEquals("user_UserListWidget", browser.getAttribute("id"));
	}
	
	@Test
	//Verify that clicking NEW USER button will open wizard menu
	public void PORTAL_USERS_004(){
		//wait for element to load
		//sleep(8);
		driver.findElement(By.className("new")).click();
	}
	
	@Test
	//Verify that "Basic" form fields and "Module Rights" are loaded
	public void PORTAL_USERS_005(){ 
		//sleep(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_w_email")));
		WebElement email = driver.findElement(By.id("user_w_email"));
		Assert.assertEquals("user_w_email", email.getAttribute("name"));
		WebElement firstname = driver.findElement(By.id("user_w_first_name"));
		Assert.assertEquals("user_w_first_name", firstname.getAttribute("name"));
		WebElement lastname = driver.findElement(By.id("user_w_last_name"));
		Assert.assertEquals("user_w_last_name", lastname.getAttribute("name"));
		WebElement mgmtname = driver.findElement(By.id("user_w_mgmtname"));
		Assert.assertEquals("user_w_mgmtname", mgmtname.getAttribute("name"));
		WebElement active = driver.findElement(By.id("user_w_active"));
		Assert.assertEquals("1", active.getAttribute("value"));
				
	}
	
	@Test
	//Verify that User Admin is able to cancel wizard
	public void PORTAL_USERS_006(){
		//sleep(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_w_email")));
		driver.findElement(By.xpath("//input[@id='user_w_email']")).sendKeys(usr_email);
		driver.findElement(By.xpath("//input[@id='user_w_first_name']")).sendKeys(usr_frst_name);
		driver.findElement(By.xpath("//input[@id='user_w_last_name']")).sendKeys(usr_lst_name);
		driver.findElement(By.xpath("//input[@id='user_w_mgmtname']")).sendKeys(usr_mgmtname);
		driver.findElement(By.className("cancel")).click();
		
				
	}
	
	@Test
	//Verify that User Admin is able to register new user
	public void PORTAL_USERS_007(){
		//wait for element to load
		//sleep(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("new")));
		WebElement next = driver.findElement(By.className("new"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", next);
		driver.findElement(By.className("new")).click();
		//Wait for form to render
		//sleep(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_w_email")));
		driver.findElement(By.xpath("//input[@id='user_w_email']")).sendKeys(usr_email);
		driver.findElement(By.xpath("//input[@id='user_w_first_name']")).sendKeys(usr_frst_name);
		driver.findElement(By.xpath("//input[@id='user_w_last_name']")).sendKeys(usr_lst_name);
		driver.findElement(By.xpath("//input[@id='user_w_mgmtname']")).sendKeys(usr_mgmtname);
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='1']")).click(); 
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='13']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='7']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='90']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='65']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='66']")).click();
		//driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='8']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='64']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='21']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='51']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='41']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='31']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='10']")).click();
		//driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='11']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='67']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='62']")).click();
		driver.findElement(By.xpath("//input[@name='user_w_roles' and @value='9']")).click();
		driver.findElement(By.className("next")).click();
		
	}


	@Test
	//Verify that user is able to Filter Results in Users table
	public void PORTAL_USERS_008(){
		sleep(5);
		driver.findElement(By.xpath("//input[@class='on searchInput' and @aria-controls='user_users']")).sendKeys(usr_mgmtname);
        //wait for element to load
		//sleep(5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='user_users' and contains(.,'" + usr_email + "')]")));
		WebElement user = driver.findElement(By.xpath("//table[@id='user_users']/tbody/tr/td[@class=' sorting_1']"));
		Assert.assertEquals(usr_email, user.getText());
		//Get rel ID
		String rel = driver.findElement(By.xpath("//tr[contains(.,'" + usr_mgmtname + "')]")).getAttribute("rel");
        portal.USERS.rel_usr = rel;				
		
	}
	
	@Test
	//Verify that user is able to expand search result using expand-arrow icon
	public void PORTAL_USERS_009(){
		//wait for element to load
		//sleep(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='user_users']/tbody/tr/td/button")));
		driver.findElement(By.xpath("//table[@id='user_users']/tbody/tr/td/button")).click();
						
	}
	
	@Test
	//Verify that child widgets and tables are rendered property
	public void PORTAL_USERS_010(){
		//wait for element to load
		//sleep(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='widget']/h2")));
		//Assert Module Roles header name
		WebElement module_roles = driver.findElement(By.xpath("//div[@class='widget' and contains(.,'Module Roles')]/h2"));
		Assert.assertEquals("Module Roles", module_roles.getText());
		//Assert all associated user roles are present
		WebElement module_rights = driver.findElement(By.xpath("//div[@class='widget' and contains(.,'Module Roles')]/div[@class='roles']"));
		Assert.assertEquals("Admin, Apim Environment Owner, Api User, AutoSP, Backlog, Backlog Admin, Billing, M4, MFIS, MFIS Admin, MFIS Consumer, MFIS Owner, OAuth Admin, Portal, SOC, UX Reviewer", module_rights.getText());
		//Assert User Properties header name
		driver.findElement(By.xpath("//div[contains(.,'User Properties')]"));
		//Assert that NEW PROPERTY button is rendered
		WebElement new_property = driver.findElement(By.xpath("//button[@class='new primary' and contains(.,'New Property')]"));
		Assert.assertEquals("New Property", new_property.getText());
		//Assert Object Rights header name
		driver.findElement(By.xpath("//div[contains(.,'Object Rights')]"));
		//Assert that NEW OBJECT ACCESS button is rendered
		WebElement new_object = driver.findElement(By.xpath("//button[@class='new primary' and contains(.,'New Object Access')]"));
		Assert.assertEquals("New Object Access", new_object.getText());
						
	}
	

	@Test
	//Verify that user is able to create new NEW PROPERTY
	public void PORTAL_USERS_011(){
		//wait for element to load
		//sleep(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='new primary' and contains(.,'New Property')]")));
		driver.findElement(By.xpath("//button[@class='new primary' and contains(.,'New Property')]")).click();
		//Get rel ID
		String rel = driver.findElement(By.xpath("//tr[@class='odd editMode']")).getAttribute("rel");
        portal.USERS.rel_usr_prprty = rel;
		//Assert that proper tables are loaded
		driver.findElement(By.xpath("//tr[@role='row']/th")).toString().contentEquals("Name");
		driver.findElement(By.xpath("//tr[@role='row']/th[2]")).toString().contentEquals("Value");
		driver.findElement(By.xpath("//tr[@role='row']/th[3]")).toString().contentEquals("Module");
		driver.findElement(By.xpath("//tr[@role='row']/th[4]")).toString().contentEquals("Active");
		driver.findElement(By.xpath("//tr[@role='row']/th[5]")).toString().contentEquals("Actions");
		//Enter form field data
		driver.findElement(By.xpath("//tr[@class='odd editMode']/td/input")).sendKeys("browser");
		driver.findElement(By.xpath("//tr[@class='odd editMode']/td[2]/input")).sendKeys("Windows Firefox 17.0");
		driver.findElement(By.xpath("//tr[@class='odd editMode']/td[3]/input")).sendKeys("user");
		WebElement active = driver.findElement(By.xpath("//tr[@class='odd editMode']/td[4]/input[@class='write']"));
		jsLib.callEmbeddedSelenium(driver, "triggerMouseEventAt", active,"click", "0,0");
		WebElement save = driver.findElement(By.xpath("//a[@class='save ss_sprite ss_disk write']"));
		jsLib.callEmbeddedSelenium(driver,"triggerMouseEventAt", save,"click", "0,0");
						
	}
		
	@Test
	//Verify that user is able to edit existing user property
	public void PORTAL_USERS_012(){
		//wait for element to load
		sleep(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@rel='" + rel_usr_prprty + "']/td[5]/a[@class='edit ss_sprite ss_pencil']")));
		WebElement active = driver.findElement(By.xpath("//tr[@rel='" + rel_usr_prprty + "']/td[5]/a[@class='edit ss_sprite ss_pencil']"));
		jsLib.callEmbeddedSelenium(driver, "triggerMouseEventAt", active,"click", "0,0");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@rel='" + rel_usr_prprty + "']/td[2]/input")));
		sleep(5);
		driver.findElement(By.xpath("//tr[@rel='" + rel_usr_prprty + "']/td[2]/input")).sendKeys("EDIT");
		WebElement save = driver.findElement(By.xpath("//div[@class='editBtns']/a[@class='save ss_sprite ss_disk write']"));
		jsLib.callEmbeddedSelenium(driver,"triggerMouseEventAt", save,"click", "0,0");
						
	}
	
	@Test
	//Verify that user is able to cancel the delete function in user properties
	public void PORTAL_USERS_013(){
		//wait for element to load
		sleep(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@rel='" + rel_usr_prprty + "']/td[5]/a[@class='delete ss_sprite ss_delete']")));
		WebElement save = driver.findElement(By.xpath("//tr[@rel='" + rel_usr_prprty + "']/td[5]/a[@class='delete ss_sprite ss_delete']"));
		jsLib.callEmbeddedSelenium(driver,"triggerMouseEventAt", save,"click", "0,0");
		sleep(2);
		driver.findElement(By.className("btns")).findElement(By.tagName("button")).click();
		sleep(2);
				
	}
	
	@Test
	//Verify that user is able to cancel the delete function by pressing 'X' UI icon
	public void PORTAL_USERS_014(){
		//wait for element to load
		//sleep(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@rel='" + rel_usr_prprty + "']/td[5]/a[@class='delete ss_sprite ss_delete']")));
		WebElement save = driver.findElement(By.xpath("//tr[@rel='" + rel_usr_prprty + "']/td[5]/a[@class='delete ss_sprite ss_delete']"));
		jsLib.callEmbeddedSelenium(driver,"triggerMouseEventAt", save,"click", "0,0");
		sleep(2);
		driver.findElement(By.xpath("//a[@aria-label='Close tooltip']")).click();
		sleep(2);
				
	}
	
	@Test
	//Verify that user is able to cancel user property configuration
	public void PORTAL_USERS_015(){
		//wait for element to load
		//sleep(8);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='new primary' and contains(.,'New Property')]")));
		driver.findElement(By.xpath("//button[@class='new primary' and contains(.,'New Property')]")).click();
		//Enter form field data
		driver.findElement(By.xpath("//tr[@class='odd editMode']/td/input")).sendKeys("browser");
		driver.findElement(By.xpath("//tr[@class='odd editMode']/td[2]/input")).sendKeys("Windows Firefox 17.0");
		driver.findElement(By.xpath("//tr[@class='odd editMode']/td[3]/input")).sendKeys("user");
		WebElement active = driver.findElement(By.xpath("//tr[@class='odd editMode']/td[4]/input[@class='write']"));
		jsLib.callEmbeddedSelenium(driver, "triggerMouseEventAt", active,"click", "0,0");
		sleep(2);
		WebElement save = driver.findElement(By.xpath("//a[@class='cancel ss_sprite ss_cancel write']"));
		jsLib.callEmbeddedSelenium(driver,"triggerMouseEventAt", save,"click", "0,0");
						
	}
	
	@Test
	//Verify deleting user
	public void PORTAL_USERS_016(){
		//wait for element to load
		System.out.println(rel_usr);
		sleep(2);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@rel='" + rel_usr + "']/td[@class='actions ']/a[@class='destroy ss_sprite ss_delete']")));
		WebElement destroy = driver.findElement(By.xpath("//tr[@rel='" + rel_usr + "']/td[8]/a[@class='destroy ss_sprite ss_delete']"));
		jsLib.callEmbeddedSelenium(driver, "triggerMouseEventAt", destroy,"click", "0,0");
		//abort deleting user
		sleep(2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dialog']/div[@class='btns']/button[1]")));
		driver.findElement(By.xpath("//div[@class='dialog']/div[@class='btns']/button[1]")).click();
		sleep(2);
		//delete user
		jsLib.callEmbeddedSelenium(driver, "triggerMouseEventAt", destroy,"click", "0,0");
		driver.findElement(By.xpath("//div[@class='dialog']/div[@class='btns']/button[2]")).click();
		sleep(2);
						
	}
	
	
}	
