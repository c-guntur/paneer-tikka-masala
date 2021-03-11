package none.cgutils.recipe.paneer.tikka.parts;

import none.cgutils.recipe.paneer.tikka.exception.NoLemonJuiceGarnishException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import static none.cgutils.recipe.paneer.tikka.parts.RecipePart.RECIPE_PART;
import static none.cgutils.recipe.paneer.tikka.parts.RecipePart.delayMinutes;

/*
    INSTRUCTIONS

    1. Roast the marinated paneer till golden brown in color from both sides.
    2. Pour the pureed masala in a pan/wok/kadhai and add water to it.
    3. Stir and add cream and chopped cilantro (coriander) leaves.
    4. Cover and cook for 4-5 minutes on medium-low flame.
    5. Add the roasted paneer pieces, let it simmer for 2-3 minutes.
    6. Switch off the flame and garnish with chopped coriander leaves and cream.
*/
public class SolutionPart3Cooking {

    private static final Logger LOGGER = LogManager.getLogger(SolutionPart3Cooking.class);

    private static final String RECIPE_PART_VALUE = "Cooking";

    private final Executor executor;

    public SolutionPart3Cooking(Executor executor) {
        this.executor = executor;
    }

    CompletableFuture<Void> chopCilantro() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        CompletableFuture<Void> chopCilantro = CompletableFuture.runAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Chopping Cilantro");
            delayMinutes(
                    3L,
                    "Chopping Cilantro");
            LOGGER.info("Chopped Cilantro");
        }, executor);

        chopCilantro.join();

        return chopCilantro;
    }

    private CompletableFuture<Void> roastOrGrillPaneer() {
        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
        CompletableFuture.runAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Roasting/Grilling Paneer");
        }, executor).join();

        // TO DO:
        //  Create a delayed executor from the provided one.
        // HINT:
        //  • Use a static CompletableFuture.delayedExecutor() method
        //    with 20 second delay and current executor.
        Executor delayedExecutor = CompletableFuture.delayedExecutor(20, TimeUnit.SECONDS, this.executor);

        // TO DO:
        //  Use the delayedExecutor to ensure that we cannot grill earlier than roast.
        // HINT:
        //  Use the 'delayedExecutor' created above instead of the 'executor'.
        CompletableFuture<String> grillPaneer = CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            delayMinutes(25L, "Search for skewers");
            LOGGER.info("Searching for skewers to grill paneer");
            delayMinutes(15L, "Grilling marinated paneer");
            return "Done grilling paneer";
        }, delayedExecutor);

        CompletableFuture<String> ovenRoastPaneer = CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            delayMinutes(5L, "Preheating oven to 350°F");
            LOGGER.info("Oven preheated to 350°F");
            delayMinutes(10L, "Roasting marinated paneer");
            return "Done roasting paneer";
        }, executor);

        // TO DO:
        //  Use either of the grilling or roasting (whichever completes first.
        //  Replace the null completedFuture with the right anyOf call, then log the result.
        // HINT:
        //  • Use a static CompletableFuture.anyOf() method to pick the first CompletableFuture
        //    that completes, and supply both the 'grillPaneer' and 'ovenRoastPaneer'.
        //  • Chain a thenAcceptAsync() to log the one that completes first..
        return CompletableFuture.anyOf(grillPaneer, ovenRoastPaneer)
                .thenAcceptAsync(LOGGER::info, executor);
    }

    private CompletableFuture<String> heatPureedMasala(Void unused) {

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info("Heating pureed Masala");
            delayMinutes(5L, "Heating pureed masala");
            return "Done heating pureed masala";
        }, executor);
    }

    private CompletableFuture<String> addCreamAndCilantroAndCook(String message) {

        return CompletableFuture.supplyAsync(() -> {
            ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
            LOGGER.info(message);
            LOGGER.info("Adding cream, a few chopped cilantro leaves, water");
            LOGGER.info("Cooking");
            LOGGER.info("Adding roasted/grilled paneer");
            delayMinutes(3L, "Cooking the mix");
            return "Done heating pureed masala";
        }, executor);
    }

    private void garnish() {
        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);
        LOGGER.info("Garnishing");
        LOGGER.info("Adding remaining chopped cilantro");
        shouldGarnishWithLemonJuice();
    }

    /**
     * Check if Lemon Juice should be used for garnish.
     * Justification:
     * Use the complete() and completeExceptionally() based on a boolean.
     * Use a thenApply() for calling a function after the CF is complete.
     * Use an exceptionally() for performing an action if the CF returns with an Exception.
     */
    void shouldGarnishWithLemonJuice() {

        ThreadContext.put(RECIPE_PART, RECIPE_PART_VALUE);

        LOGGER.info("Lemon Juice as a Garnish? Depends on mood");
        String positiveMessage = "Lemon Juice ... yes!";
        String negativeMessage = "Lemon Juice ... no!";

        CompletableFuture<Boolean> booleanCompletableFuture = new CompletableFuture<>();

        Random randomBoolean = new Random();

        // TO DO:
        //  Use the boolean value to either complete or fail with a NoLemonJuiceGarnishException.
        // HINT:
        //  • Use the instance methods complete() or completeExceptionally() on
        //    booleanCompletableFuture depending on the boolean value.
        if (randomBoolean.nextBoolean()) {
            // complete() on booleanCompletableFuture can set a value (that can later be retrieved
            // by a get() or join() on the same. This value will only need to be set if the
            // booleanCompletableFuture has not yet been completed.
            booleanCompletableFuture.complete(true);
        } else {
            // completeExceptionally() on booleanCompletableFuture can set an Exception marking the
            // non-successful completion of the CompletableFuture. In this case, a new
            // NoLemonJuiceGarnishException with the negativeMessage is thrown.
            booleanCompletableFuture.completeExceptionally(
                    new NoLemonJuiceGarnishException(negativeMessage));
        }

        // TO DO:
        //  Handle the acceptance or exception based on the CompletableFuture success or failure.
        // HINT:
        //  • Use the instance method thenApply() on the booleanCompletableFuture to log the
        //    'positiveMessage' declared above at an info-level.
        //  • Chain an instance method exceptionally() to log the message of the exception.
        booleanCompletableFuture.thenApply(aBoolean -> {
            LOGGER.info(positiveMessage);
            return null;
        }).exceptionally(ex -> {
            LOGGER.warn(ex.getCause().getMessage());
            return false;
        });
    }

    public CompletableFuture<Void> cook() {
        // TO DO:
        //  Roast or grill the paneer,chop the cilantro then,
        //  heat the pureed masala, then the cream and cilantro.
        //  Then, Cook and then garnish.
        // HINT:
        //  • Use an static CompletableFuture.allOf() method to complete all of:
        //    • roasting/grilling => roastOrGrillPaneer()
        //    • chopping of cilantro => chopCilantro()
        //  • Chain an instance method thenComposeAsync(), with an executor, to:
        //    • heat/puree => heatPureedMasala()
        //    • cook with cream and cilantro => addCreamAndCilantroAndCook()
        //  • Chain an instance method thenRunAsync(), with an executor to garnish().
        return CompletableFuture.allOf(roastOrGrillPaneer(), chopCilantro())
                .thenComposeAsync(this::heatPureedMasala, executor)
                .thenComposeAsync(this::addCreamAndCilantroAndCook, executor)
                .thenRunAsync(this::garnish, executor);
    }
}
