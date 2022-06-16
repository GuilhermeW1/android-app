package com.example.acoes.models;

public class Movement {
    int id_mov;
    int id_user;
    int id_acao;
    float vlr_unid;
    float vlr_total;
    int qntd_total_acoes;
    String date;


    public int getId_mov() {
        return id_mov;
    }

    public void setId_mov(int id_mov) {
        this.id_mov = id_mov;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_acao() {
        return id_acao;
    }

    public void setId_acao(int id_acao) {
        this.id_acao = id_acao;
    }

    public float getVlr_unid() {
        return vlr_unid;
    }

    public void setVlr_unid(float vlr_unid) {
        this.vlr_unid = vlr_unid;
    }

    public int getQntd_total_acoes() {
        return qntd_total_acoes;
    }

    public void setQntd_total_acoes(int qntd_total_acoes) {
        this.qntd_total_acoes = qntd_total_acoes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getVlr_total() {
        return vlr_total;
    }

    public void setVlr_total(float vlr_total) {
        this.vlr_total = vlr_total;
    }


    //TODO override o metodo toString para retornar valore reais
    //@Override
    @Override
    public String toString(){return date;}
}
