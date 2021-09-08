package by.itransition;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a7.A7_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilTable {
    private final String[] moves;
    private final Referee referee;
    private String table;
    private String menu;

    public UtilTable(String[] moves,Referee referee){
        this.moves = moves;
        this.referee=referee;
        drawTable();
        drawMenu();
    }

    public String getTable(){
        return table;
    }

    public String getMenu(){
        return menu;
    }

    private void drawTable(){
        AsciiTable at = new AsciiTable();
        at.getContext().setGrid(A7_Grids.minusBarPlusEquals());
        at.addRule();
        List<String> row= new ArrayList<>();
        row.addAll(Arrays.asList(moves));
        row.add(0,"PC\\User");
        at.addRow(row).setPaddingLeftRight(1);
        for(String rowMove:moves){
            row.clear();
            at.addRule();
            for(String colMove:moves){
                row.add(referee.play(colMove,rowMove));
            }
            row.add(0,rowMove);
            at.addRow(row);
        }
        at.setTextAlignment(TextAlignment.CENTER);
        table = at.render();
    }

    private void drawMenu(){
        StringBuilder sb=new StringBuilder();
        sb.append("\nAvailable moves:\n");
        for(int i=0;i< moves.length;i++){
            sb.append(String.format("%d - %s\n",i+1,moves[i]));
        }
        sb.append("0 - exit\n");
        sb.append("? - help\n");
        menu = sb.toString();
    }
}
