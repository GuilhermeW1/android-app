package com.example.acoes.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.acoes.database.DadosOpenHelper;
import com.example.acoes.database.Tables;

public class UserController {
    private SQLiteDatabase conexao;
    private Context context;

    public UserController(Context context){
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;

    }

    public boolean login(String login, String password){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(Tables.TB_USUARIOS);
        sql.append(" WHERE usuario = '"+ login +"'");
        sql.append(" AND senha = '"+password+"'");

        Cursor res = conexao.rawQuery(sql.toString(), null);
        if(res.moveToNext()){
            return true;
        }
        return false;
    }
    /*

     public Language buscar(int id){
     try{
     Language objeto = null;

     StringBuilder sql = new StringBuilder();
     sql.append("SELECT * FROM ");
     sql.append(Tables.TB_LINGUAGENS);
     sql.append(" WHERE id = '"+ id +"'");

     Cursor resultado = conexao.rawQuery(sql.toString(), null);
     if(resultado.moveToNext()){
     objeto = new Language();

     objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
     objeto.setName(resultado.getString(resultado.getColumnIndexOrThrow("name")));
     objeto.setDescription(resultado.getString(resultado.getColumnIndexOrThrow("description")));
     objeto.setFavorito(resultado.getInt(resultado.getColumnIndexOrThrow("favorito")));
     objeto.setNota(resultado.getInt(resultado.getColumnIndexOrThrow("nota")));
     }

     return objeto;

     }catch (Exception ex){
     Tools.toastMessage(ex.getMessage(), context);
     Log.e("ERRO BUSCAR CONTROLLER", ex.getMessage());
     return null;
     }
     }

     public boolean incluir(Language objeto){
     try{

     ContentValues valores = new ContentValues();
     valores.put("name", objeto.getName());
     valores.put("description", objeto.getDescription());
     valores.put("favorito", objeto.getFavorito());
     valores.put("nota", objeto.getNota());

     ContentValues test = valores;

     conexao.insertOrThrow(Tables.TB_LINGUAGENS, null, valores);

     return true;

     }catch (Exception ex){
     Tools.toastMessage(ex.getMessage(), context);
     Log.e("ERRO INCLUIR CONTROLLER", ex.getMessage());
     return false;
     }
     }

     public boolean alterar(Language objeto){
     try{

     ContentValues valores = new ContentValues();
     valores.put("name", objeto.getName());
     valores.put("description", objeto.getDescription());
     valores.put("favorito", objeto.getFavorito());
     valores.put("nota", objeto.getNota());

     String[] parametros = new String[1];
     parametros[0] = String.valueOf(objeto.getId());

     conexao.update(Tables.TB_LINGUAGENS, valores, "id = ?" , parametros);

     return true;

     }catch (Exception ex){
     Tools.toastMessage(ex.getMessage(), context);
     Log.e("ERRO ALTERAR CONTROLLER", ex.getMessage());
     return false;
     }
     }

     public boolean excluir(int id){
     try{

     String[] parametros = new String[1];
     parametros[0] = String.valueOf(id);

     conexao.delete(Tables.TB_LINGUAGENS, "id = ?", parametros);

     return true;

     }catch (Exception ex){
     Tools.toastMessage(ex.getMessage(), context);
     Log.e("ERRO EXCLUIR CONTROLLER", ex.getMessage());
     return false;
     }
     }

     public ArrayList<Language> lista(){

     ArrayList<Language> listagem = new ArrayList<>();
     try{

     StringBuilder sql = new StringBuilder();
     sql.append("SELECT * FROM ");
     sql.append(Tables.TB_LINGUAGENS);

     Cursor resultado = conexao.rawQuery(sql.toString(), null);
     if(resultado.moveToFirst()){

     Language objeto;
     do{
     objeto = new Language();

     objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
     objeto.setName(resultado.getString(resultado.getColumnIndexOrThrow("name")));
     objeto.setDescription(resultado.getString(resultado.getColumnIndexOrThrow("description")));
     objeto.setFavorito(resultado.getInt(resultado.getColumnIndexOrThrow("favorito")));
     objeto.setNota(resultado.getInt(resultado.getColumnIndexOrThrow("nota")));

     listagem.add(objeto);

     }while (resultado.moveToNext());

     }

     return listagem;

     }catch (Exception ex){
     Tools.toastMessage(ex.getMessage(), context);
     Log.e("ERRO LISTA CONTROLLER", ex.getMessage());
     return listagem;
     }
     }



     }


     */
}
