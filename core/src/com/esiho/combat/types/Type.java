package com.esiho.combat.types;

import java.util.ArrayList;

public abstract class Type {
    public String nomType;

    public Type() {
        this.nomType = nomType;
    }

    public String getNomType() {
        return nomType;
    }

    public abstract ArrayList getWeaknesses();

    public abstract ArrayList getStrengths();
}
