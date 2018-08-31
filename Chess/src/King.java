
public class King extends ChessPiece{
	
	//Constructor 
		public King(String im, boolean tm, Square lc) { //same as parent
			super(im, tm, lc);	
		}
	//isMoveLegal
		public boolean isMoveLegal(Square dest) {
			int deltar= Math.abs(this.getLoc().getRow()-dest.getRow()); //change in row
			int deltac= Math.abs(this.getLoc().getCol()-dest.getCol()); //change in col
			if(Math.abs(deltar)<2 && Math.abs(deltac)<2) //as long as its only moving one space
				if(deltar!=0 || deltac!=0) //not staying put
					return true; //yay
				else 
					return false; //nay

			else return false; //nay
		}


}
