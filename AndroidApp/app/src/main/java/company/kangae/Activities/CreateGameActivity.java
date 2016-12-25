package company.kangae.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import company.kangae.R;

public class CreateGameActivity extends AppCompatActivity {
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        // finish();
        final LinearLayout layout = (LinearLayout) findViewById(R.id.createGameLayout);
        FloatingActionButton addQuestion = (FloatingActionButton) findViewById(R.id.addQuestionButton);
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
            }
        });
    }
}
