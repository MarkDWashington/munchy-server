package dev.markdw.munchy.database;

import javax.inject.Singleton;
import com.zaxxer.hikari.HikariDataSource;
import dagger.Module;
import dagger.Provides;

@Module
public class HikariDataSourceModule {
  @Singleton
  @Provides
  static HikariDataSource provideHikariDataSource() {
    HikariDataSource dataSource = new HikariDataSource();
  }
}
