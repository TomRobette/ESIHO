package com.esiho.combat.entities;

import com.badlogic.gdx.graphics.Texture;
import com.esiho.combat.types.Eau;
import com.esiho.combat.types.Type;

import java.util.HashMap;

public enum CombatEntityType {
    JOUEUR("C1", new Eau(), "joueur");

    private String id;
    private Type type;
    private Texture sprite;

    private CombatEntityType(String id, Type type, String spriteName){
        this.id = id;
        this.type = type;
        this.sprite = new Texture(spriteName+".png");
    }


    private static HashMap<String, CombatEntityType> cbtEntityTypes;

    static {
        cbtEntityTypes = new HashMap<String, CombatEntityType>();
        for (CombatEntityType cbttype : CombatEntityType.values()){
            cbtEntityTypes.put(cbttype.id, cbttype);
        }
    }
}
