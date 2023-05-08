import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
private Score highScore;
    public TopPanel(int width,int height){
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(new Color(51,130,254));
        this.highScore = new Score();
        JLabel showScore = new JLabel("High Score: "+Integer.toString(this.highScore.getScore()),JLabel.CENTER);
        showScore.setFont(new Font("arial",Font.BOLD,30));
        showScore.setForeground(Color.white);
        showScore.setBounds(15,0,200,100);
        this.add(showScore);
    }
}
