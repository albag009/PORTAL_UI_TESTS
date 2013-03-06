package portal;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HybridCreateAPI {
  private static WebDriver driver;
  private String baseUrl="https://qa.service.corp.disney.com/";
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String EmailValue = "sergey.x.khristoforov.-nd@disney.com";
  private String OriginBaseName;

  @BeforeClass
  public static void setUp() {
      driver = new FirefoxDriver();
      driver.manage().window().maximize();
      
  }

/*  @AfterClass
  public static void tearDown() {
      driver.close();
      driver.quit();
  }*/
  
  private void sleep(int seconds) {

      try {
          TimeUnit.SECONDS.sleep(seconds);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      
  }
  
  JavascriptLibrary jsLib = new JavascriptLibrary();
  
  WebDriverWait wait = new WebDriverWait(driver, 10);

  @Test
//Verify that user with valid permissions is able to access APIM module
  public void APIM_CreateAPI_001() throws Exception {
    // LogIn
    driver.get(baseUrl + "/welcome/login#myapis");
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login_username")));
    driver.findElement(By.name("login_username")).clear();
    driver.findElement(By.name("login_username")).sendKeys("KHRIS001");
    driver.findElement(By.id("login_password")).clear();
    driver.findElement(By.id("login_password")).sendKeys("P@ssword1");
    driver.findElement(By.id("login_submit")).click();
    driver.findElement(By.id("news_show")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 30) fail("timeout");
    	try { if ("APIS".equals(driver.findElement(By.linkText("APIS")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    // Warning: waitForTextPresent may require manual changes
    for (int second = 0;; second++) {
    	if (second >= 30) fail("timeout");
    	try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*News & Announcements[\\s\\S]*$")) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    driver.findElement(By.cssSelector("h2.header")).click();

    // Warning: waitForTextPresent may require manual changes
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*My Clients[\\s\\S]*$")) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
       
    }
  
    
  @Test
//Verify that by selecting "Register New API" NewAPI wizards starts
  public void APIM_CreateAPI_002() throws Exception {

    driver.findElement(By.cssSelector("button.new.primary")).click();
    // Veifying all fields and text to be shown in proper format
    assertEquals("API Details", driver.findElement(By.xpath("//div[@id='wizard']/div/div")).getText());
    assertEquals("Basics", driver.findElement(By.cssSelector("span > h3")).getText());
    assertEquals("API Tag:", driver.findElement(By.cssSelector("label.required")).getText());
    assertEquals("", driver.findElement(By.id("apiTag")).getText());
    assertEquals("Letters and numbers only.", driver.findElement(By.cssSelector("small.helpText")).getText());
    assertEquals("Version:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/fieldset/ol/li[2]/label")).getText());
    assertTrue(isElementPresent(By.id("version")));
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Description:[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.id("description")));
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Documentation URI:[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Location of this API's documentation\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertEquals("API Owners", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/fieldset[2]/legend/span/h3")).getText());
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*API owners have full control over the API. You can specify individual users or groups\\.[\\s\\S][\\s\\S]*$"));
    assertEquals("API Owner Users:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/fieldset[2]/ol/li/label")).getText());
    assertTrue(isElementPresent(By.cssSelector("input.inputAdd")));
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Enter user emails individually\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.linkText("Add")));
    assertTrue(isElementPresent(By.cssSelector("td.item")));
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*sergey\\.x\\.khristoforov\\.-nd@disney\\.com[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.linkText("Remove")));
    assertEquals("Release Manager Users:", driver.findElement(By.cssSelector("li.releaseManagers > label")).getText());
    //assertTrue(isElementPresent(By.cssSelector("div.apiReleaseManagers > div > div.addBox > input.inputAdd")));
    assertTrue(isElementPresent(By.xpath("(//a[contains(text(),'Add')])[2]")));
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Enter user emails individually\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertEquals("Taxonomy ID:", driver.findElement(By.cssSelector("li.taxonomyli > label.required")).getText());
    assertTrue(isElementPresent(By.name("taxonomy")));
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*- or choose -[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertEquals("Business Unit:", driver.findElement(By.xpath("//div[@class='SelectTaxonomy']/ul/li[contains(.,'Business Unit:')]/label")).getText());
    assertTrue(isElementPresent(By.id("business_unit")));
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*<Select a Business Unit>ABC DaytimeABC FamilyABC Late NightABC NewsABC OTVABC TVN SegmentABC\\.comCNG SegmentCorporate ITDAD TVDAETDCAT-Shared ServicesDCAT-Technical OperationsDCP LicensingDCP SegmentDisney Interactive CorporateDisney Interactive GamesDisney Interactive MediaDisney Interactive SegmentDisney Interactive WorldsDisney Mobile GamesDisney Movie ClubDisney Publishing WorldwideDistribution Technology Group EngineeringDSIDTSS Engineering ServicesDTSS SEA-ITDTSS SEA-OtherDTSS SEA-Technical OperationsDTSS SEA-Third Party ServicesDTSS SegmentESPN Fantasy GamesESPN InternationalESPN LocalESPN MarketingESPN Mobile PropertiesESPN SegmentESPN\\.comGBTSHome EntertainmentJapan MobileMarketing Technology GroupMarvelMedia Other SegmentNGEPixarPlaydomSoapnetStudio MusicStudio SegmentTWDC CorporateWatch ESPNWDI International TechnologyWDPRWDPROWDSMPM[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertEquals("Affiliate:", driver.findElement(By.xpath("//div[@class='SelectTaxonomy']/ul/li[contains(.,'Affiliate:')]/label")).getText());
    assertTrue(isElementPresent(By.id("affiliate")));
    assertEquals("Sub affiliate:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/fieldset[2]/div/ul/li[5]/label")).getText());
    assertTrue(isElementPresent(By.id("sub_affiliate")));
    assertEquals("Origin Info", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/fieldset[3]/legend/span/h3")).getText());
    assertEquals("Origin Base Names:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/fieldset[3]/ol/li/label")).getText());
    assertTrue(isElementPresent(By.cssSelector("div.OriginBaseNamesListEditor > div > div.addBox > input.inputAdd")));
    assertTrue(isElementPresent(By.xpath("(//a[contains(text(),'Add')])[3]")));
    assertEquals("Access", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/fieldset[4]/legend/span/h3")).getText());
    assertEquals("Status:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/fieldset[4]/ol/li/label")).getText());
    assertTrue(isElementPresent(By.id("status")));
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Enabled Disabled[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Is your API enabled[\\s\\S] Who may discover it[\\s\\S][\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertEquals("Visibility:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/fieldset[4]/ol/li[2]/label")).getText());
    assertTrue(isElementPresent(By.id("visibility")));
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*- Select - Private Public for Same BU Public[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Visibility defines who may see this API and form contracts with it\\.[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertEquals("Advanced Options", driver.findElement(By.cssSelector("h3.myapis_closableLabel")).getText());
  }
  
  @Test
//Verify that new API is created by providing all required data
  public void APIM_CreateAPI_003() throws Exception {
    // Entering the required
    driver.findElement(By.id("apiTag")).clear();
    driver.findElement(By.id("apiTag")).sendKeys("TESTAUTO");
    driver.findElement(By.id("version")).clear();
    driver.findElement(By.id("version")).sendKeys("1");
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("For automation purposes");
    driver.findElement(By.id("documentationUrl")).clear();
    driver.findElement(By.id("documentationUrl")).sendKeys("www.disney.go.com/docs");
    assertEquals("sergey.x.khristoforov.-nd@disney.com", driver.findElement(By.cssSelector("span.item")).getText());
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | css=table.myapis_listTable.0.0 | ]]
    driver.findElement(By.cssSelector("div.apiReleaseManagers > div > div.addBox > input.inputAdd")).clear();
    driver.findElement(By.cssSelector("div.apiReleaseManagers > div > div.addBox > input.inputAdd")).sendKeys(EmailValue);
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("".equals(driver.findElement(By.cssSelector("div.apiReleaseManagers > div > div.addBox > input.inputAdd")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("(//a[contains(text(),'Add')])[2]")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (EmailValue.equals(driver.findElement(By.cssSelector("div.apiReleaseManagers > div > table.myapis_listTable > tbody > tr > td > span.item")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*<Select a Business Unit>ABC DaytimeABC FamilyABC Late NightABC NewsABC OTVABC TVN SegmentABC\\.comCNG SegmentCorporate ITDAD TVDAETDCAT-Shared ServicesDCAT-Technical OperationsDCP LicensingDCP SegmentDisney Interactive CorporateDisney Interactive GamesDisney Interactive MediaDisney Interactive SegmentDisney Interactive WorldsDisney Mobile GamesDisney Movie ClubDisney Publishing WorldwideDistribution Technology Group EngineeringDSIDTSS Engineering ServicesDTSS SEA-ITDTSS SEA-OtherDTSS SEA-Technical OperationsDTSS SEA-Third Party ServicesDTSS SegmentESPN Fantasy GamesESPN InternationalESPN LocalESPN MarketingESPN Mobile PropertiesESPN SegmentESPN\\.comGBTSHome EntertainmentJapan MobileMarketing Technology GroupMarvelMedia Other SegmentNGEPixarPlaydomSoapnetStudio MusicStudio SegmentTWDC CorporateWatch ESPNWDI International TechnologyWDPRWDPROWDSMPM[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    new Select(driver.findElement(By.id("business_unit"))).selectByVisibleText("DTSS Engineering Services");
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("(all affiliates)Ads ReportingAPI ManagementBig Data AnalyticsCommerce-Address ValidationCommerce-CSGCommerce-Entitlement ServiceCommerce-Legacy ServicesCommerce-NautilusCommerce-Payment HandlerCommunity Services-UGC-EPContent Management-Professional ServicesContent Management-Web PublishingCustomer Support Services-KanaData Services-Business ReportingData Services-Data Warehouse ServicesData Services-Web ReportingDeprecating ServicesDeveloper ServicesFoundational TechnologiesFoundational Technologies-Ad Images ServiceIdentity-AuthZIdentity-Disney IDIdentity-IP-GEOIdentity-Legacy ServicesIdentity-M4Marketing Apps-Polls Third PartyMarketing Apps-SweepstakesMCON-ContentGateMCON-DeviceLinkMCON-GenieMCON-Walt DeviceDetectionMedia Services Professional ServicesMessaging-Outbound Email and Event mailMessaging-Unified MessagingMessaging-Wireless AlertsPreview Room/Screening Room".equals(driver.findElement(By.id("affiliate")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    new Select(driver.findElement(By.id("affiliate"))).selectByVisibleText("API Management");
    try {
      assertEquals("(all subaffiliates)", driver.findElement(By.id("sub_affiliate")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("1752", driver.findElement(By.name("taxonomy")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("div.originBaseNames > div > div.addBox > input.inputAdd")).clear();
    driver.findElement(By.cssSelector("div.originBaseNames > div > div.addBox > input.inputAdd")).sendKeys("autoTestURI");
    driver.findElement(By.xpath("(//a[contains(text(),'Add')])[3]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | css=div.originBaseNames > div > table.myapis_listTable.0.0 | ]]
    try {
      assertEquals("Remove", driver.findElement(By.xpath("(//a[contains(text(),'Remove')])[3]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Default[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Regression for existing defect
    /*driver.findElement(By.cssSelector("div.originBaseNames > div > div.addBox > input.inputAdd")).clear();
    driver.findElement(By.cssSelector("div.originBaseNames > div > div.addBox > input.inputAdd")).sendKeys("Validation");
    driver.findElement(By.xpath("(//a[contains(text(),'Add')])[3]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | css=div.originBaseNames > div > table.myapis_listTable.0.1 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | css=div.originBaseNames > div > table.myapis_listTable.1.1 | ]]
    driver.findElement(By.xpath("(//a[contains(text(),'Remove')])[4]")).click();
    try {
      assertEquals("Validation", driver.findElement(By.cssSelector("div.originBaseNames > div > div.addBox > input.inputAdd")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("div.originBaseNames > div > div.addBox > input.inputAdd")).clear();
    driver.findElement(By.cssSelector("div.originBaseNames > div > div.addBox > input.inputAdd")).sendKeys("");*/
    // End of Regression
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Enabled Disabled[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("- Select - Private Public for Same BU Public", driver.findElement(By.id("visibility")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    new Select(driver.findElement(By.id("visibility"))).selectByVisibleText("Private");
    driver.findElement(By.cssSelector("button.next")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("".equals(driver.findElement(By.cssSelector("img[alt=\"back\"]")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
  }
  
  @Test
//Verify that new created API is  displayed properly at API Revision wizard 
  public void APIM_CreateAPI_004() throws Exception {

    // Verifying API UI elements
    try {
      assertEquals("DTSS-TESTAUTO-1", driver.findElement(By.cssSelector("div.subNav > h2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Delete Revision", driver.findElement(By.linkText("Delete Revision")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Edit API Details", driver.findElement(By.linkText("Edit API Details")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Deploy Revision", driver.findElement(By.linkText("Deploy Revision")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Copy Revision As New API", driver.findElement(By.linkText("Copy Revision As New API")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Revision: 1", driver.findElement(By.cssSelector("div.myapis_DetailInfo")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("API Version: 1", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div/div/div[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Status: Enabled", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div/div/div[3]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Visibility:", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div/div/div[4]/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Taxonomy Id: 1752", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div/div/div[6]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Documentation URI:", driver.findElement(By.cssSelector("div.clear > div.myapis_DetailInfo > span.label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("www.disney.go.com/docs", driver.findElement(By.linkText("www.disney.go.com/docs")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Last Modified:", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div/div[2]/div/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("API Owners: " + EmailValue, driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div/div[2]/div[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Release Managers: " + EmailValue, driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div/div[2]/div[3]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Origin Base Names: " + OriginBaseName + " Before you are able to deploy your API to an environment, you must specify at least one Origin Base Name. Origin Base Names will be matched with named Origin Base URIs in the environment in which the API is deployed. Upon deployment the environment will be checked to ensure these Origin Base Names are defined before allowing the deployment.", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div/div[3]/div")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Authenticator:", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div/div[3]/div[2]/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Allow Public Tokens:", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div/div[3]/div[3]/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Description:", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div/div[2]/div[2]/div[2]/div[2]/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("For automation purposes", driver.findElement(By.cssSelector("div.infoBox")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Resources", driver.findElement(By.cssSelector("button.active")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Variables", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div[2]/div/button[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Headers", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div[2]/div/button[3]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Security", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div[2]/div/button[4]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("URI Rewriting", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div[2]/div/button[5]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Logging", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div[2]/div/button[6]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Assertions", driver.findElement(By.xpath("//div[@id='myapis_api_revision_details_view_container']/div/div/div[2]/div/button[7]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.id("DataTables_Table_3_wrapper")));
    try {
      assertEquals("API Resources", driver.findElement(By.cssSelector("#DataTables_Table_3_filter > h2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Add Resource", driver.findElement(By.cssSelector("#DataTables_Table_3_filter > button.new.primary")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Edit Resource Ordering", driver.findElement(By.cssSelector("button.ordering")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("", driver.findElement(By.cssSelector("#DataTables_Table_3_filter > input.on.searchInput")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Resource\nAccess\nAction", driver.findElement(By.cssSelector("#DataTables_Table_3 > thead > tr > th.cog.sorting")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Resource", driver.findElement(By.xpath("//table[@id='DataTables_Table_3']/thead/tr/th[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Access", driver.findElement(By.xpath("//table[@id='DataTables_Table_3']/thead/tr/th[3]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Action", driver.findElement(By.xpath("//table[@id='DataTables_Table_3']/thead/tr/th[4]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("No data available in table", driver.findElement(By.cssSelector("#DataTables_Table_3 > tbody > tr.odd > td.dataTables_empty")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Add ResourceEdit Resource OrderingAPI Resources\n Resource\nAccess\nAction\n\n ResourceAccessAction No data available in tableShowing 0 to 0 of 0 entries\nFirstPreviousNextLast[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
//Verify that a Resource is created and added to the new created API 
  public void APIM_CreateAPI_005() throws Exception {
    // Adding Resources
    driver.findElement(By.cssSelector("#DataTables_Table_3_filter > button.new.primary")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Resource Overview".equals(driver.findElement(By.xpath("//div[@id='wizard']/div/div")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    try {
      assertEquals("Resource Details", driver.findElement(By.cssSelector("h3.myapis_closableLabel")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Resource Name:", driver.findElement(By.cssSelector("label.required")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.id("resource_name")));
    driver.findElement(By.id("resource_name")).clear();
    driver.findElement(By.id("resource_name")).sendKeys("autoUsers");
    try {
      assertEquals("example: users", driver.findElement(By.cssSelector("small.helpText")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Description:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/ol/li[2]/label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.id("description")));
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("For automation purposes only - to get users list");
    try {
      assertEquals("enter brief description", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/ol/li[2]/div/div/small")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("External Resource Path:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/ol/li[3]/label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.id("resource_path")));
    driver.findElement(By.id("resource_path")).clear();
    driver.findElement(By.id("resource_path")).sendKeys("/users");
    String ResourcePath = driver.findElement(By.id("resource_path")).getAttribute("value");
    try {
      assertEquals("example: /users. (NOTE: you must start with a slash '/')\nView available system variables", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/ol/li[3]/div/div/small")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("View available system variables", driver.findElement(By.linkText("View available system variables")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Visibility:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/ol/li[4]/label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("- Select - Private Public for Same BU Public", driver.findElement(By.name("resourceVisibility")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    new Select(driver.findElement(By.name("resourceVisibility"))).selectByVisibleText("Private");
    try {
      assertEquals("Private - only owners of the API will be able to see this resource or create contracts between a client and this resource.\n\n Public - any client owner may create contracts between their client and this resource\n\n Public for Same BU - only client owners who are in the same business unit may create contracts with this resource.", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/ol/li[4]/div/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Status:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/ol/li[5]/label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Enabled Disabled", driver.findElement(By.name("status")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Advanced Resource Options", driver.findElement(By.cssSelector("h4.myapis_closableLabel")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
 // Regression of existing defect
    /*driver.findElement(By.cssSelector("h4.myapis_closableLabel")).click();
    try {
      assertEquals("Customize Origin Resource Path:", driver.findElement(By.cssSelector("ol.myapis_closableContent > li > label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.name("override_origin_path")));
    try {
      assertEquals("Customize", driver.findElement(By.cssSelector("ol.myapis_closableContent > li > div > span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
        driver.findElement(By.name("override_origin_path")).click();
    try {
      assertEquals(ResourcePath, driver.findElement(By.id("orig_resource_path")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("example: /users. This is typically the same as the External Resource Path. \nView available system variables", driver.findElement(By.cssSelector("ol.myapis_closableContent > li > div > div > small.helpText")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("View available system variables", driver.findElement(By.cssSelector("a.sysResVars")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.name("override_origin_path")).click();
    try {
      assertEquals("Override Full Origin URI:", driver.findElement(By.cssSelector("li.overrideOrigin > label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.name("override_resource_origin")));
    try {
      assertEquals("Override", driver.findElement(By.cssSelector("li.overrideOrigin > div > span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Specify Origin Resource Base:", driver.findElement(By.cssSelector("li.overrideOrigin > div > div")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(driver.findElement(By.cssSelector("li.allowPublicAccess > label")).getText().matches("^exact:Allow Public Tokens[\\s\\S]$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Inherit From API Allow Do Not Allow", driver.findElement(By.name("allowPublicAccess")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Whether or not this resource allows access from a client with an AuthZ public/insecure token. This setting can further be customized at the method level. Inherit From API - defer to the setting at the API level.\n\n Allow - clients presenting a token obtained using AuthZ's public assertion type will be allowed access.\n\n Do Not Allow - clients presenting a token obtained using AuthZ's public assertion type will NOT be allowed access.", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/form/section/ol/li[7]/ol/li[3]/div/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Whether or not this resource allows access from a client with an AuthZ public/insecure token.", driver.findElement(By.cssSelector("li.allowPublicAccess > div > div > small.helpText")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("h4.myapis_closableLabel")).click();
    // End of Regressio*/
  @Test
//Verify that a GET Method is created and added to the new Resource 
  public void APIM_CreateAPI_006() throws Exception {
    driver.findElement(By.cssSelector("h3.myapis_closableLabel.resourceMethods")).click();
    try {
      assertEquals("- Select -DELETEGETHEADOPTIONSPOSTPUT", driver.findElement(By.name("availMethods")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.name("add_method")));
    new Select(driver.findElement(By.name("availMethods"))).selectByVisibleText("GET");
    driver.findElement(By.name("add_method")).click();
    driver.findElement(By.cssSelector("div.myapis_closableLabel.methodLabel")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Method: GET".equals(driver.findElement(By.cssSelector("div.myapis_closableLabel.methodLabel")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    try {
      assertEquals("delete", driver.findElement(By.linkText("delete")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Visibility:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[2]/label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("- Select - Private Public for Same BU Public", driver.findElement(By.name("visibility")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    new Select(driver.findElement(By.name("visibility"))).selectByVisibleText("Private");
    try {
      assertEquals("Private - only owners of the API will be able to see this method or create contracts between a client and this method.\n\n Public - any client owner may create contracts between their client and this method\n\n Public for Same BU - only client owners who are in the same business unit may create contracts with this method.", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[2]/div/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Visibility defines who may see this method and form contracts with it.", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[2]/div/div/small")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Status:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[3]/label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Enabled Disabled", driver.findElement(By.xpath("(//select[@name='status'])[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("External Query String Template:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[4]/label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.name("externalUriTemplate")));
    try {
      assertTrue(driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[4]/div/div/small")).getText().matches("^Template: e\\.g\\. \\{[\\s\\S]limit\\}\\. Relative to resource external URI\\. \nView available system variables$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("View available system variables", driver.findElement(By.xpath("(//a[contains(text(),'View available system variables')])[3]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  @Test
//Verify that a POST Method is created and added to the new Resource 
  public void APIM_CreateAPI_007() throws Exception {
    new Select(driver.findElement(By.name("availMethods"))).selectByVisibleText("POST");
    driver.findElement(By.name("add_method")).click();
    driver.findElement(By.cssSelector("div.myapis_closableLabel.methodLabel")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Method: POST".equals(driver.findElement(By.cssSelector("div.myapis_closableLabel.methodLabel")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    try {
      assertEquals("delete", driver.findElement(By.linkText("delete")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Visibility:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[2]/label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("- Select - Private Public for Same BU Public", driver.findElement(By.name("visibility")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    new Select(driver.findElement(By.name("visibility"))).selectByVisibleText("Private");
    try {
      assertEquals("Private - only owners of the API will be able to see this method or create contracts between a client and this method.\n\n Public - any client owner may create contracts between their client and this method\n\n Public for Same BU - only client owners who are in the same business unit may create contracts with this method.", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[2]/div/span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Visibility defines who may see this method and form contracts with it.", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[2]/div/div/small")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Status:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[3]/label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Enabled Disabled", driver.findElement(By.xpath("(//select[@name='status'])[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("External Query String Template:", driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[4]/label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertTrue(isElementPresent(By.name("externalUriTemplate")));
    try {
      assertTrue(driver.findElement(By.xpath("//div[@id='wizard']/div[2]/div/div/div[3]/section/ol/li/section/div/fieldset/ol/li[4]/div/div/small")).getText().matches("^Template: e\\.g\\. \\{[\\s\\S]limit\\}\\. Relative to resource external URI\\. \nView available system variables$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("button.next")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("".equals(driver.findElement(By.cssSelector("img[alt=\"back\"]")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*autoUsers[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Private[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("edit", driver.findElement(By.linkText("edit")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("copy", driver.findElement(By.linkText("copy")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("delete", driver.findElement(By.linkText("delete")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//table[@id='DataTables_Table_3']/tbody/tr/td/button")).click();
    try {
      assertEquals("Description:", driver.findElement(By.cssSelector("td.info > div > div > span.label")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Status:[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Allow Public Tokens:[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Methods[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Method\nAccess\nOrigin Base Name\nOrigin Path\nExternal Path", driver.findElement(By.cssSelector("#DataTables_Table_8 > thead > tr > th.cog.sorting")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("", driver.findElement(By.cssSelector("#DataTables_Table_8 > tbody > tr.odd > td.expand.")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("GET", driver.findElement(By.xpath("//table[@id='DataTables_Table_8']/tbody/tr/td[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Private", driver.findElement(By.xpath("//table[@id='DataTables_Table_8']/tbody/tr/td[3]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | id=DataTables_Table_8.1.3 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | id=DataTables_Table_8.1.4 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | id=DataTables_Table_8.1.5 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | id=DataTables_Table_8.2.1 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | id=DataTables_Table_8.2.2 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | id=DataTables_Table_8.2.3 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | id=DataTables_Table_8.2.4 | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getTable | id=DataTables_Table_8.2.5 | ]]
    driver.findElement(By.xpath("//table[@id='DataTables_Table_8']/tbody/tr/td/button")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Allow Public Tokens:[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Variables", driver.findElement(By.cssSelector("h4")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Active Assertions on this Method[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//table[@id='DataTables_Table_8']/tbody/tr[3]/td/button")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Allow Public Tokens:[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Variables", driver.findElement(By.xpath("//table[@id='DataTables_Table_8']/tbody/tr[4]/td/div/div[2]/h4")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Active Assertions on this Method[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Variables", driver.findElement(By.cssSelector("td.info > div > div.widget > h2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("img[alt=\"back\"]")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Welcome to API Management".equals(driver.findElement(By.cssSelector("h2")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

  }

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
