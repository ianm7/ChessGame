
public class Bishop extends ChessPiece {

	//Constructor 
		public Bishop(String im, boolean tm, Square lc) { //same as parent
			super(im, tm, lc);	
		}
	
	//isMoveLegal
		public boolean isMoveLegal(Square dest) {
			int deltar= Math.abs(this.getLoc().getRow()-dest.getRow()); //change in row
			int deltac= Math.abs(this.getLoc().getCol()-dest.getCol()); //change in col
			if(deltar==deltac) //if moving diagonal
				return true; //legal
			else return false; //illegal
		}

}
