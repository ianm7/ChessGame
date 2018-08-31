
public class Queen extends ChessPiece{
	
	//Constructor 
		public Queen(String im, boolean tm, Square lc) { //same as parent
			super(im, tm, lc);	
	}
	
	//isMoveLegal
		public boolean isMoveLegal(Square dest) {
			int deltar= Math.abs(this.getLoc().getRow()-dest.getRow()); //change in row
			int deltac= Math.abs(this.getLoc().getCol()-dest.getCol()); //change in col
			if((deltar==deltac)||(deltar==0||deltac==0)) //they can go diagonal and horiz + vert
				return true; //yay
			else return false; //nay
		}

}
