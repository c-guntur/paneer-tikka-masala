package none.cgutils.recipe.paneer.tikka.parts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.concurrent.CompletableFuture;

import static none.cgutils.recipe.paneer.tikka.parts.RecipePart.RECIPE_PART;
import static none.cgutils.recipe.paneer.tikka.parts.RecipePart.delayMinutes;

/*
    Instructions for Paneer Tikka

    1. Cut the paneer into small cubes.
    2. Add in the chopped ginger, garlic.
    3. Add coriander powder, garam masala powder, paprika powder, salt and red chilli powder.
    4. Add in the yogurt.
    5. Mix until everything is well coated with the spices and yogurt.
    3. Cover and keep this marinated paneer in the refrigerator for 30-40 minutes.
*/
public class SolutionPart2aMakePaneerTikka {

    private static final Logger LOGGER = LogManager.getLogger(SolutionPart2aMakePaneerTikka.class);

    private static final String RECIPE_PART_VALUE = "Paneer Tikka";

    CompletableFuture<String> chopPaneer(String priorStatus) {

        // a supplyAsync takes a a Supplier<T> and returns a CompletableFuture<T>
        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info(priorStatus);
            LOGGER.info("Chopping Paneer");
            delayMinutes(
                    5L,
                    "Chopping Paneer");
            return "Chopped Paneer";
        });
    }

    CompletableFuture<String> chopGinger(String priorStatus) {

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info(priorStatus);
            LOGGER.info("Chopping Ginger");
            delayMinutes(
                    3L,
                    "Chopping Ginger");
            return "Chopped Ginger";
        });
    }

    CompletableFuture<String> chopGarlic(String priorStatus) {

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info(priorStatus);
            LOGGER.info("Chopping Garlic");
            delayMinutes(
                    3L,
                    "Chopping Garlic");
            return "Chopped Garlic";
        });
    }

    CompletableFuture<String> chopStuff() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        CompletableFuture<String> choppingStarter = CompletableFuture
                .supplyAsync(() -> "Chopping the main ingredients for Paneer Tikka");

        String choppingComplete = "Completed chopping paneer, ginger and garlic";

        // TO DO:
        //  Compose all chopping in a chained sequence and then log the success.
        //  Use either lambdas or method references, since each method takes a priorStatus String.
        // HINT:
        //  Chain three instance method thenCompose() on choppingStarter, pass the previous
        //  CompletableFuture's result.
        //  Once all chopping is done, call an instance method thenApply() on the chain to return a
        //  successful completion of the chopping.
        //  The return of the thenApply() is the choppingComplete string.
        return choppingStarter
                .thenCompose(this::chopPaneer)
                .thenCompose(s -> chopGinger(s))
                .thenCompose(s -> chopGarlic(s))
                .thenApply(s -> choppingComplete);
    }

    String mixStuff(String choppingStatus) {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        CompletableFuture.runAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info(choppingStatus);
            LOGGER.info("Set a mixing bowl");
            LOGGER.info("Added paneer cubes, ginger pieces and garlic chunks");
            LOGGER.info("Added coriander (cilantro seed) powder, garam masala powder, salt, red chilli powder");
            LOGGER.info("Added yogurt");
            delayMinutes(5L, "Mixing Tikka ingredients");
        }).join();

        return "Mixed all tikka ingredients. It looks great !";
    }

    public CompletableFuture<String> preparePaneerTikka() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
        LOGGER.info("Making the Paneer Tikka");

        // TO DO:
        //  Use a thenCompose to trigger a mix after everything is chopped.
        // HINT:
        //  Use an instance method thenCompose() on the chopStuff() result CompletableFuture
        //  and call a supplyAsync to mixStuff() passing in the output of the chopStuff().
        CompletableFuture<String> choppingAndMixingCompletableFuture = chopStuff()
                .thenCompose(
                        (String choppingStatus) ->
                                CompletableFuture.supplyAsync(() -> mixStuff(choppingStatus)));

        // TO DO:
        //  Get the result of the CompletableFuture, using a join(), to skip setting a
        //  try-catch block.
        // HINT: Use an instance method join() on choppingAndMixingCompletableFuture
        choppingAndMixingCompletableFuture.join();

        return choppingAndMixingCompletableFuture;
    }

    public CompletableFuture<String> marinateMixedTikkas() {

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Starting marination");
            LOGGER.info("Covered mixing bowl");
            LOGGER.info("Refrigerated for 30 min");
            delayMinutes(30L, "Marination time");
            return "Done marinating the Paneer Tikka!";
        });
    }
}
