/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SSDesth
 */
public class threadCliente extends Thread {

    //solo de lectura
    DataInputStream entrada; //entrada de la informacion
    Cliente cliente; //referencia al cliente 
    DataOutputStream salida;  //salida de informacion
    boolean Correr = true;
    boolean miTurno = true;

    public threadCliente(DataInputStream entrada, Cliente cliente, DataOutputStream salida) {
        this.entrada = entrada;
        this.cliente = cliente;
        this.salida = salida;
    }

    @Override
    public void run() {
        int opcion = 0;
        while (Correr) {
            try {
                /**se queda esperando un mensaje del servidor
                  y con esto ejecutara alguna accion del switch**/
                opcion = entrada.readInt();
                switch(opcion){
                    case 0: //No pasa nada
                        System.out.println("no pasa nada");
                        break;
                    case 1://Cargar frmLoading
                        cliente.micontrolador.Esperando();
                        System.out.println("Holamundo");
                        break;
                    case 2:// Cargar frmTablero
              
                        int Turno = entrada.readInt();
                        
                        cliente.micontrolador.pasoDeFrameLoading("Contrincante encontrado");
                        sleep(1000);
                        cliente.micontrolador.pasoDeFrameLoading("Comensando en 5");
                        sleep(1000);
                        cliente.micontrolador.pasoDeFrameLoading("Comensando en 4");
                        sleep(1000);
                        cliente.micontrolador.pasoDeFrameLoading("Comensando en 3");
                        sleep(1000);
                        cliente.micontrolador.pasoDeFrameLoading("Comensando en 2");
                        sleep(1000);
                        cliente.micontrolador.pasoDeFrameLoading("Comensando en 1");
                        sleep(1000);
                        cliente.micontrolador.Tablero(Turno);
                        
                        break;
                        
                    case 3: //Fichas
                        int id = entrada.readInt();
                        int x = entrada.readInt();
                        int y = entrada.readInt();
                        cliente.micontrolador.FichaEnemiga(id, x, y);
                        
                        break;
                    case 4://Recibir Mensaje
                        String mensajeEntrada=entrada.readUTF();
                        cliente.micontrolador.Mensaje(mensajeEntrada);
                        break;
                }

            } catch (IOException ex) {
                Logger.getLogger(threadCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(threadCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
