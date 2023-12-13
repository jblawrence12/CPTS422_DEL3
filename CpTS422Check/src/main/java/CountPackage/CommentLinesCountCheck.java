package CountPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CommentLinesCountCheck extends AbstractCheck {
    private static final String CATCH_MSG = "Comment Line Count: ";
    
    //count for operator
    private int commentLinesCount = 0;

    @Override
    public int[] getDefaultTokens() {
        // token types that contain comments
        return new int[] {
            TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END, TokenTypes.COMMENT_CONTENT
        };
    }
    // add to count
    @Override
    public void visitToken(DetailAST ast) {
    	commentLinesCount++;
    }
    
    public boolean isCommentNodesRequired() {
        return true;
    }
    
    @Override
    public void beginTree(DetailAST ast) {
        commentLinesCount = 0;
    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	if(ast != null)
    	{
    		log(ast.getLineNo(), CATCH_MSG + commentLinesCount + "JL");
    	}else
    	{
    		log(0, CATCH_MSG + commentLinesCount + "JL");
    	}
    }

    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() {

        return getDefaultTokens();
    }
}

	