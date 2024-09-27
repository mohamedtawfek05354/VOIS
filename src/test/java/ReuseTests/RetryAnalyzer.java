package ReuseTests;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 3;
    // Delay in milliseconds
    private static final int retryDelay = 10000;
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            try {
                System.out.println("Retrying test " + result.getName() + " for the " + retryCount + " time.");
                Thread.sleep(retryDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
