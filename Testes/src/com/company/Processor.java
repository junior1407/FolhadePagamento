package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Aluno IC on 14/08/2017.
 */
public class Processor {


    public Scanner scannerint;
    public Scanner scannerFloat;
    public Scanner scannerString;

    public Processor() {
       scannerString=new Scanner(System.in);
       scannerFloat= new Scanner(System.in);
       scannerint = new Scanner(System.in);
    }


    public int getInteger(String message) throws InputMismatchException{

        try {
            return scannerint.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.println(message);
            scannerint.nextLine();
            return getInteger(message);
        }
    }

    public float getFloat(String message) throws InputMismatchException{
        try {
            return scannerFloat.nextFloat();
        }
        catch (InputMismatchException e)
        {
            System.out.println(message);
            scannerFloat.nextLine();
            return getFloat(message);
        }
    }
    public String getString(String message, Boolean necessary) throws InputMismatchException{

        try {
            String temp = scannerString.nextLine();
            if ((temp.isEmpty()|| temp==null) && (necessary))
            {
                throw new IllegalArgumentException("String is empty");
            }
            return temp;
        }
        catch (InputMismatchException e)
        {
            System.out.println(message);
        //    scannerString.nextLine();
            return getString(message,necessary);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("The string can't be empty!");
      //      scannerString.nextLine();
            return getString(message,necessary);
        }
    }

}
