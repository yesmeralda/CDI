/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import Principal.CDI;
import Principal.ExpresionAlgebraica;

/**
 *
 * @author Alexander Batista
 */
public class derivadaPotencia extends CDI {

    public static ExpresionAlgebraica[] derivada_Potencia(ExpresionAlgebraica[] deriv, String dif) {//Método para aplicar la ley de derivación de la Potencia que dice que a^x=(a^(x-1))*(x)
        String Exponente;//variable para almacenar el exponente de la expresion
        resultado = new ExpresionAlgebraica[deriv.length];//se instancia el atributo de resultado final que esta en CDI
        boolean letra = false;//Variable para determinat que se encuentra una letra en la expresion que se esta evaluendo

        float coef;//variable para manejar el coeficiente de la expresion evaluada
        for (int i = 0; i < deriv.length; i++) {
            resultado[i] = new ExpresionAlgebraica(deriv[i].getSimbolo(), deriv[i].getCoeficiente(), deriv[i].getVariable(), deriv[i].getExponente());//colocamos la expresion dentro del arreglo de resultado
        }

        for (int i = 0; i < resultado.length; i++) {
            coef = Float.valueOf(resultado[i].getSimbolo().concat(String.valueOf(resultado[i].getCoeficiente())));//tomamos el coeficiente de la expresion
            for (int j = 0; j < resultado[i].getExponente().length(); j++) {
                Exponente = String.valueOf(resultado[i].getExponente().charAt(j));
                if ((resultado[i].getExponente().codePointAt(j) >= 97 && resultado[i].getExponente().codePointAt(j) <= 122) || (resultado[i].getExponente().codePointAt(j) >= 65 && resultado[i].getExponente().codePointAt(j) <= 90)) {
                    letra = true;//se evalua si dentro del exponente existe una letra
                }
            }
            if (!letra) {//si no existe letra en el exponente
                float exponente = Float.valueOf(resultado[i].getExponente());//asigana el exponente a una variable
                coef = coef * exponente;//multiplica el coeficiente con el exponente
                resultado[i].setExponente(String.valueOf(Float.valueOf(resultado[i].getExponente()) - 1));//se almacena el exponente nuevo
                if (coef < 0) {//si el coeficiente resultante es menor que uno
                    resultado[i].setCoeficiente(coef * -1);//se le elimina el signo a la cantidad
                    resultado[i].setSimbolo("-");//y se almacena ese simbolo en la expresion
                } else if (coef == 0) {
                    resultado[i].setCoeficiente(0);//si resulta ser 0 se asigna a suposicion
                    resultado[i].setSimbolo("+");//para evitar los datos nulo se almacena como positivo
                } else {
                    resultado[i].setCoeficiente(coef);//de no cumplir con lo anterior se pasa tal cual a su expresion
                    resultado[i].setSimbolo("+");//y el símbolo es negativo
                }
            } else {//si el exponente contiene letra
                String exponente;//se crea una variable para el exponente
                mul1 = new ExpresionAlgebraica[1];//una variable para la nueva expresion
                mul1[0] = new ExpresionAlgebraica(resultado[i].getSimbolo(), resultado[i].getCoeficiente(), resultado[i].getVariable(), "0");//se asignan los valores de la expresion
                exponente = resultado[i].getExponente();//se le asigna el exponennte de la expresion a la variable
                ExpresionAlgebraica[] term = derivadaProducto.Derivada_Producto(null, dif);//y se manda a la derivada del producto
                resultado[i] = new ExpresionAlgebraica(term[0].getSimbolo(), term[0].getCoeficiente(), term[0].getVariable(), term[0].getExponente());//y se agregan los resultados
            }
        }
        return resultado; //se retorna el valor de la derivada de la potencia

    }
}
