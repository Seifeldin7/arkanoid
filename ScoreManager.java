
import java.util.*;
import java.io.*;

public class ScoreManager {
private ArrayList<Score> scores = new ArrayList();

public ArrayList<Score> getScores(){
    loadScoresList();
    ScoreCompare sc = new ScoreCompare();
    Collections.sort(scores,sc);
    return scores;
}

    public void addScore(Score s){
    loadScoresList();
    scores.add(s);
    updateScoresList();
}
public void loadScoresList(){
    try{
        FileInputStream filein = new FileInputStream("Highscores.txt");
        ObjectInputStream in = new ObjectInputStream(filein);
        scores = (ArrayList<Score>) in.readObject();
        in.close();
        filein.close();

    }
    catch(IOException i){
        i.printStackTrace();
    }
    catch(ClassNotFoundException c){
        c.printStackTrace();
    }
}
public void updateScoresList(){
    try{
        FileOutputStream fileout = new FileOutputStream("Highscores.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileout);
        out.writeObject(scores);
        if(out!=null){
        out.flush();
        out.close();
        fileout.close();}
    }
    catch(FileNotFoundException f){
        f.printStackTrace();
    }
    catch(IOException i){
        i.printStackTrace();
    }
}
public String getHighestScore() {
        String highest = "";
	int max = 5;
        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highest += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t"
                    + scores.get(i).getScore() + "\n";
            i++;
        }
        return highest;
}
}