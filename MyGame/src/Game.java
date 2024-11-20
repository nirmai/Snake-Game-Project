import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Game extends JPanel {
    int x = 0;
    int y = 0;

    private void moveBall() {
        x = x + 1;
        y = y + 1;
    }

    @Override
    public void paint(Graphics gr){
        super.paint(gr);
        Graphics2D myCanvas = (Graphics2D)gr;
        myCanvas.fillOval(x,y,30,30);
        //myCanvas.setRenderingHints(RenderingHints.VALUE_ANTIALIAS_ON);
        }


    public static void main(String[] args) throws InterruptedException{
    JFrame frame = new JFrame("My Game");
    Game game = new Game();
    frame.add(game);
    frame.setSize(300, 400);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    while(true){
        game.moveBall();
        game.repaint();
        Thread.sleep(10);
    }
}


}
