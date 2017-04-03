package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.picocontainer.classname.ClassName;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebConnector {

    private static AppiumDriver driver;
    private static Properties properties;
    private static DesiredCapabilities capabilities;
    private static Logger logger;
    private static int mediumTimeout;
    private static int shortTimeout;
    private static int longTimeout;
    private static String baseURL;
    private static String cloudURL;
    private static String localURL;
    private static String platformName;
    private static String deviceOrientation;
    private static int newCommandTimeout;
    private static String iosVersion;
    private static String androidVersion;
    private static int launchTimeout;
    private static boolean autoAcceptAlerts;
    private static String browserName;
    private static String deviceType;
    private static String emulator;
    private static String simulator;
    private static String device;
    private static String udid;
    private static String host;

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void setDriver(AppiumDriver driver) {
        WebConnector.driver = driver;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties() {
        WebConnector.properties = new Properties();
        try {
            properties.load(WebConnector.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public static void setCapabilities(DesiredCapabilities capabilities) {
        WebConnector.capabilities = capabilities;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger() {
        WebConnector.logger = Logger.getLogger(ClassName.class.getName());
    }

    public static int getShortTimeout() {
        return shortTimeout;
    }

    public static void setShortTimeout() {
        WebConnector.shortTimeout = Integer.parseInt(getProperties().getProperty("shortTimeout"));
    }

    public static int getMediumTimeout() {
        return mediumTimeout;
    }

    public static void setMediumTimeout() {
        WebConnector.mediumTimeout = Integer.parseInt(getProperties().getProperty("mediumTimeout"));
    }

    public static int getLongTimeout() {
        return longTimeout;
    }

    public static void setLongTimeout() {
        WebConnector.longTimeout = Integer.parseInt(getProperties().getProperty("longTimeout"));
    }

    public static String getBaseURL() {
        return baseURL;
    }

    public static void setBaseURL() {
        WebConnector.baseURL = getProperties().getProperty("environment");
    }

    public static String getCloudURL() {
        return cloudURL;
    }

    public static void setCloudURL() {
        WebConnector.cloudURL = getProperties().getProperty("cloudURL");
    }

    public static String getLocalURL() {
        return localURL;
    }

    public static void setLocalURL() {
        WebConnector.localURL = getProperties().getProperty("localURL");
    }

    public static String getPlatformName() {
        return platformName;
    }

    public static void setPlatformName() {
        WebConnector.platformName = getProperties().getProperty("platformName");
    }

    public static String getDeviceOrientation() {
        return deviceOrientation;
    }

    public static void setDeviceOrientation() {
        WebConnector.deviceOrientation = getProperties().getProperty("deviceOrientation");
    }

    public static int getNewCommandTimeout() {
        return newCommandTimeout;
    }

    public static void setNewCommandTimeout() {
        WebConnector.newCommandTimeout = Integer.parseInt(getProperties().getProperty("newCommandTimeout"));
    }

    public static String getAndroidVersion() {
        return androidVersion;
    }

    public static void setAndroidVersion() {
        WebConnector.androidVersion = getProperties().getProperty("androidVersion");
    }

    public static int getLaunchTimeout() {
        return launchTimeout;
    }

    public static void setLaunchTimeout() {
        WebConnector.launchTimeout = Integer.parseInt(getProperties().getProperty("launchTimeout"));
    }

    public static boolean getAutoAcceptAlerts() {
        return autoAcceptAlerts;
    }

    public static void setAutoAcceptAlerts() {
        WebConnector.autoAcceptAlerts = Boolean.parseBoolean(getProperties().getProperty("autoAcceptAlerts"));
    }

    public static String getBrowserName() {
        return browserName;
    }

    public static void setBrowserName() {
        WebConnector.browserName = getProperties().getProperty("browserName");
    }

    public static String getiosVersion() {
        return iosVersion;
    }

    public static void setiosVersion() {
        WebConnector.iosVersion = getProperties().getProperty("iosVersion");
    }

    public static String getDeviceType() {
        return deviceType;
    }

    public static void setDeviceType() {
        WebConnector.deviceType = getProperties().getProperty("deviceType");
    }

    public static String getEmulator() {
        return emulator;
    }

    public static void setEmulator() {
        WebConnector.emulator = getProperties().getProperty("emulator");
    }

    public static String getSimulator() {
        return simulator;
    }

    public static void setSimulator() {
        WebConnector.simulator = getProperties().getProperty("simulator");
    }

    public static String getDevice() {
        return device;
    }

    public static void setDevice() {
        WebConnector.device = getProperties().getProperty("device");
    }

    public static String getUdid() {
        return udid;
    }

    public static void setUdid() {
        WebConnector.udid = getProperties().getProperty("udid");
    }

    public static String getHost() {
        return host;
    }

    public static void setHost() {
        WebConnector.host = getProperties().getProperty("host");
    }

    public void initialize() throws MalformedURLException {
        setLogger();
        setProperties();
        initializeVariables();
        setDesiredCapabalitiesForPlatformName();
        setDesiredCapabilitiesForDeviceType();
        createInstanceOfDriver();
        manageBrowser();
    }

    public void initializeVariables(){
        getLogger().info("INITIALIZING VARIABLES");
        setShortTimeout();
        setMediumTimeout();
        setLongTimeout();
        setBaseURL();
        setCloudURL();
        setLocalURL();
        setPlatformName();
        setDeviceOrientation();
        setNewCommandTimeout();
        setAndroidVersion();
        setiosVersion();
        setLaunchTimeout();
        setAutoAcceptAlerts();
        setBrowserName();
        setDeviceType();
        setEmulator();
        setSimulator();
        setDevice();
        setUdid();
        setHost();
        getLogger().info("VARIABLES INITIALIZED");
    }

    public void setDesiredCapabalitiesForPlatformName() {
        getLogger().info("SETTING DESIRED CAPABILITIES FOR PLATFORM");
        switch (getPlatformName()) {
            case "Android":
                setCapabilities(DesiredCapabilities.android());
                //capabilities.setCapability("appiumVersion", "1.4.13");
                getCapabilities().setCapability("deviceOrientation", getDeviceOrientation());
                getCapabilities().setCapability("platformName", getPlatformName());
                getCapabilities().setCapability("newCommandTimeout", getNewCommandTimeout());
                getCapabilities().setCapability("platformVersion", getAndroidVersion());
                getCapabilities().setCapability("launchTimeout", getLaunchTimeout());
                getCapabilities().setCapability("autoAcceptAlerts", getAutoAcceptAlerts());
                getCapabilities().setCapability("browserName", getBrowserName());
                break;
            case "iOS":
                setCapabilities(DesiredCapabilities.iphone());
                //capabilities.setCapability("appiumVersion", "1.4.16");
                getCapabilities().setCapability("deviceOrientation", getDeviceOrientation());
                getCapabilities().setCapability("platformName", getPlatformName());
                getCapabilities().setCapability("newCommandTimeout", getNewCommandTimeout());
                getCapabilities().setCapability("platformVersion", getiosVersion());
                getCapabilities().setCapability("launchTimeout", getLaunchTimeout());
                getCapabilities().setCapability("autoAcceptAlerts", getAutoAcceptAlerts());
                getCapabilities().setCapability("browserName", getBrowserName());
                break;
        }
        getLogger().info("DESIRED CAPABILITIES FOR PLATFORM SET");
    }

    public void setDesiredCapabilitiesForDeviceType() throws MalformedURLException {
        getLogger().info("SETTING DESIRED CAPABILITIES FOR DEVICE TYPE");
        switch (getDeviceType()) {
            case "Emulator":
                getCapabilities().setCapability("deviceName", getEmulator());
                break;
            case "Simulator":
                getCapabilities().setCapability("deviceName", getSimulator());
                break;
            case "Real Device":
                switch (getPlatformName()) {
                    case "Android":
                        getCapabilities().setCapability("deviceName", getDevice());
                        break;
                    case "iOS":
                        getCapabilities().setCapability("deviceName", getDevice());
                        getCapabilities().setCapability("udid", getUdid());
                        break;
                }
                break;
        }
        getLogger().info("DESIRED CAPABILITIES FOR DEVICE TYPE SET");
    }

    public void createInstanceOfDriver() throws MalformedURLException {
        getLogger().info("CREATING BROWSER INSTANCE");
        switch (getHost()) {
            case "local":
                switch (getPlatformName()) {
                    case "Android":
                        setDriver(new AndroidDriver(new URL(getLocalURL()), getCapabilities()));
                        break;
                    case "iOS":
                        setDriver(new IOSDriver(new URL(getLocalURL()), getCapabilities()));
                        break;
                }
                break;
            case "cloud":
                switch (getPlatformName()) {
                    case "Android":
                        getCapabilities().setCapability("name", "Mobile Web Tests - " + getEmulator());
                        setDriver(new AndroidDriver(new URL(getCloudURL()), getCapabilities()));
                        break;
                    case "iOS":
                        getCapabilities().setCapability("name", "Mobile Web Tests - " + getSimulator());
                        setDriver(new IOSDriver(new URL(getCloudURL()), getCapabilities()));
                        break;
                }
                break;
        }
        getLogger().info("BROWSER INSTANCE CREATED");
    }

    public void manageBrowser() {
        getLogger().info("MANAGING BROWSER");
        getDriver().manage().timeouts().pageLoadTimeout(getLongTimeout(), TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(getMediumTimeout(), TimeUnit.SECONDS);
        getLogger().info("BROWSER MANAGED");
    }
}