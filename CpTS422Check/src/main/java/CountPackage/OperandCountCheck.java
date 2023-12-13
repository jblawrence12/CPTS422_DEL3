package CountPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class OperandCountCheck extends AbstractCheck {
    private static final String CATCH_MSG = "Operand Count: ";
    
    //count for operand
    private int operandCount = 0;

    @Override
    public int[] getDefaultTokens() {
        // token types that contain operators from 
    	//https://checkstyle.sourceforge.io/apidocs/com/puppycrawl/tools/checkstyle/checks/whitespace/OperatorWrapCheck.html
        return new int[] {
        		
        		//This will be used for operands
        		TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
        		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL
        };
    }
    // add to count
    @Override
    public void visitToken(DetailAST ast) {
        operandCount++;
    }

    @Override
    public void beginTree(DetailAST ast) {
        operandCount = 0;
    }
    
    @Override
    public void finishTree(DetailAST ast) {
        log(ast.getLineNo(), CATCH_MSG + operandCount + "JL");
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

	