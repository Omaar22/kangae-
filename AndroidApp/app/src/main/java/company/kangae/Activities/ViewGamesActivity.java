package company.kangae.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import company.kangae.Controller;
import company.kangae.Game;
import company.kangae.R;

public class ViewGamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_games);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addGameButton);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewGamesActivity.this, CreateGameActivity.class);
                ViewGamesActivity.this.startActivity(myIntent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("accountType");
        if("Student".equals(message)){
            fab.hide();
        }
        Button logOut = (Button) findViewById(R.id.log_out);
        logOut.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Controller.resetLoggedInUser();
                        Intent myIntent = new Intent(ViewGamesActivity.this, MainActivity.class);
                        ViewGamesActivity.this.startActivity(myIntent);
                        finish();
                    }
                }
        );

        viewGame();
    }

    public void viewGame(){

        final ListView gameList = (ListView) findViewById(R.id.gameList);
        final ArrayList <Game> games = Controller.getGames();

        if (games.size() == 0){
            CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
            TextView noAvailableGames = new TextView(this);
            noAvailableGames.setText("No available games now, stay tuned!");
            noAvailableGames.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            noAvailableGames.setTextColor(Color.RED);
            noAvailableGames.setGravity(Gravity.CENTER_HORIZONTAL);
            RelativeLayout.LayoutParams details = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            details.setMargins(30, 30, 30, 30);
            noAvailableGames.setLayoutParams(details);
            layout.addView(noAvailableGames);
            return;
        }

        ArrayAdapter arrayAdapter = new gameAdapter(this, games);
        gameList.setAdapter(arrayAdapter);

        final Context context = this;

        gameList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, GameInfoActivity.class);
                intent.putExtra("game", games.get(i));
                startActivity(intent);
            }
        });



    }

    class gameAdapter extends ArrayAdapter<Game> {

        public gameAdapter(Context context, ArrayList <Game> games) {
            super(context, R.layout.game_list, games);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.game_list, parent, false);
            TextView gameName = (TextView) view.findViewById(R.id.gameName);
            TextView category = (TextView) view.findViewById(R.id.category);
            TextView rating = (TextView) view.findViewById(R.id.rating);


            Game newGame = getItem(position);
            Log.i("game name in view" , newGame.getName());

            assert newGame != null;
            gameName.setText(newGame.getName());
            category.setText(newGame.getCategory());
            rating.setText(newGame.getRate());

            return view;
        }
    }


}
