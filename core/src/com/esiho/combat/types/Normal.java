package com.esiho.combat.types;

import java.util.ArrayList;

public class Normal extends Type {
        public String nomType;

        public Normal() {
            nomType="NORMAL";
        }

        public String getNomType() {
            return nomType;
        }

        @Override
        public ArrayList getWeaknesses() {
            ArrayList<Type> weaknesses = new ArrayList<>();
            return weaknesses;
        }

        @Override
        public ArrayList getStrengths() {
            ArrayList<Type> strengths = new ArrayList<>();
            return strengths;
        }
}
