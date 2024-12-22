package com.example.flowershop.entity;

import com.example.flowershop.R;

public class Stuff {
    private String id;//id
    private String name;//名称
    private String title;//标题
    private String kind;//分类
    private String price;//价格
    private int pic;//图片

    public Stuff() {
        this.id="-1";
    }

    public Stuff( String name, String title, String kind, String price ) {
        this.id = "id";
        this.name = name;
        this.title = title;
        this.kind = kind;
        this.price = price;
        this.pic = 0;
    }


    public Stuff(String id, String name, String title, String kind, String price) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.kind = kind;
        this.price = price;
        switch (name){
            case "白玫瑰":
                this.pic= R.drawable.stuff_bmg;
                break;
            case "粉玫瑰":
                this.pic=R.drawable.stuff_fmg;
                break;
            case "康乃馨":
                this.pic=R.drawable.stuff_knx;
                break;
            case "蓝玫瑰":
                this.pic=R.drawable.stuff_lmg;
                break;
            case "向日葵":
                this.pic=R.drawable.stuff_xrk;
                break;
            case "郁金香":
                this.pic=R.drawable.stuff_yjx;
                break;
            default:
                this.pic=R.drawable.ic_about;
                break;
        }
    }

    @Override
    public String toString() {
        return "Stuff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", kind='" + kind + '\'' +
                ", price='" + price + '\'' +
                ", pic=" + pic +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
