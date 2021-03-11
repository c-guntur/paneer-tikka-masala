package none.cgutils.recipe.paneer.tikka.parts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static none.cgutils.recipe.paneer.tikka.parts.RecipePart.RECIPE_PART;
import static none.cgutils.recipe.paneer.tikka.parts.RecipePart.delayMinutes;

/*
    Instructions for Masala

    1. Heat 1 tbsp of butter and 1 tbsp of oil in a pan.
    2. Once hot add cinnamon stick and cumin seeds.
    3. Once the seeds crackle, add the chopped onions, crushed garlic pods and chopped ginger.
    4. Fry till onions turn golden brown in color.
    5. Once onions are done, add the tomatoes and the curry powder and mix.
    6. Also add the the salt and sugar.
    7. Cook the tomatoes on medium flame till they are soft, around 6-7 minutes.
    8. Once the mixture is cooked, switch off the flame and allow it to cool a bit.
    9. Pour the mixture into a blender and puree it. Set aside.
*/
public class SolutionPart2bMakeMasala {

    private static final Logger LOGGER = LogManager.getLogger(SolutionPart2bMakeMasala.class);

    private static final String RECIPE_PART_VALUE = "Masala";

    private final Executor executor;

    public SolutionPart2bMakeMasala(Executor executor) {

        this.executor = executor;
    }

    CompletableFuture<String> chopOnions() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Chopping Onions");
            delayMinutes(
                    3L,
                    "Chopping Onions");
            return "Chopped Onions";
        }, executor);
    }

    CompletableFuture<String> chopGinger() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Chopping Ginger");
            delayMinutes(
                    3L,
                    "Chopping Ginger");
            return "Chopped Ginger";
        }, executor);
    }

    CompletableFuture<String> chopGarlic() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Chopping Garlic");
            delayMinutes(
                    3L,
                    "Chopping Garlic");
            return "Chopped Garlic";
        }, executor);
    }

    CompletableFuture<String> chopTomatoes() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Chopping Tomatoes");
            delayMinutes(
                    3L,
                    "Chopping Tomatoes");
            return "Chopped Tomatoes";
        }, executor);
    }

    CompletableFuture<String> chopOnionsGarlicAndGinger() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        CompletableFuture<String> choppingStarter = CompletableFuture
                .supplyAsync(() -> "Chopping the main ingredients for Paneer Tikka");

        String choppingComplete = "Completed chopping paneer, ginger, garlic and tomatoes";
        String interimEmptyMessage = "";

        // TO DO:
        //  Use your helpers to chop things at the same time! Use a future that
        //  will run irrespective of success or failure.
        // HINT:
        //  • Chain three instance method thenCombine()s on choppingStarter with executors,
        //    return an empty string for the BiFunction.
        //  • Return the declared 'interimEmptyMessage' for each "stage".
        //  • Chain an instance method handleAsync() on the last stage, with an executor
        //    to run with a possible exception or successful result 'choppingComplete' declared above..
        CompletableFuture<String> overallChopping = choppingStarter
                .thenCombine(chopOnions(), (choppingStarterMessage, choppingOnionsMessage) -> {
                    LOGGER.info(choppingStarterMessage);
                    LOGGER.info(choppingOnionsMessage);
                    return interimEmptyMessage;
                })
                .thenCombine(chopGarlic(), (previousStatus, chopGarlicMessage) -> {
                    LOGGER.info(chopGarlicMessage);
                    return interimEmptyMessage;
                })
                .thenCombine(chopGinger(), (previousStatus, chopGingerMessage) -> {
                    LOGGER.info(chopGingerMessage);
                    return interimEmptyMessage;
                })
                .thenCombine(chopTomatoes(), (previousStatus, chopTomatoesMessage) -> {
                    LOGGER.info(chopTomatoesMessage);
                    return interimEmptyMessage;
                })
                .handleAsync(
                        (previousStatus, throwable) ->
                                choppingComplete,
                        executor);

        overallChopping.join();

        return overallChopping;
    }

    CompletableFuture<String> heatButterAndOil(String previousTaskMessage) {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
        LOGGER.info(previousTaskMessage);

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Heating Butter and Oil in a pan");
            delayMinutes(
                    5L,
                    "Heating Butter & Oil");
            return "Done heating Butter & Oil";
        }, executor);

    }

    CompletableFuture<String> crackleCuminAndCinnamon(String message) {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        LOGGER.info(message);

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Added Cumin and Cinnamon to the pan");
            delayMinutes(
                    2L,
                    "Waiting for the Cumin to crackle");
            return "Cumin started to crackle";
        }, executor);
    }

    String fryOnionsGarlicAndGinger(String message) {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        LOGGER.info(message);

        CompletableFuture.runAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Added Onions, Garlic and Ginger to the pan");
            delayMinutes(
                    2L,
                    "Stir-frying the Onions to change color");
        }, executor).join();

        return "Onions turning golden brown. Garlic and Ginger sizzling";
    }

    void addTomatoesAndCurryPowderAndCook() {

        LOGGER.info("Adding salt");
        LOGGER.info("Adding sugar");
        LOGGER.info("Adding tomatoes");
        LOGGER.info("Adding curry powder");
        CompletableFuture.runAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            delayMinutes(
                    7L,
                    "Cooking tomatoes");
        }, executor).join();
    }

    void coolAndPuree(Void unused) {

        CompletableFuture.runAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            delayMinutes(
                    3L,
                    "Cooling the cooked mix");
            LOGGER.info("Blending the masala mix");
            delayMinutes(5L, "Making the masala Puree");
        }, executor).join();
    }

    public CompletableFuture<String> prepareMasala() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
        LOGGER.info("Making the Masala");

        String successMessage = "Base ingredients are cooked";

        // TO DO:
        //  Chain the chopping, heating oil, crackling spices,
        //  frying the allia and ginger in an asynchronous set of operations,
        //  then log the result asynchronously. Finally log a message, asynchronously
        //  that the base ingredients are cooked.
        // HINT:
        //  • Chain instance methods thenComposeAsync(), thenApplyAsync(), thenAcceptAsync()
        //    and thenRunAsync() with executors as stages on chopOnionsGarlicAndGinger():
        //  • Use the thenComposeAsync() to heat and to crackle, on chopOnionsGarlicAndGinger().
        //  • Chain a thenApplyAsync() to fry onions, garlic and ginger.
        //  • Chain a thenAcceptAsync() to log the output of the above chained operations.
        //  • Chain a thenRunAsync() to log the 'successMessage' declared above.
        CompletableFuture<Void> cookingTheBaseIngredients = chopOnionsGarlicAndGinger()
                .thenComposeAsync(this::heatButterAndOil, executor)
                .thenComposeAsync(this::crackleCuminAndCinnamon, executor)
                .thenApplyAsync(this::fryOnionsGarlicAndGinger, executor)
                .thenAcceptAsync(LOGGER::info, executor)
                .thenRunAsync(() -> {
                    ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
                    LOGGER.info(successMessage);
                }, executor);

        // How to use a Void
        CompletableFuture<Void> cookTomatoesInSpiceBase =
                cookingTheBaseIngredients
                        .whenComplete((Void v, Throwable throwable) -> addTomatoesAndCurryPowderAndCook());

        // Still might use the executor, if it is around. See logs.
        cookTomatoesInSpiceBase.thenAccept(this::coolAndPuree);
        cookTomatoesInSpiceBase.join();

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            return "Done making the Masala";
        }, executor);
    }
}