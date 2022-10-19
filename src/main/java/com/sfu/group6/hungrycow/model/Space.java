package com.sfu.group6.hungrycow.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Space extends AbstractEntity {
    private final boolean startingSpace;
    private final boolean endSpace;
    private final boolean barrierSpace;
}
