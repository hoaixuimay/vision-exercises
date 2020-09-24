import java.io.*;

public class AssertReport {

    public static void assertReport(Answer answer) throws Exception{
        String[] tcs = {
                "tc1",
                "tc2",
                "tc3",
                "tc4",
                "tc5",
                "tc6",
                "tc7"
        };
        String className = answer.getClass().getCanonicalName();
        String outputSuffix = "_output.txt";
        String inputSuffix = "_input.txt";
        String prefix = "tmp/" + className;
        String answerSuffix = "_answer.txt";
        String timeSuffix = "_time.txt";
        String reportSuffix = "_report.txt";

        System.out.println("------------------------------");
        System.out.println("ASSERT FOR class=" + className);
        System.out.println("------------------------------");
        String line;
        BufferedReader reader;
        for(int i =0; i < tcs.length; i++){
            String ansFile = prefix + "_"+ tcs[i] + answerSuffix;
            FileInputStream ansStream = new FileInputStream(new File(ansFile));
            reader = new BufferedReader(new InputStreamReader(ansStream));
            line = reader.readLine();
            int ansResult =Integer.parseInt(line);
            reader.close();
            ansStream.close();

            String outFile = tcs[i] + outputSuffix;
            FileInputStream outStream = new FileInputStream(new File(outFile));
            reader = new BufferedReader(new InputStreamReader(outStream));
            line = reader.readLine();
            int outResult =Integer.parseInt(line);
            reader.close();
            outStream.close();

            if(ansResult != outResult){
                System.out.println("Different: " + answer.getClass().getCanonicalName() + " " + tcs[i] +
                        ": " +  "answer="+ansResult + " vs expected=" + outResult);
            }

            String timeFile = prefix + "_"+ tcs[i] + timeSuffix;
            FileInputStream timeStream = new FileInputStream(new File(timeFile));
            reader = new BufferedReader(new InputStreamReader(timeStream));
            line = reader.readLine();
            double timeResult =Double.parseDouble(line);
            reader.close();
            timeStream.close();


            FileOutputStream report = new FileOutputStream(prefix + reportSuffix, i!=0?true:false);
            report.write(String.valueOf(timeResult).getBytes());
            report.write("\n".getBytes());
            report.close();
        }
    }
}
