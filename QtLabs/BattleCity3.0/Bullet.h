#ifndef BULLET_H
#define BULLET_H
#include <MovingPhysicalObject.h>
#include <Enemy.h>

class Bullet : public MovingObject
{
public:
    Bullet(Direction direction, int x, int y);

    void collision_react(PhysicalObject *obj) override;
    void draw(QPainter *painter) override;
    void move(Direction direction) override;
};

#endif // BULLET_H
