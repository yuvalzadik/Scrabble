package test.test;
public class Tile {
    public final char letter ;
    public final int score ;

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Tile tile)) return false;
        if (!super.equals(object)) return false;
        return letter == tile.letter && score == tile.score;
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), letter, score);
    }

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }
}
