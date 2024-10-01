import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: This annotation is used to measure the time taken by a method to execute.
 *
 * @author chimaek
 * @version 1.0
 * @since 2024-09-30
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MeasureTime {

}