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
    private int velocidad;
    
    public Plataforma(float anchoEscena)
    {
        super();
        setHeight(ALTO_PLATAFORMA);
        setWidth(ANCHO_PLATAFORMA);
        setY(DISTANCIA_INFERIOR);
        setX(anchoEscena/2-ANCHO_PLATAFORMA/2);
        setFill(COLOR_DE_LA_PLATAFORMA);
        velocidad = 1;
    }
    
    public void mover(Direccion direccion){
        switch(direccion){
            case DERECHA:
            velocidad = 1;
            break;
            case IZQUIERDA:
            velocidad = -1;
            break;
        }
        setX(getX()+velocidad);
    }
}
