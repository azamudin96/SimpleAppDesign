package com.example.simpleuiapp.model;

public class Model {

    private int image;
    private String title;
    private String desc;
    private int prog;

    private int background_colour;

    public Model(int image, String title, String desc , int background_colour , int prog) {
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.background_colour = background_colour;
        this.prog = prog;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getBackground_colour() {
        return background_colour;
    }

    public void setBackground_colour(int background_colour) {
        this.background_colour = background_colour;
    }

    public int getProg() {
        return prog;
    }

    public void setProg(int prog) {
        this.prog = prog;
    }
}
