package dev.markdw.munchy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.inject.Inject;
import dev.markdw.munchy.recipe.RecipeServer;
import dev.markdw.munchy.util.EnvironmentModule;

public class MunchyApp {

  private RecipeServer recipeServer;

  @Inject
  MunchyApp(RecipeServer recipeServer) {
    this.recipeServer = recipeServer;
  }

  void start() {
    try {
      recipeServer.start();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public static void main(String[] args) throws IOException, IllegalAccessException, InterruptedException, InvocationTargetException {
    if (!EnvironmentModule.verify()) {
      System.exit(1);
    }
    
    MunchyAppFactory munchyAppFactory = DaggerMunchyAppFactory.create();
    MunchyApp munchyApp = munchyAppFactory.getMunchyApp();

    System.out.println("Starting RPC server");
    munchyApp.start();
  }
}
