/* Nirmai Puthanveettil
7/23/22
referenced from:
Code, Bro, director. Java Snake Game 🐍.
Youtube.com, 2020, https://www.youtube.com/watch?v=bI6e6qjJ8JQ
. Accessed 2022.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 750;
    static final int SCREEN_HEIGHT = 750;
    //How big the objects will be
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 175;
    //Int Arrays that hold coords of bodyparts
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    //Creates the "panel" which has the background
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        //lets a custom color be created by inputting rgb values
        this.setBackground(new Color(33,49,27));
        //indicates whether a component can gain the focus if its requested
        this.setFocusable(true);
        //An adapter class for receiving keyboard events
        this.addKeyListener(new MyKeyAdapter());
        //method that contains the necessary prompts to start
        startGame();
    }
    //method to start game
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
        if (running) {
           for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
               g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
               g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
               g.setColor(new Color(72,111,56));
            }
            //draws Apples
            g.setColor((Color.RED));
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            for (int i = 0; i < bodyParts; i++) {
                // draws the head
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }

                // draws the body
                else {
                        g.setColor(new Color(45, 180, 0));
                        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                //ScoreBoard
                displayStat(g);
            }
        }
        else{
            // a method that consists of game over text shown when player dies
            gameOver(g);
        }
    }

    // gen coords of new apples
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }
    //moves snake
    public void move(){
        for(int i = bodyParts;i>0; i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch(direction){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0]= y[0]+UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;

        }
    }
    public void CheckApple(){
        if((x[0]==appleX) && (y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollisions(){
        //checks if head collides with body
        for(int i = bodyParts;i>0;i--){
            if(x[0] == x[i] && (y[0]==y[i])) {
                running = false;
            }
        }
        //check if head touches left border
        if(x[0]< 0) {
            running = false;
        }
        //if head touches R bord.
        if(x[0] > SCREEN_WIDTH) {
            running = false;
        }
        //if head touches top bord.
        if(y[0] < 0) {
            running = false;
        }
        // if head touches bottom b.
        if(y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if(!running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g){
        g.setColor(Color.RED);
        //sets font,size, and boldness
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("Score = "+applesEaten,10,g.getFont().getSize());
        g.drawString("GAME OVER!!",540,g.getFont().getSize());
    }
    public void displayStat (Graphics g){
        g.setColor(Color.RED);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("Score = "+applesEaten,10,g.getFont().getSize());

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            CheckApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
