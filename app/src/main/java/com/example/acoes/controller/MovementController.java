package com.example.acoes.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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

    public MovementController(Context context) {
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;

    }

    public ArrayAdapter<Acao> acoesArrayAdapter() {
        ArrayList<Acao> listAcoes = new ArrayList<>();
        try {
            String sql = "select * from " + Tables.TB_ACOES;
            Cursor res = conexao.rawQuery(sql, null);
            if (res.moveToFirst()) {

                Acao obj;
                do {
                    obj = new Acao();
                    obj.setId(res.getInt(res.getColumnIndexOrThrow("id")));
                    obj.setCd_acao(res.getString(res.getColumnIndexOrThrow("cd_acao")));

                    listAcoes.add(obj);
                } while (res.moveToNext());
            }

            ArrayAdapter<Acao> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listAcoes);
            return adapter;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public boolean insert(Movement mov) {
        ContentValues values = new ContentValues();
        String dataFormatada = Tools.converterData(mov.getDate(), "dd/MM/yyyy", "yyyy-MM-dd");

        try {
            values.put("id_user", mov.getId_user());
            values.put("id_acao", mov.getId_acao());
            values.put("vlr_unid", mov.getVlr_unid());
            values.put("vlr_total", mov.getVlr_total());
            values.put("qntd_acoes", mov.getQntd_total_acoes());
            values.put("data_movement", dataFormatada);

            conexao.insertOrThrow(Tables.TB_MOVIMENTCOES, null, values);
            return true;
        } catch (Exception ex) {
            Tools.toastMessage("Erros ao inserir movimentacao " + ex.getMessage(), context);
            return false;
        }

    }

    public ArrayList<Movement> lista(int idUser) {
        ArrayList<Movement> listMovement = new ArrayList<>();
        try {
            String sql = "select * from " + Tables.TB_MOVIMENTCOES + " where id_user =" + idUser;
            Cursor res = conexao.rawQuery(sql, null);
            if (res.moveToFirst()) {

                Movement obj;
                do {
                    obj = new Movement();
                    obj.setId_mov(res.getInt(res.getColumnIndexOrThrow("id")));
                    obj.setId_user(res.getInt(res.getColumnIndexOrThrow("id_user")));
                    obj.setId_acao(res.getInt(res.getColumnIndexOrThrow("id_acao")));
                    obj.setVlr_unid(res.getFloat(res.getColumnIndexOrThrow("vlr_unid")));
                    obj.setVlr_total(res.getFloat(res.getColumnIndexOrThrow("vlr_total")));
                    obj.setQntd_total_acoes(res.getInt(res.getColumnIndexOrThrow("qntd_acoes")));
                    obj.setDate(res.getString(res.getColumnIndexOrThrow("data_movement")));


                    listMovement.add(obj);
                } while (res.moveToNext());
            }

            //ArrayAdapter<Acao> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listAcoes);
            return listMovement;
        } catch (Exception ex) {
            Log.e("ERRO LISTA CONTROLLER", ex.getMessage());
            return null;
        }
    }

    public String findAcaoById(int idAcao){
        String sql = "select * from " + Tables.TB_ACOES + " where id =" + idAcao;
        Cursor res = conexao.rawQuery(sql, null);
        String cd_acao = "";
        if(res.moveToNext()){
            return cd_acao =  res.getString(res.getColumnIndexOrThrow("cd_acao"));
        }
        return null;
    }

    public Movement findMovById(int idMov){
        String sql = "select * from " + Tables.TB_MOVIMENTCOES + " where id =" + idMov;
        Cursor res = conexao.rawQuery(sql, null);

        try{
            Movement obj = new Movement();
            if(res.moveToNext()){
                obj.setId_mov(res.getInt(res.getColumnIndexOrThrow("id")));
                obj.setId_user(res.getInt(res.getColumnIndexOrThrow("id_user")));
                obj.setId_acao(res.getInt(res.getColumnIndexOrThrow("id_acao")));
                obj.setVlr_unid(res.getFloat(res.getColumnIndexOrThrow("vlr_unid")));
                obj.setVlr_total(res.getFloat(res.getColumnIndexOrThrow("vlr_total")));
                obj.setQntd_total_acoes(res.getInt(res.getColumnIndexOrThrow("qntd_acoes")));
                obj.setDate(res.getString(res.getColumnIndexOrThrow("data_movement")));
            }
            return obj;
        }catch (Exception ex){
            Log.e("ERRO LISTA CONTROLLER", ex.getMessage());
            return null;
        }

    }

    public float getTotalMovements(int idUser){
        String sql = "select vlr_total from " + Tables.TB_MOVIMENTCOES + " where id_user =" + idUser;
        Cursor res = conexao.rawQuery(sql, null);

        float vlrTotal = 0f;

        try{
            if (res.moveToFirst()) {
                while (res.moveToNext()){
                    vlrTotal = vlrTotal + res.getFloat(res.getColumnIndexOrThrow("vlr_total"));
                }

                return vlrTotal;
            }
            return 0;
        }catch (Exception e){
            Log.e("ERRO LISTA CONTROLLER", e.getMessage());
            return 0;
        }
    }

    public boolean update(Movement mov, int idMov){
        ContentValues values = new ContentValues();
        String dataFormatada = Tools.converterData(mov.getDate(), "dd/MM/yyyy", "yyyy-MM-dd");

        try {
            values.put("id_user", mov.getId_user());
            values.put("id_acao", mov.getId_acao());
            values.put("vlr_unid", mov.getVlr_unid());
            values.put("vlr_total", mov.getVlr_total());
            values.put("qntd_acoes", mov.getQntd_total_acoes());
            values.put("data_movement", dataFormatada);

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(idMov);

            conexao.update(Tables.TB_MOVIMENTCOES, values, "id = ? ", parametros);

            return true;
        } catch (Exception ex) {
            Log.e("ERRO update CONTROLLER", ex.getMessage());
            Tools.toastMessage("Erros ao inserir movimentacao " + ex.getMessage(), context);
            return false;
        }
    }

}
