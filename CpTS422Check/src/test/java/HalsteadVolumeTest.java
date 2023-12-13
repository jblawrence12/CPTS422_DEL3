
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import HalsteadPackage.HalsteadVolumeCheck;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class HalsteadVolumeTest {

	 HalsteadVolumeCheck  halsteadEffort = new  HalsteadVolumeCheck();

    //Test that the array will contain the appropriate tokentypes
    @Test
    public void testGetDefaultTokens() 
    {
    	HalsteadVolumeCheck  halsteadVolume = new  HalsteadVolumeCheck();
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
        assertArrayEquals(expectedTokens, halsteadVolume .getRequiredTokens());
    }
    
    //Test that visit is covered
    @Test
    public void testVisitToken() 
    {
    	HalsteadVolumeCheck spyHalsteadVolume = spy(new HalsteadVolumeCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyHalsteadVolume.visitToken(mockAST);
        verify(spyHalsteadVolume).visitToken(mockAST);
    }
    
    //Test that tree is empty
    @Test
    public void testBeginTree() 
    {
    	HalsteadVolumeCheck spyHalsteadVolume = spy(new HalsteadVolumeCheck());
        DetailAST mockAST = mock(DetailAST.class);
        spyHalsteadVolume .beginTree(mockAST);
        verify(spyHalsteadVolume ).beginTree(mockAST);
    }
    
    @Test
    public void testFinishTreeWhenZero() {
    	 HalsteadVolumeCheck spyHalsteadVolume = spy(new HalsteadVolumeCheck());
    	 DetailAST mockAST = mock(DetailAST.class);
    	 doNothing().when(spyHalsteadVolume).log(anyInt(), anyString());
    	 when(spyHalsteadVolume.getLength()).thenReturn(0);
    	 spyHalsteadVolume.finishTree(mockAST);
    	 verify(spyHalsteadVolume, times(1)).log(eq(0), contains("Halstead Volume: 0JL"));
    }
    
    @Test
    public void testFinishTreeWhenNotZero() {
        HalsteadVolumeCheck spyHalsteadVolume = spy(new HalsteadVolumeCheck());
        DetailAST mockAST = mock(DetailAST.class);
        doNothing().when(spyHalsteadVolume).log(anyInt(), anyString());
        when(spyHalsteadVolume.getLength()).thenReturn(3);
        spyHalsteadVolume.totalSize = 2;
        spyHalsteadVolume.finishTree(mockAST);
        verify(spyHalsteadVolume, times(1)).log(anyInt(), contains("Halstead Volume"));  // Verify log is called with any integer
    }
    
    //Test that the array contains the appropriate token type, similar to testGetDefault
    @Test
    public void testGetAcceptableTokens() 
    {
    	HalsteadVolumeCheck  halsteadVolume = new  HalsteadVolumeCheck();
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
        assertArrayEquals(expectedTokens, halsteadVolume.getAcceptableTokens());
    }

    // Test the array contains the appropriate token type
    @Test
    public void testGetRequiredTokensTest() 
    {
    	HalsteadVolumeCheck  halsteadVolume = new  HalsteadVolumeCheck();
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
        assertArrayEquals(expectedTokens, halsteadVolume.getRequiredTokens());
    }
    
    // Test the array contains the appropriate token type
    @Test
    public void testGetLengthTest() 
    {
    	HalsteadVolumeCheck spyHalsteadVolume = spy(new HalsteadVolumeCheck());
        DetailAST mockAST = mock(DetailAST.class);
        
        int[] expectedTokens = {
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
        for(int x : expectedTokens) {
        	when(mockAST.getType()).thenReturn(x);
            spyHalsteadVolume.visitToken(mockAST);
        }
        
        assertEquals(expectedTokens.length, spyHalsteadVolume.getLength());
    }
}