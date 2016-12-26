package company.kangae.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import company.kangae.Comment;
import company.kangae.Controller;
import company.kangae.Game;
import company.kangae.R;
import company.kangae.Score;
import company.kangae.Student;

public class GameInfoActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        game = getIntent().getParcelableExtra("game");

        TextView name = (TextView) findViewById(R.id.gameName);
        TextView category = (TextView) findViewById(R.id.category);
        TextView description = (TextView) findViewById(R.id.description);
        TextView instructions = (TextView) findViewById(R.id.instructions);
        TextView rating = (TextView) findViewById(R.id.rating);
        TextView difficultyRank = (TextView) findViewById(R.id.difficultyrank);
        TextView score = (TextView) findViewById(R.id.score);
        ListView commentsList = (ListView) findViewById(R.id.comments);


        assert name != null;
        name.setText(game.getName());
        assert category != null;
        category.setText(game.getCategory());
        assert description != null;
        description.setText(game.getDescription());
        assert instructions != null;
        instructions.setText(game.getInstructions());
        assert rating != null;
        rating.setText(game.getRate());
        assert difficultyRank != null;
        difficultyRank.setText(Integer.toString(game.getDifficultyRank()));
        ArrayList <Comment> comments = game.getComments();

        if (Controller.getLoggedInUser() instanceof Student){
            Student student = (Student) Controller.getLoggedInUser();
            ArrayList <Score> scores = student.getScores();
            boolean found = false;
            for (int i = 0; i < scores.size(); i++){
                if (scores.get(i).getGameId() == game.getId()){
                    found = true;
                    score.setText("Score: " + Integer.toString(scores.get(i).getScore()));
                }
            }
            if (!found) score.setText("Score: 0");
        }

        ArrayAdapter arrayAdapter = new GameInfoActivity.commentAdapter(this, comments);
        assert commentsList != null;
        commentsList.setAdapter(arrayAdapter);



    }

    public void onClickPlayGame (View view){
        Intent intent = new Intent(this, PlayGameActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    class commentAdapter extends ArrayAdapter<Comment> {

        public commentAdapter(Context context, ArrayList<Comment> comments) {
            super(context, R.layout.comments_list, comments);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.comments_list, parent, false);
            TextView userName = (TextView) view.findViewById(R.id.userName);
            TextView comment = (TextView) view.findViewById(R.id.comment);


            Comment newComment = getItem(position);
            userName.setText(newComment.getUserName());
            comment.setText(newComment.getComment());

            return view;
        }
    }



}
