package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Equation {
    private double R, r, o, scale, Tchange, centerX, centerY;
    private double t = 0;
    private double oldX, oldY;
    private Color color;

    public Equation (double R, double r, double o, double scale,double Tchange, double centerX, double centerY) {
        this.R = R;
        this.r = r;
        this.o = o;
        this.scale = scale;
        this.Tchange = Tchange;
        this.centerX = centerX;
        this.centerY = centerY;
        this.color = Color.BLACK;
    }

    public Equation (double R, double r, double o, double scale,double Tchange, double centerX, double centerY, Color color) {
        this.R = R;
        this.r = r;
        this.o = o;
        this.scale = scale;
        this.Tchange = Tchange;
        this.centerX = centerX;
        this.centerY = centerY;
        this.color = color;
    }


    public void nextPoint() {
        t += Tchange;
    }


    public Point2D runFunction(double t) {
        double x = scale*( (R-r)*cos(t) + o*cos(((R-r)/r)*t) );
        double y = scale*( (R-r)*sin(t) - o*sin(((R-r)/r)*t)  );

        Point2D p = new Point2D.Double(x, y);

        return p;
    }

    public void drawNextPoint(GraphicsContext g) {
        Point2D p = runFunction(t);

        double newX = centerX+ p.getX();
        double newY = centerY + p.getY();

        g.setStroke(color);
        //stops drawing from start, as no old point yet
        if (this.oldX != 0 && this.oldY != 0){
            g.strokeLine(this.oldX,this.oldY,newX, newY);
        }

        this.oldX = newX;
        this.oldY = newY;
    }
}
