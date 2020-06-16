package com.esiho.combat.types;

import java.util.ArrayList;

public class Plante extends Type {
    public String nomType;

    public Plante() {
        nomType="PLANTE";
    }

    public String getNomType() {
        return nomType;
    }

    @Override
    public ArrayList getWeaknesses() {
        ArrayList<Type> weaknesses = new ArrayList<>();
        weaknesses.add(new Feu());
        weaknesses.add(new Plante());
        return weaknesses;
    }

    @Override
    public ArrayList getStrengths() {
        ArrayList<Type> strengths = new ArrayList<>();
        strengths.add(new Eau());
        return strengths;
    }

}
