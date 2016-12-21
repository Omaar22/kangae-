package company.kangae;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GameInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        Game game = getIntent().getParcelableExtra("game");

        TextView name = (TextView) findViewById(R.id.gameName);
        TextView category = (TextView) findViewById(R.id.category);
        TextView description = (TextView) findViewById(R.id.description);
        TextView instructions = (TextView) findViewById(R.id.instructions);
        TextView rating = (TextView) findViewById(R.id.rating);
        TextView difficultyRank = (TextView) findViewById(R.id.difficultyrank);
        ListView commentsList = (ListView) findViewById(R.id.comments);
        Button playGame = (Button) findViewById(R.id.playGame);

        name.setText(game.getName());
        category.setText(game.getCategory());
        description.setText(game.getDescription());
        instructions.setText(game.getInstructions());
        rating.setText(game.getRate());
        difficultyRank.setText(Integer.toString(game.getDifficultyRank()));
        ArrayList <Comment> comments = game.getComments();

        ArrayAdapter arrayAdapter = new GameInfo.commentAdapter(this, comments);
        commentsList.setAdapter(arrayAdapter);

        final Context context = this;





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
