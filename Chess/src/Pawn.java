
public class Pawn extends ChessPiece{
	private int startRow= this.getLoc().getRow(); //which direction is he going to go
	public Pawn(String im, boolean tm, Square lc) { //same as parent
		super(im, tm, lc);
		
	}

	
	
	public boolean isMoveLegal(Square dest) {
		int deltar= (this.getLoc().getRow()-dest.getRow()); //change in row
		int deltac= (this.getLoc().getCol()-dest.getCol()); //change in col

		//is it going in the right direction
		if(this.getTeam()==false && deltar<0){	 				
			return false;				
		}		

		if(this.getTeam()==true &&deltar>0){
			return false;
		}	

		//landing on someone
		if(dest.getPiece()!=null){
			if(Math.abs(deltar)==1 && Math.abs(deltac)==1){ //only attack diagonal 1 space
				return true;
			}	
			else
				return false; //no other way
		}

		//still here, going the right direction && you AREN'T attacking
		if(deltac!=0){	//not going straight				
			return false;
		}	
		//assume they have the righ direction
		deltar= Math.abs(this.getLoc().getRow()-dest.getRow()); //abs value of change in row
		deltac= Math.abs(this.getLoc().getCol()-dest.getCol()); //abs value of change in col

		//first move
		if(this.getLoc().getRow()==startRow ){ //if they are on row 1 still
			if(deltar==1 || deltar==2){ //they can go 1 spaces or 2
				return true;
			}
			else { 

				return false; //no more
			}	
		}	
		else //not my first move
			if(deltar!=1){ //can't move more than one

				return false; //nay
			}		
		return true; //yay youve passed all the tests
	}
}
