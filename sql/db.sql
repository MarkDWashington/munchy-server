CREATE TABLE IF NOT EXISTS Recipe (
  id INT NOT NULL,
  title TEXT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS RecipeIngredientSection (
  recipeId INT NOT NULL,
  id INT NOT NULL,
  heading TEXT NOT NULL,
  PRIMARY KEY (recipeId, id),
  FOREIGN KEY (recipeId) REFERENCES Recipe(id)
);

-- CREATE TABLE IF NOT EXISTS RecipeIngredient (
  
-- );

CREATE TABLE IF NOT EXISTS RecipeStepSection (
  recipeId INT NOT NULL,
  id INT NOT NULL,
  heading TEXT NOT NULL,
  PRIMARY KEY (recipeId, id),
  FOREIGN KEY (recipeId) REFERENCES Recipe(id)
);

CREATE TABLE IF NOT EXISTS RecipeStep (
  recipeId INT NOT NULL,
  recipeStepSectionId INT NOT NULL,
  id INT NOT NULL,
  text TEXT NOT NULL,
  PRIMARY KEY (recipeId, recipeStepSectionId, id),
  FOREIGN KEY (recipeId, recipeStepSectionId) REFERENCES RecipeStepSection(recipeId, id)
);
