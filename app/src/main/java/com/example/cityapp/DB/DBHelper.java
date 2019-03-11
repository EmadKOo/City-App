package com.example.cityapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cityapp.Model.Item;
import com.example.cityapp.Model.User;
import com.example.cityapp.R;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    public static String DBNAME ="City";
    public static int DBVERSION =3;
    Context context;
    DBHelper helper;

    public DBHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(AdminContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(CategoriesContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(PlacesContract.CREATE_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(AdminContract.REMOVE_TABLE);
        sqLiteDatabase.execSQL(CategoriesContract.REMOVE_TABLE);
        sqLiteDatabase.execSQL(PlacesContract.REMOVE_TABLE);
        sqLiteDatabase.execSQL(UserContract.REMOVE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean addUser(String name, String password,String mail, String address, String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.user_name, name);
        contentValues.put(UserContract.user_password, password);
        contentValues.put(UserContract.user_mail, mail);
        contentValues.put(UserContract.user_phone, phone);
        contentValues.put(UserContract.user_address, address);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long n = sqLiteDatabase.insert(UserContract.TABEL_NAME, null,contentValues);
        if (n==-1){
            return false;
        }else {
            return true;
        }
    }

    public User checkLogin(String mail , String password , String role) {
        User user = null;
        String name;
        int id;
        String phone;
        String address;

        String query="";
        if (role.equals("Admin")){
            query = "Select * from " + AdminContract.TABEL_NAME
                    + " where "+ AdminContract.admin_mail + " = '" + mail  + "' and " + AdminContract.admin_password + " = '" + password +"' ;";

        }else if (role.equals("Typical")){
            query = "Select * from " + UserContract.TABEL_NAME
                    + " where "+ UserContract.user_mail + " = '" + mail  + "' and " + UserContract.user_password + " = '" + password +"' ;";
        }

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Log.i("database",cursor.getCount()+"");
        Log.i("database", "checkLogin: " + query);
        if (cursor.moveToFirst()) {
            // get user info if Admin
            if (role.equals("Admin")) {
                id = cursor.getInt(0);
                name = cursor.getString(1);
                user = new User(id, name, mail, password, role);
                return user;
            }else {
                // get user info
                id = cursor.getInt(0);
                name = cursor.getString(1);
                phone = cursor.getString(4);
                address = cursor.getString(5);
                user = new User(id,name,mail,password,role,phone,address);
                return user;
            }
        }
        else {
            Log.i("database", "Null");
            return null;
        }
    }

    public boolean addAdmin(String name, String password,String mail){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AdminContract.admin_name, name);
        contentValues.put(AdminContract.admin_password, password);
        contentValues.put(AdminContract.admin_mail, mail);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long n = sqLiteDatabase.insert(AdminContract.TABEL_NAME, null,contentValues);
        if (n==-1){
            return false;
        }else {
            return true;
        }
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserContract.user_name, user.getName());
        values.put(UserContract.user_password, user.getPassword());
        values.put(UserContract.user_mail, user.getMail());
        values.put(UserContract.user_phone, user.getPhone());
        values.put(UserContract.user_address, user.getAddress());

        return db.update(UserContract.TABEL_NAME, values,
                UserContract.user_id + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    public int updateAdmin(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AdminContract.admin_name, user.getName());
        values.put(AdminContract.admin_password, user.getPassword());
        values.put(AdminContract.admin_mail, user.getMail());

        return db.update(AdminContract.TABEL_NAME, values,
                AdminContract.admin_id + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    public boolean addPlace(String placeAddress,int locationID, int userID,String description, String placeName, String categoryName, String latitude, String lagtitude,String imagePath){
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlacesContract.place_address, placeAddress);
        contentValues.put(PlacesContract.location_id, locationID);
        contentValues.put(PlacesContract.user_id,userID);
        contentValues.put(PlacesContract.description,description );
        contentValues.put(PlacesContract.place_name, placeName);
        contentValues.put(PlacesContract.cat_name, categoryName);
        contentValues.put(PlacesContract.lagtitude, lagtitude);
        contentValues.put(PlacesContract.latitude, latitude);
        contentValues.put(PlacesContract.imagePath, imagePath);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long n = sqLiteDatabase.insert(PlacesContract.TABEL_NAME, null,contentValues);
        if (n==-1){
            return false;
        }else {
            return true;
        }
    }

    public int updatePlace(int placeID, String placeAddress, int userID,String description, String placeName, String categoryName, String latitude, String lagtitude,String imagePath) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PlacesContract.place_address, placeAddress);
        contentValues.put(PlacesContract.user_id,userID );
        contentValues.put(PlacesContract.description,description );
        contentValues.put(PlacesContract.place_name, placeName);
        contentValues.put(PlacesContract.cat_name, categoryName);
        contentValues.put(PlacesContract.lagtitude, lagtitude);
        contentValues.put(PlacesContract.latitude, latitude);
        contentValues.put(PlacesContract.imagePath, imagePath);


        return db.update(PlacesContract.TABEL_NAME, contentValues,
                PlacesContract.place_id + " = ?",
                new String[] {String.valueOf(placeID)});
    }

    public void removePlace(int id ,int userID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PlacesContract.TABEL_NAME,PlacesContract.place_id + " = ?",
                new String[] { String.valueOf(id) });
    }

    public int getLatestUser(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int id = 0;
        Cursor cursor = sqLiteDatabase.query(UserContract.TABEL_NAME, new String[]
                        {
                                UserContract.user_id,
                        }
                        , null, null,
                null, null, null);
            cursor.moveToLast();
            id  = cursor.getInt(0);

        return id;
    }

    public ArrayList<Item> getAllPlaces(int userID){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Item> arrayList = new ArrayList<>();

        String myQuery = "select * from "+ PlacesContract.TABEL_NAME +" where " + PlacesContract.user_id + " = " + userID;
        Cursor cursor = sqLiteDatabase.rawQuery(myQuery, null);
        while (cursor.moveToNext()) {
            arrayList.add(new Item(
                    cursor.getInt(0)
                    ,cursor.getString(1)
                    , cursor.getInt(2)
                    , cursor.getInt(3)
                    , cursor.getString(4)
                    , cursor.getString(5)
                    , cursor.getString(6)
                    , cursor.getString(7)
                    , cursor.getString(8)
                    , cursor.getString(9)
            ));
        }
        return arrayList;
    }

    public ArrayList<Item> getOnlyCategory(String CategoryName,int userID){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Item> arrayList = new ArrayList<>();

        String myQuery = "select * from "+ PlacesContract.TABEL_NAME +" where " + PlacesContract.user_id + " = " + userID + " And " + PlacesContract.cat_name + " = '" + CategoryName+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(myQuery, null);

        while (cursor.moveToNext()) {
            arrayList.add(new Item(
                    cursor.getInt(0)
                    ,cursor.getString(1)
                    , cursor.getInt(2)
                    , cursor.getInt(3)
                    , cursor.getString(4)
                    , cursor.getString(5)
                    , cursor.getString(6)
                    , cursor.getString(7)
                    , cursor.getString(8)
                    , cursor.getString(9)
            ));
        }
        return arrayList;
    }

    public int getLatestAdmin() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int id=0;
        Cursor cursor = sqLiteDatabase.query(AdminContract.TABEL_NAME, new String[]
                        {
                                AdminContract.admin_id
                        }
                , null, null,
                null, null, null);
          cursor.moveToLast();
          id = cursor.getInt(0);
        return id;
    }

}