/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.ProcesarFunciones;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Faustino Ayala
 */
public class Producto extends CDI {

    static ArrayList Segmento = new ArrayList();
    static ArrayList Signo = new ArrayList();

    /**
     *
     */
    public static DecimalFormat DecForm = new DecimalFormat("###");

    public static ExpresionAlgebraica[] ProductoVariables(ExpresionAlgebraica mult1[], ExpresionAlgebraica mult2[]) {

        int v = 0;
        ExpresionAlgebraica[] expreAUX = new ExpresionAlgebraica[mult1.length * mult2.length];
        float coefAUX;
        String expAUX, signAUX, varAUX;
        for (ExpresionAlgebraica mult11 : mult1) {
            for (ExpresionAlgebraica mult21 : mult2) {
                mult11.setVariable(mult11.getVariable().toLowerCase());
                mult21.setVariable(mult21.getVariable().toLowerCase());
                if (mult11.getSimbolo().equals(mult21.getSimbolo())) {
                    signAUX = "+";
                    if (mult11.getVariable().equals(mult21.getVariable())) {
                        expAUX = String.valueOf(Float.valueOf(mult11.getExponente()) + Float.valueOf(mult21.getExponente()));
                        coefAUX = mult11.getCoeficiente() * mult21.getCoeficiente();
                        varAUX = mult11.getVariable();

                        expreAUX[v] = new ExpresionAlgebraica(signAUX, coefAUX, varAUX, expAUX);
                    } else {
                        coefAUX = mult11.getCoeficiente() * mult21.getCoeficiente();
                        String var;
                        if ("".equals(mult11.getVariable())) {
                            var = mult21.getVariable();
                        } else {
                            var = mult11.getVariable();
                        }
                        expreAUX[v] = new ExpresionAlgebraica(signAUX, coefAUX, var, String.valueOf(Float.valueOf(mult11.getExponente()) + Float.valueOf(mult21.getExponente())));
                    }
                } else {
                    signAUX = "-";
                    if (mult11.getVariable().equals(mult21.getVariable())) {
                        coefAUX = mult11.getCoeficiente() * mult21.getCoeficiente();
                        expAUX = String.valueOf(Float.valueOf(mult11.getExponente()) + Float.valueOf(mult21.getExponente()));
                        varAUX = mult11.getVariable();
                        expreAUX[v] = new ExpresionAlgebraica(signAUX, coefAUX, varAUX, expAUX);
                    } else {
                        coefAUX = mult11.getCoeficiente() * mult21.getCoeficiente();
                        String var;
                        if ("".equals(mult11.getVariable())) {
                            var = mult21.getVariable();
                        } else {
                            var = mult11.getVariable();
                        }
                        expreAUX[v] = new ExpresionAlgebraica(signAUX, coefAUX, var, String.valueOf(Float.valueOf(mult11.getExponente()) + Float.valueOf(mult21.getExponente())));
                    }

                }

                v++;
            }
        }

        Segmento.clear();
        Signo.clear();
        for (ExpresionAlgebraica expreAUX1 : expreAUX) {
            Segmento.add(DecForm.format(expreAUX1.getCoeficiente())
                    + expreAUX1.getVariable() + "^" + expreAUX1.getExponente());
            Signo.add(expreAUX1.getSimbolo());
        }
        if (Signo.get(0).equals("+") && Segmento.size() == Signo.size()) {
            Signo.remove(0);
        }
        return ProcesarFunciones.jeraquia(Segmento, Signo);
    }
}
