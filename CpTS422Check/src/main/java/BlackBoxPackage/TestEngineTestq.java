package BlackBoxPackage;

import static org.junit.jupiter.api.Assertions.*;
import CountPackage.*;
import HalsteadPackage.*;
import java.io.*;
import java.util.*;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

class TestEngine {

	@Test
	void test() throws IOException, CheckstyleException {
		
		// Build File
		String filePath = "CpTS422Check/src/test/";
		File file = new File(filePath + "LoopingCountTest.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);
		
		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
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
		
		for(AbstractCheck check : list)
		{
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
				System.out.println(lm.getMessage());
			}
		System.out.println("Check Done!");
		}				
	}	
	
	public void helper(AbstractCheck b, DetailAST a) {
		while(a != null) {
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
	}
}