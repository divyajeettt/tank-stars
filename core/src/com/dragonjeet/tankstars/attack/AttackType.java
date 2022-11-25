package com.dragonjeet.tankstars.attack;

import com.dragonjeet.tankstars.tank.Tank;

public interface AttackType {
    public void attack(Tank tank);
    public boolean isSpecial();
    public void move();
    public void dealDamageTo(Tank tank, int distanceFromTank);
}
