import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame extends JFrame {

    ImagenSnake imagenSnake;
    Point snake;
    Point comida;
    Point comidaMala;
    ArrayList<Point> listaPosiciones = new ArrayList<Point>();
    int longitud = 2;
    int width = 640;
    int height = 480;
    int widthPoint = 10;
    int heightPoint = 10;
    String direccion = "RIGHT";
    long frequency = 50;
    boolean gameOver = false;
    int score = 0;
    int foodValue = 0;
    boolean comidaMalaEaten = false;
    Timer timerComida;
    Timer timerComidaMala;


    public SnakeGame() {
        setTitle("Snake");
        comida = new Point(200, 100);
        snake = new Point(320, 240);
        comidaMala = new Point(100, 200);
        startGame("");
        imagenSnake = new ImagenSnake();
        this.getContentPane().add(imagenSnake);
        setSize(width, height);
        this.addKeyListener(new Teclas());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(false);
        setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setVisible(true);
        Momento momento = new Momento();
        Thread trid = new Thread(momento);
        timerComida = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarComida();
            }
        });
        timerComida.start();

        timerComidaMala = new Timer(7000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarComidaMala();
            }
        });
        timerComidaMala.start();
        trid.start();
    }

    public void startGame(String nombreUsuario) {
        listaPosiciones = new ArrayList<Point>();
        listaPosiciones.add(snake);
        longitud = listaPosiciones.size();
        score = 0;
        generarComida();
        generarComidaMala();
    }

    public void generarComida() {
        Random rnd = new Random();
        comida.x = (rnd.nextInt(width)) + 5;
        if ((comida.x % 5) > 0) {
            comida.x = comida.x - (comida.x % 5);
        }

        if (comida.x < 5) {
            comida.x = comida.x + 10;
        }
        if (comida.x > width) {
            comida.x = comida.x - 10;
        }

        comida.y = (rnd.nextInt(height)) + 5;
        if ((comida.y % 5) > 0) {
            comida.y = comida.y - (comida.y % 5);
        }

        if (comida.y > height) {
            comida.y = comida.y - 10;
        }
        if (comida.y < 0) {
            comida.y = comida.y + 10;
        }

        foodValue = rnd.nextInt(10) + 1;
    }

    public void generarComidaMala() {
        Random rnd = new Random();
        comidaMala.x = (rnd.nextInt(width)) + 5;
        if ((comidaMala.x % 5) > 0) {
            comidaMala.x = comidaMala.x - (comidaMala.x % 5);
        }

        if (comidaMala.x < 5) {
            comidaMala.x = comidaMala.x + 10;
        }
        if (comidaMala.x > width) {
            comidaMala.x = comidaMala.x - 10;
        }

        comidaMala.y = (rnd.nextInt(height)) + 5;
        if ((comidaMala.y % 5) > 0) {
            comidaMala.y = comidaMala.y - (comidaMala.y % 5);
        }

        if (comidaMala.y > height) {
            comidaMala.y = comidaMala.y - 10;
        }
        if (comidaMala.y < 0) {
            comidaMala.y = comidaMala.y + 10;
        }
    }

    public void actualizar() {
        listaPosiciones.add(0, new Point(snake.x, snake.y));
        listaPosiciones.remove(listaPosiciones.size() - 1);

        for (int i = 1; i < listaPosiciones.size(); i++) {
            Point point = listaPosiciones.get(i);
            if (snake.x == point.x && snake.y == point.y) {
                gameOver = true;
            }
        }

        if (!comidaMalaEaten &&
                (snake.x > (comidaMala.x - 10) && snake.x < (comidaMala.x + 10)) &&
                (snake.y > (comidaMala.y - 10) && snake.y < (comidaMala.y + 10))) {
            comidaMalaEaten = true;
            gameOver = true;
        }

        if ((snake.x > (comida.x - 10) && snake.x < (comida.x + 10)) && (snake.y > (comida.y - 10) && snake.y < (comida.y + 10))) {
            listaPosiciones.add(0, new Point(snake.x, snake.y));
            score += foodValue;
            System.out.println("Score: " + score);
            generarComida();
        }
        imagenSnake.repaint();
    }



    public class ImagenSnake extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (gameOver) {
                g.setColor(new Color(0, 0, 0));
            } else {
                g.setColor(new Color(67, 255, 52));
            }
            g.fillRect(0, 0, width, height);
            g.setColor(new Color(3, 91, 255, 255));

            if (listaPosiciones.size() > 0) {
                for (int i = 0; i < listaPosiciones.size(); i++) {
                    Point p = listaPosiciones.get(i);
                    g.fillRect(p.x, p.y, widthPoint, heightPoint);
                }
            }

            g.setColor(new Color(255, 0, 0));
            g.fillRect(comida.x, comida.y, widthPoint, heightPoint);
            g.setColor(new Color(95, 73, 0, 255));
            g.fillRect(comidaMala.x, comidaMala.y, widthPoint, heightPoint);

            if (gameOver) {
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.drawString("GAME OVER", 300, 200);
                g.drawString("SCORE " + score, 300, 240);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("R to Start New Game", 100, 320);
                g.drawString("ESC to Exit", 100, 340);
            }

            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("SCORE: " + score, 10, 20);
        }
    }

    public class Teclas extends java.awt.event.KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (direccion != "LEFT") {
                    direccion = "RIGHT";
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (direccion != "RIGHT") {
                    direccion = "LEFT";
                }
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (direccion != "DOWN") {
                    direccion = "UP";
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (direccion != "UP") {
                    direccion = "DOWN";
                }
            } else if (e.getKeyCode() == KeyEvent.VK_R) {
                gameOver = false;
                startGame("");
            }
        }
    }

    public class Momento extends Thread {
        private long last = 0;

        public Momento() {

        }

        public void run() {
            while (true) {
                if ((java.lang.System.currentTimeMillis() - last) > frequency) {
                    if (!gameOver) {
                        if (direccion == "RIGHT") {
                            snake.x = snake.x + widthPoint;
                            if (snake.x > width) {
                                snake.x = 0;
                            }
                        } else if (direccion == "LEFT") {
                            snake.x = snake.x - widthPoint;
                            if (snake.x < 0) {
                                snake.x = width - widthPoint;
                            }
                        } else if (direccion == "UP") {
                            snake.y = snake.y - heightPoint;
                            if (snake.y < 0) {
                                snake.y = height;
                            }
                        } else if (direccion == "DOWN") {
                            snake.y = snake.y + heightPoint;
                            if (snake.y > height) {
                                snake.y = 0;
                            }
                        }
                    }
                    if (!comidaMalaEaten &&
                            Math.random() < 0.02) {
                        generarComidaMala();
                    }
                    actualizar();
                    last = java.lang.System.currentTimeMillis();
                }
            }
        }
    }
}
