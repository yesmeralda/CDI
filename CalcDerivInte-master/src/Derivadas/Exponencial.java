/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import Principal.ExpresionAlgebraica;
import Principal.CDI;
import Principal.SintaxisExpresiones;

/**
 *
 * @author Alexander Batista
 */
public class Exponencial extends CDI {

    static ExpresionAlgebraica[] exp;
    static ExpresionAlgebraica[] expr;

    public static ExpresionAlgebraica[] exponencial(String cad, String dif) {
        int t = 0;
        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));

        String ExpExter = "e^(".concat(ExpInter) + ")";

        exp = SintaxisExpresiones.Sintaxis(ExpInter, "d", true, dif);
        expr = new ExpresionAlgebraica[exp.length + 1];

        for (int i = 0; i < exp.length; i++) {
            expr[i] = new ExpresionAlgebraica(exp[i].getSimbolo(), exp[i].getCoeficiente(), exp[i].getVariable(), exp[i].getExponente());
            t++;
        }
        expr[t] = new ExpresionAlgebraica("", 1, "e", ExpInter);

        return expr;
    }
}
