package sample;

/*
ImageIcon
        ImageUtilities
        BufferedImage
        getRGB()
        setRGB()
*/

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

        //LOAD
            Image image = new Image(new FileInputStream("C:/Users/jakub/OneDrive - University of Southampton/1201 Programming 1/Space Cadets/6-Circle/redballs.jpg"));
            //Image image = new Image(new FileInputStream("C:/Users/jakub/OneDrive - University of Southampton/1201 Programming 1/Space Cadets/6-Circle/cartoonballjpg.jpg"), 100,100, false, false );

            int width = (int)image.getWidth();
            int height = (int)image.getHeight();
            WritableImage wImage = new WritableImage(width, height);
            WritableImage sImage = new WritableImage(width, height);

        //CONVERT to grey scale
            PixelReader pixelReader = image.getPixelReader();
            PixelWriter writer = wImage.getPixelWriter();
            PixelReader pixelReaderNew = wImage.getPixelReader();
            PixelWriter swriter = sImage.getPixelWriter();
            PixelReader sReader = sImage.getPixelReader();
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    Color c = pixelReader.getColor(x,y);

                    writer.setColor(x,y, c.grayscale());
                }
            }

        //SOBEL OPERATOR
            double max = 0, min = 2;
            for(int y = 1; y < height-1; y++) {
                for(int x = 1; x < width-1; x++) {
                    Color t = pixelReaderNew.getColor(x,y+1);
                    Color tl = pixelReaderNew.getColor(x-1,y+1);
                    Color ml = pixelReaderNew.getColor(x-1,y);
                    Color bl = pixelReaderNew.getColor(x-1,y-1);
                    Color b = pixelReaderNew.getColor(x,y-1);
                    Color tr = pixelReaderNew.getColor(x+1,y+1);
                    Color mr = pixelReaderNew.getColor(x+1,y);
                    Color br = pixelReaderNew.getColor(x+1,y-1);


                    double gx = tl.getBlue()*(-1) + ml.getBlue()*(-2) + bl.getBlue()*(-1) + tr.getBlue() + mr.getBlue()*2 + br.getBlue();
                    double gy = tl.getBlue()*(-1) + t.getBlue()*(-2) + tr.getBlue()*(-1) + bl.getBlue() + b.getBlue()*2 + br.getBlue();
                    double g = Math.sqrt( (gx*gx) + (gy*gy));


                    if (g > max) {
                        max = g;
                    }
                    if (g < min ) {
                        min = g;
                    }

                    if((g/3.03) > 0.4){
                        g = 1;
                    } else {
                        g = 0;
                    }

                    //System.out.println("------");

                    swriter.setColor(x,y, Color.gray(g));
                }
            }
            System.out.println(max);
            System.out.println(min);

        //circle detection hough transform


        //DRAW CIRCLE
            ImageView imageView = new ImageView(sImage);

            //Setting the position of the image
            imageView.setX(0);
            imageView.setY(0);

            //setting the fit height and width of the image view
            imageView.setFitHeight(455);
            imageView.setFitWidth(500);

            //Setting the preserve ratio of the image view
            imageView.setPreserveRatio(true);

            //Creating a Group object
            Group root = new Group(imageView);

            //Creating a scene object
            Scene scene = new Scene(root, 600, 500);

            //Setting title to the Stage
            primaryStage.setTitle("Loading an image");

            //Adding scene to the stage
            primaryStage.setScene(scene);

            //Displaying the contents of the stage
            primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
