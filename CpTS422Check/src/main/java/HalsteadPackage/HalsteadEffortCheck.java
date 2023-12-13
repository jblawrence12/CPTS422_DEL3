package HalsteadPackage;
import java.util.*;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadEffortCheck extends AbstractCheck {
    private static final String CATCH_MSG = "Halstead Effort: ";
    

    //Sets only allow unique values so makes sense to use a set to measure halstead length
    // however, this is not truly an operator set because it will just add any unique value since vocab
    //is just counting unique operators and operands
    private Set<Integer> operatorSet = new HashSet<>();
    private Set<Integer> operandSet = new HashSet<>();
    private int operandTotal = 0;
    private int length = 0;
    public int operandSize = 0;
    public Collection<Integer> operatorCollection = Arrays.asList(TokenTypes.QUESTION, TokenTypes.COLON, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.DIV, 
    		TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.STAR, TokenTypes.MOD, TokenTypes.SR, TokenTypes.BSR, 
    		TokenTypes.GE, TokenTypes.GT, TokenTypes.SL, TokenTypes.LE, TokenTypes.LT, TokenTypes.BXOR, 
    		TokenTypes.BOR, TokenTypes.LOR, TokenTypes.BAND, TokenTypes.LAND, TokenTypes.TYPE_EXTENSION_AND, 
    		TokenTypes.LITERAL_INSTANCEOF);
    public Collection<Integer> operandCollection = Arrays.asList(TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
    		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
    		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL);
    @Override
    public int[] getDefaultTokens() {
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
    	if(operatorCollection.contains(ast.getType()))
    	{
    		operatorSet.add(ast.getType());
    	}else {
    		operandTotal++;
    		operandSet.add(ast.getType());
    		operandSize = operandSet.size();
    	}
    	length++;
    }

    @Override
    public void beginTree(DetailAST ast) {
    	operatorSet.clear();
        operandSet.clear();
        operatorSet.size();
        operandSet.size();
    	length = 0;
    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	if(getOperandTotal() == 0) {
    		log(ast.getLineNo(), CATCH_MSG + 0 + "JL");
    	}else {
    		
    		int difficulty = ((operatorSet.size()/2) * operandTotal)/operandSize;
        	double vocab = operatorSet.size();
        	double volume = length * ((Math.log(vocab))/Math.log(2));
            log(ast.getLineNo(), CATCH_MSG + volume*difficulty + "JL");
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
    
    public int getOperandTotal() {
        return operandTotal;
    }
}

	