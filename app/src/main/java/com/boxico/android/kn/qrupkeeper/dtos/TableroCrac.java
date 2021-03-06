package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableroCrac extends AbstractArtefactDto {

    @SerializedName("kwr")
    @Expose
    private String kwr;

    @SerializedName("kws")
    @Expose
    private String kws;

    @SerializedName("kwt")
    @Expose
    private String kwt;

    @SerializedName("par")
    @Expose
    private String par;

    @SerializedName("pas")
    @Expose
    private String pas;

    @SerializedName("pat")
    @Expose
    private String pat;

    public TableroCrac() {
        super();
    }


    public String getKwr() {
        return kwr;
    }

    public void setKwr(String kwr) {
        this.kwr = kwr;
    }

    public String getKws() {
        return kws;
    }

    public void setKws(String kws) {
        this.kws = kws;
    }

    public String getKwt() {
        return kwt;
    }

    public void setKwt(String kwt) {
        this.kwt = kwt;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public TableroCrac(int id, String name, String kwr, String kws, String kwt, String par, String pas, String pat, int codigo, int idForm, int idRemoteDB, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.kwr = kwr;
        this.kws = kws;
        this.kwt = kwt;
        this.par = par;
        this.pas = pas;
        this.pat = pat;
    }

    @Override
    public String toString() {
        return "░ Tablero Crac[" +
                "name='" + name + '\'' +
                ", kwr='" + kwr + '\'' +
                ", kws='" + kws + '\'' +
                ", kwt='" + kwt + '\'' +
                ", par='" + par + '\'' +
                ", pas='" + pas + '\'' +
                ", pat='" + pat + '\'' +

                ']';
    }
}
