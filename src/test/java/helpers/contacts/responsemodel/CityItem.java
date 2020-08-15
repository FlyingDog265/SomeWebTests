package helpers.contacts.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityItem {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("name")
    @Expose
    private String cityName;
    @SerializedName("address")
    @Expose
    private String cityAddress;
    @SerializedName("mail")
    @Expose
    private String cityMail;
    @SerializedName("storage")
    @Expose
    private int cityStorage;
    @SerializedName("service")
    @Expose
    private Service cityService;
    @SerializedName("show_phones")
    @Expose
    private List<String> showPhones = null;
    @SerializedName("is_new")
    @Expose
    private boolean cityIsNew;
    @SerializedName("claddr")
    @Expose
    private String cityCladr;
    @SerializedName("max_cargo_dimensions")
    @Expose
    private MaxCargoDimensions maxCargoDimensions;
    @SerializedName("nouns")
    @Expose
    private Nouns cityNouns;
    @SerializedName("contact_city")
    @Expose
    private ContactCity contactCity;
    @SerializedName("terminal")
    @Expose
    private Terminal terminal;
    @SerializedName("worktables")
    @Expose
    private List<Worktable> worktables = null;


    public int getItemId() {
        return id;
    }

    public String getItemCity() {
        return city;
    }

    public String getItemName() {
        return cityName;
    }

    public String getItemAddress() {
        return cityAddress;
    }

    public String getItemMail() {
        return cityMail;
    }

    public int getItemStorage() {
        return cityStorage;
    }

    public Service getItemService() {
        return cityService;
    }

    public List<String> getShowPhones() {
        return showPhones;
    }

    public boolean getIsNew() {
        return cityIsNew;
    }

    public String getCityCladr() {
        return cityCladr;
    }

    public MaxCargoDimensions getMaxCargoDimensions() {
        return maxCargoDimensions;
    }

    public Nouns getCityNouns() {
        return cityNouns;
    }

    public ContactCity getContactCity() {
        return contactCity;
    }

    public Terminal getItemTerminal() {
        return terminal;
    }

    public List<Worktable> getWorktables() {
        return worktables;
    }

}