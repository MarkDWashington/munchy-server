package dev.markdw.munchy.database;

import javax.inject.Singleton;
import com.zaxxer.hikari.HikariDataSource;
import dagger.Module;
import dagger.Provides;

@Module
public class HikariDataSourceModule {
  @Provides
  @Singleton
  HikariDataSource provideHikariDataSource() {
    return new HikariDataSource();
  }
}
