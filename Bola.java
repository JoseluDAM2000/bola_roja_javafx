import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
import javafx.scene.control.Label;
import java.util.Iterator;

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
    private int velocidadY, velocidadX, ladrillosRotosPorLaBola;
    private float anchoDeLaEscena, altoDeLaEscena;
    private Label labelLadrillosRotosPorLaBola;

    public Bola(Label puntuacion, float anchoDeLaEscena, float altoDeLaEscena)
    {
        super();
        Random rnd = new Random();
        labelLadrillosRotosPorLaBola = puntuacion;
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
        final boolean atTopBorder = getBoundsInParent().getMinY() < (0);
        if (atRightBorder || atLeftBorder) {
            velocidadX *= -1;
        }
        if (atTopBorder) {
            velocidadY = 1;
        }
        setLayoutX(getLayoutX() + velocidadX);
        setLayoutY(getLayoutY() + velocidadY);
    }

    public void chocaCon(Plataforma figura){
        Shape interseccion = Shape.intersect(this, figura);
        if(interseccion.getBoundsInParent().getWidth() != -1){
            velocidadY = -1;
        }
    }

    public void chocaCon(ArrayList<Ladrillo> ladrillos){
        Iterator<Ladrillo> it = ladrillos.iterator();
        while(it.hasNext()){
            Ladrillo ladrilloAComprobar = it.next();
            Shape interseccion = Shape.intersect(this, ladrilloAComprobar);
            Bounds limitesInterseccion = interseccion.getBoundsInParent();
            if(limitesInterseccion.getWidth() != -1){
                ladrillosRotosPorLaBola++;
                labelLadrillosRotosPorLaBola.setText(String.valueOf(ladrillosRotosPorLaBola));
                ladrilloAComprobar.setFill(Color.TRANSPARENT);
                ladrilloAComprobar.setStroke(Color.TRANSPARENT);
                it.remove();
                if(limitesInterseccion.getWidth() > limitesInterseccion.getHeight()){
                    velocidadY *= -1;
                }else{
                    velocidadX *= -1;
                }
            }
        }
    }
    
    public boolean perdida(){
        return getBoundsInParent().getMinY() > altoDeLaEscena;
    }
}
