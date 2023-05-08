import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cannon {

    public final int SPEED = 24;
    private BufferedImage cannon;
    private boolean isLeft;
    private int cannonX;
    public Cannon(){
        this.cannonX= Constants.CANNON_X;
    }



    public void moveCannon(){
        if (this.isLeft){
            if (this.cannonX>=-10){
                this.cannonX-=SPEED;
            }
        }else {
            if (this.cannonX<=Constants.WIDTH-100){
                this.cannonX+=SPEED;
            }
        }

    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public void shooting(){

    }

    public int getCannonX() {
        return cannonX;
    }

    public void draw(Graphics2D graphics2D){
        try {
            this.cannon = ImageIO.read(new File("models/cannon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        graphics2D.drawImage(this.cannon,this.cannonX,Constants.CANNON_Y,100,100,null);


    }

}
