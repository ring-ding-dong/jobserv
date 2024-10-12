import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * A factory class for creating proxies that measure the execution time of methods annotated with
 * {@link MeasureTime}.
 *
 * <p>This factory creates proxies for interface-based targets only, using Java's
 * dynamic proxy mechanism.</p>
 *
 * @author YourName
 * @version 1.0
 * @since 2024-10-08
 */
public class MeasureTimeProxyFactory {

    private final TimeLogger logger;

    /**
     * Constructs a new MeasureTimeProxyFactory with the specified logger.
     *
     * @param logger the TimeLogger to use for logging execution times
     */
    public MeasureTimeProxyFactory(TimeLogger logger) {
        this.logger = logger;
    }

    /**
     * Creates a proxy for the given target object that measures execution time for methods
     * annotated with {@link MeasureTime}.
     *
     * @param <T>    the type of the target object
     * @param target the object to be proxied
     * @return a proxy of the target object
     * @throws IllegalArgumentException if the target does not implement any interfaces
     */
    @SuppressWarnings("unchecked")
    public <T> T createProxy(T target) {
        Class<?> targetClass = target.getClass();
        if (targetClass.getInterfaces().length == 0) {
            throw new IllegalArgumentException(
                "Target class must implement at least one interface");
        }
        return (T) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            targetClass.getInterfaces(),
            new MeasureTimeInvocationHandler(target, logger)
        );
    }

    /**
     * An invocation handler that measures and logs the execution time of methods annotated with
     * {@link MeasureTime}.
     */
    private static class MeasureTimeInvocationHandler implements InvocationHandler {

        private final Object target;
        private final TimeLogger logger;
        private final MeasureTimeProcessor processor;

        /**
         * Constructs a new MeasureTimeInvocationHandler.
         *
         * @param target the target object being proxied
         * @param logger the TimeLogger to use for logging execution times
         */
        MeasureTimeInvocationHandler(Object target, TimeLogger logger) {
            this.target = target;
            this.logger = logger;
            this.processor = new MeasureTimeProcessor(logger);
        }

        /**
         * Processes method invocations on the proxy instance.
         *
         * <p>If the method is annotated with {@link MeasureTime}, its execution time
         * will be measured and logged. If not, the method will be invoked normally.</p>
         *
         * @param proxy  the proxy instance
         * @param method the method being invoked
         * @param args   the arguments to the method
         * @return the result of the method invocation
         * @throws Throwable if the method invocation throws an exception
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            MeasureTime annotation = method.getAnnotation(MeasureTime.class);
            if (annotation == null) {
                annotation = target.getClass().getAnnotation(MeasureTime.class);
            }
            if (annotation != null) {
                return processor.process(method, args, target);
            }
            return method.invoke(target, args);
        }
    }
}