package app.media.opp.partytonight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PromoterSignInActivity extends Activity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_sign_in);
        findViewById(R.id.bLogIn).setOnClickListener(this);
        findViewById(R.id.bSignUp).setOnClickListener(this);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogIn:
                break;
            case R.id.bSignUp:
                startActivity(new Intent(this, PromoterSignUpActivity.class));
                break;
        }
    }
}
