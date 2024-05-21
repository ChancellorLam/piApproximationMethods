package piApproximationMethods;

public class CodeInC {
    static {
        String pathToCLibrary = String.valueOf(CodeInC.class.getResource("c_algorithms.dll"));
        char[] tempArr = new char[pathToCLibrary.toCharArray().length - 6];
        System.arraycopy(pathToCLibrary.toCharArray(), 6, tempArr, 0, tempArr.length);
        pathToCLibrary = String.valueOf(tempArr);
        System.load(pathToCLibrary);
    }

    public native double madhavaLeibniz(long numOfIterations);

    public native double madhavaLeibnizPrintCalc(long numOfIterations);
}
