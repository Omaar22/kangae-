package company.kangae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

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
        ListView comments = (ListView) findViewById(R.id.comments);

        name.setText(game.getName());
        category.setText(game.getCategory());
        description.setText(game.getDescription());
        instructions.setText(game.getInstructions());
        rating.setText(game.getRate());
        difficultyRank.setText(Integer.toString(game.getDifficultyRank()));



    }




}
