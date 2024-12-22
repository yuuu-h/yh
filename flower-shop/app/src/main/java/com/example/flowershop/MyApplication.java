package com.example.flowershop;

import android.app.Application;

import com.example.flowershop.entity.Stuff;
import com.example.flowershop.sqlite.DBStuff;
import com.example.flowershop.utils.AppUtils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (AppUtils.isFirstTimeLaunch()) {
            Stuff roseWhite = new Stuff("白玫瑰", "清新素雅的白色玫瑰，花瓣洁白如雪，散发着淡淡的清香", "玫瑰系列", "28");
            DBStuff.add(roseWhite);

            Stuff rosePink = new Stuff("粉玫瑰", "柔美粉色的玫瑰，娇艳欲滴，花语是甜蜜的爱情与温柔的关怀", "玫瑰系列", "22");
            DBStuff.add(rosePink);

            Stuff carnation = new Stuff("康乃馨", "康乃馨象征着母爱与关怀，花语是无尽的爱与感激，花瓣色彩丰富，芬芳宜人", "母亲节活动", "27");
            DBStuff.add(carnation);

            Stuff roseBlue = new Stuff("蓝玫瑰", "神秘而罕见的蓝色玫瑰，象征着奇迹与梦想，独特的色彩令人心驰神往", "玫瑰系列", "26");
            DBStuff.add(roseBlue);

            Stuff sunflower = new Stuff("向日葵", "向日葵是太阳的化身，代表着希望与积极的生命力，金黄色的花朵给人温暖与活力", "热卖款", "30");
            DBStuff.add(sunflower);

            Stuff tulip = new Stuff("郁金香", "郁金香是春天的使者，花语是美丽的爱情与真挚的情感，多姿多彩的花瓣散发着迷人的芳香", "热卖款", "21");
            DBStuff.add(tulip);

            AppUtils.setFirstTimeLaunch(false);

        }
    }
}
