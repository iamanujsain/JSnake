import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private static final int
            STARTSIZE = 13,
            STARTX = 300,
            STARTY = 200,
            BLOCKSIZE = 6;

    private int dx = 0;

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    private int dy = 0;
    private boolean isMoving, elongate;

    List<Point> snakePoints;

    public Snake() {
        init();
    }

    public void init() {
        isMoving = false;
        elongate = false;
        snakePoints = new ArrayList<>();
        for (int i = 0; i < STARTSIZE; i++) {
            snakePoints.add(new Point(STARTX - (i*BLOCKSIZE), STARTY));
        }
    }

    public void reset() {
        snakePoints.clear();
        init();
    }

    public void draw(Graphics g) {
        for (Point p: snakePoints) {
            g.fillRect(p.getX(), p.getY(), BLOCKSIZE, BLOCKSIZE);
        }
        g.setColor(Color.pink);
        for (int i = 0; i < 2; i++) {
            g.fillRect(snakePoints.get(i).getX(), snakePoints.get(i).getY(), BLOCKSIZE, BLOCKSIZE);
        }
    }

    public void update() {
        if (isMoving) {
            Point first = snakePoints.get(0);
            Point last = snakePoints.get(snakePoints.size()-1);
            Point newPoint = new Point(first.getX() + dx*BLOCKSIZE, first.getY() + dy*BLOCKSIZE);

            for (int i = snakePoints.size()-1; i > 0; i--) {
                snakePoints.set(i, snakePoints.get(i-1));
            }

            snakePoints.set(0, newPoint);

            if (elongate) {
                snakePoints.add(last);
                setElongate(false);
            }

            moveThroughWalls();
            snakeCollision();
        }
    }

    public void snakeCollision() {

        int headX = snakePoints.get(0).getX();
        int headY = snakePoints.get(0).getY();

        for (int i = 1; i < snakePoints.size(); i++) {
            if (snakePoints.get(i).getX() == headX && snakePoints.get(i).getY() == headY) {
                setIsmoving(false);
            }
        }
    }

    public void moveThroughWalls() {

        int headX = snakePoints.get(0).getX();
        int headY = snakePoints.get(0).getY();

        if (headX > StartingPoint.WIDTH-BLOCKSIZE && dx == 1) {
            snakePoints.get(0).setX(0);
        }

        if (headX < 0 && dx == -1) {
            snakePoints.get(0).setX(StartingPoint.WIDTH);
        }

        if (headY > StartingPoint.HEIGHT-BLOCKSIZE && dy == 1) {
            snakePoints.get(0).setY(0);
        }

        if (headY < 0 && dy == -1) {
            snakePoints.get(0).setY(StartingPoint.HEIGHT);
        }
    }

    public void setElongate(boolean b) {
        elongate = b;
    }

    public void setIsmoving(boolean b) {
        isMoving = b;
    }

    public boolean getIsMoving() {
        return isMoving;
    }
}
