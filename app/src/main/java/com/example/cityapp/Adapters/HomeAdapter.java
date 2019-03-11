package com.example.cityapp.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityapp.Activity.EditItemActivity;
import com.example.cityapp.Activity.ViewItem;
import com.example.cityapp.DB.DBHelper;
import com.example.cityapp.Model.Item;
import com.example.cityapp.Model.User;
import com.example.cityapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    ArrayList<Item> items = new ArrayList<>();
    Context context;
    String userRole;
    DBHelper helper;
    Intent intent;
    User user;
    String category;
    private static final String TAG = "HomeAdapter";

    public HomeAdapter(ArrayList<Item> items, Context context,String userRole, User user,String category) {
        this.items = items;
        this.context = context;
        this.userRole= userRole;
        helper = new DBHelper(context);
        this.user = user;
        this.category = category;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        return new HomeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(items.get(position).getPlaceName());
        Picasso.get().load(items.get(position).getImgPath()).into(holder.img);
        // holder.img.setImageResource(R.drawable.no);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemLayout;
        TextView title;
        ImageView img;
        Intent intent;

        public MyViewHolder(final View itemView) {
            super(itemView);

            itemLayout = itemView.findViewById(R.id.itemLayout);
            title = itemView.findViewById(R.id.textItem);
            img = itemView.findViewById(R.id.imageItem);

            final PopupMenu popup = new PopupMenu(context, itemView);
            popup.getMenuInflater()
                    .inflate(R.menu.options, popup.getMenu());

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    if (menuItem.getItemId() == R.id.viewMenu){
                        intent = new Intent(context, ViewItem.class);
                        intent.putExtra("item", items.get(getAdapterPosition()));
                        context.startActivity(intent);

                    }else if (menuItem.getItemId() == R.id.editMenu){
                        intent = new Intent(context, EditItemActivity.class);
                        intent.putExtra("Activity","Edit");
                        intent.putExtra("ActivityName", "Adapter");
                        intent.putExtra("item", items.get(getAdapterPosition()));
                        intent.putExtra("user", user);
                        context.startActivity(intent);


                    }else if (menuItem.getItemId() == R.id.removeMenu){
                        helper.removePlace(items.get(getAdapterPosition()).getPlaceID(),user.getId());
                        Toast.makeText(context, "Item Removed Successfully", Toast.LENGTH_SHORT).show();
                       if (category.equals("all")){
                            items = helper.getAllPlaces(user.getId()) ;
                            notifyDataSetChanged();
                           Log.d(TAG, "onMenuItemClick: " + user.getId());
                            Log.d(TAG, "onMenuItemClick: Deleted ALL" );
                       }else {
                           items = helper.getOnlyCategory(category, user.getId()) ;
                            notifyDataSetChanged();
                           Log.d(TAG, "onMenuItemClick: " + user.getId());
                           Log.d(TAG, "onMenuItemClick: Deleted" );
                       }
                    }
                    return true;
                }
            });



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (userRole.equals("Admin")){
                        popup.show();
                    }else {
                        intent = new Intent(context, ViewItem.class);
                        intent.putExtra("item", items.get(getAdapterPosition()));
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}