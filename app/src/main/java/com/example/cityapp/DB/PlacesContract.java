package com.example.cityapp.DB;

public class PlacesContract {
    public static String place_id = "placeID";
    public static String place_address = "placeAddress";
    public static String location_id = "locationID";
    public static String user_id = "userID";
    public static String description = "description";
    public static String place_name = "placeName";
    public static String cat_name = "categoryName";
    public static String latitude = "latitude";
    public static String lagtitude = "lagtitude";
    public static String imagePath = "imgPath";


    public static String TABEL_NAME ="Places";

    public static String CREATE_TABLE ="Create table " + TABEL_NAME + "( "
            + place_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + place_address + " varchar(30) , "
            + location_id + " INTEGER(30) , " // to treat this column as autoincrement i use this properity as sharedPreferences !
            + user_id + " INTEGER(30) , "
            + description + " varchar(30) , "
            + cat_name + " varchar(30) , "
            + place_name + " varchar(30) , "
            + latitude + " varchar(30) , "
            + lagtitude + " varchar(30) , "
            + imagePath + " varchar(300));";
    public static String REMOVE_TABLE ="drop Table "+ TABEL_NAME + " if exists";
}
