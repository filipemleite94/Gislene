package AOM;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface aCaster {
	Class<?> targetClass();
}
