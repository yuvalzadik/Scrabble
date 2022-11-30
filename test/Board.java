package test.test;

import java.util.ArrayList;
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
        //System.out.println(Arrays.deepToString(b.tiles_board));
        //System.out.println(Arrays.deepToString(b.bonus));
        return b;

    }

    public boolean boardLegal( Word word) {
        // check if it is the first word - one tile have to be on the star position
        // notfirstword will be true automatic. if it is the first word will be changed to false.
        //neartile will be false automatic. if it is the first word will be changed to true.
        boolean notfirstword = b.tiles_board[7][7] != null;
        boolean near_tile = false ;
        // check if the word inside board boundaries
        int row = word.getRow();
        int col = word.getCol();
        for (int i = 0; i < word.getTiles().length; i++) {
            if (row < 0 || row > 14 || col < 0 || col > 14)
                return false;
            if (!notfirstword) {
                near_tile = true ;
                if (row == 7 && col == 7)
                    notfirstword = true;
            }
            // check if the word contains or near existing tile.( just if it is not the first word)
            else {
            // if we want to put tile on occupied spot we have to check it is the same one
            if (b.tiles_board[row][col]!= null){
                if (b.tiles_board[row][col] != word.getTiles()[i])
                    return false ;
                else
                    near_tile = true ;
            }
            //check if the new word is near existing tile
            else {
                if (row != 0) {
                    if (b.tiles_board[row - 1][col] != null)
                        near_tile = true;
                }
                if (row != 14) {
                    if (b.tiles_board[row + 1][col] != null)
                        near_tile = true;
                }
                if (col != 0) {
                    if (b.tiles_board[row][col - 1] != null)
                        near_tile = true;
                }
                if (col != 14) {
                    if (b.tiles_board[row][col + 1] != null)
                        near_tile = true;
                }
            }
            }
            if (word.isVertical())
                row += 1;
            else
                col += 1;
        }
        // if it is the first word of the game and one of the tiles not on the star position it's not legal
        return notfirstword && near_tile;
    }
    public static boolean dictionaryLegal(Word word){
        return true;
    }

    private static  int  check_boundaries_non_vertical_up (int row, int col){
        while (row != 0) {
            if (b.tiles_board[row -1][col] != null)
                row-=1 ;
        }
        return row;
    }
    private static  int  check_boundaries_non_vertical_down (int row, int col){
        while (row != 14) {
            if (b.tiles_board[row +1][col] != null)
                row+=1 ;
        }
        return row;

    }
    public Word findword(Word word){
        if (!word.isVertical()){
            int col_occupied_spot_right = (word.getCol() + word.getTiles().length -1) ;
            int col_occupied_spot_left = word.getCol();

            while (col_occupied_spot_left != 0) {
                if (b.tiles_board[word.getRow()][col_occupied_spot_left-1] != null)
                    col_occupied_spot_left-=1 ;
            }
            while (col_occupied_spot_right != 14) {
                if (b.tiles_board[word.getRow()][col_occupied_spot_right +1] != null)
                    col_occupied_spot_right+=1 ;
            }
            int row_occupied_spot_up;
            int row_occupied_spot_down;
            for (int i = word.getRow(); i< word.getTiles().length; i++) {
                row_occupied_spot_up = check_boundaries_non_vertical_up(i, word.getCol() + i);
                row_occupied_spot_down = check_boundaries_non_vertical_down(i, word.getCol() + i);

              //  [spot_up][word get col] , [spot_down][word get col] , [spot_left][word get row] [spot_right][word get row]
            }


        }
        }
    public ArrayList<Word> getWords(Word word){
        // the function always get legal word
        ArrayList<Word> words = null;
        words.add(word);
        if (!word.isVertical()){

        }



    }

}