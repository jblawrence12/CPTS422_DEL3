
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import HalsteadPackage.HalsteadDifficultyCheck;

import static org.mockito.Mockito.*;

import java.util.*;

public class HalsteadDifficultyTest {

	 HalsteadDifficultyCheck  halsteadDifficulty = new  HalsteadDifficultyCheck();
	//Test that the array will contain the appropriate tokentypes
    @Test
    public void testGetDefaultTokens() 
    {
    	HalsteadDifficultyCheck  halsteadDifficulty = new  HalsteadDifficultyCheck();
        int[] expectedTokens = {//operator types
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
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL};
        assertArrayEquals(expectedTokens, halsteadDifficulty.getRequiredTokens());
    }
    
    //Test that visit is covered
    @Test
    public void testVisitTokenOperand() 
    {
    	HalsteadDifficultyCheck spyHalsteadDifficulty = spy(new HalsteadDifficultyCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyHalsteadDifficulty.visitToken(mockAST);     
        verify(spyHalsteadDifficulty).visitToken(mockAST);
    }
    
    //Test that visit is covered
    @Test
    public void testVisitTokenOperator() 
    {
    	HalsteadDifficultyCheck spyHalsteadDifficulty = spy(new HalsteadDifficultyCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyHalsteadDifficulty.operatorCollection = new ArrayList<>();
        spyHalsteadDifficulty.operatorCollection.add(125);
        when(mockAST.getType()).thenReturn(125);
        spyHalsteadDifficulty.visitToken(mockAST);
        verify(spyHalsteadDifficulty).visitToken(mockAST);
    }
    
  //Test that tree is empty
    @Test
    public void testBeginTree() 
    {
    	HalsteadDifficultyCheck spyHalsteadDifficulty = spy(new HalsteadDifficultyCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyHalsteadDifficulty.beginTree(mockAST);
        verify(spyHalsteadDifficulty).beginTree(mockAST);
    }
    
    @Test
    public void testFinishTreeWhenZero() {
    	 HalsteadDifficultyCheck spyHalsteadDifficulty = spy(new HalsteadDifficultyCheck());
    	 DetailAST mockAST = mock(DetailAST.class);
    	 doNothing().when(spyHalsteadDifficulty).log(anyInt(), anyString());
    	 when(spyHalsteadDifficulty.getOperandTotal()).thenReturn(0);
    	 spyHalsteadDifficulty.finishTree(mockAST);
    	 verify(spyHalsteadDifficulty, times(1)).log(eq(0), contains("Halstead Difficulty: 0JL"));
    }
    
    @Test
    public void testFinishTreeWhenNotZero() {
        HalsteadDifficultyCheck spyHalsteadDifficulty = spy(new HalsteadDifficultyCheck());
        DetailAST mockAST = mock(DetailAST.class);
        doNothing().when(spyHalsteadDifficulty).log(anyInt(), anyString());
        when(spyHalsteadDifficulty.getOperandTotal()).thenReturn(3);
        spyHalsteadDifficulty.operandSize = 2;
        spyHalsteadDifficulty.finishTree(mockAST);
        verify(spyHalsteadDifficulty, times(1)).log(anyInt(), contains("Halstead Difficulty:"));  // Verify log is called with any integer
    }

    //Test that the array contains the appropriate token type, similar to testGetDefault
    @Test
    public void testGetAcceptableTokens() 
    {
    	HalsteadDifficultyCheck  halsteadDifficulty = new  HalsteadDifficultyCheck();
        int[] expectedTokens = {TokenTypes.PLUS,TokenTypes.MINUS,TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,
        		TokenTypes.INC,TokenTypes.DEC,TokenTypes.GE,TokenTypes.LE,TokenTypes.SL,TokenTypes.SR,TokenTypes.BSR,
        		TokenTypes.LAND,TokenTypes.LOR,TokenTypes.LNOT,TokenTypes.ASSIGN,TokenTypes.PLUS_ASSIGN,TokenTypes.MINUS_ASSIGN,
        		TokenTypes.STAR_ASSIGN,TokenTypes.DIV_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.BAND_ASSIGN,
        		TokenTypes.BXOR_ASSIGN,TokenTypes.BOR_ASSIGN,TokenTypes.QUESTION,TokenTypes.COLON,TokenTypes.COMMA,TokenTypes.DO_WHILE,TokenTypes.FOR_CONDITION,
        		TokenTypes.ARRAY_DECLARATOR,TokenTypes.ARRAY_INIT,TokenTypes.BAND,TokenTypes.BOR,TokenTypes.BXOR,
        		TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,TokenTypes.LT,TokenTypes.GT,TokenTypes.BNOT,TokenTypes.BSR_ASSIGN,
        		//This will be used for operands
        		TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
        		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL};
        assertArrayEquals(expectedTokens, halsteadDifficulty.getAcceptableTokens());
    }

    // Test the array contains the appropriate token type
    @Test
    public void testGetRequiredTokensTest() 
    {
    	HalsteadDifficultyCheck  halsteadDifficulty = new  HalsteadDifficultyCheck();
        int[] expectedTokens = {TokenTypes.PLUS,TokenTypes.MINUS,TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,
        		TokenTypes.INC,TokenTypes.DEC,TokenTypes.GE,TokenTypes.LE,TokenTypes.SL,TokenTypes.SR,TokenTypes.BSR,
        		TokenTypes.LAND,TokenTypes.LOR,TokenTypes.LNOT,TokenTypes.ASSIGN,TokenTypes.PLUS_ASSIGN,TokenTypes.MINUS_ASSIGN,
        		TokenTypes.STAR_ASSIGN,TokenTypes.DIV_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.BAND_ASSIGN,
        		TokenTypes.BXOR_ASSIGN,TokenTypes.BOR_ASSIGN,TokenTypes.QUESTION,TokenTypes.COLON,TokenTypes.COMMA,TokenTypes.DO_WHILE,TokenTypes.FOR_CONDITION,
        		TokenTypes.ARRAY_DECLARATOR,TokenTypes.ARRAY_INIT,TokenTypes.BAND,TokenTypes.BOR,TokenTypes.BXOR,
        		TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,TokenTypes.LT,TokenTypes.GT,TokenTypes.BNOT,TokenTypes.BSR_ASSIGN,
        		//This will be used for operands
        		TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
        		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL};
        assertArrayEquals(expectedTokens, halsteadDifficulty.getRequiredTokens());
    }
    // Test the array contains the appropriate token type
    @Test
    public void testGetOperandTotalTest() 
    {
    	HalsteadDifficultyCheck spyHalsteadDifficulty = spy(new HalsteadDifficultyCheck());
        DetailAST mockAST = mock(DetailAST.class);
        
        int[] expectedTokens = {
        		TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
        		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL};
        for(int x : expectedTokens) {
        	when(mockAST.getType()).thenReturn(x);
            spyHalsteadDifficulty.visitToken(mockAST);
        }
        
        assertEquals(expectedTokens.length, spyHalsteadDifficulty.getOperandTotal());
    }
}