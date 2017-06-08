import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Write a description of class Bola here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bola extends Circle
{
    private static final double RADIO_DE_LA_BOLA = 5;
    private static final Paint COLOR_DE_LA_BOLA = Color.RED;
    private int velocidadY, velocidadX;
    
    public Bola(double posicionX, double posicionY)
    {
        super();
        setCenterX(posicionX);
        setCenterY(posicionY);
        setRadius(RADIO_DE_LA_BOLA);
        setFill(COLOR_DE_LA_BOLA);
        velocidadY = 1;
        velocidadX = 1;
    }
    
    public void actualizar(){
        setCenterX(getCenterX()+velocidadX);
        setCenterY(getCenterY()+velocidadY);
    }
}
