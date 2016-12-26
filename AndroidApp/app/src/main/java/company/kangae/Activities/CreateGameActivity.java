package company.kangae.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import company.kangae.Controller;
import company.kangae.Question;
import company.kangae.R;
import company.kangae.Teacher;

public class CreateGameActivity extends AppCompatActivity {
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        // finish();
        final LinearLayout layout = (LinearLayout) findViewById(R.id.createGameLayout);
        final FloatingActionButton addQuestion = (FloatingActionButton) findViewById(R.id.addQuestionButton);
        final Button submitGame = (Button) findViewById(R.id.submitCreateGame);

        EditText gameName = (EditText) findViewById(R.id.gameName);
        EditText gameDescription = (EditText) findViewById(R.id.gameDescription);
        EditText gameInstructions = (EditText) findViewById(R.id.gameInstructions);
        EditText gameCategory = (EditText) findViewById(R.id.gameCategory);

        final String name = gameName.getText().toString();
        final String description = gameDescription.getText().toString();
        final String instructions = gameInstructions.getText().toString();
        final String category = gameCategory.getText().toString();

        Log.i("game name", name);


        final ArrayList <Question> questions = new ArrayList<>();
        final int gameId = Controller.getGames().size() + 1;

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText question = new EditText(CreateGameActivity.this);
                question.setHint("Question" + counter + " Statement");
                EditText answer = new EditText(CreateGameActivity.this);
                answer.setHint("Answer Question" + counter);
                counter++;
                TextInputLayout textInputLayout = new TextInputLayout(CreateGameActivity.this);
                textInputLayout.addView(question);
                layout.addView(textInputLayout, layout.getChildCount() - 2);
                textInputLayout = new TextInputLayout(CreateGameActivity.this);
                textInputLayout.addView(answer);
                layout.addView(textInputLayout, layout.getChildCount() - 2);


                //sa7 kda? *sigh*
                String newQuestion = question.getText().toString();
                String newAnswer = answer.getText().toString();
                Question q = new Question(newQuestion, newAnswer, false);
                questions.add(q);

                /*TODO:scroll when adding
                  TODO: moving addquestion button with the new question answer using addRule and answerID
                  TODO: add difficulty rank textField
                  TODO: exception handling: game name exists
                 */
            }
        });

        final Context context = this;
        submitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Teacher teacher = (Teacher) Controller.getLoggedInUser();
                Log.i("game name", name);
                teacher.addGame(gameId, 5, name, description, instructions, category, questions);
                Intent intent = new Intent(context, ViewGamesActivity.class);
                intent.putExtra("accountType", "Teacher");
                startActivity(intent);

            }
        });
    }
}
