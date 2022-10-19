package com.sfu.group6.hungrycow.model;

import com.sfu.group6.hungrycow.control.Position;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@RequiredArgsConstructor
public abstract class AbstractEntity {
    private final Position position;
}
