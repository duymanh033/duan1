package com.example.heaven_motor.model;

public class Vehicle {
    private String  id;
    private int categorie_id;
    private String name;
    private String BKS;
    private int capacity;
    private String brand;
    private int status;
    private int price;
    private int year,trangThai;
    private byte[] img;

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Vehicle() {
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Vehicle(String id, int categorie_id, String name, String BKS, int capacity, String brand, int status, int price, int year, int trangThai, byte[] img) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.name = name;
        this.BKS = BKS;
        this.capacity = capacity;
        this.brand = brand;
        this.status = status;
        this.price = price;
        this.year = year;
        this.trangThai = trangThai;
        this.img = img;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBKS() {
        return BKS;
    }

    public void setBKS(String BKS) {
        this.BKS = BKS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
