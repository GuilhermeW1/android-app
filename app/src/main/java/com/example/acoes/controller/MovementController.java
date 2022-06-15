package com.example.acoes.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.acoes.database.DadosOpenHelper;
import com.example.acoes.database.Tables;
import com.example.acoes.models.Acao;
import com.example.acoes.models.Movement;
import com.example.acoes.tools.Tools;

import java.util.ArrayList;


public class MovementController {

    private SQLiteDatabase conexao;
    private Context context;

    public MovementController(Context context){
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;

    }

    public ArrayAdapter<Acao> acoesArrayAdapter()
    {
        ArrayList<Acao> listAcoes = new ArrayList<>();
        try{
            String sql = "select * from "+ Tables.TB_ACOES;
            Cursor res = conexao.rawQuery(sql, null);
            if(res.moveToFirst()){

                Acao obj;
                do{
                    obj = new Acao();
                    obj.setId(res.getInt(res.getColumnIndexOrThrow("id")));
                    obj.setCd_acao(res.getString(res.getColumnIndexOrThrow("cd_acao")));

                    listAcoes.add(obj);
                }while (res.moveToNext());
            }

             ArrayAdapter<Acao> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listAcoes);
            return adapter;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public boolean insert(Movement mov){
        ContentValues values = new ContentValues();
        String dataFormatada = Tools.converterData(mov.getDate(), "dd/MM/yyyy", "yyyy-MM-dd");

        try {
            values.put("id_user", mov.getId_user());
            values.put("id_acao", mov.getId_acao());
            values.put("vlr_unid", mov.getVlr_unid());
            values.put("vlr_total", mov.getVlr_total());
            values.put("qntd_acoes", dataFormatada);
            values.put("data_movement", mov.getDate());

            conexao.insertOrThrow(Tables.TB_MOVIMENTCOES, null, values);
            return true;
        }catch(Exception ex){
            Tools.toastMessage("Erros ao inserir movimentacao "+ex.getMessage(), context);
            return false;
        }

    }
}
