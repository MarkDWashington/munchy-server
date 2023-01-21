package dev.markdw.munchy;

import javax.inject.Singleton;
import dagger.Component;

@Component
@Singleton
public interface MunchyAppFactory {
  MunchyApp getMunchyApp();
}

