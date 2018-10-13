
package Datos;


public class Tablero {

    //int[][] Matriz = new int[3][3];
    private int[][] Matriz;
    // 00  01  02 //
    // 10  11  12 //
    // 20  21  22 //

    public Tablero() {
    }

    public Tablero(int[][] Matriz) {
        this.Matriz = Matriz;
    }

    public int[][] getMatriz() {
        return Matriz;
    }

    public void setMatriz(int[][] Matriz) {
        this.Matriz = Matriz;
    }

    public void InicializarTablero() {
        //Inicialisa la matriz para la entrada de datos
        Matriz = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //Coloca 0 como valor predeterminado de la matrix;
                Matriz[i][j] = 0;
            }
        }
    }

    public String ImprimirTablero() {
        //
        String Salida = "";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Salida += Matriz[i][j] + " ";
            }
            Salida += "\n";
        }
        return Salida;
    }

    public void AgregarFicha(int ID, int x, int y) {
        Matriz[x][y] = ID;
    }

    public boolean Victoria() {
        int temp = 0;

        temp = Matriz[0][0];
        if (temp == Matriz[1][0] && temp == Matriz[2][0] && temp != 0) {
            return true;
        }

        temp = Matriz[0][1];
        if (temp == Matriz[1][1] && temp == Matriz[2][1] && temp != 0) {
            return true;
        }

        temp = Matriz[0][2];
        if (temp == Matriz[1][2] && temp == Matriz[2][2] && temp != 0) {
            return true;
        }

        temp = Matriz[0][0];
        if (temp == Matriz[0][1] && temp == Matriz[0][2] && temp != 0) {
            return true;
        }

        temp = Matriz[1][0];
        if (temp == Matriz[1][1] && temp == Matriz[1][2] && temp != 0) {
            return true;
        }

        temp = Matriz[2][0];
        if (temp == Matriz[2][1] && temp == Matriz[2][2] && temp != 0) {
            return true;
        }

        temp = Matriz[0][0];
        if (temp == Matriz[1][1] && temp == Matriz[2][2] && temp != 0) {
            return true;
        }

        temp = Matriz[2][0];
        if (temp == Matriz[1][1] && temp == Matriz[0][2] && temp != 0) {
            return true;
        }

        return false;
    }

    
}
