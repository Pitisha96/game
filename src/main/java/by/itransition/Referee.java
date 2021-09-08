package by.itransition;

import java.util.Arrays;
import java.util.List;

public class Referee {
    private List<String> rules;

    public Referee(String[] rules){
        this.rules = Arrays.asList(rules);
    }

    public String play(String compMove, String yourMove){
        int compIdx = rules.indexOf(compMove);
        int yourIdx = rules.indexOf(yourMove);
        int l,r;
        if(compIdx>yourIdx){
            l = compIdx-yourIdx;
            r = rules.size()-compIdx+yourIdx;
        }else if(compIdx<yourIdx){
            r = yourIdx-compIdx;
            l = compIdx+rules.size()-yourIdx;
        }else{
            return "DRAW";
        }
        if(l>r)
            return "LOSE";
        else
            return "WIN";

    }
}
