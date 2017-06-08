import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

/**
 * Write a description of class JuegoAvanzado here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JuegoAvanzado extends Application
{
    private static final float ANCHO_DE_LA_ESCENA = 500;
    private static final float ALTO_DE_LA_ESCENA = 700;
    private static final int CANTIDAD_DE_LADRILLOS = 40;
    
    private int contadorTimer, seg, min;
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        Random rnd = new Random();
        Pane panel = new Pane();
        Scene escena = new Scene(panel, ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA);
        primaryStage.setScene(escena);
        
        
        Label cronometro = new Label();
        panel.getChildren().add(cronometro);
        
        Timer tm = new Timer();
        contadorTimer = 0;
        tm.schedule(new TimerTask(){
                @Override
                public void run() {
                    contadorTimer++;
                    seg = contadorTimer % 60;
                    min = contadorTimer / 60;
                }
            },0,1000);
        
        Label score = new Label("0");
        score.setLayoutX(ANCHO_DE_LA_ESCENA-20);
        panel.getChildren().add(score);
        
        Bola bola = new Bola(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA);
        panel.getChildren().add(bola);
        
        Plataforma plataforma = new Plataforma(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA);
        panel.getChildren().add(plataforma);
        
        ArrayList<Ladrillo> ladrillos = new ArrayList<>();
        int i = 0;
        while(i<CANTIDAD_DE_LADRILLOS){
            //Hay que pasar el tamaño de la escena por parametro
            // Ladrillo ladrillo = new Ladrillo();
            

            // boolean huecoLibre = true;
            // int ladrilloActual = 0;
            // while(ladrilloActual < ladrillos.size() && huecoLibre){
                // Shape interseccion = Shape.intersect(ladrillo, ladrillos.get(ladrilloActual));
                // if (interseccion.getBoundsInParent().getWidth() != -1) huecoLibre = false;
                // ladrilloActual++;
            // }

            // if(huecoLibre){
                // panel.getChildren().add(ladrillo);
                // ladrillos.add(ladrillo);
                // i++;
            // }
        }
        
        
        
        
        
        
        
        escena.setOnKeyPressed(event ->{
                if(event.getCode() == KeyCode.RIGHT){
                    plataforma.cambiarDireccion(Direccion.DERECHA);
                }
                if(event.getCode() == KeyCode.LEFT){
                    plataforma.cambiarDireccion(Direccion.IZQUIERDA);
                }  
            });
        
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(10), (event) -> {
                    bola.actualizar();
                    plataforma.mover();
                    bola.chocaCon(plataforma);
                    
                    
                    
                    cronometro.setText(String.format("%02d:%02d",min,seg));
                    
                });
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        
        
        
        
        primaryStage.show();
    }
}
