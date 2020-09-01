package com.esiho.world.entities.activables.Npcs;

import com.esiho.Game;
import com.esiho.ScreenLoader;
import com.esiho.world.entities.activables.Npc;

public class Mauricette extends Npc {
    @Override
    public void onUse() {
        Game.gameScreen.newConversation(conversation);
    }

    @Override
    public void onDeath() {

    }
}
