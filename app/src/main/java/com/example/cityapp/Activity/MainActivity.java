package com.example.cityapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityapp.Adapters.HomeAdapter;
import com.example.cityapp.Adapters.ViewPagerAdapter;
import com.example.cityapp.DB.DBHelper;
import com.example.cityapp.Model.Item;
import com.example.cityapp.Model.User;
import com.example.cityapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    ArrayList<Item> items = new ArrayList<>();
    String userRole;
    User user;
    // Data data ;
    TextView txtDescription;

    LinearLayout navigationSection;
    LinearLayout itemView;
    RelativeLayout touristsSection;
    RelativeLayout hospitalsSection;
    RelativeLayout hotelsSection;
    RelativeLayout governmentsSection;
    Intent sectionIntent;

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    Integer[] homeImages = {R.drawable.mnf, R.drawable.logo,R.drawable.no};
    Integer[] hospitalImages = {R.drawable.hos1,R.drawable.hos2,R.drawable.hos3};
    Integer[] hotelsImages = {R.drawable.hos1,R.drawable.hot2,R.drawable.hot3};
    Integer[] GovlImages = {R.drawable.gov1,R.drawable.gov2,R.drawable.gov3};
    Integer[] touristsImages = {R.drawable.sya1,R.drawable.sya2,R.drawable.sya3};

    String allDescription = "Description for all Categories";
    String touristsDescription = "Description for Tourists";
    String governmentDescription = "Description for Government";
    String hospitalsDescription = "Description for Hospitals";
    String hotelsDescription = "Description for Hotels";

    String refreshOrNot ="no";
    private long backPressed;
    DBHelper helper;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        helper = new DBHelper(getApplicationContext());

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), homeImages);
        viewPager.setAdapter(viewPagerAdapter);


        txtDescription = findViewById(R.id.homeDescription);
        txtDescription.setText(allDescription);
        homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"all");

        user = (User) getIntent().getSerializableExtra("user");
        userRole = user.getRole();
        //  data = new Data(getApplicationContext());
        Log.d(TAG, "onCreate: "  + user.getId());
        setUpRecyclerView();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (navigationSection.getVisibility() == View.VISIBLE){
            super.onBackPressed();

            }else {
            navigationSection.setVisibility(View.VISIBLE);
            itemView.setVisibility(View.GONE);
        }
        }
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
            intent1.putExtra("Activity","Edit");
            intent1.putExtra("ActivityName", "Activity");
            intent1.putExtra("index",0+"");
            intent1.putExtra("user", user);
            startActivity(intent1);

        }else if (id == R.id.adminRemove){
            intent1 = new Intent(getApplicationContext(), RemoveItemActivity.class);
            intent1.putExtra("user", user);
            startActivity(intent1);

        }else if (id == R.id.adminAdd){
            intent1 = new Intent(getApplicationContext(), EditItemActivity.class);
            intent1.putExtra("Activity", "Add");
            intent1.putExtra("user", user);
            startActivity(intent1);
            finish();

        }else if (id == R.id.adminLogout){
            intent1 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent1);
            finish();
        }else if (id == R.id.profileAdmin){
            intent1 = new Intent(getApplicationContext(), UpdateProfile.class);
            intent1.putExtra("user", user);
            startActivity(intent1);
        }else if (id == R.id.profileUser){
            intent1 = new Intent(getApplicationContext(), UpdateProfile.class);
            intent1.putExtra("user", user);
            startActivity(intent1);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.navHospitals) {
            items = helper.getOnlyCategory("Hospitals",user.getId());
            homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"Hospitals");
            recyclerView.setAdapter(homeAdapter);
            viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), hospitalImages);
            viewPager.setAdapter(viewPagerAdapter);
            txtDescription.setText(hospitalsDescription);
            navigationSection.setVisibility(View.GONE);
            itemView.setVisibility(View.VISIBLE);

        } else if (id == R.id.navHotels) {
            items = helper.getOnlyCategory("Hotels",user.getId());
            homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"Hotels");
            recyclerView.setAdapter(homeAdapter);
            viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), hotelsImages);
            viewPager.setAdapter(viewPagerAdapter);
            txtDescription.setText(hotelsDescription);
            navigationSection.setVisibility(View.GONE);
            itemView.setVisibility(View.VISIBLE);

        } else if (id == R.id.navGovernment) {
            items = helper.getOnlyCategory("Government",user.getId());
            homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"Government");
            recyclerView.setAdapter(homeAdapter);
            viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), GovlImages);
            viewPager.setAdapter(viewPagerAdapter);
            txtDescription.setText(governmentDescription);
            navigationSection.setVisibility(View.GONE);
            itemView.setVisibility(View.VISIBLE);

        } else if (id == R.id.navAll) {
            items = helper.getAllPlaces(user.getId());
            Log.d(TAG, "onNavigationItemSelected: " + user.getId());
            homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"all");
            recyclerView.setAdapter(homeAdapter);
            viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), homeImages);
            viewPager.setAdapter(viewPagerAdapter);
            txtDescription.setText(allDescription);
            navigationSection.setVisibility(View.GONE);
            itemView.setVisibility(View.VISIBLE);

        } else if (id == R.id.navTourist) {
            items = helper.getOnlyCategory("Tourists",user.getId());
            homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"Tourists");
            recyclerView.setAdapter(homeAdapter);
            viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), touristsImages);
            viewPager.setAdapter(viewPagerAdapter);
            txtDescription.setText(touristsDescription);
            navigationSection.setVisibility(View.GONE);
            itemView.setVisibility(View.VISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setUpRecyclerView(){
        recyclerView = findViewById(R.id.homeRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        items = helper.getAllPlaces(user.getId());
        homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"all");
        recyclerView.setAdapter(homeAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //  items.clear();
        items = helper.getAllPlaces(user.getId());
        homeAdapter.notifyDataSetChanged();
    }

    public void initViews(){
        itemView = findViewById(R.id.itemView);
        navigationSection= findViewById(R.id.navigationSection);
        touristsSection = findViewById(R.id.touristsSection);
        hospitalsSection = findViewById(R.id.hospitalsSection);
        hotelsSection = findViewById(R.id.hotelsSection);
        governmentsSection = findViewById(R.id.governmentsSection);

        hospitalsSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items = helper.getOnlyCategory("Hospitals",user.getId());
                homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"Hospitals");
                recyclerView.setAdapter(homeAdapter);
                viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), hospitalImages);
                viewPager.setAdapter(viewPagerAdapter);
                txtDescription.setText(hospitalsDescription);
                navigationSection.setVisibility(View.GONE);
                itemView.setVisibility(View.VISIBLE);
            }
        });

        hotelsSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items = helper.getOnlyCategory("Hotels",user.getId());
                homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"Hotels");
                recyclerView.setAdapter(homeAdapter);
                viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), hotelsImages);
                viewPager.setAdapter(viewPagerAdapter);
                txtDescription.setText(hotelsDescription);
                navigationSection.setVisibility(View.GONE);
                itemView.setVisibility(View.VISIBLE);
            }
        });

        touristsSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items = helper.getOnlyCategory("Tourists",user.getId());
                homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"Tourists");
                recyclerView.setAdapter(homeAdapter);
                viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), touristsImages);
                viewPager.setAdapter(viewPagerAdapter);
                txtDescription.setText(touristsDescription);
                navigationSection.setVisibility(View.GONE);
                itemView.setVisibility(View.VISIBLE);
            }
        });

        governmentsSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items = helper.getOnlyCategory("Government",user.getId());
                homeAdapter = new HomeAdapter(items, getApplicationContext(),userRole,user,"Government");
                recyclerView.setAdapter(homeAdapter);
                viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), GovlImages);
                viewPager.setAdapter(viewPagerAdapter);
                txtDescription.setText(governmentDescription);
                navigationSection.setVisibility(View.GONE);
                itemView.setVisibility(View.VISIBLE);
            }
        });

    }


}