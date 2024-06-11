package com.uaspborestoran.data;

public class Menu {
    private String nama, deskripsi, ukuran, kategori, photo;
    private double harga;
    private int id;

    public Menu(int id, String nama, String deskripsi, String ukuran, double harga, String kategori, String photo) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.ukuran = ukuran;
        this.harga = harga;
        this.kategori = kategori;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }


    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
