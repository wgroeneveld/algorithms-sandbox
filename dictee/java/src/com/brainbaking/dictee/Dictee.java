package com.brainbaking.dictee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Dictee {

    private final String invoer;
    private final String referentie;

    public Dictee(String invoer, String referentie) {
        this.invoer = invoer;
        this.referentie = referentie;

        System.out.println("Dictee: invoer     \t" + invoer);
        System.out.println("Dictee: referentie \t" + referentie);
    }

    private List<Character> getInvoerChars() {
        return invoer.chars().mapToObj(e->((char)e)).collect(Collectors.toList());
    }

    private List<Character> getReferentie() {
        return referentie.chars().mapToObj(e->((char)e)).collect(Collectors.toList());
    }

    public int verbeter() {
        List<Character> in = getInvoerChars();
        List<Character> inLower = in.stream().map(Character::toLowerCase).collect(Collectors.toList());
        List<Diff> diffs = new ArrayList<Diff>();

        for(int i = 0; i < getReferentie().size(); i++) {
            char refCurr = getReferentie().get(i);

            int index = in.indexOf(refCurr);
            if(index >= 0) {
                in.remove(index);
            } else {
                int lowerIndex = inLower.indexOf(Character.toLowerCase(refCurr));
                if(lowerIndex >= 0) {
                    diffs.add(Diff.hoofdletter(refCurr, i));

                    in.remove((Character) Character.toLowerCase(refCurr));
                    in.remove((Character) Character.toUpperCase(refCurr));
                } else {
                    diffs.add(Diff.create(refCurr, i));

                    in.remove(getInvoerChars().get(i));
                }

            }
        }

        List<Diff> diffsInLengte = in.stream().map(Diff::create).collect(Collectors.toList());
        if(diffsInLengte.size() != diffs.size()) {
            diffs.addAll(diffsInLengte);
        }

        diffs.forEach(System.out::println);

        return diffs.stream()
                .mapToInt(d -> d.getScore())
                .sum();
    }
}
