import student.micro.*;
import static org.assertj.core.api.Assertions.*;
import student.tetris.*;

// -------------------------------------------------------------------------
/**
 *  in this class the brains actions will be tested with 
 *  asserts. the intention of this class is to test the 
 *  CleverBrin class and see if the brain works as 
 *  intended. 
 *  
 *  Summarize what your test objectives are.
 *
 *  @author Federico Tafur (fedetafur)
 *  @version (2023.10.31)
 */
public class CleverBrainTest
    extends TestCase
{
    //~ Fields ................................................................
    private CleverBrain myBrain;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new CleverBrainTest test object.
     */
    public CleverBrainTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        /*# Insert your own setup code here */
        myBrain = new CleverBrain();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /** 
     * Suppose we want to set up a test case where our board has one 
     * row filled with blocks except for one single cell, and we want 
     * to test rateBoard() on this configuration 
     */
    public void testRateBoardOnRow01()
    {
        // myBrain is initialized in setUp()
        // First, set up board to use in this test
        Board board = new Board(10, 24,
            "#### #####"
            );

        // Now call the brain
        double score = myBrain.rateBoard(board);

        // Use your own expected score instead
        assertThat(score).isEqualTo(9.0);
    }
    
    /**
     * Suppose we want to set up a test case where our board has one 
     * row filled with blocks except for one single cell, and then 
     * our board has a second and line where we have two cells with 
     * no blocks, in this test we want to test rateBoard() on this 
     * specific configuration. 
     */
    public void testRateBoardOnRow02()
    {
        // myBrain is initialized in setUp()
        // First, set up board to use in this test
        Board board = new Board(10, 24,
            "###  #####",
            "#### #####"
            );
        
        // Now call the brain
        double score = myBrain.rateBoard(board);

        // Use your own expected score instead
        assertThat(score).isEqualTo(33.0);
    }
    
    /**
     * Suppose we want to set up a test case where our board has one 
     * row filled with blocks except for one single cell, then 
     * our board has a second and line where we have three cells with 
     * no blocks, and then our board has a third line where we have 
     * five cells with no blocks, in this test we want to test 
     * rateBoard() on this specific configuration. 
     */
    public void testRateBoardOnRow03()
    {
        // myBrain is initialized in setUp()
        // First, set up board to use in this test
        Board board = new Board(10, 24,
            "###     ##",
            "###    ###",
            "#### #####"
            );

        // Now call the brain
        double score = myBrain.rateBoard(board);

        // Use your own expected score instead
        assertThat(score).isEqualTo(52.0);
    }
    
    /**
     * Suppose we want to set up a test case where our board 
     * has no blocks present, in this test we want to test 
     * rateBoard() on this specific configuration. 
     */
    public void testRateBoardOnRow04()
    {
        // myBrain is initialized in setUp()
        // First, set up board to use in this test
        Board board = new Board(10, 24,
            "          "
            );

        // Now call the brain
        double score = myBrain.rateBoard(board);

        // Use your own expected score instead
        assertThat(score).isEqualTo(0.0);
    }
    
    /**
     * In this test case we will be testing a 10 by 24 board, with two
     * sets of blocks set up as a box with one and two cells with no
     * blocks respectively, on the first row 9 cells have blocks out 
     * of ten, in the second 4 cells have blocks out of ten, and in the
     * third row, 9 cells have blocks out of ten, in this test we want 
     * to test rateBoard() on this specific configuration. 
     */
    public void testRateBoardOnRow05()
    {
        // myBrain is initialized in setUp()
        // First, set up board to use in this test
        Board board = new Board(10, 24,
            
            "#### #####",
            "#  # #   #",
            "#### #####"
            );

        // Now call the brain
        double score = myBrain.rateBoard(board);

        // Use your own expected score instead
        assertThat(score).isEqualTo(81.0);
    }
    
    /**
     * In this test case we will be testing a 10 by 24 board, with two
     * sets of blocks set up as a box with one and two cells with no
     * blocks respectively, on the first row 10 cells have blocks out 
     * of ten (there's no cells left blank), in the second 4 cells have 
     * blocks out of ten, and in the third row, 9 cells have blocks out 
     * of ten, in this test we want to test rateBoard() on this specific 
     * configuration. 
     */
    public void testRateBoardOnRow06()
    {
        // myBrain is initialized in setUp()
        // First, set up board to use in this test
        Board board = new Board(10, 24,
            
            "#### #####",
            "## # #  ##",
            "##########"
            );

        // Now call the brain
        double score = myBrain.rateBoard(board);

        // Use your own expected score instead
        assertThat(score).isEqualTo(36.0);
    }
    
    
    /**
     * In this test method test we will test what our brain will do 
     * when a right L piece appears for this same board, all filled
     * up except for one block, this method tests the bestMove() 
     * method
     */
    public void testLPieceRow0()
    {
        Board board = new Board(10, 24,
            "#### #####"
            );
        Piece piece   = Piece.getPiece(Piece.RIGHT_L, 0);

        // Now call the brain
        Move move = myBrain.bestMove(board, piece, 20);

        // Expect the lower left destination is where the hole is
        assertThat(move.getLocation()).isEqualTo(new Point(4, 0));
        // Expect the piece is rotated counter-clockwise 3 times
        assertThat(move.getPiece()).isEqualTo(
            Piece.getPiece(Piece.RIGHT_L, 3));
    }
    
    /**
     * In this test method test we will test what our brain will do 
     * when a square piece appears for this same board, all filled
     * up except for one block, this method tests the bestMove() 
     * method
     */
    public void testSquarePieceRow012()
    {
        Board board = new Board(10, 24,
            "####   ###",
            "####   ###",
            "#### #####"
            );
        Piece piece   = Piece.getPiece(Piece.SQUARE, 0);

        // Now call the brain
        Move move = myBrain.bestMove(board, piece, 20);

        // Expect the lower left destination is where the hole is
        assertThat(move.getLocation()).isEqualTo(new Point(5, 1));
        // Expect the piece is rotated counter-clockwise 3 times
        assertThat(move.getPiece()).isEqualTo(
            Piece.getPiece(Piece.SQUARE, 0));
    }
    
    /**
     * In this test method test we will test what our brain will do 
     * when a square piece appears for this same board, all filled
     * up except for one block, this method tests the bestMove() 
     * method
     */
    public void testLDogPieceRow012()
    {
        Board board = new Board(10, 24,
            "####   ###",
            "####  ####",
            "#### #####"
            );
        Piece piece   = Piece.getPiece(Piece.LEFT_DOG, 0);

        // Now call the brain
        Move move = myBrain.bestMove(board, piece, 20);

        // Expect the lower left destination is where the hole is
        assertThat(move.getLocation()).isEqualTo(new Point(4, 0));
        // Expect the piece is rotated counter-clockwise 3 times
        assertThat(move.getPiece()).isEqualTo(
            Piece.getPiece(Piece.LEFT_DOG, 3));
    }
    
    /** 
     * test special scenario wheere a stick need to be dropped to a 
     * specific location. The piece falling is a "stick", and maybe 
     * we expect our brain to drop the stick vertically in the leftmost 
     * column (column 0). With this test the bestMove() test for a 
     * specific movement will be tested. 
     */
    public void testStickOnDemoScreen()
    {
        Board board = new Board(10, 24,
            "  #       ",
            "  ##    # ",
            " ### #####",
            " #######  ",
            "##### ##  ",
            "########  ",
            "########  "
            );
        //call the piece
        Piece piece   = Piece.getPiece(Piece.STICK, 0);
        
        // Now call the brain
        Move move = myBrain.bestMove(board, piece, 20);
        
        // Expect the lower left destination is where the hole is
        assertThat(move.getLocation()).isEqualTo(new Point(0, 3));
        // Expect the piece is not rotated at all
        assertThat(move.getPiece()).isEqualTo(
            Piece.getPiece(Piece.STICK, 0));
    }
}
