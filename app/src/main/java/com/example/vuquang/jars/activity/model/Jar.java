package com.example.vuquang.jars.activity.model;

import com.example.vuquang.jars.R;

import java.io.Serializable;

/**
 * Created by CPU10584-local on 26-Mar-18.
 */

public class Jar {

    public int resIcon;
    public String name;
    public long amount;
    public float ratio;

    public Jar(int resIcon, String name, long amount, float ratio) {
        this.resIcon = resIcon;
        this.name = name;
        this.amount = amount;
        this.ratio = ratio;
    }

    public static Jar initJar(JarType type, long total) {

        String nameList[] = {"FFA","LTSS","EDU","NEC","Play","Give"};
        float ratioList[] = {0.1f,0.1f,0.05f,0.55f,0.1f,0.1f};
        int resIconList[] = {R.drawable.ic_jar,R.drawable.ic_jar,
                R.drawable.ic_jar,R.drawable.ic_jar,
                R.drawable.ic_jar,R.drawable.ic_jar,};

        switch (type) {
            case FFA: {
                return new Jar(resIconList[0],nameList[0],Math.round(total*ratioList[0]), ratioList[0]);
            }
            case LTSS: {
                return new Jar(resIconList[1],nameList[1],Math.round(total*ratioList[1]), ratioList[1]);
            }
            case EDU: {
                return new Jar(resIconList[2],nameList[2],Math.round(total*ratioList[2]), ratioList[2]);
            }
            case NEC: {
                return new Jar(resIconList[3],nameList[3],Math.round(total*ratioList[3]), ratioList[3]);
            }
            case PLAY: {
                return new Jar(resIconList[4],nameList[4],Math.round(total*ratioList[4]), ratioList[4]);
            }
            case GIVE: {
                return new Jar(resIconList[5],nameList[5],Math.round(total*ratioList[5]), ratioList[5]);
            }
            default: {
                return  null;
            }
        }
    }

    public String getTitle() {
        return String.format("%s (%d)",name, Math.round(ratio));
    }

    public enum JarType implements Serializable {
        FFA,
        LTSS,
        EDU,
        NEC,
        PLAY,
        GIVE
    }
}
