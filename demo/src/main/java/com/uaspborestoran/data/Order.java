package com.uaspborestoran.data;

public class Order {
    private int id, iduser, idmenu;
    private double total;
    public Order(int id, int iduser, int idmenu, double total) {
        this.id = id;
        this.iduser = iduser;
        this.idmenu = idmenu;
        this.total = total;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    public int getIdmenu() {
        return idmenu;
    }
    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    
}
