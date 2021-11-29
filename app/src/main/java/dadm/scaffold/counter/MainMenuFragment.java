package dadm.scaffold.counter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import dadm.scaffold.BaseFragment;
import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;
import dadm.scaffold.engine.GameEngine;


public class MainMenuFragment extends BaseFragment implements View.OnClickListener {



    public static final int MAX_SCORES_AMOUNT = 5;
    private EditText et_nickName;
    private TextView tv_maxScore;
    private SharedPreferences preferences;
    public MainMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_nickName = view.findViewById(R.id.et_nickname);
        tv_maxScore = view.findViewById(R.id.textView_maxScore);
        preferences = getContext().getSharedPreferences("scores", Context.MODE_PRIVATE);
        view.findViewById(R.id.button_start).setOnClickListener(this);
        loadPreferences();
    }

    @Override
    public void onClick(View v) {
        String nick = et_nickName.getText().toString();
        et_nickName.setText("");
        if(nick.equals("")){
            et_nickName.setHint("INSERT NICKNAME");
            return;
        }
        if(nick.length()>=10){
            et_nickName.setHint("NAME TOO LONG");
            return;
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nick", nick);
        editor.commit();


        ((ScaffoldActivity)getActivity()).startGame();
    }

    private void loadPreferences(){
        String txt_maxScore = "Max Scores:\n";
        for(int i = 1; i <= MAX_SCORES_AMOUNT; i++)
            txt_maxScore += "\n" + i + "- " + preferences.getString("txt_max" + i, "") + " " +  preferences.getString("punt_max" + i, "") + "\n";
        tv_maxScore.setText(txt_maxScore);
        String nick = preferences.getString("nick", "");
        et_nickName.setText(nick);
    }
}
