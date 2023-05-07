import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    private Image icon;
    private Game game;
    public Frame(){
        createIcon();
        this.setSize(Constants.WIDTH,Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setTitle("Ball Blast");
        this.setLocationRelativeTo(null);
        TopPanel topPanel  = new TopPanel(Constants.WIDTH,Constants.HEIGHT/18);
        this.add(topPanel,BorderLayout.NORTH);
        this.game = new Game(Constants.WIDTH);
        this.add(this.game,BorderLayout.CENTER);
        this.setVisible(true);

    }
    private void doRepaint(){
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(120);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.game.repaint();
            }
        }).start();
    }

    private void createIcon(){
        try {
            this.icon = ImageIO.read(new File("models/icon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        this.setIconImage(this.icon);
    }
}
