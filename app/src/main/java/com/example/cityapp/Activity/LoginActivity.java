package com.example.cityapp.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityapp.DB.DBHelper;
import com.example.cityapp.Model.User;
import com.example.cityapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText mail;
    EditText password;
    CheckBox adminCheckBoxLogin;
    User user;
    DBHelper helper;
    String userRole;

    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpViews();
        helper = new DBHelper(getApplicationContext());
    }

    public void setUpViews(){
        mail = findViewById(R.id.mailLogin);
        password = findViewById(R.id.passwordLogin);
        adminCheckBoxLogin = findViewById(R.id.adminCheckBoxLogin);

    }
    public void login(View view) {

        if (TextUtils.isEmpty(mail.getText())
                ||TextUtils.isEmpty(password.getText())) {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();

        }else {
           if (adminCheckBoxLogin.isChecked()){
               userRole ="Admin";
           }else {
               userRole ="Typical";
           }
            user = helper.checkLogin(mail.getText().toString(),password.getText().toString(),userRole);
            Log.d(TAG, "login: " + userRole);
            if (user ==null){
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Login Failed", Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                        .show();

                Toast.makeText(this, "Check Mail and Password", Toast.LENGTH_LONG).show();
            }else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        }
    }

    public void goToSignUp(View view) {
        startActivity(new Intent(getApplicationContext(),SignupActivity.class));
        finish();
    }
}
