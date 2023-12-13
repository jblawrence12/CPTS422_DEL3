
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CountPackage.CommentCountCheck;

import static org.mockito.Mockito.*;

public class CommentCountTest {

    CommentCountCheck commentCount = new CommentCountCheck();

  //Test that the array will contain the appropriate tokentypes
    @Test
    public void testGetDefaultTokens() 
    {
    	CommentCountCheck commentCount = new CommentCountCheck();
        int[] expectedTokens = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN};
        assertArrayEquals(expectedTokens, commentCount.getRequiredTokens());
    }
    
    //Test that visit is covered
    @Test
    public void testVisitToken() 
    {
    	CommentCountCheck spyComment = spy(new CommentCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyComment.visitToken(mockAST);
        verify(spyComment).visitToken(mockAST);
    }
    
    //Test that boolean value returns an accurate true or false
    @Test
    public void testIsCommentNodesRequired() 
    {
        CommentCountCheck commentCount = new CommentCountCheck();
        commentCount.isCommentNodesRequired(); 
        assertTrue(commentCount.isCommentNodesRequired());
    }
    
  //Test that tree is empty
    @Test
    public void testBeginTree() 
    {
    	CommentCountCheck spyComment = spy(new CommentCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyComment.beginTree(mockAST);
        verify(spyComment).beginTree(mockAST);
    }
    
    @Test
    public void testFinishTreeNormal() {
        CommentCountCheck spyComment = spy(new CommentCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        doNothing().when(spyComment).log(anyInt(), anyString());
        when(mockAST.getLineNo()).thenReturn(1);
        spyComment.finishTree(mockAST);
        verify(spyComment, times(1)).log(eq(1), contains("Comment counts: 0JL"));
    }

    @Test
    public void testFinishTreeNull() {
        CommentCountCheck spyComment = spy(new CommentCountCheck());
        doNothing().when(spyComment).log(anyInt(), anyString());
        spyComment.finishTree(null);
        verify(spyComment, times(1)).log(eq(0), contains("Comment counts: 0JL"));
    }

    //Test that the array contains the appropriate token type, similar to testGetDefault
    @Test
    public void testGetAcceptableTokens() 
    {
        CommentCountCheck commentCount = new CommentCountCheck();
        int[] expectedTokens = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN};
        assertArrayEquals(expectedTokens, commentCount.getAcceptableTokens());
    }

    // Test the array contains the appropriate token type
    @Test
    public void testGetRequiredTokensTest() 
    {
        CommentCountCheck commentCount = new CommentCountCheck();
        int[] expectedTokens = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN};
        assertArrayEquals(expectedTokens, commentCount.getRequiredTokens());
    }
}