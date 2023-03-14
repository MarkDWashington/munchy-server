package dev.markdw.munchy.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.inject.Named;
import dagger.Provides;
import dagger.Module;

@Module
public class EnvironmentModule {
  
  public static final String DB_URL = "DB_URL";
  public static final String DB_USERNAME = "DB_USERNAME";
  public static final String DB_PASSWORD = "DB_PASSWORD";

  @Provides
  @Named(DB_URL)
  public static String provideDbUrl() {
    return System.getenv(DB_URL);
  }

  @Provides
  @Named(DB_USERNAME)
  public static String getDbUsername() {
    return System.getenv(DB_USERNAME);
  }

  @Provides
  @Named(DB_PASSWORD)
  public static String getDbPassword() {
    return System.getenv(DB_PASSWORD);
  }

  public static boolean verify() throws IllegalAccessException, InvocationTargetException {
    boolean verified = true;
    
    for (Method m : EnvironmentModule.class.getDeclaredMethods()) {
      if (m.isAnnotationPresent(Named.class)) {
        if (m.invoke(null) == null) {
          verified = false;
          System.err.println("Missing environment variable: " + m.getAnnotation(Named.class).value());
        }
      }
    }

    return verified;
  }
}
