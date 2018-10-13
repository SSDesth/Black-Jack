/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Cliente.Cliente;
import UI.frmInicial;
import UI.frmLoading;
import UI.frmTablero;

/**
 *
 * @author SSDesth
 */
public class Controlador {
    
private Cliente micliente;
private frmLoading frmLoading;
private frmTablero frmTablero;
private String Usuario;
    public Controlador() {
    }

    public Cliente getMicliente() {
        return micliente;
    }

    public void setMicliente(Cliente micliente) {
        this.micliente = micliente;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }


    
    
public void Iniciar(){
    frmInicial miVentana = new frmInicial();
    miVentana.setVisible(true);
}

public void Conectar(String Usuario,frmInicial temp){
    this.Usuario=Usuario;
    temp.dispose();
    System.out.println(Usuario);
    micliente = new Cliente(this);
    micliente.coneccion();  
    
}

public void Esperando(){
    frmLoading = new frmLoading();
    frmLoading.setVisible(true);
}

public void Tablero(int id){
    frmLoading.dispose();
    frmTablero = new frmTablero();
    ConfigTurno(id);
    frmTablero.cargar(Usuario);
    frmTablero.setVisible(true);
}

public void Mensaje(String Mensaje){
    frmTablero.agregarMensaje(Mensaje);
}

public void pasoDeFrameLoading(String Mensaje){
    frmLoading.getLblLoading().setText(Mensaje);
}

public void FichaEnemiga(int id,int x,int y){
    frmTablero.AgregarFichaContrincante(id, x, y);
}


public void ConfigTurno(int id){ 
    if(id==1){
        frmTablero.configurarJugador(id, true);
    }
    else
        frmTablero.configurarJugador(id, false);
    
}


public void finDelJuego(frmTablero temp){
    this.Usuario="";
    temp.dispose();
    micliente = null;  
    Iniciar();
}
    
}
