import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel {

    private BufferedImage backGround;
    private int width;
    private int height;
    private Cannon cannon;
    private KeyBoard keyBoard;
    private  boolean isShooting;
    private List<Shot> shots;
    public Game(int width){
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width,height));
        createBackGround();
        this.cannon=new Cannon();
        doRepaint();
        this.shots = new ArrayList<>();
        createShotList();
        this.keyBoard = new KeyBoard(this,this.cannon);
        this.setFocusable(true);
        this.requestFocus(true);
        this.addKeyListener(this.keyBoard);
        this.isShooting=false;
    }

    private void createShotList() {
       for (int i=0;i<5;i++){
           this.shots.add(new Shot(this.cannon,this));
       }
    }


    private void createBackGround() {
        try {
            this.backGround = ImageIO.read(new File("models/backGround2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doRepaint(){
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(120);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.repaint();
            }
        }).start();
    }

    public void createShot(){
        new Thread(()->{
            while (this.keyBoard.isStartShooting()){
                if (this.keyBoard.isStartShooting()){
                    for (Shot shot1:shots) {
                        if (this.keyBoard.isStartShooting()){
                            shot1.moveShot();
                            shot1.updateShot();
                        }else {
                            break;
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }).start();


    }

    public boolean isShooting() {
        return this.keyBoard.isStartShooting();
    }

    public void startShooting(){
        this.isShooting = this.keyBoard.isStartShooting();
    }
    public void stopShooting(){
        this.isShooting = false;
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(this.backGround,0,0,Constants.WIDTH,Constants.HEIGHT,null);
        for (Shot shot1:shots) {
            shot1.drawShot(graphics2D);
        }
        this.cannon.draw(graphics2D);
    }
}
