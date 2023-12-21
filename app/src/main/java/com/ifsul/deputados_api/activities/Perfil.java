package com.ifsul.deputados_api.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ifsul.deputados_api.R;

public class Perfil extends AppCompatActivity {

    private static final int PARTIDO_NAV_ID = R.id.partidoNav;
    private static final int DEPUTADO_NAV_ID = R.id.deputadoNav;
    private static final int PERFIL_NAV_ID = R.id.perfilNav;
    private BottomNavigationView bottomNavigationView;
    private TextView txtNome;
    private Button btLogout;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Inicializar variável
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setar o botão
        bottomNavigationView.setSelectedItemId(R.id.perfilNav);

        btLogout = findViewById(R.id.btLogout);
        txtNome = findViewById(R.id.txtNomePerfil);

        try{
            user = auth.getCurrentUser();
            txtNome.setText(user.getEmail());

        } catch(NullPointerException ex){
            txtNome.setText("");
        }

        //Implementar o listener do item selecionado
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

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
                Toast.makeText(getApplicationContext(), "Saiu da conta com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}