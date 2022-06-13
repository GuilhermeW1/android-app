package com.example.acoes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Locale;

public class DadosOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1; //versão do banco de dados
    private static final String NM_BANCO = "acoes";
    private Context context;

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try {
            //cria tabela de acoes
            StringBuilder sql = new StringBuilder();
            sql.append(" create table if not exists ");
            sql.append(Tables.TB_ACOES);
            sql.append("( ");
            sql.append(" id integer primary key autoincrement, ");
            sql.append(" cd_acao varchar(10) not null ");
            sql.append(" ) ");
            db.execSQL(sql.toString());

            //cria a tabela de usuarios
            sql = new StringBuilder();
            sql.append(" create table if not exists ");
            sql.append(Tables.TB_USUARIOS);
            sql.append(" (");
            sql.append(" id integer primary key autoincrement, ");
            sql.append(" usuario varchar not null, ");
            sql.append(" senha varchar not null ");
            sql.append(" ) ");
            String teste = sql.toString();
            db.execSQL(teste);

            //cria a tabela de movimentacoes
            sql = new StringBuilder();
            sql.append(" create table if not exists ");
            sql.append(Tables.TB_MOVIMENTCOES);
            sql.append(" ( ");
            sql.append(" id integer primary key autoincrement, ");
            sql.append(" id_acao integer not null, ");
            sql.append(" id_user integer not null, ");
            sql.append(" vlr_unid float not null, ");
            sql.append(" vlr_total float not null, ");
            sql.append(" qntd_acoes integer not null, ");
            sql.append(" data_movement date, ");
            sql.append(" foreign key (id_acao) references " + (Tables.TB_ACOES) + ", ");
            sql.append(" foreign key (id_user) references " + (Tables.TB_USUARIOS) + " ");
            sql.append(" ) ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append("insert into " + Tables.TB_USUARIOS + " values(1, 'gui', '123')");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append("insert into "+Tables.TB_ACOES+ " values(1, 'MGLU3')");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append("insert into "+Tables.TB_ACOES+ " values(2, 'TOTS3') ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append("insert into "+Tables.TB_ACOES+ " values(3, 'VALE3') ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append("insert into "+Tables.TB_ACOES+ " values(4, 'CASH3') ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append("insert into "+Tables.TB_ACOES+ " values(5, 'ITUB4') ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append("insert into "+Tables.TB_ACOES+ " values(6, 'ITUB3') ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append("insert into "+Tables.TB_ACOES+ " values(7, 'OIBR3') ");
            db.execSQL(sql.toString());


        }catch (Exception ex){
            System.out.println("error" +ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
