package BrianUtils;

public class DebugLogger {

    // basic Game Loop
    public static boolean canShowDebugMessages = false;


    public static void log(String message) {

        if(canShowDebugMessages) {
            printMessage(message);
        }
    }

    public static void log(String message, Object... args) {
        if(canShowDebugMessages) {
            printMessageAndArgs(message, args);
        }
    }

    public static void logError(String message) {
        if(canShowDebugMessages) {
            printMessage("ERROR: " + message);
        }
    }

    public static void logError(String message, Object... args) {
        if(canShowDebugMessages) {
            printMessageAndArgs("ERROR: " + message, args);
        }
    }

    public static void logWarning(String message) {
        if(canShowDebugMessages) {
            printMessage("WARNING: " + message);
        }
    }

    public static void logWarning(String message, Object... args) {
        if(canShowDebugMessages) {
            printMessageAndArgs("WARNING: " + message, args);
        }
    }

    public static void printMessage(String message) {
        System.out.printf(message + "\n");
    }

    public static void printMessageAndArgs(String Message, Object... args) {
        System.out.printf(Message + "\n", args);
    }
}
