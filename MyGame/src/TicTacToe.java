import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

        public class TicTacToe extends JPanel {

// A simple program to demonstrate
// Tic-Tac-Toe Game.

            static String[] board;
            static String turn;


            // CheckWinner method will
            // decide the combination
            // of three box given below.
            static String checkWinner() {
                for (int a = 0; a < 8; a++) {
                    String line = null;

                    switch (a) {
                        case 0:
                            line = board[0] + board[1] + board[2];
                            break;
                        case 1:
                            line = board[3] + board[4] + board[5];
                            break;
                        case 2:
                            line = board[6] + board[7] + board[8];
                            break;
                        case 3:
                            line = board[0] + board[3] + board[6];
                            break;
                        case 4:
                            line = board[1] + board[4] + board[7];
                            break;
                        case 5:
                            line = board[2] + board[5] + board[8];
                            break;
                        case 6:
                            line = board[0] + board[4] + board[8];
                            break;
                        case 7:
                            line = board[2] + board[4] + board[6];
                            break;
                    }
                    //For X winner
                    if (line.equals("XXX")) {
                        return "X";
                    }

                    // For O winner
                    else if (line.equals("OOO")) {
                        return "O";
                    }
                }

                for (int a = 0; a < 9; a++) {
                    if (Arrays.asList(board).contains(
                            String.valueOf(a + 1))) {
                        break;
                    } else if (a == 8) {
                        return "draw";
                    }
                }

                // To enter the X Or O at the exact place on board.
                System.out.println(
                        turn + "'s turn; enter a slot number to place "
                                + turn + " in:");
                return null;
            }

            // To print out the board.
    /* |---|---|---|
       | 1 | 2 | 3 |
       |-----------|
       | 4 | 5 | 6 |
       |-----------|
       | 7 | 8 | 9 |
       |---|---|---|*/

            public void PaintBoard(Graphics2D canvas) {
                int height = getHeight();
                int width = getWidth();
                int sqCenterX = height / 3;
                int sqCenterY = width / 3;

                for (int i = 0; i < 9; i++) {
                    int boxX = i * sqCenterX + sqCenterX / 2;
                    int boxY = i * sqCenterY + sqCenterY / 2;
                    if (board[i] == "X")
                        canvas.drawOval(boxX, boxY, sqCenterX / 4, sqCenterY / 4);
                    else
                        canvas.drawRect(boxX, boxY, sqCenterX / 4, sqCenterY / 4);
                }

            }

            static void printBoard() {
                System.out.println("|---|---|---|");
                System.out.println("| " + board[0] + " | "
                        + board[1] + " | " + board[2]
                        + " |");
                System.out.println("|-----------|");
                System.out.println("| " + board[3] + " | "
                        + board[4] + " | " + board[5]
                        + " |");
                System.out.println("|-----------|");
                System.out.println("| " + board[6] + " | "
                        + board[7] + " | " + board[8]
                        + " |");
                System.out.println("|---|---|---|");

            }


            public void paint1(Graphics gr) {
                Graphics2D ourCanvas = (Graphics2D) gr;
                ourCanvas.setColor(Color.RED);
                ourCanvas.fillOval(0, 0, 30, 30);
                ourCanvas.drawOval(0, 50, this.getWidth() - 30, this.getHeight() - 30);
            }

            @Override
            public void paint(Graphics gr) {
                Graphics2D canvas = (Graphics2D) gr;
                gr.setColor(Color.RED);
                gr.drawRoundRect(0, 0, 500, 500, 10, 10);

                int canvasHeight = getHeight();
                int canvasWidth = getWidth();
                int sqHeight = canvasHeight / 3;
                int sqWidth = canvasWidth / 3;

                //draw 3 horizontal lines


                gr.drawLine(0, 0, canvasWidth, 0);
                gr.drawLine(0, sqHeight, canvasWidth, sqHeight);
                gr.drawLine(0, 2 * sqHeight, canvasWidth, 2 * sqHeight);

                // Draw vertical lines
                gr.setColor(Color.GREEN);
                gr.drawLine(0, 0, 0, canvasHeight);
                gr.drawLine(sqWidth, 0, sqWidth, canvasHeight);
                gr.drawLine(2 * sqWidth, 0, 2 * sqWidth, 2 * canvasHeight);

                PaintBoard(canvas);
            }

            public static void main(String[] args) {
                JFrame frame = new JFrame("Tic-Tac-Toe");

                frame.setSize(500, 500);

                frame.add(new TicTacToe());
                Scanner in = new Scanner(System.in);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                board = new String[9];
                turn = "X";
                String winner = null;

                for (int a = 0; a < 9; a++) {
                    board[a] = String.valueOf(a + 1);
                }

                System.out.println("Welcome to 3x3 Tic Tac Toe.");
                printBoard();

                System.out.println(
                        "X will play first. Enter a slot number to place X in:");

                while (winner == null) {
                    frame.repaint();
                    int numInput;

                    // Exception handling.
                    // numInput will take input from user like from 1 to 9.
                    // If it is not in range from 1 to 9.
                    // then it will show you an error "Invalid input."
                    try {
                        numInput = in.nextInt();
                        if (!(numInput > 0 && numInput <= 9)) {
                            System.out.println(
                                    "Invalid input; re-enter slot number:");
                            continue;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(
                                "Invalid input; re-enter slot number:");
                        continue;
                    }

                    // This game has two player x and O.
                    // Here is the logic to decide the turn.
                    if (board[numInput - 1].equals(
                            String.valueOf(numInput))) {
                        board[numInput - 1] = turn;

                        if (turn.equals("X")) {
                            turn = "O";
                        } else {
                            turn = "X";
                        }

                        printBoard();
                        winner = checkWinner();
                    } else {
                        System.out.println(
                                "Slot already taken; re-enter slot number:");
                    }
                    frame.repaint();
                }

                // If no one win or lose from both player x and O.
                // then here is the logic to print "draw".
                if (winner.equalsIgnoreCase("draw")) {
                    System.out.println(
                            "It's a draw! Thanks for playing.");
                }

                // For winner -to display Congratulations! message.
                else {
                    System.out.println(
                            "Congratulations! " + winner
                                    + "'s have won! Thanks for playing.");
                }
            }
        }
