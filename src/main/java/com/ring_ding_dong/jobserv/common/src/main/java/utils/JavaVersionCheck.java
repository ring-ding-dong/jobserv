package utils;

public class JavaVersionCheck {
    public static String checkJavaVersion() {
        String version = System.getProperty("java.version");
        if (version.startsWith("1.8")) {
            return "Running on Java 8";
        } else {
            try {
                int majorVersion = getMajorVersion(version);
                if (majorVersion >= 9 && majorVersion <= 21) {
                    return "Running on Java " + majorVersion;
                } else {
                    throw new RuntimeException("Unsupported Java version: " + version);
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Unable to determine Java version: " + version);
            }
        }
    }

    static int getMajorVersion(String version) {
        int firstDot = version.indexOf('.');
        return Integer.parseInt(firstDot == -1 ? version : version.substring(0, firstDot));
    }

    public static void main(String[] args) {
        System.out.println(checkJavaVersion());
    }
}