package com.esiho.combat.types;

import java.util.ArrayList;

public class Eau extends Type {
    public String nomType;

    public Eau() {
        nomType="EAU";
    }

    public String getNomType() {
        return nomType;
    }

    @Override
    public ArrayList getWeaknesses() {
        ArrayList<Type> weaknesses = new ArrayList<>();
        weaknesses.add(new Plante());
        weaknesses.add(new Eau());
        return weaknesses;
    }

    @Override
    public ArrayList getStrengths() {
        ArrayList<Type> strengths = new ArrayList<>();
        strengths.add(new Feu());
        return strengths;
    }

}
