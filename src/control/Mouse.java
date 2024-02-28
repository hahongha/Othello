package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
    public int mx, my;
    public boolean pressed = false;
    @Override
    public void mouseClicked(MouseEvent e) {//nhan tha
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed= true;
        mx= e.getX();
        my= e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed= false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mx= e.getX();
        my= e.getY();
    }
    @Override
    public void mouseDragged(MouseEvent e) {//nhan keo
        mx= e.getX();
        my= e.getY();
    }
}
