package com.sfu.group6.hungrycow.model.animate;

import com.sfu.group6.hungrycow.model.inanimate.AbstractInanimate;
import com.sfu.group6.hungrycow.model.inanimate.Punishment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Player extends AbstractAnimate {
    private int score;

    public void punishPlayer(Punishment punishment) {
        setScore(getScore() - punishment.getValue());
    }

    public void rewardPlayer(AbstractInanimate reward) {
        setScore(getScore() + reward.getValue());
    }
}
