
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CountPackage.ExpressionCountCheck;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ExpressionCountTest {

    ExpressionCountCheck expressionCount = new ExpressionCountCheck();

  //Test that the array will contain the appropriate tokentypes
    @Test
    public void testGetDefaultTokens() 
    {
    	ExpressionCountCheck expressionCount = new ExpressionCountCheck();
        int[] expectedTokens = {TokenTypes.EXPR};
        assertArrayEquals(expectedTokens, expressionCount.getRequiredTokens());
    }
    
  //Test that visit is covered
    @Test
    public void testVisitToken() 
    {
    	ExpressionCountCheck spyExpression = spy(new ExpressionCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyExpression.visitToken(mockAST);
        verify(spyExpression).visitToken(mockAST);
    }
    
  //Test that tree is empty
    @Test
    public void testBeginTree() 
    {
    	ExpressionCountCheck spyExpression = spy(new ExpressionCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyExpression.beginTree(mockAST);
        verify(spyExpression).beginTree(mockAST);
    }
    
    @Test
    public void testFinishTreeNormal() {
        ExpressionCountCheck spyExpression = spy(new ExpressionCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        doNothing().when(spyExpression).log(anyInt(), anyString());
        when(mockAST.getLineNo()).thenReturn(1);
        spyExpression.finishTree(mockAST);
        verify(spyExpression, times(1)).log(eq(1), contains("Expression Count: 0JL"));
    }

    @Test
    public void testFinishTreeNull() {
    	ExpressionCountCheck spyExpression = spy(new ExpressionCountCheck());
        doNothing().when(spyExpression).log(anyInt(), anyString());
        spyExpression.finishTree(null);
        verify(spyExpression, times(1)).log(eq(0), contains("Expression Count: 0JL"));
    }

    //Test that the array contains the appropriate token type, similar to testGetDefault
    @Test
    public void testGetAcceptableTokens() 
    {
        ExpressionCountCheck expressionCount = new ExpressionCountCheck();
        int[] expectedTokens = {TokenTypes.EXPR};
        assertArrayEquals(expectedTokens, expressionCount.getAcceptableTokens());
    }

    // Test the array contains the appropriate token type
    @Test
    public void testGetRequiredTokensTest() 
    {
        ExpressionCountCheck expressionCount = new ExpressionCountCheck();
        int[] expectedTokens = {TokenTypes.EXPR};
        assertArrayEquals(expectedTokens, expressionCount.getRequiredTokens());
    }
}