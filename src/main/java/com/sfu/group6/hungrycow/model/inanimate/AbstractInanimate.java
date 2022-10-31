package com.sfu.group6.hungrycow.model.inanimate;


import com.sfu.group6.hungrycow.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractInanimate extends AbstractEntity {
    private final int value;
}
