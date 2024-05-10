/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factorizacion;

import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class Factorizar extends CDI {

    static int[][] anterior;
    static ExpresionAlgebraica[] resuelto;

    public static void piramide(int limite) {//metodo para crear la pirámide del binomio de newton
        anterior = new int[limite + 1][limite + 1];//le asignamos la longitud de acuerdoal limite + 1
        anterior[0][0] = 1;//asignamos la primeta línea al igual que la segunda y la tercera
        anterior[1][0] = 1;
        anterior[1][1] = 1;
        for (int i = 0; i < limite + 1; i++) {//contamos desde 0 hasta el límite mas 1
            for (int j = 0; j < limite + 1; j++) {//contamos desde 0 hasta el límite mas 1
                if (i != 0) {//cuando i sea diferente de 0
                    anterior[i][j] = anterior[i - 1][j];//asignamos a en las posiciones de los ciclos el valor anterior en las posiciones i-1,j
                }
                for (int k = 0; k < i; k++) {//contamos desde 0 hasta i
                    if (k == 0) {//cuando ka sea 0
                        anterior[i][k] = 1;//se asignará un 1 en la posicion i,k
                    } else {//de lo contrario
                        anterior[i][k] = anterior[i - 1][k - 1] + anterior[i - 1][k];//vamos sumando la posicion anterior en i-1,k-1 con la misma posicion pero en k
                    }
                }
            }
            anterior[i][i] = 1;//asignamos en i,i el valor 1
        }
    }

    public static ExpresionAlgebraica[] BinomioNewton(int pos, ExpresionAlgebraica[] parte, String op, String dif) {//metodo para factorizar el binomio de newton
        resuelto = new ExpresionAlgebraica[pos + 1];//instanciamos el atributo resuelto
        int c = pos, t = 0, z = 0, signo, signo2;//variables para exponentes en la expresion y para determinar el signo de la expresion
        float coef;//variable flotante para el nuevo coeficiente
        ArrayList exponente = new ArrayList();//arreglo para guardar los exponentes
        String sig, cadena = "";//variables para signo y la cadena final
        for (int i = 0; i < pos + 1; i++) {//contamos desde 0 hasta el valor menor a posicion menos 1 
            if (parte[0].getSimbolo().equals("-")) {//cuando el signo de la expresion 1 sea menos
                signo = -1;//signo sera -1
            } else {//de lo contrario 
                signo = 1;//será solo 1
            }
            if (parte[1].getSimbolo().equals("-")) {//cuando el signo de la expresion 2 sea menos
                signo2 = -1;//signo2 sera -1
            } else {//de lo contrario
                signo2 = 1;//será solo 1
            }

            coef = anterior[pos][i] * ((int) (Math.pow((double) (parte[0].getCoeficiente() * signo), c)) * (int) (Math.pow((double) (parte[1].getCoeficiente() * signo2), t)));//producto de las expresiones para determinar el resultado
            if (coef < 0) {//evaluamos si el coeficiente es menor que 0
                sig = "-";//el signo sera negativo
                coef = coef * -1;//y le cambiamos el signo al coeficiente
            } else {//si no
                sig = "+";//el signo será positivo
            }
            resuelto[i] = new ExpresionAlgebraica(sig,
                    coef, parte[0].getVariable().concat(parte[1].getVariable()),
                    String.valueOf((Integer.parseInt(parte[0].getExponente()) * c)) + "&" + String.valueOf((Integer.parseInt(parte[1].getExponente()) * t)));//almacenamos el nuevo resultado de la expresion
            c--;//restamos el exponente mayor
            t++;//incrementamos el exponente menor
        }

        for (ExpresionAlgebraica resuelto1 : resuelto) {//recorremos el arreglo de expresiones resuelto
            for (int k = 0; k < resuelto1.getExponente().length(); k++) {//recorremos el exponente del item de resuelto
                if ('&' == resuelto1.getExponente().charAt(k)) {//si existeese simbolo
                    exponente.add(resuelto1.getExponente().substring(0, k));//tomamos el exponente hasta la posicion k y lo agregamos al array list
                    exponente.add(resuelto1.getExponente().substring(k + 1, resuelto1.getExponente().length()));//y agregamos la otra parte del exponente al arreglo
                }
            }
        }
        for (ExpresionAlgebraica resuelto1 : resuelto) {//recorremos a resuelto nuevamente
            cadena = cadena + resuelto1.getSimbolo() + resuelto1.getCoeficiente();//concatenamos todas las partes en cadena
            if (exponente.get(z).equals("0")) {//si el exponente es igual a 0
                cadena = cadena + resuelto1.getVariable().substring(1, resuelto1.getVariable().length()) + "^" + exponente.get(z + 1);//agregamos el siguiente exponente
            } else if (exponente.get(z + 1).equals("0")) {//si el siguiente es igual a 0
                cadena = cadena + resuelto1.getVariable().substring(0, 1) + "^" + exponente.get(z);//agregamos solo la posicion z
            } else {//de lo contrario
                if (!resuelto1.getVariable().substring(0, 1).equals(resuelto1.getVariable().substring(1, resuelto1.getVariable().length()))) {//si tiene dos variables
                    cadena = cadena + resuelto1.getVariable().substring(0, 1) + "^" + exponente.get(z) + resuelto1.getVariable().substring(1, resuelto1.getVariable().length()) + "^" + exponente.get(z + 1);//colocamos ambos exponentes
                } else {//si no
                    cadena = cadena + resuelto1.getVariable().substring(0, 1) + "^" + String.valueOf(Integer.valueOf(exponente.get(z).toString()) + Integer.valueOf(exponente.get(z + 1).toString()));//agregamos el exponente al final
                }
            }
            z = z + 2;//incrementamos el contador en 2
        }

        if (cadena.charAt(0) == '+') {//si la cadena tiene un + delante
            return SintaxisExpresiones.Sintaxis(cadena.substring(1, cadena.length()), op, false, dif);//lo eliminamos para evitar futuros problemas en la simplificación
        } else {//si no
            return SintaxisExpresiones.Sintaxis(cadena, op, false, dif);//la retornamos simplificada tal cual
        }
    }
}
