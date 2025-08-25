package com.friendprojects.chessapp.util;

import java.util.List;
import java.util.Random;

public class RandomProvider {
    private static final Random RANDOM = new Random();

    private RandomProvider() {}

    public static <T> T select(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }
}
