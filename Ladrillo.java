import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Write a description of class Ladrillo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ladrillo extends Rectangle
{
    private static final float ALTO_LADRILLO = 10;
    private static final float ANCHO_LADRILLO = 30;
    private static final Color COLOR_DEL_LADRILLO = Color.GREEN;
    private static final Color OUTLINE_DEL_LADRILLO = Color.BLACK;
    
    public Ladrillo(float posicionX, float posicionY)
    {
        super();
        setHeight(ALTO_LADRILLO);
        setWidth(ANCHO_LADRILLO);
        setY(posicionY);
        setX(posicionX);
        setFill(COLOR_DEL_LADRILLO);
        setStroke(OUTLINE_DEL_LADRILLO);
    }
    
    public float getAnchoLadrillo(){
        return ANCHO_LADRILLO;
    }
    
    public float getAltoLadrillo(){
        return ALTO_LADRILLO;
    }
    
    public void golpearLadrillo(){
        
    }
    
}
