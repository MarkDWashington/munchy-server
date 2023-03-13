package dev.markdw.munchy.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
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
import com.google.protobuf.InvalidProtocolBufferException;
import com.zaxxer.hikari.HikariDataSource;
import dev.markdw.proto.Recipe;

public class RecipeDatabaseTest {
  @Mock
  HikariDataSource mockDataSource;

  @Mock
  Connection mockConnection;

  @Mock
  Statement mockStatement;

  @Mock
  ResultSet mockResultSet;

  RecipeDatabase testDatabase;


  @BeforeEach
  public void setup() throws SQLException {
    MockitoAnnotations.openMocks(this);

    when(mockDataSource.getConnection()).thenReturn(mockConnection);
    when(mockConnection.createStatement()).thenReturn(mockStatement);
    when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

    this.testDatabase =
        new RecipeDatabase(mockDataSource, "testUrl", "testUsername", "testPassword");
  }

  @Test
  public void getRecipe_FoundRecipe_ReturnsRecipe() throws SQLException, InvalidProtocolBufferException, RecipeNotFoundException {
    Recipe recipe = Recipe.getDefaultInstance();

    when(mockResultSet.next()).thenReturn(true);
    when(mockResultSet.getBytes("recipe")).thenReturn(recipe.toByteArray());

    assertEquals(recipe, testDatabase.getRecipe(0));
  }

  @Test
  public void getRecipe_NoRecipes_ThrowsRecipeNotFoundException() throws SQLException {
    when(mockResultSet.next()).thenReturn(false);
    assertThrows(RecipeNotFoundException.class, () -> {
      testDatabase.getRecipe(0);
    });
  }
}
