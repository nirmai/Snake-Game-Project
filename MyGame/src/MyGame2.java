import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class MyGame2 extends JPanel{
    public static void main(String[] args){
        JFrame frame = new JFrame("My Game");
        frame.add(new MyGame2());
        frame.setSize(300, 300);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        }

    @Override
    public void paint(Graphics gr){
        Graphics2D ourCanvas = (Graphics2D) gr;
        ourCanvas.setColor((Color.RED));
        ourCanvas.fillOval(0,0,30,30);
        ourCanvas.drawOval(0,50,30,30);
        ourCanvas.fillRect(50,0,30,30);
        ourCanvas.drawRect(50,50,30,30);
        ourCanvas.draw(new Ellipse2D.Double(0,100,30,30));
        }
    }


