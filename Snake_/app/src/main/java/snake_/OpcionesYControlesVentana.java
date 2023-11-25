package snake_;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcionesYControlesVentana extends JFrame {

    private int dificultadSeleccionada;
    private String tipografia = "Arial Black";

    public OpcionesYControlesVentana() {
        setTitle("Opciones y Configuracion");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel menuPanel = new JPanel(null);
        menuPanel.setBackground(new Color(180, 207, 70));
        menuPanel.setBorder(null);

        menuPanel.setBounds(0, 0, getWidth() - 14, getHeight() - 37);
        menuPanel.setBorder(BorderFactory.createLineBorder(new Color(64, 64, 64), 20));

        JLabel tituloIndicativo = new JLabel("Teclas de movimiento");
        tituloIndicativo.setFont(new Font(tipografia, Font.BOLD, 50));
        tituloIndicativo.setBounds(50, 30, 700, 100);
        menuPanel.add(tituloIndicativo);

        JLabel teclasW = new JLabel("- 'W' : Arriba");
        teclasW.setFont(new Font(tipografia, Font.BOLD, 30));
        teclasW.setBounds(75, 130, 350, 35);
        menuPanel.add(teclasW);

        JLabel teclasA = new JLabel("- 'A' : Izquierda");
        teclasA.setFont(new Font(tipografia, Font.BOLD, 30));
        teclasA.setBounds(75, 175, 350, 35);
        menuPanel.add(teclasA);

        JLabel teclasS = new JLabel("- 'S' : Abajo");
        teclasS.setFont(new Font(tipografia, Font.BOLD, 30));
        teclasS.setBounds(75, 220, 350, 35);
        menuPanel.add(teclasS);

        JLabel teclasD = new JLabel("- 'D' : Derecha");
        teclasD.setFont(new Font(tipografia, Font.BOLD, 30));
        teclasD.setBounds(75, 265, 350, 35);
        menuPanel.add(teclasD);

        JLabel dificultadLabel = new JLabel("Dificultad :");
        dificultadLabel.setFont(new Font(tipografia, Font.BOLD, 20));
        dificultadLabel.setBounds(200, 320, 170, 30);
        menuPanel.add(dificultadLabel);

        String[] opcionesDificultad = {"Facil", "Intermedio", "Dificil"};
        JComboBox<String> dificultadComboBox = new JComboBox<>(opcionesDificultad);
        dificultadComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        dificultadComboBox.setBounds(240, 370, 150, 30);
        menuPanel.add(dificultadComboBox);

        JButton aceptarButton = new JButton("Iniciar");
        aceptarButton.setFont(new Font("Arial", Font.PLAIN, 18));
        aceptarButton.setBounds(420, 370, 130, 30);
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultadSeleccionada = dificultadComboBox.getSelectedIndex();
                dispose();
                new VentanaJuego(dificultadSeleccionada);
            }
        });
        menuPanel.add(aceptarButton);

        JLabel descripcionDificultadLabel = new JLabel("<html><div style='border: 2px solid black; padding: 5px; text-align: center;'>A mayor dificultad se mueve mas rapido la serpiente !</div></html>");
        descripcionDificultadLabel.setFont(new Font(tipografia, Font.BOLD, 14));
        descripcionDificultadLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descripcionDificultadLabel.setBounds(170, 450, 490, 70);
        menuPanel.add(descripcionDificultadLabel);

        add(menuPanel);
        setLayout(null);
        setVisible(true);
    }

    protected int getDificultad() {
        return dificultadSeleccionada;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OpcionesYControlesVentana ventana = new OpcionesYControlesVentana();
            ventana.setVisible(true);
            int dificultad = ventana.getDificultad();
            System.out.println("Dificultad seleccionada: " + dificultad);
        });
    }
}
