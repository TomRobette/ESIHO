package com.esiho.world.entities.activables.Npcs;

import com.esiho.Game;
import com.esiho.world.entities.activables.Npc;
import com.esiho.world.scenario.QuestsStatus;

public class Chat extends Npc {
    @Override
    public void onUse() {
        Game.gameScreen.newConversation(conversation);
        QuestsStatus.talkToCat=true;
    }

    @Override
    public void onDeath() {

    }


}
