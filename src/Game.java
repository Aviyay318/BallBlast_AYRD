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
    private static boolean isStart = false;
    private int width;
    private int height;
    private Cannon cannon;
    private KeyBoard keyBoard;
    private  boolean isShooting;
    private List<Shot> shots;
    private Instructions instructions;
    private JButton start;
    private Icon startIcon;
    private Sound music;
    private Ball ball;
    private float interpolation;

    public Game(int width){
        this.setLayout(null);
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
        this.music = new Sound(this.keyBoard);
        this.music.loadClip(Sound.INTRO);
        playMusic();
        this.ball =new Ball();
        this.instructions = new Instructions(Instructions.INSTRUCTION_WIDTH,Instructions.INSTRUCTION_HEIGHT, 140,100,this.keyBoard);
        this.instructions.setVisible(true);
        this.add(instructions);
        addStartButton();

        checkKeyboardState();
        this.setVisible(true);

    }

    private void createShotList() {
       for (int i=0;i<5;i++){
           this.shots.add(new Shot(this.cannon));
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
               Utils.sleep(120);
               this.repaint();
            }
        }).start();
    }

    public void playMusic(){
        new Thread(()->{
            while(!music.isTransitionDone()){
                this.music.loop();
            }
            //this.sound.loop();
        }).start();
    }
    public void createShot(){
        new Thread(()->{
            while (this.keyBoard.isStartShooting()){
                if (this.keyBoard.isStartShooting()){
                    for (Shot shot:shots) {
                        if (this.keyBoard.isStartShooting()){
                            shot.moveShot();
                          //  shot.updateShot();
                        }else {
                            break;
                        }
                       Utils.sleep(500);
                    }
                    for (Shot shot:shots)
                        shot.updateShot();
                }}
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
    public void checkKeyboardState(){
        new Thread(()->{
            while(true){
                if (this.keyBoard.isHide()){
                    try {
                        System.out.println("shit");
                        Thread.sleep(2000);
                        System.out.println("shit2");
                        this.instructions.setVisible(false);
                        break;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
    public void addStartButton(){
        this.start = new JButton();
        this.startIcon = new ImageIcon("images/instructions/start.png");
        this.start.setBounds(Instructions.INSTRUCTION_WIDTH/2+85,Instructions.INSTRUCTION_HEIGHT/3+300,this.startIcon.getIconWidth(),this.startIcon.getIconHeight());
        this.start.setIcon(this.startIcon);
        this.start.setOpaque(false);
        this.start.setContentAreaFilled(false);
        this.add(this.start);
        this.start.setVisible(true);
        this.start.addActionListener((e -> {
            this.start.setVisible(false);
            this.ball.move();
            new Thread(()->{
                this.music.loadClip(Sound.TRANSITION);
                this.music.loop();
                this.instructions.addProgressBar();
                this.instructions.fillProgressBar();
                this.instructions.setVisible(false);
                isStart = true;
            }).start();
            //Utils.sleep(2000);
        }));
    }

    public void render(float interpolation){
        this.interpolation = interpolation;
        this.repaint();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(this.backGround,0,0,Constants.WIDTH,Constants.HEIGHT,null);
        if (isStart){
            this.ball.render(graphics2D,this.interpolation);
            for (Shot shot1:shots) {
                shot1.drawShot(graphics2D);
            }
            this.cannon.draw(graphics2D);
        }
    }
}
