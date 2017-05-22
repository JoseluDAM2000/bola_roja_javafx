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
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

import javafx.scene.input.KeyEvent;
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

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        enEjecucion = true;
        deltaX = 1;
        deltaY = 1;

        Random rnd = new Random();

        Pane panel = new Pane();
        Circle circle = new Circle(rnd.nextInt(LADO-RADIO_CIRCULO*2)+RADIO_CIRCULO, rnd.nextInt(LADO-RADIO_CIRCULO*2)+RADIO_CIRCULO, RADIO_CIRCULO, Color.RED);
        Scene escena = new Scene(panel, LADO, LADO);
        panel.getChildren().add(circle);
        primaryStage.setScene(escena);
        primaryStage.show();

        final Bounds bounds = panel.getBoundsInParent();

        Rectangle rectangle = new Rectangle(ANCHO_RECTANGULO, ALTO_RECTANGULO, Color.BLUE);
        rectangle.setX(bounds.getMaxX()/2 - ANCHO_RECTANGULO/2);
        rectangle.setY(bounds.getMaxY() - DISTANCIA_INFERIOR_RECTANGULO);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);

        panel.getChildren().add(rectangle);

        escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case LEFT:  
                        goWest  = true; 
                        goEast = false;
                        break;
                        case RIGHT: 
                        goEast  = true;
                        goWest = false;
                        break;
                    }
                }
            });
        
        
        
        KeyFrame kf = new KeyFrame(Duration.millis(10), (event) -> {
                    final boolean atRightBorder = circle.getBoundsInParent().getMaxX() >= (bounds.getMaxX());
                    final boolean atLeftBorder = circle.getBoundsInParent().getMinX() <= (bounds.getMinX());
                    final boolean atBottomBorder = circle.getBoundsInParent().getMaxY() >= (bounds.getMaxY());
                    final boolean atTopBorder = circle.getBoundsInParent().getMinY() <= (bounds.getMinY());
                    if (atRightBorder || atLeftBorder) {
                        deltaX *= -1;
                    }
                    if (atBottomBorder || atTopBorder) {
                        deltaY *= -1;
                    }

                    circle.setLayoutX(circle.getLayoutX() + deltaX);
                    circle.setLayoutY(circle.getLayoutY() + deltaY);
                    
                    if (goEast)  movimientoRectangulo = 1;
                    if (goWest)  movimientoRectangulo = -1;
                    rectangle.setLayoutX(rectangle.getLayoutX() + movimientoRectangulo);

                });
        Timeline timeline = new Timeline(kf);
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
