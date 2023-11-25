package snake_;

import javax.swing.*;
import java.awt.*;

public class PanelJuegoTereminado {

    private boolean juegoGanado;
    private int puntuacion;
    private int puntuacionMaxima;
    Contenido contenido;
    
    public PanelJuegoTereminado(boolean juegoGanado, int puntuacion, int puntuacionMaxima){
        this.juegoGanado = juegoGanado;
        this.puntuacion = puntuacion;
        this.puntuacionMaxima = puntuacionMaxima;
    }

    public void mostrarResultado() {
        if(juegoGanado){
            dialogoGanaste();
        }else{
            dialogoPerdiste();
        }
    }

    protected void dialogoPerdiste() {
        JFrame frame = new JFrame("Mensaje de Juego Terminado");
        frame.setSize(280, 150);

        JDialog dialog = new JDialog(frame, "Juego terminado", true);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(" Has perdido el juego");
        JLabel labelPuntaje = new JLabel("Puntuacion: " + puntuacion + " / " + (puntuacionMaxima*puntuacionMaxima));
        JLabel mensajeDeReinicio = new JLabel("Presiona 'r' para reiniciar");
        JButton botonReinicio = new JButton("Reiniciar");

        botonReinicio.addActionListener(e -> contenido.reiniciarJuego());
        mensajeDeReinicio.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        label.setHorizontalAlignment(SwingConstants.CENTER);
        labelPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
        mensajeDeReinicio.setHorizontalAlignment(SwingConstants.CENTER);

        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        labelPuntaje.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panel.setBackground(new Color(180,207,70));

        Font fuente = new Font("Arial Black", Font.BOLD, 16);
        label.setFont(fuente);
        labelPuntaje.setFont(fuente);
        mensajeDeReinicio.setFont(fuente);

        panel.add(label, BorderLayout.CENTER);
        panel.add(labelPuntaje, BorderLayout.CENTER);
        panel.add(mensajeDeReinicio, BorderLayout.SOUTH);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setContentPane(panel);
        dialog.setSize(280, 150);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    protected void dialogoGanaste() {
        JFrame frame = new JFrame("Mensaje de Juego Terminado");
        frame.setSize(220, 130);

        JDialog dialog = new JDialog(frame, "Juego terminado", true);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(" Ganaste ! ");
        JLabel labelPuntaje = new JLabel("Puntuacion: " + puntuacion + " / " + (puntuacionMaxima*puntuacionMaxima));

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        label.setHorizontalAlignment(SwingConstants.CENTER);
        labelPuntaje.setHorizontalAlignment(SwingConstants.CENTER);

        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        labelPuntaje.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panel.setBackground(new Color(180,207,70));

        Font fuente = new Font("Arial Black", Font.BOLD, 16);
        label.setFont(fuente);
        label.setFont(fuente);

        panel.add(label, BorderLayout.CENTER);
        panel.add(labelPuntaje, BorderLayout.SOUTH);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setContentPane(panel);
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
