/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author Faustino Ayala
 */
public class ExpresionAlgebraica {

    private String exponente;
    private float coeficiente = 0;
    private String variable;
    private String simbolo;

    public ExpresionAlgebraica(String simbolo, float coeficiente, String variable, String exponente) {
        this.variable = variable;
        this.simbolo = simbolo;
        this.coeficiente = coeficiente;
        this.exponente = exponente;
    }

    public String getExponente() {
        return exponente;
        /**
         * @return the exponente
         */
    }

    public void setExponente(String exponente) {
        this.exponente = exponente;
        /**
         * @param exponente the exponente to set
         */
    }

    public float getCoeficiente() {
        return coeficiente;
        /**
         * @return the coeficiente
         */
    }

    public void setCoeficiente(float coeficiente) {
        this.coeficiente = coeficiente;
        /**
         * @param coeficiente the coeficiente to set
         */
    }

    public String getVariable() {
        return variable;
        /**
         * @return the variable
         */
    }

    public void setVariable(String variable) {
        this.variable = variable;
        /**
         * @param variable the variable to set
         */
    }

    public String getSimbolo() {
        return simbolo;
        /**
         * @return the simbolo
         */
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
        /**
         * @param simbolo the simbolo to set
         */
    }
}
