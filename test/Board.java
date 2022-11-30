package test.test;
public class Board {
    private static Board b = null;
    private Board() {

    }
    public static Board getBoard() {
        if (b == null) ;
        {
            b = new Board();
        }
        return b;
    }


}