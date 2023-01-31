package dev.markdw.munchy.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.inject.Inject;
import javax.inject.Singleton;
import dev.markdw.proto.Recipe;

@Singleton
public class RecipeDatabase {

  @Inject
  RecipeDatabase() {}

  public Recipe getRecipe(int recipeId) {
    return null;
  }
}
