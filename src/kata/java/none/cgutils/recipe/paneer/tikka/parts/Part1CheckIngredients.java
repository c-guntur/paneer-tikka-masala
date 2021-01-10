package none.cgutils.recipe.paneer.tikka.parts;

import none.cgutils.recipe.paneer.tikka.exception.PaprikaNotFoundException;
import none.cgutils.recipe.paneer.tikka.utils.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static none.cgutils.recipe.paneer.tikka.parts.RecipePart.RECIPE_PART;
import static none.cgutils.recipe.paneer.tikka.parts.RecipePart.delayMinutes;

/*
    Paneer Tikka
        250 gms paneer
        3 tablespoons unsweetened yogurt
        1 teaspoon finely chopped ginger
        1 teaspoon finely chopped garlic
        1 teaspoon coriander (cilantro seed) powder
        ½ teaspoon garam masala
        ½ teaspoon paprika powder
        ¼ teaspoon red chili powder or to taste
        1 teaspoon salt to taste

    Masala/Curry
        1 large onion roughly chopped
        3-4 medium tomatoes roughly chopped
        1/2 teaspoon cumin seeds
        1 cinnamon stick1 or ½ teaspoon if powder
        3-4 garlic cloves crushed
        1/2 inch ginger cut into small pieces
        1.5 teaspoon curry powder
        1 tablespoon sugar
        2 tablespoons cream
        2 tablespoon chopped cilantro leaves and some more to garnish
        1 cup water or as required
        1 tablespoon oil
        1 tablespoon salted butter
        1 teaspoon salt to taste
*/
public class Part1CheckIngredients {

    private static final Logger LOGGER = LogManager.getLogger(Part1CheckIngredients.class);

    private static final String RECIPE_PART_VALUE = "Check Ingredients";

    private static final String INGREDIENT_BOUGHT = "Ingredient Bought!";
    private static final String DISHWASHER_RUNNING = "Dishwasher Running ...";

    /**
     * Check the ingredients required for the Paneer Tikka, the Masala as well as garnish.
     */
    public boolean checkIngredients() {

        // Used for logging the Mapped Diagnostic Context for each logging statement.
        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        LOGGER.info("Paneer (Indian Cottage Cheese)/Firm Tofu - 250g (½ lb)");
        LOGGER.info("Unsweetened Yogurt - 3 tablespoons");
        LOGGER.info("Ginger - ½ inch-long portion (or if crushed or paste - ½ teaspoon)");
        LOGGER.info("Garlic - 3 cloves of Garlic (or if crushed or paste - 1½ teaspoon)");
        LOGGER.info("Coriander (cilantro seed) Powder - 1 teaspoon");
        LOGGER.info("Salt - 2 teaspoons");
        LOGGER.info("Red Onion - 1 medium-sized or 1 large");
        LOGGER.info("Tomatoes - 4 medium-sized  or equivalent in other sizes");
        LOGGER.info("Cumin Seeds - ½ teaspoon");
        LOGGER.info("Cinnamon - 1 stick or ½ teaspoon if powder");
        LOGGER.info("Curry Powder - 1½ teaspoons");
        LOGGER.info("Sugar - 1 tablespoon");
        LOGGER.info("Cream - 2 tablespoons");
        LOGGER.info("Cilantro Leaves - 5 stems, if pre-chopped, 2 tablespoons");
        LOGGER.info("Water - 1 cup");
        LOGGER.info("Oil - 1 tablespoon");
        LOGGER.info("Butter - 1 tablespoon or equivalent");
        LOGGER.info("Lemon Juice - to taste");

        // Check both Paprika and Chilli Powder (we find Chilli Powder but no Paprika)
        checkForPaprikaAndChilliPowder();

        // Could not find Garam Masala, a store trip is necessary !
        if (getGaramMasala()) {
            LOGGER.info("Garam Masala (mixed Indian spices) - ½ teaspoon");
        } else {
            LOGGER.error("NEED to get Garam Masala from the store !");
        }

        return true;
    }

