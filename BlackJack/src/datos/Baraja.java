/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.*;

/**
 *
 * @author SSDesth
 */
public class Baraja {

    /*----------Variables----------*/
    private List<Carta> cartas;

    /*----------Constructor(es)----------*/
    public Baraja() {
        cartas = new ArrayList();
        GenerarBarajaAleatoria();
    }

    /*----------Setters & Getters----------*/
    /**
     * Get de la variable Carta
     *
     * @return List
     */
    public List<Carta> getCartas() {
        return cartas;
    }

    /**
     * Set de la variable Cartas
     *
     * @param cartas:List
     */
    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    /*----------Metodos Especializados----------*/
    /**
     * Metodo encargado de generar una Baraja y ordenarla de manera aleatoria
     */
    public void GenerarBarajaAleatoria() {
        for (int i = 1; i <= 52; i++) {
            cartas.add(GenerarCarta(i));
        }
        Collections.shuffle(cartas);
    }

    /**
     * Metodo encargado de recibir un ID y retornar una carta para la baraja
     *
     * @param eID:int
     * @return Carta
     */
    public Carta GenerarCarta(int eID) {
        Carta salida = new Carta();
        switch (eID) {
            /*-----Corazones-----*/
            case 1:
                salida.setID("C1");
                salida.setValor(11);
                break;
            case 2:
                salida.setID("C2");
                salida.setValor(2);
                break;
            case 3:
                salida.setID("C3");
                salida.setValor(3);
                break;
            case 4:
                salida.setID("C4");
                salida.setValor(4);
                break;
            case 5:
                salida.setID("C5");
                salida.setValor(5);
                break;
            case 6:
                salida.setID("C6");
                salida.setValor(6);
                break;
            case 7:
                salida.setID("C7");
                salida.setValor(7);
                break;
            case 8:
                salida.setID("C8");
                salida.setValor(8);
                break;
            case 9:
                salida.setID("C9");
                salida.setValor(9);
                break;
            case 10:
                salida.setID("C10");
                salida.setValor(10);
                break;
            case 11:
                salida.setID("CJ");
                salida.setValor(10);
                break;
            case 12:
                salida.setID("CQ");
                salida.setValor(10);
                break;
            case 13:
                salida.setID("CK");
                salida.setValor(10);
                break;
            /*-----Diamantes-----*/
            case 14:
                salida.setID("D1");
                salida.setValor(11);
                break;
            case 15:
                salida.setID("D2");
                salida.setValor(2);
                break;
            case 16:
                salida.setID("D3");
                salida.setValor(3);
                break;
            case 17:
                salida.setID("D4");
                salida.setValor(4);
                break;
            case 18:
                salida.setID("D5");
                salida.setValor(5);
                break;
            case 19:
                salida.setID("D6");
                salida.setValor(6);
                break;
            case 20:
                salida.setID("D7");
                salida.setValor(7);
                break;
            case 21:
                salida.setID("D8");
                salida.setValor(8);
                break;
            case 22:
                salida.setID("D9");
                salida.setValor(9);
                break;
            case 23:
                salida.setID("D10");
                salida.setValor(10);
                break;
            case 24:
                salida.setID("DJ");
                salida.setValor(10);
                break;
            case 25:
                salida.setID("DQ");
                salida.setValor(10);
                break;
            case 26:
                salida.setID("DK");
                salida.setValor(10);
                break;
            /*-----Picas-----*/
            case 27:
                salida.setID("P1");
                salida.setValor(11);
                break;
            case 28:
                salida.setID("P2");
                salida.setValor(2);
                break;
            case 29:
                salida.setID("P3");
                salida.setValor(3);
                break;
            case 30:
                salida.setID("P4");
                salida.setValor(4);
                break;
            case 31:
                salida.setID("P5");
                salida.setValor(5);
                break;
            case 32:
                salida.setID("P6");
                salida.setValor(6);
                break;
            case 33:
                salida.setID("P7");
                salida.setValor(7);
                break;
            case 34:
                salida.setID("P8");
                salida.setValor(8);
                break;
            case 35:
                salida.setID("P9");
                salida.setValor(9);
                break;
            case 36:
                salida.setID("P10");
                salida.setValor(10);
                break;
            case 37:
                salida.setID("PJ");
                salida.setValor(10);
                break;
            case 38:
                salida.setID("PQ");
                salida.setValor(10);
                break;
            case 39:
                salida.setID("PK");
                salida.setValor(10);
                break;
            /*-----Treboles-----*/
            case 40:
                salida.setID("T1");
                salida.setValor(11);
                break;
            case 41:
                salida.setID("T2");
                salida.setValor(2);
                break;
            case 42:
                salida.setID("T3");
                salida.setValor(3);
                break;
            case 43:
                salida.setID("T4");
                salida.setValor(4);
                break;
            case 44:
                salida.setID("T5");
                salida.setValor(5);
                break;
            case 45:
                salida.setID("T6");
                salida.setValor(6);
                break;
            case 46:
                salida.setID("T7");
                salida.setValor(7);
                break;
            case 47:
                salida.setID("T8");
                salida.setValor(8);
                break;
            case 48:
                salida.setID("T9");
                salida.setValor(9);
                break;
            case 49:
                salida.setID("T10");
                salida.setValor(10);
                break;
            case 50:
                salida.setID("TJ");
                salida.setValor(10);
                break;
            case 51:
                salida.setID("TQ");
                salida.setValor(10);
                break;
            case 52:
                salida.setID("TK");
                salida.setValor(10);
                break;
        }

        return salida;
    }

    /**
     * Override del metodo toString de la clase Baraja
     *
     * @return String
     */
    @Override
    public String toString() {
        String salida = "";
        for (Carta temp : cartas) {
            salida += temp.toString() + "\n";
        }
        return salida;
    }

    /**
     * Este metodo se encarga de hacer pop a la lista de cartas de la baraja
     * y retornar la carta que salio de la lista
     * 
     * @return Carta 
     */
    public Carta popCarta() {
        Carta salida;
        salida = cartas.get(1);
        cartas.remove(1);
        return salida;
    }
    
    
}
