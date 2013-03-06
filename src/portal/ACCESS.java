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

public class ACCESS {
	
	//PARAMETERS
    public static String login_username = "ALBAG009";
    public static String login_password = "Pass@word1";
    public static String login_email = "george.x.albantov.-nd@disney.com";
    public static String rel_group;
    public static String rel_group_new;
    public static String usr_email = "test300.user@disney.com";
    public static String usr_frst_name = "Test";
    public static String usr_lst_name = "300";
    public static String usr_mgmtname = "test300.user";

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

/*    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }*/
    
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
	//Verify that user with valid permissions is able to access ACCESS module
	public void PORTAL_ACCESS_001() {
	        driver.get("http://qa.service.corp.disney.com/#access");
	        //wait for element to load
	        //sleep(5);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login_username")));
	        //Enter Login credentials
	        WebElement login_username = driver.findElement(By.name("login_username"));
	        WebElement login_password = driver.findElement(By.name("login_password"));
	        login_username.sendKeys(portal.ACCESS.login_username);
	        login_password.sendKeys(portal.ACCESS.login_password);
	        //Submit login
	        WebElement login_submit = driver.findElement(By.name("login_submit"));
	        login_submit.submit();
	        sleep(10);
	        Assert.assertEquals("URL", "https://qa.service.corp.disney.com/#access", driver.getCurrentUrl());
	        //wait for element to load
	        sleep(8);
	        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header")));
			//driver.findElement(By.className("header")).click();	
				
	}
	
