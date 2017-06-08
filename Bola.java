import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

/**
 * Write a description of class Bola here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bola extends Circle
{
    private static final float RADIO_DE_LA_BOLA = 5;
    private static final Paint COLOR_DE_LA_BOLA = Color.RED;
    private int velocidadY, velocidadX;
    private float anchoDeLaEscena, altoDeLaEscena;

    public Bola(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super();
        Random rnd = new Random();
        setCenterX(rnd.nextInt((int)anchoDeLaEscena-(int)RADIO_DE_LA_BOLA*2));
        setCenterY(rnd.nextInt((int)altoDeLaEscena-(int)RADIO_DE_LA_BOLA*2));
        setRadius(RADIO_DE_LA_BOLA);
        setFill(COLOR_DE_LA_BOLA);
        this.anchoDeLaEscena = anchoDeLaEscena;
        this.altoDeLaEscena = altoDeLaEscena;
        velocidadY = 1;
        velocidadX = 1;
    }

    public void actualizar(){
        Bounds bordesCircle = getBoundsInParent();

        final boolean atRightBorder = getBoundsInParent().getMaxX() >= (anchoDeLaEscena);
        final boolean atLeftBorder = getBoundsInParent().getMinX() <= (0);
        final boolean atTopBorder = getBoundsInParent().getMinY() <= (0);

        if (atRightBorder || atLeftBorder) {
            velocidadX *= -1;
        }
        if (atTopBorder) {
            velocidadY = -1;
        }

        setLayoutX(getLayoutX() + velocidadX);
        setLayoutY(getLayoutY() + velocidadY);

    }
    
    
    public void chocaCon(Shape figura){
        Shape interseccion = Shape.intersect(this, figura);
        if(interseccion.getBoundsInParent().getWidth() != -1){
            velocidadY = -1;
        }
    }
}
