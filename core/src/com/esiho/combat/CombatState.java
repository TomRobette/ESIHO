package com.esiho.combat;

import com.esiho.combat.entities.CombatEntity;
import com.esiho.combat.moves.MoveType;
import com.esiho.combat.teams.Team;
import com.esiho.combat.types.Type;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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
    private Integer victoire;
//
    public CombatState(Team team1, Team team2){
        this.team1=team1;
        this.team2=team2;
        this.tour = 0;
        this.fin = false;
//        this.listeOrdrePnj = new ArrayList<>();
//        this.listeOrdrePnj.addAll(team1.getListeCbtEntities());
//        this.listeOrdrePnj.addAll(team2.getListeCbtEntities());
//        this.listeOrdrePnj = triVitesse();
//        this.pnjsALancer = new ArrayList<>();
//        this.movesALancer = new ArrayList<>();
//        this.pnjsARecevoir = new ArrayList<>();
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
//    private void newTour(){
//        this.pnjsALancer = new ArrayList<>();
//        this.movesALancer = new ArrayList<>();
//        this.pnjsARecevoir = new ArrayList<>();
//        this.tour++;
//    }
//
//    public Integer tour(){
//        Pnj pnjPlay = listeOrdrePnj.get(tour);
//        Boolean isPnjAllie = false;
//        for (Pnj pnj: pnjsALancer){
//            if (pnj.getId()==pnjPlay.getId()){
//                isPnjAllie=true;
//            }
//        }
//        if (pnjPlay.getEntite().getPV().getlvlpv()!=0){
//            if (isPnjAllie){
//                Integer rang = pnjsALancer.indexOf(pnjPlay);
//                Integer compteur = 0;
//                for (Pnj pnj:team2.getListeCbtEntities()) {
//                    if (pnj.equals(pnjsARecevoir.get(rang))){
//                        team2.getListeCbtEntities().get(compteur).setEntite(useMove(movesALancer.get(rang), pnjsALancer.get(rang).getEntite(), pnjsARecevoir.get(rang).getEntite()));
//                    }
//                    compteur++;
//                }
//            }else{
//                Integer genre = ThreadLocalRandom.current().nextInt(0, 1);
//                MoveList moveList;
//                if (genre == 0){
//                    moveList = pnjPlay.getEntite().getMovesPhy();
//                }else{
//                    moveList = pnjPlay.getEntite().getMovesSpe();
//                }
//                Move move;
//                if (moveList.getSize()!=0){
//                    Integer movePtr = ThreadLocalRandom.current().nextInt(0, moveList.getSize());
//                    move = moveList.getMove(movePtr);
//                }else{
//                    move = Move.coupPoing();
//                }
//                ArrayList<Pnj> cibles = new ArrayList<>();
//                for (Pnj pnj:team1.getListeCbtEntities()){
//                    if (pnj.getEntite().getPV().getlvlpv()>0){
//                        cibles.add(pnj);
//                    }
//                }
//                if (cibles.size()!=0){
//                    Integer ciblePtr = ThreadLocalRandom.current().nextInt(0, cibles.size());
//                    Pnj cible = cibles.get(ciblePtr);
//                    Integer compteur = 0;
//                    for (Pnj pnj:team1.getListeCbtEntities()) {
//                        if (pnj.equals(cible)){
//                            team1.getListeCbtEntities().get(compteur).setEntite(useMove(move, pnjPlay.getEntite(), cible.getEntite()));
//                        }
//                        compteur++;
//                    }
//                }
//            }
//        }
//        Integer victoire = analyseVictoire(team1, team2);
//        if (victoire!=0){
//            this.fin = true;
//            fin(victoire);
//        }
//
//        newTour();
//        Double reste = 0.0;
//        if (listeOrdrePnj.size()!=0){
//            reste = new Double(this.tour%listeOrdrePnj.size());
//        }
//        if (reste == 0){
//            newTourdeTable();
//        }
//        return victoire;
//    }
//
//    public void selectMove(Pnj pnjThrower, Move moveSelected, Pnj pnjReceiver){
//        this.pnjsALancer.add(pnjThrower);
//        this.movesALancer.add(moveSelected);
//        this.pnjsARecevoir.add(pnjReceiver);
//    }
//
//    private ArrayList<Pnj> triVitesse(){
//        ArrayList<Pnj> listePnjsVitesse;
//        if (this.listeOrdrePnj.size()==0){
//            listePnjsVitesse = new ArrayList<>();
//            for (Pnj pnjAjoute:team1.getListeCbtEntities()) {
//                listePnjsVitesse.add(pnjAjoute);
//            }
//            for (Pnj pnjAjoute:team2.getListeCbtEntities()) {
//                listePnjsVitesse.add(pnjAjoute);
//            }
//        }else{
//            listePnjsVitesse = this.listeOrdrePnj;
//        }
//
//
//        Pnj pnjComparant = listePnjsVitesse.get(0);
//        Integer compteurCompare = 0;
//        Integer old = 0;
//        for (int a = 1; a<listePnjsVitesse.size(); a++) {
//            Pnj pnjCompare= listePnjsVitesse.get(a);
//            if (pnjCompare.getEntite().getVitesse() > pnjComparant.getEntite().getVitesse()){
//                listePnjsVitesse.set(compteurCompare, pnjCompare);
//                listePnjsVitesse.set(old, pnjComparant);
//                pnjComparant = pnjCompare;
//                old = compteurCompare;
//            }
//
//            compteurCompare++;
//        }//On trie les PNJs dans l'ordre de leur attaques pour savoir qui est plus ou moins rapide
//
//        return listePnjsVitesse;
//    }
//
//    private void fin(Integer victoire){
//        if (victoire==1){
//            //Réussite !
//            for (Item objet:team2.getInventaire().getInventaire()) {
//                team1.getInventaire().addItem(objet);
//            }//Pillage des objets de l'équipe adverse
//            team1.addArgent(team2.getArgent()/2);//Pillage de la moitié de l'argent de l'équipe adverse
//            Integer xpObtenu = 0;
//            for (Pnj pnjEnnemis:team2.getListeCbtEntities()) {
//                xpObtenu+=pnjEnnemis.getEntite().getXp();
//            }//On récupère la somme d'xp de l'équipe adversaire
//            Integer compteur = 0;
//            for (Pnj pnjAllie:team1.getListeCbtEntities()){
//                pnjAllie.getEntite().addXp((Integer) xpObtenu/team1.getListeCbtEntities().size());
//                team1.getListeCbtEntities().set(compteur, pnjAllie);
//                compteur++;
//            }//Ajout de l'xp sur chaque entité de l'équipe alliée
//            fin=true;
//            this.victoire = 1;
//        }else if (victoire==-1){
//            //Défaite !
//            fin=true;
//            this.victoire = -1;
//        }
//    }
//
//    private Integer analyseVictoire(Team team1, Team team2){
//        Integer victoire = 0;
//        Integer pvAllie = 0;
//        Integer pvEnnemis = 0;
//        //Analyse des PV alliés
//        for (Pnj allie:team1.getListeCbtEntities()) {
//            pvAllie += allie.getEntite().getPV().getPv();
//        }
//        //Analyse des PV ennemis
//        for (Pnj ennemis:team2.getListeCbtEntities()) {
//            pvEnnemis += ennemis.getEntite().getPV().getPv();
//        }
//        if (pvAllie>0 && pvEnnemis>0){
//            victoire=0;
//        }else if (pvAllie>0 && pvEnnemis==0){
//            victoire=1;
//        }else if (pvAllie==0 && pvEnnemis>0){
//            victoire=-1;
//        }else{
//            victoire=0;
//        }
//        return victoire;
//    }
//
////    public void testMort(){
////        if (analyseVictoire(team1,team2)==-1){
////            launcher.ecranMortRespawn();
////        }
////    }
//
//    private Entity useMove(Move move, Entity entityThrow, Entity entityReceiver){
//        Entity entiteModifiee;
//        String genre=move.getGenre();
//        if (genre=="SPECIAL"){
//            double dommages=2+(2*entityThrow.getLvl())/5;
//            dommages*=move.getPuissance();
//            dommages*=entityThrow.getAttspe()/entityReceiver.getDefspe();
//            dommages/=50;
//            dommages+=2;
//            dommages*=getModifier(move, entityThrow, entityReceiver);//Faire une méthode analysant les types alliés et ennemis
//            entityReceiver.getPV().degatsPVabs((int)dommages);
//            entiteModifiee = entityReceiver;
//        }else if (genre=="PHYSIQUE"){
//            double dommages=2+(2*entityThrow.getLvl())/5;
//            dommages*=move.getPuissance();
//            dommages*=entityThrow.getAtt()/entityReceiver.getDef();
//            dommages/=50;
//            dommages+=2;
//            dommages*=getModifier(move, entityThrow, entityReceiver);//Faire une méthode analysant les types alliés et ennemis
//            entityReceiver.getPV().degatsPVabs((int)dommages);
//            entiteModifiee = entityReceiver;
//        }else{
//            entiteModifiee = entityReceiver;
//        }
//        return entiteModifiee;
//    }
//
    private Double getModifier(MoveType move, CombatEntity entityThrow, CombatEntity entityReceiver){
        double modifier = 1.0;
        Type moveType = move.getType();
        Type typeReceiver = entityReceiver.getType();
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

        Type typeThrower = entityThrow.getType();
        if (typeThrower.equals(moveType)){
            modifier*=1.5;//STAB
        }

        if (entityThrow.getArme()!=null){
            modifier*=entityThrow.getArme().getCoeffDegats();
        }//Coefficient de l'arme appliqué

        if(entityReceiver.getArmure()!=null){
            modifier*=entityReceiver.getArmure().getCoeffProtection();
        }//Coefficient de l'arme appliqué

        return modifier;
    }
//
//    private void timer(Integer duree){
//        try{
//            TimeUnit.MILLISECONDS.sleep(duree);
//        }catch(InterruptedException ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public Integer getTour() {
//        return tour;
//    }
//
//    public Boolean getFin() {
//        return fin;
//    }
//
//    public Integer getTourDeTable() {
//        return tourDeTable;
//    }
//
//    public Integer getVictoire() {
//        return victoire;
//    }
}
