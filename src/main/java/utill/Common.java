package utill;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Common {

    public static WebDriver driver;
    private String browserstack_username = "";
    private String browserstack_accesskey = "";
    private String saucelabs_username = "";
    private String saucelabs_accesskey = "";

    public Properties loadProperties(String propertiesFilePath) throws IOException {
        Properties prop = new Properties();
        InputStream ism = new FileInputStream(propertiesFilePath);
        prop.load(ism);
        ism.close();
        return prop;
    }

    public  void setUp(Boolean useCloudEnv, String cloudEnvName,
                             String os, String os_version, String browserName,
                             String browserVersion, String url,long implicitlyWaitTime) throws IOException {

        if (useCloudEnv == true) {
            if (cloudEnvName.equalsIgnoreCase("browserstack")) {
                getCloudDriver(cloudEnvName, browserstack_username, browserstack_accesskey, os, os_version, browserName, browserVersion);
            } else if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
                getCloudDriver(cloudEnvName, saucelabs_username, saucelabs_accesskey, os, os_version, browserName, browserVersion);
            }
        } else {
            getLocalDriver(os,browserName);
        }
        driver.manage().timeouts().implicitlyWait(implicitlyWaitTime, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public WebDriver getLocalDriver(String OS, String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            if (OS.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "webDrivers/mac/chromedriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", "webDrivers/windows/chromedriver.exe");
            }else if (OS.equalsIgnoreCase("Linux")) {
            System.setProperty("webdriver.chrome.driver", "webDrivers/linux/chromedriver_2");
        }
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("chrome-options")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            if (OS.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "webDrivers/mac/chromedriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", "webDrivers/windows/chromedriver.exe");
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if (OS.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.gecko.driver", "webDrivers/mac/geckodriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.gecko.driver", "webDrivers/windows/geckodriver.exe");
            }
            driver = new FirefoxDriver();

        }
        else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "webDrivers/windows/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        else if (browserName.equalsIgnoreCase("Edge")) {
            if (OS.equalsIgnoreCase("mac")) {
        System.setProperty("webdriver.edge.driver", "webDrivers/mac/msedgedriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.edge.driver", "webDrivers/windows/msedgedriver.exe");
            }
        driver = new EdgeDriver();
       }
        else if (browserName.equalsIgnoreCase("Safari")) {
            driver = new SafariDriver();
        }
        return driver;
    }

    public WebDriver getCloudDriver(String envName, String envUsername, String envAccessKey, String os, String os_version, String browserName,
                                    String browserVersion) throws IOException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if (envName.equalsIgnoreCase("Saucelabs")) {
            //resolution for Saucelabs
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
        } else if (envName.equalsIgnoreCase("Browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@hub-cloud.browserstack.com/wd/hub"), cap);
        }
        return driver;
    }
    //screenShot Method
    public void screenShot(Scenario scenario) throws IOException {
                Object Timestamp = new SimpleDateFormat(" yy-MM-dd HH-mm-ss").format(new Date());
                TakesScreenshot shot = (TakesScreenshot) driver;
                File screenshotFile = shot.getScreenshotAs(OutputType.FILE);
                byte[] screenshotfile = shot.getScreenshotAs(OutputType.BYTES);
                String screenshotName=scenario.getName() + Timestamp;
                File screensho_Destination = new File("./target/Screenshot/Screenshot" + screenshotName + ".png");
                FileUtils.copyFile(screenshotFile, screensho_Destination);
                scenario.attach(screenshotfile,"image/png",screenshotName);
        }
}