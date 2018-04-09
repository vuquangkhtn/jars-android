package com.example.vuquang.jars.activity.model;

import com.example.vuquang.jars.R;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public enum JarType {
    FFA,
    LTSS,
    EDU,
    NEC,
    PLAY,
    GIVE;
    public final static String nameList[] = {"FFA","LTSS","EDU","NEC","Play","Give"};
    public final static float ratioList[] = {0.1f,0.1f,0.05f,0.55f,0.1f,0.1f};
    public final static int resIconList[] = {R.drawable.ic_jar,R.drawable.ic_jar,
            R.drawable.ic_jar,R.drawable.ic_jar,
            R.drawable.ic_jar, R.drawable.ic_jar,};

    private int id;

    JarType() {
        switch(this) {
            case FFA: {
                this.id = 0;
                break;
            }
            case LTSS: {
                this.id = 1;
                break;
            }
            case EDU: {
                this.id = 2;
                break;
            }
            case NEC: {
                this.id = 3;
                break;
            }
            case PLAY: {
                this.id = 4;
                break;
            }
            case GIVE: {
                this.id = 5;
                break;
            }
            default: {
                this.id = -1;
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return nameList[id];
    }

    public float getRatio() {
        return ratioList[id];
    }

    public int getResIdIcon() {
        return resIconList[id];
    }
}
