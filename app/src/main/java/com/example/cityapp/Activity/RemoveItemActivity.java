package com.example.cityapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cityapp.DB.DBHelper;
import com.example.cityapp.Model.User;
import com.example.cityapp.R;

public class RemoveItemActivity extends AppCompatActivity {


    String userRole;
    EditText id;
    User user;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        //customize toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Details");

        helper = new DBHelper(getApplicationContext());
        userRole = "Admin";
        id = findViewById(R.id.removeItem);
        user = (User) getIntent().getSerializableExtra("user");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (userRole.equals("Admin")){
            getMenuInflater().inflate(R.menu.admin_menu,menu);
        }else {
            getMenuInflater().inflate(R.menu.main, menu);
        } return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent1;
        //noinspection SimplifiableIfStatement
        if (id == R.id.logoutUser) {
            intent1 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent1);
            finish();
        }else if (id == R.id.adminEdit){
            intent1 = new Intent(getApplicationContext(), EditItemActivity.class);
            startActivity(intent1);
            finish();
        }else if (id == R.id.adminRemove){
            intent1 = new Intent(getApplicationContext(), RemoveItemActivity.class);
            startActivity(intent1);
            finish();
        }else if (id == R.id.adminAdd){
            intent1 = new Intent(getApplicationContext(), EditItemActivity.class);
            startActivity(intent1);
            finish();
        }else if (id == R.id.adminLogout){
            intent1 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent1);
            finish();
        }else  if (item.getItemId() == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void remove(View view) {
        helper.removePlace(Integer.parseInt(id.getText().toString()),user.getId());
        Toast.makeText(this, "Item Removed Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}