package company.kangae;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayGameActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        game = getIntent().getParcelableExtra("game");

        //TODO view questions and loading new one until number of questions is finished

    }
}
