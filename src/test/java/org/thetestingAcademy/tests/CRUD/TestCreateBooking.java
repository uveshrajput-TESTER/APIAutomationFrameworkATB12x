package org.thetestingAcademy.tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import org.thetestingAcademy.Pojos.responsePOJO.BookingResponse;
import org.thetestingAcademy.base.BaseTest;
import org.thetestingAcademy.endpoints.APIConstants;

import static io.restassured.RestAssured.requestSpecification;

public class TestCreateBooking extends BaseTest {
    @Test(groups = "reg", priority = 1)
    @Owner("Uvesh ")
    @Description("TC#1 - Verify that the Booking can be Created")
    public void testCreateBookingPOST_Positive() {

        // Setup will first and making the request - Part - 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).log().all()
                .post();

        //Extraction Part - 2
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response,200);

        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Pramod");

    }

}
