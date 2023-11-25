package snake_;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.util.Random;


public class Contenido extends JPanel implements ActionListener{
 
    static final int PANTALLA = 600;
    static final int TAM_CUADRO_DADO = 25;//tamaño de los elementos del juego
    static final int MAX_TAM_SERPIENTE= (PANTALLA*PANTALLA)/TAM_CUADRO_DADO;

    static final int DIMENSION_CUADROS= (int) PANTALLA /MAX_TAM_SERPIENTE; //Dimension de alto o largo (tener en cuenta que es un cuadrado)
    
    protected int [] serpienteX = new int[MAX_TAM_SERPIENTE];
    protected int [] serpienteY = new int[MAX_TAM_SERPIENTE];
    protected int tamCuerpoSerpiente =100;
    
    protected int comidaX;
    protected int comidaY;

    protected char direccion = 'd'; // movimiento a w s d

    private int delay; //Tiempo de delay del movimiento de la serpiete
    private boolean estadoJuego = true;
    private Timer tiempoDelay;

    Random random = new Random();
    
    private final VentanaJuego ventana;

    public Contenido(VentanaJuego ventana,int dificultad){
        this.ventana = ventana;
        this.delay = dificultad;
        this.setPreferredSize(new Dimension(PANTALLA,PANTALLA));
        this.setFocusable(true);
        this.addKeyListener(new Controles(this));
        this.setBackground(new Color(149,181,20));
        iniciarJuego();
    }
    
    //---------------------Lógica del juego

    private void iniciarJuego(){     

        agregarComida();
        tiempoDelay = new Timer(delay, this);
        tiempoDelay.start();
    }

    private void agregarComida() {
        do {
            comidaX = random.nextInt(PANTALLA / TAM_CUADRO_DADO) * TAM_CUADRO_DADO;
            comidaY = random.nextInt(PANTALLA / TAM_CUADRO_DADO) * TAM_CUADRO_DADO;
        } while (comidaEnSerpiente());
    }

    private boolean comidaEnSerpiente() {
        for (int i = 0; i < tamCuerpoSerpiente; i++) {
            if (serpienteX[i] == comidaX && serpienteY[i] == comidaY) {
                return true;
            }
        }
        return false;
    }
    
    protected void moverSerpiente(){
        for (int i = tamCuerpoSerpiente - 1; i > 0; i--) {
            serpienteX[i] = serpienteX[i - 1];
            serpienteY[i] = serpienteY[i - 1];
        }
        
        switch (direccion) {
            case 'a':
                serpienteX[0] = serpienteX[0] - TAM_CUADRO_DADO;
                break;
            case 'd':
                serpienteX[0] = serpienteX[0] + TAM_CUADRO_DADO;
                break;
            case 'w':
                serpienteY[0] = serpienteY[0] - TAM_CUADRO_DADO;
                break;
            case 's':
                serpienteY[0] = serpienteY[0] + TAM_CUADRO_DADO;
                break;
            default:
                break;
        }
    }    

