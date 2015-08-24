package com.ljlon.sign;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ljlon.sign.config.Constant;
import com.ljlon.sign.library.serverApi.User;
import com.ljlon.sign.library.serverApi.UserApi;

/**
 * A login screen.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginTask loginTask = new LoginTask();
                loginTask.execute(mEmailView.getText().toString(), mPasswordView.getText().toString());
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    class LoginTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            User userInfo = new User();
            boolean bRet = UserApi.Login(params[0], params[1], userInfo);
            if (bRet == true) {
                Intent intent = new Intent();
                LoginActivity.this.setResult(Constant.CODE_LOGIN_ACTIVITY, intent);
                LoginActivity.this.finish();
            }
            return null;
        }
    }
}

