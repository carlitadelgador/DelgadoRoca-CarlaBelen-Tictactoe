import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tablero extends JPanel {
    private char[][] tablero;
    private final int TAMANO = 3;
    private final char VACIO = '-';
    private Juego juego;

    public Tablero(Juego juego) {
        this.juego = juego;
        tablero = new char[TAMANO][TAMANO];
        inicializarTablero();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = e.getY() / 100;
                int columna = e.getX() / 100;
                juego.realizarMovimiento(fila, columna);
            }
        });
    }

    public void inicializarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = VACIO;
            }
        }
    }

    public boolean colocarPieza(int fila, int columna, char pieza) {
        if (tablero[fila][columna] == VACIO) {
            tablero[fila][columna] = pieza;
            repaint();
            return true;
        }
        return false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                g.drawRect(j * 100, i * 100, 100, 100);
                g.drawString(String.valueOf(tablero[i][j]), j * 100 + 50, i * 100 + 50);
            }
        }
    }

    public boolean hayGanador(char pieza) {
        // Comprobar filas, columnas y diagonales
        for (int i = 0; i < TAMANO; i++) {
            if (tablero[i][0] == pieza && tablero[i][1] == pieza && tablero[i][2] == pieza) {
                return true;
            }
            if (tablero[0][i] == pieza && tablero[1][i] == pieza && tablero[2][i] == pieza) {
                return true;
            }
        }
        if (tablero[0][0] == pieza && tablero[1][1] == pieza && tablero[2][2] == pieza) {
            return true;
        }
        if (tablero[0][2] == pieza && tablero[1][1] == pieza && tablero[2][0] == pieza) {
            return true;
        }
        return false;
    }

    public boolean estaLleno() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (tablero[i][j] == VACIO) {
                    return false;
                }
            }
        }
        return true;
    }
}