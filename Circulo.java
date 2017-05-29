import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Label;

/**
 * Write a description of class Bola here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Circulo extends Application
{
    private boolean enEjecucion;

    private final int LADO=500;
    private final int RADIO_CIRCULO = 25;

    private final int ANCHO_RECTANGULO = 100;
    private final int ALTO_RECTANGULO = 10;
    private final int DISTANCIA_INFERIOR_RECTANGULO = 50;

    private double deltaX;
    private double deltaY;

    private boolean goEast, goWest;
    private int movimientoRectangulo = 1;
    
    private int contador, seg, min, puntuacion;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        enEjecucion = true;
        deltaX = 1;
        deltaY = 1;
        contador = 0;
        seg = 0;
        min = 0;
        puntuacion = 0;
        
        primaryStage.setTitle("Mira la bolita, la bolita, la bolita, la bolita...");

        Random rnd = new Random();

        Label cronometro = new Label();
        cronometro.setLayoutX(50);
        cronometro.setLayoutY(50);
        
        Label score = new Label("0");
        score.setLayoutX(LADO-50);
        score.setLayoutY(50);
        
        
        Timer tm = new Timer();
        tm.schedule(new TimerTask(){
                @Override
                public void run() {
                    contador++;
                    seg = contador % 60;
                    min = contador / 60;
                }
            },0,1000);
        
        
        Pane panel = new Pane();
        Circle circle = new Circle(rnd.nextInt(LADO-RADIO_CIRCULO*2)+RADIO_CIRCULO, rnd.nextInt(LADO-RADIO_CIRCULO*2)+RADIO_CIRCULO, RADIO_CIRCULO, Color.RED);
        Scene escena = new Scene(panel, LADO, LADO);
        panel.getChildren().add(circle);
        primaryStage.setScene(escena);
        primaryStage.show();
        panel.getChildren().add(cronometro);
        panel.getChildren().add(score);

        final Bounds bounds = panel.getBoundsInParent();

        Rectangle rectangle = new Rectangle(ANCHO_RECTANGULO, ALTO_RECTANGULO, Color.BLUE);
        rectangle.setX(bounds.getMaxX()/2 - ANCHO_RECTANGULO/2);
        rectangle.setY(bounds.getMaxY() - DISTANCIA_INFERIOR_RECTANGULO);

        panel.getChildren().add(rectangle);

        Rectangle[] posiciones = new Rectangle[10];
        for(int i = 0; i<4; i++){
            Rectangle ladrillo = new Rectangle(LADO/10, 10, Color.GREEN);
            
            ladrillo.setStroke(Color.BLACK);
            int posicion = rnd.nextInt(10);
            
            while(posiciones[posicion] != null){
                posicion = rnd.nextInt(10);
            }
            posiciones[posicion] = ladrillo;
            ladrillo.setX(LADO/10 * posicion);
            
            ladrillo.setY(bounds.getMinY() + 30);
            panel.getChildren().add(ladrillo);
        }
        
        
        
        escena.setOnKeyPressed(event ->{
                if(event.getCode() == KeyCode.RIGHT && rectangle.getBoundsInParent().getMaxX() != escena.getWidth()){
                    movimientoRectangulo = 1;
                }

                if(event.getCode() == KeyCode.LEFT && rectangle.getBoundsInParent().getMinX() != 0){
                    movimientoRectangulo = -1;
                }  

            });
            
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(10), (event) -> {
                    Bounds bordesCircle = circle.getBoundsInParent();
            
                    final boolean atRightBorder = circle.getBoundsInParent().getMaxX() >= (bounds.getMaxX());
                    final boolean atLeftBorder = circle.getBoundsInParent().getMinX() <= (bounds.getMinX());
                    final boolean atBottomBorder = circle.getBoundsInParent().getMaxY() >= (bounds.getMaxY());
                    final boolean atTopBorder = circle.getBoundsInParent().getMinY() <= (bounds.getMinY());

                    final boolean golpeBarra = circle.getBoundsInParent().getMaxY() == rectangle.getBoundsInParent().getMinY();
                    final boolean sobreBarra = circle.getBoundsInParent().getMaxX() < rectangle.getBoundsInParent().getMaxX() + RADIO_CIRCULO && circle.getBoundsInParent().getMinX() > rectangle.getBoundsInParent().getMinX() - RADIO_CIRCULO;

                    if (atRightBorder || atLeftBorder) {
                        deltaX *= -1;
                    }
                    if (atTopBorder || (sobreBarra && golpeBarra)) {
                        deltaY *= -1;
                    }

                    circle.setLayoutX(circle.getLayoutX() + deltaX);
                    circle.setLayoutY(circle.getLayoutY() + deltaY);

                    if (goEast)  movimientoRectangulo = 1;
                    if (goWest)  movimientoRectangulo = -1;
                    rectangle.setLayoutX(rectangle.getLayoutX() + movimientoRectangulo);

                    if(rectangle.getBoundsInParent().getMinX() == 0 || rectangle.getBoundsInParent().getMaxX() == panel.getWidth()){
                        movimientoRectangulo = 0;
                    }
                    
                    cronometro.setText(String.format("%02d:%02d",min,seg));
                    if (circle.getBoundsInParent().getMinY() > escena.getHeight()){
                            Label etiquetaGameOver = new Label("Game Over");
                            etiquetaGameOver.setLayoutX(200);
                            etiquetaGameOver.setLayoutY(425);
                            panel.getChildren().add(etiquetaGameOver);
                            timeline.stop();
                        }
                        
                    for(int i = 0; i<posiciones.length ;i++){
                        if(posiciones[i]!=null && posiciones[i].intersects(bordesCircle)){
                            posiciones[i].setFill(Color.TRANSPARENT);
                            posiciones[i].setStroke(Color.TRANSPARENT);
                            posiciones[i] = null;
                            puntuacion++;
                            score.setText(String.valueOf(puntuacion));
                        }
                    }
                });
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        Button btn = new Button("Parar");
        btn.setOnAction(event -> {
                if(enEjecucion){
                    timeline.stop();
                }else{
                    timeline.play();
                }
                modificarEjecucion();
            });
        panel.getChildren().add(btn);
    }

    private void modificarEjecucion(){
        enEjecucion = !enEjecucion;
    }
}
