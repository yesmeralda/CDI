/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integrales;

import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class integralCosiente extends CDI {

    static ExpresionAlgebraica[] Expre;
    static ExpresionAlgebraica[] cos1;
    static ExpresionAlgebraica[] cos2;

    public static String cosiente(ArrayList Segmentos, boolean cos, String Dif) {//se declara el metodo para resulver la ley de integración donde x'/x = ln(x)
        String Resultado = "";//declaramos la variable resultado

        if (cos) { //si la variable cos que representa cosiente es true
            Expre = SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString(), "i", false, Dif);//se integra la expresion interna

            Resultado = "ln (";//le agregamos la expresion ln ( a la bcadena del resultado
            for (ExpresionAlgebraica finalizado1 : Expre) {//recorremos el arreglo expz par estraer el resultado de la integral
                if (finalizado1 != null) {//en el caso de que finalizado sea diferente de nulo
                    if (finalizado1.getSimbolo().equals("&")) {//si el símbolo de esa expresion es el que esdta en cuestión
                        Resultado = Resultado + "*";//agrega a la cadena un asterisco 
                    } else if (finalizado1.getSimbolo().equals("/")) {//de ser un eslash
                        Resultado = Resultado + "\n";//agrega un salto de línea
                        int len = Resultado.length();//y se declara la variable para almacenar la longitud de la cadena hasta el momento
                        for (int i = 0; i < len; i = i + 2) {//contamos desde 0 hasta len
                            Resultado = Resultado + "─";//y agregamos este símbolo para representar el cosiente cuantas veces cuente el for
                        }
                        Resultado = Resultado + "\n";//incertamos un salto de línea
                    } else if (finalizado1.getCoeficiente() == 0) {//de ser 0 el coeficiente
//no hacer nada
                    } else if (finalizado1.getExponente().equals("0.0")) {//si el exponente es 0
                        Resultado = Resultado + finalizado1.getSimbolo() + finalizado1.getCoeficiente();//solo tomar el coeficiente
                    } else if (finalizado1.getCoeficiente() * 100 == Math.round(finalizado1.getCoeficiente()) * 100) {//si el coeficiente por 100 es igual a su redondeado por 100
                        Resultado = Resultado + finalizado1.getSimbolo() + Math.round(finalizado1.getCoeficiente()) + finalizado1.getVariable() + "^" + finalizado1.getExponente();//toma la expresion con solo dos cifras significativas
                    } else {
                        Resultado = Resultado + finalizado1.getSimbolo() + finalizado1.getCoeficiente() + finalizado1.getVariable() + "^" + finalizado1.getExponente();//si no cumple con el filtro anterior se agrega la cadena de la expresion tal cual sale
                    }
                }
            }
            Resultado = Resultado + ")";//cerramos el paréntesis de nuestro resultado

        } else {//si es falso
            Resultado = "";//resultado estará vacio
        }

        return Resultado;//retornamos e resultado
    }
}
