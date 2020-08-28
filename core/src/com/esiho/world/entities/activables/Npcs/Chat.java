package com.esiho.world.entities.activables.Npcs;

import com.esiho.Game;
import com.esiho.world.entities.activables.Npc;

public class Chat extends Npc {
    @Override
    public void onUse() {
        Game.gameScreen.newConversation(conversation);
    }
}
