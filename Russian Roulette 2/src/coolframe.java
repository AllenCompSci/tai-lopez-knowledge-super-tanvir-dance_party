
import java.awt.*;
import java.awt.event.*;

public class coolframe extends Frame implements MouseListener, MouseMotionListener, MouseWheelListener{
	public int mousex, mousey;
	private int mouseButton;
	public int scrollAmt;
	boolean hasClicked, clicking;
	public coolframe(String title, Dimension SIZER){
		setTitle(title);
		setSize(SIZER);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		

	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent me) {
		scrollAmt = me.getWheelRotation();
		
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		mousex = me.getX();
		mousey = me.getY();
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		mousex = me.getX();
		mousey = me.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		hasClicked = true;
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		clicking = true;
		mouseButton = me.getButton(); 
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		clicking = false;
		
	}
	public boolean hasClicked(){
		return hasClicked;
	}
	public boolean clicking(){
		return clicking;
	}
	public int getMouseButton(){
		return mouseButton;
	}
	public void setHasClicked(boolean bicpenis){
		hasClicked = bicpenis;
	}

}
