package com.company;

/**
 * Created by alunoic on 28/07/17.
 */
public class CheckInOut {
    private int dia, mes, ano, hora_ini, hora_fim, min_ini,min_fim;


    public CheckInOut(int dia, int mes, int ano, int hora_ini, int hora_fim, int min_ini, int min_fim) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora_ini = hora_ini;
        this.hora_fim = hora_fim;
        this.min_ini = min_ini;
        this.min_fim = min_fim;

    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(int hora_ini) {
        this.hora_ini = hora_ini;
    }

    public int getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(int hora_fim) {
        this.hora_fim = hora_fim;
    }

    public int getMin_ini() {
        return min_ini;
    }

    public void setMin_ini(int min_ini) {
        this.min_ini = min_ini;
    }

    public int getMin_fim() {
        return min_fim;
    }

    public void setMin_fim(int min_fim) {
        this.min_fim = min_fim;
    }




}
