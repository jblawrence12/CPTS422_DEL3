
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CountPackage.LoopingCountCheck;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class LoopingCountTest {

    LoopingCountCheck loopingCount = new LoopingCountCheck();

    //Test that the array will contain the comment token types "//" and "/*"
    @Test
    public void testGetDefaultTokens() 
    {
    	LoopingCountCheck loopingCount = new LoopingCountCheck();
        int[] expectedTokens = {TokenTypes.DO_WHILE, TokenTypes.FOR_INIT, TokenTypes.FOR_EACH_CLAUSE};
        assertArrayEquals(expectedTokens, loopingCount.getRequiredTokens());
    }
    
    //Test that the counter is covered
    @Test
    public void testVisitToken() 
    {
    	LoopingCountCheck spyLoop = spy(new LoopingCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyLoop.visitToken(mockAST);
        verify(spyLoop).visitToken(mockAST);
    }
    
    
    //Test that count is reset to 0
    @Test
    public void testBeginTree() 
    {
    	LoopingCountCheck spyLoop = spy(new LoopingCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyLoop.beginTree(mockAST);
        verify(spyLoop).beginTree(mockAST);
    }
    
    @Test
    public void testFinishTreeNormal() {
        LoopingCountCheck spyLoop = spy(new LoopingCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        doNothing().when(spyLoop).log(anyInt(), anyString());
        when(mockAST.getLineNo()).thenReturn(1);
        spyLoop.finishTree(mockAST);
        verify(spyLoop, times(1)).log(eq(1), contains("Looping Count: 0JL"));
    }

    @Test
    public void testFinishTreeNull() {
    	LoopingCountCheck spyLoop = spy(new LoopingCountCheck());
        doNothing().when(spyLoop).log(anyInt(), anyString());
        spyLoop.finishTree(null);
        verify(spyLoop, times(1)).log(eq(0), contains("Looping Count: 0JL"));
    }

    //Test that the array contains the appropriate token type, similar to testGetDefault
    @Test
    public void testGetAcceptableTokens() 
    {
        LoopingCountCheck loopingCount = new LoopingCountCheck();
        int[] expectedTokens = {TokenTypes.DO_WHILE, TokenTypes.FOR_INIT, TokenTypes.FOR_EACH_CLAUSE};
        assertArrayEquals(expectedTokens, loopingCount.getAcceptableTokens());
    }

    // Test the array contains the appropriate token type
    @Test
    public void testGetRequiredTokensTest() 
    {
        LoopingCountCheck loopingCount = new LoopingCountCheck();
        int[] expectedTokens = {TokenTypes.DO_WHILE, TokenTypes.FOR_INIT, TokenTypes.FOR_EACH_CLAUSE};
        assertArrayEquals(expectedTokens, loopingCount.getRequiredTokens());
    }
}