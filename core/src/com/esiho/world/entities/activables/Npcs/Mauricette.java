package com.esiho.world.entities.activables.Npcs;

import com.esiho.Game;
import com.esiho.ScreenLoader;
import com.esiho.world.entities.activables.Npc;
import com.esiho.world.scenario.QuestsStatus;

public class Mauricette extends Npc {
    @Override
    public void onUse() {
        Game.gameScreen.newConversation(conversation);
        QuestsStatus.talkToMauricette=true;
    }

    @Override
    public void onDeath() {

    }
}
