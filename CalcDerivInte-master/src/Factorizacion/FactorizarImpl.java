/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factorizacion;

import Principal.ExpresionAlgebraica;

public class FactorizarImpl extends Factorizar {

    public int limite;
    public ExpresionAlgebraica[] todo;

    public void llamar(String op, String dif) {//creamos el método de implementacion de la clase factorizar
        piramide(limite);//enviamos parámetros a pirámide para sacar la ley del binomio de newton
        BinomioNewton(limite, todo, op, dif);//enviamos a resolver el binomio
    }
}
