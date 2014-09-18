import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class Calculus extends JPanel implements Runnable{

	public double mousex=0;
	public double mousexTransformed=0;
	
	public double mousey=0;
	public double mouseyTransformed=0;
	
	double tanx1=0;
	double tany1=0;
	double tanx2=0;
	double tany2=0;

	JPanel equation;
	FlowLayout flo;
	JLabel x5;
	JTextField x5co;
	JLabel x4;
	JTextField x4co;
	JLabel x3;
	JTextField x3co;
	JLabel x2;
	JTextField x2co;
	JLabel x1;
	JTextField x1co;
	JTextField constant;
	JButton draw;
	
	JPanel results;
	//use flo for layout
	JLabel derivLab;
	JTextField deriv;
	JLabel xLab;
	JTextField xVal; 
	JLabel tangentLab;
	JTextField tangentVal;
	
	ArrayList<Line2D> lines = new ArrayList<Line2D>();
	
	public double yintercept;

	CalculusEvent event = new CalculusEvent(this);
	public boolean newg = false;
	

	public Calculus(){
		super();
		BorderLayout master = new BorderLayout();
		JFrame frame = new JFrame("Calculus Final by Jake Sandler");
		frame.setSize(950, 820);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(master);
		
		flo = new FlowLayout();
		equation = new JPanel();
		equation.setLayout(flo);
		results = new JPanel();
		results.setLayout(flo);
		frame.add(equation, BorderLayout.NORTH);
		frame.add(this, BorderLayout.CENTER);
		frame.add(results, BorderLayout.SOUTH);
		
		x5co =  new JTextField("",5);
		x5 = new JLabel("X^5 + ");
		x4co =  new JTextField("",5);
		x4 = new JLabel("X^4 + ");
		x3co =  new JTextField("",5);
		x3 = new JLabel("X^3 + ");
		x2co =  new JTextField("",5);
		x2 = new JLabel("X^2 + ");
		x1co =  new JTextField("",5);
		x1 = new JLabel("X^1 + ");
		constant =  new JTextField("",5);
		draw = new JButton("DRAW!");
		draw.addActionListener(event);
		
		derivLab = new JLabel("Deriviative:");
		deriv = new JTextField(15);
		deriv.setEditable(false);
		xLab = new JLabel("X-Value:");
		xVal = new JTextField(5);
		xVal.setEditable(false);
		tangentLab = new JLabel("Tangent Line:");
		tangentVal = new JTextField(25);
		tangentVal.setEditable(false);
		
		equation.add(x5co);
		equation.add(x5);
		equation.add(x4co);
		equation.add(x4);
		equation.add(x3co);
		equation.add(x3);
		equation.add(x2co);
		equation.add(x2);
		equation.add(x1co);
		equation.add(x1);
		equation.add(constant);
		equation.add(draw);
		
		results.add(derivLab);
		results.add(deriv);
		results.add(xLab);
		results.add(xVal);
		results.add(tangentLab);
		results.add(tangentVal);
		
		frame.revalidate();
		frame.repaint();
		
		this.addMouseMotionListener(event);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		Rectangle2D.Double background = new Rectangle2D.Double(0,0,900,700);
		g2d.setColor(Color.black);
		g2d.fill(background);
		//draw axis
		g2d.setColor(Color.red);
		Line2D.Double xaxis = new Line2D.Double(0,350,900,350);
		Line2D.Double yaxis = new Line2D.Double(450,0,450,700);
		g2d.draw(yaxis);
		g2d.draw(xaxis);
		g2d.drawString(Double.toString(yintercept+15),450,10);
		g2d.drawString(Double.toString(yintercept-15),450,700);
		g2d.drawString("-15",0,350);
		g2d.drawString("15",880,350);
		for(double x = 0; x <30; x++){
			Line2D.Double temp = new Line2D.Double(x*30, 345, x*30, 355);
			g2d.draw(temp);
		}
		for(double y = 0; y <30; y++){
			Line2D.Double temp = new Line2D.Double(445, y*23.3, 455, y*23.3);
			g2d.draw(temp);
		}
		//draw graph
		g2d.setColor(Color.white);
		for(int x = 0; x< lines.size(); x++){
			g2d.draw(lines.get(x));
		}
		//draw tangent
		g2d.setColor(Color.green);
		Line2D.Double tang = new Line2D.Double(tanx1, tany1, tanx2, tany2);
		g2d.draw(tang);
	}
	
	public void run(){
		try{
			double five = Double.parseDouble(x5co.getText());
			double four = Double.parseDouble(x4co.getText());
			double three = Double.parseDouble(x3co.getText());
			double two = Double.parseDouble(x2co.getText());
			double one = Double.parseDouble(x1co.getText());
			double co = Double.parseDouble(constant.getText());
			yintercept=co;
			lines.clear();
			//create graph with line array
			for(double x = 0; x<900 ; x++){
				double x1 = (x/30)-15;
				double y1 = -(five*Math.pow(x1,5) + four*Math.pow(x1,4) +three*Math.pow(x1,3)+two*Math.pow(x1,2)+one*Math.pow(x1,1));
				double x2 = ((x+1)/30)-15;
				double y2 = -(five*Math.pow(x2,5) + four*Math.pow(x2,4)+ three*Math.pow(x2,3)+two*Math.pow(x2,2)+one*Math.pow(x2,1));
				Line2D.Double temp = new Line2D.Double(x,(y1*23.3)+350,x+1,(y2*23.3)+350);
				lines.add(temp);
			}
			repaint();
			
			//derivative coefficients
			double dfour = 5*five;
			double dthree = 4*four;
			double dtwo = 3*three;
			double done = 2*two;
			double dconst = one;
			
			String d4;
			String d3;
			String d2;
			String d1;
			String dc;
			//create derivative string
			if(dfour>0){
				d4="+"+Double.toString(dfour)+"x^4";
			}
			else if(dfour<0){
				d4=Double.toString(dfour)+"x^4";
			}
			else{
				d4="";
			}
			
			if(dthree>0){
				d3="+"+Double.toString(dthree)+"x^3";
			}
			else if(dthree<0){
				d3=Double.toString(dthree)+"x^3";
			}
			else{
				d3="";
			}
			if(dtwo>0){
				d2="+"+Double.toString(dtwo)+"x^2";
			}
			else if(dtwo<0){
				d2=Double.toString(dtwo)+"x^2";
			}
			else{
				d2="";
			}
			if(done>0){
				d1="+"+Double.toString(done)+"x";
			}
			else if(done<0){
				d1=Double.toString(done)+"x";
			}
			else{
				d1="";
			}
			if(dconst>0){
				dc="+"+Double.toString(dconst);
			}
			else if(dconst<0){
				dc=Double.toString(dconst);
			}
			else{
				dc="";
			}
			
			String der = d4+d3+d2+d1+dc;
			deriv.setText(der);
			newg=false;
			//draw tangent lines
			while(!newg){
				mousexTransformed=((mousex)/30)-15;
				mouseyTransformed=-(((mousey)/23.3)-15+yintercept);
				double slope = (dfour*Math.pow(mousexTransformed,4))+(dthree*Math.pow(mousexTransformed,3))+(dtwo*Math.pow(mousexTransformed,2))+(done*Math.pow(mousexTransformed,1))+(dconst);
				double y = (five*Math.pow(mousexTransformed,5) + four*Math.pow(mousexTransformed,4) +three*Math.pow(mousexTransformed,3)+two*Math.pow(mousexTransformed,2)+one*Math.pow(mousexTransformed,1));
				double b =( y-(slope*mousexTransformed));
				String sb;
				if(b==0){
					sb="";
				} 
				else if(b<0){
					sb=Double.toString(b);
				}
				else{
					sb="+"+Double.toString(b);
				}
				
				double y1 = -((-15*slope)+b) ;
				double y2 = -((15* slope)+b);
				
				tany1= (y1*23.3)+350;
				tany2= (y2*23.3)+350;
				tanx1= 0;
				tanx2= 900;
				
				String tan = "y=" + slope + "*x " +sb;
				tangentVal.setText(tan);
				xVal.setText(Double.toString(mousexTransformed));
				repaint();
				try{Thread.sleep(50);}catch(Exception e){}
			}
		} catch(java.lang.NumberFormatException e){
			JFrame error = new JFrame("ERROR");
			JLabel err = new JLabel("Invalid numbers entered");
			err.setFont(new Font(err.getFont().getName(), Font.PLAIN, 30));
			error.add(err);
			error.setSize(350,100);
			error.setVisible(true);
			error.revalidate();
		}
		
	}
	

	public static void main(String[] args){
		Calculus go = new Calculus();
	}

}
