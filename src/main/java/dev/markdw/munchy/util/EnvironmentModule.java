package dev.markdw.munchy.util;

import java.lang.reflect.Method;
import javax.inject.Named;
import dagger.Provides;
import dagger.Module;

@Module
public class EnvironmentModule {

  private final String dbUrl;
  private final String dbUsername;
  private final String dbPassword;

  public EnvironmentModule(String dbUrl, String dbUsername, String dbPassword) {
    this.dbUrl = dbUrl;
    this.dbUsername = dbUsername;
    this.dbPassword = dbPassword;
  }

  @Provides
  @Named("DB_URL")
  public String provideDbUrl() {
    return dbUrl;
  }

  @Provides
  @Named("DB_USERNAME")
  public String getDbUsername() {
    return dbUsername;
  }

  @Provides
  @Named("DB_PASSWORD")
  public String getDbPassword() {
    return dbPassword;
  }
}
