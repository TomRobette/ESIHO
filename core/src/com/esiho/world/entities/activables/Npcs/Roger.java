package com.esiho.world.entities.activables.Npcs;

import com.esiho.ScreenLoader;
import com.esiho.world.entities.activables.Npc;

public class Roger extends Npc {
    @Override
    public void onUse() {
        ScreenLoader.team();
        ScreenLoader.game.cbtScreen(ScreenLoader.cbt);
    }

    @Override
    public void onDeath() {

    }
}
