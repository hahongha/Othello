package model;

public class Board {
    protected Piece[][] board;
    protected Piece[][] dudoanboard;
    public static final int ROWS = 8;
    public static final int COLS = 8;
    private int scoreWhite;
    private int scoreBlack;
    private int i,j=0;

    public boolean isBlack = true;//hien tai dang la quan den
    public boolean isWhite=false;//hien tai dang la quan den

    public boolean WhiteContinue= false;

    private boolean gameOn;

    private boolean gameContinue;

    public Board(){
        board= new Piece[ROWS][COLS];
        gameOn= true;
        gameContinue = true;
        startBoard();
    }

    private void startBoard(){
        scoreBlack=2;
        scoreWhite=2;
        for (i = 0; i < ROWS; i++) {
            for (j = 0; j < COLS; j++) {
                    Piece piece = new Piece(i, j, 0);
                    board[i][j] = piece;
            }
        }

        board[ROWS/2-1][COLS/2-1].setValue(2);
        board[ROWS/2][COLS/2].setValue(2);
        board[ROWS/2][COLS/2-1].setValue(1);
        board[ROWS/2-1][COLS/2].setValue(1);

    }

    public void update(int row, int col) {
        gameContinue = setDead();
        if(gameContinue){

            if(isBlack && !isWhite) {
                Piece newPiece = new Piece(row, col, 1);
                if (canPutPiece(row, col, 1)) {
                    board[row][col] = newPiece;
                    System.out.println("black");
                }else System.out.println("black is fail");

                isWhite= true;
                isBlack= false;
                WhiteContinue=true;
            }
            else if(!isBlack && isWhite) {
                Piece newPiece = new Piece(row, col, 2);
                if (canPutPiece(row, col, 2)) {
                    board[row][col] = newPiece;
                    System.out.println("white");
                }else System.out.println("white is fail");
                isWhite= false;
                isBlack= true;
                WhiteContinue= false;
            }
        }
    }

    public int getScore(int value){
        int score=0;
        for (i = 0; i < Board.ROWS; i++) {
            for (j = 0; j < Board.COLS; j++) {
                if(board[i][j].getValue()==value) score+=1;
            }
        }
        return score;
    }

    //tinh so quan trang trong moi lan lat sau do su dung minimax tinh maxWhite moi lan quan trang chay va minWhite moi lan quan dem=n chay
    //chay het ban co return ra cot va hang co gia tri score tang len thi duoc phep chay
    //su dung 1 map (list) de luu tru hang va cot co the chay
    //phat trien theo huong lua chon max

    public Piece[][] getBoard() {
        return board;
    }
    //kiem tra xem co dat co duoc khong
    public boolean canPutPiece(int row, int col, int value){
        boolean canMove = false;
        if(board[row][col].getValue() !=0) return false;
        //xet huong len tren xem co quan nao lat duoc khong
        for (i = row-1; i >=0 ; i--) {
            if(board[i][col].getValue()==0){
                canMove= false;
                break;
            }
            if(board[i][col].getValue()==value){
                flipPieceTop(row,col,value);
                canMove=true;
                break;
            }
        }
        //xet huong xuong duoi xem co quan co nao lat duoc khong
        for (i = row+1; i <Board.ROWS ; i++) {
            if(board[i][col].getValue()==0){
                canMove= false;
                break;
            }
            if(board[i][col].getValue()==value){
                flipPieceBottom(row,col,value);
                canMove=true;
                break;
            }
        }
        //xet huong sang phai
        for (i = col+1; i <Board.COLS ; i++) {
            if(board[row][i].getValue()==0){
                canMove= false;
                break;
            }
            if(board[row][i].getValue()==value){
                flipPieceRight(row,col,value);
                canMove=true;
                break;
            }
        }
        //xet huong sang trai
        for (i = col-1; i >=0 ; i--) {
            if(board[row][i].getValue()==0){
                canMove= false;
                break;
            }
            if(board[row][i].getValue()==value){
                flipPieceLeft(row,col,value);
                canMove=true;
                break;
            }
        }
        //xet huong cheo tren ben trai
        for (i = row-1, j = col-1; i >=0 && j>=0 ; i--, j--){
            if (board[i][j].getValue() == 0) {
                break;
            }
            if (board[i][j].getValue() == value) {
                flipPieceTopLeft(row, col, value);
                canMove = true;
            }
        }

//        //xet huong cheo tren phai
        for (i = row-1, j= col+1; i >=0&&j < Board.COLS ; i--,j++) {
                if(board[i][j].getValue()==0){
                    break;
                }
                if(board[i][j].getValue()==value){
                    flipPieceTopRight(row,col,value);
                    canMove=true;
                }

        }
////
////        //xet huong cheo duoi trai
        for (i = row+1, j= col-1; i <Board.ROWS&&j>=0 ; i++,j--) {
                if (board[i][j].getValue() == 0) {
                    break;
                }
                if (board[i][j].getValue() == value) {
                    flipPieceBottomLeft(row, col, value);
                    canMove=true;
                }
        }
////
////        //xet duoi phai
        for (i = row+1,j = col + 1; i <Board.ROWS && j < Board.COLS ; i++) {
                if (board[i][j].getValue() == 0) {
                    break;
                }
                if (board[i][j].getValue() == value) {
                    flipPieceBottomRight(row, col, value);
                    canMove=true;
                }
        }
        return canMove;
    }


