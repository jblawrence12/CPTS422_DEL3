package HalsteadPackage;

import java.util.*;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadVolumeCheck extends AbstractCheck {
    private static final String CATCH_MSG = "Halstead Volume: ";
    
    //Sets only allow unique values so makes sense to use a set to measure halstead length
    // however, this is not truly an operator set because it will just add any unique value since vocab
    //is just counting unique operators and operands
    private Set<Integer> operatorSet = new HashSet<>();
    private int length = 0;
    public int totalSize = 0;
    @Override
    public int[] getDefaultTokens() {
        // token types that contain operators from 
    	//https://checkstyle.sourceforge.io/apidocs/com/puppycrawl/tools/checkstyle/checks/whitespace/OperatorWrapCheck.html
        int[] tokens = {
        		//operator types
        		TokenTypes.PLUS,TokenTypes.MINUS,TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,
        		TokenTypes.INC,TokenTypes.DEC,TokenTypes.GE,TokenTypes.LE,TokenTypes.SL,TokenTypes.SR,TokenTypes.BSR,
        		TokenTypes.LAND,TokenTypes.LOR,TokenTypes.LNOT,TokenTypes.ASSIGN,TokenTypes.PLUS_ASSIGN,TokenTypes.MINUS_ASSIGN,
        		TokenTypes.STAR_ASSIGN,TokenTypes.DIV_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.BAND_ASSIGN,
        		TokenTypes.BXOR_ASSIGN,TokenTypes.BOR_ASSIGN,TokenTypes.QUESTION,TokenTypes.COLON,TokenTypes.COMMA,TokenTypes.DO_WHILE,TokenTypes.FOR_CONDITION,
        		TokenTypes.ARRAY_DECLARATOR,TokenTypes.ARRAY_INIT,TokenTypes.BAND,TokenTypes.BOR,TokenTypes.BXOR,
        		TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,TokenTypes.LT,TokenTypes.GT,TokenTypes.BNOT,TokenTypes.BSR_ASSIGN,
        		//This will be used for operands
        		TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
        		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL
        };
        
        return tokens;
    }
    
    @Override
    public void visitToken(DetailAST ast) {
    	length++;
    	operatorSet.add(ast.getType());
    	totalSize = operatorSet.size();
    }

    @Override
    public void beginTree(DetailAST ast) {
    	operatorSet.clear();
    	operatorSet.size(); 
    	length = 0;
    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	if(totalSize != 0) {
    		double vocab = operatorSet.size();
    		double volume = length * ((Math.log(vocab))/Math.log(2));
    		log(ast.getLineNo(), CATCH_MSG + volume + "JL");
    	} else
    	{
    		log(ast.getLineNo(), CATCH_MSG + 0 + "JL");
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
    
    public int getLength() {
        return length;
    }
}