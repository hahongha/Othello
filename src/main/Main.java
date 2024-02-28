package main;

import model.Board;

import javax.swing.*;
import java.awt.*;

public class Main {
    public MainPanel game;
    public static void main(String[] args) {
        JFrame window = new JFrame("Othello");
        window.setSize(1100,800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);// chan khong cho thay doi kich thuoc
        window.setBackground(Color.GRAY);

        //add game
        MainPanel game = new MainPanel();
        window.add(game);
        //window.pack();//chinh kich thuoc vua voi bang

        window.setLocationRelativeTo(null);// can giua
        window.setVisible(true);// hien thi

        game.lauchGame();
    }
}
