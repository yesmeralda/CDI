/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import Principal.CDI;
import java.util.ArrayList;

/**
 *
 * @author fav_0
 */
public class NewMain {

    public static ArrayList Procedimiento = new ArrayList();
    public static ArrayList Resultado = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here 
        ArrayList componentes = new ArrayList();
        String[] cadena;

        ArrayList resueltocdi;
        String expesion = ""
                + "+cos(x)"
                + "+3x^5"
                + "-tan(x)"
                + "-ln(4x^3)"
                + "-4x^7";
        String[] cad = new String[expesion.length()];
        int cont = 0, cont2 = 0;

        for (int i = 0; i < expesion.length(); i++) {//43  45

            if (expesion.charAt(i) == '+' || expesion.charAt(i) == '-') {
//                simbolos[cont] = expesion.charAt(i);
                cad[cont] = /*expesion.charAt(i) +*/ expesion.substring(cont2, i);
                //            System.out.println(CDI.CDIMaster(cad[cont], "d", "x"));
                cont++;

                cont2 = i;//+ 1;
            }

            if (!expesion.substring(cont2 + 1, expesion.length()).contains("+") && !expesion.substring(cont2 + 1, expesion.length()).contains("-")) {
//                simbolos[cont] = expesion.charAt(i);
                cad[cont] = /*expesion.charAt(cont2)+*/ expesion.substring(cont2, expesion.length());
            }
        }
        for (String cad1 : cad) {
            if (cad1 != null) {
                if (cad1.length() != 0) {
                    componentes.add(cad1);
                }
            }
        }
        cadena = new String[componentes.size()];
        for (int i = 0; i < componentes.size(); i++) {
            cadena[i] = componentes.get(i).toString();
        }
        for (int i = 0; i < cadena.length; i++) {
            System.out.println(">>" + cadena[i]);
        }

        System.out.println("\n\n\n");

        for (int i = 0; i < cadena.length; i++) {
            if (cadena[i].charAt(0) == '+') {
                cadena[i] = cadena[i].substring(1);
            }
        }
        for (int i = 0; i < cadena.length; i++) {
            System.out.println(">>" + cadena[i]);
        }
        for (String cad1 : cadena) {
            resueltocdi = CDI.CDIMaster(cad1, "d", "x");
            for (int i = 0; i < resueltocdi.size(); i++) {
                if (resueltocdi.get(i) != "&&&") {
                    Procedimiento.add(resueltocdi.get(i));
                } else {
                    int c = i++;
                    for (int j = c; j < resueltocdi.size(); j++) {
                        Resultado.add(resueltocdi.get(j));
                    }
                    break;
                }
            }
            CDI.expz.clear();
            CDI.resultados.clear();

            //+ " cont2: " + cont2 + " i:" + i);
        }
        for (int i = 0; i < Procedimiento.size(); i++) {
            System.out.println(Procedimiento.get(i));
        }
        System.out.println("\n\n\n");
        for (int i = 0; i < Resultado.size(); i++) {
            if (Resultado.get(i) != "&&&") {
                System.out.print(Resultado.get(i) + " ");
            }
        }

    }

}
