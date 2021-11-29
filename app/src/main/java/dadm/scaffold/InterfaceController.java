package dadm.scaffold;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InterfaceController {

    private static final int MAX_LIVES = 3;
    private int actualLives;
    private Activity mainActivity;
    private TextView score;
    private TextView time;
    private int lastSecond;
    private ImageView[] lifeImages;
    public InterfaceController(Activity mainActivity) {
        this.mainActivity = mainActivity;
        score = mainActivity.findViewById(R.id.tv_score);
        time = mainActivity.findViewById(R.id.tv_time);
        lifeImages = new ImageView[MAX_LIVES];
        lifeImages[0] = (ImageView) mainActivity.findViewById(R.id.life0);
        lifeImages[1] = (ImageView) mainActivity.findViewById(R.id.life1);
        lifeImages[2] = (ImageView) mainActivity.findViewById(R.id.life2);
        lastSecond = -1;
        actualLives = MAX_LIVES;
    }

    public void updateTime(int newSecond){
        if(lastSecond!=newSecond){
            lastSecond = newSecond;
            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    time.setText("" + lastSecond);
                }
            });
        }
    }

    public void updateScore(){
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                score.setText("Score: " + Score.getScore());
            }
        });
    }

    public boolean hitShip(){
        actualLives--;
        Score.addScore(-500);
        updateScore();
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lifeImages[actualLives].setVisibility(View.INVISIBLE);
            }
        });
        return actualLives<=0;
    }
}
