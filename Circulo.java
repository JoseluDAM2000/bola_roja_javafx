import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

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
    }
}
