package com.sfu.group6.hungrycow.model.animate;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class Animate extends AbstractEntity {
    public void move(Direction direction) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        switch (direction) {
            case UP -> getPosition().setX(y - 1);
            case DOWN -> getPosition().setX(y + 1);
            case LEFT -> getPosition().setY(x - 1);
            case RIGHT -> getPosition().setY(x + 1);
            case NEUTRAL -> {
            } // do nothing
            default -> throw new IllegalArgumentException("Error: Invalid direction input provided");
        }
    }
}
