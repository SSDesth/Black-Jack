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
import javax.swing.JOptionPane;

/**
 *
 * @author SSDesth
 */
public class threadServidor extends Thread {

    //BARAJA PARA LA PARTIDA
    private Baraja miBaraja;

    //Variables de coidigo para victoria y derrotas
    private int estadoJ1 = 0;
    private int estadoJ2 = 0;
    private int estadoJ3 = 0;

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

                if (estadoJ1 == 0) {
                    salida.writeInt(4);
                    seleccion = entrada.read();//Respuesta del cliente 1
                    Ejecucion(1, seleccion, salida);
                }
                //valida el estado del jugador 1
                salida.writeInt(6);
                estadoJ1 = entrada.read();

                if (estadoJ2 == 0) {
                    salida2.writeInt(4);
                    seleccion = entrada2.read();//Respuesta del cliente 1
                    Ejecucion(2, seleccion, salida2);
                }

                //valida el estado del jugador 2
                salida2.writeInt(6);
                estadoJ2 = entrada2.read();

                /*revisa el estado de la partida del jugador 2*/
                if (estadoJ3 == 0) {
                    salida3.writeInt(4);
                    seleccion = entrada3.read();//Respuesta del cliente 1
                    Ejecucion(3, seleccion, salida3);
                }

                //valida el estado del jugador 3
                salida3.writeInt(6);
                estadoJ3 = entrada3.read();

                // se revisa si ya se tremino el juego
                if (RevisarEstados()) {
                    ejecucionHilo = false;
                }
            } catch (IOException ex) {
                System.out.println("Error con la informacion que se recibio");
            }
        }
        finalJuego();
        //System.out.println("Ya todo se decidio jajajajajajajajajjaaj XD XD");

    }

    public void Ejecucion(int Jugador, int seleccion, DataOutputStream salidaCliente) {
        switch (seleccion) {
            case 0:
                MensajeServidor("Jugador " + Jugador + " Paso su Turno");
                break;
            case 2://Pedir una carta
                MensajeServidor("Jugador " + Jugador + " Pidio una carta");
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

        MensajeServidor("Se envio una carta a todos los Jugadores\n");
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
            MensajeServidor("Se envio una carta al Jugador 1\n");
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
            MensajeServidor("Se envio una carta al Jugador 2\n");

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
            MensajeServidor("Se envio una carta al Jugador 3\n");
        } catch (IOException ex) {
            Logger.getLogger(threadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo encargado de enviar una carta a un jugador especifico
     *
     * @param Jugador:int
     */
    public void EnviarCarta(int Jugador) {
        Carta temp;
        //consigo la carta a enviar
        temp = miBaraja.popCarta();
        switch (Jugador) {
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

    public boolean RevisarEstados() {
        /*JOptionPane.showMessageDialog(null,"Estado"
                + "\nJugador 1: " + estadoJ1
                + "\nJugador 2: " + estadoJ2
                + "\nJugador 3: " + estadoJ3);*/

        if (estadoJ1 != 0 && estadoJ2 != 0 && estadoJ3 != 0) {
            return true;
        }
        return false;
    }

    /**
     * Envia un mensaje al frmServidor para actualizar lo que se esta haciendo
     *
     * @param mensaje:String
     */
    public void MensajeServidor(String mensaje) {
        servidor.EscribirMensajeServidor(mensaje);
    }

    /**
     * comunica a los clientes que eljuego se termino
     */
    public void finalJuego() {
        try {
            salida.writeInt(7);
            salida2.writeInt(7);
            salida3.writeInt(7);

            /*
            0: Derrota
            1: Victoria
            2: Enpate
             */
            //validaciones jugador 1
            //perder el juego
            if (estadoJ1 == 3) {
                salida.writeInt(0);
            } else {
                //victoria Black Jack
                if (estadoJ1 == 1) {
                    //revisa si hay un enpate
                    if (estadoJ2 == 1 || estadoJ3 == 1) {
                        salida.writeInt(2);
                    } else {
                        //retorna victoria
                        salida.writeInt(1);
                    }
                } else {
                    // revisa si otro jugador tiene Black Jack
                    if (estadoJ2 == 1 || estadoJ3 == 1) {
                        salida.writeInt(0);
                    } else {
                        if (estadoJ2 == 2 || estadoJ3 == 2) {
                            salida.writeInt(2);
                        } else {
                            salida.writeInt(1);
                        }
                    }
                }
            }
            //validaciones jugador 2
            //perder el juego
            if (estadoJ2 == 3) {
                salida2.writeInt(0);
            } else {
                //victoria Black Jack
                if (estadoJ2 == 1) {
                    //revisa si hay un enpate
                    if (estadoJ1 == 1 || estadoJ3 == 1) {
                        salida2.writeInt(2);
                    } else {
                        //retorna victoria
                        salida2.writeInt(1);
                    }
                } else {
                    // revisa si otro jugador tiene Black Jack
                    if (estadoJ1 == 1 || estadoJ3 == 1) {
                        salida2.writeInt(0);
                    } else {
                        if (estadoJ1 == 2 || estadoJ3 == 2) {
                            salida2.writeInt(2);
                        } else {
                            salida2.writeInt(1);
                        }
                    }
                }
            }
            //validaciones jugador 3
            //perder el juego
            if (estadoJ3 == 3) {
                salida3.writeInt(0);
            } else {
                //victoria Black Jack
                if (estadoJ3 == 1) {
                    //revisa si hay un enpate
                    if (estadoJ1 == 1 || estadoJ2 == 1) {
                        salida3.writeInt(2);
                    } else {
                        //retorna victoria
                        salida3.writeInt(1);
                    }
                } else {
                    // revisa si otro jugador tiene Black Jack
                    if (estadoJ1 == 1 || estadoJ2 == 1) {
                        salida3.writeInt(0);
                    } else {
                        if (estadoJ1 == 2 || estadoJ2 == 2) {
                            salida3.writeInt(2);
                        } else {
                            salida3.writeInt(1);
                        }
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(threadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
