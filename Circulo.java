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

/**
 * Write a description of class Bola here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Circulo extends Application
{
    boolean enEjecucion = true;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Pane panel = new Pane();
        Circle circle = new Circle(250, 250, 25, Color.RED);
        Scene escena = new Scene(panel, 500, 500);
        panel.getChildren().add(circle);
        primaryStage.setScene(escena);
        primaryStage.show();
        
        KeyFrame kf = new KeyFrame(Duration.millis(10), (event) -> {
            circle.setTranslateX(circle.getTranslateX()+1);
            circle.setTranslateY(circle.getTranslateY()+1);
        });
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
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
