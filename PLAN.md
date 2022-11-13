# About

This is a living plan for the project that will be updated as the project evolves.

## Objects and their Attributes/Methods

This section contains the classes that are (currently) identified in the project.

- Tank
  - Attributes
    - Health
    - Position
    - Angle of the attack
    - Power of the attack
    - Fuel (resets every turn)
  - Methods
    - Move (and decrease fuel)
    - Change Angle of the attack
    - Change Power of the attack
    - Shoot / Launch attack
    - Recoil
    - Decrease Health
- Bullet
  - Attributes
    - Position
    - Angle of the attack
    - Speed
    - Damage
  - Methods
    - Launch
    - Deal damage
- Powerups/Drops (Drop from the sky)
  - Attributes
    - Position (must be dynamic)
  - Methods
    - Apply powerup
- GUI Elements
  - Ground
  - Sky
  - Main Screen and Pause Menu (???)
- Pause Menu
  - Methods
    - Save Game
    - Resume Game
- Main Menu
  - Methods
    - Start New Game
    - Load Saved Game
    - Exit Game

## Possible Interfaces

This section contains the interfaces, with a list of their common functionalities that are (currently) identified in the project.

- Offensive Objects
  - Who implements this
    - Bullet
    - Special Attack
  - Common Functionalities
    - Launch
    - Deal damage
- Powerups/Drops (Drop from the sky)
  - Who implements this
    - Bonus Health Powerup
    - Extra Fuel Powerup
    - Bonus Movement Speed Powerup
    - Extra Damage Powerup
    - (Maybe) Shield Powerup
  - Common Functionalities
    - Dynamic Position

## Possible cases of Inheritance

This section contains the cases of inheritance (a list of class relationships) that are (currently) identified in the project.

- Tank
  - Tank Type 1
  - Tank Type 2
  - Tank Type 3
- (For bonus) Bullet (There can be different types of bullets, for example one that can pierce through the ground, one that can bounce off the ground, or one that can explode on the target directly)
  - Bullet Type 1
  - Bullet Type 2
  - Bullet Type 3
