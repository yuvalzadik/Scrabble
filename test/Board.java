package test.test;

import java.util.Arrays;

public class Board {
    private static Board b = null;
    public Tile[][] tiles_board;
    private int[][] bonus;

    //public Object[][] Board_matrix;
    private Board() {
        this.tiles_board = new Tile[15][15];
        for (Tile[]a : this.tiles_board) {
            Arrays.fill(a, null);
        }
        // 1 -normal ,2 - double letter score , 3 - triple letter score, 22 - double word score , 33- triple word score
        this.bonus = new int[15][15];
        for (int[]b : this.bonus) {
            Arrays.fill(b, 1);
        }

        //33
        bonus[0][0]= 33 ;  bonus[0][7] = 33; bonus[0][14]= 33;
        bonus[7][0]= 33; bonus[7][14]= 33;
        bonus[14][0]= 33; bonus[14][7]= 33; bonus[14][14]= 33;
        //22
        for (int i=1; i<14;i++){
            if (i == 5){
                i = 10;
            }
            bonus[i][i] = 22; bonus[i][14-i] = 22;
        }
        // 2
        bonus[0][3]= 2; bonus[0][11]= 2;
        bonus[2][6]= 2; bonus[2][8]= 2;
        bonus[3][0]= 2; bonus[3][7]= 2; bonus[3][14]= 2;
        bonus[6][2]= 2; bonus[6][6]= 2; bonus[6][8]= 2; bonus[6][12]= 2;
        bonus[7][3]= 2; bonus[7][11]= 2;
        bonus[8][2]= 2; bonus[8][6]= 2; bonus[8][8]= 2; bonus[8][12]= 2;
        bonus[11][0]= 2; bonus[11][7]= 2; bonus[11][14]= 2;
        bonus[12][6]= 2; bonus[12][8]= 2;
        bonus[14][3]= 2; bonus[14][11]= 2;
        //3
        bonus[1][5]= 3; bonus[1][9]= 3;
        bonus[5][1]= 3; bonus[5][5]= 3; bonus[5][9]= 3; bonus[5][13]= 3;
        bonus[9][1]= 3; bonus[9][5]= 3; bonus[9][9]= 3; bonus[9][13]= 3;
        bonus[13][5]= 3; bonus[13][9]= 3;

    }

    public Tile[][] getTiles() {
        return this.tiles_board.clone();

    }

    public static Board getBoard() {
        if (b == null) {
            b = new Board();
        }
        System.out.println(Arrays.deepToString(b.tiles_board));
        System.out.println(Arrays.deepToString(b.bonus));
        return b;

    }
}