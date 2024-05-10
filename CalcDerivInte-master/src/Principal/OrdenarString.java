/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author fav_0
 */
public class OrdenarString extends CDI {

    private static void arregla(String[] pru) {
        String aux;
        //   Arrays.sort(pru);
        quicksort(pru, 0, pru.length - 1);
        //  burbuja(pru);
        for (String pru1 : pru) {
            System.out.print(pru1 + " -> ");
        }
        System.out.println("");
        for (String pru1 : pru) {
            System.out.println(pru1.hashCode() + " -> " + pru1 + " ");
        }
    }

    private static void burbuja(String[] a) {
        String aux;
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j].hashCode() > a[j + 1].hashCode()) {
                    aux = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = aux;
                }
            }
        }
    }

    public static void quicksort(String[] A, int izq, int der) {
        int i, j;
        String X, aux;
        i = izq;
        j = der;
        X = A[(izq + der) / 2];

        do {
            while ((A[i].hashCode() < X.hashCode()) && (j <= der)) {
                i++;
            }

            while ((X.hashCode() < A[j].hashCode()) && (j > izq)) {
                j--;
            }

            if (i <= j) {
                aux = A[i];
                A[i] = A[j];
                A[j] = aux;
                i++;
                j--;
            }

        } while (i <= j);

        if (izq < j) {
            quicksort(A, izq, j);
        }
        if (i < der) {
            quicksort(A, i, der);
        }
    }
}
