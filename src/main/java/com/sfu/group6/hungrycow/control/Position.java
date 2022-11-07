package com.sfu.group6.hungrycow.control;

import lombok.Builder;
import lombok.Data;

/**
 * A Position represents the coordinate of an entity's position respective to the board.
 * A position consist of values x and y coordinates.
 */
@Data
@Builder
public class Position {
    @Builder.Default
    private int x = 0;
    @Builder.Default
    private int y = 0;
}
