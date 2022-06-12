package com.example.acoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.acoes.controller.UserController;
import com.example.acoes.database.DadosOpenHelper;

public class MainActivity extends AppCompatActivity {
    Context context;
    Button btnLogin;
    EditText txtUser;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
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
                    if(userController.login(user, password)){
                        //TODO chamar nova tela
                        System.out.println("asadf");
                    }else{
                        //TODO n sei o que faz aqui
                    }
                }else{

                }


            }
        });


    }
}