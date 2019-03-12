package com.example.cityapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityapp.DB.DBHelper;
import com.example.cityapp.Model.User;
import com.example.cityapp.R;

public class SignupActivity extends AppCompatActivity {

    EditText name;
    EditText mail;
    EditText password;
    EditText repeatPasswordRegister;
    EditText phoneRegister;
    CheckBox checkBox;
    User user;
    TextView doYou;
    DBHelper helper ;

    private static final String TAG = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //customize toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.register);

        helper = new DBHelper(getApplicationContext());
        setUpViews();
    }

    public void setUpViews(){
        name = findViewById(R.id.nameRegister);
        mail = findViewById(R.id.mailRegister);
        password = findViewById(R.id.passwordRegister);
        phoneRegister = findViewById(R.id.phoneRegister);
        checkBox = findViewById(R.id.adminCheckBoxSignUp);
        repeatPasswordRegister = findViewById(R.id.repeatPasswordRegister);
        doYou = findViewById(R.id.doo);

    }

    public void register(View view) {
        if (checkBox.isChecked()){
            if (TextUtils.isEmpty(name.getText())
                    || TextUtils.isEmpty(mail.getText())
                    || TextUtils.isEmpty(repeatPasswordRegister.getText())
                    || TextUtils.isEmpty(phoneRegister.getText())
                    ||TextUtils.isEmpty(password.getText())) {
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            }else if(!(repeatPasswordRegister.getText().toString().equals(password.getText().toString()))){
                Snackbar.make(view, "Passwords doesn`t match", Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                        .show();
            }else {
                helper.addAdmin(name.getText().toString(),password.getText().toString(),mail.getText().toString());
                addInitDataToSQLITE(helper.getLatestAdmin());
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }else {
            if (TextUtils.isEmpty(name.getText())
                    || TextUtils.isEmpty(mail.getText())
                    || TextUtils.isEmpty(repeatPasswordRegister.getText())
                    || TextUtils.isEmpty(phoneRegister.getText())
                    ||TextUtils.isEmpty(password.getText())) {
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            }else if(!(repeatPasswordRegister.getText().toString().equals(password.getText().toString()))){
                Snackbar.make(view, "Passwords doesn`t match", Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                        .show();
            } else {
                helper.addUser(name.getText().toString(), password.getText().toString(), mail.getText().toString(), "",phoneRegister.getText().toString());
                addInitDataToSQLITE(helper.getLatestUser());
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                this.finish();
            }
        }
    }


    public void addInitDataToSQLITE(int id){
      Log.d(TAG, "addInitDataToSQLITE: " + "*******************************************");
        helper = new DBHelper(SignupActivity.this);
        int currentLocation = getSharedPreferences() + 1;
        addToSharedPreferences(currentLocation);
        int newLocation = getSharedPreferences();
        boolean res;
       res =    helper.addPlace("",newLocation,id, String.valueOf(R.string.panorama),"Panorma Shibin","Hotels","37.7749","-122.4194","https://i.ibb.co/r6Sv0X1/panorama.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.highWay),"High way Hotel","Hotels","37.7749","-122.4194","https://i.ibb.co/kMgvhw7/highWay.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.rual),"Rural Guest House","Hotels","37.7749","-122.4194","https://i.ibb.co/TtTBYV8/rual.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.panorama),"The Rose House","Hotels","37.7749","-122.4194","https://me.ibb.co/QCBvxFB/rosee.jpg");

        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.khleel),"قصر خليل الجزار","Tourists","37.7749","-122.4194","https://i.ibb.co/nC6ggT7/5leel.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.bndr),"تل البندارية","Tourists","37.7749","-122.4194","https://me.ibb.co/1vk38hj/srs.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.qasr),"برج المنوفية السياحي","Tourists","37.7749","-122.4194","https://me.ibb.co/4S9Hvdm/sya7a.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.rual),"The Rose House","Tourists","37.7749","-122.4194","https://me.ibb.co/QCBvxFB/rosee.jpg");

        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.europe),"المستشفى الاوروبى بشبين الكوم","Hospitals","37.7749","-122.4194","https://me.ibb.co/1vhncDz/tarek.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.gam3a),"مستشفى جامعة المنوفية","Hospitals","37.7749","-122.4194","https://i.ibb.co/s5LhNd1/gam4a.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.m3had),"مستشفي معهد المنوفيه","Hospitals","37.7749","-122.4194","https://me.ibb.co/N1WDC5H/m3had.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.europe),"المستشفى الاوروبى بشبين الكوم","Hospitals","37.7749","-122.4194","https://me.ibb.co/1vhncDz/tarek.jpg");

        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.s7ya),"الاداره الطبيه بالمنوفيه","Government","37.7749","-122.4194","https://me.ibb.co/Z6yJ1wH/edara.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.zra3a),"اداره الزراعه بالمنوفيه","Government","37.7749","-122.4194","https://i.ibb.co/gwGhKS6/zra3a.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.edara),"اداره جامعه المنوفيه","Government","37.7749","-122.4194","https://i.ibb.co/Z6yJ1wH/edara.jpg");
        res =   helper.addPlace("",newLocation,id, String.valueOf(R.string.m7kma),"محكمه شبين الكوم","Government","37.7749","-122.4194","https://i.ibb.co/QpRFLXj/m7kma.jpg");
        Log.d(TAG, "addInitDataToSQLITE: " + res);
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

    public void goToLogin(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent1;
        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            intent1 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent1);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
