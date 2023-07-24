package automation;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class RunTest {
	public static final int SUCCESS = 0, FAIL = 1;
	
	public static void main(String args[]) {
		int testsResult = 0;
		
		// Sanity test: consists of Product Search test and Payment Page test
		testsResult += runTest(ProductSearchTest.class);
		testsResult += runTest(PaymentPageTest.class);

		// Web Map Search test
		testsResult += runTest(WebMapSearchTest.class);
		
		// Contact Us page test
		testsResult += runTest(ContactUsTest.class);
		
		System.exit(testsResult);
	}

	// Change class to run(before the .class) to switch between tests
	// runTest(ClassName.class)
	// Example: runTest(WebMapSearchTest.class);
	public static int runTest(Class<?> classToRun) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		Log.beginLogging(classToRun);
		org.junit.runner.Result result = junit.run(classToRun);
		return calculateTestResult(result.getFailureCount());
	}
	
	public static int calculateTestResult(int failureCount) {
		int testStatus = FAIL;
		
		if (failureCount > 0) {
			Log.fatal("Test failed.");
			testStatus = FAIL;
		} else if (failureCount == 0) {
			Log.info("Test finished successfully.");
			testStatus = SUCCESS;
		}
		else {
			Log.error("Test is still ongoing.");
		}

		Log.finishLogging();
		return testStatus;
	}
}