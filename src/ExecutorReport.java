public class ExecutorReport {

    public static void main(String[] args) throws Exception {

        Executor exec = new Executor();

        // Lan's solution
        MyAnswer myAnswer = new MyAnswer();
        exec.exec(myAnswer);
        AssertReport.assertReport(myAnswer);

        // Ngan solution
        CityFinter cityFinter = new CityFinter();
        exec.exec(cityFinter);
        AssertReport.assertReport(cityFinter);

        // Duc solution
        MyTest myTest = new MyTest();
        exec.exec(myTest);
        AssertReport.assertReport(myTest);

        // Hoai
        WidestPlace widestPlace = new WidestPlace();
        exec.exec(widestPlace);
        AssertReport.assertReport(widestPlace);

        // Thanh
        AnswerThanh answerThanh = new AnswerThanh();
        exec.exec(answerThanh);
        AssertReport.assertReport(answerThanh);

        // Duy
        ProblemSolver765 problemSolver765 = new ProblemSolver765();
        exec.exec(problemSolver765);
        AssertReport.assertReport(problemSolver765);
    }
}
