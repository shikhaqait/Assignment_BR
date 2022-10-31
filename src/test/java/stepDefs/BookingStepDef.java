package stepDefs;

import com.jayway.jsonpath.JsonPath;
import httpclient.Requests;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logger.MainLogger;
import objects.BookingDetail;
import objects.CreateBookingRequest;
import org.junit.Assert;
import services.BookingService;
import utilities.DateUtility;
import variables.Credentials;
import variables.Variable;

import java.util.List;
import java.util.Map;

public class BookingStepDef {


    @Given("I have a token to access the application")
    public void setToken() {
        Credentials cred = Variable.setCredentials();
        System.out.println(cred);
        String postBody = "{ \"username\": \"" + cred.getUserName() + "\",\n" +
                "  \"password\": \"" + cred.getPassword() + "\"\n" +
                "}";
        Object post = new Requests().postRequestWithJsonBody(cred.getApiDomain() + "/auth", postBody);
        Variable.credentials.setToken(JsonPath.read(post, "$.token"));
        MainLogger.logger().info(Variable.credentials.getToken());
    }

    @Then("I have created a booking for the following user:")
    public void createBulkBooking(List<String> names) {
        BookingService booking = new BookingService();
        CreateBookingRequest bookingRequest;
        for (String name : names) {
            bookingRequest = new CreateBookingRequest(name.split(" ")[0], name.split(" ")[1]);
            booking.createABooking(bookingRequest);
        }
    }

    @Given("I have booking details for customer:")
    public void getTheBookingDetails(List<String> name) {
        String bookingId = Variable.allBookingIds.get(name.get(0));
        BookingDetail booking = new Requests().get(Variable.credentials.getApiDomain() + "/booking/" + bookingId).as(BookingDetail.class);
        Variable.bookingId.set(bookingId);
        Variable.bookingDetail.set(booking);
    }

    @When("I have updated the customer details:")
    public void updateBookingDetails(Map<String, String> updateDetails) {
        BookingDetail booking = Variable.bookingDetail.get();
        if (updateDetails.containsKey("name")) {
            String expectedName = updateDetails.get("name");
            booking.setFirstname(expectedName.split(" ")[0]);
            booking.setLastname(expectedName.split(" ")[1]);
        }
        if (updateDetails.containsKey("price")) {
            booking.setTotalprice(Integer.valueOf(updateDetails.get("price")));
        }
        if (updateDetails.containsKey("checkout")) {
            String currentCheckout = booking.getBookingdates().getCheckout();
            MainLogger.logger().info("Current checkout date will be " + currentCheckout);
            booking.getBookingdates().setCheckout(DateUtility.getUpdatedDate(currentCheckout, Integer.valueOf(updateDetails.get("checkout"))));
        }
        MainLogger.logger().info("Updated date will be " + booking.getBookingdates().getCheckout());
        BookingService bookingService = new BookingService();
        int status = bookingService.updateBooking(booking, Variable.bookingId.get());
        Assert.assertEquals(status, 200);


    }

    @Then("following booking value should be updated:")
    public void verifyUpdatedBooking(Map<String, String> updatedDetails) {
        BookingDetail updatedBooking = new BookingService().getBooking(Variable.bookingId.get());
        if (updatedDetails.containsKey("name")) {
            Assert.assertEquals(updatedBooking.getFirstname() + " " + updatedBooking.getLastname(), updatedDetails.get("name"));
        }
        if (updatedDetails.containsKey("price")) {
            Assert.assertEquals(String.valueOf(updatedBooking.getTotalprice()), updatedDetails.get("price"));
        }
    }

    @And("I delete the customer")
    public void deleteCustomer() {
        Assert.assertEquals(new BookingService().deleteBooking(Variable.bookingId.get()), 201);
        MainLogger.logger().info("Deleted customer id " + Variable.bookingId.get());
    }

    @Then("customer should be vanished")
    public void validateDeleteCustomer() {
        Assert.assertEquals(new BookingService().getBookingStatus(Variable.bookingId.get()), 404);
    }

    @And("delete all other created booking")
    public void deleteAllOtherBooking(){
        for (String bookingId : Variable.allBookingIds.values()) {
            new BookingService().deleteBooking(Variable.credentials.getApiDomain() + "/booking/" + bookingId);
        }
    }

}