	@Test
	//Verify that Groups widget is rendered properly
	public void PORTAL_ACCESS_002() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@role='row']/th[contains(.,'Name')]")));
		Assert.assertEquals("Name", driver.findElement(By.xpath("//tr[@role='row']/th[contains(.,'Name')]")).getText());
		Assert.assertEquals("Description", driver.findElement(By.xpath("//tr[@role='row']/th[contains(.,'Description')]")).getText());	
		Assert.assertEquals("Owner", driver.findElement(By.xpath("//tr[@role='row']/th[contains(.,'Owner')]")).getText());
		Assert.assertEquals("Status", driver.findElement(By.xpath("//tr[@role='row']/th[contains(.,'Status')]")).getText());
		Assert.assertEquals("Enabled?", driver.findElement(By.xpath("//tr[@role='row']/th[contains(.,'Enabled?')]")).getText());
		Assert.assertEquals("Actions", driver.findElement(By.xpath("//tr[@role='row']/th[contains(.,'Actions')]")).getText());
		Assert.assertEquals("Admin", driver.findElement(By.xpath("//tr[@role='row']/th[contains(.,'Admin')]")).getText());
	}
	
	@Test
	//Verify that user is able to CANCEL create new group
	public void PORTAL_ACCESS_003() {
		driver.findElement(By.xpath("//button[@class='new primary' and contains(.,'New Group')]")).click();
		String rel = driver.findElement(By.xpath("//tr[@class='odd editMode']")).getAttribute("rel");
        portal.ACCESS.rel_group = rel;
        driver.findElement(By.xpath("//input[@rel='name']")).sendKeys("TEST_GROUP");
        driver.findElement(By.xpath("//input[@rel='description']")).sendKeys("TEST_DESCRIPTION");
        Assert.assertEquals(login_email, driver.findElement(By.xpath("//input[@rel='owner']")).getAttribute("value"));
        //Assert green status for owner
        Assert.assertEquals("status_icon status_green", driver.findElement(By.xpath("//tr[@rel='" + rel_group + "']/td[5]/span[contains(.,'Owner')]")).getAttribute("class"));
        //Assert "Enabled" is checked by default
        Assert.assertEquals("true", driver.findElement(By.xpath("//tr[@rel='" + rel_group + "']/td[6]/input[@type='checkbox']")).getAttribute("checked"));
        //cancel
        driver.findElement(By.xpath("//div[@class='editBtns']/a[contains(.,'cancel')]")).click();
        sleep(5);
	}
	
	@Test
	//Verify that user is able to create new group
	public void PORTAL_ACCESS_004() {
		driver.findElement(By.xpath("//button[@class='new primary' and contains(.,'New Group')]")).click();
		String rel = driver.findElement(By.xpath("//tr[@class='odd editMode']")).getAttribute("rel");
        portal.ACCESS.rel_group = rel;
        driver.findElement(By.xpath("//input[@rel='name']")).sendKeys("TEST_GROUP");
        driver.findElement(By.xpath("//input[@rel='description']")).sendKeys("TEST_DESCRIPTION");
        //save
        driver.findElement(By.xpath("//div[@class='editBtns']/a[contains(.,'save')]")).click();
	}
	
	@Test
	//Verify that group owner is able to edit group 
	public void PORTAL_ACCESS_005() {
		sleep(2);
		driver.findElement(By.xpath("//tr[@rel='" + rel_group + "']/td[7]/a[contains(.,'edit')]")).click();
		//assert name input is disabled
		//Assert.assertEquals("", driver.findElement(By.xpath("//input[@rel='name']")).getAttribute("disabled"));
		driver.findElement(By.xpath("//input[@rel='description']")).clear();
		driver.findElement(By.xpath("//input[@rel='description']")).sendKeys("TEST_DESCRIPTION_EDIT");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='editBtns']/a[contains(.,'save')]")));
		driver.findElement(By.xpath("//div[@class='editBtns']/a[contains(.,'save')]")).click();
				
	}
	
	@Test
	//Verify that group owner is able to disable and restore group
	public void PORTAL_ACCESS_006() {
		sleep(2);
		driver.findElement(By.xpath("//tr[@rel='" + rel_group + "']/td[7]/a[contains(.,'disable')]")).click();
		sleep(2);
		driver.findElement(By.xpath("//tr[@rel='" + rel_group + "']/td[7]/a[contains(.,'restore')]")).click();
		
	}
	
	@Test
	//Verify that group owner is able to clone group
	public void PORTAL_ACCESS_007() {
		driver.findElement(By.xpath("//tr[@rel='" + rel_group + "']/td[7]/a[contains(.,'clone')]")).click();
		sleep(1);
		driver.findElement(By.xpath("//a[@title='Close tooltip']")).click();
		driver.findElement(By.xpath("//tr[@rel='" + rel_group + "']/td[7]/a[contains(.,'clone')]")).click();
		sleep(1);
		//WebElement name = driver.findElement(By.xpath("//div[@class='dialog']/form/input[@name='name']"));
		//jsLib.callEmbeddedSelenium(driver, "novalidate.name.value = 'clone';", name);
		driver.findElement(By.xpath("//div[@id='ui-tooltip-PortalDialog']/div[@class='ui-tooltip-content']/div[@class='dialog']/div[@class='content']/div[@class='dialog']/form/input[@name='name']")).sendKeys("CLONE_NAME");
		driver.findElement(By.xpath("//div[@id='ui-tooltip-PortalDialog']/div[@class='ui-tooltip-content']/div[@class='dialog']/div[@class='content']/div[@class='dialog']/form/input[@name='description']")).sendKeys("CLONE_DESCRIPTION");
		driver.findElement(By.xpath("//div[@id='ui-tooltip-PortalDialog']/div[@class='ui-tooltip-content']/div[@class='dialog']/div[@class='btns']/button[contains(.,'cancel')]")).click();
		sleep(2);
		driver.findElement(By.xpath("//tr[@rel='" + rel_group + "']/td[7]/a[contains(.,'clone')]")).click();
		driver.findElement(By.xpath("//div[@id='ui-tooltip-PortalDialog']/div[@class='ui-tooltip-content']/div[@class='dialog']/div[@class='content']/div[@class='dialog']/form/input[@name='name']")).sendKeys("CLONE_NAME");
		driver.findElement(By.xpath("//div[@id='ui-tooltip-PortalDialog']/div[@class='ui-tooltip-content']/div[@class='dialog']/div[@class='content']/div[@class='dialog']/form/input[@name='description']")).sendKeys("CLONE_DESCRIPTION");
		driver.findElement(By.xpath("//div[@id='ui-tooltip-PortalDialog']/div[@class='ui-tooltip-content']/div[@class='dialog']/div[@class='btns']/button[contains(.,'Clone')]")).click();
		sleep(3);

	}
	
	@Test
	//Verify filtering
	public void PORTAL_ACCESS_008() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='access_GroupList_filter']/input[@class='on searchInput']")));
		driver.findElement(By.xpath("//div[@id='access_GroupList_filter']/input[@class='on searchInput']")).sendKeys(login_email);
		sleep(3);
		driver.findElement(By.xpath("//span[contains(.,'TEST_GROUP')]")).click();
		sleep(3);
		
	}

	@Test
	//Verify that group owner is able to add user to a group
	public void PORTAL_ACCESS_009() {
		//driver.findElement(By.xpath("//tr[@rel='" + rel_group_new + "']")).click();
		driver.findElement(By.xpath("//button[@class='new primary' and contains(.,'New Group User')]")).click();
		driver.findElement(By.xpath("//table[@class='access_GroupUserList dataTable']/tbody/tr[@class='even editMode']/td/input")).sendKeys("test350.user@disney.com");
		driver.findElement(By.xpath("//table[@class='access_GroupUserList dataTable']/tbody/tr[@class='even editMode']/td[3]/div[@class='editBtns']/a[contains(.,'save')]")).click();
		sleep(5);
	}
	
	@Test
	//Verify that group owner is able to delete created groups
	public void PORTAL_ACCESS_010() {
		driver.findElement(By.xpath("//tbody[contains(.,'CLONE_NAME')]/tr/td[8]/a[contains(.,'delete')]")).click();
		sleep(1);
		driver.findElement(By.xpath("//div[@class='dialog']/div[@class='btns']/button[contains(.,'delete')]")).click();
		sleep(1);
		driver.findElement(By.xpath("//tbody[contains(.,'TEST_GROUP')]/tr/td[8]/a[contains(.,'delete')]")).click();
		sleep(1);
		driver.findElement(By.xpath("//div[@class='dialog']/div[@class='btns']/button[contains(.,'cancel')]")).click();
		driver.findElement(By.xpath("//tbody[contains(.,'TEST_GROUP')]/tr/td[8]/a[contains(.,'delete')]")).click();
		sleep(1);
		driver.findElement(By.xpath("//div[@class='dialog']/div[@class='btns']/button[contains(.,'delete')]")).click();

	}
	
}	
