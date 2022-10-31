package com.sfu.group6.hungrycow.model;

import com.sfu.group6.hungrycow.model.AbstractEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Space extends AbstractEntity {
    @Builder.Default
    private final boolean startingSpace = false;
    @Builder.Default
    private final boolean endSpace = false;
    @Builder.Default
    private final boolean barrierSpace = false;
}
