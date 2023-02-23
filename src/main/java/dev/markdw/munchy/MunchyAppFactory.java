package dev.markdw.munchy;

import javax.inject.Singleton;
import dagger.Component;
import dev.markdw.munchy.database.HikariDataSourceModule;


@Component(modules = HikariDataSourceModule.class)
@Singleton
public interface MunchyAppFactory {
  MunchyApp getMunchyApp();
}

