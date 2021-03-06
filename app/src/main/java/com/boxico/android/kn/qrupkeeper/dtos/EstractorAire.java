package com.boxico.android.kn.qrupkeeper.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstractorAire extends AbstractArtefactDto {


    @SerializedName("arranque")
    @Expose
    private String arranque;

    @SerializedName("funcionamiento")
    @Expose
    private String funcionamiento;

    @SerializedName("engrase")
    @Expose
    private String engrase;

    @SerializedName("limpieza")
    @Expose
    private String limpieza;

    @SerializedName("correas")
    @Expose
    private String correas;


    public EstractorAire() {

    }

    public String getArranque() {
        return arranque;
    }

    public void setArranque(String arranque) {
        this.arranque = arranque;
    }

    public String getFuncionamiento() {
        return funcionamiento;
    }

    public void setFuncionamiento(String funcionamiento) {
        this.funcionamiento = funcionamiento;
    }

    public String getEngrase() {
        return engrase;
    }

    public void setEngrase(String engrase) {
        this.engrase = engrase;
    }

    public String getLimpieza() {
        return limpieza;
    }

    public void setLimpieza(String limpieza) {
        this.limpieza = limpieza;
    }

    public String getCorreas() {
        return correas;
    }

    public void setCorreas(String correas) {
        this.correas = correas;
    }

    public EstractorAire(int id, String name, int codigo, int idForm, int idRemoteDB, String arranque, String funcionamiento, String engrase, String limpieza, String correas, String desc) {
        super(id, name, codigo, idForm, idRemoteDB, desc);
        this.arranque = arranque;
        this.funcionamiento = funcionamiento;
        this.engrase = engrase;
        this.limpieza = limpieza;
        this.correas = correas;
    }

    @Override
    public String toString() {
        return "EstractorAire{" +
                "arranque='" + arranque + '\'' +
                ", funcionamiento='" + funcionamiento + '\'' +
                ", engrase='" + engrase + '\'' +
                ", limpieza='" + limpieza + '\'' +
                ", correas='" + correas + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
