package dev.markdw.munchy;

import javax.inject.Singleton;
import dagger.Component;
import dev.markdw.munchy.database.HikariDataSourceModule;
import dev.markdw.munchy.util.EnvironmentModule;


@Component(modules = {EnvironmentModule.class, HikariDataSourceModule.class})
@Singleton
public interface MunchyAppFactory {
  MunchyApp getMunchyApp();
}

