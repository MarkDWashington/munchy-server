package dev.markdw.munchy.util;

import javax.inject.Inject;

public class Environment {

  private static final String[] ENVIRONMENT_VARIABLES = {
    "DATABASE_URL",
    "DATABASE_USERNAME",
    "DATABASE_PASSWORD"
  };

  @Inject
  Environment() {}
  
  public String getDatabaseUrl() {
    return System.getenv("DATABASE_URL");
  }

  public String getDatabaseUsername() {
    return System.getenv("DATABASE_USERNAME");
  }

  public String getDatabasePassword() {
    return System.getenv("DATABASE_PASSWORD");
  }

  public static boolean verify () {
    boolean verified = true;

    for (String var : ENVIRONMENT_VARIABLES) {
      boolean present = System.getenv(var) != null;
      System.out.printf("Environment variable '%s' present: %b\n", var, present);
      if (!present) {
        verified = false;
      }
    }

    return verified;
  }
}
