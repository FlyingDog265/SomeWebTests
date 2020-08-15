package helpers.expressCalculation.ltl.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LtlResponse {

    @SerializedName("ltl")
    @Expose
    private Ltl ltl;

    public Ltl getLtl() {
        return ltl;
    }

}