    /**
     * Check for Paprika and Red Chilli Powder.
     * The intent is to find Red Chilli Powder, but no Paprika Powder.
     * The find operations are done in other methods.
     * <p>
     * Justification:
     * Show how values from completed futures can be used.
     * A successful CF can use a get() operation,
     * while a failed CF can avail a whenComplete()
     */
    void checkForPaprikaAndChilliPowder() {

        // TODO:
        //  Fix the successfulFindChilliPowder()
        CompletableFuture<String> checkForChilliPowder =
                (CompletableFuture<String>) successfulFindChilliPowder();

        // TODO:
        //  Check if the checkForChilliPowder is done. Replace the 'false' with a check.
        // HINT:
        //  Use the isDone() method.
        if (false) {
            try {
                // a get() waits for the CF to complete, then returns its result.
                // TODO:
                //  Get the value from the checkForChilliPowder. Replace the empty string
                //  with the result of the CompletableFuture.
                // HINT:
                //  Use the get() method on checkForChilliPowder.
                //  Update the exceptions that are caught.
                LOGGER.info("{} - ½ teaspoon", "");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        // TODO:
        //  Fix the unsuccessfulFindPaprikaPowder()
        CompletableFuture<String> checkForPaprikaPowder =
                (CompletableFuture<String>) unsuccessfulFindPaprikaPowder();

        // a whenComplete() returns a new CompletionStage with the same result or exception as
        // this stage, that executes the given action when this stage completes.
        // TODO:
        //  Handle the exception from the checkForPaprikaPowder.
        // HINT:
        //  Use the whenComplete method and LOG an warn message.
        //  Log the exception message as a warning.


    }

    /**
     * Successfully find Red Chilli Powder.
     * <p>
     * Justification:
     * Show how to create a completedFuture(...)
     */
    Future<String> successfulFindChilliPowder() {

        String successMessage = "Red Chilli Powder";

        // completedFuture() returns a new CompletableFuture that is already completed with the
        // given value.
        // TODO:
        //  Create a completedFuture. Replace the null with a completedFuture.
        // HINT:
        //  Use CompletableFuture.completedFuture() static method
        //  with a successMessage.
        return null;
    }

    /**
     * Fail to find Paprika Powder.
     * Show how to create a failedFuture(...)
     */
    Future<String> unsuccessfulFindPaprikaPowder() {

        String exceptionMessage = "Paprika Powder not found";

        // failedFuture() returns a new CompletableFuture that is already completed
        // exceptionally with the given exception.
        // TODO:
        //  Create a failedFuture. Replace the null with a failedFuture.
        // HINT:
        //  Use CompletableFuture.failedFuture() with a PaprikaNotFoundException.
        //  Use the Exception's string constructor with exceptionMessage.
        return null;

    }

    /**
     * Go to the store to get Garam Masala. This takes about 30 minutes.
     * During the 30 mins, the dishwasher should be running.
     * Use a runAsync() with a delay while shopping at store
     * Show that while the CF isDone() is false, other activities can be performed.
     *
     * @return - A boolean representing whether or not Garam Masala was found.
     */
    boolean getGaramMasala() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
        LOGGER.warn("Uh oh ... No Garam Masala ... Going to the store !!!");

        Timer timer = new Timer("Garam Masala Shopping");

        String delayReason = "Store trip for Garam Masala";

        // Create an async process to go to the store
        // runAsync() takes a Runnable as an input parameter
        // and returns a CompletableFuture<Void>.
        // TODO:
        //  Create a CompletableFuture and run an async set of statements to
        //  delay for 30 mins while a store trip is made. Replace the CompletedFuture() with one
        //  that logs a delay or 30 mins for a "Store trip for Garam Masala".
        // HINT:
        //  Use the CompletableFuture.runAsync() static method.
        //  Add a ThreadContext for pretty logging:
        //      ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
        //  Use the delayMinutes(30, delayReason) from the RecipePart.java
        CompletableFuture<Void> getGaramMasalaFromStore = new CompletableFuture<>();







        // While the CompletableFuture is not done, other activities can be performed
        // isDone() determines if the CompletableFuture completed (success, failure or exception).
        while ((!getGaramMasalaFromStore.isDone())) {
            // TODO:
            //  Uncomment the below line
//            getPotsAndPansReady();
        }

        timer.end();

        ThreadContext.put(RECIPE_PART, INGREDIENT_BOUGHT);
        LOGGER.info("Returned from the store in {}", timer.getDuration());

        // Verify that the future is done
        if (getGaramMasalaFromStore.isDone()) {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            return true;
        }

        return false;
    }

    /**
     * Things to do while we're out grocery shopping for Garam Masala.
     */
    private void getPotsAndPansReady() {
        ThreadContext.put(RECIPE_PART, DISHWASHER_RUNNING);
        LOGGER.info("Dishwasher running");
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
