package none.cgutils.recipe.paneer.tikka.parts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class Part1CheckIngredientsTest {

    Part1CheckIngredients checkIngredients;

    @BeforeEach
    public void setup() {
        Part1CheckIngredients checkIngredients = new Part1CheckIngredients();
    }

    @Test
    void checkIngredients() {
        checkIngredients.checkIngredients();
    }

    @Test
    void checkForPaprikaAndChilliPowder() {
        checkIngredients.checkForPaprikaAndChilliPowder();
    }

    @Test
    void successfulFindChilliPowder() {
        Future<String> chilliPowederFound = checkIngredients.successfulFindChilliPowder();
        assertTrue(chilliPowederFound instanceof CompletableFuture);
    }

    @Test
    void unsuccessfulFindPaprikaPowder() {
        Future<String> paprikaNotFound = checkIngredients.unsuccessfulFindPaprikaPowder();
        assertTrue(paprikaNotFound instanceof CompletableFuture);
    }

    @Test
    void getGaramMasala() {
    }

    @Test
    void getPotsAndPansReady() {
    }

    @Test
    void shouldGarnishWithLemonJuice() {
    }
}