package com.esiho.combat.teams;

import com.esiho.combat.entities.CombatEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class TeamSnapshot {

    public String type;
    public ArrayList<CombatEntity> listeCombatEntities;
    public HashMap<String, String> data;

    public TeamSnapshot(){}

    public TeamSnapshot(String type, ArrayList<CombatEntity> listeCombatEntities){
        this.type=type;
        this.listeCombatEntities=listeCombatEntities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void putFloat(String key, float value){
        data.put(key, ""+value);
    }

    public void putInt(String key, int value){
        data.put(key, ""+value);
    }

    public void putBoolean(String key, boolean value){
        data.put(key, ""+value);
    }

    public void putString(String key, String value){
        data.put(key, value);
    }

    public float getFloat(String key, float defaultvalue){
        if (data.containsKey(key)){
            try{
                return Float.parseFloat(data.get(key));
            }catch (Exception e){
                return defaultvalue;
            }
        }else{
            return defaultvalue;
        }
    }

    public int getInt(String key, int defaultvalue){
        if (data.containsKey(key)){
            try{
                return Integer.parseInt(data.get(key));
            }catch (Exception e){
                return defaultvalue;
            }
        }else{
            return defaultvalue;
        }
    }

    public boolean getBoolean(String key, boolean defaultvalue){
        if (data.containsKey(key)){
            try{
                return Boolean.parseBoolean(data.get(key));
            }catch (Exception e){
                return defaultvalue;
            }
        }else{
            return defaultvalue;
        }
    }

    public String getString(String key, String defaultvalue){
        if (data.containsKey(key)){
            return data.get(key);
        }else{
            return defaultvalue;
        }
    }
}
