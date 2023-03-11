package dev.markdw.munchy.rpc;

import dev.markdw.munchy.database.RecipeDatabase;
import dev.markdw.munchy.database.RecipeNotFoundException;
import dev.markdw.proto.CreateRecipeResponse;
import dev.markdw.proto.Recipe;
import dev.markdw.proto.RecipeRequest;
import dev.markdw.proto.RecipeServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;
import javax.inject.Inject;

public class RecipeService extends RecipeServiceGrpc.RecipeServiceImplBase {

  private RecipeDatabase recipeDatabase;

  @Inject
  public RecipeService(RecipeDatabase recipeDatabase) {
    this.recipeDatabase = recipeDatabase;
  }

  @Override
  public void getRecipe(RecipeRequest request, StreamObserver<Recipe> responseObserver) {
    try {
      Recipe recipe = recipeDatabase.getRecipe(request.getId());
      responseObserver.onNext(recipe);
      responseObserver.onCompleted();
    } catch (RecipeNotFoundException e) {
      responseObserver.onError(Status.NOT_FOUND.withDescription("Recipe not found").asException());
      Status.NOT_FOUND.asException();
    } catch (Exception e) {
      System.err.println("Something went wrong");
      e.printStackTrace(System.err);
    }
  }

  @Override
  public void createRecipe(Recipe recipe, StreamObserver<CreateRecipeResponse> responseObserver) {
    
  }
}
