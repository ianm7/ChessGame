import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JOptionPane;

public abstract class ChessPiece {	
	private Image img;
	private Square loc;
	private Color color; 
	private boolean team;
	//variable for what Square I'm sitting on 
	
	//Acessors
		public Square getLoc(){return loc;} 
		public Color getColor(){return color;}
		public boolean getTeam(){return team;}
		
	//Mutators
		public void setLoc(Square x){loc= x;}
		public void setColor(Color c){color= c;}
		
	//Constructor :  you will need some more parameters! 
	public ChessPiece(String im, boolean tm, Square s){ //how to make all pieces
		loadImage(im);
		loc= s;
		loc.setPiece(this);
		team= tm;
	}//end constructor
	
	//helper function for loading up your image
	private void loadImage( String im ){
		img = Toolkit.getDefaultToolkit().getImage( getClass().getResource(im) ); 	
	    
		MediaTracker tracker = new MediaTracker (new Component () {});
		tracker.addImage(img, 0);
		//block while reading image
		try { tracker.waitForID (0); }
	        catch (InterruptedException e) {
	        	JOptionPane.showMessageDialog(null, "Error reading file");
	        }
	}//end loadImage

	public void draw(Graphics g){
		g.drawImage(img,0,0,90,90,null,null);
	}
	
	public boolean move(Square dest){
	  if(dest.getPiece()==null || (this.getTeam() != dest.getPiece().getTeam())	){	//if no piece in spot or different teams 
		  
			this.getLoc().setPiece(null); //old spot
			this.setLoc(dest); //new spot
			dest.setPiece(this); //new spot's piece
			return true;
	  }
	  
	  else{ //same team  
		  System.out.println("Same team!");	
		  return false;
	  }	  
	}
	
	public abstract boolean isMoveLegal(Square dest);
}
