/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integrales;

import Principal.ExpresionAlgebraica;

/**
 *
 * @author Alexander Batista
 */
public class integralPotencia {

    static ExpresionAlgebraica[] resu;

    public static ExpresionAlgebraica[] integral_Potencia(ExpresionAlgebraica[] integrar, String Dif) {//creamos el método para resolver la ley de integrales de nla potencia que es (X^(a+1))/(a+1)
        String signo;//declaramos la variable que nos servirá como signo
        resu = integrar;//el atributo resu pasa a ser igual al parámetro a resolver integrar
        float exp, coef, res;//expresiones flotantes para los datos numéricos
        for (int i = 0; i < resu.length; i++) {//recorremos el arreglo de expresiones resu
            if (!"0".equals(resu[i].getExponente())) {//si el exponente es diferente de cero (0)
                if (!"-1".equals(resu[i].getExponente())) {//si el exponente es diferente de -1
                    exp = Float.valueOf(resu[i].getExponente());//tomamos el exponente de nuestra expresion
                    coef = Float.valueOf(resu[i].getSimbolo() + "" + resu[i].getCoeficiente());//tomamos la expresion del coeficiente junto a su signo
                    exp++;//incrementamos el exonente en 1
                    res = coef / exp;//dividimos el coeficiente con el exponente incrementado
                    if (res < 0) {//si el resultado es menor de 0
                        signo = "-";//la variable signo pasa a ser menos
                        res = (coef / exp) * -1;//y eliminamos el signo del resultado
                    } else {//si no 
                        signo = "+";//el signo es positivo
                    }
                    if (resu[i].getVariable().equals(Dif)) {//si la variable es igual al diferencial
                        resu[i] = new ExpresionAlgebraica(signo, res, resu[i].getVariable(), String.valueOf(exp));//almacenamos la cadena con su variable original y los resultados obtenidos en el proceso
                    } else {//si no
                        resu[i] = new ExpresionAlgebraica(signo, res, resu[i].getVariable().concat(Dif), String.valueOf(exp));//almacenamos el resultado obtenido junto a la variable y el diferencial

                    }
                } else {//si es menos 1
                    resu[i] = new ExpresionAlgebraica("", 0, null, null); //al no ser posible realizarlo se envía un dato nulo
                }
            } else { //si es un cero (0)
                resu[i] = new ExpresionAlgebraica(resu[i].getSimbolo(), resu[i].getCoeficiente(), resu[i].getVariable(), "1");//solo se cambia en la expresion completa el exponente por un 1
            }
        }
        return resu;//retornamos el resultado de nuestra integral
    }
}
