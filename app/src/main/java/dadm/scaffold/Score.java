package dadm.scaffold;

public class Score {
    private static int score;
    public static boolean destroyed;

    public static void resetScore(){
        score = 0;
    }
    public static void addScore(int aditionalScore){
        score+=aditionalScore;
    }

    public static int getScore(){
        return score;
    }
}
