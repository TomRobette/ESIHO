package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public enum ArmureType {
    ARMURE_BANALE("I5", "armure_banale", "Armure Banale", 100, 1.2),
    ARMURE_LEGENDAIRE("I6", "armure_legendaire", "Armure Légendaire", 5000, 1.5);

    private String id;
    public Texture sprite;
    public String nom;
    public Integer valeur;
    public double coeffProtection;

    private ArmureType(String id, String spriteName, String nom, Integer valeur, double coeffProtection){
        this.id = id;
        this.sprite = new Texture("items/"+spriteName+".png");
        this.nom = nom;
        this.valeur = valeur;
        this.coeffProtection = coeffProtection;
    }

    private static HashMap<String, ArmureType> armureTypes;

    static {
        armureTypes = new HashMap<String, ArmureType>();
        for (ArmureType armureType : ArmureType.values()){
            ArmureType.armureTypes.put(armureType.id, armureType);
        }
    }
}
