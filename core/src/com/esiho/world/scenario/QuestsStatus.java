package com.esiho.world.scenario;

import com.esiho.world.item.Arme;
import com.esiho.world.item.ArmeType;
import com.esiho.world.item.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestsStatus {
    public static boolean killSlime = false;
    public static boolean talkToCat = false;
    public static boolean talkToMauricette = false;

    public static ArrayList<Quest> questsList;

    public QuestsStatus(){
        questsList = new ArrayList<>();
        questsList.add(new Quest("Le meurtre d'un slime", "Tuer le slime devant la cabane de la plaine.", 15, new ArrayList<>(Arrays.asList(new Arme(ArmeType.DAGUE)))) {
            @Override
            public void howToComplete() {
                if (QuestsStatus.killSlime)
                    this.status=true;
            }
        });
        questsList.add(new Quest("Le Chat", "Parler au chat devant la cabane dans la plaine", 5, null) {
            @Override
            public void howToComplete() {
                if (QuestsStatus.talkToCat)
                    this.status=true;
            }
        });
        questsList.add(new Quest("Mauricette", "Parler à Mauricette dans la cabane qui est dans la plaine", 10, null) {
            @Override
            public void howToComplete() {
                if (QuestsStatus.talkToMauricette)
                    this.status=true;
            }
        });
    }

    public void refreshQuestsStatus(){
        for (Quest quest:questsList){
            quest.refreshStatus();
        }
    }
}
