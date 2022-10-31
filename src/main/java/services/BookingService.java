package services;

import com.jayway.jsonpath.JsonPath;
import httpclient.Requests;
import logger.MainLogger;
import objects.BookingDetail;
import objects.CreateBookingRequest;
import variables.Variable;

import java.util.HashMap;
import java.util.Map;

public class BookingService extends Requests {

    Map<String, String> headers;

    public BookingService() {
        headers = new HashMap<String, String>();
    }

    public BookingDetail getBooking(String id) {
        BookingDetail details = get(Variable.credentials.getApiDomain() + "/booking/" + id).as(BookingDetail.class);
        return details;
    }

    public int getBookingStatus(String id) {
        return get(Variable.credentials.getApiDomain() + "/booking/" + id).statusCode();
    }

    public void createABooking(CreateBookingRequest request) {
        Object obj = postRequestWithJsonBody(Variable.credentials.getApiDomain() + "/booking", request);
        int bookingId = JsonPath.read(obj, "$.bookingid");
        MainLogger.logger().info("Booking id is " + bookingId + " for customer " + request.getFirstname() + " " + request.getLastname());
        Variable.allBookingIds.put(request.getFirstname() + " " + request.getLastname(), "" + bookingId);
    }

    public int deleteBooking(String bookingId) {
        headers.put("Cookie", "token=" + Variable.credentials.getToken());
        return delete(Variable.credentials.getApiDomain() + "/booking/" + bookingId, headers);
    }

    public int updateBooking(BookingDetail details, String bookingId) {
        headers.put("Cookie", "token=" + Variable.credentials.getToken());
        return update(Variable.credentials.getApiDomain() + "/booking/" + bookingId, headers, details);
    }


}
