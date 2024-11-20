/* Nirmai Puthanveettil
7/23/22
referenced from:
Code, Bro, director. Java Snake Game 🐍.
Youtube.com, 2020, https://www.youtube.com/watch?v=bI6e6qjJ8JQ
. Accessed 2022.
*/
import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame() {
        GamePanel panel = new GamePanel();
        //combines the panel to the frame
        this.add(panel);

        this.setTitle("Worm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //will fit JFrame to all components added to frame
        this.pack();

        this.setVisible(true);
        //will put window in middle of screen
        this.setLocationRelativeTo(null);

   }
}
