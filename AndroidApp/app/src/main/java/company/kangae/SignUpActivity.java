package company.kangae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final Spinner accountType = (Spinner) findViewById(R.id.accountType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> accountTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.account_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        accountTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        if (accountType != null) {
            accountType.setAdapter(accountTypeAdapter);
        }

        final Spinner gender = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_type, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (gender != null) {
            gender.setAdapter(genderAdapter);
        }

        final EditText firstName = (EditText) findViewById(R.id.firstName), lastName = (EditText) findViewById(R.id.lastName),
                userName = (EditText) findViewById(R.id.userName), password = (EditText) findViewById(R.id.password),
                email = (EditText) findViewById(R.id.email), biography = (EditText) findViewById(R.id.biography),
                birthDate = (EditText) findViewById(R.id.birthDate);

        Button signUpButton = (Button) findViewById(R.id.submit);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean type = accountType.getSelectedItem().toString().equals("Student Account");
                int validationAnswer = Controller.signUp(firstName.getText().toString(), lastName.getText().toString(), userName.getText().toString(),
                        password.getText().toString(), email.getText().toString(), gender.getSelectedItem().toString(),
                        biography.getText().toString(), birthDate.getText().toString(), type);
                if (validationAnswer == 0) {
                    email.setError(getString(R.string.error_exist_email));
                    email.requestFocus();
                } else if (validationAnswer == 1) {
                    userName.setError(getString(R.string.error_exist_username));
                    userName.requestFocus();
                } else {
                    Intent myIntent = new Intent(SignUpActivity.this, ViewGamesActivity.class);
                    SignUpActivity.this.startActivity(myIntent);
                    finish();
                }
            }
        });
    }
}
