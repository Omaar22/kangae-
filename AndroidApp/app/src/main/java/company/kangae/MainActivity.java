package company.kangae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logIn = (Button) findViewById(R.id.logInButton);
        logIn.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                                         MainActivity.this.startActivity(myIntent);
//                                         finish();
                                     }
                                 }
        );

        Button signUp = (Button) findViewById(R.id.signUpButton);
        signUp.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent myIntent = new Intent(MainActivity.this, SignUpActivity.class);
                                         MainActivity.this.startActivity(myIntent);
                                     }
                                 }
        );
    }
}
