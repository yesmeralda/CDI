/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import Derivadas.derivadaCosiente;
import Derivadas.derivadaPotencia;
import Principal.Enrrutar;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class procesoDerivadaCociente extends Enrrutar {

    public ExpresionAlgebraica[] proceso(ArrayList Segmentos, ArrayList Signos, String op, String Dif) {//proceso para derivar al cosiente cuya ley dice ((BA')(AB')/(B^2))
        String cadena = "";//se declara la variable cadena
        if (Segmentos.get(0).toString().substring(0, 3).equals("ln(")) {//en caso de que la expresion sea logaritmica
            mul1 = SintaxisExpresiones.Sintaxis(Segmentos.get(0).toString().substring(4, Segmentos.get(0).toString().length() - 1), op, false, Dif);//envia a derivar lo que esta dentro del paréntecis
        } else {//de lo contrario
            mul1 = SintaxisExpresiones.Sintaxis(Segmentos.get(0).toString().substring(1, Segmentos.get(0).toString().length() - 1), op, false, Dif);//envía la expresión sin paréntesis
        }
        if (Segmentos.get(0).toString().substring(0, 3).equals("ln(")) {//en caso de que la expresion sea logaritmica
            mul2 = SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString().substring(1, Segmentos.get(1).toString().length() - 2), op, false, Dif);//envia a derivar lo que esta dentro del paréntecis
        } else {//de lo contrario
            mul2 = SintaxisExpresiones.Sintaxis(Segmentos.get(1).toString().substring(1, Segmentos.get(1).toString().length() - 1), op, false, Dif);//envía la expresión sin paréntesis
        }
        derivadaPotencia.derivada_Potencia(mul1, Dif);//sacamos la derivada de la primera
        der1 = new ExpresionAlgebraica[resultado.length];//instanciamos la variable de la derivada del primero
        for (int j = 0; j < resultado.length; j++) {//recorremos el arreglo de resultado
            der1[j] = new ExpresionAlgebraica(resultado[j].getSimbolo(), resultado[j].getCoeficiente(), resultado[j].getVariable(), resultado[j].getExponente());//asignamos el valor de la derivada al arreglo der1
        }
        derivadaPotencia.derivada_Potencia(mul2, Dif);//tomamos la derivada de la segunda
        der2 = new ExpresionAlgebraica[resultado.length];//instanciamos la variable de la derivada de la segunda
        for (int j = 0; j < resultado.length; j++) {//recorremos el arreglo de resultado
            der2[j] = new ExpresionAlgebraica(resultado[j].getSimbolo(), resultado[j].getCoeficiente(), resultado[j].getVariable(), resultado[j].getExponente());//almacenamos el valor de la derivada de la segunda
        }
        resultado = derivadaCosiente.Cosiente(op, Dif);//llamamos la case dela derivada del cosiente para retminar el proceso
        Segmentos.clear();//limpiamos el atrreglo de segmentos
        Signos.clear();//limpiamos el arreglo de sigmos
        
        return llenado;//retornamos el resultado
    }
}
