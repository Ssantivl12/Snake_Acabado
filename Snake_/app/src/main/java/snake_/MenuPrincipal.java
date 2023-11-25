package snake_;


import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPrincipal extends JFrame {

    private JButton btnJugar;
    private JButton btnOpciones;
    private String fuente = "Arial Black";

    public MenuPrincipal() {
        
        setTitle("Snake");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        JLabel lblTitulo = new JLabel("Snake");
        lblTitulo.setFont(new Font(fuente, Font.BOLD, 100));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(200, 120, 400, 90);

        btnJugar = new JButton("Jugar");
        estilizarBoton(btnJugar, 200, 250, 400, 80);

        btnOpciones = new JButton("Opciones y Controles");
        estilizarBoton(btnOpciones, 200, 350, 400, 80);


        JPanel menuPanel = new JPanel(null); 
        menuPanel.setBackground(new Color(180,207,70));

        int distanciaBordes = 40;
        menuPanel.setBounds(distanciaBordes, distanciaBordes, getWidth() - 2 * distanciaBordes, getHeight() - 2 * distanciaBordes);
        menuPanel.setBorder(new LineBorder(new Color(64, 64, 64), 20));


        menuPanel.add(lblTitulo);
        menuPanel.add(btnJugar);
        menuPanel.add(btnOpciones);

        add(menuPanel);
        setVisible(true);
    }

    
    private void estilizarBoton(JButton boton, int x, int y, int width, int height) {
        boton.setFont(new Font(fuente, Font.PLAIN, 24));
        boton.setBackground(new Color(64, 64, 64));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setMargin(new Insets(10, 10, 10, 10));
        boton.setBounds(x, y, width, height);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnJugar) {
                    new VentanaJuego(0);
                    // dispose();
                } else if (e.getSource() == btnOpciones) {
                    new OpcionesYControlesVentana(); 
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal();
        });
    }
}
