import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Write a description of class Plataforma here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plataforma extends Rectangle
{
    private static final float ALTO_PLATAFORMA = 10;
    private static final float ANCHO_PLATAFORMA = 200;
    private static final float DISTANCIA_INFERIOR = 20;
    private static final Color COLOR_DE_LA_PLATAFORMA = Color.BLUE;
    private float limiteEnX;
    private int velocidad;
    
    public Plataforma(float anchoEscena, float altoEscena)
    {
        super();
        setHeight(ALTO_PLATAFORMA);
        setWidth(ANCHO_PLATAFORMA);
        setY(altoEscena-DISTANCIA_INFERIOR);
        setX(anchoEscena/2-ANCHO_PLATAFORMA/2);
        setFill(COLOR_DE_LA_PLATAFORMA);
        limiteEnX = anchoEscena;
        velocidad = 1;
    }
    
    
    public void cambiarDireccion(Direccion direccion){
        switch(direccion){
            case DERECHA:
            velocidad = 1;
            break;
            case IZQUIERDA:
            velocidad = -1;
            break;
        }
    }
    
    public void mover(){
        setX(getX()+velocidad);
        if (getBoundsInParent().getMinX() == 0 || getBoundsInParent().getMaxX() == limiteEnX){
            velocidad = 0;
        }
    }
}
