package dev.markdw.munchy.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.google.protobuf.InvalidProtocolBufferException;
import com.zaxxer.hikari.HikariDataSource;
import dev.markdw.proto.Recipe;
import dev.markdw.proto.RecipeIngredientSection;
import dev.markdw.proto.RecipeStep;
import dev.markdw.proto.RecipeStepSection;

public class RecipeDatabase {

  HikariDataSource hikariDataSource;

  @Inject
  RecipeDatabase(HikariDataSource hikariDataSource, @Named("DB_URL") String dbUrl, @Named("DB_USERNAME") String dbUsername, @Named("DB_PASSWORD") String dbPassword) {
    hikariDataSource.setJdbcUrl(dbUrl);
    hikariDataSource.setUsername(dbUsername);
    hikariDataSource.setPassword(dbPassword);

    this.hikariDataSource = hikariDataSource;
  }

  public Recipe getRecipe(int recipeId)
      throws InvalidProtocolBufferException, RecipeNotFoundException, SQLException {
    Connection connection = hikariDataSource.getConnection();
    ResultSet resultSet = connection.createStatement()
        .executeQuery(String.format("SELECT recipe FROM Recipe WHERE id = %d", recipeId));

    if (!resultSet.next()) {
      throw new RecipeNotFoundException();
    }

    return Recipe.parseFrom(resultSet.getBytes("recipe"));
  }

  public void addRecipe(Recipe recipe) throws SQLException {
    Connection connection = hikariDataSource.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Recipe (recipe) VALUES (?)");
    preparedStatement.setBytes(1, recipe.toByteArray());
    preparedStatement.executeUpdate();
  }
}
