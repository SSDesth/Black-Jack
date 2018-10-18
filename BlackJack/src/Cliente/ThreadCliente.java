/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Logica.main;
import datos.Carta;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Andres Montero
 * @version 14/10/2018
 */
public class ThreadCliente extends Thread {

    /*----------Variables----------*/
    private DataInputStream entrada; //entrada de la informacion
    private Cliente cliente; //referencia al cliente 
    private DataOutputStream salida;  //salida de informacion
    private boolean Correr = true;//booleano que defina la vida del hilo
    private int numeroJugador;//numero de jugar

    /*----------Constructor(es)----------*/
    /**
     * Contructor Default de la clase
     */
    public ThreadCliente() {
    }

    /**
     * Contructor sobrecargado de la clase
     *
     * @param eEntrada:DataInputStream
     * @param eCliente:Cliente
     * @param eSalida:DataOutputStream
     */
    public ThreadCliente(DataInputStream eEntrada, Cliente eCliente, DataOutputStream eSalida) {
        this.entrada = eEntrada;
        this.cliente = eCliente;
        this.salida = eSalida;
    }

    /**
     * Override del metodo run del hilo cliente
     */
    @Override
    public void run() {
        int opcion = 0;
        //While que siempre correra esperando algun mensaje del servidor
        while (Correr) {

            try {
                //recibe un numero del servidor que decidira cual accion tomara
                opcion = entrada.readInt();
                switch (opcion) {
                    //Asignar numero de Jugador
                    case 0:
                        numeroJugador=entrada.readInt();
                        cliente.getMicontrolador().CargarVentanaLoading(numeroJugador);
                        break;
                    //Agrega un nuevo Jugador a la pertida;    
                    case 1:
                        int temp=entrada.readInt();
                        cliente.getMicontrolador().ActualizarVentanaLoading(temp);
                        break;
                    //prepara el programa para abrir la venta de Jugar
                    case 2:
                         cliente.getMicontrolador().
                                 ActualizarMensajeVentanaLoading(""
                                         + "La partida Iniciara en 5");
                         sleep(1000);
                         
                         cliente.getMicontrolador().
                                 ActualizarMensajeVentanaLoading(""
                                         + "La partida Iniciara en 4");
                         sleep(1000);
                         
                         cliente.getMicontrolador().
                                 ActualizarMensajeVentanaLoading(""
                                         + "La partida Iniciara en 3");
                         sleep(1000);
                         
                         cliente.getMicontrolador().
                                 ActualizarMensajeVentanaLoading(""
                                         + "La partida Iniciara en 2");
                         sleep(1000);
                         
                         cliente.getMicontrolador().
                                 ActualizarMensajeVentanaLoading(""
                                         + "La partida Iniciara en 1");
                         sleep(1000);
                         
                         cliente.getMicontrolador().CargarFrmJuego();
                         break;
                    
                    case 3://Agrega una carta al jugador
                        Carta nuevaCarta = new  Carta();
                        nuevaCarta.setID(entrada.readUTF());
                        nuevaCarta.setValor(entrada.read());
                        System.out.println(nuevaCarta.toString());
                        cliente.getMicontrolador().AgregarCarta(nuevaCarta);
                        break;
                    
                    case 4://Turno
                        cliente.getMicontrolador().AsignarTurno();
                        break;
                    /*case 5://pedir estado
                            cliente.EnviarEstadoJugador();
                        break;*/
                    
                    case 6://Valida estado
                            cliente.ValidareEstado();
                        break;
                    
                    case 7://final del juego
                        int finalJuego=entrada.readInt();
                        cliente.getMicontrolador().CargarVentanaFinal(finalJuego);
                        sleep(5000);
                        cliente.getMicontrolador().CargarVentanaInicioReset();
                        break;
                        
                    
                        
                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
