/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import datos.Baraja;
import datos.Carta;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SSDesth
 */
public class threadServidor extends Thread {

    private Baraja miBaraja;

    private Socket cliente = null;//referencia a socket de comunicacion de cliente 1
    private Socket cliente2 = null;//referencia a socket de comunicacion de cliente 2
    private Socket cliente3 = null;//referencia a socket de comunicacion de cliente 3
    /*----------Entrada y salida info cliente 1----------*/
    private DataInputStream entrada = null;//Para leer comunicacion
    private DataOutputStream salida = null;//Para enviar comunicacion	

    /*----------Entrada y salida info cliente 2----------*/
    private DataInputStream entrada2 = null;//Para leer comunicacion
    private DataOutputStream salida2 = null;//Para enviar comunicacion	

    /*----------Entrada y salida info cliente 3----------*/
    private DataInputStream entrada3 = null;//Para leer comunicacion
    private DataOutputStream salida3 = null;//Para enviar comunicacion	

    private boolean ejecucionHilo = true;

    private Servidor servidor;// referencia al servidor

    /*----------Contructor(es)----------*/
    /**
     * Constructor default de la clase
     *
     * @param servidor
     */
    public threadServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    /**
     * COnstructor sobrecargado del hilo servidor
     *
     * @param cliente:Socket
     * @param serv:Servidor
     * @throws IOException
     */
    public threadServidor(Socket cliente, Servidor serv) throws IOException {
        this.cliente = cliente;
        this.servidor = serv;
        this.salida = new DataOutputStream(cliente.getOutputStream());//comunic
        this.entrada = new DataInputStream(cliente.getInputStream());//comunic
    }

    /*----------Setters y getters---------*/
    /**
     * Get de la variable Cliente
     *
     * @return Socket
     */
    public Socket getCliente() {
        return cliente;
    }

    /**
     * Set de la variable Cliente
     *
     * @param cliente:Socket
     */
    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    /**
     * Get de la variable Cliente2
     *
     * @return Socket
     */
    public Socket getCliente2() {
        return cliente2;
    }

    /**
     * Set de la variable Cliente2
     *
     * @param cliente2:Socket
     */
    public void setCliente2(Socket cliente2) {
        this.cliente2 = cliente2;
    }

    /**
     * Get de la variable Cliente3
     *
     * @return Socket
     */
    public Socket getCliente3() {
        return cliente3;
    }

    /**
     * Set de la variable Cliente3
     *
     * @param cliente3
     */
    public void setCliente3(Socket cliente3) {
        this.cliente3 = cliente3;
    }

    /**
     * Get de la variable Entrada
     *
     * @return DataInputStream
     */
    public DataInputStream getEntrada() {
        return entrada;
    }

    /**
     * Set de la variable Entrada
     *
     * @param entrada:DataInputStream
     */
    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    public DataOutputStream getSalida() {
        return salida;
    }

    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    public DataInputStream getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(DataInputStream entrada2) {
        this.entrada2 = entrada2;
    }

    public DataOutputStream getSalida2() {
        return salida2;
    }

    public void setSalida2(DataOutputStream salida2) {
        this.salida2 = salida2;
    }

    public DataInputStream getEntrada3() {
        return entrada3;
    }

    public void setEntrada3(DataInputStream entrada3) {
        this.entrada3 = entrada3;
    }

    public DataOutputStream getSalida3() {
        return salida3;
    }

    public void setSalida3(DataOutputStream salida3) {
        this.salida3 = salida3;
    }

    public boolean isEjecucionHilo() {
        return ejecucionHilo;
    }

