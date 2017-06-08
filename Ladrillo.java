import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.Random;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

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
    private static final int MARGEN_SUPERIOR_GENERACION = 18;

    public Ladrillo(float anchoDeLaEscena, float altoDeLaEscena)
    {
        super();
        Random rnd = new Random();
        setHeight(ALTO_LADRILLO);
        setWidth(ANCHO_LADRILLO);
        setY(rnd.nextInt((int)altoDeLaEscena/2-(int)ALTO_LADRILLO*2)+ MARGEN_SUPERIOR_GENERACION);
        setX(rnd.nextInt((int)anchoDeLaEscena-(int)ANCHO_LADRILLO));
        setFill(COLOR_DEL_LADRILLO);
        setStroke(OUTLINE_DEL_LADRILLO);
    }

    public float getAnchoLadrillo(){
        return ANCHO_LADRILLO;
    }

    public float getAltoLadrillo(){
        return ALTO_LADRILLO;
    }

    public static ArrayList<Ladrillo> generarLadrillos(int numeroDeLadrillos, float anchoDeLaEscena, float altoDeLaEscena){
        ArrayList<Ladrillo> ladrillos = new ArrayList<>();
        int i = 0;
        while(i<numeroDeLadrillos){
            Ladrillo ladrillo = new Ladrillo(anchoDeLaEscena, altoDeLaEscena);
            boolean huecoLibre = true;
            int ladrilloActual = 0;
            while(ladrilloActual < ladrillos.size() && huecoLibre){
                Shape interseccion = Shape.intersect(ladrillo, ladrillos.get(ladrilloActual));
                if (interseccion.getBoundsInParent().getWidth() != -1) huecoLibre = false;
                ladrilloActual++;
            }
            if(huecoLibre){
                ladrillos.add(ladrillo);
                i++;
            }
        }
        return ladrillos;
    }
}
