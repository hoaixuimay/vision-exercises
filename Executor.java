package org.qpro;

import org.qpro.solution.MyAnswer;

public class Executor {

	
	public static void exec() throws Exception{
		String[] tcs = {
				"tc1",
				"tc2",
				"tc3",
				"tc4",
				"tc5",
				"tc6"
		};
		
		String inputSuffix = "_input.txt";
		String answerSuffix = "_answer.txt";
		
		for(int i =0; i < tcs.length; i++){
			String args[] = {tcs[i]+inputSuffix, tcs[i]+answerSuffix};
			MyAnswer.main(args);
		}
	}
}
