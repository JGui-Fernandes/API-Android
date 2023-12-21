package com.ifsul.deputados_api.requests;

import com.ifsul.deputados_api.entities.Link;
import com.ifsul.deputados_api.entities.Partido;

import java.util.List;

public class ApiPartidos {

    private List<Partido> dados;
    private List<Link> links;

    public List<Partido> getDados() {
        return dados;
    }

    public void setDados(List<Partido> partidos) {
        this.dados = partidos;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
