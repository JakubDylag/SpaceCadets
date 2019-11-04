package sample;

/*
https://openjfx.io/openjfx-docs/
https://www.youtube.com/watch?v=KvtqeYpvrnk
*/

import com.sun.prism.Image;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    private GraphicsContext g;
    private double t = 0.0;
    private double oldX = 400, oldY = 300;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(800,600);

        Canvas canvas = new Canvas(800,600);
        g = canvas.getGraphicsContext2D();

        Equation circle = new Equation(2,0.1,0.09, 150 ,0.1, 400, 300, Color.BLACK);
        Equation circle2 = new Equation(2,1.3,1.3, 150 ,0.1, 400, 300, Color.GREEN);

        ArrayList<Equation> toDraw = new ArrayList<>();
        toDraw.add(circle);
        toDraw.add(circle2);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(Equation e: toDraw){
                    e.nextPoint();
                    e.drawNextPoint(g);
                }
            }
        };
        timer.start();

        root.getChildren().add(canvas);
        return root;
    }

    private void saveScreenshot(Scene s)  {
        BufferedImage image = SwingFXUtils.fromFXImage(s.snapshot(null), null);

        try {
            ImageIO.write(image, "png", new File("screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene s = new Scene(createContent());
        s.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.ENTER){
                saveScreenshot(s);
            }
        });

        primaryStage.setScene(s);

        primaryStage.setTitle("Spirograph");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
