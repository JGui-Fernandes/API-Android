package com.ifsul.deputados_api.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ifsul.deputados_api.R;

public class Login extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText txtSenha, txtEmail;
    private Button btEnviar, btCadastrar;
    private FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), ListandoPartidos.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);
        txtSenha = findViewById(R.id.txtSenha);
        txtEmail = findViewById(R.id.txtEmail);
        btEnviar = findViewById(R.id.btEnviar);
        btCadastrar = findViewById(R.id.btCadastrarLogin);

        mAuth = FirebaseAuth.getInstance();


        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtSenha.getText().toString();

                progressBar.setVisibility(View.VISIBLE);

//                mAuth.createUserWithEmailAndPassword(email, password)
//                mAuth.signInWithEmailAndPassword(email, password)

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(Login.this, "Todos os campos devem estar preenchidos!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {



                                        Toast.makeText(Login.this, "Sucesso :)", Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(getApplicationContext(), ListandoPartidos.class);

                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(Login.this, "Email ou senha incorretos, por favor tente novamente", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Cadastrar.class);
                startActivity(i);
                finish();
            }
        });
    }
}