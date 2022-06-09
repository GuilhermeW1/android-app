package com.example.acoes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DadosOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 6; //versão do banco de dados
    private static final String NM_BANCO = "bancao";
    private Context context;

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //cria tabela de acoes
        StringBuilder sql = new StringBuilder();
        sql.append(" create table if not exists ");
        sql.append(Tables.TB_ACOES);
        sql.append("( ");
        sql.append(" id integer primary key autoincrement, ");
        sql.append(" cd_acao varchar(10) not null, ");
        sql.append(" constraint pk_acoes primary key (id) ");
        sql.append(" ) ");
        db.execSQL(sql.toString());

        //cria a tabela de usuarios
        sql = new StringBuilder();
        sql.append(" create table if not exists ");
        sql.append(Tables.TB_USUARIOS);
        sql.append("( ");
        sql.append(" id integer primary key autoincrement, ");
        sql.append(" usuario varchar not null, ");
        sql.append(" senha varchar not null, ");
        sql.append(" constraint pk_movimentacoes primary key (id) ");
        sql.append(" ) ");
        db.execSQL(sql.toString());

        //cria a tabela de movimentacoes
        sql = new StringBuilder();
        sql.append(" create table if not exits ");
        sql.append(Tables.TB_MOVIMENTCOES);
        sql.append(" ( ");
        sql.append(" id integer primary key autoincrement, ");
        sql.append(" id_acao integer not null, ");
        sql.append(" id_user integer not null, ");
        sql.append(" vlr_unid float not null, ");
        sql.append(" qntd_acoes integer not null, ");
        sql.append(" constraint pk_movimentacoes primary key (id), ");
        sql.append(" constraint fk_mov_acoes foreign key (id_acao) references "+(Tables.TB_ACOES)+", ");
        sql.append(" constraint fk_mov_user foreign key (id_user) references "+(Tables.TB_USUARIOS)+" ");
        sql.append(" ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
