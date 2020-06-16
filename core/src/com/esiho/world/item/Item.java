package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;

public abstract class Item{
    private String id;
    private String nom;
    private Integer valeur;
    private Texture texture;
    private String typeI;

    public Item(String id, String nom, Integer valeur, Texture texture) {
        this.id = id;
        this.nom = nom;
        this.valeur = valeur;
        this.texture = texture;
    }

    public Item(String id, String nom, Integer valeur) {
        this.id = id;
        this.nom = nom;
        this.valeur = valeur;
    }


    public abstract void useItem();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getTypeI() {
        return typeI;
    }

    public void setTypeI(String typeI) {
        this.typeI = typeI;
    }

    public static Arme epee(){
        Arme epee = new Arme("I3","Épée légendaire",500,5);
        return epee;
    }

    public static Arme baton(){
        Arme baton = new Arme("I1","Baton",100,1);
        return baton;
    }

    public static Arme dague(){
        Arme dague = new Arme("I2","dague",250,3);
        return dague;
    }


    public static Arme arc(){
        Arme arc = new Arme("I4","arc",300,  4);
        return arc;
    }

    public static Armure banale(){
        Armure banale = new Armure("I5", "Armure banale", 100, 2);
        return banale;
    }

    public static Armure legendaire(){
        Armure leg = new Armure("I6", "Armure légendaire", 500, 5);
        return leg;
    }

    public static Divers balle(){
        Divers balle = new Divers("I10", "balle", 200);
        return balle;
    }

    public static Divers queue(){
        Divers queue = new Divers("I9", "queue de rat", 50);
        return queue;
    }
}
