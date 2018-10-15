/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Cliente.Cliente;
import UI.frmClienteInicio;
import UI.frmClienteJuego;
import UI.frmClienteLoading;
import datos.Carta;

/**
 *
 * @author SSDesth
 */
public class Controlador {
    /*----------Variables----------*/
    private frmClienteInicio ventanaInicio;
    private frmClienteLoading ventanaLoading;
    private frmClienteJuego ventanaJuego;
    private String Usuario;
    private Cliente miCliente;

    /*----------Contructor(es)----------*/
    /**
     * Contructor default de la clase
     */
    public Controlador() {
    }
    
    /*----------Setters y Getters----------*/
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public Cliente getMiCliente() {
        return miCliente;
    }

    public void setMiCliente(Cliente miCliente) {
        this.miCliente = miCliente;
    }
    
    

    /*----------Metodos especialisados----------*/
    
    /**
     * Este metodo se encarga de ejecutar la primera ventana del programa
     */
    public void CargarVentanaInicio() {
        ventanaInicio = new frmClienteInicio();
        ventanaInicio.CargarVentana();
        ventanaInicio.setVisible(true);
    }

    /**
     * Este metodo se encargara de intentar hacer el Socket cliente y conectarlo
     * con un servidor
     * 
     * @param entradaUsuario:String 
     */
    public void ConectarServer(String entradaUsuario) {
        this.Usuario = entradaUsuario;
        ventanaInicio.dispose();
        System.out.println(Usuario);
        miCliente = new Cliente(this);
        miCliente.coneccion();
    }
    /**
     * Carga la Ventada de espera hasta que esten los demas jugadores
     * @param jugador:int
     */
    public void CargarVentanaLoading(int jugador){
        ventanaLoading= new frmClienteLoading();
        ventanaLoading.Cargador(jugador);
        ventanaLoading.setVisible(true);
    }
    /**
     * actualisa el frm cin la llegada de un nuevo jugador
     * @param jugador:int
     */
    public void ActualizarVentanaLoading(int numeroJugadores){
        ventanaLoading.Actualizar(numeroJugadores);
    }
    /**
     * envia un mensaje a la ventana loading
     * @param mensaje 
     */
    public void ActualizarMensajeVentanaLoading(String mensaje){
        ventanaLoading.ActualizarMensaje(mensaje);
    }
    /**
     * metodo encargado de cargar la ventana de Juego
     */
    public void CargarFrmJuego(){
        ventanaLoading.dispose();
        ventanaJuego= new frmClienteJuego();
        ventanaJuego.CargarVentana(Usuario);
        ventanaJuego.setVisible(true);
    }
    
    /**
     * Este metodo agrega una carta al Jugador
     * @param entradaCarta:Carta
     */
    public void AgregarCarta(Carta entradaCarta){
        ventanaJuego.AgregarCarta(entradaCarta);
    }
    
    public void AsignarTurno(){
        ventanaJuego.turno();
    }
    
   
}
