package com.esiho.world.scenario;

import com.esiho.world.item.Item;

import java.util.ArrayList;

public abstract class Quest {
    private String nom;
    private boolean status;
    private int sommeVictoire;
    private ArrayList<Item> cadeauxVictoire;
}
