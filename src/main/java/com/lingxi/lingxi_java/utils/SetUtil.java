package com.lingxi.lingxi_java.utils;

import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SetUtil {
    public static <T> DiffResult<T> diffSet(Set<T> left, Set<T> right) {
        Set<T> leftOnly = new HashSet<>();
        Set<T> rightOnly = new HashSet<>();
        Set<T> both = new HashSet<>();
        if (CollectionUtils.isEmpty(left)) {
            rightOnly.addAll(right);
        } else if (CollectionUtils.isEmpty(right)) {
            leftOnly.addAll(left);
        } else {
            left.forEach(x -> {
                if (right.contains(x)) {
                    both.add(x);
                } else {
                    leftOnly.add(x);
                }
            });
            rightOnly.addAll(right.stream().filter(x -> !left.contains(x)).collect(Collectors.toSet()));
        }
        return new DiffResult<T>(leftOnly, rightOnly, both);
    }
}
