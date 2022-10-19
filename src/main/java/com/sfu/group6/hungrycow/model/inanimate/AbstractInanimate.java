package com.sfu.group6.hungrycow.model.inanimate;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@RequiredArgsConstructor
public abstract class AbstractInanimate {
    private final int value;
}
