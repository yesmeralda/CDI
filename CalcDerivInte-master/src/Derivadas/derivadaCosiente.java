/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import Factorizacion.FactorizarImpl;
import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;

/**
 *
 * @author Alexander Batista
 */
public class derivadaCosiente extends CDI {

    public static ExpresionAlgebraica[] Cosiente(String op, String dif) {//Metodo para aplicar la ley de derivación del cosiente (((BA')-(AB'))/(B^2))
        ExpresionAlgebraica[] Division;//Variable para almacenar el resultado
        int h = 0; /*Esta se utiliza para controlar las posiciones en el arreglo Resultado*/

        int k; /*esta almacena el tamaño del resultado de la division*/

        String cadena = "";//
        k = (mul1.length + mul2.length) * 2;//Tamaño del resultado sin simplificarse

        resultado = new ExpresionAlgebraica[k];//instanciación del atributo de resultado ubicado en CDI
        String signo;//almacena el signo de la expresion resultante

        for (int i = 0; i < mul2.length; i++) {//se recorre el arreglo mul2 para multiplicar segun la regla BA'
            for (ExpresionAlgebraica der11 : der1) {//se recorre el arreglo para realizar el pruducto con cada expresion algebraica
                if (mul2[i].getSimbolo().equals(der1[i].getSimbolo())) {//se evalua la ley de los signo
                    signo = "+";//como plantea la ley de los signos para el producto, si son iguales el resultado es positivo
                } else {
                    signo = "-";//en caso de ser diferentes entonces es negativo
                }
                resultado[h] = new ExpresionAlgebraica(signo, mul2[i].getCoeficiente() * der11.getCoeficiente(), mul1[i].getVariable(), String.valueOf(Float.valueOf(mul2[i].getExponente()) + Float.valueOf(der11.getExponente())));
                /*Se asigna el resultado del producto de las dos posiciones del los arreglos mul2 y der1 a nuestro arreglo del resultado*/
                h++;//se incrementa la posicion para agregar el siguiente
            }
        }
        for (int i = 0; i < mul1.length; i++) {//se recorre el arreglo mul1 para multiplicar segun la regla AB'
            for (ExpresionAlgebraica der21 : der2) {//se recorre el arreglo para realizar el pruducto con cada expresion algebraica
                if (mul1[i].getSimbolo().equals(der2[i].getSimbolo()) /*&& mul1[i].getSimbolo().equals("-")*/) {//se evalua la ley de los signo
                    signo = "-";//como en la ley de esta derivada existe lo que es la resta en caso de ser iguales es + pero al haber un - cambia entonces a -
                } else {
                    signo = "+";//como al ser diferentes es - al multiplicar por el - de afuera resulta +
                }
                resultado[h] = new ExpresionAlgebraica(signo, mul1[i].getCoeficiente() * der21.getCoeficiente(), mul1[i].getVariable(), String.valueOf(Float.valueOf(mul1[i].getExponente()) + Float.valueOf(der21.getExponente())));
                /*Se asigna el resultado del producto de las dos posiciones del los arreglos mul2 y der1 a nuestro arreglo del resultado*/
                h++;//se incrementa la posicion para agregar el siguiente            
            }
        }

        FactorizarImpl fac = new FactorizarImpl();
        if (mul2.length > 1) {//en el caso de que sea factorizable
            fac.limite = 2;//asignamos límite 2 con relacion a la ley
            fac.todo = mul2;//dato a factorizar
            fac.llamar(op, dif);//se ejecuta la operacion
        } else {
            float num;//variable para tomar el exponente
            num = Float.valueOf(mul2[0].getSimbolo().concat(String.valueOf(mul2[0].getCoeficiente())));//coeficiente de la segunda expresion
            num = Float.valueOf(String.valueOf(Math.pow((double) num, 2)));//potencia cuadrada del coeficiente
            if (num < 0) {//determinacion del signo a traves de la regla del 0 donde si es meno es negativo
                mul2[0].setSimbolo("-");//se asigna el signo
                mul2[0].setCoeficiente(num * -1);//se le elimina el signo al coeficiente
            } else {
                mul2[0].setSimbolo("+");//si es mayor que 0 es positivo
                mul2[0].setCoeficiente(num);//se asigna el coeficiente
            }
            mul2[0].setExponente(String.valueOf(Integer.valueOf(mul2[0].getExponente()) * 2));//se multiplica el exponente de la expresion por la potencia que buscamos
        }
        mult2C = new ExpresionAlgebraica[llenado.length];//expresion simplificada
        for (int i = 0; i < llenado.length; i++) {
            mult2C[i] = new ExpresionAlgebraica(llenado[i].getSimbolo(), llenado[i].getCoeficiente(), llenado[i].getVariable(), llenado[i].getExponente());//se le asigna el simplificado a mul2c 
        }

        for (ExpresionAlgebraica resultado1 : resultado) {
            if (resultado1 != null) {
                cadena = cadena + resultado1.getSimbolo() + resultado1.getCoeficiente() + resultado1.getVariable() + "^" + resultado1.getExponente();//convertimos el resultado en una cadena completa del numerador
            }
        }
        if (cadena.charAt(0) == '+') {//eliminamos el signo positivo del principio (si lo tiene) de la cadena
            resultado = SintaxisExpresiones.Sintaxis(cadena.substring(1, cadena.length()), op, false, dif);//Simplificamos el resultado del numerador
        } else {
            resultado = SintaxisExpresiones.Sintaxis(cadena, op, false, dif);//si no tienen simbolo + delante se simplifica igual
        }
        for (int i = 0; i < llenado.length; i++) {
            resultado[i] = new ExpresionAlgebraica(llenado[i].getSimbolo(), llenado[i].getCoeficiente(), llenado[i].getVariable(), llenado[i].getExponente());//el simplificado del numerador se pasa al arreglo resultado
        }

        int t = 0;//variable para controlar la posicion del arreglo
        Division = new ExpresionAlgebraica[(resultado.length + mult2C.length) + 1];//se instancia el arreglo para asignarle su longitud
        for (ExpresionAlgebraica resultado1 : resultado) {
            Division[t] = new ExpresionAlgebraica(resultado1.getSimbolo(), resultado1.getCoeficiente(), resultado1.getVariable(), resultado1.getExponente());//se le asigna el valor del numerador
            t++;//incrementa la posicion
        }
        Division[t] = new ExpresionAlgebraica("/", 0, null, null);//se coloca el simbolo del cosiente
        t++;//se incrementa la variable
        for (ExpresionAlgebraica mul2C1 : mult2C) {
            Division[t] = new ExpresionAlgebraica(mul2C1.getSimbolo(), mul2C1.getCoeficiente(), mul2C1.getVariable(), mul2C1.getExponente());//le agregamos el denominador
            t++;//se incrementa la posicion
        }
        return Division;//se retorna el resultado de la derivada de nuestro cosiente
    }
}
