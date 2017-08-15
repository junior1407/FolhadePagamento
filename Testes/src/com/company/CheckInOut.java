package com.company;

import java.util.Calendar;

/**
 * Created by alunoic on 28/07/17.
 */
public class CheckInOut {

    private Calendar entrada;
    private Calendar saida;


    public CheckInOut  getCopy()
    {
        return new CheckInOut((Calendar)entrada.clone(), (Calendar)saida.clone());
    }



    public CheckInOut(int dia, int mes, int ano, int hora_ini, int hora_fim, int min_ini, int min_fim) {
        entrada = Calendar.getInstance();
        saida = Calendar.getInstance();

        entrada.set(ano, mes, dia, hora_ini, min_ini);
        saida.set(ano, mes, dia, hora_fim, min_fim);
    }

    public Calendar getEntrada() {
        return entrada;
    }

    public void setEntrada(Calendar entrada) {
        this.entrada = entrada;
    }

    public Calendar getSaida() {
        return saida;
    }

    public void setSaida(Calendar saida) {
        this.saida = saida;
    }

    public CheckInOut(Calendar entrada, Calendar saida) {
        this.entrada = entrada;
        this.saida = saida;
    }

    public float getWorkedHours() {
        String s1= ""+Main.getDateString(entrada)+"  "+ entrada.get(Calendar.HOUR_OF_DAY)+ "   "+entrada.get(Calendar.MINUTE);
        int start_time = entrada.get(Calendar.HOUR_OF_DAY) * 60 + entrada.get(Calendar.MINUTE);
        String s2= ""+Main.getDateString(saida)+"  "+ saida.get(Calendar.HOUR_OF_DAY) +"   "+ saida.get(Calendar.MINUTE);
        int end_time = saida.get(Calendar.HOUR_OF_DAY) * 60 + saida.get(Calendar.MINUTE);
        int diferrence = end_time - start_time;
        return (float) (diferrence) / 60;
    }

    public boolean happennedBetween(Calendar start, Calendar end) {
      /*  Main.PrintCalendar(start);
        Main.PrintCalendar(end);
        Main.PrintCalendar(this.entrada);
        System.out.println("\n");
*/

        if (entrada.get(Calendar.YEAR) >= start.get(Calendar.YEAR) && entrada.get(Calendar.YEAR) <= end.get(Calendar.YEAR)) {
            if (entrada.get(Calendar.MONTH) >= start.get(Calendar.MONTH) && entrada.get(Calendar.MONTH) <= end.get(Calendar.MONTH)) {
                if (entrada.get(Calendar.DAY_OF_MONTH) >= start.get(Calendar.DAY_OF_MONTH) && entrada.get(Calendar.DAY_OF_MONTH) <= end.get(Calendar.DAY_OF_MONTH)) {
                    return true;
                }
            }
        }

        return false;
    }


}
