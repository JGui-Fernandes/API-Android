package com.ifsul.deputados_api.requests;

import com.ifsul.deputados_api.entities.Deputado;
import com.ifsul.deputados_api.entities.Link;

import java.util.List;

public class ApiBiografia {

    private Deputado dados;

    private List<Link> links;

    public Deputado getDados() {
        return dados;
    }

    public void setDados(Deputado dados) {
        this.dados = dados;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
