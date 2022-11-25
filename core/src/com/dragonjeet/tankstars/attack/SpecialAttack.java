package com.dragonjeet.tankstars.attack;

import com.dragonjeet.tankstars.tank.Tank;

public class SpecialAttack implements AttackType {
    private int x, y;
    private final int speed;
    private int initialAngle;
    private final int fullDamage;

    public SpecialAttack(int x, int y, int speed, int initialAngle, int fullDamage) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.initialAngle = initialAngle;
        this.fullDamage = fullDamage;
    }

    public void move() {
        // move the attack if needed
        // this will not be required if the special attack directly blasts at the opposing tank's position
    }

    public boolean isSpecial() {
        return true;
    }

    public void attack(Tank tank) {
        // attack tank
        // calculate distance from tank and pass to dealDamageTo()
    }

    public void dealDamageTo(Tank tank, int distanceFromTank) {
        // deal damage to tank based on distance or otherwise
    }
}
