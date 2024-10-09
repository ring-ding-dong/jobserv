
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class MeasureTimeTest {

    @Test
    void testDefaultValues() throws NoSuchMethodException {
        Method method = TestClass.class.getMethod("defaultMethod");
        MeasureTime annotation = method.getAnnotation(MeasureTime.class);

        assertNotNull(annotation);
        assertEquals("", annotation.value());
        assertEquals(TimeUnit.MILLISECONDS, annotation.timeUnit());
        assertEquals(0.0, annotation.threshold());
        assertEquals("", annotation.loggerName());
        assertFalse(annotation.includeParameters());
        assertArrayEquals(new String[]{}, annotation.tags());
        assertFalse(annotation.trackMemoryUsage());
        assertEquals(0, annotation.maxLogCount());
        assertFalse(annotation.logStackTraceOnThresholdExceeded());
    }

    @Test
    void testCustomValues() throws NoSuchMethodException {
        Method method = TestClass.class.getMethod("customMethod");
        MeasureTime annotation = method.getAnnotation(MeasureTime.class);

        assertNotNull(annotation);
        assertEquals("customOperation", annotation.value());
        assertEquals(TimeUnit.SECONDS, annotation.timeUnit());
        assertEquals(1.5, annotation.threshold());
        assertEquals("customLogger", annotation.loggerName());
        assertTrue(annotation.includeParameters());
        assertArrayEquals(new String[]{"tag1", "tag2"}, annotation.tags());
        assertTrue(annotation.trackMemoryUsage());
        assertEquals(100, annotation.maxLogCount());
        assertTrue(annotation.logStackTraceOnThresholdExceeded());
    }

    static class TestClass {

        @MeasureTime
        public void defaultMethod() {
        }

        @MeasureTime(
            value = "customOperation",
            timeUnit = TimeUnit.SECONDS,
            threshold = 1.5,
            loggerName = "customLogger",
            includeParameters = true,
            tags = {"tag1", "tag2"},
            trackMemoryUsage = true,
            maxLogCount = 100,
            logStackTraceOnThresholdExceeded = true
        )
        public void customMethod() {
        }
    }
}