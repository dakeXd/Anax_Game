package dadm.scaffold.counter;

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
        view.findViewById(R.id.button_title).setOnClickListener(this);
        if(Score.destroyed){
            tv_title.setText("Your ship has been destroyed");
        }else{
            tv_title.setText("You reached the objective");
        }
        tv_subtitle.setText("Score: " +Score.getScore());
    }

    @Override
    public void onClick(View v) {
        ((ScaffoldActivity)getActivity()).goToTitle();
    }
}