package company.kangae.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import company.kangae.Controller;
import company.kangae.Game;
import company.kangae.Question;
import company.kangae.R;
import company.kangae.Score;
import company.kangae.Student;

public class PlayGameActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        game = getIntent().getParcelableExtra("game");
        
    }

    public void viewQuestions (){

        final ArrayList <Question> questions = game.getQuestions();
        int numOfQuestions = questions.size();
        int cnt = 0;
        int correctAnswers = 0;

        while (cnt < numOfQuestions){

            final TextView question = (TextView) findViewById(R.id.questions);
            question.setText(questions.get(cnt).getQuestion());

            final ArrayList <String> options = questions.get(cnt).getOptions();
            final ListView optionsList = (ListView) findViewById(R.id.options);
            ArrayAdapter arrayAdapter = new ArrayAdapter <String> (this,android.R.layout.simple_list_item_1, options);
            optionsList.setAdapter(arrayAdapter);

            final int[] bla = {-1};
            optionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   bla[0] = i;
               }

           });

            if (bla [0] != -1){
                ++cnt;
                if (questions.get(cnt).getAnswer().equals(options.get(bla[0]))){
                    optionsList.getChildAt(bla[0]).setBackgroundColor(Color.GREEN);
                    ++correctAnswers;
                }
                else optionsList.getChildAt(bla[0]).setBackgroundColor(Color.RED);
            }

        }

        ArrayList<Score> scores = ((Student) Controller.getLoggedInUser()).getScores();
        boolean found = false;
        for (int i = 0; i < scores.size(); i++)
            if (scores.get(i).getGameId() == game.getId() && scores.get(i).getScore() < correctAnswers) {
                scores.get(i).setScore(correctAnswers);
            } else if (scores.get(i).getGameId() == game.getId()) found = true;

        if (!found) {
            Score score = new Score(game.getId(), correctAnswers);
            ((Student) Controller.getLoggedInUser()).setScore(score);
        }


    }




}
