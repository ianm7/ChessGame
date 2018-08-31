
public class Knight extends ChessPiece{
	
	//Constructor 
		public Knight(String im, boolean tm, Square lc) { //same as parent
			super(im, tm, lc);	
		}
	//isMoveLegal
		public boolean isMoveLegal(Square dest) {
			int deltar= Math.abs(this.getLoc().getRow()-dest.getRow()); //change in row
			int deltac= Math.abs(this.getLoc().getCol()-dest.getCol()); //change in col
			if( (deltar==2 && deltac==1) || (deltac==2 && deltar==1)) //moving in the "L" shape
				return true; //yay
			else return false; //nay
					
		}
}
