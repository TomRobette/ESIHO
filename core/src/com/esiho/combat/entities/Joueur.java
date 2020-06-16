package com.esiho.combat.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esiho.combat.moves.MoveList;
import com.esiho.combat.types.Type;

public class Joueur extends CombatEntity {


    public void create(CombatEntityType entityType, int lvl, int xpmax, int xp, int pvmax, int pv, int att, int def, int attspe, int defspe, int vitesse, Arme arme, Armure armure, MoveList movesPhy, MoveList movesSpe) {
        super.create(entityType, lvl, xpmax, xp, pvmax, pv, att, def, attspe, defspe, vitesse, arme, armure, movesPhy, movesSpe);
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
