package dev.markdw.munchy.rpc;

import dev.markdw.munchy.database.RecipeDatabase;
import dev.markdw.munchy.database.RecipeNotFoundException;
import dev.markdw.proto.Recipe;
import dev.markdw.proto.RecipeRequest;
import dev.markdw.proto.RecipeServiceGrpc;
import dev.markdw.proto.StatusResponse;
import io.grpc.stub.StreamObserver;
import java.sql.SQLException;
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
    } catch (SQLException e) {
      
    } catch (RecipeNotFoundException e) {
      
    }
    
  }

  @Override
  public void createRecipe(Recipe recipe, StreamObserver<StatusResponse> responseObserver) {
    
  }
}
