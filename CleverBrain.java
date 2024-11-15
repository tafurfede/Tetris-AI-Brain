 import student.tetris.*; 
 //-------------------------------------------------------------------------	  	
 /**  	
  *  a brain really needs to do is provide two methods: one 	  	
  *  to rate a board (to determine if one configuration is 	  	
  *  better than another), and one to choose the "best move",  	
  *  given the current state of the board and the current   	
  *  piece that is falling. Before we look at brains in more   	
  *  detail, let's look at the core classes that brains have   	
  *  to deal with in order to do their job.  	
  *    	
  *  Follow it with additional details about its purpose, what abstraction  	
  *  it represents, and how to use it.  	
  *  	
  *  @author Federico Tafur (fedetafur)  	
  *  @version (2023.10.31)  	
  */  	
 public class CleverBrain  	
     implements Brain  	
 {  	
     //~ Fields ................................................................  		  	
     //~ Constructor ...........................................................  	
     // ----------------------------------------------------------
     /**
      * Initializes a newly created CleverBrain object.
      */
     public CleverBrain()
 	
     { 	
         super(); 	
         /*# Do any work to initialize your class here. */ 	
     }	  	
     //~ Methods ...............................................................	  	
     /**
	  	
      * this method rates the dificulty to which the board
 	  	
      * is at after every movement a new piece is dropped to 
  	
      * the board.   	
      * 
   	
      * @param board this calls the board being used by the AI
  	
      * for tetris 
  	
      *   	
      * @return the rating of the board when the pieces are 	  	
      * moved around
 	  	
      */
	  	
     public double rateBoard(Board board)
 	  	
     {
  	
         double score = 0.0;
  	
         int width = board.getWidth();
         int[] columnHeights = board.getColumnHeights();
  	
         for (int column = 0; column < width; column++) {
  	
             int columnHeight = columnHeights[column];
  	
             double columnScore = columnHeight * columnHeight;
  	
             score += columnScore;  	
}	  	
       	
         return score;
     }  	
     /**  	
      * this method uses the brain to check what would the final  	
      * desireed position be when the block moves along the board.  	
      * The intention is to look for the best possibe position,  	
      * and then let the code do the rest, making it finish at the   	
      * desired position.   	
      *   	
      * @param board this gets the board where AI tetris is played  	
      *   	
      * @param piece this is the piece to which is drops on the 	  	
      * AI tetris  	
      * 	
      * @param heightLimit is the parameter taken to get the height 	
      * of the tetris board limit  	
      *   	
      * @return the best move  	
      */
 	
     public Move bestMove(Board board, Piece piece, int heightLimit)
   	
     {
         Point point;   	
         int y; 
  	
         Move smartMove = new Move(piece);   	
         smartMove.setScore(Double.POSITIVE_INFINITY);   	
         smartMove.setLocation(new Point(4, 4)); 
  	
         Move nMove = new Move(piece);  	
         for (Piece piece1 : piece.getRotations()) {
             for (int x = 0; x <= board.getWidth() - piece1.getWidth(); x++) {
                 y = board.rowAfterDrop(piece1, x); 
                 point =  new Point(x, y);
                 
 	  	
                 board.place(piece1, point); 
	  	
                 board.clearRows(); 
  	
                 if (y + piece1.getHeight() - 1 < heightLimit) {
 	  	
                     nMove.setPiece(piece1);
  	
                     nMove.setLocation(point);
  	
                     nMove.setScore(this.rateBoard(board));
   	
                     if (nMove.getScore() < smartMove.getScore()) {
	  	
                         smartMove = new Move(piece1, point, nMove.getScore());
	  	
                     }
                 }
  	
                 board.undo();
  	
             }
  	
         }
 	
         return smartMove;	  	
     }	  	
 }