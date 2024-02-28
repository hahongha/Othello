package main;

import control.Mouse;
import model.Piece;
import model.Board;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements Runnable{
    public static final int SQUARE_SIZE= 80;
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE/2;
    public static final int SPACING = SQUARE_SIZE/10;// khoảng cách giữa các ô
    public static final int ARC = 10;
    public static int BOARD_WIDTH = (Board.COLS + 1) * SPACING + Board.COLS * SQUARE_SIZE;
    public static int BOARD_HEIGHT = (Board.ROWS + 1) * SPACING + Board.ROWS * SQUARE_SIZE;

    public Board board;

    final int FPS = 60;
    Thread gameThread;

    Mouse mouse;


    public MainPanel(){
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        board = new Board();
        mouse= new Mouse();
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
    }

    public void update(){
        int col = getPoint(mouse.mx);
        int row = getPoint(mouse.my);
        if(row<=Board.ROWS &&col<=Board.COLS){
        if(mouse.pressed){
            Piece newPiece = new Piece(row,col,2);
            board.getBoard()[row][col]= newPiece;
//            Piece newPiece2 = new Piece(row,col+1,2);
//            board.getBoard()[row][col+1]= newPiece2;
            board.printBoard();
            System.out.print(row+"-"+col);
            board.flipPiece(col,row,2);
        }}
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

       renderplay(g);
    }

    public void renderplay(Graphics g){
        //ve bang
        for (int j = 0; j < Board.COLS; j++) {
            for (int i = 0; i < Board.ROWS; i++) {
                g.setColor(new Color(0x3C6255));
                g.drawRoundRect(getTileX(j), getTileY(i), SQUARE_SIZE, SQUARE_SIZE, ARC, ARC);
                g.fillRoundRect(getTileX(j), getTileY(i), SQUARE_SIZE, SQUARE_SIZE, ARC, ARC);
            }
        }
        for (int j = 0; j < Board.COLS; j++) {
            for (int i = 0; i < Board.ROWS; i++) {
                Piece current = board.getBoard()[i][j];
                g.setColor(current.setBackGround());
                g.drawOval(getTileX(j)+2, getTileY(i)+2, SQUARE_SIZE-5, SQUARE_SIZE-5);
                g.fillOval(getTileX(j)+2, getTileY(i)+2, SQUARE_SIZE-5, SQUARE_SIZE-5);
            }
        }

    };
    private int getTileY(int row) {
        return SPACING + row * SQUARE_SIZE+ row * SPACING;
    }

    private int getTileX(int col) {
        return SPACING + col * SQUARE_SIZE + col * SPACING;
    }

    private int getPoint(int x){
        return (int)((x-MainPanel.SPACING)/(MainPanel.SQUARE_SIZE+ MainPanel.SPACING));
    }



    public void lauchGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //game loop
        double drawInterval = 1000000000/FPS;
        double delta =0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread!= null){
            currentTime = System.nanoTime();
            delta += (currentTime- lastTime)/drawInterval;
            lastTime= currentTime;

            if(delta>= -1){
                update();
                repaint();
                delta--;
            }
        }


    }

    public Board getBoard() {
        return board;
    }
}
