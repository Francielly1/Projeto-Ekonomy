package br.com.senaijandira.ekonomy;

import java.util.Date;

public class Ganho {

    private String categoria;
    private String valorGanho;
    private String descricao;
    private String dtnasc;
    private Integer id;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getValorGanho() {
        return valorGanho;
    }

    public void setValorGanho(String valorGanho) {
        this.valorGanho = valorGanho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(String dtnasc) {
        this.dtnasc = dtnasc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
