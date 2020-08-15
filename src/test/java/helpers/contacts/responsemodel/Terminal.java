package helpers.contacts.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Terminal {

    @SerializedName("terminalUID")
    @Expose
    private String terminalUID;
    @SerializedName("terminalName")
    @Expose
    private String terminalName;
    @SerializedName("terminalAddress")
    @Expose
    private String terminalAddress;
    @SerializedName("city")
    @Expose
    private String terminalCity;
    @SerializedName("longitude")
    @Expose
    private double terminalLongitude;
    @SerializedName("latitude")
    @Expose
    private double terminalLatitude;
    @SerializedName("orders_giveout_point")
    @Expose
    private boolean ordersGiveoutPoint;

    public String getTerminalUID() {
        return terminalUID;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public String getTerminalAddress() {
        return terminalAddress;
    }

    public String getTerminalCity() {
        return terminalCity;
    }

    public double getTerminalLatitude() {
        return terminalLatitude;
    }

    public double getTerminalLongitude() {
        return terminalLongitude;
    }

    public boolean isOrdersGiveoutPoint() {
        return ordersGiveoutPoint;
    }

}