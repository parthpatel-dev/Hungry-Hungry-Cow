package com.sfu.group6.hungrycow.model.animate;

import com.sfu.group6.hungrycow.driver.Board;
import com.sfu.group6.hungrycow.model.inanimate.AbstractInanimate;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * A Player represents a player in a Board {@link Board}
 * and keeps track of its score.
 * A player can be punished by a Punishment {@link Punishment}.
 * A player can be rewarded by a Reward {@link com.sfu.group6.hungrycow.model.inanimate.RegularReward}.
 * A player can be rewarded by a BonusReward {@link com.sfu.group6.hungrycow.model.inanimate.BonusReward}.
 */
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Player extends AbstractAnimate {
    private int score;

    /**
     * Reduce the score of the player by the input Punishment's value.
     *
     * @param punishment a Punishment object
     */
    public void punishPlayer(Punishment punishment) {
        setScore(getScore() - punishment.getValue());
    }

    /**
     * Increase the score of the player by the input Reward's value.
     *
     * @param reward a RegularReward{@link com.sfu.group6.hungrycow.model.inanimate.RegularReward}
     *               or BonusReward {@link com.sfu.group6.hungrycow.model.inanimate.BonusReward}
     */
    public void rewardPlayer(AbstractInanimate reward) {
        setScore(getScore() + reward.getValue());
    }
}
