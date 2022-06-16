package com.example.acoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.acoes.controller.MovementController;
import com.example.acoes.models.Acao;
import com.example.acoes.models.Movement;
import com.example.acoes.tools.Tools;

import java.text.ParseException;

public class AddMovement extends AppCompatActivity {

    MovementController controller;
    Context context;
    Button btnDeletar;
    EditText qntdAcoes;
    EditText valorUnit;
    EditText dataMov;
    Spinner spinnerAcao;
    int idUser;
    int idMovement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_moviment);

        context = AddMovement.this;
        btnDeletar = findViewById(R.id.addMovementActivity_btn_deletar);
        qntdAcoes = findViewById(R.id.addMovementActivity_txt_qntdAcoes);
        valorUnit = findViewById(R.id.addMovementActivity_txt_vlrUnit);
        dataMov = findViewById(R.id.addMovementActivity_date_compra);
        spinnerAcao = findViewById(R.id.spininer);

        //seta o spinner de acoes na tala
        controller = new MovementController(context);
        spinnerAcao.setAdapter(controller.acoesArrayAdapter());

        //deicha o botao invisivel se o usuario estiver adicionando uma nova movimentacao
        btnDeletar.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            try{
                idUser = extras.getInt("id", 0);
            }catch (Exception ex){

            }
            try{
                idMovement = extras.getInt("id_movement", 0);
            }catch (Exception ex){

            }

        }

        if(idMovement > 0){
            Movement mov = new Movement();
            controller = new MovementController(context);
            mov = controller.findMovById(idMovement);

            qntdAcoes.setText(String.valueOf(mov.getQntd_total_acoes()));
            valorUnit.setText(String.valueOf(mov.getVlr_unid()));
            String data = Tools.converterData(mov.getDate(), "yyyy-MM-dd", "dd/MM/yyyy");
            dataMov.setText(data);

            for (int i = 0; i <= spinnerAcao.getAdapter().getCount(); i++) {
                Acao acao = (Acao) spinnerAcao.getItemAtPosition(i);
                if (mov.getId_acao() == acao.getId()) {
                    System.out.println("entrei");
                    spinnerAcao.setSelection(i);
                    break;
                }
            }

        }
    }



    //Funcao para inflar o menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.action_ok:

                //SALVAR
                salvar();

            case R.id.action_cancel:

                finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar(){

        //pega e valida os dados da tela
        String dataMo = dataMov.getText().toString();
        Acao acao = (Acao)spinnerAcao.getSelectedItem();
        float valorUn = 0f;
        int qntdAcao = 0;
        try{
            valorUn = Float.parseFloat(valorUnit.getText().toString());
        }catch(Exception ex){
            Log.e("ERROR", ex.getMessage());
        }
        try{
             qntdAcao = Integer.parseInt(qntdAcoes.getText().toString());
        }catch(Exception ex){
            Log.e("ERROR", ex.getMessage());
        }

        try{
            Movement mov = new Movement();
            mov.setId_user(idUser);
            mov.setId_acao(acao.getId());
            mov.setVlr_total(valorUn * qntdAcao);
            mov.setVlr_unid(valorUn);
            mov.setQntd_total_acoes(qntdAcao);
            mov.setDate(dataMo);

            controller = new MovementController(context);

            if(idMovement > 0){
                controller.update(mov, idUser);
            }

            if(controller.insert(mov)){
                Tools.toastMessage("Tudo certo por aqui", context);
            }else{
                System.out.println("fudeo");
            }


        }catch (Exception ex){
            Log.e("ERROR", ex.getMessage());
        }
    }
}