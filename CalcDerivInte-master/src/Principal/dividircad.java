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
public class dividircad extends OrdenarString {

    static String negalet[], posilet[], nega[], posi[];
    static int a = 0, b = 0, c = 0, d = 0;

    public static String[] correr(String[] exp) {
        divide(exp);
        posi = limpiar(posi);
        nega = limpiar(nega);
        posilet = limpiar(posilet);
        negalet = limpiar(negalet);
        return ordenado();
    }

    public static void divide(String[] exponentes) {
        nega = new String[exponentes.length];
        posi = new String[exponentes.length];
        negalet = new String[exponentes.length];
        posilet = new String[exponentes.length];
        String let;
        boolean letra = false, signo = false;
        for (String exponente : exponentes) {
            if (exponente!=null) {
                for (int j = 0; j < exponente.length(); j++) {
                    let = String.valueOf(exponente.charAt(j));
                    signo = exponente.substring(0, 1).equals("-");
                    letra = (let.hashCode() >= 65 && let.hashCode() <= 90) || (let.hashCode() >= 97 && let.hashCode() <= 122);
                }
                if (signo && letra) {
                    negalet[a] = exponente;
                    a++;
                } else if (signo && !letra) {
                    nega[b] = exponente;
                    b++;
                } else if (!signo && letra) {
                    posilet[c] = exponente;
                    c++;
                } else if (!signo && !letra) {
                    posi[d] = exponente;
                    d++;
                }
                letra = false;
                signo = false;
            }
        }
        a = 0;
        b = 0;
        c = 0;
        d = 0;
    }

    public static String[] limpiar(String[] posi) {
        ArrayList nums;
        String[] data;
        int s = 0;
        nums = new ArrayList();
        for (String posi1 : posi) {
            if (posi1 != null) {
                nums.add(posi1);
            }
        }
        data = new String[nums.size()];
        for (Object num : nums) {
            if (num != null) {
                data[s] = num.toString();
                s++;
            }
        }
        return data;
    }

    private static String[] reunir(String[] posi, String[] posilet, String[] nega, String[] negalet) {
        String aux[] = new String[(posi.length + posilet.length + nega.length + negalet.length)];
        int j = 0;
        for (int i = posi.length - 1; i >= 0; i--) {
            if (posi[i] != null) {
                aux[j] = posi[i];
                j++;
            }
        }
        for (String nega1 : nega) {
            if (nega1 != null) {
                aux[j] = nega1;
                j++;
            }
        }
        for (int i = posilet.length - 1; i >= 0; i--) {
            if (posilet[i] != null) {
                aux[j] = posilet[i];
                j++;
            }
        }
        for (String negalet1 : negalet) {
            if (negalet1 != null) {
                aux[j] = negalet1;
                j++;
            }
        }
        return aux;
    }

    private static String[] ordenado() {
        if (posi.length > 0) {
            quicksort(posi, 0, posi.length - 1);
        }
        if (posilet.length > 0) {
            quicksort(posilet, 0, posilet.length - 1);
        }
        if (nega.length > 0) {
            quicksort(nega, 0, nega.length - 1);
        }
        if (negalet.length > 0) {
            quicksort(negalet, 0, negalet.length - 1);
        }
        String[] armado;
        armado = reunir(posi, posilet, nega, negalet);
        return armado;
    }
}
