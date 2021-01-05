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

    1. Heat 1 tbsp of butter and 1 tbsp of oil in a pan. Once hot add cinnamon stick and cumin seeds.
    2. Once the seeds crackle, add the chopped onions, crushed garlic pods and chopped ginger. Fry till onions turn golden brown in color.
    3. Once onions are done, add the tomatoes and the curry powder and mix. Also add the the salt and sugar.
    4. Cook the tomatoes on medium flame till they are soft, around 6-7 minutes.
    5. Once the mixture is cooked, switch off the flame and allow it to cool a bit. Pour the mixture into a blender and puree it. Set aside.
*/
public class Part2bMakeMasala {

    private static final Logger LOGGER = LogManager.getLogger(Part2bMakeMasala.class);

    private static final String RECIPE_PART_VALUE = "Masala";

    private final Executor executor;

    public Part2bMakeMasala(Executor executor) {

        this.executor = executor;
    }

    public CompletableFuture<String> prepareMasala() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
        LOGGER.info("Making the Masala");

        // TODO:
        //  Fix the other TODOs below, then :
        //  Chain the chopping, heating oil, crackling spices,
        //  frying the allia and ginger in an asynchronous set of operations,
        //  then log the result asynchronously. Finally log a message, asynchronously
        //  that the base ingredients are cooked.
        // HINT:
        //  Use thenComposeAsync(), thenApplyAsync(), thenAcceptAsync()
        //  and thenRunAsync() with executors
        CompletableFuture<Void> cookingTheBaseIngredients = new CompletableFuture<>();









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

    CompletableFuture<String> chopOnionsGarlicAndGinger() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        CompletableFuture<String> choppingStarter = CompletableFuture
                .supplyAsync(() -> "Chopping the main ingredients for Paneer Tikka");

        // TODO:
        //  Use your helpers to chop things at the same time! Use a future that
        //  will run irrespective of success or failure.
        // HINT:
        //  Use a thenCombine() with an executor to combine, return an empty
        //  string for the BiFunction.
        //  Use a handleAsync() with an executor to run with a possible exception
        //  or successful result.
        CompletableFuture<String> overallChopping = choppingStarter;






















        overallChopping.join();

        return overallChopping;
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
}