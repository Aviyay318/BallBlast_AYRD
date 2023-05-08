import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    public static final int INTRO = 0;
    public static final int TRANSITION = 1;
    public static final int DURING = 2;
    private Clip clip;
    private AudioInputStream intro;
    private AudioInputStream transition;
    private AudioInputStream during;

    private boolean isTransitionAvailable;
    private boolean isTransitionDone;

    private String state;
    private KeyBoard keyboard;

    public Sound(KeyBoard keyboard) {
        try {
            this.clip = AudioSystem.getClip();
            this.intro = AudioSystem.getAudioInputStream(new File("music/ballBlastIntro.wav"));
            this.transition = AudioSystem.getAudioInputStream(new File("music/ballBlastTransition.wav"));
            this.during = AudioSystem.getAudioInputStream(new File("music/ballBlastDuringGame.wav"));
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }

        this.state = "intro";
        this.keyboard = keyboard;

        this.isTransitionAvailable = false;
        this.isTransitionDone = false;
//        checkTransitionAvailability();
//        loadClip();
    }

//    public void isHidingInvoked(){
//        if (this.keyboard.isHide()){
//            this.isTransitionAvailable = true;
//        }
//    }

//    public void checkTransitionAvailability(){
//        new Thread(()->{
//            while (true){
//                isHidingInvoked();
//                if(this.isTransitionAvailable) {
//                    setState();
//                    break;
//                }
//            }
//        }).start();
//    }

//    public void setState() {
//        close();
//        this.state = "transition";
//        loadClip();
//        try {
//            Thread.sleep(2329);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        this.state = "during";
//        close();
//        loadClip();
//        this.isTransitionDone=true;
//    }

        public void loadClip (int state){
            try {
                switch (state) {
                    case 0 -> {
                        this.clip.open(this.intro);
                    }
                    case 1 -> {
                        this.clip.open(this.transition);
                    }
                    case 2 -> {
                        this.clip.open(this.during);
                    }
                }
            } catch (LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void start() {
            this.clip.setMicrosecondPosition(0);
            this.clip.start();
        }
        public void loop() {
            //this.clip.setMicrosecondPosition(0);
            this.clip.loop(1);
            //System.out.println(this.clip.getLineInfo());
        }
        public void close () {
            clip.close();
        }

        public boolean isTransitionDone () {
            return this.isTransitionDone;
        }
    }
