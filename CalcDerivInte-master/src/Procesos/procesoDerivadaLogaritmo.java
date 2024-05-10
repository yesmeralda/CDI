/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesos;

import static Principal.CDI.llenado;
import static Principal.CDI.mul1;
import Principal.Enrrutar;
import Principal.ExpresionAlgebraica;
import Principal.SintaxisExpresiones;
import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class procesoDerivadaLogaritmo extends Enrrutar {

    String logn = "";//declaramos el atributo para la cadena del logaritmo natural
    int sig, v = 0, f;
    ExpresionAlgebraica[] terminos;
    ExpresionAlgebraica[] resultados2;

    public ExpresionAlgebraica[] proceso(ArrayList Segmentos, ArrayList Signos, String op, String dif) {//se creael metodo para trabajar la ley que dice ln (x) = x'/x
        if (Segmentos.size() != 1) {//si la longitud del segmento es mas que 1
            Segmentos.stream().map((Segmento) -> {//recorremos el arreglo
                logn = logn.concat(Segmento.toString());//concatenamos todo en el at5ributo de la cadena del logaritmo
                return Segmento;//retornamos el segmento
            }).filter((_item) -> (!Signos.isEmpty() && sig < Signos.size())).map((_item) -> {//en caso de que exista un signo dentro del arreglo signo
                logn = logn.concat(Signos.get(sig).toString());//lo concatenamos a la cadena de logn
                return _item;//se retorna el item encontrado
            }).forEach((_item) -> {//al encontrar un signo 
                sig++;//se incrementa el contador para los signos
            });
        } else {//si no
            logn = Segmentos.get(0).toString();//se pasa el único ele4mento que posee el arreglo segmento
            if (!Signos.isEmpty()) {//en caso de que haya un signo 
                logn=Signos.get(0).toString().concat(logn);//se le coloca delante a la expresion
            }
        }
        if (Segmentos.get(0).toString().charAt(3) == '(') {//preguntamos si hay un parentesis 
            Segmentos.set(0, Segmentos.get(0).toString().substring(3, Segmentos.get(0).toString().length()));//se toma lo que esta dentro del parentecis
            Segmentos.set(Segmentos.size() - 1, Segmentos.get(Segmentos.size() - 1).toString().substring(0, Segmentos.get(Segmentos.size() - 1).toString().length() - 1));//se toma la expresión entre paréntesis
            String cad = ""; //se crea la variable para almacenar la cadena
            for (int i = 0; i < Segmentos.size(); i++) {//recorremos el arreglo de segmentos
                cad = cad + Segmentos.get(i).toString();//concatenamos la cadena con la ecpresion dentro de segmentos
                if (!Signos.isEmpty() && i < Signos.size()) {//se pregunta si hay un signo
                    cad = cad + Signos.get(i);//se coloca el signo en la cadena
                }
            }
            resultados2 = SintaxisExpresiones.Sintaxis(cad, op, true, dif);//envia la cadena a derivar y se almacena en el arreglo resultado
            v = resultados2.length + 1;//tomamos la longitud mas uno del resultado
        } else {//si no hay parentesis
            resultados2 = SintaxisExpresiones.Sintaxis(logn.substring(3, logn.length() - 1), op,true, dif);//mandamos la cadena a derivar y la almacenamos en resultado
            v = resultados2.length + llenado.length + 1;//tomamos la longitud de la derivada y del simplificado mas uno
        }
        terminos = new ExpresionAlgebraica[v];//se instancia el arreglo terminos con una longitud de v
        for (int j = 0; j < resultados2.length; j++) {//recorremos el arreglo resultado2
            terminos[j] = new ExpresionAlgebraica(resultados2[j].getSimbolo(), resultados2[j].getCoeficiente(), resultados2[j].getVariable(), resultados2[j].getExponente());//le asignamos los datos a terminos
        }
        terminos[resultados2.length] = new ExpresionAlgebraica("/", 0, null, null);//colocamos el simbolo de cosiente
        f = resultados2.length + 1;//asignamos la longitud al contador
        if (mul1 != null) {//si mul1 es diferente de nulo
            for (ExpresionAlgebraica termino : terminos) {//recorremos el arreglo de expresiones terminos
                expz.add(termino.getSimbolo() + termino.getCoeficiente() + termino.getVariable() + "^" + termino.getExponente());//se lo agregamos al ArrayList
            }
            Segmentos.stream().forEach((Segmento) -> {
                expz.add(Segmento);//y a traves de un for each le asignamos los valores de segmento
            });
        } else {//de ser nulo
            for (ExpresionAlgebraica llenado1 : llenado) {//recorremos el arreglo de expresiones llenado
                terminos[f] = new ExpresionAlgebraica(llenado1.getSimbolo(), llenado1.getCoeficiente(), llenado1.getVariable(), llenado1.getExponente());//y agregamos esas expresiones al resultado terminos
                f++;//e incrementamos el contador
            }
        }
                
        return terminos;//retornamos el resultado final
    }
}
