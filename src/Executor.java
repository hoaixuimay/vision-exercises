import java.io.FileOutputStream;

public class Executor {

	public void exec(Answer ans) throws Exception {
		String[] tcs = {
				"tc1",
				"tc2",
				"tc3",
				"tc4",
				"tc5",
				"tc6"
		};
		String className = ans.getClass().getCanonicalName();
		String inputSuffix = "_input.txt";
		String answerPrefix = "tmp/" + className;
		String answerSuffix = "_answer.txt";
		String reportSuffix = "_time.txt";
		
		for(int i =0; i < tcs.length; i++){
			long start = System.nanoTime();

			String params[] = {tcs[i]+inputSuffix, answerPrefix + "_"+ tcs[i]+answerSuffix};
			ans.exec(params);

			long finish = System.nanoTime();
			double timeElapsed = (double)(finish - start)/1000000000;

			FileOutputStream file = new FileOutputStream(answerPrefix + "_" + tcs[i]+reportSuffix);
			file.write(String.valueOf(timeElapsed).getBytes());
			file.close();

			System.out.println("className: " + className + " - tc: " + tcs[i] + " - Time elapsed: " + timeElapsed);
		}
	}
}
