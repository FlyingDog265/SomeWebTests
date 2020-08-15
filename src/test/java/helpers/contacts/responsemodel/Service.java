package helpers.contacts.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("office")
    @Expose
    private boolean office;
    @SerializedName("receive_giveout")
    @Expose
    private boolean receiveGiveout;
    @SerializedName("storage")
    @Expose
    private boolean storage;
    @SerializedName("express")
    @Expose
    private boolean express;
    @SerializedName("only_giveout")
    @Expose
    private boolean onlyGiveout;
    @SerializedName("only_receive")
    @Expose
    private boolean onlyReceive;

    public boolean getIsOffice() {
        return office;
    }

    public boolean getIsReceiveGiveout() {
        return receiveGiveout;
    }

    public boolean getIsStorage() {
        return storage;
    }

    public boolean getIsExpress() {
        return express;
    }

    public boolean getIsOnlyGiveout() {
        return onlyGiveout;
    }

    public boolean getIsOnlyReceive() {
        return onlyReceive;
    }

}
