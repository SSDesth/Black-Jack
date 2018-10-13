
package Datos;


public class Jugador {
    
    private String Nombre;
    private String Contraseña;
    private int PartidasGanas;
    private int PartidasPerdidas;
    private int PartidasEmpatadas;

    public Jugador() {
    }

    public Jugador(String Nombre, String Contraseña) {
        this.Nombre = Nombre;
        this.Contraseña = Contraseña;
        this.PartidasGanas=0;
        this.PartidasEmpatadas=0;
        this.PartidasPerdidas=0;
    }

    public Jugador(String Nombre, String Contraseña, int PartidasGanas, int PartidasPerdidas, int PartidasEmpatadas) {
        this.Nombre = Nombre;
        this.Contraseña = Contraseña;
        this.PartidasGanas = PartidasGanas;
        this.PartidasPerdidas = PartidasPerdidas;
        this.PartidasEmpatadas = PartidasEmpatadas;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public int getPartidasGanas() {
        return PartidasGanas;
    }

    public void setPartidasGanas(int PartidasGanas) {
        this.PartidasGanas = PartidasGanas;
    }

    public int getPartidasPerdidas() {
        return PartidasPerdidas;
    }

    public void setPartidasPerdidas(int PartidasPerdidas) {
        this.PartidasPerdidas = PartidasPerdidas;
    }

    public int getPartidasEmpatadas() {
        return PartidasEmpatadas;
    }

    public void setPartidasEmpatadas(int PartidasEmpatadas) {
        this.PartidasEmpatadas = PartidasEmpatadas;
    }

    @Override
    public String toString() {
        return Nombre + "," + Contraseña + "," + PartidasGanas + "," + PartidasPerdidas + "," + PartidasEmpatadas ;
    }
    
    
    
}
