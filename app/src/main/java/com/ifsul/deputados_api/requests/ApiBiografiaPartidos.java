package com.ifsul.deputados_api.requests;

import com.ifsul.deputados_api.entities.Deputado;
import com.ifsul.deputados_api.entities.Link;
import com.ifsul.deputados_api.entities.Partido;

import java.util.List;

public class ApiBiografiaPartidos {

    private Partido dados;

    private List<Link> links;

    public Partido getDados() {
        return dados;
    }

    public void setDados(Partido dados) {
        this.dados = dados;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
