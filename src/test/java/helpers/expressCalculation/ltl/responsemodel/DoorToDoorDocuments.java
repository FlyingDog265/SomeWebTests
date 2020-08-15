package helpers.expressCalculation.ltl.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoorToDoorDocuments {

    @SerializedName("period_from")
    @Expose
    private int periodFrom;
    @SerializedName("period_to")
    @Expose
    private int periodTo;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("requests_path")
    @Expose
    private String requestsPath;
    @SerializedName("comment")
    @Expose
    private String comment;

    public String getPeriodFrom() {
        return String.valueOf(periodFrom);
    }

    public String getPeriodTo() {
        return String.valueOf(periodTo);
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public String getRequestsPath() {
        return requestsPath;
    }

    public String getComment() {
        return comment;
    }

}
