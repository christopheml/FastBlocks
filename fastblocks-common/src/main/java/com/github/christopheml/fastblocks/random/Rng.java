package com.github.christopheml.fastblocks.random;

import java.security.SecureRandom;
import java.util.Random;

public class Rng {

    private static final Random random = new SecureRandom();

    public static <T extends Enum<?>> T pick(Class<T> enumClass){
        var i = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[i];
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }
}
