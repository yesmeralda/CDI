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
public class integralProducto extends CDI {

    static ExpresionAlgebraica[] mult1;
    static ExpresionAlgebraica[] mult2;
    static ExpresionAlgebraica[] res;

    public static ExpresionAlgebraica[] integral_producto(ArrayList Segmentos, String Dif) {//creamos el método para resolver la ley de integracion del producto se simplifica la expresion y se integra el resultado
        String cad = "";//declaramos la cadena que nos servirá para el resultado final
        for (int i = 0; i < Segmentos.size() - 1; i++) {//recorremos el arreglo de segmentos
            mult1 = SintaxisExpresiones.Sintaxis(Segmentos.get(i).toString().substring(1, Segmentos.get(i).toString().length() - 1), "i", false, Dif);//mandamos a simplificar la primera expresion

            mult2 = SintaxisExpresiones.Sintaxis(Segmentos.get(i + 1).toString().substring(1, Segmentos.get(i + 1).toString().length() - 1), "i", false, Dif);//mandamos a simplificar la segunda expresion

            res = Operaciones.Producto.ProductoVariables(mult1, mult2);//hacemos el producto de las dos expresiones
            cad = "(";//le asignamos un parentecis a la cadena
            for (ExpresionAlgebraica re : res) {//recorremos el arreglo que contiene el resultado
                cad = cad + re.getSimbolo() + re.getCoeficiente() + re.getVariable() + "^" + re.getExponente();//lo concarenamos en la cadena
            }
            if (cad.charAt(1) == '+') {//si el simbolo despues del paréntesis es +
                cad = "(" + cad.substring(2, cad.length());//eliminamos el símbolo
            }
            cad = cad + ")";//cerramos el paréntecis
            Segmentos.set(i, "");//limpiamos la posicion 1
            Segmentos.set(i + 1, cad);//asignamos el resultadop a la posicion 2 y continúa así hasta que termine de recorrer el arreglo de segmentos
        }
        cad = cad.substring(1, cad.length() - 1); //sacamos la cadena en paréntecis

        res = integralPotencia.integral_Potencia(SintaxisExpresiones.Sintaxis(cad, "i", false, Dif), Dif);//integramos la cadena simplificada
        return res;//retornamos el resultado
    }

}
