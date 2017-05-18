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

/**
 * Write a description of class Bola here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Circulo extends Application
{
    

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
        
        Timeline timeline = new Timeline();
        final KeyValue kv = new KeyValue(circle.centerXProperty(), 600);
        final KeyFrame kf = new KeyFrame(Duration.millis(5000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        
    }
}