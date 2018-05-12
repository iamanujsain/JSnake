public class StartingPoint {

    public static final int WIDTH = 600, HEIGHT = 500;

    public static void main(String[] args) {
       Game game = new Game("Snake", WIDTH, HEIGHT);
       game.start();
    }
}
