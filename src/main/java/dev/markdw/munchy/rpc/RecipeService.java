package dev.markdw.munchy.rpc;

import dev.markdw.munchy.database.RecipeDatabase;
import dev.markdw.proto.Recipe;
import dev.markdw.proto.RecipeRequest;
import dev.markdw.proto.RecipeServiceGrpc;
import io.grpc.stub.StreamObserver;
import javax.inject.Inject;

public class RecipeService extends RecipeServiceGrpc.RecipeServiceImplBase {
  
  @Inject
  public RecipeService(RecipeDatabase database) {

  }

  @Override
  public void getRecipe(RecipeRequest request, StreamObserver<Recipe> responseObserver) {
    
  }
}
