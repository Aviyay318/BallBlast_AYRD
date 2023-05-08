import java.io.*;

public class Score {
    private int score;
    private String scoreToString;
    private File file;

    public Score(){
        this.file = new File(Constants.FILE_PATH);
        this.score = 0;
        this.scoreToString = Integer.toString(this.score) ;
        readScore();
        writeScore();

    }

    public int getScore() {
        return score;
    }

    public void readScore(){
        if (this.file.exists()&& this.file!=null){
            try {
                FileReader fileReader = new FileReader(this.file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                this.score = Integer.valueOf(bufferedReader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                this.file.createNewFile();
            }catch (IOException e){
              e.printStackTrace();
            }

        }

    }
    public void writeScore(){
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String data = scoreToString; //TODO: we should swap this "0" with the actual score of the player.
            bufferedWriter.write("0");
            bufferedWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String addScore(){
        return this.scoreToString;
    }

}

