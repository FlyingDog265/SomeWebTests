package helpers.contacts.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactCity {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("timeshift")
    @Expose
    private int timeshift;
    @SerializedName("day2day")
    @Expose
    private boolean day2day;
    @SerializedName("opening")
    @Expose
    private String opening;
    @SerializedName("subdomain")
    @Expose
    private String subdomain;
    @SerializedName("terminals_count")
    @Expose
    private String terminalsCount;
    @SerializedName("lat")
    @Expose
    private double locationLat;
    @SerializedName("lon")
    @Expose
    private double locationLon;
    @SerializedName("zoom")
    @Expose
    private int zoom;
    @SerializedName("requestEndTime")
    @Expose
    private String requestEndTime;
    @SerializedName("sfrequestEndTime")
    @Expose
    private String sfrequestEndTime;
    @SerializedName("day2dayRequest")
    @Expose
    private boolean day2dayRequest;
    @SerializedName("day2daySFRequest")
    @Expose
    private boolean day2daySFRequest;
    @SerializedName("preorderRequest")
    @Expose
    private boolean preorderRequest;
    @SerializedName("freeStorageDays")
    @Expose
    private int freeStorageDays;
    @SerializedName("chat_mode")
    @Expose
    private String chatMode;

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getTimeshift() {
        return timeshift;
    }

    public boolean isDay2day() {
        return day2day;
    }

    public String getOpening() {
        return opening;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public String getTerminalsCount() {
        return terminalsCount;
    }

    public double getLocationLatitude() {
        return locationLat;
    }

    public double getLocationLongitude() {
        return locationLon;
    }

    public int getZoom() {
        return zoom;
    }

    public String getRequestEndTime() {
        return requestEndTime;
    }

    public String getSfrequestEndTime() {
        return sfrequestEndTime;
    }

    public boolean isDay2dayRequest() {
        return day2dayRequest;
    }

    public boolean isDay2daySFRequest() {
        return day2daySFRequest;
    }

    public boolean isPreorderRequest() {
        return preorderRequest;
    }

    public int getFreeStorageDays() {
        return freeStorageDays;
    }

    public String getChatMode() {
        return chatMode;
    }

}