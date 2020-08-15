package helpers.expressCalculation;

import com.google.gson.Gson;
import helpers.expressCalculation.ftl.responsemodel.TruckFtlResponse;
import helpers.expressCalculation.ftl.responsemodel.TruckRentResponse;
import helpers.expressCalculation.ftl.responsemodel.TruckStraightResponse;
import helpers.expressCalculation.ltl.responsemodel.LtlResponse;
import io.restassured.response.Response;
import settings.utils.PropertyLoader;

import static io.restassured.RestAssured.given;

public class ExpressCalculationHelper {
    public static final String BASE_URL = PropertyLoader.loadProperty("site.url");

    /**
     * =================================================================================================================
     * Public Functions
     * =================================================================================================================
     */

    public String getPriceLTL(String derivalCLADR, String arrivalCLADR, typeOfDelivery type) {
        switch (type) {
            case TERMINAL_STANDARD:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsStandard().getPrice();
            case TERMINAL_EXPRESS:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsExpress().getPrice();
            case TERMINAL_AVIA:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsAvia().getPrice();
            case TERMINAL_DOCUMENTS:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsDocuments().getPrice();
            case HOME_STANDARD:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorStandard().getPrice();
            case HOME_DOCUMENT:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorDocuments().getPrice();
            case HOME_PACKAGE:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorParcel().getPrice();
            case HOME_EXPRESS:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorExpress().getPrice();
            case HOME_AVIA:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorAvia().getPrice();
        }
        return String.valueOf(this);
    }

    public String getPriceFTL(String derivalCLADR, String arrivalCLADR, TypeOfTransport type) {
        switch (type) {
            case TRANSPORT_DELIVERY:
                return getTruckStraightResponse(derivalCLADR, arrivalCLADR).getTruckStraight().getPrice();
            case TRANSPORT_TRUCK:
                return getTruckFtlResponse(derivalCLADR, arrivalCLADR).getTruckFtl().getPrice();
            case TRANSPORT_RENT:
                return getTruckRentResponse(derivalCLADR, arrivalCLADR).getTruckRent().getPrice();
            case TRANSPORT_CONTAINER:
                return "По запросу";
        }
        return String.valueOf(this);
    }

    public String getPeriod(String derivalCLADR, String arrivalCLADR, typeOfDelivery type) {
        String from = periodFrom(derivalCLADR, arrivalCLADR, type);
        String to = periodTo(derivalCLADR, arrivalCLADR, type);
        if (from.equals("0")) {
            return to;
        } else {
            return from + "-" + to;
        }
    }

    public enum typeOfDelivery {
        TERMINAL_STANDARD,
        TERMINAL_EXPRESS,
        TERMINAL_DOCUMENTS,
        TERMINAL_AVIA,
        HOME_STANDARD,
        HOME_DOCUMENT,
        HOME_PACKAGE,
        HOME_EXPRESS,
        HOME_AVIA
    }

    public enum TypeOfTransport {
        TRANSPORT_DELIVERY,
        TRANSPORT_TRUCK,
        TRANSPORT_RENT,
        TRANSPORT_CONTAINER
    }


    /**
     * =================================================================================================================
     * Private Functions
     * =================================================================================================================
     */

    private static LtlResponse getLtlResponse(String derivalCLADR, String arrivalCLADR) {
        Response response = given()
                .when()
                .get(BASE_URL + "api/calculation/v1/main_page/ltl?derival_point_code=" + derivalCLADR + "&arrival_point_code=" + arrivalCLADR);
        Gson gson = new Gson();
        return gson.fromJson(response.asString(), LtlResponse.class);
    }

    private static TruckStraightResponse getTruckStraightResponse(String derivalCLADR, String arrivalCLADR) {
        Response response = given()
                .when()
                .get(BASE_URL + "api/calculation/v1/main_page/truck_straight?derival_point_code=" + derivalCLADR + "&arrival_point_code=" + arrivalCLADR);
        Gson gson = new Gson();
        return gson.fromJson(response.asString(), TruckStraightResponse.class);
    }

    private static TruckFtlResponse getTruckFtlResponse(String derivalCLADR, String arrivalCLADR) {
        Response response = given()
                .when()
                .get(BASE_URL + "api/calculation/v1/main_page/truck_ftl?derival_point_code=" + derivalCLADR + "&arrival_point_code=" + arrivalCLADR);
        Gson gson = new Gson();
        return gson.fromJson(response.asString(), TruckFtlResponse.class);
    }

    private static TruckRentResponse getTruckRentResponse(String derivalCLADR, String arrivalCLADR) {
        Response response = given()
                .when()
                .get(BASE_URL + "api/calculation/v1/main_page/truck_rent?derival_point_code=" + derivalCLADR + "&arrival_point_code=" + arrivalCLADR);
        Gson gson = new Gson();
        return gson.fromJson(response.asString(), TruckRentResponse.class);
    }

    private String periodFrom(String derivalCLADR, String arrivalCLADR, typeOfDelivery type) {
        switch (type) {
            case TERMINAL_STANDARD:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsStandard().getPeriodFrom();
            case TERMINAL_EXPRESS:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsExpress().getPeriodFrom();
            case TERMINAL_AVIA:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsAvia().getPeriodFrom();
            case TERMINAL_DOCUMENTS:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsDocuments().getPeriodFrom();
            case HOME_STANDARD:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorStandard().getPeriodFrom();
            case HOME_DOCUMENT:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorDocuments().getPeriodFrom();
            case HOME_PACKAGE:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorParcel().getPeriodFrom();
            case HOME_EXPRESS:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorExpress().getPeriodFrom();
            case HOME_AVIA:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorAvia().getPeriodFrom();
        }
        return String.valueOf(this);
    }

    private String periodTo(String derivalCLADR, String arrivalCLADR, typeOfDelivery type) {
        switch (type) {
            case TERMINAL_STANDARD:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsStandard().getPeriodTo();
            case TERMINAL_EXPRESS:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsExpress().getPeriodTo();
            case TERMINAL_AVIA:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsAvia().getPeriodTo();
            case TERMINAL_DOCUMENTS:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getTerminalsDocuments().getPeriodTo();
            case HOME_STANDARD:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorStandard().getPeriodTo();
            case HOME_DOCUMENT:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorDocuments().getPeriodTo();
            case HOME_PACKAGE:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorParcel().getPeriodTo();
            case HOME_EXPRESS:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorExpress().getPeriodTo();
            case HOME_AVIA:
                return getLtlResponse(derivalCLADR, arrivalCLADR).getLtl().getDoorToDoorAvia().getPeriodTo();
        }
        return String.valueOf(this);
    }
}
