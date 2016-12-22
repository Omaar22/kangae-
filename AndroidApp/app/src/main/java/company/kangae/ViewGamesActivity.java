package company.kangae;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewGamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_games);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        viewGame();

    }

    public void viewGame(){

        final ListView gameList = (ListView) findViewById(R.id.gameList);
        final ArrayList <Game> games = Controller.getGames();

        if (games.size() == 0){
            RelativeLayout layout = new RelativeLayout(this);
            TextView noAvailableGames = new TextView(this);
            noAvailableGames.setText("No available games now, stay tuned!");
            noAvailableGames.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            RelativeLayout.LayoutParams details = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            details.setMargins(10, 10, 10, 10);
            layout.addView(noAvailableGames, details);
            return;
        }

        ArrayAdapter arrayAdapter = new gameAdapter(this, games);
        gameList.setAdapter(arrayAdapter);

        final Context context = this;

        gameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
            assert newGame != null;
            gameName.setText(newGame.getName());
            category.setText(newGame.getCategory());
            rating.setText(newGame.getRate());

            return view;
        }
    }


}
