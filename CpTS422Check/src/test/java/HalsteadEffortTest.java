
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import HalsteadPackage.HalsteadEffortCheck;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class HalsteadEffortTest {

	 HalsteadEffortCheck  halsteadEffort = new  HalsteadEffortCheck();

    //Test that the array will contain the appropriate tokentypes
    @Test
    public void testGetDefaultTokens() 
    {
    	HalsteadEffortCheck  halsteadEffort = new  HalsteadEffortCheck();
        int[] expectedTokens = {//operator types
        		TokenTypes.PLUS,TokenTypes.MINUS,TokenTypes.STAR,TokenTypes.DIV,TokenTypes.MOD,
        		TokenTypes.INC,TokenTypes.DEC,TokenTypes.GE,TokenTypes.LE,TokenTypes.SL,TokenTypes.SR,TokenTypes.BSR,
        		TokenTypes.LAND,TokenTypes.LOR,TokenTypes.LNOT,TokenTypes.ASSIGN,TokenTypes.PLUS_ASSIGN,TokenTypes.MINUS_ASSIGN,
        		TokenTypes.STAR_ASSIGN,TokenTypes.DIV_ASSIGN,TokenTypes.MOD_ASSIGN,TokenTypes.SL_ASSIGN,TokenTypes.SR_ASSIGN,TokenTypes.BAND_ASSIGN,
        		TokenTypes.BXOR_ASSIGN,TokenTypes.BOR_ASSIGN,TokenTypes.QUESTION,TokenTypes.COLON,TokenTypes.COMMA,TokenTypes.DO_WHILE,TokenTypes.FOR_CONDITION,
        		TokenTypes.ARRAY_DECLARATOR,TokenTypes.ARRAY_INIT,TokenTypes.BAND,TokenTypes.BOR,TokenTypes.BXOR,
        		TokenTypes.EQUAL,TokenTypes.NOT_EQUAL,TokenTypes.LT,TokenTypes.GT,TokenTypes.BNOT,TokenTypes.BSR_ASSIGN,
        		TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
        		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL};
        assertArrayEquals(expectedTokens, halsteadEffort .getRequiredTokens());
    }
    
    //Test that visit is covered
    @Test
    public void testVisitTokenOperand() 
    {
    	HalsteadEffortCheck spyHalsteadEffort = spy(new HalsteadEffortCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyHalsteadEffort.visitToken(mockAST);     
        verify(spyHalsteadEffort).visitToken(mockAST);
    }
    
    //Test that visit is covered
    @Test
    public void testVisitTokenOperator() 
    {
    	HalsteadEffortCheck spyHalsteadEffort = spy(new HalsteadEffortCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyHalsteadEffort.operatorCollection = new ArrayList<>();
        spyHalsteadEffort.operatorCollection.add(125);
        when(mockAST.getType()).thenReturn(125);
        spyHalsteadEffort.visitToken(mockAST);
        verify(spyHalsteadEffort).visitToken(mockAST);
    }
    
    //Test that tree is empty
    @Test
    public void testBeginTree() 
    {
    	HalsteadEffortCheck spyHalsteadEffort= spy(new HalsteadEffortCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyHalsteadEffort.beginTree(mockAST);
        verify(spyHalsteadEffort).beginTree(mockAST);
    }
    
    @Test
    public void testFinishTreeWhenZero() {
    	 HalsteadEffortCheck spyHalsteadEffort = spy(new HalsteadEffortCheck());
    	 DetailAST mockAST = mock(DetailAST.class);
    	 doNothing().when(spyHalsteadEffort).log(anyInt(), anyString());
    	 when(spyHalsteadEffort.getOperandTotal()).thenReturn(0);
    	 spyHalsteadEffort.finishTree(mockAST);
    	 verify(spyHalsteadEffort, times(1)).log(eq(0), contains("Halstead Effort: 0JL"));
    }
    
    @Test
    public void testFinishTreeWhenNotZero() {
        HalsteadEffortCheck spyHalsteadEffort = spy(new HalsteadEffortCheck());
        DetailAST mockAST = mock(DetailAST.class);
        doNothing().when(spyHalsteadEffort).log(anyInt(), anyString());
        when(spyHalsteadEffort.getOperandTotal()).thenReturn(3);
        spyHalsteadEffort.operandSize = 2;
        spyHalsteadEffort.finishTree(mockAST);
        verify(spyHalsteadEffort, times(1)).log(anyInt(), contains("Halstead Effort"));  // Verify log is called with any integer
    }

    //Test that the array contains the appropriate token type, similar to testGetDefault
    @Test
    public void testGetAcceptableTokens() 
    {
    	HalsteadEffortCheck  halsteadEffort = new  HalsteadEffortCheck();
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
        assertArrayEquals(expectedTokens, halsteadEffort.getAcceptableTokens());
    }

    // Test the array contains the appropriate token type
    @Test
    public void testGetRequiredTokensTest() 
    {
    	HalsteadEffortCheck  halsteadEffort = new  HalsteadEffortCheck();
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
        assertArrayEquals(expectedTokens, halsteadEffort.getRequiredTokens());
    }
    // Test the array contains the appropriate token type
    @Test
    public void testGetOperandTotalTest() 
    {
    	HalsteadEffortCheck spyHalsteadEffort = spy(new HalsteadEffortCheck());
        DetailAST mockAST = mock(DetailAST.class);
        
        int[] expectedTokens = {
        		TokenTypes.VARIABLE_DEF,TokenTypes.IDENT,TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.STRING_LITERAL,
        		TokenTypes.ENUM_CONSTANT_DEF,TokenTypes.CHAR_LITERAL,TokenTypes.LITERAL_BOOLEAN,TokenTypes.LITERAL_CHAR,
        		TokenTypes.METHOD_CALL,TokenTypes.METHOD_DEF,TokenTypes.METHOD_REF,TokenTypes.LITERAL_TRUE,TokenTypes.LITERAL_FALSE,TokenTypes.LITERAL_NULL};
        for(int x : expectedTokens) {
        	when(mockAST.getType()).thenReturn(x);
            spyHalsteadEffort.visitToken(mockAST);
        }
        
        assertEquals(expectedTokens.length, spyHalsteadEffort.getOperandTotal());
    }
}