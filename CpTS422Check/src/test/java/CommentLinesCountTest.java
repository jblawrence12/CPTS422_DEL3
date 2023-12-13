
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CountPackage.CommentLinesCountCheck;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CommentLinesCountTest {

    CommentLinesCountCheck commentLinesCount = new CommentLinesCountCheck();

  //Test that the array will contain the appropriate tokentypes
    @Test
    public void testGetDefaultTokens() 
    {
    	CommentLinesCountCheck commentCount = new CommentLinesCountCheck();
        int[] expectedTokens = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END, TokenTypes.COMMENT_CONTENT};
        assertArrayEquals(expectedTokens, commentCount.getRequiredTokens());
    }
    
  //Test that visit is covered
    @Test
    public void testVisitToken() 
    {
    	CommentLinesCountCheck spyComment = spy(new CommentLinesCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyComment.visitToken(mockAST);
        verify(spyComment).visitToken(mockAST);
    }
    
    //Test that boolean value returns an accurate true or false
    @Test
    public void testIsCommentNodesRequired() 
    {
        CommentLinesCountCheck commentCount = new CommentLinesCountCheck();
        commentCount.isCommentNodesRequired(); 
        assertTrue(commentCount.isCommentNodesRequired());
    }
    
  //Test that tree is empty
    @Test
    public void testBeginTree() 
    {
    	CommentLinesCountCheck spyComment = spy(new CommentLinesCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyComment.beginTree(mockAST);
        verify(spyComment).beginTree(mockAST);
    }
    
    @Test
    public void testFinishTreeNormal() {
        CommentLinesCountCheck spyComment = spy(new CommentLinesCountCheck());
        DetailAST mockAST = mock(DetailAST.class);
        doNothing().when(spyComment).log(anyInt(), anyString());
        when(mockAST.getLineNo()).thenReturn(1);
        spyComment.finishTree(mockAST);
        verify(spyComment, times(1)).log(eq(1), contains("Comment Line Count: 0JL"));
    }

    @Test
    public void testFinishTreeNull() {
        CommentLinesCountCheck spyComment = spy(new CommentLinesCountCheck());
        doNothing().when(spyComment).log(anyInt(), anyString());
        spyComment.finishTree(null);
        verify(spyComment, times(1)).log(eq(0), contains("Comment Line Count: 0JL"));
    }

    //Test that the array contains the appropriate token type, similar to testGetDefault
    @Test
    public void testGetAcceptableTokens() 
    {
        CommentLinesCountCheck commentCount = new CommentLinesCountCheck();
        int[] expectedTokens = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END, TokenTypes.COMMENT_CONTENT};
        assertArrayEquals(expectedTokens, commentCount.getAcceptableTokens());
    }

    // Test the array contains the appropriate token type
    @Test
    public void testGetRequiredTokensTest() 
    {
        CommentLinesCountCheck commentCount = new CommentLinesCountCheck();
        int[] expectedTokens = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END, TokenTypes.COMMENT_CONTENT};
        assertArrayEquals(expectedTokens, commentCount.getRequiredTokens());
    }
}