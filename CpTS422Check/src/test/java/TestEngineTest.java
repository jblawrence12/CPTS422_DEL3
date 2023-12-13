import CountPackage.*;
import HalsteadPackage.*;
import java.io.*;
import java.util.*;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.JavaParser.Options;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

class TestEngineTest {
	public CommentCountCheck commentCheck = new CommentCountCheck();

	@Test
	void test() throws IOException, CheckstyleException {
		
		
		// Build File
		File commentFile = new File("/Users/justinblawrence/eclipse-workspace/CpTS422Check/src/main/java/BlackBoxPackage/CommentCountTest1.java");
		File commentLineFile = new File("/Users/justinblawrence/eclipse-workspace/CpTS422Check/src/main/java/BlackBoxPackage/CommentLineCountTest1.java");
		File expressionFile = new File("/Users/justinblawrence/eclipse-workspace/CpTS422Check/src/main/java/BlackBoxPackage/ExpressionCountTest1.java");
		File halsteadFile = new File("/Users/justinblawrence/eclipse-workspace/CpTS422Check/src/main/java/BlackBoxPackage/HalsteadTest1.java");
		File loopingFile = new File("/Users/justinblawrence/eclipse-workspace/CpTS422Check/src/main/java/BlackBoxPackage/LoopingCountTest1.java");
		File operandFile = new File("/Users/justinblawrence/eclipse-workspace/CpTS422Check/src/main/java/BlackBoxPackage/OperandCountTest1.java");
		File operatorFile = new File("/Users/justinblawrence/eclipse-workspace/CpTS422Check/src/main/java/BlackBoxPackage/OperatorCountTest1.java");
				
		
		// Initialize Intended Check
		CommentCountCheck commentCheck = new CommentCountCheck();
		CommentLinesCountCheck commentLinesCheck = new CommentLinesCountCheck();
		ExpressionCountCheck expressionCheck = new ExpressionCountCheck();
		LoopingCountCheck loopCheck = new LoopingCountCheck();
		OperandCountCheck operandCheck = new OperandCountCheck();
		OperatorCountCheck operatorCheck = new OperatorCountCheck();
		HalsteadDifficultyCheck difficultyCheck = new HalsteadDifficultyCheck();
		HalsteadEffortCheck effortCheck = new HalsteadEffortCheck();
		HalsteadLengthCheck lengthCheck = new HalsteadLengthCheck();
		HalsteadVocabCheck vocabCheck = new HalsteadVocabCheck();
		HalsteadVolumeCheck volumeCheck = new HalsteadVolumeCheck();
		
		AbstractCheck[] list = {commentCheck, commentLinesCheck,expressionCheck, loopCheck, operandCheck,
				operatorCheck,difficultyCheck,effortCheck, lengthCheck,vocabCheck, volumeCheck};
		
		Map<AbstractCheck, File> dictionary = new HashMap<>();
		dictionary.put(commentCheck, commentFile);
		dictionary.put(commentLinesCheck, commentLineFile);
		dictionary.put(expressionCheck, expressionFile);
		dictionary.put(loopCheck, loopingFile);
		dictionary.put(operandCheck, operandFile);
		dictionary.put(operatorCheck, operatorFile);
		dictionary.put(difficultyCheck, halsteadFile);
		dictionary.put(effortCheck, halsteadFile);
		dictionary.put(lengthCheck, halsteadFile);
		dictionary.put(vocabCheck, halsteadFile);
		dictionary.put(volumeCheck, halsteadFile);
		
		for(AbstractCheck actualCheck : list)
		{
			AbstractCheck check = actualCheck;
			FileText ft = new FileText(dictionary.get(actualCheck),"UTF-8");
			//FileContents fc = new FileContents(ft);		
			// Fill AST with FileContents
			DetailAST root = JavaParser.parseFileText(ft, Options.WITH_COMMENTS);
			// Configure Check
			check.configure(new DefaultConfiguration("Local"));
			check.contextualize(new DefaultContext());
		
			// Initialize Local Variables in Check
			check.beginTree(root);
			
			// Visit Each Token in Tree
			helper(check,root);
		
			// Complete tree and display intended logs to user.
			check.finishTree(root);
			 
			for(LocalizedMessage lm : check.getMessages()) {
				if(check.equals(commentCheck)) {  
			    	Assertions.assertEquals("Comment counts: 2JL", lm.getMessage());			
			    }else if(check.equals(commentLinesCheck))
			    {
			    	Assertions.assertEquals("Comment Line Count: 7JL", lm.getMessage());
			    }else if(check.equals(expressionCheck))
			    {
			    	Assertions.assertEquals("Expression Count: 3JL", lm.getMessage());
			    }else if(check.equals(loopCheck))
			    {
			    	Assertions.assertEquals("Looping Count: 4JL", lm.getMessage());
			    }else if(check.equals(operandCheck))
			    {
			    	Assertions.assertEquals("Operand Count: 4JL", lm.getMessage());
			    }else if(check.equals(operatorCheck))
			    {
			    	Assertions.assertEquals("Operator Count: 4JL", lm.getMessage());
			    }else if(check.equals(difficultyCheck))
			    {
			    	Assertions.assertEquals("Halstead Difficulty: 264JL", lm.getMessage());
			    }else if(check.equals(effortCheck))
			    {
			    	Assertions.assertEquals("Halstead Effort: 19396.0JL", lm.getMessage());
			    }else if(check.equals(lengthCheck))
			    {
			    	Assertions.assertEquals("Halstead Length: 373JL", lm.getMessage());
			    }else if(check.equals(vocabCheck))
			    {
			    	Assertions.assertEquals("Halstead Vocab: 9JL", lm.getMessage());
			    }else if(check.equals(volumeCheck))
			    {
			    	Assertions.assertEquals("Halstead Volume: 1182.3820255379826JL", lm.getMessage());
			    }
				System.out.println(lm.getMessage());
				
			}			
		System.out.println("Check Done!");
		}				
	}	
	
	public void helper(AbstractCheck check, DetailAST a) {
	    List<Integer> tokenTypes = new ArrayList<>();
	    
	    for (int tokenType : check.getDefaultTokens()) {
	        tokenTypes.add(tokenType);
	    }

	    while (a != null) {
	    	
	        if (tokenTypes.contains(a.getType())) {
	        	check.visitToken(a);	
	        }
	        helper(check, a.getFirstChild());
	        a = a.getNextSibling();
	    }    
	}
}