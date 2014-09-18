import java.awt.event.*;

public class CalculusEvent implements ActionListener, MouseMotionListener{

	Calculus main;

	public CalculusEvent(Calculus in){
		main=in;
	}

	public void actionPerformed(ActionEvent e){
		main.newg=true;
		try{Thread.sleep(100);}catch(Exception a){}
		new Thread(main).start();

	}

	public void mouseMoved(MouseEvent e){
		main.mousex = e.getX();
		main.mousey = e.getY();
	}

	public void mouseDragged(MouseEvent e){

	}


}
