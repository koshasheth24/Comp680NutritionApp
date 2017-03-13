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
    private float height;
    private float weight;
    private int phone;
    private String dob;
    private float max_cal;
    private float max_protien;
    private float max_fiber;
    private float age;

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

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public float getMax_cal() {
        return max_cal;
    }

    public void setMax_cal(float max_cal) {
        this.max_cal = max_cal;
    }

    public float getMax_protien() {
        return max_protien;
    }

    public void setMax_protien(float max_protien) {
        this.max_protien = max_protien;
    }

    public float getMax_fiber() {
        return max_fiber;
    }

    public void setMax_fiber(float max_fiber) {
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
