package com.example.acoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.acoes.controller.UserController;

public class LoginActivity extends AppCompatActivity {
    Context context;
    Button btnLogin;
    EditText txtUser;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;
        txtUser = findViewById(R.id.loginActivity_txt_user);
        txtPassword = findViewById(R.id.loginActivity_txt_password);
        btnLogin = findViewById(R.id.loginActivity_bnt_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUser.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                UserController userController = new UserController(context);

                if(!user.equals("") || !password.equals("")){
                    int idUser = userController.login(user, password);
                    if(idUser >= 1){
                        Intent tela = new Intent(context, MainActivity.class);
                        tela.putExtra("id", idUser);
                        startActivity(tela);
                    }else{
                        //TODO n sei o que faz aqui
                    }
                }else{

                }


            }
        });


    }
}