
public class Rook extends ChessPiece {

	//Constructor 
		public Rook(String im, boolean tm, Square lc) { //same as parent
			super(im, tm, lc);	
		}
	
	//isMoveLegal	
		public boolean isMoveLegal(Square dest) {
			int deltar= Math.abs(this.getLoc().getRow()-dest.getRow()); //change in row
			int deltac= Math.abs(this.getLoc().getCol()-dest.getCol()); //chnage in col
			if(deltar==0||deltac==0) //going very or horiz ONLY
				return true; //yay
			else return false; //nay
		}
	

}
