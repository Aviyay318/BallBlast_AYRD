import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
    private Game game;
    Cannon cannon;
    private static boolean startShooting;
    private static boolean isHide;
    public KeyBoard(Game game,Cannon cannon){
       this.game =game;
       this.cannon = cannon;
       startShooting = false;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
     switch (e.getKeyCode()){
         case KeyEvent.VK_SPACE -> {startShooting=true;this.game.createShot(); isHide=true;}
         case KeyEvent.VK_LEFT -> {this.cannon.moveCannon();this.cannon.setLeft(true); isHide=true;}
         case KeyEvent.VK_RIGHT -> {this.cannon.moveCannon();this.cannon.setLeft(false); isHide=true;}
     }
    }

    public boolean isStartShooting() {
        return startShooting;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_SPACE -> {startShooting=false;}
        }
    }

    public boolean isHide(){
        System.out.println("");
        return isHide;
    }
}
