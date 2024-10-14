package com.ring_ding_dong.jobserv.common.utils;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class JavaVersionCheckTest {

    private String originalVersion;

    @BeforeEach
    void setUp() {
        originalVersion = System.getProperty("java.version");
    }

    @AfterEach
    void tearDown() {
        System.setProperty("java.version", originalVersion);
    }

    @Test
    void testJava8Version() {
        System.setProperty("java.version", "1.8.0_292");
        assertEquals("Running on Java 8", JavaVersionCheck.checkJavaVersion());
    }

    @ParameterizedTest
    @ValueSource(strings = {"9", "10", "11", "17", "21"})
    void testSupportedJavaVersions(String version) {
        System.setProperty("java.version", version);
        assertEquals("Running on Java " + version, JavaVersionCheck.checkJavaVersion());
    }

    @Test
    void testUnsupportedJavaVersion() {
        System.setProperty("java.version", "22");
        Exception exception = assertThrows(RuntimeException.class, JavaVersionCheck::checkJavaVersion);
        assertTrue(exception.getMessage().contains("Unsupported Java version"));
    }

    @Test
    void testInvalidJavaVersion() {
        System.setProperty("java.version", "invalid");
        Exception exception = assertThrows(RuntimeException.class, JavaVersionCheck::checkJavaVersion);
        assertTrue(exception.getMessage().contains("Unable to determine Java version"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"9", "10", "11", "17", "21"})
    void testGetMajorVersion(String version) {
        assertEquals(Integer.parseInt(version), JavaVersionCheck.getMajorVersion(version));
    }

    @Test
    void testGetMajorVersionWithMinorVersion() {
        assertEquals(11, JavaVersionCheck.getMajorVersion("11.0.12"));
    }

    @Test
    void testMainMethod() {
        // This test is just to cover the main method execution
        assertDoesNotThrow(JavaVersionCheck::checkJavaVersion);
    }
}