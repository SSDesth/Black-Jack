/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Carlos Andres Montero
 * @version 12-10-2018
 */
public class Carta {
    /*----------Variables----------*/
    private String ID;
    private int valor;

    /*----------Constructor(es)----------*/
    public Carta() {
    }

    /*----------Setters & Getters----------*/
    /**
     * Get de la variable ID
     * @return:String
     */
    public String getID() {
        return ID;
    }

    /**
     * Set de la variable ID
     * @param ID:String
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Get de la variable valor
     * @return int
     */
    public int getValor() {
        return valor;
    }

    /**
     * Set de la variable valor
     * @param valor:int
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Override del metodo toString
     * @return String
     */
    @Override
    public String toString() {
        return "ID: " + ID + " | valor: " + valor;
    }
    
    
    
    
}
