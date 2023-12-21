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
import com.ifsul.deputados_api.R;
import com.ifsul.deputados_api.services.RestService;
import com.ifsul.deputados_api.entities.Partido;
import com.ifsul.deputados_api.requests.ApiBiografiaPartidos;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BiografiaPartido extends AppCompatActivity {

    private static final int PARTIDO_NAV_ID = R.id.partidoNav;
    private static final int DEPUTADO_NAV_ID = R.id.deputadoNav;
    private static final int PERFIL_NAV_ID = R.id.perfilNav;
    private BottomNavigationView bottomNavigationView;
    private ProgressBar progressBar;

    private Button btVoltar;
    private TextView txtNomePartido;
    private TextView txtData;
    private TextView txtSigla;
    private TextView txtNumeroMembros;
    private TextView txtLider;
    private ImageView imgPartido;

    private final static String URL = "https://dadosabertos.camara.leg.br/api/v2/";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biografia_partido);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.partidoNav);
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

        btVoltar = findViewById(R.id.btVoltarPartido);
        imgPartido = findViewById(R.id.imgPartido);
        txtNomePartido = findViewById(R.id.txtNomePartido);
        txtData = findViewById(R.id.txtData);
        txtSigla = findViewById(R.id.txtSigla);
        txtNumeroMembros = findViewById(R.id.txtNumMembros);
        txtLider = findViewById(R.id.txtLider);
        progressBar = findViewById(R.id.progressBarBiografiaPartido);

        String id = getIntent().getStringExtra("id");

        consultaRespostaPartido(id);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ListandoPartidos.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void consultaRespostaPartido(String id) {
        RestService restService = retrofit.create(RestService.class);

        Call<ApiBiografiaPartidos> call = restService.consultarBiografiaPartido(id);

        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<ApiBiografiaPartidos>() {
            @Override
            public void onResponse(Call<ApiBiografiaPartidos> call, Response<ApiBiografiaPartidos> response) {
                if (response.isSuccessful()) {
                    ApiBiografiaPartidos resposta = response.body();

                    Partido partido = resposta.getDados();

                    progressBar.setVisibility(View.GONE);

                    String urlImagem = partido.getUrlLogo();
                    System.out.println(urlImagem);


                    txtNomePartido.setText(partido.getNome());
                    txtData.setText(partido.getStatus().getData());
                    txtSigla.setText(partido.getSigla());
                    txtNumeroMembros.setText("Nº de membros: " + partido.getStatus().getTotalMembros());
                    txtLider.setText("Líder: " + partido.getStatus().getLider().getNome());
                    Picasso.get().load(urlImagem).into(imgPartido);
                }
            }

            @Override
            public void onFailure(Call<ApiBiografiaPartidos> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Erro " + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                Log.e("Erro", "Falha na requisição: " + t.getMessage());
            }
        });
    }
}