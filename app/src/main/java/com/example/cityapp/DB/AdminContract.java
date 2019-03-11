package com.example.cityapp.DB;

public class AdminContract {
    public static String admin_id = "id";
    public static String admin_name = "name";
    public static String admin_password = "password";
    public static String admin_mail = "mail";
    public static String TABEL_NAME ="Admin";

    public static String CREATE_TABLE ="Create table " + TABEL_NAME + "( "
            + admin_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + admin_name + " varchar(30) , "
            + admin_password + " varchar(30) , "
            + admin_mail + " varchar(30))";
    public static String REMOVE_TABLE ="drop Table "+ TABEL_NAME + " if exists";
}
