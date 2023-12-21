package com.ifsul.deputados_api.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.ifsul.deputados_api.R;

public class Cadastrar extends AppCompatActivity {

    private ProgressBar progressBar;
    private EditText txtSenha, txtEmail, txtConfirmaSenha;
    private Button btCadastrar, btVoltar;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        progressBar = findViewById(R.id.progressBarCadastrar);
        txtEmail = findViewById(R.id.txtEmailCadastrar);
        txtSenha = findViewById(R.id.txtSenhaCadastrar);
        txtConfirmaSenha = findViewById(R.id.txtConfirmaSenha);
        btCadastrar = findViewById(R.id.btCadastrar);
        btVoltar = findViewById(R.id.btVoltarCadastrar);

        mAuth = FirebaseAuth.getInstance();


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();
                String confirmaSenha = txtConfirmaSenha.getText().toString();

                progressBar.setVisibility(View.VISIBLE);

                if(email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()){
                    Toast.makeText(Cadastrar.this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    if(!senha.equals(confirmaSenha)){
                        Toast.makeText(Cadastrar.this, "As senhas inseridas são diferentes!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                    else{
                        mAuth.createUserWithEmailAndPassword(email, senha)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);

                                        if (task.isSuccessful()) {
                                            Toast.makeText(Cadastrar.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                                            Intent i = new Intent(getApplicationContext(), Login.class);
                                            startActivity(i);
                                            finish();
                                        } else {
                                            Toast.makeText(Cadastrar.this, "Insira um email válido!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}