import javax.swing.JFrame;
import java.awt.*;

public class Display {

    private Canvas canvas;

    private JFrame frame;
    private String title;
    private int width, height;

    public Display(String title, int width, int height, Game game) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay(game);
    }

    private void createDisplay(Game game) {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setBackground(Color.black);

        frame.add(canvas);
        frame.addKeyListener(game);
        frame.pack(); // To have the complete canvas contained inside the JFrame.
    }

    public Canvas getCanvas() {
        return this.canvas;
    }
}
