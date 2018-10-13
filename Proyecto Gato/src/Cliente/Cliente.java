/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Negocio.Controlador;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SSDesth
 */
public class Cliente {

    DataInputStream entrada = null;//leer comunicacion
    DataOutputStream salida = null;//escribir comunicacion
    Socket cliente = null;//para la comunicacion
    Controlador micontrolador;

    public Cliente(Controlador eControlador) {
        this.micontrolador = eControlador;

    }

    public Cliente() {
    }
    
    public void coneccion(){
        //manejo de errores     
        try {
                                    //(IP , Puerto)
                cliente = new Socket("localhost", 9998);
                entrada = new DataInputStream(cliente.getInputStream());
                salida = new DataOutputStream(cliente.getOutputStream());
                
                
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            new threadCliente(entrada, this, salida).start();
    
    }
    
    public void enviarMensaje (String Usuario,String Mensaje){
        try {
            salida.writeInt(3);
            salida.writeUTF(Usuario+": "+Mensaje);
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void enviarFicha(int id, int x, int y){
    
         try {
            salida.writeInt(2);
            salida.writeInt(id);//id
            salida.writeInt(x);//x
            salida.writeInt(y);//y
            
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            
    
}
