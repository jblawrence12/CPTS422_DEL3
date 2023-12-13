package CountPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ExpressionCountCheck extends AbstractCheck {
    private static final String CATCH_MSG = "Expression Count: ";
    
    //count for expression
    private int expressionCount = 0;

    @Override
    public int[] getDefaultTokens() {
        // token types that contain comments
        return new int[] {
            TokenTypes.EXPR
        };
    }
    // add to count
    @Override
    public void visitToken(DetailAST ast) {
        expressionCount++;
    }
    
    
    @Override
    public void beginTree(DetailAST ast) {
        expressionCount = 0;
    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	if(ast != null)
    	{
    		log(ast.getLineNo(), CATCH_MSG + expressionCount + "JL");
    	}else
    	{
    		log(0, CATCH_MSG + expressionCount + "JL");
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

	