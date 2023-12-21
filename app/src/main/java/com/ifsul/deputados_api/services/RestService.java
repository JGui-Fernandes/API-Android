package com.ifsul.deputados_api.services;

import com.ifsul.deputados_api.requests.ApiBiografia;
import com.ifsul.deputados_api.requests.ApiBiografiaPartidos;
import com.ifsul.deputados_api.requests.ApiDeputados;
import com.ifsul.deputados_api.requests.ApiPartidos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestService {

    @GET("partidos?ordem=ASC&ordenarPor=sigla")
    Call<ApiPartidos> consultarDadosApi();

    @GET("deputados?ordem=ASC&ordenarPor=nome")
    Call<ApiDeputados> consultarDeputados();

    @GET("deputados/{id}")
    Call<ApiBiografia> consultarBiografia(@Path("id") String id);

    @GET("partidos/{id}")
    Call<ApiBiografiaPartidos> consultarBiografiaPartido(@Path("id") String id);
}
