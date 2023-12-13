package CountPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class OperatorCountCheck extends AbstractCheck {
    private static final String CATCH_MSG = "Operator Count: ";
    
    //count for operator
    private int operatorCount = 0;

    @Override
    public int[] getDefaultTokens() {
        // token types that contain operators from 
    	//https://checkstyle.sourceforge.io/apidocs/com/puppycrawl/tools/checkstyle/checks/whitespace/OperatorWrapCheck.html
        return new int[] {
        		//operator types
        		TokenTypes.PLUS,TokenTypes.MINUS,TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,
        		TokenTypes.INC,TokenTypes.DEC,TokenTypes.GE,TokenTypes.LE,TokenTypes.SL,TokenTypes.SR,TokenTypes.BSR,
        		TokenTypes.LAND,TokenTypes.LOR,TokenTypes.LNOT,TokenTypes.ASSIGN,TokenTypes.PLUS_ASSIGN,TokenTypes.MINUS_ASSIGN,
        		TokenTypes.STAR_ASSIGN,TokenTypes.DIV_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.BAND_ASSIGN,
        		TokenTypes.BXOR_ASSIGN,TokenTypes.BOR_ASSIGN,TokenTypes.QUESTION,TokenTypes.COLON,TokenTypes.COMMA,TokenTypes.DO_WHILE,TokenTypes.FOR_CONDITION,
        		TokenTypes.ARRAY_DECLARATOR,TokenTypes.ARRAY_INIT,TokenTypes.BAND,TokenTypes.BOR,TokenTypes.BXOR,
        		TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,TokenTypes.LT,TokenTypes.GT,TokenTypes.BNOT,TokenTypes.BSR_ASSIGN,
        		
        };
    }
    // add to count
    @Override
    public void visitToken(DetailAST ast) {
        operatorCount++;
    }

    @Override
    public void beginTree(DetailAST ast) {
        operatorCount = 0;
    }
    
    @Override
    public void finishTree(DetailAST ast) {
        log(ast.getLineNo(), CATCH_MSG + operatorCount + "JL");
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

	