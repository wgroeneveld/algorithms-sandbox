package com.brainbaking.dictee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dictee {


    private final String invoer;
    private final String referentie;

    public Dictee(String invoer, String referentie) {
        this.invoer = invoer;
        this.referentie = referentie;
    }

    private Tuple zoekIndexInInvoer(int in, List<Tuple> tuples) {
        List<Tuple> laatstVoorkomendeTuple = tuples.stream().filter(t -> t.isChar(in)).collect(Collectors.toList());
        int index = !laatstVoorkomendeTuple.isEmpty() ? laatstVoorkomendeTuple.get(laatstVoorkomendeTuple.size() - 1).getIndex() + 1 : 0;

        int charTeZoeken = Character.isUpperCase(in) ? Character.toLowerCase(in) : Character.toUpperCase(in);
        int indexFromIndexHf = invoer.indexOf(charTeZoeken, index);
        int indexFromIndexGewoon = invoer.indexOf(in, index);

        if(indexFromIndexGewoon >= 0 && indexFromIndexHf >= 0 && indexFromIndexGewoon > indexFromIndexHf) {
            return Tuple.hoofdletter(in, indexFromIndexHf);
        } else if(indexFromIndexGewoon == -1 && indexFromIndexHf >= 0) {
            return Tuple.hoofdletter(in, indexFromIndexHf);
        } else if(indexFromIndexGewoon == -1) {
            return Tuple.nietGevonden(in);
        }

        return new Tuple(in, indexFromIndexGewoon);
    }

    public int verbeter() {
        List<Tuple> tuples = berekenTupleLijst();
        int score = 0;

        printStartDictee(tuples);

        for(int i = 0; i < tuples.size() - 1; i++) {
            Tuple curr = tuples.get(i);
            Tuple next = tuples.get(i + 1);

            if(curr.komtNietVoor()) {
                score += 2;
            } else if(curr.komtNietVoor() && next.komtNietVoor()) {
                score += 2 * 2;
            } else if(next.getIndex() - curr.getIndex() > 1) {
                score += 2;
            }

            score += curr.getExtraMinpunt();

            System.out.println("vgl " + i + " " + curr + " met " + (i + 1) + " " + next + " -- score: " + score);
        }

        score += verhoogScoreIndienBeginGemist(tuples, score);
        return score;
    }

    private int verhoogScoreIndienBeginGemist(List<Tuple> tuples, int score) {
        List<Integer> indexes = tuples.stream().mapToInt(t -> t.getIndex()).boxed().collect(Collectors.toList());
        if(!indexes.contains(0) && !indexes.contains(-1)) {
            return 2;
        }
        return 0;
    }

    private List<Tuple> berekenTupleLijst() {
        List<Tuple> tuples = new ArrayList<>();
        for(int refI = 0; refI < referentie.length(); refI++) {
            char ch = referentie.charAt(refI);
            tuples.add(zoekIndexInInvoer(ch, tuples));
        }
        return tuples;
    }

    private void printStartDictee(List<Tuple> tuples) {
        System.out.println("dictee voor ref: '" + referentie + "' tegen invoer: '" + invoer + "'");
        tuples.forEach(System.out::print);
        System.out.println();
    }
}
