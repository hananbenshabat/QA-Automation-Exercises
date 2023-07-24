package automation;

import org.apache.logging.log4j.*;

public class Log { 
	public static final String STARS_DECORATIONS = "***********************************************************************************************";
	public static final String NUMBER_DECORATIONS = "###############################################################################################";
	public static final String DOLLAR_DECORATIONS = "$$$$$$$$$$$$$$$$$$$$$$";
	public static final String END_X_DECORATIONS = "XXXXXXXXXXXXXXXXXXXXXXXXXXX";
	public static final String START_TEST_DECORATIONS = "███████████▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░";
	public static final String END_TEST_DECORATIONS = "░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓███████████";
	public static final String TEST = "TEST-";
	public static final String SPACES = "               ";
	public static final String END = "-E---N---D-";
	public static final String DISPLAY_END = END_X_DECORATIONS + SPACES + END + SPACES + END_X_DECORATIONS;
	
	private static Logger logger = LogManager.getLogger(Log.class);
	private static String className = Log.class.getSimpleName();
	public  static String displayStart = DOLLAR_DECORATIONS + SPACES + className + SPACES + DOLLAR_DECORATIONS;
	private static int packageNameLength = Log.class.getPackage().getName().length();
	
	public static void startOfLogging() {
		displayStart = DOLLAR_DECORATIONS + SPACES + className + SPACES + DOLLAR_DECORATIONS;
		logger.info(STARS_DECORATIONS);
		logger.info(displayStart);
		logger.info(STARS_DECORATIONS);
	} 
	
	public static void finishLogging() {
		logger.info(NUMBER_DECORATIONS);
		logger.info(DISPLAY_END);
		logger.info(NUMBER_DECORATIONS);
	}
	
	public static void testLogging(int testNumber) {
		logger.info(START_TEST_DECORATIONS + TEST + testNumber + END_TEST_DECORATIONS);
	}
	
	public static void setLog(final Class<?> classToRun) {
		logger = LogManager.getLogger(classToRun);
		packageNameLength = classToRun.getPackage().getName().length();
		
		if(packageNameLength > 0) {
			className = classToRun.getName().substring(packageNameLength + 1);
		}
		else {
			className = classToRun.getName();
		}
	}
	
	public static void setLog() {
		setLog(Log.class);
	}
	
	public static void beginLogging(final Class<?> classToRun) {
		setLog(classToRun);
		startOfLogging();
	}

	public static void beginLogging() {
		setLog();
		startOfLogging();
	}

	public static void infoOpeningWebsite() {
	    Log.info("opening website");
	}

	public static void debugButtonClick(String buttonName) {
	    Log.debug("clicking on the " + buttonName + " button");
	}
	
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void warn(String message) {
		logger.warn(message);
	}
	
	public static void error(String message) {
		logger.error(message);
	}
	
	public static void fatal(String message) {
		logger.fatal(message);
	}
	
	public static void debug(String message) {
		logger.debug(message);
	}
}