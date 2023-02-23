package dev.markdw.munchy.database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
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

  public Recipe getRecipe(int recipeId) throws RecipeNotFoundException, SQLException {

    Connection connection = hikariDataSource.getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet =
        statement.executeQuery(String.format("SELECT * FROM Recipe WHERE id = %d", recipeId));

    String title;
    List<RecipeIngredientSection> ingredientSections = new ArrayList<>();
    List<RecipeStepSection> stepSections = new ArrayList<>();

    if (!resultSet.next()) {
      throw new RecipeNotFoundException();
    }

    title = resultSet.getString(0);
    ingredientSections = getRecipeIngredientSections(connection, recipeId);
    stepSections = getRecipeStepSections(connection, recipeId);

    return Recipe.newBuilder().setTitle(title).addAllIngredientSections(ingredientSections)
        .addAllStepSections(stepSections).build();
  }

  public List<RecipeIngredientSection> getRecipeIngredientSections(Connection connection, int recipeId)
      throws SQLException {
    List<RecipeIngredientSection> ingredientSections = new ArrayList<>();

    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(String
        .format("SELECT * FROM RecipeIngredientSection WHERE recipeId = %d ORDER BY id", recipeId));

    while (resultSet.next()) {
      int sectionId = resultSet.getInt(1);
      // TODO: Get all ingredients using section id

      String heading = resultSet.getString(3);

      RecipeIngredientSection section =
          RecipeIngredientSection.newBuilder().setHeading(heading).build();
      ingredientSections.add(section);
    }

    return ingredientSections;
  }

  public List<RecipeStepSection> getRecipeStepSections(Connection connection, int recipeId) throws SQLException {
    List<RecipeStepSection> stepSections = new ArrayList<>();

    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(
        String.format("SELECT * FROM RecipeStepSection WHERE recipeId = %d ORDER BY id", recipeId));

    while (resultSet.next()) {
      int sectionId = resultSet.getInt(2);

      // Get all steps using section id
      String heading = resultSet.getString(3);
      List<RecipeStep> steps = getRecipeSteps(connection, recipeId, sectionId);
      RecipeStepSection section =
          RecipeStepSection.newBuilder().setHeading(heading).addAllSteps(steps).build();

      stepSections.add(section);
    }

    return stepSections;
  }

  public List<RecipeStep> getRecipeSteps(Connection connection, int recipeId, int recipeStepSectionId)
      throws SQLException {
    List<RecipeStep> steps = new ArrayList<>();

    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(String.format(
        "SELECT * FROM RecipeStep WHERE recipeId = %d AND recipeStepSectionId = %d ORDER BY id",
        recipeId, recipeStepSectionId));

    while (resultSet.next()) {
      String text = resultSet.getString(4);
      RecipeStep step = RecipeStep.newBuilder().setText(text).build();
      steps.add(step);
    }

    return steps;
  }
}
