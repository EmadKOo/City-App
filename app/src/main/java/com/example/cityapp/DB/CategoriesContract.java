package com.example.cityapp.DB;

public class CategoriesContract {
    public static String cat_id = "id";
    public static String cat_name = "name";
    public static String place_id = "placeID";
    public static String TABEL_NAME ="Categories";

    public static String CREATE_TABLE ="Create table " + TABEL_NAME + "( "
            + cat_id + " INTEGER PRIMARY KEY , "
            + cat_name + " varchar(30) , "
            + place_id + " varchar(30))";
    public static String REMOVE_TABLE ="drop Table "+ TABEL_NAME + " if exists";
}
