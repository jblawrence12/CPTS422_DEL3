
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CountPackage.OperandCountCheck;

import static org.mockito.Mockito.*;

public class OperandCountTest {

    OperandCountCheck operandCount = new OperandCountCheck();

    //Test that the array will contain the comment token types "//" and "/*"
    @Test
    public void testGetDefaultTokens() 
    {
    	OperandCountCheck operandCount = new OperandCountCheck();
        int[] expectedTokens = {TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
        		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL};
        assertArrayEquals(expectedTokens, operandCount.getRequiredTokens());
    }
    
    //Test that the counter is covered
    @Test
    public void testVisitToken() 
    {
    	OperandCountCheck spyComment = spy(new OperandCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyComment.visitToken(mockAST);
        verify(spyComment).visitToken(mockAST);
    }
    
    //Test that count is reset to 0
    @Test
    public void testBeginTree() 
    {
    	OperandCountCheck spyOperand = spy(new OperandCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyOperand.beginTree(mockAST);
        verify(spyOperand).beginTree(mockAST);
    }
    
    //Test that the finish tree method reports the correct comment count
    @Test
    public void testFinishTree() {
        OperandCountCheck spyOperand = spy(new OperandCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        doNothing().when(spyOperand).log(anyInt(), anyString());
        when(mockAST.getLineNo()).thenReturn(1);
        spyOperand.finishTree(mockAST);
        verify(spyOperand, times(1)).log(eq(1), contains("Operand Count: 0JL"));
    }

    //Test that the array contains the appropriate token type, similar to testGetDefault
    @Test
    public void testGetAcceptableTokens() 
    {
        OperandCountCheck operandCount = new OperandCountCheck();
        int[] expectedTokens = {TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
        		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL};
        assertArrayEquals(expectedTokens, operandCount.getAcceptableTokens());
    }

    // Test the array contains the appropriate token type
    @Test
    public void testGetRequiredTokensTest() 
    {
        OperandCountCheck operandCount = new OperandCountCheck();
        int[] expectedTokens = {TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
        		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL};
        assertArrayEquals(expectedTokens, operandCount.getRequiredTokens());
    }
}