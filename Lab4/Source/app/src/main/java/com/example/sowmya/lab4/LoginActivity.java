package com.example.sowmya.lab4;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    public void init() {
        Button button = (Button) findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameUI = (EditText) findViewById(R.id.uiname);
                EditText passwordUI = (EditText) findViewById(R.id.uipwd);
                String uname = usernameUI.getText().toString();
                String upwd = passwordUI.getText().toString();
                TextView errorText = (TextView) findViewById(R.id.Error);

                boolean validationFlag = false;
                //Verify if the username and password are not empty.
                if (!uname.isEmpty() && !upwd.isEmpty()) {
                    if (uname.equals("sowmya") && upwd.equals("sowmya5c6")) {
                        validationFlag = true;

                    }

                }
                if (!validationFlag) {
                    errorText.setVisibility(View.VISIBLE);
                } else {
                    //This code redirects the from login page to the home page.
                    Intent redirect = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(redirect);
                }
            }


        });
    }

    public void gotoNextPage(View view) {
                Intent nextPage = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(nextPage);

            }

}