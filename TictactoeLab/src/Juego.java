import javax.swing.*;

public class Juego {
    private Tablero tablero;
    private char jugadorActual;
    private final char JUGADOR_X = 'X';
    private final char JUGADOR_O = 'O';

    public Juego() {
        JFrame frame = new JFrame("Tres en Raya");
        tablero = new Tablero(this);
        frame.add(tablero);
        frame.setSize(320, 340);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        jugadorActual = JUGADOR_X;
    }

    public void realizarMovimiento(int fila, int columna) {
        if (tablero.colocarPieza(fila, columna, jugadorActual)) {
            if (tablero.hayGanador(jugadorActual)) {
                JOptionPane.showMessageDialog(null, "¡El jugador " + jugadorActual + " ha ganado!");
                tablero.inicializarTablero();
            } else if (tablero.estaLleno()) {
                JOptionPane.showMessageDialog(null, "¡Es un empate!");
                tablero.inicializarTablero();
            } else {
                cambiarJugador();
            }
        }
    }

    private void cambiarJugador() {
        jugadorActual = (jugadorActual == JUGADOR_X) ? JUGADOR_O : JUGADOR_X;
    }

    public static void main(String[] args) {
        new Juego();
    }
}
