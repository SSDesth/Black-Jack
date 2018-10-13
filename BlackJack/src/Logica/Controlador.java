/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import UI.frmClienteInicio;

/**
 *
 * @author SSDesth
 */
public class Controlador {
    private frmClienteInicio ventanaInicio;
    private String Usuario;  

    public Controlador() {
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
  
    /**
     * Este metodo se encarga de ejecutar la primera ventana del programa
     */
    public void CargarVentanaInicio(){
        ventanaInicio= new frmClienteInicio();
        ventanaInicio.CargarVentana();
        ventanaInicio.setVisible(true);
    }
    
    public void ConectarServer(String entradaUsuario){
        this.Usuario=entradaUsuario;
        ventanaInicio.dispose();
        System.out.println(Usuario);
    }
}


