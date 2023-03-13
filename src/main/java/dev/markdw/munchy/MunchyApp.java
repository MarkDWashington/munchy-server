package dev.markdw.munchy;

import java.io.IOException;
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

  public static void main(String[] args) throws IOException, InterruptedException {
    if (args.length < 3) {
      System.out.println("Usage: MunchyApp <DB_URL> <DB_USERNAME> <DB_PASSWORD>");
      System.exit(1);
    }

    String dbUrl = args[0];
    String dbUsername = args[1];
    String dbPassword = args[2];

    MunchyAppFactory munchyAppFactory = DaggerMunchyAppFactory.builder()
        .environmentModule(new EnvironmentModule(dbUrl, dbUsername, dbPassword)).build();
    MunchyApp munchyApp = munchyAppFactory.getMunchyApp();

    System.out.println("Starting RPC server");
    munchyApp.start();
  }
}
