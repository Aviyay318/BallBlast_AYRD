import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    private final float GAME_HERTZ = 120.0f;
    private final double TIME_BETWEEN_UPDATES = 100000000/GAME_HERTZ;
    private final float TARGET_FPS = 120.0f;
    private final double TIME_BETWEEN_RENDERS = 100000000/TARGET_FPS;
    private final int MAX_UPDATES_BEFORE_RENDER = 5;
    private double lastUpdateTime;
    private double lastRenderTime;

    private Thread gameLoop;
    private boolean isRunning;

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
//        this.panelOne = new PanelOne();
//        this.add(this.panelOne);

       setupGameLoop();
       this.gameLoop.start();


        this.setVisible(true);

        this.isRunning=true;
        //this.gameLoop.start();

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

    public void setupGameLoop() {
        this.gameLoop = new Thread(()->{
            while(isRunning){
                long now = System.nanoTime();
                int updateCounter = 0;

                while(now - this.lastUpdateTime > TIME_BETWEEN_UPDATES && updateCounter < MAX_UPDATES_BEFORE_RENDER){
                    this.game.repaint();
                    this.lastUpdateTime+=TIME_BETWEEN_UPDATES;
                    updateCounter++;
                }

                if (now-this.lastUpdateTime > TIME_BETWEEN_UPDATES){
                    this.lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                float interpolation = Math.min(1.0f, (float) ((now-this.lastUpdateTime)/TIME_BETWEEN_UPDATES));
                this.game.render(interpolation);
                this.lastRenderTime = now;

                while(now - this.lastRenderTime < TIME_BETWEEN_RENDERS && now - this.lastUpdateTime < TIME_BETWEEN_UPDATES){
                    Thread.yield();
                    now = System.nanoTime();
                }
            }
        });
    }

}
