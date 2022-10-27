package com.sfu.group6.hungrycow.model.animate;

public class AnimateFactory {

    public Animate makeAnimateEntity(Animate animateEntityType) {

        if(animateEntityType instanceof Player) {

            return new Player(Player.builder());

        } else if(animateEntityType instanceof Enemy) {

            return new Enemy(Enemy.builder());

        } else return null;

    }
}
