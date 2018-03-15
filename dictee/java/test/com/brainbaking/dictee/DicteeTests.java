package com.brainbaking.dictee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DicteeTests {

    @ParameterizedTest
    @ValueSource(strings = {
            "sup Dikke kerels|wup dikkE kerel|6",
            "sup Dikke kerels|wup dikkE kerels|4" })
    public void combinatieGeval_hoofdLetterEnKleineLetters(String input) {
        Dictee dictee = createDictee(input);
        int expected = getExpected(input);

        assertEquals(expected, dictee.verbeter());
    }

    @Test
    public void uitgebreidGeval() {
        Dictee dictee = new Dictee("mogelike drangken zijn water cola fruitsap",
                "Mogelijke dranken zijn: water, cola, fruitsap.");

        assertEquals(13, dictee.verbeter());
    }

    @ParameterizedTest
    @ValueSource(strings = { "kat|kat|0", "kot|kat|2" })
    public void basisGevallen_zelfdeGrootte(String input) {
        Dictee dictee = createDictee(input);
        int expected = getExpected(input);

        assertEquals(expected, dictee.verbeter());
    }

    @ParameterizedTest
    @ValueSource(strings = { "kast|kat|2", "at|kat|2", "kat|at|2" })
    public void verschillenInGrootte(String input) {
        Dictee dictee = createDictee(input);
        int expected = getExpected(input);

        assertEquals(expected, dictee.verbeter());
    }

    @ParameterizedTest
    @ValueSource(strings = { "kat|Kat|1", "Kat|kat|1", "Kat|lat|2", "lat|Kat|2" })
    public void hoofdletters(String input) {
        Dictee dictee = createDictee(input);
        int expected = getExpected(input);

        assertEquals(expected, dictee.verbeter());
    }

    private int getExpected(String input) {
        return Integer.parseInt(input.split("\\|")[2]);
    }

    private Dictee createDictee(String input) {
        String[] split = input.split("\\|");
        return new Dictee(split[0], split[1]);
    }
}
