package Servidor;

import java.net.*;
// Este paquete se encarga del manejo de los
// DataStream y ObjectStream
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    DataInputStream entrada;//Para leer comunicacion
    DataOutputStream salida;//Para enviar comunicacion
    //public ArrayList<threadServidor> hilosserver;
    Socket cliente1;
    Socket cliente2;
    FrmServidor ventana;

    threadServidor user1;
    threadServidor user2;

    public Servidor(FrmServidor entrada) {
        this.ventana = entrada;

    }

    public void runServer() {

        ServerSocket ss;
        try {
            ss = new ServerSocket(9998);
            while (true) {
                //Queda a la espera de la coneccion de los clientes
                cliente1 = ss.accept();
                System.out.println("Primer Cliente Conectado");
                ventana.agregarMensaje("Primer Cliente Conectado");

                //aqui iniciaria el hilo
                user1 = new threadServidor(cliente1, this);
                user1.salida.writeInt(1);
                //user1.start();

                //Queda a la espera de la coneccion de los clientes
                cliente2 = ss.accept();
                System.out.println("Segundo Cliente Conectado");
                ventana.agregarMensaje("Segundo Cliente Conectado");
                ventana.agregarMensaje("---------------------------");

                user2 = new threadServidor(cliente2, this);
                user2.salida.writeInt(1);

                user1.salida.writeInt(2);//inicia proceso de carga de otra pantalla
                user2.salida.writeInt(2);//inicia proceso de carga de otra pantalla

                user1.salida.writeInt(1);// Asigna el turno al jugador
                user2.salida.writeInt(2);

                user1.asignacionCliente2(cliente2);
                user2.asignacionCliente2(cliente1);

                user1.start();
                user2.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
