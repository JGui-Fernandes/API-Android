package com.ifsul.deputados_api.entities;

import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("data")
    private String data;

    @SerializedName("idLegislatura")
    private String idLegislatura;

    @SerializedName("situacao")
    private String situacao;

    @SerializedName("totalPosse")
    private String totalPosse;

    @SerializedName("totalMembros")
    private String totalMembros;

    @SerializedName("uriMembros")
    private String uriMembros;

    @SerializedName("lider")
    private Deputado lider;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdLegislatura() {
        return idLegislatura;
    }

    public void setIdLegislatura(String idLegislatura) {
        this.idLegislatura = idLegislatura;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTotalPosse() {
        return totalPosse;
    }

    public void setTotalPosse(String totalPosse) {
        this.totalPosse = totalPosse;
    }

    public String getTotalMembros() {
        return totalMembros;
    }

    public void setTotalMembros(String totalMembros) {
        this.totalMembros = totalMembros;
    }

    public String getUriMembros() {
        return uriMembros;
    }

    public void setUriMembros(String uriMembros) {
        this.uriMembros = uriMembros;
    }

    public Deputado getLider() {
        return lider;
    }

    public void setLider(Deputado lider) {
        this.lider = lider;
    }
}
