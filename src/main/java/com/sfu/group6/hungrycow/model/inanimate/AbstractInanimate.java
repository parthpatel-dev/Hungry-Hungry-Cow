package com.sfu.group6.hungrycow.model.inanimate;


import com.sfu.group6.hungrycow.driver.Board;
import com.sfu.group6.hungrycow.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * An AbstractInanimate represents an entity in a Board {@link Board}
 * which have a value.
 */
@SuperBuilder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractInanimate extends AbstractEntity {
    private final int value;
}
