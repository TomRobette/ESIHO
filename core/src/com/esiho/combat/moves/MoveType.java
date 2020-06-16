package com.esiho.combat.moves;

import com.esiho.combat.types.Eau;
import com.esiho.combat.types.Feu;
import com.esiho.combat.types.Normal;
import com.esiho.combat.types.Plante;
import com.esiho.combat.types.Type;

import java.util.HashMap;

public enum MoveType {
    COUP_POING("A0", "Coup de poing", "PHYSIQUE", 40, new Normal(), 95),
    COUP_EPEE("A1", "Coup d'épée", "PHYSIQUE", 85, new Normal(), 85),
    LAME_HERBE("A2", "Lame d'herbe", "PHYSIQUE", 80, new Plante(), 90),
    LAME_FEU("A3", "Lame de feu", "PHYSIQUE", 90, new Feu(), 80),
    LAME_EAU("A4", "Lame d'eau", "PHYSIQUE", 85, new Eau(), 85),
    FEUILLES_LANCER("A5", "Feuilles à lancer", "SPECIAL", 80, new Plante(), 90),
    LANCE_FLAMME("A6", "Lance-Flammes", "SPECIAL", 90, new Feu(), 80),
    CANNON_EAU("A7", "Cannon à Eau", "SPECIAL", 85, new Eau(), 85),
    TIR_FLECHE("A8","Tir de flèche", "SPECIAL", 80, new Normal(), 85),
    FLECHE_EPINE("A9", "Flèche d'épines", "SPECIAL", 80, new Plante(), 90),
    FLECHE_EAU("A10", "Flèche d'eau", "SPECIAL", 90, new Normal(), 80),
    FLECHE_FEU("A11", "Flèche de feu", "SPECIAL", 85, new Normal(), 85);

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


    private static HashMap<String, MoveType> moveType;

    static {
        moveType = new HashMap<String, MoveType>();
        for (MoveType moveType : MoveType.values()){
            MoveType.moveType.put(moveType.id, moveType);
        }
    }
}
