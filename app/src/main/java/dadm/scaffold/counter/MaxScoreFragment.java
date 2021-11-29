package dadm.scaffold.counter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dadm.scaffold.BaseFragment;
import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;
import dadm.scaffold.Score;


public class MaxScoreFragment extends BaseFragment implements View.OnClickListener {

    private TextView tv_title;
    private TextView tv_subtitle;
    private TextView tv_score;

    private String nombres[];
    private int int_puntuaciones[];
    private int position;
    private SharedPreferences preferences;
   public MaxScoreFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_max_score, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_title =  view.findViewById(R.id.score_title);
        tv_subtitle =  view.findViewById(R.id.score_subtitle);
        tv_score = view.findViewById(R.id.textViewScore2);

        nombres = new String[MainMenuFragment.MAX_SCORES_AMOUNT];
        int_puntuaciones = new int[MainMenuFragment.MAX_SCORES_AMOUNT];

        preferences = getContext().getSharedPreferences("scores", Context.MODE_PRIVATE);
        view.findViewById(R.id.button_title).setOnClickListener(this);
        if(Score.destroyed){
            tv_title.setText("Your ship has been destroyed");
        }else{
            tv_title.setText("You reached the objective");
        }
        tv_subtitle.setText("Score: " +Score.getScore());
        position = -1;
        preferences = getContext().getSharedPreferences("scores", Context.MODE_PRIVATE);
        loadMaxScores();
        if(position>-1)
            saveNewMaxScores();
        printMaxScores();
    }

    @Override
    public void onClick(View v) {
        ((ScaffoldActivity)getActivity()).goToTitle();
    }

    private void loadMaxScores(){

        for(int i = 1; i <= MainMenuFragment.MAX_SCORES_AMOUNT; i++){
            int index = i-1;
            nombres[index] =preferences.getString("txt_max" + i, "");
            int_puntuaciones[index] = Integer.parseInt(preferences.getString("punt_max" + i, "-99999"));
            if(Score.getScore()>int_puntuaciones[index] && position<=-1)
                position = index;
        }

        if(position>-1) {
            for (int i = MainMenuFragment.MAX_SCORES_AMOUNT - 1; i > position; i--) {
                nombres[i] = nombres[i-1];
                int_puntuaciones[i] = int_puntuaciones[i-1];
            }
            nombres[position] = preferences.getString("nick", "");
            int_puntuaciones[position] = Score.getScore();
        }
    }

    private void printMaxScores(){
        String txt_maxScore = "Max Scores:\n";
        for(int i = 1; i <= MainMenuFragment.MAX_SCORES_AMOUNT; i++)
            txt_maxScore += "\n" + i + "- " + preferences.getString("txt_max" + i, "") + " " +  preferences.getString("punt_max" + i, "") + "\n";
        tv_score.setText(txt_maxScore);
    }

    private void saveNewMaxScores(){
        SharedPreferences.Editor editor = preferences.edit();
        for(int i = 1; i <= MainMenuFragment.MAX_SCORES_AMOUNT; i++){
            editor.putString("txt_max" + i, nombres[i-1]);
            if(int_puntuaciones[i-1]!=-99999)
                editor.putString("punt_max" + i, ""+int_puntuaciones[i-1]);
        }
        editor.commit();
    }
}