/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;


import Logica.Controlador;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author SSDesth
 */
public class Cliente {
    /*----------Variables----------*/
    private DataInputStream entrada = null;//leer comunicacion
    private DataOutputStream salida = null;//escribir comunicacion
    private Socket cliente = null;//para la comunicacion
    private Controlador micontrolador;
    
    /*----------Constructor(es)----------*/
    /**
     * Constructor Default de la clase
     */
    public Cliente() {
    }

    /**
     * Constructor sobrecargado de la clase
     * @param eControlador:Controlador
     */
    public Cliente(Controlador eControlador) {
        this.micontrolador = eControlador;
    }
    /*----------Setters y Getters----------*/
    /**
     * Get de la variable entrada
     * @return DataInputStream
     */
    public DataInputStream getEntrada() {
        return entrada;
    }

    /**
     * Set de la variable entrada
     * @param entrada:DataInputStream
     */
    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    /**
     * Get de la variable salida
     * @return DataOutputStream
     */
    public DataOutputStream getSalida() {
        return salida;
    }

    /**
     * Set de la variable salida
     * @param salida:DataOutputStream
     */
    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    /**
     * Get de la variable cliente
     * @return Socket
     */
    public Socket getCliente() {
        return cliente;
    }

    /**
     * Set de la variable cliente
     * @param cliente:Socket
     */
    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    /**
     * Get de la variable micontrolador
     * @return Controlador 
     */
    public Controlador getMicontrolador() {
        return micontrolador;
    }

    /**
     * Set de la variable micontrolador
     * @param micontrolador:Controlador
     */
    public void setMicontrolador(Controlador micontrolador) {
        this.micontrolador = micontrolador;
    }
    
    /*----------Metodos Especializados----------*/
    
    /**
     * Metodo encargado de la coneccion con el servidor y ademas inicializar el
     * hilo de ejecucion
     */
    public void coneccion(){
         try {//Manejo de erroroes
                                    //(IP , Puerto)
                cliente = new Socket("localhost", 9998);
                entrada = new DataInputStream(cliente.getInputStream());
                salida = new DataOutputStream(cliente.getOutputStream());
                
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"No se encontro ningun\n"
                        + "servidor activo", "Alerta",0);
            }
            new ThreadCliente(entrada, this, salida).start();
    }
    
    public void PedirCarta(){
        try {
            salida.write(2);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
