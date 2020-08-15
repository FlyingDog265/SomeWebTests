package helpers.expressCalculation.ftl.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TruckRentResponse {

    @SerializedName("truck_rent")
    @Expose
    private TruckRent truckRent;

    public TruckRent getTruckRent() {
        return truckRent;
    }

}
