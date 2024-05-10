/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alexander Batista
 */
public class CDI {
//se declaran todos los atributos que se utilizaran en todo el programa

    public static ExpresionAlgebraica[] resultado;
    public static ExpresionAlgebraica[] Exp;
    public static ExpresionAlgebraica[] llenado;
    public static ExpresionAlgebraica[] mul1;
    public static ExpresionAlgebraica[] mul2;
    public static ExpresionAlgebraica[] der1;
    public static ExpresionAlgebraica[] der2;
    public static ExpresionAlgebraica[] mult2C;
    public static ExpresionAlgebraica[] producto;
    public static DecimalFormat df = new DecimalFormat("###.##");
    public static String result = "";
    public static ArrayList expz = new ArrayList();
    public static ArrayList resultados = new ArrayList();
    static boolean trigo2;
    static boolean expon;
    public static Scanner sc = new Scanner(System.in);
    private static boolean asterisco;
    public static ArrayList Comentario = new ArrayList();

    /**
     * @param cad
     * @param operacion
     * @param Dif
     * @return
     */
    public static ArrayList CDIMaster(String cad, String operacion, String Dif) {//aperturamos nuesttro método principal
        ExpresionAlgebraica[] trigo = null;//variable para las expresiones trigonometricas

        if (cad.charAt(0) == '+') {//si la cadena posee un símbolo de + delante
            cad = cad.substring(1, cad.length());//se elimina para evitar futuros errores
        }

        resultado = SintaxisExpresiones.Sintaxis(cad, operacion, true, Dif);//mandamos la cadena a derivar o integrar según sea operacion

        resultados.add("\n");//Agregamos un sqalto de línea al arreglo de resulrados que es el que nos presentara los datos en pantalla
        //aquí se comienza a sacar el resultado de la operación
        if (expz.isEmpty()) {//preguntamos si el arreglo que se llena con las trigonométricas y el Logaritmo Natural esta lleno
            if (resultado != null) {//si resultado es diferente de nulo
                resultados.add("&&&");//agregamos el indicador de que de aqui en adelante es el resultado final
                cad = "\n";//colocamos un salto en la cadena final
                for (ExpresionAlgebraica finalizado1 : resultado) {//recorremos el arreglo de resultado
                    if (finalizado1 != null) {//en el caso de que finalizado sea diferente de nulo
                        if (finalizado1.getSimbolo().equals("&")) {//si el símbolo de esa expresion es el que esdta en cuestión
                            cad = cad + "*";//agrega a la cadena un asterisco 
                        } else if (finalizado1.getSimbolo().equals("/")) {//de ser un eslash
                            cad = cad + "\n";//agrega un salto de línea
                            int len = cad.length();//y se declara la variable para almacenar la longitud de la cadena hasta el momento
                            for (int i = 0; i < len; i = i + 2) {//contamos desde 0 hasta len
                                cad = cad + "─";//y agregamos este símbolo para representar el cosiente cuantas veces cuente el for
                            }
                            cad = cad + "\n";//incertamos un salto de línea
                        } else if (finalizado1.getCoeficiente() == 0) {//de ser 0 el coeficiente
//no hacer nada
                        } else if (finalizado1.getExponente().equals("0.0")) {//si el exponente es 0
                            cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente();//solo tomar el coeficiente
                        } else if (finalizado1.getCoeficiente() * 100 == Math.round(finalizado1.getCoeficiente()) * 100) {//si el coeficiente por 100 es igual a su redondeado por 100
                            cad = cad + finalizado1.getSimbolo() + Math.round(finalizado1.getCoeficiente()) + finalizado1.getVariable() + "^" + finalizado1.getExponente();//toma la expresion con solo dos cifras significativas
                        } else {
                            cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente() + finalizado1.getVariable() + "^" + finalizado1.getExponente();//si no cumple con el filtro anterior se agrega la cadena de la expresion tal cual sale
                        }
                    }
                }
            }
        } else {//si expz esta lleno significa que es trigonoétrica o logaritmica por tanto lleva el siguiente proceso
            cad = "";//limpiamos la cadena
            String re;//y declaramos otra cadena que nos servirá para determinar la funcion a la que nos enfrentamos
            for (Object expz1 : expz) {//recorremos a expz
                re = expz1.toString().substring(0, expz1.toString().indexOf(" "));//asignamos la expresion que esta antes del espacio a re
                if (!"-cos".equals(re) && !"sen".equals(re) && !"-ln".equals(re) && !"ln".equals(re)) {//comparamos para determinar que no sea ninguna de las anteriores
                    trigo = SintaxisExpresiones.Sintaxis(re, "d", false, Dif);//en caso de ser verdad se almacena en resultado de la derivada o integral en la variable trigo
                }
                if (trigo != null) {//si trigo no es nulo
                    for (ExpresionAlgebraica finalizado1 : trigo) {//recorremos el arreglo de resultado
                        if (finalizado1 != null) {//en el caso de que finalizado sea diferente de nulo
                            if (finalizado1.getSimbolo().equals("&")) {//si el símbolo de esa expresion es el que esdta en cuestión
                                cad = cad + "*";//agrega a la cadena un asterisco 
                            } else if (finalizado1.getSimbolo().equals("/")) {//de ser un eslash
                                cad = cad + "\n";//agrega un salto de línea
                                int len = cad.length();//y se declara la variable para almacenar la longitud de la cadena hasta el momento
                                for (int i = 0; i < len; i = i + 2) {//contamos desde 0 hasta len
                                    cad = cad + "─";//y agregamos este símbolo para representar el cosiente cuantas veces cuente el for
                                }
                                cad = cad + "\n";//incertamos un salto de línea
                            } else if (finalizado1.getCoeficiente() == 0) {//de ser 0 el coeficiente
//no hacer nada
                            } else if (finalizado1.getExponente().equals("0.0")) {//si el exponente es 0
                                cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente();//solo tomar el coeficiente
                            } else if (finalizado1.getCoeficiente() * 100 == Math.round(finalizado1.getCoeficiente()) * 100) {//si el coeficiente por 100 es igual a su redondeado por 100
                                cad = cad + finalizado1.getSimbolo() + Math.round(finalizado1.getCoeficiente()) + finalizado1.getVariable() + "^" + finalizado1.getExponente();//toma la expresion con solo dos cifras significativas
                            } else {
                                cad = cad + finalizado1.getSimbolo() + finalizado1.getCoeficiente() + finalizado1.getVariable() + "^" + finalizado1.getExponente();//si no cumple con el filtro anterior se agrega la cadena de la expresion tal cual sale
                            }
                        }
                    }
                    cad = cad + expz1.toString().substring(expz1.toString().indexOf(" "), expz1.toString().length());//agregamos la expresion trigonométrica
                } else {//si trigo es null
                    cad = cad + expz1.toString();//se concatena la cadena con lo que este en el arreglo expz
                }

            }
            resultados.add("&&&");//cerramos el resultado
        }
        if (!"D".equals(operacion.toUpperCase())) {//si no es una derivada
            cad = cad + "+c";//por ser una integral se le agrega la expresion entre parentecis
        }
        if (cad.equals("")) {//si cad queda vacía
            cad = "0";//se imprime un 0
        }
        switch (cad.charAt(0)) {//se evalúa el primer signo de la cadena
            case '-':
                resultados.add(cad);//si ews negativa de agrega igual
                break;
            case '+':
                resultados.add(cad.substring(1, cad.length()));//de ser positivo se elimina el signo
                break;
            default:
                resultados.add(cad);//de lo contrario se pasa igual
                break;
        }
        resultados.add("");//se agrega un espacio al resultado
        for (Object resultado1 : resultados) {//recorremos al resultado
            for (int j = 0; j < resultado1.toString().length(); j++) {//recorremos la cadena que esta en esa posicion
                if (resultado1.toString().charAt(j) == '*') {//si existe un asterisco
                    asterisco = true;//cambiamos el atributo booleano a verdadero
                    break;//rompemos el ciclo
                }
            }
        }
        if (asterisco) {//si asterisco es verdadero
            cad = "";//limpiamos la cadena
            for (Object resultado1 : resultados) {//recorremos el resultado
                cad = cad.concat(resultado1.toString());//agregamos los datos a la cadena
            }
            cad = cad.substring(0, cad.lastIndexOf("─") + 1).concat(" (").concat(cad.substring(cad.indexOf("*") + 1, cad.length())).concat(")\n|").concat(cad.substring(cad.lastIndexOf("─") + 2, cad.indexOf("*"))).concat("|");//formateamos la cadena para que muestre el esultado esperado
            resultados.clear();//limpiamos el resultado

            resultados.add(cad);//le asignamos la variable cad
        }

        return resultados;//retornamos el resultado de nuestra operación
    }
}
