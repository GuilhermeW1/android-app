package com.example.acoes.models;

public class Acao {
    int id;
    String cd_acao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCd_acao() {
        return cd_acao;
    }

    public void setCd_acao(String cd_acao) {
        this.cd_acao = cd_acao;
    }

    @Override
    public String toString(){return this.cd_acao;}

}
