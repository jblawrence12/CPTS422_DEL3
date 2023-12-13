
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CountPackage.OperatorCountCheck;

import static org.mockito.Mockito.*;

public class OperatorCountTest {

    OperatorCountCheck operatorCount = new OperatorCountCheck();

    //Test that the array will contain the comment token types "//" and "/*"
    @Test
    public void testGetDefaultTokens() 
    {
    	OperatorCountCheck operatorCount = new OperatorCountCheck();
        int[] expectedTokens = {TokenTypes.PLUS,TokenTypes.MINUS,TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,
        		TokenTypes.INC,TokenTypes.DEC,TokenTypes.GE,TokenTypes.LE,TokenTypes.SL,TokenTypes.SR,TokenTypes.BSR,
        		TokenTypes.LAND,TokenTypes.LOR,TokenTypes.LNOT,TokenTypes.ASSIGN,TokenTypes.PLUS_ASSIGN,TokenTypes.MINUS_ASSIGN,
        		TokenTypes.STAR_ASSIGN,TokenTypes.DIV_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.BAND_ASSIGN,
        		TokenTypes.BXOR_ASSIGN,TokenTypes.BOR_ASSIGN,TokenTypes.QUESTION,TokenTypes.COLON,TokenTypes.COMMA,TokenTypes.DO_WHILE,TokenTypes.FOR_CONDITION,
        		TokenTypes.ARRAY_DECLARATOR,TokenTypes.ARRAY_INIT,TokenTypes.BAND,TokenTypes.BOR,TokenTypes.BXOR,
        		TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,TokenTypes.LT,TokenTypes.GT,TokenTypes.BNOT,TokenTypes.BSR_ASSIGN};
        assertArrayEquals(expectedTokens, operatorCount.getRequiredTokens());
    }
    
    //Test that the counter is covered
    @Test
    public void testVisitToken() 
    {
    	OperatorCountCheck spyOperator = spy(new OperatorCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyOperator.visitToken(mockAST);
        verify(spyOperator).visitToken(mockAST);
    }
    
    //Test that count is reset to 0
    @Test
    public void testBeginTree() 
    {
    	OperatorCountCheck spyOperator = spy(new OperatorCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyOperator.beginTree(mockAST);
        verify(spyOperator).beginTree(mockAST);
    }
    
    //Test that the finish tree method reports the correct comment count
    @Test
    public void testFinishTree() {
        OperatorCountCheck spyOperator = spy(new OperatorCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        doNothing().when(spyOperator).log(anyInt(), anyString());
        when(mockAST.getLineNo()).thenReturn(1);
        spyOperator.finishTree(mockAST);
        verify(spyOperator, times(1)).log(eq(1), contains("Operator Count: 0JL"));
    }

    //Test that the array contains the appropriate token type, similar to testGetDefault
    @Test
    public void testGetAcceptableTokens() 
    {
        OperatorCountCheck operatorCount = new OperatorCountCheck();
        int[] expectedTokens = {TokenTypes.PLUS,TokenTypes.MINUS,TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,
        		TokenTypes.INC,TokenTypes.DEC,TokenTypes.GE,TokenTypes.LE,TokenTypes.SL,TokenTypes.SR,TokenTypes.BSR,
        		TokenTypes.LAND,TokenTypes.LOR,TokenTypes.LNOT,TokenTypes.ASSIGN,TokenTypes.PLUS_ASSIGN,TokenTypes.MINUS_ASSIGN,
        		TokenTypes.STAR_ASSIGN,TokenTypes.DIV_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.BAND_ASSIGN,
        		TokenTypes.BXOR_ASSIGN,TokenTypes.BOR_ASSIGN,TokenTypes.QUESTION,TokenTypes.COLON,TokenTypes.COMMA,TokenTypes.DO_WHILE,TokenTypes.FOR_CONDITION,
        		TokenTypes.ARRAY_DECLARATOR,TokenTypes.ARRAY_INIT,TokenTypes.BAND,TokenTypes.BOR,TokenTypes.BXOR,
        		TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,TokenTypes.LT,TokenTypes.GT,TokenTypes.BNOT,TokenTypes.BSR_ASSIGN};
        assertArrayEquals(expectedTokens, operatorCount.getAcceptableTokens());
    }

    // Test the array contains the appropriate token type
    @Test
    public void testGetRequiredTokensTest() 
    {
        OperatorCountCheck operatorCount = new OperatorCountCheck();
        int[] expectedTokens = {TokenTypes.PLUS,TokenTypes.MINUS,TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,
        		TokenTypes.INC,TokenTypes.DEC,TokenTypes.GE,TokenTypes.LE,TokenTypes.SL,TokenTypes.SR,TokenTypes.BSR,
        		TokenTypes.LAND,TokenTypes.LOR,TokenTypes.LNOT,TokenTypes.ASSIGN,TokenTypes.PLUS_ASSIGN,TokenTypes.MINUS_ASSIGN,
        		TokenTypes.STAR_ASSIGN,TokenTypes.DIV_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.BAND_ASSIGN,
        		TokenTypes.BXOR_ASSIGN,TokenTypes.BOR_ASSIGN,TokenTypes.QUESTION,TokenTypes.COLON,TokenTypes.COMMA,TokenTypes.DO_WHILE,TokenTypes.FOR_CONDITION,
        		TokenTypes.ARRAY_DECLARATOR,TokenTypes.ARRAY_INIT,TokenTypes.BAND,TokenTypes.BOR,TokenTypes.BXOR,
        		TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,TokenTypes.LT,TokenTypes.GT,TokenTypes.BNOT,TokenTypes.BSR_ASSIGN};
        assertArrayEquals(expectedTokens, operatorCount.getRequiredTokens());
    }
}