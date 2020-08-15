package helpers.contacts.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Worktable {

    @SerializedName("id")
    @Expose
    private int worktableID;
    @SerializedName("contactID")
    @Expose
    private int contactID;
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("mon")
    @Expose
    private String mon;
    @SerializedName("tue")
    @Expose
    private String tue;
    @SerializedName("wed")
    @Expose
    private String wed;
    @SerializedName("thu")
    @Expose
    private String thu;
    @SerializedName("fri")
    @Expose
    private String fri;
    @SerializedName("sat")
    @Expose
    private String sat;
    @SerializedName("sun")
    @Expose
    private String sun;
    @SerializedName("order")
    @Expose
    private int order;
    @SerializedName("work_hours")
    @Expose
    private String workHours;

    public int getWorktableID() {
        return worktableID;
    }

    public int getContactID() {
        return contactID;
    }

    public String getHeader() {
        return header;
    }

    public String getMonday() {
        return mon;
    }

    public String getTuesday() {
        return tue;
    }

    public String getWednesday() {
        return wed;
    }

    public String getThursday() {
        return thu;
    }

    public String getFriday() {
        return fri;
    }

    public String getSaturday() {
        return sat;
    }

    public String getSunday() {
        return sun;
    }

    public int getOrder() {
        return order;
    }

    public String getWorkHours() {
        return workHours;
    }

}
