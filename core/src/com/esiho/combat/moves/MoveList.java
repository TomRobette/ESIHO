package com.esiho.combat.moves;

import java.util.ArrayList;

public class MoveList {
    private ArrayList<MoveType> movelist;

    public MoveList(MoveType move1) {
        movelist = new ArrayList<>();
        movelist.add(move1);
    }

    public MoveList(MoveType move1, MoveType move2) {
        movelist = new ArrayList<>();
        movelist.add(move1);
        movelist.add(move2);
    }

    public MoveList(MoveType move1, MoveType move2, MoveType move3) {
        movelist = new ArrayList<>();
        movelist.add(move1);
        movelist.add(move2);
        movelist.add(move3);
    }

    public MoveList(MoveType move1, MoveType move2, MoveType move3, MoveType move4) {
        movelist = new ArrayList<>();
        movelist.add(move1);
        movelist.add(move2);
        movelist.add(move3);
        movelist.add(move4);
    }

    public MoveType get(Integer position) {
        MoveType move;
        if (movelist.size()-1>=position && movelist.get(position)!=null){
            move = movelist.get(position);
        }else{
            move = null;
        }
        return move;
    }

    public int getSize(){
        return  movelist.size();
    }
    public ArrayList<MoveType> getMovelist() {
        return movelist;
    }
}
