package com.esiho.combat.moves;

import com.esiho.combat.types.Eau;
import com.esiho.combat.types.Feu;
import com.esiho.combat.types.Normal;
import com.esiho.combat.types.Plante;
import com.esiho.combat.types.Type;

import java.util.HashMap;

public enum MoveType {
    NULL("A0", "null", "CHAOS", 0, new Normal(), 0),
    COUP_POING("A1", "Coup de poing", "PHYSIQUE", 40, new Normal(), 95),
    COUP_EPEE("A2", "Coup d'épée", "PHYSIQUE", 85, new Normal(), 85),
    LAME_HERBE("A3", "Lame d'herbe", "PHYSIQUE", 80, new Plante(), 90),
    LAME_FEU("A4", "Lame de feu", "PHYSIQUE", 90, new Feu(), 80),
    LAME_EAU("A5", "Lame d'eau", "PHYSIQUE", 85, new Eau(), 85),
    FEUILLES_LANCER("A6", "Feuilles à lancer", "SPECIAL", 80, new Plante(), 90),
    LANCE_FLAMME("A7", "Lance-Flammes", "SPECIAL", 90, new Feu(), 80),
    CANNON_EAU("A8", "Cannon à Eau", "SPECIAL", 85, new Eau(), 85),
    TIR_FLECHE("A9","Tir de flèche", "SPECIAL", 80, new Normal(), 85),
    FLECHE_EPINE("A10", "Flèche d'épines", "SPECIAL", 80, new Plante(), 90),
    FLECHE_EAU("A11", "Flèche d'eau", "SPECIAL", 90, new Normal(), 80),
    FLECHE_FEU("A12", "Flèche de feu", "SPECIAL", 85, new Normal(), 85);

    protected String id;
    protected String nom;
    protected String genre;
    protected Integer puissance;
    protected Type type;
    protected Integer precision;

    private MoveType(String id, String nom, String genre, Integer puissance, Type type, Integer precision) {
        this.id=id;
        this.nom=nom;
        this.genre=genre;
        this.puissance = puissance;
        this.type = type;
        this.precision=precision;
    }

    private MoveType(String id, String nom, String genre, Type type, Integer precision){
        this.id=id;
        this.nom=nom;
        this.genre=genre;
        this.type = type;
        this.precision=precision;
    }

    private MoveType(String id, String nom, String genre, Type type){
        this.id=id;
        this.nom=nom;
        this.genre=genre;
        this.type = type;
    }


    private static HashMap<String, MoveType> moveTypes;

    static {
        moveTypes = new HashMap<String, MoveType>();
        for (MoveType moveType : MoveType.values()){
            MoveType.moveTypes.put(moveType.id, moveType);
        }
    }

    public Type getType() {
        return type;
    }

    public Integer getPuissance() {
        return puissance;
    }

    public Integer getPrecision() {
        return precision;
    }

    public String getGenre() {
        return genre;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
