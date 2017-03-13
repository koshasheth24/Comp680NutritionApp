package model;

import java.util.Date;

/**
 * Created by Kosha on 3/10/2017.
 */

public class ItemNutrient {

    private String item;
    private float calories;
    private float protiens;
    private float fiber;


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProtiens() {
        return protiens;
    }

    public void setProtiens(float protiens) {
        this.protiens = protiens;
    }

    public float getFiber() {
        return fiber;
    }

    public void setFiber(float fiber) {
        this.fiber = fiber;
    }
}
