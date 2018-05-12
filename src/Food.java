import java.awt.*;

public class Food {

    private int x;
    private int y;
    private int blockSize;
    private Snake snake;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score = 0;

    public Food(Snake snake) {
        this.snake = snake;
        blockSize = 6;
        newPosition();
    }

    public void draw(Graphics g) {
        if (snake.getIsMoving()) {
            g.setColor(Color.pink);
            g.setFont(new Font("Consolas", Font.PLAIN, 19));
            g.drawString(score + "", StartingPoint.WIDTH-40, 30);
            g.setColor(Color.green);
            g.fillRect(x, y, blockSize, blockSize);
        }
    }

    public void update() {
        if (snakeCollision(snake)) {
            newPosition();
            score++;
            snake.setElongate(true);
        }
    }

    public void newPosition() {
        x = (int) (Math.random() * (StartingPoint.WIDTH - blockSize));
        y = (int) (Math.random() * (StartingPoint.HEIGHT - blockSize));
    }

    public boolean snakeCollision(Snake s) {
        int headX = s.snakePoints.get(0).getX() + 2;
        int headY = s.snakePoints.get(0).getY() + 2;
        if (headX >= x-1 && headX <= x+7) {
            if (headY >= y-1 && headY <= y+7) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        score = 0;
    }
}
