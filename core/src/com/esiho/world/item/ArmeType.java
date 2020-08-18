package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public enum ArmeType {
    EPEELEGENDAIRE("I1", "epee_legendaire", "Épée légendaire", 1000, 4),
    BATON("I2", "baton", "Bâton", 100, 1.3),
    DAGUE("I3", "dague", "Dague", 250,1.5),
    ARC("I4", "arc", "Arc", 300, 1.7);

    private String id;
    public Texture sprite;
    public String nom;
    public Integer valeur;
    public double coeffDegats;

    private ArmeType(String id, String spriteName, String nom, Integer valeur, double coeffDegats){
        this.id = id;
        this.sprite = new Texture("items/"+spriteName+".png");
        this.nom = nom;
        this.valeur = valeur;
        this.coeffDegats = coeffDegats;
    }

    public static ArmeType getElement(String key){
        try{
            if (armeTypes.get(key).nom!=null){
                return armeTypes.get(key);
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    private static HashMap<String, ArmeType> armeTypes;

    static {
        armeTypes = new HashMap<String, ArmeType>();
        for (ArmeType armeType : ArmeType.values()){
            ArmeType.armeTypes.put(armeType.id, armeType);
        }
    }
}
