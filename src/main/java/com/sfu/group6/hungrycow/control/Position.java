package com.sfu.group6.hungrycow.control;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Position {
    private int x = 100;
    private int y = 100;
}
