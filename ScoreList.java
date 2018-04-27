package gametest2;

import java.io.*;
import java.util.*;

public class GameTest2 {

    public static void main(String[] args) {
        ScoreManager hm = new ScoreManager();
        Score s1= new Score("A",10);
        Score s2= new Score("B",8);
        Score s3= new Score("C",13);
        hm.addScore(s1);
        hm.addScore(s2);
        hm.addScore(s3);
        ArrayList<Score> scorestemp = hm.getScores();
        System.out.println(scorestemp.size());

        System.out.print(hm.getHighestScore());
    }

}

class Score implements Serializable {

    private String name;
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Score() {
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

class ScoreCompare implements Comparator<Score> {

    @Override
    public int compare(Score s1, Score s2) {
        return s2.getScore() - s1.getScore();
    }
}
