package dev.markdw.munchy.recipe;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import dev.markdw.munchy.rpc.RecipeService;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

public class RecipeServer {
  private static final int PORT = 50051;
  
  private Server server;
  private RecipeService recipeService;

  @Inject
  RecipeServer(RecipeService recipeService) {
    this.recipeService = recipeService;
  }
 
  public void start() throws IOException, InterruptedException {
    server = Grpc.newServerBuilderForPort(PORT, InsecureServerCredentials.create())
        .addService(recipeService).build().start();    

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        RecipeServer.this.stop();
      } catch (InterruptedException e) {
        e.printStackTrace(System.err);
      }
      System.out.println("Server shut down");
    }));

    this.blockUntilShutdown();
  }

  private void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }
}
