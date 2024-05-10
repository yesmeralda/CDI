/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class derivadaProducto extends CDI {

    public static ExpresionAlgebraica[] Derivada_Producto(ArrayList mults, String dif) {//metodo para la resolucion de la ley de derivacion referente al producto que dice: AB'+BA'
        ExpresionAlgebraica[] res1, res2, res/*resultado final*/;//variables de resultados para cada parte
        int pos;//posicion para el arreglo del resultado
        String cad = "";//Cadena para simplificar mas tarde
        for (int i = 0; i < mults.size(); i++) {
            mults.set(i, mults.get(i).toString().substring(1, mults.get(i).toString().length() - 1));//Eliminamos los paréntecis de las cadena
        }
        for (int i = 0; i < mults.size() - 1; i++) {
            der1 = SintaxisExpresiones.Sintaxis(mults.get(i).toString(), "d", true, dif);//sacamos la derivada de la primera
            mul1 = llenado;//la primera ya simplificada
            der2 = SintaxisExpresiones.Sintaxis(mults.get(i + 1).toString(), "d", true, dif);//sacamos la derivada de la segunda
            mul2 = llenado;//segunda ya simplificada
            cad = "";//confirmamos que la cadena debe de estar vacía
            res1 = Operaciones.Producto.ProductoVariables(mul1, der2);//realizamos el producto de AB'
            res2 = Operaciones.Producto.ProductoVariables(mul2, der1);//Realizamos el producto de BA'
            res = new ExpresionAlgebraica[res1.length + res2.length];//instanciamos la variable del resultado final
            for (int j = 0; j < res1.length; j++) {
                res[j] = new ExpresionAlgebraica(res1[j].getSimbolo(), res1[j].getCoeficiente(), res1[j].getVariable(), res1[j].getExponente());//almacenamos la primera parte
            }
            pos = res1.length;//asignamos la siguente posicion
            for (ExpresionAlgebraica res21 : res2) {
                res[pos] = new ExpresionAlgebraica(res21.getSimbolo(), res21.getCoeficiente(), res21.getVariable(), res21.getExponente());//asigna la segunda parte
                pos++;//incrementa el contador de posiciones
            }

            for (ExpresionAlgebraica re : res) {
                cad = cad + re.getSimbolo() + re.getCoeficiente() + re.getVariable() + "^" + re.getExponente();//le coloca el resultado a la cadena
            }
            if (cad.charAt(0) == '+') {
                cad = cad.substring(1, cad.length());//se elimina el signo +
            }
            SintaxisExpresiones.Sintaxis(cad, "d", false, dif);//se manda a simplificar la cadena final
            cad = "";//se limpia la cadena
            for (ExpresionAlgebraica llenado1 : llenado) {
                cad = cad + llenado1.getSimbolo() + llenado1.getCoeficiente() + llenado1.getVariable() + "^" + llenado1.getExponente();//guardamos en la cadena el resultado simplificado
            }
            if (cad.charAt(0) == '+') {
                cad = cad.substring(1, cad.length());//se elimina el simbolo +
            }

            mults.set(i, "");//se limpia la primera posicion
            mults.set(i + 1, cad);//se coloca el resultado en la siguiente
        }//y continuamos hasta que se acaben las expresiones a multiplicar

        return SintaxisExpresiones.Sintaxis(cad, "d", false, dif);//al terminar se retorna el resultado final para simplificar
    }
}
