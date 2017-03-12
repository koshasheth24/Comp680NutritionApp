package model;

import java.util.Date;

/**
 * Created by Kosha on 3/10/2017.
 */

public class UserCalorieCount {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal_cal() {
        return total_cal;
    }

    public void setTotal_cal(float total_cal) {
        this.total_cal = total_cal;
    }

    public float getTotal_protien() {
        return total_protien;
    }

    public void setTotal_protien(float total_protien) {
        this.total_protien = total_protien;
    }

    public float getTotal_fiber() {
        return total_fiber;
    }

    public void setTotal_fiber(float total_fiber) {
        this.total_fiber = total_fiber;
    }

    private Date date;
    private float total_cal;
    private float total_protien;
    private float total_fiber;

}
