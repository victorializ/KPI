#ifndef TANK_H
#define TANK_H

#include <GameObject.h>
#include <Bullet.h>

class Tank: public GameObject {
     Q_OBJECT

public:
    Tank(Direction direction, int width, int height) : GameObject(direction, width, height) {}
    void shoot(Bullet* bullete);

protected:
    int moveTank(int speed);
};

#endif // TANK_H
