package com.example.vuquang.jars.activity.data.db.model;

import com.example.vuquang.jars.R;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public enum JarType {
    FFA(0),
    LTSS(1),
    EDU(2),
    NEC(3),
    PLAY(4),
    GIVE(5),
    ALL(6);
    public final static String nameList[] = {"FFA","LTSS","EDU","NEC","Play","Give","All"};
    public final static float ratioList[] = {0.1f,0.1f,0.05f,0.55f,0.1f,0.1f,1f};
    public final static int resIconList[] = {R.drawable.ic_ffa_jar,
            R.drawable.ic_ltss_jar,
            R.drawable.ic_edu_jar,
            R.drawable.ic_nec_jar,
            R.drawable.ic_play_jar,
            R.drawable.ic_give_jar,
            R.drawable.ic_jar};

    private int id;

    JarType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static int getIdFromName(String name) {
        for (int i = 0; i < nameList.length; i++) {
            if (name.equals(nameList[i])) {
                return i;
            }
        }
        return -1;
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
