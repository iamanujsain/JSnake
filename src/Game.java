import javafx.application.Application;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game implements Runnable, KeyListener {

    private Display display;
    public int width, height;
    public String title;

    private Thread thread;
    private boolean running, gameActive;
    private BufferStrategy bs;
    private Graphics g;
    private Snake snake;
    private Food food;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void init() {
        display = new Display(title, width, height, this);
        snake = new Snake();
        food = new Food(snake);
        gameActive = false;
    }

    public void endGameDecision(Graphics g) {
        if (gameActive) {
            if (!snake.getIsMoving()) {
                g.setColor(Color.pink);
                g.setFont(new Font("Consolas", Font.BOLD, 19));
                g.drawString("Press C to play again or Q to exit!", 50, 50);
            }
        }
    }

    public void tick(int fps) {
        try {
            thread.sleep(fps);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        // Draw here
        if (gameActive) {
            g.setColor(Color.white);
            snake.draw(g);
            food.draw(g);
            endGameDecision(g);
        }

        if (!snake.getIsMoving()) {
            g.setColor(Color.pink);
            g.setFont(new Font("consolas", Font.BOLD, 19));
            g.drawString("Press Enter to begin!", 100, 100);
        }

        // End drawing and show it on the canvas
        bs.show();
        g.dispose();
    }

    public void update() {
        snake.update();
        food.update();
    }

    @Override
    public void run() {
        init();
        while (running) {
            tick(30);
            render();
            update();
        }
    }

    public synchronized void start() {
        if (running) {
            return;
        } else {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void reset() {
        snake.reset();
        food.reset();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (snake.getDx() != -1) {
                    snake.setDy(0);
                    snake.setDx(1);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (snake.getDx() != 1) {
                    snake.setDy(0);
                    snake.setDx(-1);
                }
                break;
            case KeyEvent.VK_UP:
                if (snake.getDy() != 1) {
                    snake.setDx(0);
                    snake.setDy(-1);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snake.getDy() != -1) {
                    snake.setDx(0);
                    snake.setDy(1);
                }
                break;
            case KeyEvent.VK_ENTER:
                if (gameActive && snake.getIsMoving()) {
                    return;
                } else {
                    gameActive = true;
                    snake.setIsmoving(true);
                    snake.setDx(1);
                }
                break;
            case KeyEvent.VK_C:
                if (gameActive && !snake.getIsMoving()) {
                    snake.setIsmoving(true);
                    reset();
                }
                break;
            case KeyEvent.VK_Q:
                if (gameActive && !snake.getIsMoving()) {
                    System.exit(0);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
