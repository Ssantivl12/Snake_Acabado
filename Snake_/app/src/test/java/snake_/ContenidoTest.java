package snake_;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContenidoTest {

    private VentanaJuego ventana;
//------------------------Verifica el seguimiento correcto del cuerpo a la dir que va
    @Test
    void testSeguimientoCorrectoCuerpo(){
        Contenido contenido = new Contenido(ventana, 110); 
        int[] posicionesInicialesX = { 100, 100, 100 }; 
        int[] posicionesInicialesY = { 100, 75, 50 };   
        
        contenido.serpienteX = posicionesInicialesX;
        contenido.serpienteY = posicionesInicialesY;
        contenido.direccion = 's'; 

        contenido.moverSerpiente(); 

        int[] posicionesFinalesX = contenido.serpienteX;
        int[] posicionesFinalesY = contenido.serpienteY;

        assertEquals(posicionesInicialesX[0], posicionesFinalesX[0]); // La cabeza no se mueve en X
        assertEquals(posicionesInicialesY[0], posicionesFinalesY[0]); 

        assertEquals(posicionesInicialesX[1], posicionesFinalesX[1]);
        assertEquals(posicionesInicialesY[1], posicionesFinalesY[1]);

        assertEquals(posicionesInicialesX[2], posicionesFinalesX[2]);
        assertEquals(posicionesInicialesY[2], posicionesFinalesY[2]);

    }

    @Test
        void juegoGanado(){
        Contenido contenido = new Contenido(ventana,110);
        
        while (!contenido.juegoGanado()) {
            contenido.tamCuerpoSerpiente++;
        }

        assertTrue(contenido.juegoGanado());
    }

    @Test
    void verificarComidaEnPosicionValida() {
        Contenido contenido = new Contenido(ventana,110); 

        int comidaXInicial = contenido.comidaX;
        int comidaYInicial = contenido.comidaY;
    

        assertTrue(comidaXInicial >= 0 && comidaXInicial < Contenido.PANTALLA);
        assertTrue(comidaYInicial >= 0 && comidaYInicial < Contenido.PANTALLA);

        contenido.serpienteX[0] = comidaXInicial;
        contenido.serpienteY[0] = comidaYInicial;
        contenido.revisarComida(); 

        assertNotEquals(comidaXInicial, contenido.comidaX);
        assertNotEquals(comidaYInicial, contenido.comidaY);

        assertTrue(contenido.comidaX >= 0 && contenido.comidaX < Contenido.PANTALLA);
        assertTrue(contenido.comidaY >= 0 && contenido.comidaY < Contenido.PANTALLA);
    }
    
}
