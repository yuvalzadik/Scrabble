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

    private static  int  check_boundaries_up (int row, int col){
        while (row != 0) {
            if (b.tiles_board[row -1][col] != null)
                row-=1 ;
        }
        return row;
    }
    private static  int  check_boundaries_down (int row, int col){
        while (row != 14) {
            if (b.tiles_board[row +1][col] != null)
                row+=1 ;
        }
        return row;

    }
    private static  int  check_boundaries_left (int row, int col){
        while (col != 0) {
            if (b.tiles_board[row][col -1] != null)
                col-=1 ;
        }
        return col;
    }

    private static  int  check_boundaries_right (int row, int col){
        while (col != 14) {
            if (b.tiles_board[row][col +1] != null)
                col+=1 ;
        }
        return col;

    }
    private static Tile[] create_Tilearr_tocheck_word( int length, int first_row, int first_col, boolean isvertical, Tile tile){
        Tile[] checktiles = new Tile[length];
        for (int i = 0 ; i< length; i++){
            if (isvertical) {
                    if (b.getTiles()[first_row + i][first_col] != null)
                        checktiles[i] = b.getTiles()[first_row + i][first_col];
                    else
                        checktiles[i] = tile;
            }
            else
                if (b.getTiles()[first_row][first_col + i] !=null)
                    checktiles[i] = b.getTiles()[first_row][first_col + i];
                else
                    checktiles[i] = tile;
        }
        return  checktiles;
    }
    private ArrayList<Word> getWords(Word word){
        // the function always get legal word
        ArrayList<Word> words = new ArrayList<>();
        words.add(word);
        int row = word.getRow();
        int col = word.getCol();
        int word_length = word.getTiles().length;
        if (!word.isVertical()){
            // need to check once the row of the word if it's not vertical word.
            int right_col = check_boundaries_right(row, (col +word_length - 1));
            int left_col = check_boundaries_left(row,col);
            //Tile[] checktiles = create_Tilearr_tocheck_word((right_col - left_col + 1),row,left_col,false);
            Tile[] lefttiles = create_Tilearr_tocheck_word((col - left_col),row,left_col,false,null);
            Tile[] righttiles = create_Tilearr_tocheck_word((right_col -col-word_length +1),row,col+word_length-1,false,null);
            //Tile[] checktiles = lefttiles+word.getTiles() + righttiles;
            Word check = new Word(checktiles, row,left_col,false );
            if(dictionaryLegal(word) && (right_col != (col +word_length - 1) || left_col!= col ))
                words.add( check);
            //check boundaries for each tile
            for (int i=0 ; i < word_length; i++){
                if (b.tiles_board[row][col+i]!= null){
                    int up_row = check_boundaries_up(row,col + i);
                    int down_row = check_boundaries_down(row, col +i);
                    checktiles = create_Tilearr_tocheck_word((down_row - up_row +1),up_row, col + i, true, word.getTiles()[i]);
                    check = new Word(checktiles, up_row,col+i , true );
                    if(dictionaryLegal(word))
                        words.add( check);
                }
            }
        }
        else {
            // need to check once the colum of the word if it's vertical word.
            int up_row = check_boundaries_up(row,col);
            int down_row = check_boundaries_down((row + word_length -1), col);
            Tile[] checktiles = create_Tilearr_tocheck_word((down_row - up_row +1),up_row, col, true);
            Word check =  new Word(checktiles, up_row,col,true );
            if(dictionaryLegal(word) && (down_row != row + (word_length -1) || up_row!= row ))
                words.add( check);
            //check boundaries for each tile
            for (int i=0 ; i < word_length; i++){
                if (b.tiles_board[row + i][col]!= null){
                    int right_col = check_boundaries_right(row +i , col);
                    int left_col = check_boundaries_left(row +i,col);
                    checktiles = create_Tilearr_tocheck_word((right_col - left_col + 1),row +i ,right_col,false, word.getTiles()[i]);
                    check = new Word(checktiles, row +i,right_col , false );
                    if(dictionaryLegal(word))
                        words.add( check);
                }
            }

        }
        return words;

    }
    private int getScore(Word word){
        int row = word.getRow();
        int col = word.getCol();
        int score = 0;
        int word_bonus = 0;
        for (Tile tile :word.getTiles()) {
            int location_bonus = b.bonus[row][col];
            int tile_value = tile.score;
            if (location_bonus<4)
                tile_value = tile_value* location_bonus;
            else
                word_bonus+= location_bonus%10;
            score += tile_value ;

            if (word.isVertical())
                row +=1;
            else
                col+=1;
        }
        if (word_bonus!= 0)
            score= score * word_bonus;

        return  score;
    }

    public int tryPlaceWord(Word word){
        if (!(boardLegal(word)))
            return 0;
        else if (!(dictionaryLegal(word)))
            return 0;
        else {
            ArrayList<Word> Total_words = getWords(word);
            //insert word
            int row = word.getRow();
            int col = word.getCol();
            Tile skiplocation;
            for (Tile tile :word.getTiles()) {
                if (b.tiles_board[row][col]== null)
                    b.tiles_board[row][col] = tile;
                else
                    skiplocation = b.tiles_board[row][col];
                if (word.isVertical())
                    row +=1;
                else
                    col+=1;

            }



        }
        return 0 ;
    }

}