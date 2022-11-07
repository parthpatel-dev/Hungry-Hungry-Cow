package com.sfu.group6.hungrycow.model.animate;

import com.sfu.group6.hungrycow.driver.Board;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * An Enemy represents an enemy in a Board {@link Board}.
 */
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Enemy extends AbstractAnimate {
}
