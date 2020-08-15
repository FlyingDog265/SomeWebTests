package helpers.expressCalculation.ltl.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ltl {

    @SerializedName("terminals_standard")
    @Expose
    private TerminalsStandard terminalsStandard;
    @SerializedName("terminals_documents")
    @Expose
    private TerminalsDocuments terminalsDocuments;
    @SerializedName("terminals_express")
    @Expose
    private TerminalsExpress terminalsExpress;
    @SerializedName("terminals_avia")
    @Expose
    private TerminalsAvia terminalsAvia;
    @SerializedName("door_to_door_standard")
    @Expose
    private DoorToDoorStandard doorToDoorStandard;
    @SerializedName("door_to_door_documents")
    @Expose
    private DoorToDoorDocuments doorToDoorDocuments;
    @SerializedName("door_to_door_parcel")
    @Expose
    private DoorToDoorParcel doorToDoorParcel;
    @SerializedName("door_to_door_express")
    @Expose
    private DoorToDoorExpress doorToDoorExpress;
    @SerializedName("door_to_door_avia")
    @Expose
    private DoorToDoorAvia doorToDoorAvia;

    public TerminalsStandard getTerminalsStandard() {
        return terminalsStandard;
    }

    public TerminalsDocuments getTerminalsDocuments() {
        return terminalsDocuments;
    }

    public TerminalsExpress getTerminalsExpress() {
        return terminalsExpress;
    }

    public TerminalsAvia getTerminalsAvia() {
        return terminalsAvia;
    }

    public DoorToDoorStandard getDoorToDoorStandard() {
        return doorToDoorStandard;
    }

    public DoorToDoorDocuments getDoorToDoorDocuments() {
        return doorToDoorDocuments;
    }

    public DoorToDoorParcel getDoorToDoorParcel() {
        return doorToDoorParcel;
    }

    public DoorToDoorExpress getDoorToDoorExpress() {
        return doorToDoorExpress;
    }

    public DoorToDoorAvia getDoorToDoorAvia() {
        return doorToDoorAvia;
    }

}
