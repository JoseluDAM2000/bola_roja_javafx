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
import javafx.scene.text.TextAlignment;

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
    private static final String MENSAJE_GAME_OVER = "Game Over\nPulsa Esc para salir";
    private static final float ANCHO_MENSAJE_GAME_OVER = 100;
    
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
        
        Label puntuacion = new Label("0");
        puntuacion.setLayoutX(ANCHO_DE_LA_ESCENA-20);
        panel.getChildren().add(puntuacion);
        
        Bola bola = new Bola(puntuacion, ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA);
        panel.getChildren().add(bola);
        
        Plataforma plataforma = new Plataforma(ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA);
        panel.getChildren().add(plataforma);
        
        ArrayList<Ladrillo> ladrillos = Ladrillo.generarLadrillos(CANTIDAD_DE_LADRILLOS, ANCHO_DE_LA_ESCENA, ALTO_DE_LA_ESCENA);
        panel.getChildren().addAll(ladrillos);
        
        escena.setOnKeyPressed(event ->{
                if(event.getCode() == KeyCode.RIGHT){
                    plataforma.cambiarDireccion(Direccion.DERECHA);
                }
                if(event.getCode() == KeyCode.LEFT){
                    plataforma.cambiarDireccion(Direccion.IZQUIERDA);
                }  
                //Codigo adicional por comodidad
                if(event.getCode() == KeyCode.ESCAPE){
                    System.exit(0);
                }
            });
        
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(10), (event) -> {
                    bola.actualizar();
                    plataforma.mover();
                    bola.chocaCon(plataforma);
                    bola.chocaCon(ladrillos);
                    if(bola.perdida()){
                        Label etiquetaGameOver = new Label(MENSAJE_GAME_OVER);
                        etiquetaGameOver.setTextAlignment(TextAlignment.CENTER);
                        etiquetaGameOver.setMaxWidth(ANCHO_MENSAJE_GAME_OVER);
                        etiquetaGameOver.setLayoutX(ANCHO_DE_LA_ESCENA / 2 - ANCHO_MENSAJE_GAME_OVER / 2);
                        etiquetaGameOver.setLayoutY(ALTO_DE_LA_ESCENA / 2);
                        panel.getChildren().add(etiquetaGameOver);
                        timeline.stop();
                    }
                    cronometro.setText(String.format("%02d:%02d",min,seg));
                });
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        primaryStage.show();
    }
}
