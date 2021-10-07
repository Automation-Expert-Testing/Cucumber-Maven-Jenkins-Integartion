package generic;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.support.PageFactory;
import utill.Common;
import webPages.AmazonHomePage;

import java.io.IOException;
import java.util.Properties;

public  class StarterClass extends Common {
    public static AmazonHomePage amazonHomePage;
    private final String PropertiesFilePath ="config.properties";

    private static Properties prop;
    {
        try {
            prop = loadProperties(PropertiesFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String testingEnvironment= prop.getProperty("TestingEnvironment");
    private Boolean useCloudEnv= Boolean.parseBoolean(prop.getProperty("UseCloudEnv")) ;
    private String cloudEnvName= prop.getProperty("CloudEnvName");
    private String os= prop.getProperty("Os");
    private String os_version = prop.getProperty("Os_version");
    private String browserName = prop.getProperty("BrowserName");
    private String browserVersion = prop.getProperty("BrowserVersion");
    private long implicitlyWaitTime=Long.parseLong(prop.getProperty("ImplicitlyWaitTime").trim());

    private final String secretFilePath =prop.getProperty("SecretFilePath");
    private static Properties secretProp;
    {
        try {
            secretProp = loadProperties(secretFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    String url = secretProp.getProperty(testingEnvironment);
    public static void Init() {
        amazonHomePage = PageFactory.initElements(driver,AmazonHomePage.class);
    }

    @Before
    public void setUp_Init() throws IOException {
        setUp( useCloudEnv,  cloudEnvName,
                os,  os_version,  browserName,
                browserVersion, url,implicitlyWaitTime);
        Init();
    }
    @After
    public void tearDown(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            try{
            System.out.println(scenario.getName()+" is Failed");
            screenShot(scenario);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            try{
            System.out.println(scenario.getName()+" is Passed");
            }catch (Exception E){
                E.printStackTrace();
            }
        }
        driver.quit();

    }
}