package helpers.expressCalculation.ftl.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TruckFtlResponse {

    @SerializedName("truck_ftl")
    @Expose
    private TruckFtl truckFtl;

    public TruckFtl getTruckFtl() {
        return truckFtl;
    }

}
