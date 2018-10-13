/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SSDesth
 */
public class threadServidor extends Thread {

    Socket cliente = null;//referencia a socket de comunicacion de cliente
    Socket cliente2 = null;//referencia a socket de comunicacion de cliente

    DataInputStream entrada = null;//Para leer comunicacion
    DataOutputStream salida = null;//Para enviar comunicacion	

    DataInputStream entrada2 = null;//Para leer comunicacion
    DataOutputStream salida2 = null;//Para enviar comunicacion	

    Servidor servidor;// referencia al servidor

    public threadServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    //--------Contructor del HiloServidor--------//
    public threadServidor(Socket cliente, Servidor serv) throws IOException {
        this.cliente = cliente;
        this.servidor = serv;
        this.salida = new DataOutputStream(cliente.getOutputStream());//comunic
        this.entrada = new DataInputStream(cliente.getInputStream());//comunic

    }

    public void asignacionCliente2(Socket eCliente2) throws IOException {
        this.cliente2 = eCliente2;
        this.salida2 = new DataOutputStream(cliente2.getOutputStream());//comunic
        this.entrada2 = new DataInputStream(cliente2.getInputStream());//comunic
    }

    public void run() {
    int seleccion = 0;
        while (true) {
            
            try {
                seleccion=entrada.readInt();
                switch(seleccion){
                    case 2://Recibir ficha
                        int id = entrada.readInt();
                        int x = entrada.readInt();
                        int y = entrada.readInt();
                        
                        salida2.writeInt(3);//avisa al hilo cliente que va una ficha
                        
                        salida2.writeInt(id);
                        salida2.writeInt(x);
                        salida2.writeInt(y);
                        
                        break;
                        
                    case 3:
                        String Mensaje=entrada.readUTF();
                        salida2.writeInt(4);
                        salida2.writeUTF(Mensaje);
                        break;
                }
                
                
                //salida = new DataOutputStream(cliente.getOutputStream());//comunic
            } catch (IOException ex) {
                Logger.getLogger(threadServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
