package settings.testrail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestRailRequestModel {

    @Expose
    @SerializedName("status_id")
    private final int statusId;

    @Expose
    @SerializedName("comment")
    private final String comment;

    @Expose
    @SerializedName("version")
    private final String version;

    @Expose
    @SerializedName("defects")
    private final String defects;

    public TestRailRequestModel(int statusId, String comment, String version, String defects) {
        this.statusId = statusId;
        this.comment = comment;
        this.version = version;
        this.defects = defects;
    }

}
