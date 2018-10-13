/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import UI.FrmServidor;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SSDesth
 */
public class Servidor {

    /*----------Variables----------*/
    private DataInputStream entrada;//Para leer comunicacion
    private DataOutputStream salida;//Para enviar comunicacion
    //public ArrayList<threadServidor> hilosserver;
    private Socket cliente1;//Socket del cliente 1
    private Socket cliente2;//Socket del cliente 2
    private Socket cliente3;//Socket del cliente 3

    private FrmServidor ventana;

    private threadServidor user1;
    private threadServidor user2;
    private threadServidor user3;

    public Servidor(FrmServidor entradaVentana) {
        this.ventana = entradaVentana;
    }

    public void runServer() {
        ServerSocket ss;
        try {
            ss = new ServerSocket(9998);
            //Queda a la espera del primer cliente
            cliente1 = ss.accept();
            System.out.println("Primer Cliente Conectado");
            EscribirMensajeServidor("Primer Cliente Conectado");
            //aqui iniciaria el hilo
            user1 = new threadServidor(cliente1, this);
            //user1.getSalida().writeInt(1);

            //Queda a la espera del Segundo cliente
            cliente2 = ss.accept();

        } catch (IOException ex) {
            System.out.println("A ocurrido un problema con el servidor");
        }
    }

    public void EscribirMensajeServidor(String entrada) {
        ventana.agregarMensaje(entrada);
    }

}
