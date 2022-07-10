package com.lingxi.lingxi_java.utils;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetUtilTest {

    @Test
    void diffSetCorrectly() {
        Set<Integer> set1 = Set.of(0, 1, 2, 3);
        Set<Integer> set2 = Set.of(2, 3, 4, 5);
        DiffResult<Integer> diffResult = SetUtil.diffSet(set1, set2);
        assertEquals(diffResult.getLeftOnly(), Set.of(0, 1));
        assertEquals(diffResult.getRightOnly(), Set.of(4, 5));
        assertEquals(diffResult.getBoth(), Set.of(2, 3));
    }
}