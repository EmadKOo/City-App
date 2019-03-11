package com.example.cityapp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityapp.Model.Item;
import com.example.cityapp.R;
import com.squareup.picasso.Picasso;

public class ViewItem extends AppCompatActivity {

    ImageView imageView;
    TextView txtTitle;
    TextView txtDescription;
    Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        //customize toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.details);

        item = (Item) getIntent().getSerializableExtra("item");
        setUpViews();
    }

    public void setUpViews(){
        imageView = findViewById(R.id.imageViewItem);
        txtTitle = findViewById(R.id.textTitle);
        txtDescription = findViewById(R.id.textDescription);

        Picasso.get().load(item.getImgPath()).placeholder(R.drawable.no).into(imageView);
        txtDescription.setText(item.getDescription());
        txtTitle.setText(item.getPlaceName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }

    public void goToMaps(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.google.android.apps.maps");
        try {
            intent.setData(Uri.parse("geo:"+item.getLatitude()+","+item.getLagtitude()));
        }catch (Exception ex){
            intent.setData(Uri.parse("geo:37.7749,-122.4194"));
            Toast.makeText(this, "Location is not correct", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);

    }
}