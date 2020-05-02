package com.github.christopheml.fastblocks.random;

import java.security.SecureRandom;
import java.util.Random;

public class Rng {

    private static final Random random = new SecureRandom();

    public static <T extends Enum<?>> T pick(Class<T> enumClass){
        var i = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[i];
    }

}
