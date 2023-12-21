package com.ifsul.deputados_api.entities;

import com.google.gson.annotations.SerializedName;

public class UltimoStatus {
    @SerializedName("id")
    private String id;

    @SerializedName("uri")
    private String uri;

    @SerializedName("nome")
    private String nome;

    @SerializedName("siglaPartido")
    private String siglaPartido;

    @SerializedName("uriPartido")
    private String uriPartido;

    @SerializedName("siglaUf")
    private String siglaUf;

    @SerializedName("idLegislatura")
    private int idLegislatura;

    @SerializedName("urlFoto")
    private String urlFoto;

    @SerializedName("email")
    private String email;

    @SerializedName("data")
    private String data;

    @SerializedName("nomeEleitoral")
    private String nomeEleitoral;

    @SerializedName("gabinete")
    private Gabinete gabinete;

    @SerializedName("situacao")
    private String situacao;

    @SerializedName("condicaoEleitoral")
    private String condicaoEleitoral;

    @SerializedName("descricaoStatus")
    private String descricaoStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public String getUriPartido() {
        return uriPartido;
    }

    public void setUriPartido(String uriPartido) {
        this.uriPartido = uriPartido;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public int getIdLegislatura() {
        return idLegislatura;
    }

    public void setIdLegislatura(int idLegislatura) {
        this.idLegislatura = idLegislatura;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNomeEleitoral() {
        return nomeEleitoral;
    }

    public void setNomeEleitoral(String nomeEleitoral) {
        this.nomeEleitoral = nomeEleitoral;
    }

    public Gabinete getGabinete() {
        return gabinete;
    }

    public void setGabinete(Gabinete gabinete) {
        this.gabinete = gabinete;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getCondicaoEleitoral() {
        return condicaoEleitoral;
    }

    public void setCondicaoEleitoral(String condicaoEleitoral) {
        this.condicaoEleitoral = condicaoEleitoral;
    }

    public String getDescricaoStatus() {
        return descricaoStatus;
    }

    public void setDescricaoStatus(String descricaoStatus) {
        this.descricaoStatus = descricaoStatus;
    }
}
