package variables;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.Scenario;
import logger.MainLogger;
import objects.BookingDetail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Variable {

    public static Map<String, String> allBookingIds = new HashMap<String, String>();

    private static final ThreadLocal<Scenario> localScenario = new ThreadLocal<>();

    public static final ThreadLocal<BookingDetail> bookingDetail = new ThreadLocal<>();

    public static final ThreadLocal<String> bookingId = new ThreadLocal<>();

    public static void setScenario(Scenario scenario) {
        localScenario.set(scenario);
    }

    public static Scenario getScenario() {
        return localScenario.get();
    }

    public static Credentials credentials;


    public static Credentials setCredentials() {
        String file = System.getProperty("user.dir") + File.separator + "cred.json";
        Credentials cred = new Credentials();
        try {
            String url = JsonPath.parse(new File(file)).read("$.apiDomain");
            String userName = JsonPath.parse(new File(file)).read("$.userName");
            String password = JsonPath.parse(new File(file)).read("$.password");
            cred.setApiDomain(url);
            cred.setPassword(password);
            cred.setUserName(userName);
            credentials = cred;
        } catch (IOException e) {
            MainLogger.logger().info("No such file exist in the project root cred.json");
            throw new RuntimeException(e);
        }
        return credentials;
    }
}
