import javax.swing.*;
import java.awt.*;

public class Shot extends Thread {
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private int x;
    private int y;
    private  boolean isShooting;
    private Cannon cannon;
    private Game game;
    private boolean keepGoing;
    private Rectangle rectangleOfShot;

    public Shot(Cannon cannon){
        this.x =cannon.getCannonX()+40;
        this.y = Constants.CANNON_Y+10;
        this.cannon = cannon;
        this.rectangleOfShot = new Rectangle((this.cannon.getCannonX() + 40),this.y,WIDTH,HEIGHT);
        //this.isShooting = isShooting;
        this.keepGoing = true;
        this.game = game;
        direction();



//checkState();
    }

    public void createShot(){
        new Thread(()->{
            while (true){
                this.y= Constants.CANNON_Y;
                System.out.println("say");
                if (this.isShooting){
                    System.out.println("never");
                    moveShot();
                    break;
                }
            }
        }).start();


    }
//private void checkState(){
//        new Thread(()->{
//            this.x=this.cannon.getCannonX();
//            this.isShooting=this.game.isShooting();
//        }).start();
//}

    public void startShooting(){
        this.isShooting = true;
    }
    public void stopShooting(){
        this.isShooting = false;
    }
    private void direction(){

    }
    public void run(){
        while (true){
            while (this.y>-100){
                this.y-=15;
                Utils.sleep(100);
            }
            System.out.println("kj");
            this.y= Constants.CANNON_Y;
            System.out.println("say");
        }
    }
    public void updateShot(){
        this.x =cannon.getCannonX();
        this.y = Constants.CANNON_Y;
    }
    public void moveShot(){
        new Thread(()->{
            while (this.y>-100){
                this.y-=50;

                // this.x=this.cannon.getCannonX();
                Utils.sleep(100);
            }
        }).start();

    }
    public Rectangle getRectangleOfShot(){
        return this.rectangleOfShot;
    }
    private void moveS(){

    }

    public void drawShot(Graphics2D graphics2D){
        graphics2D.fillOval((this.cannon.getCannonX() + 40), this.y, WIDTH, HEIGHT);
        this.rectangleOfShot.setBounds(this.cannon.getCannonX() + 40, this.y, WIDTH, HEIGHT);
        //System.out.println(this.y);


    }


}

