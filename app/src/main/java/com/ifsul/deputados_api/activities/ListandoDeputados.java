package com.ifsul.deputados_api.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ifsul.deputados_api.requests.ApiDeputados;
import com.ifsul.deputados_api.R;
import com.ifsul.deputados_api.services.RestService;
import com.ifsul.deputados_api.entities.Deputado;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListandoDeputados extends AppCompatActivity {

    private static final int PARTIDO_NAV_ID = R.id.partidoNav;
    private static final int DEPUTADO_NAV_ID = R.id.deputadoNav;
    private static final int PERFIL_NAV_ID = R.id.perfilNav;

    private final static String URL = "https://dadosabertos.camara.leg.br/api/v2/";
    private Retrofit retrofit;
    ListView lista;
    ArrayAdapter<String> adapter;
    List<Deputado> deputados = new ArrayList<>();
    private ProgressBar progressBar;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listando_deputados);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.deputadoNav);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.itemlista, R.id.itemLista);
        lista = findViewById(R.id.listaDeputado);
        lista.setAdapter(adapter);

        progressBar = findViewById(R.id.progressBarListarDeputado);

        consultaResposta();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelecionado = deputados.get(position).getId();

                Intent i = new Intent(getApplicationContext(), BiografiaDeputado.class);

                i.putExtra("id", itemSelecionado);
                startActivity(i);
                finish();

            }
        });

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == PARTIDO_NAV_ID) {
                    startActivity(new Intent(getApplicationContext(), ListandoPartidos.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == DEPUTADO_NAV_ID) {
                    startActivity(new Intent(getApplicationContext(), ListandoDeputados.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == PERFIL_NAV_ID) {
                    startActivity(new Intent(getApplicationContext(), Perfil.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });
    }

    private void consultaResposta(){
        RestService restService = retrofit.create(RestService.class);

        Call<ApiDeputados> call = restService.consultarDeputados();

        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<ApiDeputados>() {
            @Override
            public void onResponse(Call<ApiDeputados> call, Response<ApiDeputados> response) {
                if (response.isSuccessful()) {
                    ApiDeputados responseDeputado = response.body();
                    deputados = responseDeputado.getDados();

                    adapter.clear();
                    deputados.forEach(deputado -> {
                        adapter.add(deputado.getNome());
                    });
                    adapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ApiDeputados> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}