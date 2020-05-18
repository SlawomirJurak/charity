package pl.sgnit.charity.util;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class PasswordCheckerTest {

    private PasswordChecker passwordChecker = new PasswordChecker();

    @Test
    public void isRightLength() {
        String password = "12345678";

        boolean result = passwordChecker.isRightLength(password);

        assertEquals(true, result);
    }

    @Test
    public void isNotRightLength() {
        String password = "123";

        boolean result = passwordChecker.isRightLength(password);

        assertEquals(false, result);
    }

    @Test
    void containsSmallLetter() {
        String password = "a12sw34rDD";

        boolean result = passwordChecker.containsSmallLetter(password);

        assertEquals(true, result);
    }

    @Test
    void notContainsSmallLetter() {
        String password = "1234!@DD";

        boolean result = passwordChecker.containsSmallLetter(password);

        assertEquals(false, result);
    }

    @Test
    void containsCapitalLetter() {
        String password = "a12sw34rDD";

        boolean result = passwordChecker.containsSmallLetter(password);

        assertEquals(true, result);
    }

    @Test
    void notContainsCapitalLetter() {
        String password = "1234!@swqa";

        boolean result = passwordChecker.containsSmallLetter(password);

        assertEquals(true, result);
    }

    @Test
    void containsDigit() {
        String password = "a12sw34rDD";

        boolean result = passwordChecker.containsSmallLetter(password);

        assertEquals(true, result);
    }

    @Test
    void notContainsDigit() {
        String password = "aswrDD";

        boolean result = passwordChecker.containsSmallLetter(password);

        assertEquals(true, result);
    }

    @Test
    void containsSpecialCharacter() {
        String password = "a12sw#$DD";

        boolean result = passwordChecker.containsSmallLetter(password);

        assertEquals(true, result);
    }

    @Test
    void notContainsSpecialCharacter() {
        String password = "a12sw34rDD";

        boolean result = passwordChecker.containsSmallLetter(password);

        assertEquals(true, result);
    }
}
