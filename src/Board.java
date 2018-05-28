import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class Board extends JPanel implements ActionListener {

    private int win_W = 800;
    private int win_H = 800;
    private int pixel = 25;
    private int total = 1024;

    private int arr_x[] = new int[total];
    private int arr_y[] = new int[total];

    private int points;
    private int food_x;
    private int food_y;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean alive = true;

    private Timer timer;
    private Image body;
    private Image food;
    private Image head;

    public Board() {

        doBoard();
    }

    private void doBoard() {

        addKeyListener(new keyPress());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(win_W, win_H));
        loadSprites();
        startGame();
    }

    private void loadSprites() {

        ImageIcon head = new ImageIcon("src/resources/head.png");
        this.head = head.getImage();

        ImageIcon body = new ImageIcon("src/resources/body.png");
        this.body = body.getImage();

        ImageIcon food = new ImageIcon("src/resources/food.png");
        this.food = food.getImage();

    }

    private void startGame() {

        points = 3;

        generateFood();

        int delay = 80;
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        score(g);
        doDrawing(g);
    }

    private void score(Graphics g) {
        String score = "Punktid: " + String.format("%d", points-3);
        Font font = new Font("DialogInput", Font.BOLD, 20);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(score, 650, 20);
    }

    private void doDrawing(Graphics g) {

        if (alive) {


            g.drawImage(food, food_x, food_y, this);
            System.out.println(food_x + " " + food_y);

            for (int i = 0; i < points; i++) {
                if (i == 0) {
                    g.drawImage(head, arr_x[i], arr_y[i], this);
                } else {
                    g.drawImage(body, arr_x[i], arr_y[i], this);
                }
            }


        } else {

            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {

        String gameOver = "Mäng läbi!";
        Font end = new Font("DialogInput", Font.BOLD, 40);
        FontMetrics wordSize = getFontMetrics(end);

        g.setColor(Color.red);
        g.setFont(end);
        g.drawString(gameOver, (win_W - wordSize.stringWidth(gameOver)) / 2, win_H / 2);

    }

    private void eatFood() {

        if ((arr_x[0] == food_x) && (arr_y[0] == food_y)) {

            points++;
            generateFood();
        }
    }

    private void move() {

        for (int i = points; i > 0; i--) {
            arr_x[i] = arr_x[(i - 1)];
            arr_y[i] = arr_y[(i - 1)];
        }

        if (left) {
            arr_x[0] -= pixel;
        }

        if (right) {
            arr_x[0] += pixel;
        }

        if (up) {
            arr_y[0] -= pixel;
        }

        if (down) {
            arr_y[0] += pixel;
        }
    }

    private void checkCollision() {

        for (int i = points; i > 0; i--) {

            if ((i > 4) && (arr_x[0] == arr_x[i]) && (arr_y[0] == arr_y[i])) {
                alive = false;
            }
        }

        if (arr_y[0] >= win_H) {
            alive = false;
        }

        if (arr_y[0] < 0) {
            alive = false;
        }

        if (arr_x[0] >= win_W) {
            alive = false;
        }

        if (arr_x[0] < 0) {
            alive = false;
        }

        if (!alive) {
            timer.stop();
        }
    }

    private void generateFood() {

        int r = (int) (Math.random() * 30);
        food_x = ((r * pixel));

        r = (int) (Math.random() * 30);
        food_y = ((r * pixel));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (alive) {

            eatFood();
            checkCollision();
            move();
        }

        repaint();
    }
    //keyboard
    private class keyPress extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }
}