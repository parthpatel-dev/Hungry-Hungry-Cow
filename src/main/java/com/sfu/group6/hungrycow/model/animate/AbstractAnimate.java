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
public abstract class AbstractAnimate extends AbstractEntity {
    public void move(Direction direction) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        switch (direction) {
            case UP -> getPosition().setY(y - 1);
            case DOWN -> getPosition().setY(y + 1);
            case LEFT -> getPosition().setX(x - 1);
            case RIGHT -> getPosition().setX(x + 1);
            default -> throw new IllegalArgumentException("Error: Invalid direction input provided");
        }
    }
}
