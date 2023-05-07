import java.awt.*;

public class Shot extends Thread {
    private int x;
    private int y;
    private  boolean isShooting;
    private Cannon cannon;
    private Game game;

    public Shot(Cannon cannon, Game game){
        this.x =cannon.getCannonX()+40;
        this.y = Constants.CANNON_Y+10;
        //this.isShooting = isShooting;
        this.game = game;
        this.cannon = cannon;
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
//        new Thread(()->{
//            while (true){
//                System.out.println("work");
//                this.x=this.cannon.getCannonX();
//            }
//        }).start();

    }
    public void run(){
        while (true){
            System.out.println("ok");
        }
    }
public void updateShot(){
    this.x =cannon.getCannonX();
    this.y = Constants.CANNON_Y;
}
    public void moveShot(){
        new Thread(()->{
            while (this.y>-100){
                this.y-=15;
               // this.x=this.cannon.getCannonX();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }


    public void drawShot(Graphics2D graphics2D){
                  //graphics2D.setColor(Color.orange);
      //  if (this.game.isShooting()){
            graphics2D.fillOval(this.x,this.y,20,20);
      //  }

    }


}

