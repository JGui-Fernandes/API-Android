package com.ifsul.deputados_api.entities;

import com.google.gson.annotations.SerializedName;

public class Gabinete {

    @SerializedName("nome")
    private String nome;

    @SerializedName("predio")
    private String predio;

    @SerializedName("sala")
    private String sala;

    @SerializedName("andar")
    private String andar;

    @SerializedName("telefone")
    private String telefone;

    @SerializedName("email")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
