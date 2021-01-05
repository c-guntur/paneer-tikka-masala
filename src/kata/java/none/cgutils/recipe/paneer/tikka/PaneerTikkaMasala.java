package none.cgutils.recipe.paneer.tikka;

import none.cgutils.recipe.paneer.tikka.parts.Part1CheckIngredients;
import none.cgutils.recipe.paneer.tikka.parts.Part2aMakePaneerTikka;
import none.cgutils.recipe.paneer.tikka.parts.Part2bMakeMasala;
import none.cgutils.recipe.paneer.tikka.parts.Part3Cooking;
import none.cgutils.recipe.paneer.tikka.utils.MyThreadFactory;
import none.cgutils.recipe.paneer.tikka.utils.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PaneerTikkaMasala {

    private static final Logger LOGGER = LogManager.getLogger(PaneerTikkaMasala.class);

    public static void main(String[] args) throws Exception {

        System.setProperty("isThreadContextMapInheritable", "true");
        System.setProperty("log4j2.isThreadContextMapInheritable", "true");
        System.setProperty("log4j.configuration", "log4j2.xml");

        final ExecutorService executor =
                Executors.newFixedThreadPool(10, new MyThreadFactory());

        Timer overallTimer = new Timer("Paneer Tikka Masala Timer");
        overallTimer.start();

        LOGGER.info("\n\nHere goes the Paneer Tikka Masala !!!" +
                "\n--------------------------------------");

        Part1CheckIngredients checkIngredients = new Part1CheckIngredients();
        Part2aMakePaneerTikka makePaneerTikka = new Part2aMakePaneerTikka();
        Part2bMakeMasala makeMasala = new Part2bMakeMasala(executor);
        Part3Cooking cooking = new Part3Cooking(executor);

        // TODO:
        //  Uncomment the below line
//        checkIngredients.checkIngredients();

        // TODO:
        //  Prepare the Paneer Tikka and then marinate them using an executor.
        // HINT:
        //  Use a thenComposeAsync() with the executor.
        CompletableFuture<String> paneerTikka =
                CompletableFuture.supplyAsync(() -> "Fix this");

        // TODO:
        //  Uncomment the below line
//        CompletableFuture<String> masala = makeMasala.prepareMasala();

        // TODO:
        //  Ensure that BOTH paneerTikka and masala parts are completed.
        // HINT:
        //  Use the allOf() to ensure both are completed.
        //  Then join the new CompletableFuture.
        CompletableFuture<Void> makeRecipeParts = new CompletableFuture<>();

        // TODO:
        //  Uncomment the below three lines
//        LOGGER.info(paneerTikka.get());
//        LOGGER.info(masala.get());
//        cooking.cook().join();

        // TODO:
        //  Comment the next two lines. These just exist so we don't hang the program in the kata.
        paneerTikka.get();
        makeRecipeParts.complete(null);

        overallTimer.end();
        LOGGER.info("Paneer Tikka Masala ready in {}", overallTimer.getDuration());

        // TODO:
        //  Comment the next two lines
        paneerTikka.get(5L, TimeUnit.SECONDS);
        makeRecipeParts.get(5L, TimeUnit.SECONDS);

        executor.awaitTermination(200, TimeUnit.MILLISECONDS);

        System.exit(1);
    }
}

/*
    NOTES
    For a vegan version, you can skip the butter and cream and use tofu in place of paneer.
    The paneer should ideally by grilled so in case you have a grill use that to make the tikka.
    Use 1/2 teaspoon paprika powder in the curry for a deep red/orange color.
    I used paprika powder while marinating the paneer but forgot to add in the curry.
    If you want, you can strain the pureed mixture before putting it back in the pan.
    This will just make the curry totally smooth.
    The quantity of butter and cream in the recipe depends on your taste and preference.
    The original recipe had an additional tablespoon of butter which I didn't add.
    You can also drizzle around 1/2 teaspoon of lemon juice on the curry once it's done.
 */

