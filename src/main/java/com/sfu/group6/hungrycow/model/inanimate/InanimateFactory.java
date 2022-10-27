package com.sfu.group6.hungrycow.model.inanimate;

public class InanimateFactory {

    public AbstractInanimate makeInanimateEntity(AbstractInanimate inanimateEntityType) {

        if(inanimateEntityType instanceof BonusReward) {

            return new BonusReward(BonusReward.builder());

        } else if (inanimateEntityType instanceof Punishment) {

            return new Punishment(Punishment.builder());

        } else if (inanimateEntityType instanceof RegularReward) {

            return new RegularReward(RegularReward.builder());

        } else return null;

    }
}