    protected void revisarComida() {
        try {
            if (serpienteX[0] == comidaX && serpienteY[0] == comidaY){
                tamCuerpoSerpiente++;
                agregarComida();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
    

    private void revisarColisiones() {
        for (int i = 1; i < tamCuerpoSerpiente; i++) {
            if (serpienteX[0] == serpienteX[i] && serpienteY[0] == serpienteY[i]) {
                estadoJuego = false; // Colisión de la cabeza con el cuerpo
                break;
            }
        }
    
        if (serpienteX[0] < 0 || serpienteY[0] < 0 || serpienteX[0] >= PANTALLA || serpienteY[0] >= PANTALLA) {
            estadoJuego = false; // Colisión con los bordes del panel
        }
    }

    protected boolean confirmarEstadoJuego(){
        return estadoJuego;
    }

    protected void reiniciarJuego(){
        new VentanaJuego(delay);
    }
    
    protected void cambiarDireccion(char dir){
        direccion = dir;
    }

    protected boolean confirmarMovimiento(char dir) {
        if (tamCuerpoSerpiente < 2) {
            return true;
        }

        int nuevaCabezaX = serpienteX[0];
        int nuevaCabezaY = serpienteY[0];

        switch (dir) {
            case 'w':
                nuevaCabezaY -= TAM_CUADRO_DADO;
                break;
            case 's':
                nuevaCabezaY += TAM_CUADRO_DADO;
                break;
            case 'a':
                nuevaCabezaX -= TAM_CUADRO_DADO;
                break;
            case 'd':
                nuevaCabezaX += TAM_CUADRO_DADO;
                break;
            default:
                return false;
        }

        for (int i = 1; i < tamCuerpoSerpiente; i++) {
            if (nuevaCabezaX == serpienteX[i] && nuevaCabezaY == serpienteY[i]) {
                return false;
            }
        }

        return true;
    }

    protected boolean juegoGanado(){
        int puntuacionMax = PANTALLA/TAM_CUADRO_DADO;
        return tamCuerpoSerpiente == puntuacionMax;
    }

    protected int obtenerPuntuacion(){
        return tamCuerpoSerpiente;
    }

    protected int getTamMaxSerpiente(){
        return MAX_TAM_SERPIENTE;
    }

    public boolean juegoTerminado() {
        return !estadoJuego || juegoGanado();
    }
    
    private void mensajeGanastePerdiste(){
        if(juegoGanado()) {
            PanelJuegoTereminado panelPerdiste = new PanelJuegoTereminado(juegoGanado(), tamCuerpoSerpiente,PANTALLA/TAM_CUADRO_DADO);
            panelPerdiste.dialogoGanaste();
        }else{ 
            PanelJuegoTereminado panelPerdiste = new PanelJuegoTereminado(juegoGanado(), tamCuerpoSerpiente,PANTALLA/TAM_CUADRO_DADO);
            panelPerdiste.dialogoPerdiste();
        }   
    }

    
    //-----------------------Pintado y repintado Tablero

    @Override
    public void actionPerformed(ActionEvent e) {
        if (estadoJuego){
            moverSerpiente();
            revisarComida();
            revisarColisiones();
        }
        repaint();
        
        if(juegoTerminado()){
            tiempoDelay.stop();
            mensajeGanastePerdiste();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    
        g.setColor(new Color(45,54,42));    
        for (int i = 0; i <= PANTALLA; i += TAM_CUADRO_DADO) {
            g.drawLine(i, 0, i, PANTALLA);
            g.drawLine(0, i, PANTALLA, i);
        }
        g.setColor(new Color(115,37,37));
        g.fillOval(comidaX, comidaY, TAM_CUADRO_DADO,TAM_CUADRO_DADO);

        g.setColor(new Color(38,38,38));
        for(int i = 0; i < tamCuerpoSerpiente; i++){
            g.fillRect(serpienteX[i],serpienteY[i],TAM_CUADRO_DADO - 1, TAM_CUADRO_DADO - 1);
        }
    }
    
    //-------------------------------Controles

    
    public class Controles extends KeyAdapter {
        
        private Contenido contenido;


        public Controles(Contenido contenido) {
            this.contenido = contenido;
        }
        

        @Override
        public void keyPressed(KeyEvent e) {
            boolean confirmadorMov;
            switch (e.getKeyChar()) {
                case 'w':
                    confirmadorMov = confirmarMovimiento('w');
                    if (confirmadorMov) {
                        cambiarDireccion('w');
                    }
                    break;
                case 's':
                    confirmadorMov = confirmarMovimiento('s');
                    if (confirmadorMov) {
                        cambiarDireccion('s');
                    }
                    break;
                case 'a':
                    confirmadorMov = confirmarMovimiento('a');
                    if (confirmadorMov) {
                        cambiarDireccion('a');
                    }
                    break;
                case 'd':
                    confirmadorMov = confirmarMovimiento('d');
                    if (confirmadorMov) {
                        cambiarDireccion('d');
                    }
                    break;
                case 'r':
                    reiniciarJuego();
                    ventana.dispose();
                    break ; 
            
                default:
                    break;
            }
        } 
   }


}