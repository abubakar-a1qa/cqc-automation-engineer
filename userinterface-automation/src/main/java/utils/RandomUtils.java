package utils;

import org.apache.commons.lang3.RandomStringUtils;

public final class RandomUtils {
    private RandomUtils() {
    }

    public static String randomEmailName() {
        return RandomStringUtils.randomAlphabetic(6).toLowerCase();
    }

    public static String randomDomain() {
        return RandomStringUtils.randomAlphabetic(6).toLowerCase();
    }

    public static String strongPassword(String emailName) {
        String base = emailName != null && !emailName.isEmpty() ? emailName.substring(0, 1) : "a";
        String upper = RandomStringUtils.randomAlphabetic(1).toUpperCase();
        String lower = RandomStringUtils.randomAlphabetic(4).toLowerCase();
        String digits = RandomStringUtils.randomNumeric(2);
        String extra = RandomStringUtils.randomAlphabetic(2);
        return upper + lower + digits + base + extra;
    }
}
