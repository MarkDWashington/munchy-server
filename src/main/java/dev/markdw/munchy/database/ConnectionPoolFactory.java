package dev.markdw.munchy.database;

import javax.inject.Inject;
import dev.markdw.munchy.util.Environment;

public class ConnectionPoolFactory {
  
  private Environment environment;

  @Inject
  ConnectionPoolFactory(Environment environment) {
    this.environment = environment;
  }

  public ConnectionPool getConnectionPool() {
    return new ConnectionPool(this.environment);
  }
}
