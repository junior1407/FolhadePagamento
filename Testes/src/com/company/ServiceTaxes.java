package com.company;

import java.util.Calendar;

/**
 * Created by alunoic on 28/07/17.
 */
public class ServiceTaxes {
    private int dia, mes, ano;
    private float value;

    public ServiceTaxes(int dia, int mes, int ano, float value) {

        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.value = value;
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean happennedBetween(Calendar start, Calendar end) {


        if (ano >= start.get(Calendar.YEAR) && ano <= end.get(Calendar.YEAR)) {
            if (mes >= start.get(Calendar.MONTH) && mes <= end.get(Calendar.MONTH)) {
                if (dia >= start.get(Calendar.DAY_OF_MONTH) && dia <= end.get(Calendar.DAY_OF_MONTH)) {
                    return true;
                }
            }
        }

        return false;
    }
}
