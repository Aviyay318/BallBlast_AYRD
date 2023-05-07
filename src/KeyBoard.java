import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
    private Game game;
    Cannon cannon;
    private static boolean startShooting;
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
         case KeyEvent.VK_SPACE -> {startShooting=true;this.game.createShot();}
         case KeyEvent.VK_LEFT -> {this.cannon.moveCannon(-24);this.cannon.setLeft(true);}
         case KeyEvent.VK_RIGHT -> {this.cannon.moveCannon(24);this.cannon.setLeft(false);}
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
}
