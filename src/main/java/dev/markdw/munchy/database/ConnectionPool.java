package dev.markdw.munchy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.inject.Inject;
import dev.markdw.munchy.util.Environment;


public class ConnectionPool {
  private BlockingQueue<Connection> availableConnections;
  private Set<Connection> usedConnections;

  @Inject
  ConnectionPool(Environment environment) {
    this.availableConnections = new LinkedBlockingQueue<>();
    this.usedConnections = new HashSet<>();

    for (int i = 0; i < 10; i++) {
      try {
        Connection connection = DriverManager.getConnection(environment.getDatabaseUrl(),
          environment.getDatabaseUsername(), environment.getDatabasePassword());
        this.availableConnections.add(connection);
      } catch (SQLException e) {
        handleSQLException(e);
      }
    }
  }

  Connection getConnection() throws InterruptedException {
    Connection connection = availableConnections.take();
    usedConnections.add(connection);
    return connection;
  }

  public void releaseConnection(Connection connection) throws InterruptedException {
    if (usedConnections.contains(connection)) {
      usedConnections.remove(connection);
      availableConnections.add(connection);
    }
  }

  private void handleSQLException(SQLException e) {
    System.out.println("SQLException: " + e.getMessage());
    System.out.println("SQLState:     " + e.getSQLState());
    System.out.println("Error code:   " + e.getErrorCode());
    e.printStackTrace();
  }
}
