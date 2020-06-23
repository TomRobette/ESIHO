package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public enum ArmeType {
    EPEELEGENDAIRE("I1", "epee_legendaire", "Épée légendaire", 1000, 10);

    private String id;
    public Texture sprite;
    public String nom;
    public Integer valeur;
    private Integer coeffDegats;

    private ArmeType(String id, String spriteName, String nom, Integer valeur, Integer coeffDegats){
        this.id = id;
        this.sprite = new Texture("items/"+spriteName+".png");
        this.nom = nom;
        this.valeur = valeur;
        this.coeffDegats = coeffDegats;
    }

    private static HashMap<String, ArmeType> armeTypes;

    static {
        armeTypes = new HashMap<String, ArmeType>();
        for (ArmeType armeType : ArmeType.values()){
            ArmeType.armeTypes.put(armeType.id, armeType);
        }
    }
}
