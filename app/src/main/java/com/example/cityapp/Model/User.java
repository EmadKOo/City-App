package com.example.cityapp.Model;

import java.io.Serializable;

public class User  implements Serializable {
    private String name;
    private String mail;
    private String password;
    private String role;
    // new
    private int id;
    private String phone;
    private String address;

    public User(int id,String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.id = id;
    }

    public User(int id , String name, String mail, String password, String role) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public User( int id, String name, String mail, String password, String role, String phone, String address) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.id = id;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
