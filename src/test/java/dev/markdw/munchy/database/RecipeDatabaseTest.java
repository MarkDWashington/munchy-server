package dev.markdw.munchy.database;

import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.zaxxer.hikari.HikariDataSource;

public class RecipeDatabaseTest {
  @Mock HikariDataSource mockDataSource;
  @Mock Connection mockConnection;
  @Mock Statement mockStatement;
  @Mock ResultSet mockResultSet;

  @Test
  public void getRecipe_returnsRecipe() throws SQLException {
    when(mockDataSource.getConnection()).thenReturn(mockConnection);
    when(mockConnection.createStatement()).thenReturn(mockStatement);
    when(mockStatement.executeQuery("SELECT * FROM Recipe WHERE id = 1")).thenReturn(mockResultSet);
    when(mockResultSet.next());
  }
}
