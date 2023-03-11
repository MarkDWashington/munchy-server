package dev.markdw.munchy.database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.zaxxer.hikari.HikariDataSource;
import dev.markdw.munchy.util.Environment;
import dev.markdw.proto.Recipe;
import dev.markdw.proto.RecipeIngredientSection;
import dev.markdw.proto.RecipeStep;
import dev.markdw.proto.RecipeStepSection;

public class RecipeDatabase {

  HikariDataSource hikariDataSource;

  @Inject
  RecipeDatabase(HikariDataSource hikariDataSource, Environment environment) {
    hikariDataSource.setJdbcUrl(environment.getDatabaseUrl());
    hikariDataSource.setUsername(environment.getDatabaseUsername());
    hikariDataSource.setPassword(environment.getDatabasePassword());
  }

  public Recipe getRecipe(int recipeId)
      throws InvalidProtocolBufferException, RecipeNotFoundException, SQLException {
    Connection connection = hikariDataSource.getConnection();
    ResultSet resultSet = connection.createStatement()
        .executeQuery(String.format("SELECT * FROM Recipe WHERE id = %d", recipeId));

    if (!resultSet.next()) {
      throw new RecipeNotFoundException();
    }

    return Recipe.parseFrom(resultSet.getBytes("recipe"));
  }
}
