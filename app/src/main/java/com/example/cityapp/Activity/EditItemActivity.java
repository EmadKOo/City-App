package com.example.cityapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cityapp.DB.DBHelper;
import com.example.cityapp.Model.Item;
import com.example.cityapp.Model.User;
import com.example.cityapp.R;

public class EditItemActivity extends AppCompatActivity {

    EditText editID;
    EditText editAddress;
    EditText editPlaceName;
    Spinner spinnerCategory;
    EditText editLagtitude;
    EditText editLatitude;
    EditText editImagePath;
    EditText desc;
    Button submit;
    int index;
    String userRole;
    User user;
    DBHelper helper;
    Item item;
    private static final String TAG = "EditItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        //customize toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Edit Item");

        helper = new DBHelper(getApplicationContext());

        Log.d(TAG, "onCreate: " + getSharedPreferences());
        user = (User) getIntent().getSerializableExtra("user");
        userRole = "Admin";

        setUpViews();

        if (getIntent().getStringExtra("Activity").toString().equals("Edit")) {

            if (getIntent().getStringExtra("ActivityName").toString().equals("Adapter")) {
                item = (Item) getIntent().getSerializableExtra("item");
                editID.setText(item.getPlaceID() + "");
                editAddress.setText(item.getPlaceAddress());
                editPlaceName.setText(item.getPlaceName());
                int selectionPosition = 0;
                String[] spinnerList = getResources().getStringArray(R.array.Category);
                for (int x = 0; x < spinnerList.length; x++) {
                    if (item.getCategory().equals(spinnerList[x])) {
                        selectionPosition = x;
                        break;
                    }
                }
                spinnerCategory.setSelection(selectionPosition, true);
                editLagtitude.setText(item.getLagtitude());
                desc.setText(item.getDescription());
                editLatitude.setText(item.getLatitude());
                editImagePath.setText(item.getImgPath());
            }

            editID.setVisibility(View.VISIBLE);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(editAddress.getText()) ||
                            TextUtils.isEmpty(editPlaceName.getText())
                            || TextUtils.isEmpty(editID.getText())
                            || TextUtils.isEmpty(editLagtitude.getText())
                            || TextUtils.isEmpty(editImagePath.getText())
                            || TextUtils.isEmpty(desc.getText())
                            || TextUtils.isEmpty(editLatitude.getText())) {
                        Toast.makeText(EditItemActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                    } else {
                        int placeID = Integer.valueOf(editID.getText().toString());
                        //  int res = helper.updatePlace(Integer.parseInt(String.valueOf(placeID)),editAddress.getText().toString(),user.getId(),desc.getText().toString(),editPlaceName.getText().toString(),editCategory.getText().toString(),editLatitude.getText().toString(),editLagtitude.getText().toString(),editImagePath.getText().toString());
                        int res = helper.updatePlace(Integer.parseInt(String.valueOf(placeID)), editAddress.getText().toString(), user.getId(), desc.getText().toString(), editPlaceName.getText().toString(), spinnerCategory.getSelectedItem().toString(), editLatitude.getText().toString(), editLagtitude.getText().toString(), editImagePath.getText().toString());
                        Log.i("DATABASE", "onClick: " + res);
                        Toast.makeText(getApplicationContext(), "Edit Done Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        } else {
            editImagePath.setText("http://i.imgur.com/DvpvklR.png");
            editID.setVisibility(View.GONE);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(editAddress.getText()) ||
                            TextUtils.isEmpty(editPlaceName.getText())
                            || TextUtils.isEmpty(editLagtitude.getText())
                            || TextUtils.isEmpty(editImagePath.getText())
                            || TextUtils.isEmpty(desc.getText())
                            || TextUtils.isEmpty(editLatitude.getText())) {
                        Toast.makeText(EditItemActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                    } else {
                        int currentLocation = getSharedPreferences() + 1;
                        addToSharedPreferences(currentLocation);
                        int newLocation = getSharedPreferences();

                        //  boolean result = helper.addPlace(editAddress.getText().toString(),newLocation,user.getId(),desc.getText().toString(),editPlaceName.getText().toString(),editCategory.getText().toString(),editLatitude.getText().toString(),editLagtitude.getText().toString(),editImagePath.getText().toString());
                        boolean result = helper.addPlace(editAddress.getText().toString(), newLocation, user.getId(), desc.getText().toString(), editPlaceName.getText().toString(), spinnerCategory.getSelectedItem().toString(), editLatitude.getText().toString(), editLagtitude.getText().toString(), editImagePath.getText().toString());
                        if (result == true) {
                            Toast.makeText(EditItemActivity.this, "Place Added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(EditItemActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent1;
        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            intent1 = new Intent(getApplicationContext(), MainActivity.class);
            intent1.putExtra("user", user);
            startActivity(intent1);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void setUpViews() {
        editID = findViewById(R.id.editID);
        editAddress = findViewById(R.id.editAddress);
        editPlaceName = findViewById(R.id.editPlaceName);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        String[] spinnerList = getResources().getStringArray(R.array.Category);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerCategory.setAdapter(spinnerAdapter);


        editLagtitude = findViewById(R.id.editLagtitude);
        desc = findViewById(R.id.editDescription);
        editLatitude = findViewById(R.id.editLatitude);
        editImagePath = findViewById(R.id.editImagePath);
        submit = findViewById(R.id.submitEdit);
    }

    public void addToSharedPreferences(int locationID) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("myLocationID", locationID);
        editor.commit();
    }

    public int getSharedPreferences() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int locationID = sharedPref.getInt("myLocationID", 1);
        return locationID;
    }
}