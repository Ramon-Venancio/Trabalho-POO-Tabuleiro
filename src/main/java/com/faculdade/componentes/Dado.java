
package com.faculdade.componentes;
import java.util.Random;

/**
 *
 * @author vinan
 */
public class Dado {
    private static Random random = new Random();
    public static int rolarDados() {
        return random.nextInt(6) + 1;
    }
}
