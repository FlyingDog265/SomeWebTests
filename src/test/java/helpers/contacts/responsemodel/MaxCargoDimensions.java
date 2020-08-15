package helpers.contacts.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaxCargoDimensions {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("starts_at")
    @Expose
    private String startsAt;
    @SerializedName("business_unit_uid")
    @Expose
    private String businessUnitUid;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("decimal")
    @Expose
    private String decimal; // Вот тут я хрен знает, что это за параметр, там везде null. Будет стринга)
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("shipping_weight")
    @Expose
    private String shippingWeight;
    @SerializedName("shipping_volume")
    @Expose
    private String shippingVolume;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("last_starts_at")
    @Expose
    private String lastStartsAt;

    public int getCargoId() {
        return id;
    }

    public String getStartsAt() {
        return startsAt;
    }

    public String getBusinessUnitUid() {
        return businessUnitUid;
    }

    public String getWeight() {
        return weight;
    }

    public String getDecimal() {
        return decimal;
    }

    public String getVolume() {
        return volume;
    }

    public String getLength() {
        return length;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getShippingWeight() {
        return shippingWeight;
    }

    public String getShippingVolume() {
        return shippingVolume;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getLastStartsAt() {
        return lastStartsAt;
    }

}
