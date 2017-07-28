package com.company;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by alunoic on 28/07/17.
 */
public class Testezin {

    public static void main(String[] args) {


        ArrayList<Employee> lista1 = new ArrayList<Employee>();
        lista1.add(new MonthlyWorker ("a","a","a",0,0) );



        ArrayList<Employee>lista2= new ArrayList<Employee>(lista1);


        lista1.get(0).setName("J");
        System.out.println(lista1.get(0).getName());
        System.out.println(lista2.get(0).getName());
    }


}
