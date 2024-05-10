/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derivadas;

import Principal.CDI;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;

/**
 *
 * @author Alexander Batista
 */
public class derivadaValorAbsoluto extends CDI {

    public static ExpresionAlgebraica[] ValorAbsoluto(String Segmentos, String op, String dif) {//Se crea el método para la resolución de la ley del valor absoluto |a|/a
        String cad = Segmentos;//creamos la variable a utilizar durante el proceso y le asignamos el parámetro
        int z = 0, h, s = 0;//Controles para la posicion en el arreglo
        ExpresionAlgebraica[] valor = SintaxisExpresiones.Sintaxis(cad, op, true, dif); //Arreglo de expresiones para la resolucion de la derivada

        resultado = new ExpresionAlgebraica[valor.length + (llenado.length * 2) + 2];//Instanciamos el arreglo resultado ubicado en la clase CDI
        for (int i = 0; i < llenado.length; i++) {//recorremos el arreglo simplificado
            resultado[i] = new ExpresionAlgebraica(llenado[i].getSimbolo(), llenado[i].getCoeficiente(), llenado[i].getVariable(), llenado[i].getExponente());//asignamos la simplificación al resultado
            z++;//incrementamos la posicion para almacenarla y ser utilizada en otro proceso
        }
        resultado[z] = new ExpresionAlgebraica("/", 0, null, null);//asignamos el simbolo / en la posicion z
        z++;//incrementamos la posicion
        for (ExpresionAlgebraica llenado1 : llenado) {//recorremos el arreglo simplificado
            resultado[z] = new ExpresionAlgebraica("+", llenado1.getCoeficiente(), llenado1.getVariable(), llenado1.getExponente());//asignamos su valor absoluto
            z++;//incrementamos la posicion z
            s++;//incrementamos la posicion s
        }
        resultado[z] = new ExpresionAlgebraica("&", 0, null, null);//asignamos u espacio
        z++;//incrementamos a z
        h = z;//igualamos la posicion h al valor de z
        s = 0;//reiniciamos el contador s
        for (int i = z; i < valor.length + h; i++) {
            resultado[i] = new ExpresionAlgebraica(valor[s].getSimbolo(), valor[s].getCoeficiente(), valor[s].getVariable(), valor[s].getExponente());//tomamos los datos del arreglo valor
            s++;//incrementamos la posicion s
            z++;//incrementamos la posicion z
        }

        return resultado;//retornamos el resultado final
    }

}
