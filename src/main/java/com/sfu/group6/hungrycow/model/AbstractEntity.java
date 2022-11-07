package com.sfu.group6.hungrycow.model;

import com.sfu.group6.hungrycow.control.Position;
import com.sfu.group6.hungrycow.driver.Board;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * An AbstractEntity is an abstract class which represents an entity in a Board
 * {@link Board}
 */
@SuperBuilder
@Data
@RequiredArgsConstructor
public abstract class AbstractEntity {
    private final Position position;
}
