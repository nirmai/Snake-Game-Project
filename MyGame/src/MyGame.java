import javax.swing.JFrame;
import java.awt.*;

public class MyGame {
    public static void main(String [] args){
        JFrame frame = new JFrame("My Game");
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setTitle("Calc");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
