package snake_;

import javax.swing.JFrame;

public class VentanaJuego extends JFrame{


    public VentanaJuego(int dificultad){
        this.setTitle("Snake");
        this.add(new Contenido(this,getDificultad(dificultad)));
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    protected int getDificultad(int dificultad){
        if(dificultad == 0){
            return 110;
        }else if(dificultad == 1){
            return 95; 
        }else{
            return 70;
        }
    }
    
}
