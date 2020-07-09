package com.esiho.combat;

import com.esiho.combat.entities.CombatEntity;
import com.esiho.combat.moves.MoveType;
import com.esiho.combat.teams.Team;
import com.esiho.combat.types.Type;
import com.esiho.world.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class CombatState {
    public Team team1;
    public Team team2;
    public CombatEntity entity1;
    public CombatEntity entity2;
    private Integer tour;
    private Boolean fin;
//    private ArrayList<Pnj> listeOrdrePnj;
//    private ArrayList<Pnj> pnjsALancer;
//    private ArrayList<MoveType> movesALancer;
//    private ArrayList<Pnj> pnjsARecevoir;
    private ArrayList<MoveType> ordreMoves;
    private ArrayList<CombatEntity> ordreLanceurs;
    private ArrayList<CombatEntity> ordreReceveurs;
    private Integer victoire;
//
    public CombatState(Team team1, Team team2){
        this.team1=team1;
        this.team2=team2;
        this.tour = 0;
        this.fin = false;
        this.entity1 = team1.getListeCbtEntities().get(0);
        this.entity2 = team2.getListeCbtEntities().get(0);
//        this.listeOrdrePnj = new ArrayList<>();
//        this.listeOrdrePnj.addAll(team1.getListeCbtEntities());
//        this.listeOrdrePnj.addAll(team2.getListeCbtEntities());
//        this.listeOrdrePnj = triVitesse();
//        this.pnjsALancer = new ArrayList<>();
//        this.movesALancer = new ArrayList<>();
//        this.pnjsARecevoir = new ArrayList<>();
        this.ordreMoves = new ArrayList<>();
        this.ordreLanceurs = new ArrayList<>();
        this.ordreReceveurs = new ArrayList<>();
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
//
//    private void newTourdeTable(){
//        this.listeOrdrePnj = triVitesse();
//        tour=0;
//        tourDeTable++;
//    }
//
    private void newTour(){
        this.ordreMoves = new ArrayList<>();
        this.ordreLanceurs = new ArrayList<>();
        this.ordreReceveurs = new ArrayList<>();
        this.tour++;
    }

    public void memorizeMove(MoveType move, CombatEntity user, CombatEntity cible){
        if (ordreMoves.size()<2){
            int index = 1;
            if (ordreMoves.isEmpty()){
                index = 0;
            }
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

            if (ordreLanceurs.get(0).getPv()>0) useMove(ordreMoves.get(0), ordreLanceurs.get(0), ordreReceveurs.get(0));
            if (ordreLanceurs.get(1).getPv()>0) useMove(ordreMoves.get(1), ordreLanceurs.get(1), ordreReceveurs.get(1));
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
        CombatEntity tempoLanceur = ordreLanceurs.get(0);
        CombatEntity tempoCible = ordreReceveurs.get(0);
        ordreMoves.set(0, ordreMoves.get(1));
        ordreLanceurs.set(0, ordreLanceurs.get(1));
        ordreReceveurs.set(0, ordreReceveurs.get(1));
        ordreMoves.set(1, tempoMove);
        ordreLanceurs.set(1, tempoLanceur);
        ordreReceveurs.set(1, tempoLanceur);
    }

    private void fin(Integer victoire){
        if (victoire==1){
            //Réussite !
            for (Item objet:team2.getInventaire()) {
                team1.getInventaire().add(objet);
            }//Pillage des objets de l'équipe adverse
            team1.addArgent(team2.getArgent()/2);//Pillage de la moitié de l'argent de l'équipe adverse
            int xpObtenu = 0;
            for (CombatEntity pnjEnnemi:team2.getListeCbtEntities()) {
                xpObtenu+=pnjEnnemi.getXp()*pnjEnnemi.getLvl();
            }//On récupère la somme d'xp de l'équipe adversaire
            int compteur = 0;
            for (CombatEntity pnjAllie:team1.getListeCbtEntities()){
                pnjAllie.addXp((Integer) xpObtenu/team1.getListeCbtEntities().size());
                team1.getListeCbtEntities().set(compteur, pnjAllie);
                compteur++;
            }//Ajout de l'xp sur chaque entité de l'équipe alliée
            this.victoire = 1;
        }else if (victoire==-1){
            //Défaite !
            this.victoire = -1;
        }
        fin=true;
    }

    private Integer analyseVictoire(){
        int pvAllie = 0;
        int pvEnnemis = 0;
        //Analyse des PV alliés
        for (CombatEntity allie:team1.getListeCbtEntities()) {
            pvAllie += allie.getPv();
        }
        //Analyse des PV ennemis
        for (CombatEntity ennemis:team2.getListeCbtEntities()) {
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

    private CombatEntity useMove(MoveType move, CombatEntity entityThrow, CombatEntity entityReceiver){
        CombatEntity entiteModifiee;
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

    private Double getModifier(MoveType move, CombatEntity lanceur, CombatEntity receveur){
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
