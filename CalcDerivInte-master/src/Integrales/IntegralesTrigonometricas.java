/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integrales;

import java.util.ArrayList;

/**
 *
 * @author fav_0
 */
public class IntegralesTrigonometricas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<String> esp;
        esp = new ArrayList<>();

        esp.add("sen(x)");
        esp.add("cos(x)");
        esp.add("tan(x)");
        esp.add("cot(x)");
        esp.add("sec(x)");
        esp.add("csc(x)");
        esp.add("sec^2(x)");
        esp.add("csc^2(x)");
        System.out.println(correr(esp, "x"));
    }

    /**
     *
     * @param expre
     * @param dif
     * @return
     */
    public static ArrayList correr(ArrayList expre, String dif) {
        ArrayList expr = expre;
        String salida;
        int id;
        for (int i = 0; i < expr.size(); i++) {
            System.err.println(expr.get(i).toString().toLowerCase().substring(0, expr.get(i).toString().indexOf("(")));
            switch (expr.get(i).toString().toLowerCase().substring(0, expr.get(i).toString().indexOf("("))) {
                case "sec^2":
                    id = 6;
                    salida = expr.get(i).toString();
                    expr.set(i, funcion(salida, id, dif));
                    break;
                case "Csc^2":
                    id = 7;
                    salida = expr.get(i).toString();
                    expr.set(i, funcion(salida, id, dif));
                    break;
                case "sen":
                    salida = expr.get(i).toString();
                    id = 0;
                    expr.set(i, funcion(salida, id, dif));
                    break;
                case "cos":
                    salida = expr.get(i).toString();
                    id = 1;
                    expr.set(i, funcion(salida, id, dif));
                    break;
                case "tan":
                    salida = expr.get(i).toString();
                    id = 2;
                    expr.set(i, funcion(salida, id, dif));
                    break;
                case "cot":
                    salida = expr.get(i).toString();
                    id = 3;
                    expr.set(i, funcion(salida, id, dif));
                    break;
                case "sec":
                    salida = expr.get(i).toString();
                    id = 4;
                    expr.set(i, funcion(salida, id, dif));
                    break;
                case "csc":
                    id = 5;
                    salida = expr.get(i).toString();
                    expr.set(i, funcion(salida, id, dif));
                    break;

            }

        }
        expr.stream().forEach(System.out::println);
        return expr;
    }

    private static String funcion(String cad, int op, String dif) {
        String respuesta = "";
        String ExpInter, ExpExter;

        switch (op) {

            case 0: {
                ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
                ExpExter = "-cos (".concat(ExpInter) + ") + C";

                cad = "";
                cad = cad.concat(ExpExter);

                cad = Verificar(ExpInter, dif, cad);

                respuesta = (cad);
            }
            break;

            case 1: {
                ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
                ExpExter = "sen (".concat(ExpInter) + ") + C";

                cad = "";
                cad = cad.concat(ExpExter);

                cad = Verificar(ExpInter, dif, cad);

                respuesta = (cad);
            }
            break;

            case 2: {
                ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
                ExpExter = "-ln |cos(".concat(ExpInter) + ")| + C";

                cad = "";
                cad = cad.concat(ExpExter);

                cad = Verificar(ExpInter, dif, cad);

                respuesta = (cad);
            }
            break;

            case 3: {
                ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
                ExpExter = "ln |sen(".concat(ExpInter) + ")| + C";

                cad = "";
                cad = cad.concat(ExpExter);

                cad = Verificar(ExpInter, dif, cad);

                respuesta = (cad);
            }
            break;

            case 4: {
                ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
                ExpExter = "ln |sec(".concat(ExpInter) + ") + " + "tan(".concat(ExpInter) + ")| + C";

                cad = "";
                cad = cad.concat(ExpExter);

                cad = Verificar(ExpInter, dif, cad);

                respuesta = (cad);
            }
            break;

            case 5: {
                ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
                ExpExter = "-ln |csc(".concat(ExpInter) + ") " + "cot(".concat(ExpInter) + ")| + C";

                cad = "";
                cad = cad.concat(ExpExter);
                respuesta = (cad);
            }
            break;

            case 6: {
                ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
                ExpExter = "tan (".concat(ExpInter) + ") + C";

                cad = "";
                cad = cad.concat(ExpExter);

                cad = Verificar(ExpInter, dif, cad);

                respuesta = (cad);
            }
            break;

            case 7: {
                ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
                ExpExter = "-cot (".concat(ExpInter) + ") + C";

                cad = "";
                cad = cad.concat(ExpExter);

                cad = Verificar(ExpInter, dif, cad);

                respuesta = (cad);
            }
            break;
        }
        return respuesta;
    }

    private static String Verificar(String ExpInter, String dif, String cad) {
        if (ExpInter.length() > 1 || !ExpInter.equals(dif)) {
            cad = "";
//            System.out.println("Integrales.IntegralesTrigonometricas.funcion()");
        }
        return cad;
    }

    private static String sen(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "-cos (".concat(ExpInter) + ") + C";

        cad = "";
        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String cos(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "sen (".concat(ExpInter) + ") + C";

        cad = "";
        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String tan(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "-ln |cos(".concat(ExpInter) + ")| + C";

        cad = "";
        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String cot(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "ln |sen(".concat(ExpInter) + ")| + C";

        cad = "";
        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String sec(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "ln |sec(".concat(ExpInter) + ") + " + "tan(".concat(ExpInter) + ")|";

        cad = "";
        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String csc(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "-ln |csc(".concat(ExpInter) + ") " + "cot(".concat(ExpInter) + ")| + C";

        cad = "";
        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String sec2(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "tan (".concat(ExpInter) + ") + C";

        cad = "";
        cad = cad.concat(ExpExter);

        return cad;
    }

    private static String csc2(String cad) {

        String ExpInter = cad.substring(cad.indexOf("(") + 1, cad.lastIndexOf(")"));
        String ExpExter = "-cot (".concat(ExpInter) + ") + C";

        cad = "";
        cad = cad.concat(ExpExter);

        return cad;
    }
}
