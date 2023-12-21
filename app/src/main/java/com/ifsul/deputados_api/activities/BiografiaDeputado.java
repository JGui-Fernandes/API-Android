package com.ifsul.deputados_api.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ifsul.deputados_api.requests.ApiBiografia;
import com.ifsul.deputados_api.services.RestService;
import com.ifsul.deputados_api.entities.Deputado;
import com.ifsul.deputados_api.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BiografiaDeputado extends AppCompatActivity {

    private static final int PARTIDO_NAV_ID = R.id.partidoNav;
    private static final int DEPUTADO_NAV_ID = R.id.deputadoNav;
    private static final int PERFIL_NAV_ID = R.id.perfilNav;
    private BottomNavigationView bottomNavigationView;
    private ProgressBar progressBar;

    private Button btVoltar;
    private TextView txtNomeDeputado;
    private TextView txtNascimento;
    private TextView txtPartidoDeputado;
    private TextView txtUfDeputado;
    private TextView txtMunicipio;
    private ImageView imgDeputado;

    private final static String URL = "https://dadosabertos.camara.leg.br/api/v2/";
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biografia_deputado);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.deputadoNav);
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

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btVoltar = findViewById(R.id.btVoltarDeputado);
        imgDeputado = findViewById(R.id.imgDeputado);
        txtNomeDeputado = findViewById(R.id.txtNomeDeputado);
        txtNascimento = findViewById(R.id.txtNascimento);
        txtPartidoDeputado = findViewById(R.id.txtSiglaPartido);
        txtUfDeputado = findViewById(R.id.txtUfDeputado);
        txtMunicipio = findViewById(R.id.txtMunicipio);
        progressBar = findViewById(R.id.progressBarBiografiaDeputado);

        String id = getIntent().getStringExtra("id");

        consultaResposta(id);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ListandoDeputados.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void consultaResposta(String id) {
        RestService restService = retrofit.create(RestService.class);

        Call<ApiBiografia> call = restService.consultarBiografia(id);

        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<ApiBiografia>() {
            @Override
            public void onResponse(Call<ApiBiografia> call, Response<ApiBiografia> response) {
                if (response.isSuccessful()) {
                    ApiBiografia resposta = response.body();

                    Deputado deputado = resposta.getDados();

                    progressBar.setVisibility(View.GONE);

                    String urlImagem = deputado.getUrlFoto();


                    txtNomeDeputado.setText(deputado.getNomeCivil());
                    txtNascimento.setText(deputado.getDataNascimento());
                    txtPartidoDeputado.setText(deputado.getUltimoStatus().getSiglaPartido());
                    txtUfDeputado.setText(deputado.getUltimoStatus().getSiglaUf());
                    txtMunicipio.setText(deputado.getMunicipioNascimento());
                    Picasso.get().load(deputado.getUltimoStatus().getUrlFoto()).into(imgDeputado);
                }
            }

            @Override
            public void onFailure(Call<ApiBiografia> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Erro " + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                Log.e("Erro", "Falha na requisição: " + t.getMessage());
            }
        });
    }
}