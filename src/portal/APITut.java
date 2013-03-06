package portal;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class APITut {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://qa.service.corp.disney.com/";
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
  }

  @Test
  public void testAPITut() throws Exception {
    driver.get(baseUrl + "/welcome/login#myapis");
    driver.findElement(By.name("login_username")).clear();
    driver.findElement(By.name("login_username")).sendKeys("KHRIS001");
    driver.findElement(By.id("login_password")).clear();
    driver.findElement(By.id("login_password")).sendKeys("P@ssword1");
    driver.findElement(By.id("login_submit")).click();
    driver.findElement(By.id("news_show")).click();
    driver.findElement(By.cssSelector("h2.header")).click();
    // Warning: waitForTextPresent may require manual changes
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*APIM Tutorials[\\s\\S]*$")) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.linkText("Configuring an API")).click();
    assertTrue(isElementPresent(By.id("apiTag")));
    assertTrue(isElementPresent(By.id("version")));
    assertTrue(isElementPresent(By.id("description")));
    assertTrue(isElementPresent(By.id("documentationUrl")));
    assertEquals("API Owner Users:", driver.findElement(By.xpath("//label[@for='APIOwners']")).getText());
    //assertTrue(isElementPresent(By.cssSelector("div.apiReleaseManagers > div > div.addBox > input.inputAdd")));
    assertTrue(isElementPresent(By.name("taxonomy")));
    assertTrue(isElementPresent(By.id("business_unit")));
    //assertTrue(isElementPresent(By.id("affiliate")));
    assertTrue(isElementPresent(By.id("sub_affiliate")));
    //assertTrue(isElementPresent(By.cssSelector("div.originBaseNames > div > div.addBox > input.inputAdd")));
    assertTrue(isElementPresent(By.id("status")));
    assertTrue(isElementPresent(By.id("visibility")));
    driver.findElement(By.cssSelector("button.tutlStart.myapis_tutorialCtrlsBtn")).click();
    assertTrue(isElementPresent(By.cssSelector("span.suggVlu")));
    driver.findElement(By.linkText("use this value")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("div.tutlBbl.tutlBbl-0 > div > button.myapis_tutorialCtrlsBtn")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*1[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//a[contains(text(),'use this value')])[2]")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    assertTrue(isElementPresent(By.cssSelector("span.suggVlu")));
    driver.findElement(By.xpath("(//a[contains(text(),'use this value')])[3]")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
    driver.findElement(By.id("documentationUrl")).clear();
    driver.findElement(By.id("documentationUrl")).sendKeys("www.disney.go.com/docs");
    driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
    try {
      assertEquals("www.disney.go.com/docs", driver.findElement(By.id("documentationUrl")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    //assertTrue(isElementPresent(By.cssSelector("span.item")));
    driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
    assertTrue(isElementPresent(By.cssSelector("span.suggVlu")));
    driver.findElement(By.cssSelector("div.apiReleaseManagers > div > div.addBox > input.inputAdd")).clear();
    driver.findElement(By.cssSelector("div.apiReleaseManagers > div > div.addBox > input.inputAdd")).sendKeys("sergey.x.khristoforov.-nd@disney.com");
    driver.findElement(By.xpath("(//a[contains(text(),'Add')])[2]")).click();
    assertTrue(isElementPresent(By.cssSelector("div.apiReleaseManagers > div > table.myapis_listTable > tbody > tr > td")));
    driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*We record the taxonomy information for your API to allow you to require a client \\(API consumer\\) be part of your same BU before they can access your API\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    new Select(driver.findElement(By.id("business_unit"))).selectByVisibleText("DTSS Engineering Services");
    new Select(driver.findElement(By.id("affiliate"))).selectByVisibleText("API Management");
    try {
      assertEquals("1752", driver.findElement(By.name("taxonomy")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*\\(all subaffiliates\\)[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[13]")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*In this case, we only need one Origin URI: DefaultURI\\. In our environment, DefaultURI will point to http://vn7adapiapp01:8082/origin-server/data/rest/v1\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("div.originBaseNames > div > div.addBox > input.inputAdd")).clear();
    driver.findElement(By.cssSelector("div.originBaseNames > div > div.addBox > input.inputAdd")).sendKeys("DefaultURI");
    driver.findElement(By.xpath("(//a[contains(text(),'Add')])[3]")).click();
    assertEquals("DefaultURI", driver.findElement(By.cssSelector("div.originBaseNames > div > table.myapis_listTable > tbody > tr > td > span.item")).getText());
    driver.findElement(By.xpath("(//button[@type='button'])[15]")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*The status of your API defines whether or not your API is enabled\\. In a disabled state, no traffic will be allowed to your API\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[17]")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*PRIVATE[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//a[contains(text(),'use this value')])[4]")).click();
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*- Select - Private Public for Same BU Public[\\s\\S]*$"));
    driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*We also have a section for Advanced Options for an API\\. Expand that section and let's continue\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("h3.myapis_closableLabel")).click();
    assertEquals("All", driver.findElement(By.cssSelector("div > label")).getText());
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*AUTHZ[\\s\\S]*$"));
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*DEVICEPAIRING[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*EVASERVER[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*GLOBALREG[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*KEYSTONE[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*MGMTPROD[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[21]")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*With the Authenticator setting, you can require that a client authenticate with AuthZ in a particular way\\. If your API requires a particular method, select it above\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[23]")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*In the API wizards, you'll see an Edit In Place section\\. This allows you to make a configuration change and then immediately deploy it to an environment where the API has already been deployed\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[25]")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*We have now entered all the basic info for our API\\. Click SAVE below to save the API\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("button.next")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("div.tutlBbl.tutlBbl-13"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("(//button[@type='button'])[27]")).click();
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Now that you have saved your API, let's continue on to creating a resource! \n back[\\s\\S]*$"));
    driver.findElement(By.linkText("creating a resource")).click();
    
  }

/*  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }*/

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
}
