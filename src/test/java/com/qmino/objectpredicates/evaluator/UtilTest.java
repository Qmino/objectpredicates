package com.qmino.objectpredicates.evaluator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UtilTest {

    @Test
    @DisplayName("Test like")
    public void testLike() {
        Assertions.assertTrue(Util.like("abc", "a_c"));      // true
        Assertions.assertTrue(Util.like("abcde", "a%c%e"));  // true
        Assertions.assertFalse(Util.like("abc", "a\\%c"));    // false
        Assertions.assertTrue(Util.like("a%c", "a\\%c"));    // true
        Assertions.assertTrue(Util.like("a_c", "a\\_c"));    // true
        Assertions.assertTrue(Util.like("a_c", "a_c"));      // true
    }
}
