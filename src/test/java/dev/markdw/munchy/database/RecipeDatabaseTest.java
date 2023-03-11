package dev.markdw.munchy.database;

import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.zaxxer.hikari.HikariDataSource;

public class RecipeDatabaseTest {
  @Mock HikariDataSource mockDataSource;
  @Mock Connection mockConnection;
  @Mock Statement mockStatement;
  @Mock ResultSet mockResultSet;

  @BeforeEach
  public void setup() throws SQLException {
    MockitoAnnotations.openMocks(this);

    when(mockDataSource.getConnection()).thenReturn(mockConnection);
  }

  @Test
  public void getRecipe_OneRecipe_ReturnsRecipe() throws SQLException {
    
  }

  @Test
  public void getRecipe_NoRecipes_ThrowsRecipeNotFoundException() throws SQLException {
    
  }
}
