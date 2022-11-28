package test.test;

import java.util.Objects;

//import  java.util.Objects ;
public class Tile {
    public final char letter ;
    public final int score ;

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)
            return false;
        if (!(o instanceof Tile tile)) return false;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }
}
