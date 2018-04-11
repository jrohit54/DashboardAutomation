package Generic_Component;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by rohit on 11/4/18.
 */
public class CustomizeReport implements ITestListener{
    public void onTestStart(ITestResult result) {
        System.out.println(" Starting test: " + result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("  Test passed:  " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("  Test failed:  " + result.getMethod().getMethodName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("  Test skipped:  " + result.getMethod().getMethodName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {
        System.out.println("+Begin test: " + context.getName());
    }

    public void onFinish(ITestContext context) {
        System.out.println("-End test: " + context.getName());
    }
}
