package com.example.cityapp.DB;

public class UserContract {
    public static String user_id = "id";
    public static String user_name = "name";
    public static String user_password = "password";
    public static String user_mail = "mail";
    public static String user_phone = "phone";
    public static String user_address = "address";
    public static String TABEL_NAME ="User";

    public static String CREATE_TABLE ="Create table " + TABEL_NAME + "( "
            + user_id + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + user_name + " varchar(30) , "
            + user_password + " varchar(30) , "
            + user_mail + " varchar(30) , "
            + user_phone + " varchar(30) , "
            + user_address + " varchar(30))";
    public static String REMOVE_TABLE ="drop Table "+ TABEL_NAME + " if exists";
}
