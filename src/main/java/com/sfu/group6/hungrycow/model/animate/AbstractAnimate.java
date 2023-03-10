package com.sfu.group6.hungrycow.model.animate;

import com.sfu.group6.hungrycow.control.Direction;
import com.sfu.group6.hungrycow.driver.Board;
import com.sfu.group6.hungrycow.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * An AbstractAnimate represents an entity in a Board {@link Board}
 * which can move.
 */
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractAnimate extends AbstractEntity {
    private Direction facingDirection;

    /**
     * Move the animate object based on input Direction {@link Direction}.
     *
     * @param direction a Direction
     */
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
        setFacingDirection(direction);
    }
}
