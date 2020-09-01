package com.esiho.world.item;

import com.badlogic.gdx.graphics.Texture;
import com.esiho.combat.combattants.Combattant;
import com.esiho.combat.teams.Team;

public abstract class Item{

    public abstract Combattant useItemOnEntity(Combattant combattant);

    public abstract Team useItemOnTeam(Team team);

    public abstract String getNom();

    public abstract Texture getSprite();

    public abstract int getValeur();

    public static Item getItemFromID(String id){
        Item item = null;
        try {
            boolean fin = false;
            if (getArme(id)!=null){
                item=getArme(id);
                fin = true;
            }
//        test(item);
            if (!fin){
                if (getArmure(id)!=null){
                    item=getArmure(id);
                    fin = true;
                }
            }
//        test(item);
            if (!fin){
                if (getConsommable(id)!=null){
                    item=getConsommable(id);
                    fin = true;
                }
            }
//        test(item);
            if (!fin){
                if (getDivers(id)!=null){
                    item=getDivers(id);
                }
            }
//        test(item);
        }catch (Exception e){
            System.out.println(e);
        }

        return item;
    }

    private static void test(Item item){
        if (item.getNom()!=null){
            System.out.println(item.getNom());
        }
    }

    private static Item getArme(String id){
        return new Arme(ArmeType.getElement(id));
    }

    private static Item getArmure(String id){
        return new Armure(ArmureType.getElement(id));
    }

    private static Item getConsommable(String id){
        return new Consommable(ConsommableType.getElement(id));
    }

    private static Item getDivers(String id){
        return new Divers(DiversType.getElement(id));
    }
}
