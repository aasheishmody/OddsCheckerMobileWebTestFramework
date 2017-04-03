package util;

public class LoggerHelper extends WebConnector {

    public static void navigating(String name, Runnable function) {
        getLogger().info("Navigating " + name);
        function.run();
        getLogger().info("Navigated " + name);
    }

    public static void searching(String name, Runnable function) {
        getLogger().info("Searching " + name);
        function.run();
        getLogger().info("Searched " + name);
    }

    public static void asserting(String name, Runnable function) {
        getLogger().info("Asserting " + name);
        function.run();
        getLogger().info("Asserted " + name);
    }

    public static void clicking(String name, Runnable function) {
        getLogger().info("Clicking on" + name);
        function.run();
        getLogger().info("Clicked " + name);
    }
}
