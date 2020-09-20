public class ExecutorReport {

    public static void main(String[] args) throws Exception {

        Executor exec = new Executor();

        // Lan's solution
        MyAnswer myAnswer = new MyAnswer();
        exec.exec(myAnswer);
        AssertReport.assertReport(myAnswer);

        // Ngan solution
//        CityFinter cityFinter = new CityFinter();
//        exec.exec(cityFinter);
//        AssertReport.assertReport(cityFinter);

        // Hoai
//        WidestPlace widestPlace = new WidestPlace();
//        exec.exec(widestPlace);
//        AssertReport.assertReport(widestPlace);
    }
}
