package CountPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LoopingCountCheck extends AbstractCheck {
    private static final String CATCH_MSG = "Looping Count: ";
    
    //count for loop
    private int loopCount = 0;

    @Override
    public int[] getDefaultTokens() {
        // token types that contain comments
        return new int[] {
            TokenTypes.DO_WHILE, TokenTypes.FOR_INIT, TokenTypes.FOR_EACH_CLAUSE, TokenTypes.LITERAL_WHILE
        };
    }
    // add to count
    @Override
    public void visitToken(DetailAST ast) {
        loopCount++;
    }

    @Override
    public void beginTree(DetailAST ast) {
        loopCount = 0;
    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	if(ast != null)
    	{
    		log(ast.getLineNo(), CATCH_MSG + loopCount + "JL");
    	}else
    	{
    		log(0, CATCH_MSG + loopCount + "JL");
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

	