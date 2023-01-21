package dev.markdw.munchy.database;

import javax.inject.Inject;
import javax.inject.Singleton;
import dev.markdw.proto.Recipe;

@Singleton
public class RecipeDatabase {

  ConnectionPool connectionPool;

  @Inject
  RecipeDatabase(ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  public Recipe getRecipe(int recipeId) {
    return null;
  }
}
