package com.ifsul.deputados_api.requests;

import com.ifsul.deputados_api.entities.Deputado;
import com.ifsul.deputados_api.entities.Link;

import java.util.List;

public class ApiDeputados {

    private List<Deputado> dados;

    private List<Link> links;

    public List<Deputado> getDados() {
        return dados;
    }

    public void setDados(List<Deputado> deputados) {
        this.dados = deputados;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