    public void setEjecucionHilo(boolean ejecucionHilo) {
        this.ejecucionHilo = ejecucionHilo;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    /*----------Metodos especializados----------*/
    /**
     * Este metodo asigna al hilo el cliente 2
     *
     * @param eCliente2
     * @throws IOException
     */
    public void asignacionCliente2(Socket eCliente2) throws IOException {
        this.cliente2 = eCliente2;
        this.salida2 = new DataOutputStream(cliente2.getOutputStream());//comunic
        this.entrada2 = new DataInputStream(cliente2.getInputStream());//comunic
    }

    /**
     * Este metodo asigna al hilo el cliente 2
     *
     * @param eCliente2
     * @throws IOException
     */
    public void asignacionCliente3(Socket eCliente3) throws IOException {
        this.cliente3 = eCliente3;
        this.salida3 = new DataOutputStream(cliente3.getOutputStream());//comunic
        this.entrada3 = new DataInputStream(cliente3.getInputStream());//comunic
    }

    /**
     * Metodo que siempre quedara a la escucha de los clientes con lo cual
     * cuando un clienteenvia algo al servidor este renviara un mensaje a los
     * demas usuarios conectados y con este mensaje el hilo de chiente se
     * encargara de ejecutar el debido procedimiento
     *
     */
    @Override
    public void run() {

        //Se crea la bara con la que se jugara
        miBaraja = new Baraja();

        //se envian las primeras 2 cartas a todos los jugadores
        EnviarCartasJugadoresInicio();
        EnviarCartasJugadoresInicio();

        //Con esta variable se definira que susedera dentra del run del hilo
        int seleccion = 0;
        boolean ganador = false;
        
        /*while eterno encargado de siempre estar pendiente de lo que algun 
        cliente va a decir */
        while (ejecucionHilo) {
            try {

                salida.writeInt(4);
                seleccion = entrada.read();//Respuesta del cliente 1
                Ejecucion(1, seleccion, salida);
                
                salida2.writeInt(4);
                seleccion = entrada2.read();//Respuesta del cliente 1
                Ejecucion(2, seleccion, salida2);
                
                salida3.writeInt(4);
                seleccion = entrada3.read();//Respuesta del cliente 1
                Ejecucion(3, seleccion, salida3);
                
            } catch (IOException ex) {
                System.out.println("Error con la informacion que se recibio");
            }
        }

    }

    public void Ejecucion(int Jugador,int seleccion, DataOutputStream salidaCliente) {
        switch (seleccion) {
            case 2://Pedir una carta
                EnviarCarta(Jugador);
                break;
            
        }

    }
    /**
     * envia una carta a todos los usuarios
     */
    public void EnviarCartasJugadoresInicio() {

        Carta temp;
        //consigo la carta a enviar
        temp = miBaraja.popCarta();
        //se envia al jugador 1
        EnviarCartaJugador1(temp);
        //consigo la carta a enviar
        temp = miBaraja.popCarta();
        //se envia al jugador 2
        EnviarCartaJugador2(temp);
        //consigo la carta a enviar
        temp = miBaraja.popCarta();
        //se envia al jugador 3
        EnviarCartaJugador3(temp);

    }

    /**
     * envia una carta al Jugador 1
     *
     * @param entrada:Carta
     */
    public void EnviarCartaJugador1(Carta entrada) {
        try {
            //le avisa al cliente 1 que le va a enviar una carta
            salida.writeInt(3);
            //envia el id de la carta
            salida.writeUTF(entrada.getID());
            //envia el valor de la carta
            salida.write(entrada.getValor());

        } catch (IOException ex) {
            Logger.getLogger(threadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * envia una carta al Jugador 2
     *
     * @param entrada:Carta
     */
    public void EnviarCartaJugador2(Carta entrada) {
        try {
            //le avisa al cliente 1 que le va a enviar una carta
            salida2.writeInt(3);
            //envia el id de la carta
            salida2.writeUTF(entrada.getID());
            //envia el valor de la carta
            salida2.write(entrada.getValor());

        } catch (IOException ex) {
            Logger.getLogger(threadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * envia una carta al Jugador 3
     *
     * @param entrada:Carta
     */
    public void EnviarCartaJugador3(Carta entrada) {
        try {
            //le avisa al cliente 1 que le va a enviar una carta
            salida3.writeInt(3);
            //envia el id de la carta
            salida3.writeUTF(entrada.getID());
            //envia el valor de la carta
            salida3.write(entrada.getValor());

        } catch (IOException ex) {
            Logger.getLogger(threadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo encargado de enviar una carta a un jugador especifico
     * @param Jugador:int
     */
    public void EnviarCarta(int Jugador){
        Carta temp;
        //consigo la carta a enviar
        temp = miBaraja.popCarta();
        switch(Jugador){
            case 1://envia una carta al jugador 1
                EnviarCartaJugador1(temp);
                break;
                
            case 2://envia una carta al jugador 2
                EnviarCartaJugador2(temp);
                break;
                
            case 3://envia una carta al jugador 3
                EnviarCartaJugador3(temp);
                break;
        }
    }
}
