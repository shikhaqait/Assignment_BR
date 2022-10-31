package webDriver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    /**
     * The Constant threadLocal.
     */
    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();

    /**
     * Gets the driver.
     *
     * @return the driver
     */
    public static WebDriver getDriver() {
        return threadLocal.get();
    }


    /**
     * This method set the driver instance
     *
     * @param scenarioName
     * @throws Exception
     */
    public static void setDriver() {
        DriverFactory driverClass = new DriverFactory();// main class for initliazing browser
        threadLocal.set(driverClass.openInstance());
    }

    /**
     * this method close the instance picked from the thread pool.
     */
    public static void closeDriver() {
        if (getDriver() != null) {
            try {
                getDriver().quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        threadLocal.remove();
    }


}
