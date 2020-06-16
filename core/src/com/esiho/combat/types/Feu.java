package com.esiho.combat.types;

import java.util.ArrayList;

public class Feu extends Type {
    public String nomType;

    public Feu() {
        nomType="FEU";
    }

    public String getNomType() {
        return nomType;
    }

    @Override
    public ArrayList getWeaknesses() {
        ArrayList<Type> weaknesses = new ArrayList<>();
        weaknesses.add(new Eau());
        weaknesses.add(new Feu());
        return weaknesses;
    }

    @Override
    public ArrayList getStrengths() {
        ArrayList<Type> strengths = new ArrayList<>();
        strengths.add(new Plante());
        return strengths;
    }
}
