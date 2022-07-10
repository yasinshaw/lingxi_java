package com.lingxi.lingxi_java.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class DiffResult<T> {
    private Set<T> leftOnly;
    private Set<T> rightOnly;
    private Set<T> both;
}
