package com.ifsul.deputados_api.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partido {
    @SerializedName("id")
    private String id;

    @SerializedName("sigla")
    private String sigla;

    @SerializedName("nome")
    private String nome;

    @SerializedName("uri")
    private String uri;

    @SerializedName("status")
    private Status status;

    @SerializedName("numeroEleitoral")
    private String numeroEleitoral;

    @SerializedName("urlLogo")
    private String urlLogo;

    @SerializedName("urlWebSite")
    private String urlWebSite;

    @SerializedName("urlFacebook")
    private String urlFacebook;

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNumeroEleitoral() {
        return numeroEleitoral;
    }

    public void setNumeroEleitoral(String numeroEleitoral) {
        this.numeroEleitoral = numeroEleitoral;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getUrlWebSite() {
        return urlWebSite;
    }

    public void setUrlWebSite(String urlWebSite) {
        this.urlWebSite = urlWebSite;
    }

    public String getUrlFacebook() {
        return urlFacebook;
    }

    public void setUrlFacebook(String urlFacebook) {
        this.urlFacebook = urlFacebook;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


}
