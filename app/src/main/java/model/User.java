package model;

import java.util.Date;

/**
 * Created by Kosha on 3/8/2017.
 */

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String username;
    private String address;
    private String sex;
    private double height;
    private double weight;
    private String phone;
    private String dob;
    private double max_cal;
    private double max_protien;
    private double max_fiber;
    private int age;

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getMax_cal() {
        return max_cal;
    }

    public void setMax_cal(Double max_cal) {
        this.max_cal = max_cal;
    }

    public double getMax_protien() {
        return max_protien;
    }

    public void setMax_protien(Double max_protien) {
        this.max_protien = max_protien;
    }

    public double getMax_fiber() {
        return max_fiber;
    }

    public void setMax_fiber(Double max_fiber) {
        this.max_fiber = max_fiber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
}
