package com.example.acoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lblValorTotalMovs;
    Button btnAddMov;
    Button btnMovHistory;
    Context context;
    int idUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblValorTotalMovs = findViewById(R.id.mainActivity_lbl_vlrMovs);
        btnAddMov = findViewById(R.id.mainActivity_btn_addMov);
        btnMovHistory = findViewById(R.id.mainActivity_btn_histMovs);
        context = MainActivity.this;

        //lblValorTotalMovs.setText(setValueTotalMovs());

        btnAddMov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent screen = new Intent(context, AddMovement.class);
                screen.putExtra("id", idUser);
                startActivity(screen);
            }
        });

        btnMovHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent tela = new Intent(context, ...);
                //startActivity(tela);
            }
        });

        //estou mandando o id do usuario que passo na hora de instanciar esta tela
        Bundle extras = getIntent().getExtras();
        try {
            if(extras != null){
                idUser = extras.getInt("id", 0);
            }
        }catch (Exception ex){
            System.out.println("erro");
        }

    }

    //TODO funcao para setar a lable de valor total de movimentacoes
    //private static String setValueTotalMovs(){

    //}
}