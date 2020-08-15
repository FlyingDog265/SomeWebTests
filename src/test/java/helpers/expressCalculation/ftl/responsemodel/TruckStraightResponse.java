package helpers.expressCalculation.ftl.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TruckStraightResponse {

    @SerializedName("truck_straight")
    @Expose
    private TruckStraight truckStraight;

    public TruckStraight getTruckStraight() {
        return truckStraight;
    }

}
