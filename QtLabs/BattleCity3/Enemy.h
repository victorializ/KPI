#ifndef ENEMY_H
#define ENEMY_H
#include <MovingObject.h>
#include <QPainter>
#include <Player.h>

class Enemy : public MovingObject
{
public:
    Enemy(Direction direction, int x, int y);

    void collision_react(PhysicalObject *obj) override;
    void draw(QPainter *painter) override;
    void move(Direction direction) override;
    void setRandomDirection();

private:
    void reverseDirection();
    void changeDirection();
};

#endif // ENEMY_H
