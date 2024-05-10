/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.util.ArrayList;

/**
 *
 * @author Alexander Batista
 */
public class Segmentador extends CDI {

    public static ArrayList resultadosFinales = new ArrayList();
    public static ArrayList Procedimiento = new ArrayList();
    public static ArrayList Resultado = new ArrayList();
    public static ArrayList Cosientes = new ArrayList();//Arreglo para guardar los cosientes   
    public static ArrayList Retorno = new ArrayList();
    public static boolean segmentar = true;

    public static ArrayList Segmentar(String Cad, ArrayList Segmentos, ArrayList Signos, String Diferencial, String Operacion) {//metodo para separar por partes y mandar a derivao o a integrar
        String cadena = "";//Cadena para almacenar la fincion a resolver
        int pos = 0;//posicion del arreglo signos
        int lenth = Segmentos.size();
        for (int i = 0; i < lenth; i++) {//recorremos el arreglo de segmentos
            if (Signos.size() == Segmentos.size() && i == 0) {//en caso de que sean de mismo tamaño
                cadena = Signos.get(pos).toString().concat(Segmentos.get(i).toString());//agregamos el signo a la expresion
                pos++;
            } else if (Cad.charAt(i) == '-') {//si el primer caracter es el signo negativo
                cadena = Signos.get(i).toString().concat(Segmentos.get(i).toString());//indica que la primera expresion lleva el signo almacenado en la posicion i
            } else if (Segmentos.get(i).toString().charAt(i) == '(') {//si la expresion comienza comparéntesis
                if (Segmentos.get(i + 1).toString().charAt(i) == '(') {//y la siguiente por igual
                    cadena = Segmentos.get(i).toString().concat(Segmentos.get(i + 1).toString());//se pasan ambas posiciones concatenadas
                } else {//si no
                    cadena = Segmentos.get(i).toString().substring(Segmentos.get(i).toString().indexOf("("), Segmentos.get(i).toString().lastIndexOf(")"));//la expresion se pasa sin los paréntesis debido a que es un cosiente
                }
            } else {//si no
                if (pos < Signos.size()) {//si signos aún tiene datos
                    cadena = Signos.get(pos).toString().concat(Segmentos.get(i).toString());//tomamos la cadena completa
                } else {
                    cadena = Segmentos.get(i).toString();
                }
            }
            segmentar = false;
            resultadosFinales = CDI.CDIMaster(cadena, Operacion, Diferencial);//enviamos a procesar el segmento optenido
            for (int j = 0; j < resultadosFinales.size(); j++) {//recorremos el arreglo del resultado
                if (resultadosFinales.get(j) != "&&&") {//si no es igual a la ocurrencia anterior
                    Procedimiento.add(resultadosFinales.get(j));//se agregan los datos al procedimiento
                } else {//si no
                    int c = j++;//tomamos la posicion j incrementada en 1
                    for (int k = c; k < resultadosFinales.size(); k++) {//recorremos el arreglo desde c hasta el final
                        if (!resultadosFinales.get(k).toString().equals("&&&")) {
                            Resultado.add(resultadosFinales.get(k));//y se le agrega al resultado
                        }
                    }
                    break;//rompemos
                }
            }
            CDI.expz.clear();//limpiamos los arreglos de resulrados en CDI
            CDI.resultados.clear();
        }
        
        for (int i = 0; i < Resultado.size(); i++) {//recorremos el arreglo del resultado
            if (Resultado.get(i).toString().contains("─")) {//si existe el indicador del cosiente
                Cosientes.add(Resultado.get(i));//se almacenan en el arreglo cosientes
                Resultado.set(i, "");//y limpiamos la posicion en resultado
            }
        }
        cadena = "\n";//limpiamos la cadena con un sqalto de línea
        for (Object Resultado1 : Resultado) {
            //recorremos nuevamente el arreglo
            if (!Resultado1.toString().equals("")) {//si no esta vacio el arreglo
                cadena = cadena.concat(Resultado1.toString());//agregamos las expresiones a la cadena
            }
        }
        String cosientes = "";
        if (!Cosientes.isEmpty()) {
            for (int i = 0; i < cadena.length() + 1; i++) {
                cosientes = cosientes.concat(" ");
            }
            cosientes = cosientes.concat(Cosientes.get(0).toString().substring(0, Cosientes.get(0).toString().indexOf("─") - 1));
            cosientes = cosientes.concat(" ");
            if (Cosientes.size() > 0) {
                for (int i = 1; i < Cosientes.size(); i++) {
                    cosientes = cosientes.concat(Cosientes.get(i).toString().substring(0, Cosientes.get(i).toString().indexOf("─") - 1).concat(" "));
                }
            }
            cosientes = cosientes.concat(cadena);
            cosientes = cosientes.concat(" ");
            cosientes = cosientes.concat(Cosientes.get(0).toString().substring(Cosientes.get(0).toString().indexOf("─"), Cosientes.get(0).toString().lastIndexOf("─") + 1).concat(" "));
            if (Cosientes.size() > 0) {
                for (int i = 1; i < Cosientes.size(); i++) {
                    cosientes = cosientes.concat(Cosientes.get(i).toString().substring(Cosientes.get(0).toString().indexOf("─"), Cosientes.get(0).toString().lastIndexOf("─") + 1) + " ");
                }
            }
            cosientes = cosientes.concat("\n");
            for (int i = 0; i < cadena.length() + 1; i++) {
                cosientes = cosientes.concat(" ");
            }
            cosientes = cosientes.concat(Cosientes.get(0).toString().substring(Cosientes.get(0).toString().lastIndexOf("─") + 2, Cosientes.get(0).toString().length()));
            cosientes = cosientes.concat(" ");
            if (Cosientes.size() > 0) {
                for (int i = 1; i < Cosientes.size(); i++) {
                    cosientes = cosientes.concat(Cosientes.get(i).toString().substring(Cosientes.get(0).toString().lastIndexOf("─") + 2, Cosientes.get(0).toString().length()).concat(" "));
                }
            }
        } else {
            cosientes = cadena;
        }
        Procedimiento.stream().forEach((Procedimiento1) -> {
            if (!Procedimiento1.toString().equals("&&&")) {
                Retorno.add(Procedimiento1);
            }
        });
        Retorno.add("&&&");
        
        Retorno.add(cosientes);
        return Retorno;
    }

}
