package model;

public class Board {
    protected Piece[][] board;
    public static final int ROWS = 8;
    public static final int COLS = 8;

    public Board(){
        board= new Piece[ROWS][COLS];
        startBoard();

    }

    private void startBoard(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                    Piece piece = new Piece(i, j, 0);
                    board[i][j] = piece;
            }
        }
        printBoard();
        board[ROWS/2-1][COLS/2-1].setValue(2);
        board[3][6].setValue(1);
        board[ROWS/2][COLS/2].setValue(2);
        board[ROWS/2][COLS/2-1].setValue(1);
        board[ROWS/2-1][COLS/2].setValue(1);
    }

    public void printBoard(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j].getValue()+" ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    public Piece[][] getBoard() {
        return board;
    }
    //kiem tra xem co di chuyen duoc khong
    public boolean isValidMove(int row, int col) {
        // Check if the cell is empty and within the bounds of the board
        if(row>=Board.ROWS ||col>= Board.COLS|| row<0||col<0)
            return false;
        return true;
    }
    //kiem tra xem co dat co duoc khong
    public boolean canPutPiece(int col, int row, int value){
        //col
        //xet sang trai
        for (int i = col-1; i >=0 ; i--) {
            if(board[row][i].getValue()==0)
                break;
            if(board[row][i].getValue()==value){
                return true;
            }
        }
//        //xét sang phai
        for (int i = col+1; i <Board.COLS ; i++) {
            if(board[row][i].getValue()==0)
                break;
            if(board[row][i].getValue()==value){
                return true;
            }
        }
//
//        //row
//        //xet len
        for (int i = row-1; i >=0 ; i--) {
            if(board[i][col].getValue()==0)
                break;
            if(board[i][col].getValue()==value){
                return true;
            }
        }
        //xet xuong
        for (int i = row+1; i < Board.ROWS ; i++) {
            if(board[i][col].getValue()==0)
                break;
            if(board[i][col].getValue()==value){
                return true;
            }
        }
        //xet duoi phai
        for (int i = row+1; i < Board.ROWS ; i++) {
            for (int j = col+1; j < Board.COLS ; j++) {
                if(board[i][j].getValue()==0)
                    break;
                if(board[i][j].getValue()==value){
                    return true;
                }
            }
        }
        //xet tren trai
        for (int i = row-1; i >=0 ; i--) {
            for (int j = col-1; j >=0 ; j--) {
                if(board[i][j].getValue()==0)
                    break;
                if(board[i][j].getValue()==value){
                    return true;
                }
            }
        }
        //xet tren phai
        for (int i = row-1; i >=0 ; i--) {
            for (int j = col+1; j <Board.COLS ; j++) {
                if(board[i][j].getValue()==0)
                    break;
                if(board[i][j].getValue()==value){
                    return true;
                }
            }
        }
        //xet duoi phai
        for (int i = row+1; i < Board.ROWS ; i++) {
            for (int j = col-1; j >=0 ; j--) {
                if(board[i][j].getValue()==0)
                    break;
                if(board[i][j].getValue()==value){
                    return true;
                }
            }
        }
        printBoard();
        return false;
    }
//lat co
    public void flipPiece(int col, int row, int value){
        //col
        //xet sang trai
        for (int i = col-1; i >=0 ; i--) {
            if(board[row][i].getValue()==value){
                for (int j = col-1; j >i ; j--) {
                    board[row][j].setValue(value);
                }
                break;
            }
        }
//        //xét sang phai
        for (int i = col+1; i <Board.COLS ; i++) {
            //khong tim duoc o de ket noi
            if (board[row][i].getValue()==0){
                break;
            }
            if(board[row][i].getValue()==value){
                for (int j = col+1; j <i ; j++) {
                    board[row][j].setValue(value);
                }
                break;
            }
        }

        //        //xet len
        for (int i = row-1; i >=0 ; i--) {
            //khong tim duoc o de ket noi
            if (board[i][col].getValue()==0){
                break;
            }
            if(board[i][col].getValue()==value){
                for (int j = row-1; j >i ; j--) {
                    board[j][col].setValue(value);
                }
                break;
            }
        }
        //xet xuong
        for (int i = row+1; i < Board.ROWS ; i++) {
            //khong tim duoc o de ket noi
            if (board[i][col].getValue()==0){
                break;
            }
            if(board[i][col].getValue()==value){
                for (int j = row+1; j <i ; j++) {
                    board[j][col].setValue(value);
                }
                break;
            }
        }
        //xet tren trai
        for (int i = row-1; i >=0 ; i--) {
            for (int j = col-1; j >=0 ; j--) {
                if(board[i][j].getValue()==value||board[i][j].getValue()==0){
                    break;
                }
                else{
                    board[i][j].setValue(value);
                }
            }
        }

        printBoard();
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }
}
