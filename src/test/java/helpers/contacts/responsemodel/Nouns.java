package helpers.contacts.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nouns {

    @SerializedName("nominative")
    @Expose
    private String nominative;
    @SerializedName("genitive")
    @Expose
    private String genitive;
    @SerializedName("dative")
    @Expose
    private String dative;
    @SerializedName("instrumental")
    @Expose
    private String instrumental;
    @SerializedName("accusative")
    @Expose
    private String accusative;
    @SerializedName("prepositional")
    @Expose
    private String prepositional;
    @SerializedName("i")
    @Expose
    private String i;
    @SerializedName("r")
    @Expose
    private String r;
    @SerializedName("d")
    @Expose
    private String d;
    @SerializedName("t")
    @Expose
    private String t;
    @SerializedName("v")
    @Expose
    private String v;
    @SerializedName("p")
    @Expose
    private String p;

    public String getNominative() {
        return nominative;
    }

    public String getGenitive() {
        return genitive;
    }

    public String getDative() {
        return dative;
    }

    public String getInstrumental() {
        return instrumental;
    }

    public String getAccusative() {
        return accusative;
    }

    public String getPrepositional() {
        return prepositional;
    }

    public String getI() {
        return i;
    }

    public String getR() {
        return r;
    }

    public String getD() {
        return d;
    }

    public String getT() {
        return t;
    }

    public String getV() {
        return v;
    }

    public String getP() {
        return p;
    }

}
