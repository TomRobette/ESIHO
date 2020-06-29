package com.esiho.combat.entities;

import com.badlogic.gdx.graphics.Texture;
import com.esiho.combat.moves.MoveList;
import com.esiho.combat.moves.MoveType;
import com.esiho.combat.types.Eau;
import com.esiho.combat.types.Type;
import com.esiho.world.item.Arme;
import com.esiho.world.item.ArmeType;
import com.esiho.world.item.Armure;
import com.esiho.world.item.ArmureType;

import java.util.HashMap;

public enum CombatEntityType {
    JOUEUR("C1", new Eau(), "Swordman", 5, 300, 0, 200, 200, 150, 100, 50, 100, 100, new Arme(ArmeType.EPEELEGENDAIRE), new Armure(ArmureType.ARMURE_BANALE), new MoveList(MoveType.COUP_EPEE, MoveType.LAME_EAU), new MoveList(MoveType.FLECHE_EAU));

    private String id;
    protected Type type;
    private Texture sprite;
    protected int lvl;
    protected int xpmax;
    protected int xp;
    protected int pvmax;
    protected int pv;
    protected int att;
    protected int def;
    protected int attspe;
    protected int defspe;
    protected int vitesse;
    protected Arme arme;
    protected Armure armure;
    protected MoveList movesPhy;
    protected MoveList movesSpe;

    private CombatEntityType(String id, Type type, String spriteName, int lvl, int xpmax, int xp, int pvmax, int pv, int att, int def, int attspe, int defspe, int vitesse, Arme arme, Armure armure, MoveList movesPhy, MoveList movesSpe){
        this.id = id;
        this.type = type;
        this.sprite = new Texture("combat/"+spriteName+".png");
        this.lvl = lvl;
        this.xpmax = xpmax;
        this.xp = xp;
        this.pvmax = pvmax;
        this.pv = pv;
        this.att = att;
        this.def = def;
        this.attspe = attspe;
        this.defspe = defspe;
        this.vitesse = vitesse;
        this.arme = arme;
        this.armure = armure;
        this.movesPhy = movesPhy;
        this.movesSpe = movesSpe;
    }


    private static HashMap<String, CombatEntityType> cbtEntityTypes;

    static {
        cbtEntityTypes = new HashMap<String, CombatEntityType>();
        for (CombatEntityType cbttype : CombatEntityType.values()){
            cbtEntityTypes.put(cbttype.id, cbttype);
        }
    }

    public Texture getSprite() {
        return sprite;
    }
}
