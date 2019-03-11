package com.example.cityapp.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cityapp.DB.DBHelper;
import com.example.cityapp.Model.User;
import com.example.cityapp.R;

public class UpdateProfile extends AppCompatActivity {

    TextInputLayout name;
    TextInputLayout mail;
    TextInputLayout password;
    TextInputLayout addressUpdate;
    TextInputLayout phoneUpdate;

    DBHelper helper;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        //customize toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Update Profile");

        setViews();
        helper = new DBHelper(getApplicationContext());
        user = (User) getIntent().getSerializableExtra("user");

        if (user.getRole().equals("Admin")){
            addressUpdate.setVisibility(View.GONE);
            phoneUpdate.setVisibility(View.GONE);
        }else

            name.getEditText().setText(user.getName());
        mail.getEditText().setText(user.getMail());
        password.getEditText().setText(user.getPassword());
        addressUpdate.getEditText().setText(user.getAddress());
        phoneUpdate.getEditText().setText(user.getPhone());

    }

    public void setViews(){
        name = findViewById(R.id.nameUpdate);
        mail = findViewById(R.id.mailUpdate);
        password = findViewById(R.id.passwordUpdate);
        addressUpdate = findViewById(R.id.addressUpdate);
        phoneUpdate = findViewById(R.id.phoneUpdate);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }

    public void update(View view) {

        if (user.getRole().equals("Admin")){
            user = new User(user.getId(),name.getEditText().getText().toString(),mail.getEditText().getText().toString(),password.getEditText().getText().toString(),user.getRole());
            helper.updateAdmin(user);
        }else {
            user = new User(user.getId(),name.getEditText().getText().toString(),mail.getEditText().getText().toString(),password.getEditText().getText().toString(),user.getRole(),phoneUpdate.getEditText().getText().toString(),addressUpdate.getEditText().getText().toString());
            helper.updateUser(user);
        }

        Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}
