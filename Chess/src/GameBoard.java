import java.awt.*;

import javax.swing.*;

public class GameBoard extends JFrame{
  private static final int ROWS = 8, COLS = 8;
  private boolean turn= true;
  //you'll need a 2d array
   Square[][] squares= new Square[ROWS][COLS];
  //you'll need variables to keep track of the 1st and 2nd squares that were clicked
   Square first= null; //for resetting purposes
   Square second= null; //for resetting purposes
  
  public GameBoard(){
   super("CHESS");
   
   this.setLayout(new GridLayout(ROWS,COLS)); //make board
   boolean black = false; 

  //creating squares
    for(int r=0; r<squares.length;r++){ 
     for(int c=0; c<squares[0].length;c++){     
       squares[r][c]= new Square(r,c,this,black);      
       this.add(squares[r][c]);
       black=!black;
     }
     black=!black;
    }
      
   //White 
    //pawns
     for(int c=0; c<squares[0].length; c++){
      Pawn wp= new Pawn("pawn.png", true, squares[1][c]);
     } 
    //rooks
     Rook wr1= new Rook("rook-white.png", true, squares[0][0]);
     Rook wr2= new Rook("rook-white.png", true, squares[0][7]);
    //bishops
     Bishop wb1= new Bishop("white bishop.png", true, squares[0][2]);
     Bishop wb2= new Bishop("white bishop.png", true, squares[0][5]);
    //queen 
     Queen wq= new Queen("white queen.png", true, squares[0][4]); 
    //king
     King wk= new King("white king.png", true, squares[0][3]);
    //knight
     Knight wkn1= new Knight("white knight.png", true, squares[0][1]);
     Knight wkn2= new Knight("white knight.png", true, squares[0][6]);
   //Black
    //pawns
     for(int c=0; c<squares[0].length; c++){
      Pawn bp= new Pawn("black pawn.png", false, squares [6][c]);
     }
    //rook
     Rook br1= new Rook("black rook.png", false, squares[7][0]);
     Rook br2= new Rook("black rook.png", false, squares[7][7]);
    //bishops
     Bishop bb1= new Bishop("blackbishop.png", false, squares[7][2]);
     Bishop bb2= new Bishop("blackbishop.png", false, squares[7][5]);
    //queen
     Queen bq= new Queen("black queen.png", false, squares[7][4]);
    //king 
     King bk= new King("black king.png", false, squares[7][3]);
    //knight
     Knight bkn1= new Knight("black knight.png", false, squares[7][1]);
     Knight bkn2= new Knight("black knight.png", false, squares[7][6]);
     
      
     
    
      
   
   //some finishing touches + size
   this.setSize(750,750); 
   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   this.setVisible(true);
  }
  
  //one of the squares will call this function to tell the board it was clicked
  public void clicked(Square whoGotClicked){

  if(first== null){//it is the FIRST click (selecting a piece)
    if(whoGotClicked.getPiece().getTeam()!=turn){ //if wrong teams turn
     JOptionPane.showMessageDialog(this, "Not your turn!"); //tell them
     return;   
    } 
    first= whoGotClicked; //record who we are moving
   //HIGHLIGHTING	
    for(int r=0; r<squares.length;r++) 
     for(int c=0; c<squares[0].length;c++)
      if(first.getPiece().isMoveLegal(squares[r][c])==true && this.blocked(first, squares[r][c])== false) 
       squares[r][c].setHighlight(true);
    //JOptionPane.showMessageDialog(this, "You clicked at ("+first.getRow()+","+first.getCol()+")"); 
   } 
   else{//it is the SECOND click (picking destination)
    second= whoGotClicked; //record destination
    //JOptionPane.showMessageDialog(this, "move from ("+first.getRow()+","+first.getCol()+") to ("+second.getRow()+","+second.getCol()+")"); 
    boolean move=false; //establishing false in case if statement is false
    if(first.getPiece().isMoveLegal(second) && this.blocked(first, second)==false){ //if move is legal and there is nobody in the way
     move = first.getPiece().move(second); //do it
    }
    
     if(move) //if they moved
      if(turn) //who's turn
       turn=false; //switch turns
      else 
       turn= true; //don't switch
    else
     JOptionPane.showMessageDialog(this, "ILLEGAL MOVE!"); //tell them
    //turning HIGHLIGHTING off
    for(int r=0; r<squares.length;r++)
     for(int c=0; c<squares[0].length;c++)
      squares[r][c].setHighlight(false);

    //reseting variables
    first= null;
    second= null;
   }
  }
  
  //blocked method
  public boolean blocked(Square start, Square finish){
	  boolean team= start.getPiece().getTeam(); //landing places team
	  if(start.getPiece() instanceof Knight){ //knights can jump
		  if(finish.getPiece()==null) //if its open
			  return false;
		  if(team!= finish.getPiece().getTeam()) //if dif teams
			  return false;
		  
		  else return true;  //same team
	  }	  
	  int incr= (finish.getRow()-start.getRow()); //change in rows every time
	  int incc= (finish.getCol()-start.getCol()); //change in cols every time
	//checking for 0 in denominator
	  if(incr!=0) 
		  incr/=(Math.abs(incr)); 
	  
	  if(incc!=0) 
		  incc/=(Math.abs(incc));
	  
	 //current place (not exactly where but the next step)
	  int curr= start.getRow()+incr; 
	  int curc= start.getCol()+incc;
System.out.println("is blocked from "+start+" to "+finish+"?: "+incr+"x"+incc); //wheres he going
	  while(curr!=finish.getRow() || curc!=finish.getCol()){ //we aren't there yet
		  System.out.println("\tChecking "+curr+", "+curc); //where are we

		  if(squares[curr][curc].getPiece()!=null){  //if someone is there
			 
				  System.out.println("Blocked @ "+curr+", "+curc); //not okay
				  return true;
		  }
		  else{
			  //keep moving
			  curr+=incr; 
			  curc+=incc;
		  } 
	  }
	  if(finish.getPiece()!=null) //if we landed on someone
	  	if(team== finish.getPiece().getTeam()) //same team
	  		return true;
	  	else return false; //dif teams
	  
	  return false; //landed on nobody
  }

 //lame main
  public static void main(String[] args) {
   new GameBoard();
  }

}

