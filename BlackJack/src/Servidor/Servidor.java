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

    private threadServidor hiloServidor;
    private threadServidor user2;
    private threadServidor user3;

    public Servidor(FrmServidor entradaVentana) {
        this.ventana = entradaVentana;
    }

    public void runServer() {
        ServerSocket ss;
        try {
            ss = new ServerSocket(9998);

            while (true) {
                //Queda a la espera del primer cliente
                cliente1 = ss.accept();
                System.out.println("Primer Cliente Conectado");
                EscribirMensajeServidor("Primer Cliente Conectado");

                //aqui iniciaria el hilo
                hiloServidor = new threadServidor(cliente1, this);
                //Le dice al cliente que va a decirle su numero de jugador
                hiloServidor.getSalida().writeInt(0);
                //le envia al cliente su numero de jugador
                hiloServidor.getSalida().writeInt(1);

                //Queda a la espera del Segundo cliente
                cliente2 = ss.accept();
                System.out.println("Segundo Cliente Conectado");
                EscribirMensajeServidor("Segundo Cliente Conectado");

                //Se agrega el cliente 2 al hilo del servidor
                hiloServidor.asignacionCliente2(cliente2);

                //Le dice al cliente que va a decirle su numero de jugador
                hiloServidor.getSalida2().writeInt(0);
                //le envia al cliente su numero de jugador
                hiloServidor.getSalida2().writeInt(2);

                //Actualiza la ventana del Jugador 1
                hiloServidor.getSalida().writeInt(1);
                //le envia la cantidad de jugadores actuales
                hiloServidor.getSalida().writeInt(2);

                //Queda a la espera del Tercer cliente
                cliente3 = ss.accept();
                System.out.println("Tercer Cliente Conectado");
                EscribirMensajeServidor("Tercer Cliente Conectado");

                //Se agrega el cliente 3 al hilo del servidor
                hiloServidor.asignacionCliente3(cliente3);

                //Le dice al cliente que va a decirle su numero de jugador
                hiloServidor.getSalida3().writeInt(0);
                //le envia al cliente su numero de jugador
                hiloServidor.getSalida3().writeInt(3);

                //Actualiza la ventana del Jugador 1
                hiloServidor.getSalida().writeInt(1);
                //le envia la cantidad de jugadores actuales
                hiloServidor.getSalida().writeInt(3);

                //Actualiza la ventana del Jugador 2
                hiloServidor.getSalida2().writeInt(1);
                //le envia la cantidad de jugadores actuales
                hiloServidor.getSalida2().writeInt(3);

                //Se les comunica a los cliente que la partida va a iniciar
                hiloServidor.getSalida().writeInt(2);
                hiloServidor.getSalida2().writeInt(2);
                hiloServidor.getSalida3().writeInt(2);

                hiloServidor.start();

                EscribirMensajeServidor("------------------------");
                EscribirMensajeServidor("---inicio una Partida---");
                EscribirMensajeServidor("------------------------");
            }
        } catch (IOException ex) {
            System.out.println("A ocurrido un problema con el servidor");
        }
    }

    public void EscribirMensajeServidor(String entrada) {
        ventana.agregarMensaje(entrada);
    }

}