    public void flipPieceTop(int row, int col, int value){
        for (int i = row; i >=0 ; i--) {
            if(board[i][col].getValue()==value) {
                break;
            }else board[i][col].setValue(value);
        }
    }
    public void flipPieceBottom(int row, int col, int value){
        for (int i = row; i <Board.ROWS ; i++) {
            if(board[i][col].getValue()==value) {
                break;
            }else board[i][col].setValue(value);
        }
    }
    public void flipPieceBottomLeft(int row, int col, int value){
        for (i = row, j= col; i <Board.ROWS&&j>=0 ; i++,j--){
            if(board[i][j].getValue()==value) {
                break;
            }else board[i][j].setValue(value);
    }
    }

    public void flipPieceBottomRight(int row, int col, int value) {
        for (i = row,j = col; i <Board.ROWS && j < Board.COLS ; i++) {
                if (board[i][j].getValue() == value) {
                    break;
                } else board[i][j].setValue(value);
        }
    }
    public void flipPieceTopRight(int row, int col, int value) {
        int i,j;
        for (i = row, j= col; i >=0 &&j < Board.COLS ; i--,j++){

                if (board[i][j].getValue() == value) {
                    break;
                } else board[i][j].setValue(value);
        }
    }

    public void flipPieceTopLeft(int row, int col, int value){
        int i,j;
        for (i = row, j = col; i >=0 && j>=0 ; i--, j--){
                if (board[i][j].getValue() == value) {
                    break;
                } else board[i][j].setValue(value);
        }
    }

    public void flipPieceRight(int row, int col, int value){
        for (int i = col; i <Board.COLS ; i++) {
            if(board[row][i].getValue()==value) {
                break;
            }else board[row][i].setValue(value);
        }
    }
    public void flipPieceLeft(int row, int col, int value){
        for (int i = col; i >=0 ; i--) {
            if(board[row][i].getValue()==value) {
                break;
            }else board[row][i].setValue(value);
        }
    }

    private boolean setDead(){
        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLS; j++) {
                if(board[i][j].getValue() ==0) return true;
            }
        }
        return false;
    }

    public void reset(){
        board= new Piece[ROWS][COLS];
        gameOn= true;
        gameContinue = true;
        startBoard();

    }

    public void printBoard(){
        for (int k = 0; k < Board.COLS; k++) {
            for (int l = 0; l < Board.ROWS; l++) {
                System.out.print("" + board[k][l].getValue()+" ");
            }
            System.out.println();
        }
    }


    public void setBoard(Piece[][] board) {
        this.board = board;
    }
    public int getScoreWhite(){return scoreWhite;}
    public int getScoreBlack(){return scoreBlack;}

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    public boolean isGameContinue() {
        return gameContinue;
    }

    public void setGameContinue(boolean gameContinue) {
        this.gameContinue = gameContinue;
    }
}
