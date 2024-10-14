package utils;

public class JavaVersionCheck {
    public static void main(String[] args) {
        String version = System.getProperty("java.version");
        System.out.println("Running on Java version: " + version);

        if (version.startsWith("1.8")) {
            System.out.println("Running on Java 8");
        } else {
            try {
                // Java 9+ 방식으로 버전 확인
                int majorVersion = getMajorVersion(version);
                if (majorVersion >= 9 && majorVersion <= 21) {
                    System.out.println("Running on Java " + majorVersion);
                } else {
                    throw new RuntimeException("Unsupported Java version: " + version);
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Unable to determine Java version: " + version);
            }
        }
    }

    private static int getMajorVersion(String version) {
        // Java 9 이상의 버전 문자열 파싱
        int firstDot = version.indexOf('.');
        return Integer.parseInt(firstDot == -1 ? version : version.substring(0, firstDot));
    }
}