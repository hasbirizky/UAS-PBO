package com.uaspborestoran.data;

public class User {
    private String name, usn, pw;
    private int id;

    public User(int id, String name, String usn, String pw) {
        this.name = name;
        this.usn = usn;
        this.pw = pw;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
    
}
