package com.esiho.combat;

import com.esiho.Game;
import com.esiho.combat.entities.Combattant;
import com.esiho.combat.moves.MoveType;
import com.esiho.combat.teams.Team;
import com.esiho.combat.teams.TeamType;
import com.esiho.combat.types.Type;
import com.esiho.world.entities.Entity;
import com.esiho.world.item.Item;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CombatState {
    private Entity entityToKill;
    public Team team1;
    public Team team2;
    public Combattant entity1;
    public Combattant entity2;
    private Integer tour;
    private Boolean fin;
    private ArrayList<MoveType> ordreMoves;
    private ArrayList<Combattant> ordreLanceurs;
    private ArrayList<Combattant> ordreReceveurs;
    private Integer victoire;

    public String messageFin;
    public ArrayList<Item> itemsObtenus;
    public int orObtenu;
    public ArrayList<Integer> listeXP;

    public CombatState(Team team1, Team team2){
        this.team1=team1;
        this.team2=team2;
        this.tour = 0;
        this.fin = false;
        this.entity1 = team1.getListeCbtEntities().get(0);
        this.entity2 = team2.getListeCbtEntities().get(0);
        this.ordreMoves = new ArrayList<>();
        this.ordreLanceurs = new ArrayList<>();
        this.ordreReceveurs = new ArrayList<>();
        this.itemsObtenus = new ArrayList<>();
        this.listeXP = new ArrayList<>();
        victoire=0;
    }

    public CombatState(Team team1, Team team2, Entity entityToKill){
        this.entityToKill = entityToKill;
        this.team1=team1;
        this.team2=team2;
        this.tour = 0;
        this.fin = false;
        this.entity1 = team1.getListeCbtEntities().get(0);
        this.entity2 = team2.getListeCbtEntities().get(0);
        this.ordreMoves = new ArrayList<>();
        this.ordreLanceurs = new ArrayList<>();
        this.ordreReceveurs = new ArrayList<>();
        this.itemsObtenus = new ArrayList<>();
        this.listeXP = new ArrayList<>();
        victoire=0;
    }

    public Boolean selectEntity1(int pointer){
        if (team1.getListeCbtEntities().get(pointer).getPv()>0){
            this.entity1=team1.getListeCbtEntities().get(pointer);
            return true;
        }else{
            return false;
        }
    }

    public Boolean selectEntity2(int pointer){
        if (team2.getListeCbtEntities().get(pointer).getPv()>0){
            this.entity2=team2.getListeCbtEntities().get(pointer);
            return true;
        }else{
            return false;
        }
    }

    private void newTour(){
        this.ordreMoves = new ArrayList<>();
        this.ordreLanceurs = new ArrayList<>();
        this.ordreReceveurs = new ArrayList<>();
        if (entity2.getPv()<=0) changeEntityOnDeathAdv();
        this.tour++;
    }

    public void memorizeMove(MoveType move, Combattant user, Combattant cible){
        if (ordreMoves.size()<2){
            ordreMoves.add(move);
            ordreLanceurs.add(user);
            ordreReceveurs.add(cible);
        }
    }

    public Boolean tour(){
        if (ordreMoves.size()==2){
            if (ordreLanceurs.get(0).getSpeed()>ordreLanceurs.get(1).getSpeed()){
                //C'est déjà dans le bon ordre
            }else if (ordreLanceurs.get(0).getSpeed()<ordreLanceurs.get(1).getSpeed()){
                inversionOrdre();
            }else{
                int random = ThreadLocalRandom.current().nextInt(0, 1);
                if(random==1) inversionOrdre();
            }

            if (ordreLanceurs.get(0).getPv()>0) entity2 = useMove(ordreMoves.get(0), ordreLanceurs.get(0), ordreReceveurs.get(0));
            if (ordreLanceurs.get(1).getPv()>0) entity1 = useMove(ordreMoves.get(1), ordreLanceurs.get(1), ordreReceveurs.get(1));
            Integer victoire = analyseVictoire();
            if (victoire!=0){
                fin(victoire);
            }else{
                newTour();
            }
            return true;//Si le tour a fonctionné
        }
        return false;//Si le tour n'a pas été lancé
    }

    public void inversionOrdre(){
        MoveType tempoMove = ordreMoves.get(0);
        Combattant tempoLanceur = ordreLanceurs.get(0);
        Combattant tempoCible = ordreReceveurs.get(0);
        ordreMoves.set(0, ordreMoves.get(1));
        ordreLanceurs.set(0, ordreLanceurs.get(1));
        ordreReceveurs.set(0, ordreReceveurs.get(1));
        ordreMoves.set(1, tempoMove);
        ordreLanceurs.set(1, tempoLanceur);
        ordreReceveurs.set(1, tempoLanceur);
    }

    private void fin(Integer victoire){
        if (victoire==1){
            if (Game.debug) System.out.println("VICTOIRE !");
            this.messageFin = "Victoire !";
            //Réussite !
            if (team2.getInventaire()!=null){
                if (team2.getInventaire().size()!=0){
                    for (Item objet:team2.getInventaire()) {
                        team1.getInventaire().add(objet);
                    }//Pillage des objets de l'équipe adverse
                }
                this.itemsObtenus = team2.getInventaire();
            }
            team1.addArgent(Math.round(team2.getArgent()/2));//Pillage de la moitié de l'argent de l'équipe adverse
            this.orObtenu = Math.round(team2.getArgent()/2);
            int xpObtenu = 0;
            for (Combattant pnjEnnemi:team2.getListeCbtEntities()) {
                xpObtenu+=pnjEnnemi.getXp()*pnjEnnemi.getLvl();
            }//On récupère la somme d'xp de l'équipe adversaire
            int compteur = 0;
            for (Combattant pnjAllie:team1.getListeCbtEntities()){
                pnjAllie.addXp(Math.round(xpObtenu/team1.getListeCbtEntities().size()));
                this.listeXP.add(Math.round(xpObtenu/team1.getListeCbtEntities().size()));
                team1.getListeCbtEntities().set(compteur, pnjAllie);
                compteur++;
            }//Ajout de l'xp sur chaque entité de l'équipe alliée
            this.victoire = 1;
            if (this.entityToKill!=null)
                Game.killEntityOnActiveMap(this.entityToKill);
        }else if (victoire==-1){
            if (Game.debug) System.out.println("DÉFAITE !");
            this.messageFin = "Défaite...";
            //Défaite !
            this.victoire = -1;
        }
        TeamType.JOUEUR.listeCbtEntities = team1.getListeCbtEntities();
        TeamType.JOUEUR.inventaire = team1.getInventaire();
        TeamType.JOUEUR.argent = team1.getArgent();
        fin=true;
        Game.finCbtState = this;
        Game.finCbt = true;
    }

    private Integer analyseVictoire(){
        int pvAllie = 0;
        int pvEnnemis = 0;
        //Analyse des PV alliés
        for (Combattant allie:team1.getListeCbtEntities()) {
            pvAllie += allie.getPv();
        }
        //Analyse des PV ennemis
        for (Combattant ennemis:team2.getListeCbtEntities()) {
            pvEnnemis += ennemis.getPv();
        }
        if (pvAllie>0 && pvEnnemis>0){
            victoire=0;
        }else if (pvAllie>0 && pvEnnemis==0){
            victoire=1;
        }else if (pvAllie==0 && pvEnnemis>0){
            victoire=-1;
        }else{
            victoire=0;
        }
        return victoire;
    }

//    public void testMort(){
//        if (analyseVictoire()==-1){
//            launcher.ecranMortRespawn();
//        }
//    }

    private Combattant useMove(MoveType move, Combattant entityThrow, Combattant entityReceiver){
        Combattant entiteModifiee;
        String genre=move.getGenre();
        if (genre=="SPECIAL"){
            double dommages=2+(2*entityThrow.getLvl())/5;
            dommages*=move.getPuissance();
            dommages*=entityThrow.getAtkSpe()/entityReceiver.getDefSpe();
            dommages/=50;
            dommages+=2;
            dommages*=getModifier(move, entityThrow, entityReceiver);//Faire une méthode analysant les types alliés et ennemis
            entityReceiver.degatsPVabs((int)dommages);
            entiteModifiee = entityReceiver;
        }else if (genre=="PHYSIQUE"){
            double dommages=2+(2*entityThrow.getLvl())/5;
            dommages*=move.getPuissance();
            dommages*=entityThrow.getAtk()/entityReceiver.getDef();
            dommages/=50;
            dommages+=2;
            dommages*=getModifier(move, entityThrow, entityReceiver);//Faire une méthode analysant les types alliés et ennemis
            entityReceiver.degatsPVabs((int)dommages);
            entiteModifiee = entityReceiver;
        }else{
            entiteModifiee = entityReceiver;
        }
        return entiteModifiee;
    }

    private Double getModifier(MoveType move, Combattant lanceur, Combattant receveur){
        double modifier = 1.0;
        Type moveType = move.getType();
        Type typeReceiver = receveur.getType();
        ArrayList<Type> faiblesses = moveType.getWeaknesses();

        for (Type typeCompare:faiblesses) {
            if (typeCompare.getNomType().equals(typeReceiver.getNomType())){
                modifier = 0.5;//PAS TRÈS EFFICACE !
            }
        }
        ArrayList<Type> forces = moveType.getStrengths();

        for (Type typeCompare:forces) {
            if (typeCompare.getNomType().equals(typeReceiver.getNomType())){
                modifier = 2;//SUPER EFFICACE
            }
        }

        Type typeThrower = lanceur.getType();
        if (typeThrower.equals(moveType)){
            modifier*=1.5;//STAB
        }

        if (lanceur.getArme()!=null){
            modifier*=lanceur.getArme().getCoeffDegats();
        }//Coefficient de l'arme appliqué

        if(receveur.getArmure()!=null){
            modifier*=receveur.getArmure().getCoeffProtection();
        }//Coefficient de l'arme appliqué

        return modifier;
    }

    private void changeEntityOnDeathAdv(){
        if (team2.getListeCbtEntities().size()>1){
            ArrayList<Combattant> notKOEntities = new ArrayList<>();
            for (Combattant entity:team2.getListeCbtEntities()) {
                if (entity.getPv()>0) notKOEntities.add(entity);
            }
            int random = ThreadLocalRandom.current().nextInt(0, notKOEntities.size());
            entity2 = notKOEntities.get(random);
        }
    }

    public Integer getTour() {
        return tour;
    }

    public Boolean getFin() {
        return fin;
    }

    public Integer getVictoire() {
        return victoire;
    }
}
